package net.julnamoo.swm.herimarque.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class TurnOnGPSFragment extends DialogFragment{
	public TurnOnGPSFragment() {}
	
	@Override
	public Dialog onCreateDialog(Bundle arg0) 
	{
		return new AlertDialog.Builder(getActivity())
		.setTitle("내 위치를 확인할 수 없습니다")
		.setMessage("내 위치를 사용하시겠습니까?")
		.setPositiveButton("설정확인하러가기", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d("gpsdialog", "clicked");
				Intent gpsOnIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(gpsOnIntent);
			}
		})
		.create();
	}
}
