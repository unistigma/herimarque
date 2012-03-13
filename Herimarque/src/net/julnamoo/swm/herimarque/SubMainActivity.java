package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.util.ExitExecutor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class SubMainActivity extends FragmentActivity {

	private String tag = SubMainActivity.class.getSimpleName();

	private Button infoButt;
	private Button createButt;
	private Button showButt;
	private Button configButt;
	
	private FrameLayout fragmentContainer;
	
	private ExitExecutor ee;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.submain);

		ee = new ExitExecutor(3000, 3000);
		infoButt = (Button) findViewById(R.id.infoButt);
		infoButt.setOnClickListener(flipper);

		createButt = (Button) findViewById(R.id.createButt); 
		createButt.setOnClickListener(flipper);

		showButt = (Button) findViewById(R.id.showButt);
		showButt.setOnClickListener(flipper);

		configButt = (Button) findViewById(R.id.configButt);
		configButt.setOnClickListener(flipper);

		fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
		//set tab button
		int menu = getIntent().getIntExtra("menu", R.id.infoButt);
		changeView(findViewById(menu));
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if( keyCode == KeyEvent.KEYCODE_BACK )
		{
			Log.d(tag, "back : " + event.getRepeatCount());
			
			if(getSupportFragmentManager().getBackStackEntryCount() > 0)
			{
				getSupportFragmentManager().popBackStackImmediate();
				return true;
			}
			
			if(ee.isFinish()) 
			{
				Log.d(tag, "finish");
				finish();
			}else
			{
				Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
				ee.start();
				ee.setFinish(true);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	OnClickListener flipper = new OnClickListener() {

		@Override
		public void onClick(View v) { changeView(v); }
	};

	private void changeView(View v)
	{
		infoButt.setTextColor(Color.BLACK);
		createButt.setTextColor(Color.BLACK);
		showButt.setTextColor(Color.BLACK);
		configButt.setTextColor(Color.BLACK);

		((Button) v).setTextColor(Color.RED);

		for(int i = 0 ; i < fragmentContainer.getChildCount(); ++i)
		{
			View f = (View) fragmentContainer.getChildAt(i);
			f.setVisibility(View.INVISIBLE);
		}
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//		ft.addToBackStack("main");
		ft.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);

//		Fragment f = null;
		switch (v.getId()) {
		case R.id.infoButt:
			Log.d(tag, "info pushed");
			findViewById(R.id.info_main).setVisibility(View.VISIBLE);
//			f = new InfoMainFragment();
			break;
		case R.id.createButt:
			Log.d(tag, "create pushed");
			findViewById(R.id.create_main).setVisibility(View.VISIBLE);
//			f = new CreateMainFragment();
			break;
		case R.id.showButt:
			Log.d(tag, "show pushed"); 
			findViewById(R.id.show_main).setVisibility(View.VISIBLE);
//			f = new ShowMainFragment();
			break;
		case R.id.configButt:
			Log.d(tag, "config pushed");
			findViewById(R.id.config_main).setVisibility(View.VISIBLE);
//			f = new ConfigMainFragment();
			break;
		default:
			break;
		}
//		ft.replace(R.id.fragment_container, f);
//		int val = ft.commitAllowingStateLoss();
	}
}
