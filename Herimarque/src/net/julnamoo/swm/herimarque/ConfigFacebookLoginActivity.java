package net.julnamoo.swm.herimarque;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class ConfigFacebookLoginActivity extends SubMainActivity {

	Facebook facebook;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		facebook = new Facebook("333692923320687");
		
		facebook.authorize(this, new DialogListener() {
			
			@Override
			public void onFacebookError(FacebookError e) {	}
			
			@Override
			public void onError(DialogError e) {	}
			
			@Override
			public void onComplete(Bundle values) {		}
			
			@Override
			public void onCancel() {	}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
	}
}
