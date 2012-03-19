package net.julnamoo.swm.herimarque.common;

import net.julnamoo.swm.herimarque.view.HeritageImageView;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ImageActivity extends Activity {

	HeritageImageView imageView;

//	public ImageActivity(HeritageImageView imageView) 
//	{
//		this.imageView = imageView;
//	}

	public ImageActivity()	{}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		imageView = (HeritageImageView) savedInstanceState.getSerializable("imageView");
		
		super.onCreate(savedInstanceState);
		ViewGroup vg = (ViewGroup) imageView.getParent();
		if( vg != null)
		{
			vg.removeView(imageView);
		}
		
		FrameLayout frameLayout = new FrameLayout(ImageActivity.this);
		frameLayout.addView(imageView);
		
		setContentView(frameLayout);
	}
}
