package net.julnamoo.swm.herimarque.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.julnamoo.swm.herimarque.util.Constants;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class HeritageSQLiteHelper extends SQLiteOpenHelper{

	private String tag = HeritageSQLiteHelper.class.getSimpleName();

	private static String DB_PATH = "/data/data/net.julnamoo/databases/";
	private static String DB_NAME = "heritage";

	private Context context;
	
	public HeritageSQLiteHelper(Context context)
	{
		super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
		this.context = context;
	}

//	@Override
//	public synchronized void close() 
//	{
//		if(myDb != null) myDb.close();
//		super.close();
//	}
	
	public void createDataBase()
	{
		if(!checkDataBase())
		{
			getReadableDatabase();
			Log.i(tag, "copy database file");
			copyDataBase();
		} 
	}
	
	private boolean checkDataBase()
	{
		SQLiteDatabase db = null;
		try
		{
			db =SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
			Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + ";", null);
			if(cursor.getCount() < 2)
			{
				Log.d(tag, "databases exist but empty table");
				db.close();
				db = null;
			}
		}catch (Exception e) {
			Log.d(tag, "catch the exception in checkDB");
			db = null;
		}
		if(db != null) db.close();
		return db != null ? true : false;
	}
	
	private void copyDataBase()
	{
		try 
		{
			Log.d(tag, "read the file then set the db");
			InputStream is = context.getAssets().open("herimarque.db");
			OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);

			byte[] buffer = new byte[1024];
			int read = 0;
			while((read = is.read(buffer)) > 0)
			{
				os.write(buffer, 0, read);
			}
			os.flush();
			os.close();
			is.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
//	public SQLiteDatabase openDataBase()
//	{
//		this.createDataBase();
//		myDb = this.getReadableDatabase();
//		return myDb;
//	}
//	
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		Log.i(tag, "onCreate");
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ").append(Constants.TABLE_NAME);
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
//		Log.w(tag,"Upgrading database from version " + oldVersion + " to "
//				+ newVersion + ", which will destroy all old data");
//
		Log.d(tag, "sqlHelper, upgrade");
		db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
		onCreate(db);
	}
	
}
