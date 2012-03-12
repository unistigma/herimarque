package net.julnamoo.swm.herimarque.fragment;

import net.julnamoo.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;

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
}
