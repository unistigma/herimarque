package net.julnamoo.swm.herimarque.view;

import net.julnamoo.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SearchBar extends LinearLayout {

	private String tag = SearchBar.class.getSimpleName();

	View container;
	EditText queryStringView;
	ImageView searchButton;


	public SearchBar(Context context) 
	{
		super(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		container = inflater.inflate(R.layout.search, SearchBar.this);
		queryStringView = (EditText) container.findViewById(R.id.query);
		searchButton = (ImageView) container.findViewById(R.id.search);
		searchButton.setOnClickListener(searchClickListener);
	}

	public SearchBar(Context context, AttributeSet attrSet) {
		super(context, attrSet);
		LayoutInflater inflater = LayoutInflater.from(context);
		container = inflater.inflate(R.layout.search, SearchBar.this);
		queryStringView = (EditText) container.findViewById(R.id.query);
		searchButton = (ImageView) container.findViewById(R.id.search);
		searchButton.setOnClickListener(searchClickListener);
	}

	public SearchBar(Context context, AttributeSet attrSet, int defStyle)
	{
		super(context, attrSet, defStyle);
		LayoutInflater inflater = LayoutInflater.from(context);
		container = inflater.inflate(R.layout.search, SearchBar.this);
	}

	protected void setChildren()
	{
		queryStringView = (EditText) container.findViewById(R.id.query);
		searchButton = (ImageView) container.findViewById(R.id.search);
		searchButton.setOnClickListener(searchClickListener);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(keyCode == KeyEvent.KEYCODE_ENTER || keyCode == 66) 
		{
			Log.w(tag, "enter");
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	OnClickListener searchClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) 
		{
			Log.w(tag, "search clicked");
		}
	};
	
	public EditText getQueryStringView() {
		return queryStringView;
	}

	public ImageView getSearchButton() {
		return searchButton;
	}
	
	public String getQueryString()
	{
		return queryStringView.getText().toString();
	}
}
