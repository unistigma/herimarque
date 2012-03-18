package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.HeritageListAdapter;
import net.julnamoo.swm.herimarque.db.HeritageDataSource;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SearchResultFragment extends Fragment {

	private String tag = SearchResultFragment.class.getSimpleName();
	private Context mContext;
	private String query;
	private Cursor cursor;
	
	public SearchResultFragment(Context mContext, String query) 
	{
		this.mContext = mContext;
		this.query = query;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState != null)
		{
			query = savedInstanceState.getString("query");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle arg2) 
	{
		//set basic list view
		LinearLayout v = (LinearLayout) inflater.inflate(R.layout.list, parent, false);
		ListView list = (ListView) v.findViewById(R.id.list);
		list.setAdapter(new HeritageListAdapter(inflater.getContext(), cursor));
		list.setOnItemClickListener(itemClickListener);
		list.setTextFilterEnabled(true);
		
		//set queryString
		EditText queryText = (EditText) v.findViewById(R.id.search).findViewById(R.id.query);
		queryText.setHint(query);
		return v;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		outState.putString("query", query);
	}
	
	OnItemClickListener itemClickListener = new OnItemClickListener() 
	{
		public void onItemClick(android.widget.AdapterView<?> arg0, View view, int selected, long id) {
			Log.d(tag, "search list, selected : " + selected);
			Fragment f = new KindFragment(selected, getActivity().getApplicationContext());
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.replace(R.id.info_main, f, "info");
			ft.addToBackStack("info");
			ft.commit();
		};
	};
	
	/**
	 * execute the query get from the savedInstance,
	 * and execute the query then get the cursor of sqlite
	 */
	private void setCursor()
	{
		HeritageDataSource dataSource = new HeritageDataSource(mContext);
	}
}
