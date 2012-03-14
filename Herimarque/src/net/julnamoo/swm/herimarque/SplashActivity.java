package net.julnamoo.swm.herimarque;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.db.HeritageDataFromJSON;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SplashActivity extends Activity {

	private String tag = SplashActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		 
		init();
	}
	
	private void init()
	{
		setupDB();
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg)
			{
				finish();
				startActivity(new Intent(SplashActivity.this, HerimarqueActivity.class));
			}
		};
		handler.sendEmptyMessageDelayed(0, 2500);
	}
	
	private void setupDB()
	{
		new HeritageSQLiteHelper(getApplicationContext()).createDataBase();
		//set data
		/*
		InputStream is;
		try 
		{
			is = getAssets().open("kindList.json");
//			Thread t = new Thread();
//			t.start();
			new HeritageDataFromJSON(is, getApplicationContext()).run();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

		//get sqlite file
		try
		{
			File db = new File("/data/data/net.julnamoo/databases/heritage");
			File dir = Environment.getExternalStorageDirectory();
			Log.d(tag, "dir : " + dir.getAbsolutePath());
			File target = new File(dir.getAbsolutePath() + "/herimarque.db");
			FileOutputStream fos = new FileOutputStream(target);
			FileInputStream fis = new FileInputStream(db); //openFileInput(db.getAbsolutePath()); //

			byte[] buff = new byte[2048];
			int read = 0;
			while((read = fis.read(buff)) > 0)
			{
				fos.write(buff, 0, read);
				Log.d(tag, "write");
			}
			fos.flush();
			fos.close();
			fis.close();
			Log.d(tag, "finish to write the file");
		}catch (Exception e) 
		{
			e.printStackTrace();
		}*/
	}
}
