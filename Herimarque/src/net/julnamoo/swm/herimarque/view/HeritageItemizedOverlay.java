package net.julnamoo.swm.herimarque.view;

import java.util.ArrayList;
import java.util.List;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.DetailFragment;
import net.julnamoo.swm.herimarque.db.HeritageDataSource;
import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.util.MapContainer;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class HeritageItemizedOverlay extends BalloonItemizedOverlay {

	private List<OverlayItem> overlays;
	private Context mContext;
	private int MAX = 600;

	private FragmentManager fragmentManager;
	
//	public HeritageItemizedOverlay(Drawable defaultMarker) 
//	{
//		super(boundCenterBottom(defaultMarker), MapContainer.mapView);
////		super(defaultMarker, MapContainer.mapView);
//		int markerHeight = ((BitmapDrawable) defaultMarker).getBitmap().getHeight();
//		setBalloonBottomOffset(markerHeight);
//		overlays = new ArrayList<OverlayItem>();
//		populate();
//	}

	public HeritageItemizedOverlay(Drawable defaultMarker, Context context, FragmentManager fragmentManager)
	{
		super(boundCenterBottom(defaultMarker), MapContainer.mapView);
//		super(defaultMarker, MapContainer.mapView);
		overlays = new ArrayList<OverlayItem>();
		int markerHeight = ((BitmapDrawable) defaultMarker).getBitmap().getHeight();
		setBalloonBottomOffset(markerHeight);
		this.mContext = context;
		this.fragmentManager = fragmentManager;
		populate();
	}

	@Override
	protected OverlayItem createItem(int index) 
	{
		if(index > overlays.size()) return null;
		else return overlays.get(index);
	}

	@Override
	public int size() {
		return overlays.size();
	}

	public void addOverlay(OverlayItem overlay) throws Exception
	{
		if(overlays.size() < MAX)
		{
			Log.d("heritage", "add");
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
			Log.d("heritage", "add");
			overlays.add(item);
			setLastFocusedIndex(-1);
			populate();
		} else throw new Exception("OverlayItem list is full");
	}
	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) 
	{
		Log.d("heritage", "ballonTap : " + item.getTitle());
		HeritageDataSource dataSource = new HeritageDataSource(mContext);
		dataSource.open();
		List<Item> items = dataSource.select("crltsNm", item.getTitle());
		dataSource.close();
		Item heritage = items.get(0);
		FragmentTransaction ft = fragmentManager.beginTransaction();
		Fragment f = new DetailFragment(heritage, mContext);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.info_main, f, "nearDetail");
		ft.addToBackStack("nearDetail");
		ft.commit();
		
		return true;
	}
	
	public void clear()
	{
		setLastFocusedIndex(-1);
		this.overlays.clear();
	}
	
	@Override
	protected int getIndexToDraw(int arg0) {
		if(arg0 > overlays.size()) return 0;
		else return super.getIndexToDraw(arg0);
	}
}
