package net.julnamoo.swm.herimarque.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class LocationItemizedOverlay extends ItemizedOverlay {
	
	private List<OverlayItem> overlays;
	private Context mContext;
	private int MAX = 600;
	private float FONT_SIZE = 10;
	private int TITLE_MARGIN = 3;
	
	private int markerHeight;

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
		markerHeight = ((BitmapDrawable) defaultMarker).getBitmap().getHeight();
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
	
//	@Override
//	public void draw(Canvas canvas, MapView mapView, boolean shadow) 
//	{
//		super.draw(canvas, mapView, shadow);
//
//		for(OverlayItem item : overlays)
//		{
//			GeoPoint point = item.getPoint();
//			Point marker = new Point();
//			//get overlayitem pixel point
//			mapView.getProjection().toPixels(point, marker);
//
//			// Find the width and the height of the title
//			TextPaint textPaint = new TextPaint();
//			textPaint.setTextSize(FONT_SIZE);
//
//			Paint paintRect = new Paint();
//
//			Rect rect = new Rect();
//			rect.inset(3, 3);
//			rect.offsetTo(marker.x - rect.width()/2, marker.y - markerHeight - rect.height());
//
//			textPaint.getTextBounds(item.getTitle(), 0, item.getTitle().length(), rect);
//			textPaint.setTextAlign(Paint.Align.CENTER);
//			textPaint.setTextSize(FONT_SIZE);
//			textPaint.setARGB(255, 255, 255, 255);
//			paintRect.setARGB(130, 0, 0, 0);
//			
//			canvas.drawRoundRect(new RectF(rect), TITLE_MARGIN, TITLE_MARGIN, paintRect);
//			canvas.drawText(item.getTitle(), rect.left + rect.width()/2, rect.bottom - 3, textPaint);
//		}
//	}
	
	public void clear()
	{
		setLastFocusedIndex(-1);
		this.overlays.clear();
	}
}
