package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.KindImageAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class InfoOurHeritageServiceActivity extends Activity {

	private String tag = InfoOurHeritageServiceActivity.class.getSimpleName();
	
	//request codes for list search. The size of them is the same size with kind category size.
	final static int LIST0 = 0;
	final static int LIST1 = 1;
	final static int LIST2 = 2;
	final static int LIST3 = 3;
	final static int LIST4 = 4;
	final static int LIST5 = 5;
	final static int LIST6 = 6;
	final static int LIST7 = 7;
	final static int LIST8 = 8;
	final static int LIST9 = 9;
	final static int LIST10 = 10;
	final static int LIST11 = 11;
	final static int LIST12 = 12;
	
	//request code for kind category search
	final static int CATEGORY_KIND = 101;
	
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

		/** connect views with radio buttons and init view**/
		findViewById(R.id.butt_info_our_list).setOnClickListener(flipper);
		findViewById(R.id.butt_info_our_category).setOnClickListener(flipper);
		list.setVisibility(View.VISIBLE);
		category.setVisibility(View.INVISIBLE);
		
		/** build list search view **/
		GridView grid = (GridView) list;
		KindImageAdapter adapter = new KindImageAdapter(InfoOurHeritageServiceActivity.this);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(listStarter);
		
		////////cannot for using return request code
		/** Set request code for each search **/
		/*
		//list search request code from 0 to kind size
		LIST_KIND = new ArrayList<Integer>();
		for(int i = 0; i < adapter.getCount(); ++i)
		{
			LIST_KIND.add(i);
		}
		//category search request code following list search
		CATEGORY_KIND = adapter.getCount();
		 */
		
		/** build category search view **/
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

	/** For list search **/
	OnItemClickListener listStarter = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
		{
			Toast.makeText(InfoOurHeritageServiceActivity.this, arg1.getId() + "선택", Toast.LENGTH_SHORT).show();
			
			//start the activity with the request code of selected index
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
		case LIST0:
			Toast.makeText(InfoOurHeritageServiceActivity.this, "국보", Toast.LENGTH_SHORT).show();
			break;
		case LIST1:
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "보물", Toast.LENGTH_SHORT).show();
			break;
		case LIST2: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "사적", Toast.LENGTH_SHORT).show();
			break;
		case LIST3: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "명승 ", Toast.LENGTH_SHORT).show();
			break;
		case LIST4: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "천연기념물 ", Toast.LENGTH_SHORT).show();
			break;
		case LIST5: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "무형문화재 ", Toast.LENGTH_SHORT).show();
			break;
		case LIST6: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "민속문화재 ", Toast.LENGTH_SHORT).show();
			break;
		case LIST7: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "등록문화재 ", Toast.LENGTH_SHORT).show();
			break;
		case LIST8: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "시도 유형문화재 ", Toast.LENGTH_SHORT).show();
			break;
		case LIST9: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "시도 무형문화재 ", Toast.LENGTH_SHORT).show();
			break;
		case LIST10: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "시도 기념물 ", Toast.LENGTH_SHORT).show();
			break;
		case LIST11: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "시도 민속문화재 ", Toast.LENGTH_SHORT).show();
			break;
		case LIST12: 
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "문화재 자료 ", Toast.LENGTH_SHORT).show();
			break;
		case CATEGORY_KIND:
//			Toast.makeText(InfoOurHeritageServiceActivity.this, "return from kind", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	};
}
