package net.julnamoo.swm.herimarque.util;

import android.os.CountDownTimer;
import android.util.Log;

public class ExitExecutor extends CountDownTimer {

	private String tag = ExitExecutor.class.getSimpleName();
	
	private boolean finish;
	
	public ExitExecutor(long millisInFuture, long countDownInterval)
	{
		super(millisInFuture, countDownInterval);
		finish = false;
	}

	@Override
	public void onFinish() 
	{ 
		Log.d(tag, "finish exit executor");
		finish = finish ? false : true;
	}

	@Override
	public void onTick(long millisUntilFinished) {	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
	
}
