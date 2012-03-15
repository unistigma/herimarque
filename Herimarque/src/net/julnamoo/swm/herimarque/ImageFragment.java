package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.view.HeritageImageView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ImageFragment extends Fragment {

	HeritageImageView imageView;
	ViewGroup imageContainer;
	
	public ImageFragment(HeritageImageView imageView) 
	{
		this.imageView = imageView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup arg1, Bundle arg2) 
	{
		//set original container
		imageContainer = (ViewGroup) imageView.getParent();
		if( imageContainer != null)
		{
			imageContainer.removeView(imageView);
		}
		
		this.imageView.setClickable(false);
		imageView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		FrameLayout frameLayout = new FrameLayout(inflater.getContext());
		frameLayout.addView(imageView);
		frameLayout.setBackgroundColor(getResources().getColor(R.color.basic));
		
		return frameLayout;
	}
	
	@Override
	public void onPause() 
	{
		((ViewGroup) imageView.getParent()).removeView(imageView);
		imageContainer.addView(imageView);
		super.onPause();
	}
}
