package net.julnamoo.swm.herimarque.create;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class CancleDialogFragment extends DialogFragment{
	
	private String tag = CancleDialogFragment.class.getSimpleName();
	
	Context mContext;
	
	public CancleDialogFragment(Context mContext) 
	{
		this.mContext = mContext;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle arg0) 
	{
		return	new AlertDialog.Builder(mContext)
		.setTitle("발자국 남기기 취소하기")
		.setMessage("취소하시겠습니까?")
		.setPositiveButton("예", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				//remove the data from sharedpreference
				Log.d(tag, "cancle make checkin");

				dialog.dismiss();
			}
		})
		.setNegativeButton("아니오", null)
		.create();
	}
}