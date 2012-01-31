package net.julnamoo.swm.herimarque;

import java.lang.reflect.Field;

import net.julnamoo.swm.herimarque.model.Item;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HeritageDataWrapper extends SQLiteOpenHelper {

	private static final String tag = HeritageDataWrapper.class.getSimpleName();
	
	private static final String DB_NAME = "herimarque.db";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "heritage";
	
	private SQLiteDatabase db;
	
	public HeritageDataWrapper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
		this.db = getWritableDatabase();
	}
	
	public void insert(Item item)
	{
		ContentValues row = new ContentValues();;
		
		//insert values of the item instance
		try
		{
			Field[] fields = item.getClass().getDeclaredFields();
			for(Field field : fields)
			{
				String fname = field.getName();
				String value = (String) field.get(item);
				if(value == null) value = "";
				
				row.put(fname, value);
			}
		}catch (Exception e) {
			Log.e(tag, "error in building insert sql string with " + item.getCrltsNm());
		}
		db.insert(TABLE_NAME, null, row);
		Log.d(tag, "Success to insert " +item.getCrltsNm());
		
		return;
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		StringBuilder sb = new StringBuilder("CREATE TABLE ");
		sb.append(TABLE_NAME).append(" ");
		
		for(String column : Constants.itemFields)
		{
			sb.append(column).append(" TEXT");
		}
		sb.append(");");
		
		String q = sb.toString();
		Log.d(tag, "create query:" + q);
		db.execSQL(q); 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		Log.w(tag, "Upgrading database, this will drop tables and recreate");
		onCreate(db);
	}
}
