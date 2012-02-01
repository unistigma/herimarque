package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.KindImageAdapter;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class InfoOurHeritageListActivity extends Activity {

	private String tag = InfoOurHeritageListActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_our_list_service);

		int select = getIntent().getIntExtra("select", 0);
		
		SQLiteDatabase db = new HeritageSQLiteHelper(InfoOurHeritageListActivity.this).getReadableDatabase();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(Constants.TABLE_NAME);
		sb.append(" WHERE ").append("itemCd").append("='").append(Constants.kindCode[select]).append("';");
		String sql = sb.toString();
		Cursor cursor = db.rawQuery(sql, null);
		startManagingCursor(cursor);
		
		String[] cols = { Constants.itemFields[1], Constants.itemFields[4]}; //crltsNm, itemNm, crltsNoNm
		int[] to = {android.R.id.text1, android.R.id.text2};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(InfoOurHeritageListActivity.this, android.R.layout.simple_list_item_1, cursor, cols, to);
		ListView list = (ListView) findViewById(R.id.info_our_list_view);
		list.setAdapter(adapter);
		
		db.close();
		
//		HeritageDataSource db = new HeritageDataSource(InfoOurHeritageListActivity.this);
//
//		Log.i(tag, "List of " + Constants.kindCode[select]);
//		List<Item> result = db.select("itemCd", Constants.kindCode[select]);
//		List<String> nList = new ArrayList<String>();
//		for(Item i : result)
//		{
//			nList.add(i.getCrltsNm());
//		}
//		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(InfoOurHeritageListActivity.this, android.R.layout.simple_list_item_1, nList);
//		
	}
	
}
