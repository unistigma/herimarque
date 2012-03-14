package net.julnamoo.swm.herimarque.temp;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class Overlays extends ItemizedOverlay {

	private List<OverlayItem> overlays;
	private int MAX = 200;
	
	public Overlays(Drawable arg0) 
	{
		super(boundCenterBottom(arg0));
		overlays = new ArrayList<OverlayItem>();
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
			overlays.add(overlay);
		} else throw new Exception("OverlayItem list is full");
		populate();
	}
}
