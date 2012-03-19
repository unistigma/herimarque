package net.julnamoo.swm.herimarque.info;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class LoadingFragment extends DialogFragment {
	public LoadingFragment() {	
	}

	@Override
	public void onCreate(Bundle arg0) 
	{
		int style = DialogFragment.STYLE_NO_FRAME;
		int theme = android.R.style.Theme_Light;
		setStyle(style, theme);
		super.onCreate(arg0);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		ProgressDialog dialog = new ProgressDialog(getActivity());
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMessage("Loading...");
		return dialog;
	}

}
