package net.julnamoo.swm.herimarque.view;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.info.SearchResultFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
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
		setChildren();
	}

	public SearchBar(Context context, AttributeSet attrSet) {
		super(context, attrSet);
		LayoutInflater inflater = LayoutInflater.from(context);
		container = inflater.inflate(R.layout.search, SearchBar.this);
		setChildren();
	}

	public SearchBar(Context context, AttributeSet attrSet, int defStyle)
	{
		super(context, attrSet, defStyle);
		LayoutInflater inflater = LayoutInflater.from(context);
		container = inflater.inflate(R.layout.search, SearchBar.this);
		setChildren();
	}

	protected void setChildren()
	{
		queryStringView = (EditText) container.findViewById(R.id.query);
		queryStringView.setFilters(new InputFilter[]{
				new InputFilter.LengthFilter(10)
		});
		searchButton = (ImageView) container.findViewById(R.id.search);
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
