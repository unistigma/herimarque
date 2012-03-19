package net.julnamoo.swm.herimarque.create;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.SubMainActivity;
import net.julnamoo.swm.herimarque.common.TurnOnGPSFragment;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import net.julnamoo.swm.herimarque.info.LoadingFragment;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.util.MapContainer;
import net.julnamoo.swm.herimarque.view.HeritageItemizedOverlay;
import net.julnamoo.swm.herimarque.view.LocationItemizedOverlay;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class CreateMainFragment extends Fragment implements LocationListener {

	private String tag = CreateMainFragment.class.getSimpleName();
	private final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;

	private Context mContext;
	private ViewGroup originMapContainer;
	private MapView mapView;
	private MapController mapController;
	private List<Overlay> mapOverlay;
	private HeritageItemizedOverlay heritageOverlay;
	private LocationItemizedOverlay locationOverlay;

	//for menu
	private RelativeLayout menuView;

	//for update location
	private int minTime = 5; //default value
	private int minDistance = 5; //default value
	private LocationManager locationManager;

	private TurnOnGPSFragment gpsStarter;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		mContext = getActivity();

		//set locationmanger
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, this);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, this);
		//set overlay
		heritageOverlay = new HeritageItemizedOverlay(getResources().getDrawable(R.drawable.pin2), mContext, getFragmentManager(), 1);
		locationOverlay = new LocationItemizedOverlay(getResources().getDrawable(R.drawable.pin1), mContext);
		//set gps switcher
		gpsStarter = new TurnOnGPSFragment();

		setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(tag, "create CreateFragment view");
		mapView = MapContainer.mapView;

		//set mapview parent
		originMapContainer = (ViewGroup) mapView.getParent();
		if(originMapContainer != null)
		{
			originMapContainer.removeView(mapView);
		}

		mapView.setBuiltInZoomControls(false);
		mapView.setSatellite(false);
		mapView.setTraffic(false);
		mapController = mapView.getController();
		mapOverlay = mapView.getOverlays();

		//init mapview
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
		setMyLocationOverlay(point);

		FrameLayout icContainer = new FrameLayout(inflater.getContext());
		FrameLayout.LayoutParams fParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT);
		icContainer.setLayoutParams(fParams);

		//add the Button for moving to the current location to mapview
		Button getMyLoc = new Button(inflater.getContext());
		fParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.TOP|Gravity.LEFT); 
		getMyLoc.setLayoutParams(fParams);
		getMyLoc.setBackgroundDrawable(getResources().getDrawable(R.drawable.myloc));
		getMyLoc.setOnClickListener(myLocButtonListener);
		icContainer.addView(getMyLoc);

		// add getNearHeritage to mapview 
		Button getNearHeritage = new Button(inflater.getContext());
		fParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.TOP|Gravity.RIGHT);
		getNearHeritage.setLayoutParams(fParams);
		getNearHeritage.setBackgroundResource(R.drawable.main_info_selector);
		getNearHeritage.setOnClickListener(nearHeritageButtonListener);
		icContainer.addView(getNearHeritage);

		//add the near button
		menuView = (RelativeLayout) inflater.inflate(R.layout.create_main, container, false);
		fParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT, Gravity.RIGHT | Gravity.BOTTOM);
		menuView.setLayoutParams(fParams);
		icContainer.addView(menuView);
		//set menu listener
		setupMenuListener();

		mapView.addView(icContainer);
		mapView.invalidate();

		return mapView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) 
	{
		MenuItem item;
		item = menu.add("자전거");
		item.setIcon(R.drawable.ic_launcher);
		MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
		item = menu.add("도보");
		item.setIcon(R.drawable.ic_menu);
		MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
		item = menu.add("체크인");
		item.setIcon(R.drawable.ic_menu_search);
		MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);

		super.onCreateOptionsMenu(menu, menuInflater);
	}

	@Override
	public void onPause() 
	{
		locationManager.removeUpdates(this);
		((ViewGroup) mapView.getParent()).removeView(mapView);
		if(originMapContainer != null)
		{
			originMapContainer.addView(mapView);
		}
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
			locationManager.requestLocationUpdates(provider, minTime, minDistance, this);
		}
		super.onResume();
	}

	@Override
	public void onLocationChanged(Location location) 
	{
		GeoPoint point = locationToGeoPoint(location);
		setMyLocationOverlay(point);
		mapController.animateTo(point);
	}

	@Override
	public void onProviderDisabled(String provider) 
	{
		Log.d(tag, "Disabled " + provider + ": " + ((SubMainActivity) getActivity()).getCurrMenu());
		//		Toast.makeText(mContext, "GPS를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
		if(((SubMainActivity) getActivity()).getCurrMenu() == 1 && (provider.equals(LocationManager.GPS_PROVIDER) || provider.equals(LocationManager.NETWORK_PROVIDER )))
		{
			if(!gpsStarter.isVisible())
			{
				gpsStarter.show(getFragmentManager(), "");
			}
		}
	}

	@Override
	public void onProviderEnabled(String provider) {	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {	}

	class LoadMapItems extends AsyncTask<Void, Void, Void>
	{
		LoadingFragment loadingFragment;

		@Override
		protected void onPreExecute() 
		{
			loadingFragment = new LoadingFragment();
			loadingFragment.show(getFragmentManager(), "주변 문화유산을 찾는 중...");
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
			loadingFragment.dismiss();
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

	/** for update mapView **/
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

	public GeoPoint locationToGeoPoint(Location location)
	{
		GeoPoint point = new GeoPoint((int) (location.getLatitude()*1E6), (int) (location.getLongitude()*1E6));
		return point;
	}

	/** Listeners **/
	OnClickListener myLocButtonListener = new OnClickListener() 
	{
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
			GeoPoint p = locationToGeoPoint(location);
			setMyLocationOverlay(p);
			mapController.animateTo(p);
		}
	};

	OnClickListener nearHeritageButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) 
		{
			new LoadMapItems().execute();
		}
	};

	// setup listener
	private void setupMenuListener()
	{
		//for menu button
		View mainMenu = menuView.findViewById(R.id.create_main_menu);
		final View subMenu = menuView.findViewById(R.id.create_main_submenu);
		mainMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				Log.d(tag, "mainMenuClicked");
				if(subMenu.getVisibility() == View.INVISIBLE)
				{
					subMenu.setVisibility(View.VISIBLE);
				}else
				{
					subMenu.setVisibility(View.INVISIBLE);
				}
			}
		});

		//for start button
		View start = menuView.findViewById(R.id.create_start);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				Log.d(tag, "startClicked");
				//test to send the data to server
				
				File file = new File( Environment.getExternalStorageDirectory()+"/net.julnamoo/file1.jpg");
			    Uri outputFileUri = Uri.fromFile( file );

			    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
			    intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );

			    startActivityForResult( intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE );
			}
		});
	}

//	private void sendTestData()
//	{
//		String boundary =  "*****";
//		String twoHyphens = "--";
//		String sendingBoundary = "--*****";
//		String lineEnd = "\r\n";
//		try 
//		{
//			URL url = new URL("http://localhost:8080/herimarque/api/c/upload/map/testId");
//			HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
//
//			httpURLCon.setDefaultUseCaches(false);
//			httpURLCon.setDoInput(true);
//			httpURLCon.setDoOutput(true);
//			httpURLCon.setRequestMethod("POST");
//			httpURLCon.setRequestProperty("content-type", "multipart/form-data; boundary="+boundary);
//
//			/**
//			 * ------WebKitFormBoundarysAPbx0Jb5UuEDXNf
//			Content-Disposition: form-data; name="id"
//
//			testId
//
//
//
//			Content-Disposition: form-data; name="map"; filename=""
//			Content-Type: application/octet-stream
//
//			 */
//			DataOutputStream dos = new DataOutputStream(httpURLCon.getOutputStream());
//			
//			//set the first param
//			dos.writeBytes(sendingBoundary);
//			dos.writeBytes("Content-Disposition: form-data; name='id'");
//			dos.writeBytes(lineEnd);
//			dos.writeBytes("testId");
//			dos.writeBytes(lineEnd);
//			
//			//set the second param
//			dos.writeBytes(sendingBoundary);
//			dos.writeBytes("Content-Disposition: form-data; name='map'; filename=''");
//			dos.writeBytes("Content-Type: application/octet-stream");
//			dos.writeBytes(lineEnd);
////			dos.writeBytes("testId"); //write bytes
//			dos.writeBytes(lineEnd);
//			
//			dos.writeBytes(sendingBoundary);
//			dos.writeBytes("Content-Disposition: form-data; name='file1'; filename=''");
//			dos.writeBytes("Content-Type: application/octet-stream");
//			dos.writeBytes(lineEnd);
////			dos.writeBytes("testId"); //write bytes
//			dos.writeBytes(lineEnd);
//			
//		} catch (MalformedURLException e) 
//		{
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
//		super.onActivityResult(arg0, arg1, arg2);
		
		if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
		{
			Toast.makeText(getActivity(), "Image saved to:\n" +
                    data.getData(), Toast.LENGTH_LONG).show();
		}
		Log.d(tag, "capture the screen");
		View v1 = getView().getRootView();
		v1.setDrawingCacheEnabled(true);
		Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
		v1.setDrawingCacheEnabled(false);
//		String boundary =  "*****";
//		String twoHyphens = "--";
//		String sendingBoundary = "--*****";
//		String lineEnd = "\r\n";
//		try 
//		{
//			URL url = new URL("http://localhost:8080/herimarque/api/c/upload/map/testId");
//			HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
//
//			httpURLCon.setDefaultUseCaches(false);
//			httpURLCon.setDoInput(true);
//			httpURLCon.setDoOutput(true);
//			httpURLCon.setRequestMethod("POST");
//			httpURLCon.setRequestProperty("content-type", "multipart/form-data; boundary="+boundary);
//
//			/**
//			 * ------WebKitFormBoundarysAPbx0Jb5UuEDXNf
//			Content-Disposition: form-data; name="id"
//
//			testId
//
//
//
//			Content-Disposition: form-data; name="map"; filename=""
//			Content-Type: application/octet-stream
//
//			 */
//			DataOutputStream dos = new DataOutputStream(httpURLCon.getOutputStream());
//			
//			//set the first param
//			dos.writeBytes(sendingBoundary);
//			dos.writeBytes("Content-Disposition: form-data; name='id'");
//			dos.writeBytes(lineEnd);
//			dos.writeBytes("testId");
//			dos.writeBytes(lineEnd);
//			
//			//set the second param
//			dos.writeBytes(sendingBoundary);
//			dos.writeBytes("Content-Disposition: form-data; name='map'; filename=''");
//			dos.writeBytes("Content-Type: application/octet-stream");
//			dos.writeBytes(lineEnd);
////			dos.writeBytes("testId"); //write bytes
//			dos.writeBytes(lineEnd);
//			
//			dos.writeBytes(sendingBoundary);
//			dos.writeBytes("Content-Disposition: form-data; name='file1'; filename=''");
//			dos.writeBytes("Content-Type: application/octet-stream");
//			dos.writeBytes(lineEnd);
////			dos.writeBytes("testId"); //write bytes
//			dos.writeBytes(lineEnd);
//			
//		} catch (MalformedURLException e) 
//		{
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}
