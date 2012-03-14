package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfoMainFragment extends Fragment implements OnItemClickListener {

	private String tag = InfoMainFragment.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.list, container, false);
		ListView l = (ListView) v.findViewById(R.id.list);

		//set menus
		//		String[] menus = {"종목", "지역", "시대", "주변 문화유산 보기"};
		String[] menus = {"종목", "지역", "주변 문화유산 보기"};
		ArrayAdapter<String> infoMenuList = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, menus);

		//attatch adapter
		l.setAdapter(infoMenuList);
		l.setOnItemClickListener(this);

		return v;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int selected, long id) 
	{
		Log.d(tag, "selcted " + selected);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = null;
		if(selected == 2)
		{
			f = new NearFragment(getActivity().getApplicationContext(), 5, 5);
		}else
		{
			f = new InfoSubMainFragment(selected);
		}
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.info_main, f, "infoSub");
		ft.addToBackStack("infoSub");
		ft.commit();
	}
}
