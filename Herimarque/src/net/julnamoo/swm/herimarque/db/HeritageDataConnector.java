package net.julnamoo.swm.herimarque.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.julnamoo.swm.herimarque.Constants;
import net.julnamoo.swm.herimarque.model.Item;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HeritageDataConnector extends SQLiteOpenHelper {

	private static final String tag = HeritageDataConnector.class.getSimpleName();
	
	private static final String DB_NAME = "herimarque.db";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "heritage";
	
	private SQLiteDatabase db;
	
	public HeritageDataConnector(Context context)
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

	/*
	 * Return Heritage object from a sqlite satisfying param.
	 * It retrieves all fields of the table for creating a instance.
	 */
	public List<Item> select(String key, String value)
	{
		List<Item> result = new ArrayList<Item>();
		
		//build select query
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(DB_NAME);
		sb.append(" WHERE ").append(key).append("= '").append(value).append("';");
		String sql = sb.toString();
		Log.d(tag, "exec run query, " + sql);
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext())
		{
			Item item = new Item();
			for(int i =0 ; i < Constants.itemFields.length; ++i)
			{
				String val = cursor.getString(i);
				try 
				{
					//set value of the field with reflection API
					Item.class.getField(Constants.itemFields[i]).set(item, value);
				} catch (Exception e) {
//					e.printStackTrace();
					Log.e(tag, e.getMessage(), e.getCause());
				}
				
				result.add(item);
			}
		}
		
		return result;
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
