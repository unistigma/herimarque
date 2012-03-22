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
			getWritableDatabase();
			Log.i(tag, "copy database file");
			copyDataBase();
		} 
		
		close();
	}
	
	private boolean checkDataBase()
	{
		SQLiteDatabase db = null;
		try
		{
			db =SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
			Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.HERITAGE_TABLE_NAME + ";", null);
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
			InputStream is = context.getAssets().open("herimarque2.db");
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
	
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		initTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
//		Log.w(tag,"Upgrading database from version " + oldVersion + " to "
//				+ newVersion + ", which will destroy all old data");
//
		Log.d(tag, "sqlHelper, upgrade");
		db.execSQL("DROP TABLE IF EXISTS " + Constants.HERITAGE_TABLE_NAME);
		onCreate(db);
	}
	
	private void initTable(SQLiteDatabase db)
	{
		Log.i(tag, "onCreate");
		//crate heritage table
        StringBuilder tableCreator = new StringBuilder();
        tableCreator.append("CREATE TABLE IF NOT EXISTS ").append(Constants.HERITAGE_TABLE_NAME);
        tableCreator.append("( _id INTEGER PRIMARY KEY AUTOINCREMENT ");
        for(int i = 0; i < Constants.itemFields.length; ++i)
        {
                tableCreator.append(", ").append(Constants.itemFields[i]).append(" TEXT ");
        }
        tableCreator.append(", sin_x_rad TEXT, cos_x_rad TEXT, sin_y_rad TEXT, cos_y_rad TEXT);");
        String sql = tableCreator.toString();
        Log.d(tag, "create shceme : " + sql);
        db.execSQL(sql);

        //crate index with description of the itemCd field
//        tableCreator = new StringBuilder("CREATE INDEX IF NOT EXISTS ");
//        tableCreator.append(Constants.DB_NAME).append(".idx_heritage ON ");
//        tableCreator.append(Constants.TABLE_NAME).append(" ( crltsNm )");
//        sql = tableCreator.toString();
//        Log.d(sql, "create index : " + sql);
//        db.execSQL(sql);
        
        //creat FTS virtual table with index
        /**
         * db.execSQL("CREATE VIRTUAL TABLE " + TABLE_WORDS_FTS + " USING fts3(" + COL_ID + ", " + COL_KEY_LABEL + ", "
                + COL_KEY_DESCRIPTION + " , " + COL_KEY_LANGUAGE + ", " + COL_KEY_TERM + " " + ");");
         */
//        StringBuilder vtableCreator = new StringBuilder("CREATE VIRTUAL TABLE ");
//        vtableCreator.append(Constants.TABLE_NAME).append("_fts USING fts( _id");
//        for(int i = 0; i < Constants.itemFields.length; ++i)
//        {
//        	vtableCreator.append(", ").append(Constants.itemFields[i]);
//        }
//        vtableCreator.append(");");
//        String s = vtableCreator.toString();
//        Log.d(tag, "create fts virtual table : " + s);
//        db.execSQL(s);
	}
}
