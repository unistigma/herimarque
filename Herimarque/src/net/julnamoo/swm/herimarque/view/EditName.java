package net.julnamoo.swm.herimarque.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class EditName extends EditText {

	public EditName(Context context) {
		super(context);
	}

	public EditName(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	public EditName(Context context, AttributeSet attrs, int defStsyle)
	{
		super(context, attrs, defStsyle);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(keyCode == KeyEvent.KEYCODE_ENTER) return true;
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		// TODO Auto-generated method stub
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
	}
}
