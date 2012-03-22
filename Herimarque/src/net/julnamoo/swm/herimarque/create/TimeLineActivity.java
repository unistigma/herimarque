package net.julnamoo.swm.herimarque.create;

import java.util.ArrayList;
import java.util.List;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.TImelineItemAdapter;
import net.julnamoo.swm.herimarque.db.TrackingDataSource;
import net.julnamoo.swm.herimarque.model.TimeLineItem;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.view.EditName;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class TimeLineActivity extends Activity {

	private String tag = TimeLineActivity.class.getSimpleName();
	
	EditName title;
	EditText comment;

	List<Integer> areas;
	String tableName;
	Uri mappath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_finish);
		
		tableName = getIntent().getStringExtra("tablename");
		mappath = Uri.parse(getIntent().getStringExtra("mappath"));
		areas = new ArrayList<Integer>();
		
		//set input
		title = (EditName) findViewById(R.id.title);
		title.setFocusable(false);
		comment = (EditText) findViewById(R.id.comment);
		
		//setmapview
		((ImageView) findViewById(R.id.mymap)).setImageURI(mappath);
		
		//set listview
		TrackingDataSource tds = new TrackingDataSource(TimeLineActivity.this, tableName);
		List<TimeLineItem> items = tds.getAllCheckIn();
		ListView list = (ListView) findViewById(R.id.timeline);
		list.setAdapter(new TImelineItemAdapter(TimeLineActivity.this, items));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				hideKeyBoard();
			}
		});
		
		//set area selector
		((Button) findViewById(R.id.areaselect)).setOnClickListener(areaClickListener);
		
		//set ok
		((Button) findViewById(R.id.ok)).setOnClickListener(okClickListener);
	}
	
	OnClickListener okClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Toast.makeText(TimeLineActivity.this, "새로운 여행이 서버로 업로드 됩니다", Toast.LENGTH_SHORT).show();
			//TODO
			onBackPressed();
		}
	};
	
	OnClickListener areaClickListener = new OnClickListener() {
		
		boolean[] checkedItems = new boolean[Constants.ctrdName.length];
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AlertDialog.Builder ab = new Builder(TimeLineActivity.this);
			ab.setTitle("여행한 지역을 골라주세요");
			ab.setMultiChoiceItems(Constants.ctrdName, checkedItems, null);
			ab.setNeutralButton("선택", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					for(int i = 0; i < checkedItems.length; ++i)
					{
						if(checkedItems[i])
						{
							areas.add(new Integer(Constants.ctrdCode[i]));
							Log.d(tag, Constants.ctrdName[i] + "checked");
						}
					}
				}
			});
			ab.create();
		}
	};
	
	private void hideKeyBoard()
	{
		InputMethodManager imm = (InputMethodManager) TimeLineActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(title.getApplicationWindowToken(), 0);
		imm.hideSoftInputFromWindow(comment.getApplicationWindowToken(), 0);
	}
	//String json = new Gson().toJson(timeline);
	//Log.d(tag, "converted json :" + json);
	
}
