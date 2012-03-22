package net.julnamoo.swm.herimarque.util;

import java.lang.reflect.Field;

import net.julnamoo.swm.herimarque.model.Heritage;
import android.database.Cursor;
import android.util.Log;

public class CursorToHeritage {

	final private static String tag = CursorToHeritage.class.getSimpleName();
	
	public static Heritage cursor2heritage(Cursor cursor)
	{
		Heritage item = new Heritage();
		Field[] fs = Heritage.class.getDeclaredFields();
//		Log.d(tag, "cursor to item, cursor size is " + cursor.getColumnCount() + ", field size is " + fs.length);
		for(int i = 0; i < cursor.getColumnCount(); ++i)
		{
			for(Field f : fs)
			{
				if(f.getName().equals(cursor.getColumnName(i)))
				{
					f.setAccessible(true);
					try 
					{
						f.set(item, cursor.getString(i));
					} catch (Exception e) 
					{
						Log.e(tag, "Cannot convert");
						continue;
					}
					continue;
				}
			}
		}
		
		Log.d(tag, item.getCrltsNm());
		return item;
	}
}
