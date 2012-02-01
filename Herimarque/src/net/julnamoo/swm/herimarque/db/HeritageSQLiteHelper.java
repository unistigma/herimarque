package net.julnamoo.swm.herimarque.db;

import net.julnamoo.swm.herimarque.Constants;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class HeritageSQLiteHelper extends SQLiteOpenHelper{

	private String tag = HeritageSQLiteHelper.class.getSimpleName();
	
	public HeritageSQLiteHelper(Context context)
	{
		super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		Cursor cursor = db.rawQuery("SELECT tbl_name FROM sqlite_master WHERE tbl_name='"+Constants.TABLE_NAME+"';", null);
		
		if(cursor != null && cursor.getCount() > 0)
		{
			cursor.close();
			db.close();
			
			return;
		}
		
		Log.i(tag, "Initial DB");
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(Constants.TABLE_NAME);
		sb.append("( _id INTEGER PRIMARY KEY AUTOINCREMENT ");
		int i = 0;
		for(i = 0; i < Constants.itemFields.length; ++i)
		{
			sb.append(", ").append(Constants.itemFields[i]).append(" TEXT ");
		}
		sb.append(");");
		String sql = sb.toString();
		Log.d(tag, "create shceme : " + sql);
		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		Log.w(tag,"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		
		db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
		onCreate(db);
	}
}
