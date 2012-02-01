package net.julnamoo.swm.herimarque.adapter;

import android.R;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class InfoOurListAdapter extends SimpleCursorAdapter {

	Context mContext;
	int layout;
	
	LayoutInflater inflater;
	
	public InfoOurListAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		this.mContext = context;
		this.layout = layout;
	}
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) 
	{
		final LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(layout, parent, false);

		String name = cursor.getString(cursor.getColumnIndex("crltsNm"));
		String codeNum = cursor.getString(cursor.getColumnIndex("crltsNoNm")); 
		StringBuilder sb = new StringBuilder();
		sb.append(codeNum).append("호");
		String code = sb.toString();
		
		TextView target_name = (TextView) v.findViewById(R.id.text1);
		TextView target_code = (TextView) v.findViewById(R.id.text2);
		
		target_name.setText(name);
		target_code.setText(code);
		return v;
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		String name = cursor.getString(cursor.getColumnIndex("crltsNm"));
		String codeNum = cursor.getString(cursor.getColumnIndex("crltsNoNm")); 
		StringBuilder sb = new StringBuilder();
		sb.append(codeNum).append("호");
		String code = sb.toString();
		
		TextView target_name = (TextView) view.findViewById(R.id.text1);
		TextView target_code = (TextView) view.findViewById(R.id.text2);
		
		target_name.setText(name);
		target_code.setText(code);
	}
	
}
