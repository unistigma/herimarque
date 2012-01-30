package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class InfoOurHeritageServiceActivity extends Activity {

	View category;
	GridView list;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_our_main);
		
		//set main layout for each menus
		list = (GridView) findViewById(R.id.info_our_list);
		//need to be initiate the list
		
		category = (View) findViewById(R.id.info_our_category);
		
		//connect views to buttons
		findViewById(R.id.butt_info_our_list).setOnClickListener(flipper);
		findViewById(R.id.butt_info_our_category).setOnClickListener(flipper);
		
	}
	
	OnClickListener flipper = new OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			switch (v.getId()) {
			case R.id.butt_info_our_list:
				changeView(0);
				break;
			case R.id.butt_info_our_category:
				changeView(1);
				break;
			default:
				break;
			}
		}
	};
	
	public void changeView(int key)
	{
		switch (key) {
		case 0:
			category.setVisibility(View.INVISIBLE);
			list.setVisibility(View.VISIBLE);
			break;
		case 1:
			list.setVisibility(View.INVISIBLE);
			category.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
	
	OnClickListener listStarter = new OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			Toast.makeText(InfoOurHeritageServiceActivity.this, "Please set the grid", Toast.LENGTH_SHORT).show();
		}
	};
	
	OnClickListener categoryStarter = new OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			switch (v.getId()) {
			case R.id.butt_info_our_kind:
				Toast.makeText(InfoOurHeritageServiceActivity.this, "kind pressed", Toast.LENGTH_SHORT).show();
				break;
				
			case R.id.butt_info_our_age:
				Toast.makeText(InfoOurHeritageServiceActivity.this, "age pressed", Toast.LENGTH_SHORT).show();
				break;

			case R.id.butt_info_our_area:
				Toast.makeText(InfoOurHeritageServiceActivity.this, "area pressed", Toast.LENGTH_SHORT).show();
				break;
				
			default:
				break;
			}
		}
	};
}
