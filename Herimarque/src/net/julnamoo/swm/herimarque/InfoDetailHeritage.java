package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.model.Item;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class InfoDetailHeritage extends Activity {

	final private String tag = InfoDetailHeritage.class.getSimpleName();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_detail);
		
		Item item = (Item) getIntent().getSerializableExtra("item");
		setDetail(item);
		
		//for test 
//		String name = getIntent().getStringExtra(Constants.itemFields[1]);
		String name = item.getCrltsNm();
		Log.d(tag, "passed item : " + name);
		
	}
	
	private void setDetail(Item item)
	{
		//set name
		TextView tv = (TextView) findViewById(R.id.info_detail_name);
		tv.setText(item.getCrltsNm());
	}
}
