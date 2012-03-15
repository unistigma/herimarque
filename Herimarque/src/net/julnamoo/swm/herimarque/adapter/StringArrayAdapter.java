package net.julnamoo.swm.herimarque.adapter;

import net.julnamoo.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StringArrayAdapter extends BaseAdapter {

	Context mContext;
	String[] values;
	
	public StringArrayAdapter(Context mContext, int id, String[] values) 
	{
		super();
		this.mContext = mContext;
		this.values = values;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.list_item_1, parent, false);
		
		TextView tv = (TextView) view.findViewById(R.id.text);
		tv.setText((String) getItem(position));
		
		return view;
	}


	@Override
	public int getCount() {
		return values.length;
	}


	@Override
	public Object getItem(int position) {
		return values[position];
	}


	@Override
	public long getItemId(int position) {
		return position;
	}
	
}
