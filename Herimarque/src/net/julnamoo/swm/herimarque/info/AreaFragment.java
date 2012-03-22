package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.HeritageListAdapter;
import net.julnamoo.swm.herimarque.common.DetailFragment;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import net.julnamoo.swm.herimarque.model.Heritage;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.util.CursorToHeritage;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AreaFragment extends Fragment{

	private String tag = AreaFragment.class.getSimpleName();

	private int ctrdCd;
	private Context mContext;
	private SQLiteDatabase db;
	private Cursor cursor;

	public AreaFragment(int ctrdCd, Context mContext)
	{
		this.ctrdCd = ctrdCd;
		this.mContext = mContext;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null)
		{
			Log.d(tag, "check the savedInstanceState, ctrdCd:" + ctrdCd);
			ctrdCd = savedInstanceState.getInt("ctrdCd");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		Log.d(tag, "oncreate areaFragment view");
		HeritageSQLiteHelper sqlHelper = new HeritageSQLiteHelper(mContext);
		db = sqlHelper.getReadableDatabase();

		//build query
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ").append(Constants.HERITAGE_TABLE_NAME);
//		query.append(" WHERE ").append("ctrdCd = '").append(Constants.ctrdCode[ctrdCd]).append("' ");
		query.append(" WHERE ").append("ctrdCd = '").append(ctrdCd).append("' ");
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

			view.addView(tv);
			
			//for manage fragment lifecycle
			view.setOnKeyListener(onBackPressed);
			return view;
		}else
		{
			ListView view = new ListView(inflater.getContext());
			view.setBackgroundColor(R.color.basic);
			view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			view.setAdapter(new HeritageListAdapter(mContext, cursor));
			view.setOnItemClickListener(itemClickListener);

			//for maange fragment lifecycle
			view.setOnKeyListener(onBackPressed);
			return view;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		outState.putInt("ctrdCd", ctrdCd);
		outState.putInt("depth", 2);
	}

	OnItemClickListener itemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int selected,
				long id) {
			Heritage item = CursorToHeritage.cursor2heritage((Cursor) arg0.getItemAtPosition(selected));
			Log.d(tag, "selected : " + item.getCrltsNm());

			DetailFragment f = new DetailFragment(item, mContext, R.id.info_main);
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.replace(R.id.info_main, f, "info");
			ft.addToBackStack("info");
			ft.commit();
		}
	};

	OnKeyListener onBackPressed = new OnKeyListener() {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) 
		{
			if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP)
			{
				getFragmentManager().popBackStackImmediate();
				Log.d(tag, "from fragment");
				return true;
			}
			return false;
		}
	};
}
