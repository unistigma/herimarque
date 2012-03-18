package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.KindImageAdapter;
import net.julnamoo.swm.herimarque.adapter.StringArrayAdapter;
import net.julnamoo.swm.herimarque.info.listener.SearchButtonListener;
import net.julnamoo.swm.herimarque.info.listener.SearchKeyListener;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.view.SearchBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

public class InfoSubMainFragment extends Fragment {

	private String tag = InfoSubMainFragment.class.getSimpleName();
	
	private int menu;
	private SearchBar searchBar;
	
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
//			View v = inflater.inflate(R.layout.grid, container, false);
//			Log.d(tag, "set kindImageAdapter");
//			GridView gv = (GridView) v.findViewById(R.id.category_grid);
//			gv.setAdapter(new KindImageAdapter(inflater.getContext()));
//			gv.setOnItemClickListener(kindItemClickListener);
//			return gv;
			View v = inflater.inflate(R.layout.list, container, false);
			//for manage fragment lifecycle
			v.setOnKeyListener(onBackPressed);
			
			ListView list = (ListView) v.findViewById(R.id.list);
			list.setAdapter(new KindImageAdapter(inflater.getContext()));
			list.setOnItemClickListener(kindItemClickListener);
			list.setOnScrollListener(scrollListener);
			//set searchbar listener
			searchBar = (SearchBar) v.findViewById(R.id.searchbar);
			SearchKeyListener onKeyListener = new SearchKeyListener(searchBar, getFragmentManager(), inflater.getContext());
			SearchButtonListener onButtonListener = new SearchButtonListener(searchBar, getFragmentManager(), inflater.getContext());
			searchBar.getQueryStringView().setOnKeyListener(onKeyListener);
			searchBar.getSearchButton().setOnClickListener(onButtonListener);
			
			return v;
		//for area
		case 1:
			Log.d(tag, "set ctrdArrayAdapter");
			v = inflater.inflate(R.layout.list, container, false);
			//for manage fragment lifecycle
			v.setOnKeyListener(onBackPressed);
			
			ListView l = (ListView) v.findViewById(R.id.list);
			l.setAdapter(new StringArrayAdapter(inflater.getContext(), R.layout.list_item_1, Constants.ctrdName));
			l.setOnItemClickListener(ctrdClickListener);
			l.setOnScrollListener(scrollListener);
			//set searchbar listener
			searchBar = (SearchBar) v.findViewById(R.id.searchbar);
			onKeyListener = new SearchKeyListener(searchBar, getFragmentManager(), inflater.getContext());
			onButtonListener = new SearchButtonListener(searchBar, getFragmentManager(), inflater.getContext());
			searchBar.getQueryStringView().setOnKeyListener(onKeyListener);
			searchBar.getSearchButton().setOnClickListener(onButtonListener);
			
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
	
	public void onPause() 
	{
		hideKeyBoard();
		super.onPause();
	}
	
	OnItemClickListener kindItemClickListener = new OnItemClickListener() 
	{
		public void onItemClick(android.widget.AdapterView<?> arg0, View view, int selected, long id) 
		{
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
	
	OnScrollListener scrollListener = new OnScrollListener() {
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) 
		{
			if(scrollState == SCROLL_STATE_TOUCH_SCROLL)
			{
				hideKeyBoard();
			}
		}
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) 
		{}
	};
	
	private void hideKeyBoard()
	{
		EditText editText = searchBar.getQueryStringView();
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
	}
	
	OnKeyListener onBackPressed = new OnKeyListener() {
		
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) 
		{
			if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP)
			{
				getFragmentManager().popBackStackImmediate();
			}
			return false;
		}
	};
}
