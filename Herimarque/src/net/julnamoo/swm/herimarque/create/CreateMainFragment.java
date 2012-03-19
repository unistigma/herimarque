package net.julnamoo.swm.herimarque.create;

import net.julnamoo.R;
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

public class CreateMainFragment extends Fragment {

	private String tag = CreateMainFragment.class.getName();

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v =inflater.inflate(R.layout.create_main, container, false);
		v.findViewById(R.id.butt_create_start).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				Fragment f = new Createsub();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.replace(R.id.create_main, f, "create");
				ft.addToBackStack("create");
				ft.commit();
			}
		});
		
		
		v.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					Log.d(tag, "can be..?");
				}
				return false;
			}
		});
		return v;
	}
	
}
