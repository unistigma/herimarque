package net.julnamoo.swm.herimarque.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.util.Constants;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Convert JSON data to sql.
 * Control SQLite version with json data version.
 * @author Julie_air
 *
 */
public class HeritageDataFromJSON implements Runnable{

	final private String tag = HeritageDataFromJSON.class.getSimpleName();
	
	InputStream is;
	
	Context mContext;
	
	public HeritageDataFromJSON(InputStream is, Context mContext)
	{
		this.mContext = mContext;
		this.is = is;
	}
	/**
	 * Initialize database with json
	 */
	@Override
	public void run()
	{
		if(isTableExist()) return;
		
		Log.i(tag, "Create the new table");
		HeritageDataSource dataSource = new HeritageDataSource(mContext);
		dataSource.open();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try 
		{
			StringBuffer sb = new StringBuffer();
			String line = br.readLine();
			while(line != null)
			{
				sb.append(line);
				line = br.readLine();
			}
			
			is.close();
			String string = sb.toString();
			JsonParser jsonparser = new JsonParser();
			JsonArray jsonArray = jsonparser.parse(string).getAsJsonArray();
			
			Gson gson = new Gson();
			for(JsonElement je : jsonArray)
			{
				Item item = gson.fromJson(je, Item.class);
				Log.d(tag, "insert " + item.getCrltsNm());
				dataSource.insert(item);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			Log.e(tag, "Fail to init db");
		} 
		dataSource.close();
		
		Log.d(tag, "Finish to import");
	}
	
	private boolean isTableExist()
	{
		boolean exist;
		HeritageSQLiteHelper sqlHelper = new HeritageSQLiteHelper(mContext);
		SQLiteDatabase db = sqlHelper.getReadableDatabase();
		
		//this is always true because called after calling onCreate in HeritageSQLHelper
//		Cursor cursor = db.rawQuery("SELECT COUNT() FROM sqlite_master WHERE name ='" + Constants.TABLE_NAME+"';", null);
		
		Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME, null);
		Log.d(tag, "current tuple size : " + cursor.getCount()); 
		//It needs sufficient test logic whether data is there.
		exist = cursor != null && cursor.getCount() > 10 ? true : false;
		
		Log.d(tag, "table exist test : " + exist);
		cursor.close();
		db.close();
		sqlHelper.close();
		return exist;
	}
}
	