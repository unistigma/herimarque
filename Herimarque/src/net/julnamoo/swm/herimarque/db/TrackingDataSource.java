package net.julnamoo.swm.herimarque.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.julnamoo.swm.herimarque.model.TimeLineItem;
import net.julnamoo.swm.herimarque.util.Constants;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;

import com.google.gson.Gson;

public class TrackingDataSource {

	private String tag = TrackingDataSource.class.getSimpleName();

	private Context mContext;
	private String tableName;
	
	private String[] columns = {"time", "type", "latitude", "longitude", "content", "image"};
	private String[] types = {"TR", "CO", "PT", "CI"};
	public static int TRACKING = 0; //for only gps
	public static int COMMENT = 1; //for only comment
	public static int PHOTO = 2; //for photo and comment
	public static int CHECKIN = 3; //for with a heritage
	
	public TrackingDataSource(Context mContext, String tableName)
	{
		this.mContext = mContext;
		this.tableName = tableName;
		//create the table
		SQLiteDatabase db = mContext.openOrCreateDatabase(Constants.DB_NAME, 0, null);
		
		StringBuilder createTable = new StringBuilder("CREATE TABLE IF NOT EXISTS");
		createTable.append("'").append(tableName).append("'");
		createTable.append("( _id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT, type TEXT, latitude DOUBLE, longitude DOUBLE, content TEXT, image TEXT);");
		String createSQL = createTable.toString();
		
		Log.d(tag, "exec sql : " + createSQL);
		db.execSQL(createSQL);
		db.close();
	}
	
	public void addCheckIn(int type, Location checkInLocation, String content, String photoFilePath)
	{
		SQLiteDatabase db = mContext.openOrCreateDatabase(Constants.DB_NAME, 0, null);
		
		Date date = new Date(checkInLocation.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = sdf.format(date);
		
		ContentValues values = new ContentValues();
		values.put("time", timeStamp);
		values.put("type", types[type]);
		assert checkInLocation != null;
		values.put("latitude", checkInLocation.getLatitude());
		values.put("longitude", checkInLocation.getLongitude());
		values.put("content", content);
		values.put("image", photoFilePath);
		
		db.insert(tableName, null, values);
		db.close();
	}
	
	public void addCheckIn(String type, String x, String y, String timeStamp,  String content, String photoFilePath)
	{
		SQLiteDatabase db = mContext.openOrCreateDatabase(Constants.DB_NAME, 0, null);
		
		ContentValues values = new ContentValues();
		values.put("time", timeStamp);
		values.put("type", type);
		values.put("latitude", x);
		values.put("longitude", y);
		values.put("content", content);
		values.put("image", photoFilePath);
		
		db.insert(tableName, null, values);
		db.close();
	}
	
	/**
	 * convert checkin data to json array
	 * @return formatted json
	 */
	public String getAllCheckIn()
	{
		SQLiteDatabase db = mContext.openOrCreateDatabase(Constants.DB_NAME, 0, null);
		String sql = "SELECT * FROM " + tableName + ";";
		Cursor cursor = db.rawQuery(sql, null);

		List<TimeLineItem> timeline = new ArrayList<TimeLineItem>();
		while(cursor.moveToNext())
		{
			//convert to cursor to item
			TimeLineItem timeLine = cursorToItem(cursor);
			timeline.add(timeLine);
		}
		String json = new Gson().toJson(timeline);
		Log.d(tag, "converted json :" + json);
		db.close();
		return json;
	}
	
	private TimeLineItem cursorToItem(Cursor cursor)
	{
		TimeLineItem timeLine = new TimeLineItem();
		timeLine.setType(cursor.getString(cursor.getColumnIndex("type")));
		timeLine.setX(cursor.getString(cursor.getColumnIndex("latitude")));
		timeLine.setY(cursor.getString(cursor.getColumnIndex("longitude")));
		timeLine.setContent(cursor.getString(cursor.getColumnIndex("content")));
		timeLine.setImage(cursor.getString(cursor.getColumnIndex("image")));
		Log.d(tag, "cursor to item : " + timeLine.getContent());
		return timeLine;
	}

	public void dropTable()
	{
		SQLiteDatabase db = mContext.openOrCreateDatabase(Constants.DB_NAME, 0, null);
		db.execSQL("DROP TABLE " + tableName);
		db.close();
	}
}
