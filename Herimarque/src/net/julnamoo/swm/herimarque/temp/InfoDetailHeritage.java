package net.julnamoo.swm.herimarque.temp;

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
		String name = item.getCrltsNm();
		Log.d(tag, "passed item : " + name);
		
	}
	
	private void setDetail(Item item)
	{
		TextView tv;
		String value;
		
		//set name bar
		value = item.getCrltsNm();
		tv = (TextView) findViewById(R.id.info_detail_name_bar);
		tv.setText(value);
		
		//set code
		tv = (TextView) findViewById(R.id.info_detail_code);
		value = new StringBuilder(item.getItemNm()).append(" ").
				append(item.getCrltsNoNm()).append("호").toString();
		tv.setText(value);
		
		//set age
		tv = (TextView) findViewById(R.id.info_detail_age);
		value = item.getAgeCd();
		tv.setText(value);
		
		//set area
		tv = (TextView) findViewById(R.id.info_detail_area);
		value = new StringBuilder(item.getCtrdNm()).append(" ").append(item.getSignguNm()).toString();
		tv.setText(value);
		
		//set description
		tv = (TextView) findViewById(R.id.info_detail_desc);
		value = item.getCrltsDc();
		tv.setText(value);
	}
}
