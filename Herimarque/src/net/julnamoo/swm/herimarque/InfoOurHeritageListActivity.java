package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.InfoOurListAdapter;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

public class InfoOurHeritageListActivity extends Activity {

	private String tag = InfoOurHeritageListActivity.class.getSimpleName();
	Cursor cursor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_our_list_service);

		int select = getIntent().getIntExtra("select", 0);
		TextView bar = (TextView) findViewById(R.id.info_our_list_bar);
		bar.setText(Constants.kindImgsCD[select]);
		
		SQLiteDatabase db = new HeritageSQLiteHelper(InfoOurHeritageListActivity.this).getReadableDatabase();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(Constants.TABLE_NAME);
		sb.append(" WHERE ").append("itemCd").append("='").append(Constants.kindCode[select]).append("';");
		String sql = sb.toString();
		cursor = db.rawQuery(sql, null);
		startManagingCursor(cursor);
		
		String[] cols = { Constants.itemFields[1], Constants.itemFields[4]}; //crltsNm, crltsNoNm
		int[] to = {android.R.id.text1, android.R.id.text2};
		InfoOurListAdapter adapter = new InfoOurListAdapter(InfoOurHeritageListActivity.this, android.R.layout.simple_list_item_2, cursor, cols, to);
		
		ListView list = (ListView) findViewById(R.id.info_our_list_view);
		list.setAdapter(adapter);
		list.setOnItemClickListener(listListener);
		
		db.close();
	}
	
	OnItemClickListener listListener = new OnItemClickListener() 
	{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			cursor.moveToFirst();
			cursor.move(arg2);
			
			String temp = cursor.getString(cursor.getColumnIndex("crltsNm"));
			TwoLineListItem tv = (TwoLineListItem)arg1;
			Toast.makeText(InfoOurHeritageListActivity.this, tv.getText2().getText() + ", "+temp+" 항목 선택", Toast.LENGTH_SHORT).show();
			
			//connect the tuple to item and pass it with intent
			Intent intent = new Intent(InfoOurHeritageListActivity.this, InfoDetailHeritage.class);
			//how about using preference?
		}
	};
	
}
