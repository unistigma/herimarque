package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.fragment.ConfigMainFragment;
import net.julnamoo.swm.herimarque.fragment.CreateMainFragment;
import net.julnamoo.swm.herimarque.fragment.InfoMainFragment;
import net.julnamoo.swm.herimarque.fragment.ShowMainFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SubMainActivity extends FragmentActivity {

	private String tag = SubMainActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submain);

		findViewById(R.id.infoButt).setOnClickListener(flipper);
		findViewById(R.id.createButt).setOnClickListener(flipper);
		findViewById(R.id.showButt).setOnClickListener(flipper);
		findViewById(R.id.configButt).setOnClickListener(flipper);

		Intent intent = getIntent();
		int menu = intent.getIntExtra("menu", 0);

		changeView(menu);
		
	}

	OnClickListener flipper = new OnClickListener() {

		@Override
		public void onClick(View v) {

			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			
			switch (v.getId()) {
			case R.id.infoButt:
				Log.d(tag, "info pushed");
				changeView(0);
				break;
			case R.id.createButt:
				changeView(1);
				break;
			case R.id.showButt:
				changeView(2);
				break;
			case R.id.configButt: 
				changeView(3);
				break;
			default:
				break;
			}
		}
	};

	private void changeView(int id)
	{
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		switch (id) {
		case 0:
			InfoMainFragment imf = new InfoMainFragment();
			transaction.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction.replace(R.id.fragment_container, imf);
			break;
		case 1:
			CreateMainFragment cmf = new CreateMainFragment();
			transaction.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction.replace(R.id.fragment_container, cmf);
			break;
		case 2:
			ShowMainFragment smf = new ShowMainFragment();
			transaction.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction.replace(R.id.fragment_container, smf);
			break;
		case 3:
			ConfigMainFragment cfmf = new ConfigMainFragment();
			transaction.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction.replace(R.id.fragment_container, cfmf);
			break;
		default:
			break;
		}
		
		transaction.commit();
	}

	/** Info **/
//	public OnClickListener startOurHeritage = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			Intent intent = new Intent(SubMainActivity.this, InfoOurHeritageServiceActivity.class);
//			startActivity(intent);
//		}
//	};
//	
//	public OnClickListener startNearHeritage = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			
//		}
//	};
//	
//	/** Create **/
//	public OnClickListener startCreateService = new OnClickListener() {
//
//		@Override
//		public void onClick(View v) {
//
//			String name = findViewById(R.id.create_etxt_name).toString();
//
//			Intent intent = new Intent(SubMainActivity.this, CreateServiceStarterActivity.class);
//			startActivity(intent);
//		}
//	};
//	
//	/** Config **/
//	public OnClickListener fbLogin = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			Intent intent = new Intent(SubMainActivity.this, ConfigFacebookLoginActivity.class);
//			startActivity(intent);
//		}
//	};
}
