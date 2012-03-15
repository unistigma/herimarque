package net.julnamoo.swm.herimarque.info;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.util.CursorToItem;
import net.julnamoo.swm.herimarque.util.MapContainer;
import net.julnamoo.swm.herimarque.widget.HeritageItemizedOverlay;
import net.julnamoo.swm.herimarque.widget.LocationItemizedOverlay;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class NearFragment extends Fragment {

	private String tag = NearFragment.class.getSimpleName();

	private Context mContext;
	private MapView mapView;
	private MapController mapController;
	private HeritageItemizedOverlay heritageOverlay;
	private LocationItemizedOverlay locationOverlay;

	private long minTime;
	private float minDistance;
	private LocationManager locationManager;

	private List<Overlay> mapOverlay;

	private boolean isCalled;

	public NearFragment(Context mContext, long minTime, float minDistance) 
	{
		this.mContext = mContext;
		this.minTime = minTime;
		this.minDistance = minDistance;
		isCalled = false;
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

		heritageOverlay = new HeritageItemizedOverlay(getResources().getDrawable(R.drawable.greenpin), mContext);
		locationOverlay = new LocationItemizedOverlay(getResources().getDrawable(R.drawable.yellowpin), mContext);
	}

	@Override
	public void onAttach(Activity arg0) 
	{
		super.onAttach(arg0);
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

//		mapView.setOnTouchListener(touchLisnter);
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
		setMyLocationOverlay(point);

		mapController.animateTo(point);
		mapController.setZoom(18);
		getHeritages();

		mapView.invalidate();
		//check near heritage
		return mapView;
	}

	OnTouchListener touchLisnter = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_UP)
			{
				Log.d(tag,event.getX()+ ","+event.getY());
				LoadMapItems lmi = new LoadMapItems();
				lmi.execute(0);
				mapOverlay.add(heritageOverlay);
				mapView.invalidate();
				return true;
			}
			return false;
		}
	};

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
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);

			GeoPoint point = new GeoPoint(lat, lng);
			mapController.animateTo(point); 
			setMyLocationOverlay(point);
//			getHeritages();
			new LoadMapItems().execute(0);
			mapOverlay.add(heritageOverlay);
			mapView.invalidate();
		}
	};

	private void addMarker(GeoPoint p, String title, String subTitle)
	{
		OverlayItem overlayItem = new OverlayItem(p, title, subTitle);
		try 
		{
			heritageOverlay.addOverlay(overlayItem);
		} catch (Exception e) {	}

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
	}

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

	}

	class LoadMapItems extends AsyncTask<Integer, Integer, Void>
	{
		Cursor cursor;
		boolean working;

		@Override
		protected void onPreExecute() 
		{
			isCalled = isCalled ? false : true;
			working = isCalled;

		}

		@Override
		protected Void doInBackground(
				Integer... params) {
			heritageOverlay.clear();

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
			cursor = db.rawQuery(query.toString(), null);
			Log.d(tag, query.toString() + "query, total size : " + cursor.getCount());
			db.close();
			sqlHelper.close();
			while(cursor.moveToNext())
			{
				if(isCalled != working) return null;

				Item item = CursorToItem.cursor2Item(cursor);
				double latitude = Double.valueOf(item.getXCnts()) * 1E6;
				double longitude = Double.valueOf(item.getYCnts()) * 1E6;
				GeoPoint point = new GeoPoint(Double.valueOf(longitude).intValue(), Double.valueOf(latitude).intValue());
				Log.d(tag, Double.valueOf(latitude).intValue()+ " ," +Double.valueOf(longitude).intValue());
				String title = item.getCrltsNm();
				String subTitle = item.getItemNm() +" " + item.getCrltsNoNm() + "호";

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
			//			mapView.getOverlays().add(heritageOverlay);
		}

	}
}
