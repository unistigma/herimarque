package net.julnamoo.swm.herimarque;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.model.Item;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputBinding;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

	private String tag = DetailFragment.class.getSimpleName();

	private Item item;
	private Bitmap bmImg;
	private boolean isFinish;

	public DetailFragment(Item item)
	{
		this.item = item;

		//	if(imgUrl != null)
		//	{
		//	Log.d(tag, "request url with thread : " + imgUrl);
		//	Thread t = new Thread(new GetImage(imgUrl, bmImg));
		//	isFinish = false;
		//	t.start();
		//	}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		if(savedInstanceState != null)
		{
			Log.d(tag, "check the savedInstanceState, itemCd:" + item.getItemCd());
			item = (Item) savedInstanceState.get("item");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.info_detail, container, false);

		TextView tv;
		String value;

		//set name bar
		value = item.getCrltsNm();
		tv = (TextView) v.findViewById(R.id.info_detail_name_bar);
		tv.setText(value);

		//set code
		tv = (TextView) v.findViewById(R.id.info_detail_code);
		value = new StringBuilder(item.getItemNm()).append(" ").
				append(item.getCrltsNoNm()).append("호").toString();
		tv.setText(value);

		//set age
		tv = (TextView) v.findViewById(R.id.info_detail_age);
		value = item.getAgeCd();
		tv.setText(value);

		//set area
		tv = (TextView) v.findViewById(R.id.info_detail_area);
		value = new StringBuilder(item.getCtrdNm()).append(" ").append(item.getSignguNm()).toString();
		tv.setText(value);

		//set description
		tv = (TextView) v.findViewById(R.id.info_detail_desc);
		value = item.getCrltsDc();
		tv.setText(value);

		//set Image
		ImageView image = (ImageView) v.findViewById(R.id.info_detail_img);
		//// get the image
		String imgUrl = item.getImageUrl();
		if(imgUrl == null || imgUrl.length() == 0 || imgUrl.contains("default"))
		{
			imgUrl = item.getListImageUrl();
		}
		try 
		{
			URL origin = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection) origin.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bmImg = BitmapFactory.decodeStream(is);
			image.setImageBitmap(bmImg);
		} catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		/** kind of using thread **/
		//		while(!isFinish)
		//		{
		//			try 
		//			{
		//				Thread.sleep(500);
		//				Log.d(tag, "sleep");
		//			} catch (InterruptedException e) 
		//			{
		//				e.printStackTrace();
		//			}
		//		}
		//		Log.d(tag, "success to get img");
		return v;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		outState.putSerializable("item", item);
		outState.putInt("depth", 3);
	}

	private class GetImage implements Runnable
	{
		String url;
		Bitmap bmImg;

		public GetImage(String url, Bitmap bmImg) 
		{
			this.url = url;
			this.bmImg = bmImg;
		}

		@Override
		public void run() {
			try 
			{
				URL origin = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) origin.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream is = conn.getInputStream();
				bmImg = BitmapFactory.decodeStream(is);
				isFinish = true;
			} catch (MalformedURLException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}

		}
	}
}