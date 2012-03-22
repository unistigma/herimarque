package net.julnamoo.swm.herimarque.create;

import net.julnamoo.R;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AddCommentFragment extends Fragment {

	Bitmap photo;
	
	public AddCommentFragment(Bitmap photo)
	{
		this.photo = photo;
	}
	
	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) 
	{
		View view = arg0.inflate(R.layout.addcomment, arg1, false);
		view.findViewById(R.id.myphoto);
		return view;
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
}
