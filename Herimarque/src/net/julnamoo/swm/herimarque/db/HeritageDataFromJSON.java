package net.julnamoo.swm.herimarque.db;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.julnamoo.swm.herimarque.model.Item;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Convert JSON data to sql.
 * Control SQLite version with json data version.
 * @author Julie_air
 *
 */
public class HeritageDataFromJSON implements Runnable{

	final private String tag = HeritageDataFromJSON.class.getSimpleName();
	
	InputStream is;
	
	Context mContext;
	
	public HeritageDataFromJSON(InputStream is, Context mContext)
	{
		this.mContext = mContext;
		this.is = is;
	}
	/**
	 * Initialize database with json
	 */
	@Override
	public void run()
	{
		HeritageDataConnector dbCon = new HeritageDataConnector(mContext);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try 
		{
//			StringBuffer sb = new StringBuffer();
//			byte[] buffer = new byte[2048];
//			int offset = 0;
//			while((offset = is.read(buffer)) > 0)
//			{
//				String s = new String(buffer);
//				Log.d(tag, "append " + s);
//				sb.append(s);
//			}
			StringBuffer sb = new StringBuffer();
			String line = br.readLine();
			while(line != null)
			{
				sb.append(line);
				line = br.readLine();
			}
			
			is.close();
			String string = sb.toString();
			Log.d(tag, "read json, " + string);
			JsonParser jsonparser = new JsonParser();
			JsonArray jsonArray = jsonparser.parse(string).getAsJsonArray();
			
			Gson gson = new Gson();
			for(JsonElement je : jsonArray)
			{
				Item item = gson.fromJson(je, Item.class);
				Log.d(tag, "insert " + item.getCrltsNm());
				dbCon.insert(item);
			}
		} catch (IOException e) 
		{
//			e.printStackTrace();
			Log.e(tag, "Fail to init db");
		}
		dbCon.close();
	}
	
}
	