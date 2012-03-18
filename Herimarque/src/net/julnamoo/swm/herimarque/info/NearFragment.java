package net.julnamoo.swm.herimarque.info;

import java.util.List;

import net.julnamoo.R;
import net.julnamoo.R.layout;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.util.CursorToItem;
import net.julnamoo.swm.herimarque.util.MapContainer;
import net.julnamoo.swm.herimarque.view.HeritageItemizedOverlay;
import net.julnamoo.swm.herimarque.view.LocationItemizedOverlay;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class NearFragment extends Fragment implements OnTouchListener{

	protected String tag = NearFragment.class.getSimpleName();

	protected Context mContext;
	protected MapView mapView;
	protected MapController mapController;
	protected HeritageItemizedOverlay heritageOverlay;
	protected LocationItemizedOverlay locationOverlay;

	protected long minTime;
	protected float minDistance;
	protected LocationManager locationManager;

	protected List<Overlay> mapOverlay;

	//for detecting event
	protected int currZoom;
	protected GeoPoint currStart;
	protected ImageView progress;

	public NearFragment(Context mContext, long minTime, float minDistance) 
	{
		this.mContext = mContext;
		this.minTime = minTime;
		this.minDistance = minDistance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null)
		{
			Log.d(tag, "check the savedInstanceState of near Fragment");
		}
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, locationListener);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, locationListener);

		heritageOverlay = new HeritageItemizedOverlay(getResources().getDrawable(R.drawable.pin2), mContext, getFragmentManager());
		locationOverlay = new LocationItemizedOverlay(getResources().getDrawable(R.drawable.pin1), mContext);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		Log.d(tag, "create nearFragment view");
		mapView = MapContainer.mapView;

		ViewGroup vg = (ViewGroup) mapView.getParent();
		if(vg != null)
		{
			vg.removeView(mapView);
		}
		//set progress
		progress = new ImageView(getActivity().getApplicationContext());
		progress.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.progress));
		progress.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL));
		progress.setVisibility(View.INVISIBLE);
		mapView.addView(progress);
		//get heritages
		currStart = mapView.getProjection().fromPixels(0, 0);
		new LoadMapItems().execute();

		mapView.setOnTouchListener(this);
		mapView.setBuiltInZoomControls(false);
		mapView.setSatellite(false);
		mapView.setTraffic(false);
		mapController = mapView.getController();
		mapOverlay = mapView.getOverlays();

		//init view
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(location == null)
		{
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if(location == null)
			{
				location = new Location(LocationManager.NETWORK_PROVIDER);
				location.setLatitude(Double.valueOf("37.57273"));
				location.setLongitude(Double.valueOf("126.9687"));
			}
		}
		Log.d(tag, "last location :" + location.getLatitude() + ","+location.getLongitude());
		Double lat = location.getLatitude() * 1E6;
		Double lng = location.getLongitude() * 1E6;
		GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
		mapController.setZoom(18);
		mapController.animateTo(point);
		currZoom = 18;
		setMyLocationOverlay(point);
		//expect heritageOverlay is updated
		mapOverlay.add(heritageOverlay);

		//add the Button for moving to the current location
		Button getMyLoc = new Button(mContext);
		getMyLoc.setBackgroundDrawable(getResources().getDrawable(R.drawable.myloc));
		LayoutParams sizeParam = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.TOP);
		getMyLoc.setLayoutParams(sizeParam);
		getMyLoc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				//get user's location and move to the point
				Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if(location == null)
				{
					location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					if(location == null)
					{
						location = new Location(LocationManager.NETWORK_PROVIDER);
						location.setLatitude(Double.valueOf("37.57273"));
						location.setLongitude(Double.valueOf("126.9687"));
					}
				}
				GeoPoint p = convertToGeoPoint(location);
				setMyLocationOverlay(p);
				mapController.animateTo(p);
			}
		});

		mapView.addView(getMyLoc);

		mapView.invalidate();
		return mapView;
	}

	@Override
	public void onPause() 
	{
		locationManager.removeUpdates(locationListener);
		Log.d(tag, "Remove all listener");
		super.onPause();
	}

	@Override
	public void onResume() 
	{
		Log.d(tag, "Resume location listener");
		List<String> providers = locationManager.getAllProviders();
		for(String provider : providers)
		{
			Log.d(tag, "Enroll the provider " + provider);
			locationManager.requestLocationUpdates(provider, 5, 15, locationListener);
		}
		super.onResume();
	}

	private LocationListener locationListener  = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			String msg = new String(new StringBuilder().append("Changed location provider").append(provider));
			Log.d(provider, msg);
		}

		@Override
		public void onProviderEnabled(String provider) {
			Log.d(tag, "Enabled gps");
		}

		@Override
		public void onProviderDisabled(String provider) {
			Log.d(provider, "Disabled gps");
			Toast.makeText(mContext, "GPS를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onLocationChanged(Location location) 
		{
			Log.d(tag, "locationupdate : " + location.getLatitude() + "," + location.getLongitude());

			GeoPoint point = convertToGeoPoint(location);
			setMyLocationOverlay(point);
			mapView.invalidate();
		}
	};

	/*
	private void addMarker(GeoPoint p, String title, String subTitle)
	{
		OverlayItem overlayItem = new OverlayItem(p, title, subTitle);
		try 
		{
			heritageOverlay.addOverlay(overlayItem);
		} catch (Exception e) {	}

		if(mapOverlay.size() < 2)
		{
			mapOverlay.add(heritageOverlay);
		}else
		{
			mapOverlay.set(1, heritageOverlay);
		}
		mapView.invalidate();
	}*/

	private void setMyLocationOverlay(GeoPoint p)
	{
		OverlayItem overlayItem = new OverlayItem(p, "현재 위치", "");
		try 
		{
			locationOverlay.clear();
			locationOverlay.addOverlay(overlayItem);
		} catch (Exception e) {	}
		if(mapOverlay.size() == 0)
		{
			mapOverlay.add(locationOverlay);
		}else
		{
			mapOverlay.set(0, locationOverlay);
		}
		mapView.invalidate();
	}

	/*
	private void getHeritages()
	{
		heritageOverlay.clear();
		//get point
		GeoPoint topleft = mapView.getProjection().fromPixels(0, 0);
		GeoPoint bottomright = mapView.getProjection().fromPixels(mapView.getWidth(), mapView.getHeight());

		double topRightLat = topleft.getLatitudeE6()/1E6;
		double bottomLeftLat = bottomright.getLatitudeE6()/1E6;
		double topRightLong = topleft.getLongitudeE6()/1E6;
		double bottomLeftLong = bottomright.getLongitudeE6()/1E6;

		HeritageSQLiteHelper sqlHelper = new HeritageSQLiteHelper(mContext);
		SQLiteDatabase db = sqlHelper.getReadableDatabase();

		//build query
		StringBuilder query = new StringBuilder("SELECT * FROM ");
		query.append(Constants.TABLE_NAME);
		query.append(" WHERE XCnts > ").append(topRightLong).append(" AND XCnts < ").append(bottomLeftLong);
		query.append(" AND YCnts > ").append(bottomLeftLat).append(" AND YCnts < ").append(topRightLat);
		query.append(" LIMIT 100;");
		//get data
		Cursor cursor = db.rawQuery(query.toString(), null);

		Log.d(tag, query.toString() + "query, total size : " + cursor.getCount());
		while(cursor.moveToNext())
		{
			Item item = CursorToItem.cursor2Item(cursor);
			double latitude = Double.valueOf(item.getXCnts()) * 1E6;
			double longitude = Double.valueOf(item.getYCnts()) * 1E6;
			GeoPoint point = new GeoPoint(Double.valueOf(longitude).intValue(), Double.valueOf(latitude).intValue());
			Log.d(tag, Double.valueOf(latitude).intValue()+ " ," +Double.valueOf(longitude).intValue());
			String subTitle = item.getItemNm() +" " + item.getCrltsNoNm() + "호";
			addMarker(point, item.getCrltsNm(), subTitle);
		}
		db.close();
		sqlHelper.close();
	}*/

	class LoadMapItems extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected void onPreExecute() 
		{
			progress.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(
				Void... params) {

			GeoPoint topleft = mapView.getProjection().fromPixels(0, 0);
			GeoPoint bottomright = mapView.getProjection().fromPixels(mapView.getWidth(), mapView.getHeight());

			double topRightLat = topleft.getLatitudeE6()/1E6;
			double bottomLeftLat = bottomright.getLatitudeE6()/1E6;
			double topRightLong = topleft.getLongitudeE6()/1E6;
			double bottomLeftLong = bottomright.getLongitudeE6()/1E6;

			HeritageSQLiteHelper sqlHelper = new HeritageSQLiteHelper(mContext);
			SQLiteDatabase db = sqlHelper.getReadableDatabase();

			//build query
			StringBuilder query = new StringBuilder("SELECT * FROM ");
			query.append(Constants.TABLE_NAME);
			query.append(" WHERE XCnts > ").append(topRightLong).append(" AND XCnts < ").append(bottomLeftLong);
			query.append(" AND YCnts > ").append(bottomLeftLat).append(" AND YCnts < ").append(topRightLat);
			query.append(" LIMIT 200;");
			//get data
			Cursor cursor = db.rawQuery(query.toString(), null);
			Log.d(tag, query.toString() + "query, total size : " + cursor.getCount());
			db.close();
			sqlHelper.close();
			heritageOverlay.clear();
			while(cursor.moveToNext())
			{
//				Item item = CursorToItem.cursor2Item(cursor);
//				double latitude = Double.valueOf(item.getXCnts()) * 1E6;
//				double longitude = Double.valueOf(item.getYCnts()) * 1E6;
				double latitude = Double.valueOf(cursor.getString(cursor.getColumnIndex("XCnts"))) * 1E6;
				double longitude = Double.valueOf(cursor.getString(cursor.getColumnIndex("YCnts"))) * 1E6;
				GeoPoint point = new GeoPoint(Double.valueOf(longitude).intValue(), Double.valueOf(latitude).intValue());

				//check existence of the overla at the point
				if(isExist(point)) continue;

				Log.d(tag, Double.valueOf(latitude).intValue()+ " ," +Double.valueOf(longitude).intValue());
				String title = cursor.getString(cursor.getColumnIndex("crltsNm"));
				String subTitle = cursor.getString(cursor.getColumnIndex("itemNm")) +" " + cursor.getString(cursor.getColumnIndex("crltsNoNm")) + "호";

				OverlayItem overlay = new OverlayItem(point, title, subTitle);
				try 
				{
					heritageOverlay.addOverlay(overlay);
				} catch (Exception e) {		}

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) 
		{
			if(mapOverlay.size() == 1)
			{
				mapOverlay.add(heritageOverlay);
			}else if(mapOverlay.size() == 2)
			{
				mapOverlay.set(1, heritageOverlay);
			}else
			{
				mapOverlay.add(heritageOverlay);
			}

			progress.setVisibility(View.INVISIBLE);
		}

		private boolean isExist(GeoPoint check)
		{
			for(int i = 0; i < heritageOverlay.size(); ++i)
			{
				GeoPoint point = heritageOverlay.getItem(i).getPoint();
				if(point.getLatitudeE6() == check.getLatitudeE6() 
						&& point.getLongitudeE6() == check.getLongitudeE6()) return true;
			}
			return false;
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) 
	{
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			GeoPoint edge = mapView.getProjection().fromPixels(0, 0);
			if((mapView.getZoomLevel() != currZoom) || (edge != currStart))
			{
				new LoadMapItems().execute();

				if(mapOverlay.size() == 1)
				{
					mapOverlay.add(heritageOverlay);
				}else if(mapOverlay.size() == 2)
				{
					mapOverlay.set(1, heritageOverlay);
				}else
				{
					mapOverlay.add(heritageOverlay);
				}
				mapView.invalidate();
				return false;
			}
		}
		return false;
	}

	public GeoPoint getCenter(GeoPoint p1, GeoPoint p2)
	{
		GeoPoint center = new GeoPoint((p1.getLatitudeE6() + p2.getLatitudeE6())/2, (p1.getLongitudeE6() + p2.getLongitudeE6())/2);
		return center;
	}

	public GeoPoint convertToGeoPoint(Location location)
	{
		GeoPoint point = new GeoPoint((int) (location.getLatitude()*1E6), (int) (location.getLongitude()*1E6));
		return point;
	}
}
