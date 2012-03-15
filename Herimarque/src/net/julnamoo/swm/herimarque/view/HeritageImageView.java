package net.julnamoo.swm.herimarque.view;

import java.io.Serializable;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

public class HeritageImageView extends ImageView implements Serializable
{
	private Bitmap bitMap;

	public HeritageImageView(Context mContext) 
	{
		super(mContext);
	}

	public HeritageImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HeritageImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		if(bitMap == null)
		{
			setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
		}else
		{
			int width = MeasureSpec.getSize(widthMeasureSpec);
			int height = width * bitMap.getHeight() / bitMap.getWidth();
			setMeasuredDimension(width, height);
		}
	}

	@Override
	public void setImageBitmap(Bitmap bm) 
	{
		this.bitMap = bm;
		super.setImageBitmap(bm);
	}

	public void setBitMap(Bitmap bitMap) {
		this.bitMap = bitMap;
	}
	
	
}