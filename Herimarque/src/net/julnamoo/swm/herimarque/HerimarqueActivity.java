package net.julnamoo.swm.herimarque;

import java.io.IOException;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.db.HeritageDataFromJSON;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
//        HeritageDataFromJSON insert = new HeritageDataFromJSON("ageList_ver_1.json", HerimarqueActivity.this);
        HeritageDataFromJSON insert;
		try {
			insert = new HeritageDataFromJSON(getAssets().open("ageList_ver_1.json"), HerimarqueActivity.this);
			Thread thread = new Thread(insert);
	        thread.start();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    OnClickListener selector = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HerimarqueActivity.this, SubMainActivity.class);
			
			switch (v.getId()) {
			case R.id.info:
				intent.putExtra("menu", 0);
				break;
			case R.id.create:
				intent.putExtra("menu", 1);
				break;
			case R.id.show:
				intent.putExtra("menu", 2);
				break;
			case R.id.config:
				intent.putExtra("menu", 3);
				break;
			default:
				break;
			}
			startActivity(intent);
		}
	};
}