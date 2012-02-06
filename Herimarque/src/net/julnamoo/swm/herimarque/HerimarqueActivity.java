package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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