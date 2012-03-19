package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.util.ExitExecutor;
import net.julnamoo.swm.herimarque.util.MapContainer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.maps.MapView;

public class SubMainActivity extends FragmentActivity {

	private String tag = SubMainActivity.class.getSimpleName();

	private Button infoButt;
	private Button createButt;
	private Button showButt;
	private Button configButt;
	
	private FrameLayout fragmentContainer;
	private int currMenu;
	
	private ExitExecutor ee;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setMapView();
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
				Log.d(tag, "curr menu : " + currMenu);
				Fragment target = null;
				switch (currMenu) {
				case 0:
					Log.d(tag, "at info, count " + getSupportFragmentManager().getBackStackEntryCount());
					target = getSupportFragmentManager().findFragmentById(R.id.info_main);
//					getSupportFragmentManager().beginTransaction().remove(target);
//					getSupportFragmentManager().beginTransaction().commitAllowingStateLoss();
					target.getFragmentManager().popBackStackImmediate();
					Log.d(tag, "at info, count " + getSupportFragmentManager().getBackStackEntryCount());
					break;
				case 1 :
					Log.d(tag, "at create, count " + getSupportFragmentManager().getBackStackEntryCount());
					target= getSupportFragmentManager().findFragmentById(R.id.create_main);
//					getSupportFragmentManager().beginTransaction().remove(target);
//					getSupportFragmentManager().beginTransaction().commitAllowingStateLoss();
					target.getFragmentManager().popBackStackImmediate();
					Log.d(tag, "at create, count " + getSupportFragmentManager().getBackStackEntryCount());
					break;
				case 2 : 
					break;
				case 3 :
					break;
				default:
					break;
				}
				return true;
			}
			
			//finish the application
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
		infoButt.setSelected(false);
		createButt.setSelected(false);
		showButt.setSelected(false);
		configButt.setSelected(false);
		v.setSelected(true);

		for(int i = 0 ; i < fragmentContainer.getChildCount(); ++i)
		{
			View f = (View) fragmentContainer.getChildAt(i);
			f.setVisibility(View.INVISIBLE);
		}
		
		switch (v.getId()) {
		case R.id.infoButt:
			Log.d(tag, "info pushed");
			findViewById(R.id.info_main).setVisibility(View.VISIBLE);
			currMenu = 0;
			break;
		case R.id.createButt:
			Log.d(tag, "create pushed");
			findViewById(R.id.create_main).setVisibility(View.VISIBLE);
			currMenu = 1;
			break;
		case R.id.showButt:
			Log.d(tag, "show pushed"); 
			findViewById(R.id.show_main).setVisibility(View.VISIBLE);
			currMenu = 2;
			break;
		case R.id.configButt:
			Log.d(tag, "config pushed");
			findViewById(R.id.config_main).setVisibility(View.VISIBLE);
			currMenu = 3;
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean isRouteDisplayed() 
	{
		return false;
	}
	
	private void setMapView()
	{
//		AttributeSet attrSet = Xml.asAttributeSet(getResources().getXml(R.layout.map));
//		FrameLayout fl = new FrameLayout(SubMainActivity.this, attrSet);
		MapContainer.mapView = new MapView(SubMainActivity.this, this.getString(R.string.apiKey));
		MapContainer.mapView.setClickable(true);
		MapContainer.mapView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));;
	}

	public int getCurrMenu() {
		return currMenu;
	}
	
	
}
