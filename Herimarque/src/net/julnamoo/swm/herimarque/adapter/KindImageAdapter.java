package net.julnamoo.swm.herimarque.adapter;

import net.julnamoo.swm.herimarque.util.Constants;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KindImageAdapter extends BaseAdapter {

	final private String tag = KindImageAdapter.class.getSimpleName();
	
	private Context mContext;

	public KindImageAdapter(Context c)
	{
		mContext = c;
	}

	@Override
	public int getCount() {
		return 13;
	}

	@Override
	public Object getItem(int arg0) {
		return Constants.kindImgs[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) 
	{
		LinearLayout view = new LinearLayout(mContext);;
		ImageView image= new ImageView(mContext);;
		TextView tv = new TextView(mContext);
		
		view.setOrientation(LinearLayout.VERTICAL);
		GridView.LayoutParams params = new GridView.LayoutParams(100, 150);
		view.setLayoutParams(params);

		image.setLayoutParams(new LinearLayout.LayoutParams(100,100));
		image.setScaleType(ImageView.ScaleType.FIT_XY);
		image.setPadding(8, 8, 8, 8);
		image.setContentDescription(Constants.kindImgsCD[arg0]);
		image.setId(arg0);
		image.setImageResource(Constants.kindImgs[arg0]);
		
		tv.setText(Constants.kindImgsCD[arg0]);
		tv.setGravity(Gravity.CENTER);

		view.addView(image);
		view.addView(tv);
		return view;
	}

}
