package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.KindImageAdapter;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class HerimarqueActivity extends Activity {
	
    /** Called when the activity is first created. */
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.info).setOnClickListener(selector);
        findViewById(R.id.create).setOnClickListener(selector);
        findViewById(R.id.show).setOnClickListener(selector);
        findViewById(R.id.config).setOnClickListener(selector);
        
        /** set up db **/
//        Thread t = new Thread(new Runnable() {
//        	
//			@Override
//			public void run() {
//				HeritageSQLiteHelper sqlHelper = new HeritageSQLiteHelper(getApplicationContext());
//				SQLiteDatabase db = sqlHelper.getReadableDatabase();
//				while(db.isOpen() && db.isDbLockedByCurrentThread()){
//					getWindow().takeSurface(null);
//					Log.d("main", "checking db");
//				}
//				db.close();
//			}
//		});
//        t.start();
//        SQLiteDatabase db = new HeritageSQLiteHelper(this).getReadableDatabase();
//        while(db.isOpen() && (db.isDbLockedByCurrentThread() || db.isDbLockedByOtherThreads()))
//        {
//        	Log.d("main", "checking db");
//        }
//        db.close();
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
			case R.id.show:
				intent.putExtra("menu", R.id.showButt);
				break;
			case R.id.config:
				intent.putExtra("menu", R.id.configButt);
				break;
			default:
				break;
			}
			finishFromChild(activity);
			startActivity(intent);
			
		}
	};
	
}