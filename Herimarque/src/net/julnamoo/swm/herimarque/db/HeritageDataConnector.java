package net.julnamoo.swm.herimarque.db;

import java.io.File;
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
	
	private SQLiteDatabase db;
	
	public HeritageDataConnector(Context context)
	{
		super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
		this.db = getWritableDatabase();
	}
	
	public void insert(Item item)
	{
		ContentValues row = new ContentValues();;
		
		//insert values of the item instance
		try
		{
			Field[] fields = item.getClass().getDeclaredFields();
			Log.d(tag, "Total " + fields.length + " fields");
			for(Field field : fields)
			{
				field.setAccessible(true);
				
				String fname = field.getName();
				String value = (String) field.get(item);
				if(value == null || value.equals("null")) value = "default";
				value.replace('\n', ' ');
				
				Log.d(fname, value);
				row.put(fname, value);
			}
			
			db.insert(Constants.TABLE_NAME, null, row);
			Log.d(tag, "Success to insert " +item.getCrltsNm());
		}catch (Exception e) {
			Log.e(tag, e.getLocalizedMessage());
			e.printStackTrace();
		}
		
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
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(Constants.DB_NAME);
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
		sb.append(Constants.TABLE_NAME).append(" (");

		int i = 0;
		for(i = 0; i < Constants.itemFields.length -1 ; ++i)
		{
			sb.append(Constants.itemFields[i]).append(" TEXT, ");
		}
		sb.append(Constants.itemFields[i]).append(" TEXT);");
		
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
