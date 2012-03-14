package net.julnamoo.swm.herimarque.info;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class AgeFragment extends Fragment {

	private String tag = AgeFragment.class.getSimpleName();
	
	private int ageCd;
	private Context mContext;
	private SQLiteDatabase db;
	private Cursor cursor;
	
	public AgeFragment(int ageCd, Context mContext)
	{
		this.ageCd = ageCd;
		this.mContext = mContext;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null)
		{
		}
	}
}
