package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class InfoOurHeritageServiceActivity extends Activity {

	final static int CATEGORY_KIND = 0;
	
	//search menu view
	View category, list;

	//category search 
	Button kindList, ageList, areaList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_our_main);

		//set main layout for each menus
		list = (View) findViewById(R.id.info_our_list);
		category = (View) findViewById(R.id.info_our_category);

		/** connect views to buttons **/
		//for main search
		findViewById(R.id.butt_info_our_list).setOnClickListener(flipper);
		findViewById(R.id.butt_info_our_category).setOnClickListener(flipper);
		list.setVisibility(View.VISIBLE);
		category.setVisibility(View.INVISIBLE);

		//for category search
		//click listener for category search buttons
		findViewById(R.id.butt_info_our_kind).setOnClickListener(categoryStarter);
		findViewById(R.id.butt_info_our_age).setOnClickListener(categoryStarter);
		findViewById(R.id.butt_info_our_area).setOnClickListener(categoryStarter);
	}

	/** for changing view **/
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
		list.setVisibility(View.INVISIBLE);
		category.setVisibility(View.INVISIBLE);

		switch (key) {
		case 0:
			list.setVisibility(View.VISIBLE);
			break;
		case 1:
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
				Intent intent = new Intent(InfoOurHeritageServiceActivity.this, InfoOurHeritageKindActivity.class);
				startActivityForResult(intent, CATEGORY_KIND);
				break;

			case R.id.butt_info_our_age:
//				Toast.makeText(InfoOurHeritageServiceActivity.this, "age pressed", Toast.LENGTH_SHORT).show();
				break;

			case R.id.butt_info_our_area:
//				Toast.makeText(InfoOurHeritageServiceActivity.this, "area pressed", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (requestCode) {
		case CATEGORY_KIND:
			Toast.makeText(InfoOurHeritageServiceActivity.this, "return from kind", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	};
}
