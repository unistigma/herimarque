package net.julnamoo.location.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.maps.MapActivity;

public class LocationServiceExampleActivity extends MapActivity {
    /** Called when the activity is first created. */
	Context context;
	TextView mText;

	ToggleButton mToggleButt;
	Button mButt;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtrip);
        
        context = this;
        
    }

    public void onClick(View target)
    {
    	if (target == mToggleButt)
    	{
    		if(mToggleButt.isChecked())
    		{
    			Toast.makeText(context, "시작?", Toast.LENGTH_SHORT).show();
    			Intent start = new Intent(LocationTrackingService.ACTION_START);
    			start.putExtra("fname", "test");
				startService(new Intent(LocationTrackingService.ACTION_START));
    		}else
			{
				Toast.makeText(context, "끝", Toast.LENGTH_SHORT).show();
				startService(new Intent(LocationTrackingService.ACTION_STOP));
			}
    		
    	}else if(target == mButt)
    	{
    		startService(new Intent(LocationTrackingService.ACTION_GETLOCATION));
    	}
    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}