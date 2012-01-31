package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.app.ListFragment;
import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfoOurHeritageListFragment extends ListFragment {

	private String tag = InfoOurHeritageListFragment.class.getSimpleName();
	
	public void onCreate(android.os.Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		Log.i(tag, "onCreate listfragment");
	};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		super.onActivityCreated(savedInstanceState);
		String[] values = new String[] {"종목", "시대", "지역"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.info_our_category);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Log.d(item, "Select "+item);
		
	}
}
