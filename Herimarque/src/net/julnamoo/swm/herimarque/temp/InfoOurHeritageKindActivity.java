package net.julnamoo.swm.herimarque.temp;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.adapter.KindImageAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class InfoOurHeritageKindActivity extends Activity {

	private static String tag = InfoOurHeritageKindActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		GridView grid = (GridView) findViewById(R.id.grid);
		Log.d(tag, "Set grid view");
		KindImageAdapter adapter = new KindImageAdapter(this);
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(InfoOurHeritageKindActivity.this, arg1.getId() + "선택", Toast.LENGTH_SHORT).show();
			}
			
		});
		Log.d(tag, "Complete to set up grid view");
	}
	
	
}
