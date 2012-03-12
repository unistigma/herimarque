package net.julnamoo.swm.herimarque;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class ConfigFacebookLoginActivity extends Activity {

	private static String appkey = "333692923320687";
	Facebook facebook;
	
	//SSO setting
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences fbPrefs;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		facebook = new Facebook(appkey);
		
		//get access token
		fbPrefs = getPreferences(MODE_PRIVATE);
		String access_token = fbPrefs.getString("access_token", null);
		long expires = fbPrefs.getLong("access_expires", 0);
		
		if(access_token != null)
		{
			facebook.setAccessToken(access_token);
		}
		
		if(expires != 0)
		{
			facebook.setAccessExpires(expires);
		}
		
		// if access token is expired
		if(!facebook.isSessionValid())
		{
			facebook.authorize(this, new String[] {}, new DialogListener() {
				
				@Override
				public void onFacebookError(FacebookError e) {
					Toast.makeText(ConfigFacebookLoginActivity.this, "페이스북 로그인에 실패하였습니다.:"+e, Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onError(DialogError e) {
					Toast.makeText(ConfigFacebookLoginActivity.this, "페이스북 로그인에 실패하였습니다.:"+e, Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onComplete(Bundle values) {
					SharedPreferences.Editor editor = fbPrefs.edit();
					editor.putString("access_token", facebook.getAccessToken());
					editor.putLong("access_expires", facebook.getAccessExpires());
					editor.commit();
				}
				
				@Override
				public void onCancel() {}
			});
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
	}
}
