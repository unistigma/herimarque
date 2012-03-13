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
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class HerimarqueActivity extends Activity {

	/** Called when the activity is first created. */
	private String tag = HerimarqueActivity.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById(R.id.info).setOnClickListener(selector);
		findViewById(R.id.create).setOnClickListener(selector);

		//setup db
//		setupDB();
	}


	OnClickListener selector = new OnClickListener() {

		@Override
		public void onClick(View v) {
			SubMainActivity activity = new SubMainActivity();
			Intent intent = new Intent(HerimarqueActivity.this, activity.getClass());
			switch (v.getId()) {
			case R.id.info:
				intent.putExtra("menu", R.id.infoButt);
				break;
			case R.id.create:
				intent.putExtra("menu", R.id.createButt);
				break;
			default:
				break;
			}
			finishFromChild(activity);
			startActivity(intent);

		}
	};

}