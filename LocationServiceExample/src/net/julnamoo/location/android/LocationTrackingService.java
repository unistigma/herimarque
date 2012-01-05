package net.julnamoo.location.android;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocationTrackingService extends Service {

	public static String tag = LocationTrackingService.class.getSimpleName();
	
	public static final String ACTION_START = "net.julnamoo.location.android.action.START";
	public static final String ACTION_STOP = "net.julnamoo.location.android.action.STOP";
	public static final String ACTION_GETLOCATION = "net.julnamoo.location.android.action.GETLOCATION";

	ArrayList<Coord> mCoords ;

	LocationManager locationManager;
	LocationListener locationListener;
	
	Context context;
	String logfname;
	
	public LocationTrackingService() {
		mCoords = new ArrayList<Coord>();
	}
	
	@Override
	public void onCreate() {
		Log.d(tag, "create service");
		
		context = this;
		
		/** set up location manager and listener **/
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "현재 위치를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, arg0.getLatitude() + "," + arg0.getLongitude(), Toast.LENGTH_SHORT);
			}
		};
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		
		super.onStart(intent, startId);
		
	}

	// for update the text view of the map activity
	private void updateTextView(Location location)
	{
		StringBuilder sb = new StringBuilder("Providers: ");
		String provider = location.getProvider();
		sb.append(provider).append(">>");
		Double latitude = location.getLatitude();
		Double longitude = location.getLongitude();
		Float accur = location.getAccuracy();
		
		sb.append(latitude).append(", ");
		sb.append(longitude).append(", ");
		sb.append(accur).append("\n");
		
		// set the coordinate text view
//		coordText.setText(sb.toString());
	}
}
