package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.DetailFragment;
import net.julnamoo.swm.herimarque.adapter.HeritageListAdapter;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.util.CursorToItem;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class KindFragment extends Fragment {

	private String tag = KindFragment.class.getSimpleName();

	private int kindCd;
	private Context mContext;
	private SQLiteDatabase db;
	private Cursor cursor;

	public KindFragment(int kindCd, Context mContext) 
	{
		this.kindCd = kindCd;
		this.mContext = mContext;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null)
		{
			Log.d(tag, "check the savedInstanceState, kindCd:" + kindCd);
			kindCd = savedInstanceState.getInt("kindCd");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		Log.d(tag, "oncreate kindFragment view");
		HeritageSQLiteHelper sqlHelper = new HeritageSQLiteHelper(mContext);
		db = sqlHelper.getReadableDatabase();

		//build query
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ").append(Constants.TABLE_NAME);
		query.append(" WHERE ").append("itemCd = '").append(Constants.kindCode[kindCd]).append("' ");
		query.append("ORDER BY crltsNo ASC").append(";");
		//sql query
		String sql = query.toString();
		cursor = db.rawQuery(sql, null);
		
		Log.d(tag, "sql : " + sql);
		Log.d(sql, "total count : " +cursor.getCount());
		
		getActivity().startManagingCursor(cursor);
		db.close();

		//set view
		if(cursor.getCount() == 0)
		{
			LinearLayout view = new LinearLayout(inflater.getContext());
			view.setBackgroundColor(net.julnamoo.R.color.basic);
			
			TextView tv = new TextView(mContext);
			tv.setText("해당 문화유산이 없습니다!");
			tv.setTextSize((float) 30);
			tv.setGravity(Gravity.CENTER | Gravity.TOP);
//			tv.setTextColor(android.R.color.black);
			
			view.addView(tv);
			return view;
		}else
		{
			ListView view = new ListView(inflater.getContext());
			view.setBackgroundColor(R.color.basic);
			view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			view.setAdapter(new HeritageListAdapter(mContext, cursor));
			view.setOnItemClickListener(itemClickListener);
			
			return view;
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		outState.putInt("kindCd", kindCd);
		outState.putInt("depth", 2);
	}

	OnItemClickListener itemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int selected,
				long id) {
			Item item = CursorToItem.cursor2Item((Cursor) arg0.getItemAtPosition(selected));
			Log.d(tag, "selected : " + item.getCrltsNm());
			
//			DetailFragment f = new DetailFragment(item);
			DetailFragment f = new DetailFragment(item, mContext);
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	    	ft.replace(R.id.info_main, f, "infoDetail");
	    	ft.addToBackStack("infoDetail");
	    	ft.commit();
		}
	};
}
