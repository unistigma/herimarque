package net.julnamoo.swm.herimarque.show;

import net.julnamoo.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;

public class ShowMainFragment extends Fragment {

	private String tag = ShowMainFragment.class.getSimpleName();
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.show_main, container, false);
		v.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if( keyCode == KeyEvent.KEYCODE_BACK )
				{
//					getFragmentManager().popBackStack("main", 0);
					Log.d(tag, "back : " + event.getRepeatCount());
					return true;
				}
				return v.onKeyDown(keyCode, event);
			}
		});
		return v;
	}
}
