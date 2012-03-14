package net.julnamoo.swm.herimarque.temp;

import java.util.List;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.model.MyLocation;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class CreateServiceActivity extends MapActivity {

	private static String tag = CreateServiceActivity.class.getSimpleName();

	//my new trip
	String name;
	TextView txtname;

	//for getting current location
	LocationManager locationManager;
	LocationListener locationListener;
	Criteria criteria;

	//for map view
	MapView map;
	MapController mapController;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.create_service);

		//Get the trip name
		name = getIntent().getStringExtra("name");

		//for getting my location with button....
		//it needs to log the location with this button click listener
		findViewById(R.id.butt_mylocation).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MyLocation ml = getCurrentLocation();
				showUpdate(ml);
			}
		});

		//Get the current location
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		locationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				String msg = new String(new StringBuilder().append("Changed location provider").append(provider));
				Log.d(tag, msg);
			}

			@Override
			public void onProviderEnabled(String provider) {
				Log.d(tag, "Enabled gps");
			}

			@Override
			public void onProviderDisabled(String provider) {
				Log.d(provider, "Disabled gps");
				Toast.makeText(CreateServiceActivity.this, "GPS를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onLocationChanged(Location location) {
				StringBuilder sb = new StringBuilder();
				sb.append("changed location:");
				sb.append(location.getLatitude()).append(",");
				sb.append(location.getLongitude());
				Log.d(tag, sb.toString());

				//send the new location info to the service
			}
		};
		MyLocation currloc = getCurrentLocation();

		//set map view
		showUpdate(currloc);

		txtname = (TextView) findViewById(R.id.create_txt_name);
		txtname.setText(name);

	}
	
	@Override
	protected void onPause() {
		locationManager.removeUpdates(locationListener);
		locationManager.removeTestProvider(LocationManager.GPS_PROVIDER);
		locationManager.removeTestProvider(LocationManager.NETWORK_PROVIDER);
		Log.d(tag, "Remove all listener");
		super.onPause();
	}
	
	protected void onResume() {
		
		Log.i(tag, "Resume location listener");
		List<String> providers = locationManager.getAllProviders();
		for(String provider : providers)
		{
			Log.d(tag, "Enroll the provider " + provider);
			locationManager.requestLocationUpdates(provider, 5, 15, locationListener);
		}
		super.onResume();
	};

	private MyLocation getCurrentLocation()
	{
		if(this.locationManager != null)
		{
			List<String> providers = locationManager.getAllProviders();
			for(String provider : providers)
			{
				locationManager.requestLocationUpdates(provider, 0, 5, locationListener );
				Location loc = locationManager.getLastKnownLocation(provider);
				if(loc != null)
				{
					double latitude = loc.getLatitude();
					double longitude = loc.getLongitude();
					StringBuilder sb = new StringBuilder().append("Set new location, ").append(latitude)
							.append(", ").append(longitude);
					String msg = new String(sb.toString());
					Log.i(tag, msg);

					return new MyLocation(latitude * 1E6, longitude * 1E6);
				}
			}
		}
		Log.i(tag, "Fail to get current location");
		return null;
	}

	private void showUpdate(MyLocation location)
	{
		//set map view
		map = (MapView) findViewById(R.id.create_map_mylocation);
		mapController = map.getController();

		GeoPoint curr = new GeoPoint(location.getLatitude().intValue(), location.getLongitude().intValue());
		mapController.setCenter(curr);
		mapController.animateTo(curr);
		mapController.setZoom(18);

		map.invalidate();
	}

	@Override
	public void onBackPressed() {
		finish();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
