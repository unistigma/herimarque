package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.app.Activity;
import android.os.Bundle;

public class InfoDetailHeritage extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_detail);
		
		String num = getIntent().getStringExtra("registerNum");
	}
}
