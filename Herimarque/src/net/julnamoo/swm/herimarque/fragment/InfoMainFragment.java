package net.julnamoo.swm.herimarque.fragment;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.InfoOurHeritageServiceActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class InfoMainFragment extends Fragment {

	private String tag = InfoMainFragment.class.getSimpleName();

	Button ourHeritage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.info_main, container, false);

		v.findViewById(R.id.butt_info_ourheritage).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) 
			{
				Log.d(tag, "our heritage pushed. start activity");
				Intent intent = new Intent(getActivity(), InfoOurHeritageServiceActivity.class);
				startActivity(intent);
			}
		});

		v.findViewById(R.id.butt_info_nearheritage).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				Log.d(tag, "near heritage pushed");
			}
		});
		return v;
	}

}
