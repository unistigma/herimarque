package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoOurHeritageListKindActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_our_category_kind);
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			String s = extras.getString("value");
			TextView tv = (TextView) findViewById(R.id.info_our_category_test_text);
			tv.setText(s);
		}
	}
}
