package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.view.SearchBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InfoMainFragment extends Fragment {

	private String tag = InfoMainFragment.class.getSimpleName();

	private SearchBar searchBar;
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.info_main, container, false);
		v.findViewById(R.id.kind).setOnClickListener(tabClickListener);
		v.findViewById(R.id.area).setOnClickListener(tabClickListener);
		v.findViewById(R.id.near).setOnClickListener(tabClickListener);
		
		searchBar = (SearchBar) v.findViewById(R.id.search_infomain);
		searchBar.getQueryStringView().setOnKeyListener(onKeyListener);
		
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				Log.w(tag, "infomain touched");
			}
		});
		return v;
	}

	private void startKind()
	{
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = new InfoSubMainFragment(0);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.info_main, f, "infoSub");
		ft.addToBackStack("infoSub");
		ft.commit();
	}

	private void startArea()
	{
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = new InfoSubMainFragment(1);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.info_main, f, "infoSub");
		ft.addToBackStack("infoSub");
		ft.commit();
	}

	private void startNear()
	{

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = new NearFragment(getActivity().getApplicationContext(), 5, 5);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.info_main, f, "infoSub");
		ft.addToBackStack("infoSub");
		ft.commit();
	}

	OnClickListener tabClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.d(tag, "selcted " + v.getId());

			switch (v.getId()) {
			case R.id.kind : startKind();
			break;
			case R.id.area : startArea();
			break;
			case R.id.near : startNear();
			break;
			default:
				break;
			}
		}
	};
	
	OnKeyListener onKeyListener = new OnKeyListener() {
		
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) 
		{
			if(keyCode == KeyEvent.KEYCODE_ENTER)
			{
				String query = searchBar.getQueryString();
				Log.d(tag, "start search : " + query);
				return true;
			}
			return false;
		}
	};
}
