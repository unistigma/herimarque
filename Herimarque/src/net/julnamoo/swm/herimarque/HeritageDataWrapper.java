package net.julnamoo.swm.herimarque;

import java.lang.reflect.Field;

import net.julnamoo.swm.herimarque.model.Item;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class HeritageDataWrapper extends SQLiteOpenHelper {

	private static final String tag = HeritageDataWrapper.class.getSimpleName();
	
	private static final String DB_NAME = "herimarque.db";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "heritage";
	
	private Context context;
	private SQLiteDatabase db;
	private SQLiteStatement stmt;
	
	public HeritageDataWrapper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
		this.db = getWritableDatabase();
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
