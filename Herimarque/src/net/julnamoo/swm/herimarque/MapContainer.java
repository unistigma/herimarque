package net.julnamoo.swm.herimarque;

import android.view.View;

import com.google.android.maps.MapView;

public class MapContainer {

	public static MapView mapView;
	
	public static void hideMapChilderen()
	{
		MapView map = MapContainer.mapView;
		for(int i = 0; i < map.getChildCount(); ++i)
		{
			View view = map.getChildAt(i);
			view.setVisibility(View.INVISIBLE);
		}
	}
	
	public static void showMapChildren()
	{
		MapView map = MapContainer.mapView;
		for(int i = 0; i < map.getChildCount(); ++i)
		{
			View view = map.getChildAt(i);
			view.setVisibility(View.VISIBLE);
		}
	}
}
