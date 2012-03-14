package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.temp.Overlays;
import net.julnamoo.swm.herimarque.util.MapContainer;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;

public class NearFragment extends Fragment {

	private String tag = NearFragment.class.getSimpleName();

	private Context mContext;
	private MapView mapView;
	private MapController mapController;
	private Overlays overlays;

	private long minTime;
	private float minDistance;
	private LocationManager locationManager;
	
	private int latitudeSpan;
	private int longitudeSpan;

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
		
		mapView.setBuiltInZoomControls(false);
		mapView.setSatellite(false);
		mapView.setTraffic(false);
		mapController = mapView.getController();

		//init view
		Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if(location == null)
		{
			location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
		
		int lat = (int) (location.getLatitude() * 1E6);	
		int lng = (int) (location.getLongitude() * 1E6);
		GeoPoint initPoint = new GeoPoint(lat, lng);
		mapController.animateTo(initPoint);
		mapController.setZoom(18);
		
		Drawable drawble = getResources().getDrawable(R.drawable.myloc);
		overlays = new Overlays(drawble);
		setMarker(initPoint);
		
		mapView.invalidate();
		latitudeSpan = mapView.getLatitudeSpan()/2;
		longitudeSpan = mapView.getLongitudeSpan()/2;
		int topRightLat = lat + latitudeSpan;
		int topRightLong = lng - longitudeSpan;
		int bottomLeftLat = lat - latitudeSpan;
		int bottomLeftLong = lng + longitudeSpan;
		
		//check near heritage
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
	public void onStop() 
	{
		Log.d(tag, "near stop");
		super.onStop();
	}
	
	@Override
	public void onDestroyView() 
	{
		Log.d(tag, "near destroy view");
		super.onDestroyView();
	}
	

	@Override
	public void onResume() 
	{
		Log.d(tag, "Resume location listener");
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, locationListener);
		
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
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			
			GeoPoint point = new GeoPoint(lat, lng);
			setMarker(point);
			mapController.animateTo(point); 
			
			//calculate the point
			int topRightLat = lat + latitudeSpan;
			int topRightLong = lng - longitudeSpan;
			int bottomLeftLat = lat - latitudeSpan;
			int bottomLeftLong = lng + longitudeSpan;
			
//			mapView.invalidate();
		}
	};
	
	private void setMarker(GeoPoint p)
	{
		OverlayItem overlayItem = new OverlayItem(p, "Title", "subTitle");
		try 
		{
			overlays.addOverlay(overlayItem);
		} catch (Exception e) {	}
		mapView.getOverlays().add(overlays);
	}
}
