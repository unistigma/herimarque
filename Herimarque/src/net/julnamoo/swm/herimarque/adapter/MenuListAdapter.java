package net.julnamoo.swm.herimarque.adapter;

import java.util.List;

import net.julnamoo.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuListAdapter extends BaseAdapter {

	final private String tag = KindImageAdapter.class.getSimpleName();

	private Context mContext;
	private int[] mList;

	public MenuListAdapter(Context c, int[] menus)
	{
		mContext = c;
		mList = menus;
	}
	
	@Override
	public int getCount() {
		return mList.length;
	}

	@Override
	public Object getItem(int position) {
		return mList[position];
	}

	@Override
	public long getItemId(int position) 
	{
		return mList[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ImageView image= new ImageView(mContext);

		image.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT));
		image.setScaleType(ImageView.ScaleType.FIT_XY);
		image.setPadding(0, 0, 0, 0);
		image.setId(mList[position]);
		image.setImageResource(mList[position]);

		return image;
	}

}
