package net.julnamoo.swm.herimarque.fragment;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.temp.InfoSubMainFragment;
import net.julnamoo.swm.herimarque.util.ExitExecutor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfoMainFragment extends Fragment implements OnItemClickListener {

	private String tag = InfoMainFragment.class.getSimpleName();

//	Button kind;
//	Button age;
//	Button area;
//	Button near;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.info_main, container, false);
		ListView l = (ListView) v.findViewById(R.id.info_menu_list);
		
		//set menus
		String[] menus = {"종목", "지역", "시대", "주변 문화유산 보기"};
		ArrayAdapter<String> infoMenuList = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, menus);
		
		//attatch adapter
		l.setAdapter(infoMenuList);
		l.setOnItemClickListener(this);
		
		v.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if( keyCode == KeyEvent.KEYCODE_BACK )
				{
					Log.d(tag, "back : " + event.getRepeatCount());
					return true;
				}
				return v.onKeyDown(keyCode, event);
			}
		});
		return v;

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int selected, long id) 
	{
		Log.d(tag, "selcted " + selected);
		
		Fragment f = new InfoSubMainFragment(selected);
    	FragmentTransaction ft = getFragmentManager().beginTransaction();
    	ft.addToBackStack("infoSub");
    	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    	ft.add(R.id.info_main, f, "infoSub");
    	ft.hide(InfoMainFragment.this);
    	ft.commit();
	}
	
	@Override
	public void onPause() 
	{
		// TODO Auto-generated method stub
		super.onPause();
	}
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) 
//	{
//	    if (keyCode == KeyEvent.KEYCODE_BACK) {
//	        moveTaskToBack(true);
//	        return true;
//	    }
//	    return super.onKeyDown(keyCode, event);
//	}
}
