package net.julnamoo.swm.herimarque;

import android.app.Activity;
import android.os.Bundle;

public class CreateServiceActivity extends Activity {

	String name;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		name = getIntent().getStringExtra("name");
		
	}
}
