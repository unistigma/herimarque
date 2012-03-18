package net.julnamoo.swm.herimarque.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.util.CursorToItem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class HeritageDataSource {

	private String tag = HeritageDataSource.class.getSimpleName();
	
	private SQLiteDatabase db;
	private HeritageSQLiteHelper dbHelper;
	
	public HeritageDataSource(Context context)
	{
		dbHelper = new HeritageSQLiteHelper(context);
	}
	
	public void open()
	{
		db = dbHelper.getReadableDatabase();
	}
	
	public void close()
	{
		dbHelper.close();
		if(db != null) db.close();
	}
	
	public void insert(Item item) throws IllegalArgumentException, IllegalAccessException
	{
		ContentValues values = new ContentValues();
		Field[] fields = Item.class.getDeclaredFields();
		for(Field f : fields)
		{
			f.setAccessible(true);
			String column = f.getName();
			String value = (String) f.get(item);
			if(value == null || value.equals("null"))
			{
				value = "default";
			}
			value = value.replace('\n', ' ');
			values.put(column, value);
		}
		
		db.insert(Constants.TABLE_NAME, null, values);
//		db.insert(Constants.TABLE_NAME+"_fts", null, values);
	}
	
	public List<Item> getAllItems()
	{
		List<Item> result = new ArrayList<Item>();
		
		Cursor cursor = db.query(Constants.TABLE_NAME, Constants.columns, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			result.add(CursorToItem.cursor2Item(cursor));
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return result;
	}
	
	public List<Item> select(String column, String value)
	{
		List<Item> result = new ArrayList<Item>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(Constants.TABLE_NAME);
		sb.append(" WHERE ").append(column).append("='").append(value).append("';");
		
		String sql = sb.toString();
		Log.d(value, "select sql, " + sql);
		Cursor cursor =  db.rawQuery(sql, null);
		
		if(cursor == null) return null;
			
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			result.add(CursorToItem.cursor2Item(cursor));
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return result;
	}

//	public List<Item> search(String query)
//	{
//		
//	}
}
