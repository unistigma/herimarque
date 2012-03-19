package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class LoadingFragment extends DialogFragment {
	public LoadingFragment() {	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		return new AlertDialog.Builder(getActivity())
		.setIcon(R.drawable.progress)
		.setTitle("Loading...")
		.create();
	}

}
