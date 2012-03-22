package net.julnamoo.swm.herimarque.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.julnamoo.swm.herimarque.model.Heritage;
import net.julnamoo.swm.herimarque.util.Constants;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
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
				Heritage item = gson.fromJson(je, Heritage.class);
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
		
		getSQLFile();
	}
	
	private boolean isTableExist()
	{
		boolean exist;
		HeritageSQLiteHelper sqlHelper = new HeritageSQLiteHelper(mContext);
		SQLiteDatabase db = sqlHelper.getReadableDatabase();
		
		//this is always true because called after calling onCreate in HeritageSQLHelper
//		Cursor cursor = db.rawQuery("SELECT COUNT() FROM sqlite_master WHERE name ='" + Constants.TABLE_NAME+"';", null);
		Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.HERITAGE_TABLE_NAME, null);
		Log.d(tag, "current tuple size : " + cursor.getCount()); 
		//It needs sufficient test logic whether data is there.
		exist = cursor != null && cursor.getCount() > 10 ? true : false;
		
		Log.d(tag, "table exist test : " + exist);
		cursor.close();
		db.close();
		sqlHelper.close();
		return exist;
	}
	
	private void getSQLFile()
	{
		//get sqlite file
		try
		{
			File db = new File("/data/data/net.julnamoo/databases/heritage");
			File dir = Environment.getExternalStorageDirectory();
			Log.d(tag, "dir : " + dir.getAbsolutePath());
			File target = new File(dir.getAbsolutePath() + "/herimarque.db");
			FileOutputStream fos = new FileOutputStream(target);
			FileInputStream fis = new FileInputStream(db); //openFileInput(db.getAbsolutePath()); //

			byte[] buff = new byte[2048];
			int read = 0;
			while((read = fis.read(buff)) > 0)
			{
				fos.write(buff, 0, read);
//				Log.d(tag, "write");
			}
			fos.flush();
			fos.close();
			fis.close();
			Log.d(tag, "finish to write the file");
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
	