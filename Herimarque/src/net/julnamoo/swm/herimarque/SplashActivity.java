package net.julnamoo.swm.herimarque;

import java.io.IOException;
import java.io.InputStream;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.db.HeritageDataFromJSON;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

	private String tag = SplashActivity.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Thread t = new Thread(db);
		t.start();
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

	//	private void init()
	//	{
	//		setupDB();
	//		Handler handler = new Handler() {
	//			@Override
	//			public void handleMessage(Message msg)
	//			{
	//				finish();
	//				startActivity(new Intent(SplashActivity.this, HerimarqueActivity.class));
	//			}
	//		};
	//		handler.sendEmptyMessageDelayed(0, 2500);
	//	}

	private void setupDB()
	{
		new HeritageSQLiteHelper(getApplicationContext()).createDataBase();
		//set data
//		InputStream is;
//		try 
//		{
//			is = getAssets().open("kindList.json");
//			Thread t = new Thread(new HeritageDataFromJSON(is, getApplicationContext()));
//			t.start();
//		} catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
	}

	Runnable db = new Runnable() {

		@Override
		public void run() {
			setupDB();	
		}
	};
}
