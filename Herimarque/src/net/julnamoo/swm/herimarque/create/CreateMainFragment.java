package net.julnamoo.swm.herimarque.create;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.MapContainer;
import net.julnamoo.swm.herimarque.SubMainActivity;
import net.julnamoo.swm.herimarque.common.TurnOnGPSFragment;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import net.julnamoo.swm.herimarque.db.TrackingDataSource;
import net.julnamoo.swm.herimarque.info.LoadingFragment;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.view.HeritageItemizedOverlay;
import net.julnamoo.swm.herimarque.view.LocationItemizedOverlay;
import net.julnamoo.swm.herimarque.view.RouteOverlay;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
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
	private List<GeoPoint> myRoute;

	//for menu
	private RelativeLayout menuView;

	//for update location
	private final int TWO_MINUTES = 1000 * 60 * 2;
	private final int FIVE_MINUTES = 1000 *  60 *  5;
	private final int TEN_MINUTES = 1000 * 60 * 10;
	private int minTime = 5; //default value
	private int minDistance = 5; //default value
	private LocationManager locationManager;
	private Location lastLocation;

	private TurnOnGPSFragment gpsStarter;

	/** for heritage marking meta information **/
	private TrackingDataSource trackingTable;
	private boolean isTracking;
	private int transferType; //0-car, 1-bike, 2-walk
	private String PREF_NAME = "checkin";
	private String tableName;
	private List<String> areaCodes;
	public int PHOTO_COMMENT_ACTION = 2;

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
		myRoute = new ArrayList<GeoPoint>();
		//set gps switcher
		gpsStarter = new TurnOnGPSFragment();
		isTracking = false;

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
		lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(lastLocation == null)
		{
			lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if(lastLocation == null)
			{
				lastLocation = new Location(LocationManager.NETWORK_PROVIDER);
				lastLocation.setLatitude(Double.valueOf("37.57273"));
				lastLocation.setLongitude(Double.valueOf("126.9687"));
			}
		}
		Log.d(tag, "last location :" + lastLocation.getLatitude() + ","+lastLocation.getLongitude());
		Double lat = lastLocation.getLatitude() * 1E6;
		Double lng = lastLocation.getLongitude() * 1E6;
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
		getMyLoc.layout(10, 10, 0, 0);
		getMyLoc.setBackgroundDrawable(getResources().getDrawable(R.drawable.myloc));
		getMyLoc.setOnClickListener(myLocButtonListener);
		icContainer.addView(getMyLoc);

		// add getNearHeritage to mapview 
		Button getNearHeritage = new Button(inflater.getContext());
		fParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.TOP|Gravity.RIGHT);
		getNearHeritage.setLayoutParams(fParams);
		getNearHeritage.setBackgroundResource(R.drawable.ic_heritage);
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
		item = menu.add(Menu.NONE, 0, 0, null);
		item.setIcon(R.drawable.ic_car);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) 
			{
				setLocationManagerParams(0);
				return true;
			}
		});
		MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		item = menu.add(Menu.NONE, 1, 1, "");
		item.setIcon(R.drawable.ic_bike);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) 
			{
				setLocationManagerParams(1);
				return true;
			}
		});
		MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		item = menu.add(Menu.NONE, 2, 2, "");
		item.setIcon(R.drawable.ic_walk);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) 
			{
				setLocationManagerParams(2);
				return true;
			}
		});
		MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);

		super.onCreateOptionsMenu(menu, menuInflater);
	}

	@Override
	public void onPause() 
	{
		locationManager.removeUpdates(this);
		//		((ViewGroup) mapView.getParent()).removeView(mapView);
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
		//real service
		if(isBetterLocation(location, lastLocation))
		{
			lastLocation = location;
			
			if(isTracking)
			{
				myRoute.add(convertToGeoPoint(lastLocation));
			}
		}

		//for test
		//lastLocation = location;

		GeoPoint point = locationToGeoPoint(lastLocation);
		setMyLocationOverlay(point);
		mapController.animateTo(point);
		drawPath(myRoute, getResources().getColor(R.color.brown));
		mapView.invalidate();
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
			query.append(Constants.HERITAGE_TABLE_NAME);
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

		//TODO
		//test/////////////////////////////////////////
		try 
		{
			if(!isTracking)	locationOverlay.clear();
			locationOverlay.addOverlay(overlayItem);
		} catch (Exception e) {	}
		///////////////////////////////////////////////

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
				if(isTracking)
				{
					//stop listener
					new FinishConfirmFragment().show(getFragmentManager(), null);

				}else
				{
					//start listener
					((Button) v).setBackgroundResource(R.drawable.create_stop_selector);
					isTracking = true;
					Toast.makeText(mContext, "Heritage Mark를 시작합니다.", Toast.LENGTH_SHORT).show();

					//setup sqlite table with start time
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					tableName = "T" + sdf.format(new Date()) + "_";
					Log.d(tag, "tableName:"+tableName);

					trackingTable = new TrackingDataSource(mContext, tableName);
					trackingTable.addCheckIn(TrackingDataSource.TRACKING, lastLocation, null, null);
				}
			}
		});

		//transfer type
		menuView.findViewById(R.id.create_car).setOnClickListener(transferTypeClickListener);
		menuView.findViewById(R.id.create_bike).setOnClickListener(transferTypeClickListener);
		menuView.findViewById(R.id.create_walk).setOnClickListener(transferTypeClickListener);

		//checkin
		menuView.findViewById(R.id.newtimeline).setOnClickListener(checkInClickListener);
		//need to connect location service with create_turnoffgps button in menuview
	}

	OnClickListener transferTypeClickListener = new OnClickListener() 
	{
		@Override
		public void onClick(View v) 
		{
			menuView.findViewById(R.id.create_car).setPressed(false);
			menuView.findViewById(R.id.create_bike).setPressed(false);
			menuView.findViewById(R.id.create_walk).setPressed(false);
			((Button) v).setPressed(true);
			Log.d(tag, ((Button) v).getText() + " clicked");
			//set trasferType
			switch (v.getId()) 
			{
			case R.id.create_car: setLocationManagerParams(0);	break;
			case R.id.create_bike:setLocationManagerParams(1);	break;
			case R.id.create_walk: setLocationManagerParams(2);	break;
			}
		}
	};

	private void setLocationManagerParams(int id)
	{
		//set trasferType
		switch (id) 
		{
		case 0:
			transferType = 0;
			minTime = TEN_MINUTES * 3 / 2 ; //15mins
			minDistance = 20;
			Toast.makeText(mContext, "자동차로 여행중입니다", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			transferType = 1;
			minTime = TEN_MINUTES;
			minDistance = 10;
			Toast.makeText(mContext, "자전거로 여행중입니다", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			//			minTime = TWO_MINUTES;
			//			minDistance = 3;
			//			transferType = 2;

			minTime = 0;
			minDistance = 2;
			Toast.makeText(mContext, "걸으며 여행중입니다", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	OnClickListener checkInClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.d(tag, "checkin clicked");

			if(!isTracking)
			{
				Toast.makeText(mContext, "출발을 해주세요!", Toast.LENGTH_SHORT).show();
				return;
			}

			locationManager.removeUpdates(CreateMainFragment.this);
			new CheckInFragment(mContext, lastLocation, tableName).show(getFragmentManager(), null);
		}
	};
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


	/** Determines whether one Location reading is better than the current Location fix
	 * @param location  The new Location that you want to evaluate
	 * @param currentBestLocation  The current Location fix, to which you want to compare the new one
	 */
	protected boolean isBetterLocation(Location location, Location currentBestLocation) {
		if (currentBestLocation == null) {
			// A new location is always better than no location
			return true;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
		boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
		boolean isNewer = timeDelta > 0;

		// If it's been more than two minutes since the current location, use the new location
		// because the user has likely moved
		if (isSignificantlyNewer) {
			return true;
			// If the new location is more than two minutes older, it must be worse
		} else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(location.getProvider(),
				currentBestLocation.getProvider());

		// Determine location quality using a combination of timeliness and accuracy
		if (isMoreAccurate) {
			return true;
		} else if (isNewer && !isLessAccurate) {
			return true;
		} else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
			return true;
		}
		return false;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}

	/** Dialog Fragment for confirm finish **/
	class FinishConfirmFragment extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new AlertDialog.Builder(mContext)
			.setTitle("Heritage Mark 마치기")
			.setMessage("여행이 모두 끝나셨나요?")
			.setPositiveButton("예", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					menuView.findViewById(R.id.create_start).setBackgroundResource(R.drawable.create_start_selector);
					isTracking = false;
					Log.d(tag, "changing the trasfer type to walk");
					transferTypeClickListener.onClick(menuView.findViewById(R.id.create_walk));

					//capture the map
					MapContainer.hideMapChilderen();
					mapView.setDrawingCacheEnabled(true);
					Bitmap origin = mapView.getDrawingCache();
					int width = origin.getWidth();
					int height = origin.getHeight();
					Bitmap bitmap = Bitmap.createScaledBitmap(origin, width, height, true);
					mapView.setDrawingCacheEnabled(false);
					MapContainer.showMapChildren();

					String mappath = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "map_" + tableName + ".jpg", tableName);
					Log.d(tag, mappath + " map created");

					Log.d(tag, "프래그먼트 넘어가면서 타이틀, 전체커멘트, 지역정보 수정으로 ");
					Intent intent = new Intent(mContext, TimeLineActivity.class);
					intent.putExtra("mappath", mappath);
					intent.putExtra("tablename", tableName);
					startActivity(intent);

					// TODO 
					Log.d(tag, "turn on location listener");
					locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, CreateMainFragment.this);
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, CreateMainFragment.this);
				}
			})
			.setNegativeButton("아니요", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) 
				{
					Toast.makeText(mContext, "Heritage Mark를 계속합니다", Toast.LENGTH_SHORT).show();
				}
			})
			.create();
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		//TODO
		Log.d(tag, "called : " + requestCode + "," + resultCode);
		Bundle extras = data.getExtras();
		if(extras != null)
		{
			if(extras.containsKey("photo"))
			{
				Log.d(tag, "from addckecin : " + extras.getString("photo") + "," + extras.getString("content"));

				new CheckInFragment(mContext, lastLocation, tableName).onActivityResult(CheckInFragment.CHECKIN_PHOTO, Activity.RESULT_OK, data);
			}else
			{
				Log.d(tag, "get bitmap image from camera. save it to external storage. call addcheckin" );

				Intent intent = new Intent(mContext, AddPhotoAndCommentActivity.class);
				intent.putExtras(data);
				intent.putExtra("tablename", tableName);
				startActivityForResult(intent, CheckInFragment.CHECKIN_PHOTO);
			}

		}
	}

	private void drawPath(List geoPoints, int color) {
		
		for (int i = 1; i < geoPoints.size(); i++) {
			mapOverlay.add(new RouteOverlay((GeoPoint)geoPoints.get(i - 1),(GeoPoint) geoPoints.get(i), color));
		}
	}
	public GeoPoint convertToGeoPoint(Location location)
	{
		GeoPoint point = new GeoPoint((int) (location.getLatitude()*1E6), (int) (location.getLongitude()*1E6));
		return point;
	}
}
