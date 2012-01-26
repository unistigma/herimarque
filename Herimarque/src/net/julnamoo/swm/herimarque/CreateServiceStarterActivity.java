package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CreateServiceStarterActivity extends SubMainActivity {

	View main, infoin;
	Button start;
	Button ok;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_main);
		
		main = (View) findViewById(R.id.layout_create_main);
		infoin = (View) findViewById(R.id.layout_create_infoin);
		
		start = (Button) findViewById(R.id.butt_create_start);
		ok = (Button) findViewById(R.id.butt_create_ok);
		
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				main.setVisibility(View.INVISIBLE);
				infoin.setVisibility(View.VISIBLE);
			}
		});
		
		
	}
	
	public OnClickListener startService = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			String name = findViewById(R.id.create_etxt_name).toString();
			
			Intent intent = new Intent(CreateServiceStarterActivity.this, CreateServiceActivity.class);
			intent.putExtra("name", name);
			startActivity(intent);
		}
	};
}
