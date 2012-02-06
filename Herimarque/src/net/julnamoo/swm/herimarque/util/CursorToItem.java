package net.julnamoo.swm.herimarque.util;

import java.lang.reflect.Field;

import net.julnamoo.swm.herimarque.model.Item;
import android.database.Cursor;
import android.util.Log;

public class CursorToItem {

	final private static String tag = CursorToItem.class.getSimpleName();
	
	public static Item cursor2Item(Cursor cursor)
	{
		Item item = new Item();
		Field[] fs = Item.class.getDeclaredFields();
		Log.d(tag, "cursor to item, cursor size is " + cursor.getColumnCount() + ", field size is " + fs.length);
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
						Log.d(f.getName(), cursor.getString(i));
					} catch (Exception e) 
					{
						Log.e(tag, "Cannot convert");
						continue;
					}
					continue;
				}
			}
		}
		
		return item;
	}
}
