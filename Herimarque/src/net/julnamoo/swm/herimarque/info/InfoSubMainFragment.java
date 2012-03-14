package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.KindImageAdapter;
import net.julnamoo.swm.herimarque.util.Constants;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class InfoSubMainFragment extends Fragment {

	private String tag = InfoSubMainFragment.class.getSimpleName();
	
	private int menu;
	
	public InfoSubMainFragment(int selected)
	{
		this.menu = selected;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null)
		{
			Log.d(tag, "check the savedInstanceState, menu index:" + menu);
			menu = savedInstanceState.getInt("menu");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		switch (menu) {
		//for kind
		case 0:
			View v = inflater.inflate(R.layout.grid, container, false);
			Log.d(tag, "set kindImageAdapter");
			GridView gv = (GridView) v.findViewById(R.id.category_grid);
			gv.setAdapter(new KindImageAdapter(inflater.getContext()));
			gv.setOnItemClickListener(kindItemClickListener);
			return gv;
		//for area
		case 1:
			Log.d(tag, "set ctrdArrayAdapter");
			v = inflater.inflate(R.layout.list, container, false);
			ListView l = (ListView) v.findViewById(R.id.list);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,  Constants.ctrdName);
			l.setAdapter(adapter);
			l.setOnItemClickListener(ctrdClickListener);
			return v;
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		outState.putInt("menu", menu);
		outState.putInt("depth", 1);
	}
	
	OnItemClickListener kindItemClickListener = new OnItemClickListener() 
	{
		public void onItemClick(android.widget.AdapterView<?> arg0, View view, int selected, long id) {
			Log.d(tag, "kind, selected : " + selected);
			Fragment f = new KindFragment(selected, getActivity().getApplicationContext());
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.replace(R.id.info_main, f, "infoSubList");
			ft.addToBackStack("infoSubList");
			ft.commit();
		};
	};
	
	OnItemClickListener ctrdClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int selected,
				long arg3) {
			Log.d(tag, "area, selected : "+selected);
			Fragment f = new AreaFragment(selected, getActivity().getApplicationContext());
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.replace(R.id.info_main, f, "infoSubList");
			ft.addToBackStack("infoSubList");
			ft.commit();
		}
	};
}
