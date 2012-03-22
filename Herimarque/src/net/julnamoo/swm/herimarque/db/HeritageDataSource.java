package net.julnamoo.swm.herimarque.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.julnamoo.swm.herimarque.model.Heritage;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.util.CursorToHeritage;
import net.julnamoo.swm.herimarque.util.DegreeToRadius;
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
	
	public void insert(Heritage item) throws IllegalArgumentException, IllegalAccessException
	{
		ContentValues values = new ContentValues();
		Field[] fields = Heritage.class.getDeclaredFields();
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
		
		//calculate radian value for distance
		if(item.getXCnts() == null)
		{
			values.put("sin_x_rad", 0);
			values.put("cos_x_rad", 0);
		}else
		{
			double x_rad = DegreeToRadius.degToRad(Double.valueOf(item.getXCnts()));
			String sin_x_rad = String.valueOf(Math.sin(x_rad));
			String cos_x_rad = String.valueOf(Math.sin(x_rad));
			values.put("sin_x_rad", sin_x_rad);
			values.put("cos_x_rad", cos_x_rad);
		}
		
		if(item.getYCnts() == null)
		{
			values.put("sin_y_rad", 0);
			values.put("cos_y_rad", 0);
		}else
		{
			double y_rad = DegreeToRadius.degToRad(Double.valueOf(item.getYCnts()));
			String sin_y_rad = String.valueOf(Math.sin(y_rad));
			String cos_y_rad = String.valueOf(Math.cos(y_rad));
			values.put("sin_y_rad", sin_y_rad);
			values.put("cos_y_rad", cos_y_rad);
		}
		
		db.insert(Constants.HERITAGE_TABLE_NAME, null, values);
//		db.insert(Constants.TABLE_NAME+"_fts", null, values);
	}
	
	
	public List<Heritage> getAllItems()
	{
		List<Heritage> result = new ArrayList<Heritage>();
		
		Cursor cursor = db.query(Constants.HERITAGE_TABLE_NAME, Constants.columns, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			result.add(CursorToHeritage.cursor2heritage(cursor));
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return result;
	}
	
	public List<Heritage> select(String column, String value)
	{
		List<Heritage> result = new ArrayList<Heritage>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(Constants.HERITAGE_TABLE_NAME);
		sb.append(" WHERE ").append(column).append("='").append(value).append("';");
		
		String sql = sb.toString();
		Log.d(value, "select sql, " + sql);
		Cursor cursor =  db.rawQuery(sql, null);
		
		if(cursor == null) return null;
			
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			result.add(CursorToHeritage.cursor2heritage(cursor));
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return result;
	}

	public Cursor search(String query)
	{
		StringBuilder queryBuilder = new StringBuilder("SELECT ");
		Cursor cursor = null;
		
		return cursor;
	}
}
