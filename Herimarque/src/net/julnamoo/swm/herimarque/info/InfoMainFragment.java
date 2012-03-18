package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.info.listener.SearchButtonListener;
import net.julnamoo.swm.herimarque.info.listener.SearchKeyListener;
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
		SearchKeyListener onKeyListener = new SearchKeyListener(searchBar, getFragmentManager(), inflater.getContext());
		SearchButtonListener onButtonListener = new SearchButtonListener(searchBar, getFragmentManager(), inflater.getContext());
		searchBar.getQueryStringView().setOnKeyListener(onKeyListener);
		searchBar.getSearchButton().setOnClickListener(onButtonListener);

		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) 
			{
				hideKeyboard();
			}
		});
		return v;
	}

	@Override
	public void onPause() 
	{
		hideKeyboard();
		super.onPause();
	}
	private void startKind()
	{
		hideKeyboard();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = new InfoSubMainFragment(0);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.info_main, f, "infoSub");
		ft.addToBackStack("infoSub");
		ft.commit();
	}

	private void startArea()
	{
		hideKeyboard();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = new InfoSubMainFragment(1);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.info_main, f, "infoSub");
		ft.addToBackStack("infoSub");
		ft.commit();
	}

	private void startNear()
	{
		hideKeyboard();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = new NearFragment(getActivity().getApplicationContext(), 5, 5);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.info_main, f, "infoSub");
		ft.addToBackStack("infoSub");
		ft.commit();
	}

	private void hideKeyboard()
	{
		EditText editText = searchBar.getQueryStringView();
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
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
}
