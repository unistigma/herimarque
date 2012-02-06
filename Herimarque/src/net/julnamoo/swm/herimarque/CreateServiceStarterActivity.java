package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class CreateServiceStarterActivity extends SubMainActivity {

	View main, infoin;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_main);

		//set events
		main = (View) findViewById(R.id.layout_create_main);
		main.setVisibility(View.INVISIBLE);
		infoin = (View) findViewById(R.id.layout_create_infoin);
		infoin.setVisibility(View.VISIBLE);

		findViewById(R.id.butt_create_start).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				main.setVisibility(View.INVISIBLE);
				infoin.setVisibility(View.VISIBLE);
			}
		});

		findViewById(R.id.butt_create_ok).setOnClickListener(startService);
		
		//remove new line
	}

	public OnClickListener startService = new OnClickListener() {

		@Override
		public void onClick(View v) {

			EditText et = (EditText) findViewById(R.id.create_etxt_name);
			String name = et.getText().toString(); 

			Intent intent = new Intent(CreateServiceStarterActivity.this, CreateServiceActivity.class);
			intent.putExtra("name", name);
			startActivity(intent);
		}
	};
}
