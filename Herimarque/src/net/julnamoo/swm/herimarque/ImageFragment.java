package net.julnamoo.swm.herimarque;

import net.julnamoo.swm.herimarque.view.HeritageImageView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ImageFragment extends Fragment {

	HeritageImageView imageView;
	
	public ImageFragment(HeritageImageView imageView) 
	{
		this.imageView = imageView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup arg1, Bundle arg2) 
	{
		ViewGroup vg = (ViewGroup) imageView.getParent();
		if( vg != null)
		{
			vg.removeView(imageView);
		}
		
		FrameLayout frameLayout = new FrameLayout(inflater.getContext());
		frameLayout.addView(imageView);
		
		return frameLayout;
	}
	
}
