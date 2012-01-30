package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoOurHeritageListKindFragment extends Fragment {

	private String tag = InfoOurHeritageListKindFragment.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.info_our_category_kind, container, false);
//		return super.onCreateView(inflater, container, savedInstanceState);
		return view;
	}
	
	public void setText(String item)
	{
		TextView tv = (TextView) getView().findViewById(R.id.info_our_category_test_text);
		tv.setText(item);
	}
}
