package net.julnamoo.swm.herimarque.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class LocationItemizedOverlay extends ItemizedOverlay {
	
	private List<OverlayItem> overlays;
	private Context mContext;
	private int MAX = 600;
	
	public LocationItemizedOverlay(Drawable arg0) 
	{
		super(boundCenterBottom(arg0));
		overlays = new ArrayList<OverlayItem>();
		populate();
	}

	public LocationItemizedOverlay(Drawable defaultMarker, Context context)
	{
		super(boundCenterBottom(defaultMarker));
		overlays = new ArrayList<OverlayItem>();
		this.mContext = context;
		populate();
	}

	@Override
	protected OverlayItem createItem(int index) 
	{
		return overlays.get(index);
	}

	@Override
	public int size() {
		return overlays.size();
	}

	public void addOverlay(OverlayItem overlay) throws Exception
	{
		if(overlays.size() < MAX)
		{
			Log.d("loc", "add");
			overlays.add(overlay);
			setLastFocusedIndex(-1);
			populate();
		} else throw new Exception("OverlayItem list is full");
	}

	public void addOverlay(int latitude, int longitude, String title, String snippet) throws Exception
	{
		if(overlays.size() < MAX)
		{
			GeoPoint geoPoint = new GeoPoint(latitude, longitude);
			OverlayItem item = new OverlayItem(geoPoint, title, snippet);
			Log.d("loc", "add");
			overlays.add(item);
			setLastFocusedIndex(-1);
			populate();
		} else throw new Exception("OverlayItem list is full");
	}

	@Override
	protected boolean onTap(int arg0) 
	{
		Log.d("overlayitem", "tab : " + arg0);
		return super.onTap(arg0);
	}

	@Override
	public boolean onTap(GeoPoint point, MapView mapView) 
	{
		Log.d("overlayitem", "tab2 : " + point.getLatitudeE6());
		return super.onTap(point, mapView);
	}
	
	public void clear()
	{
		setLastFocusedIndex(-1);
		this.overlays.clear();
	}
}
