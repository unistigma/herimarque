package net.julnamoo.swm.herimarque;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.view.HeritageImageView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

	private String tag = DetailFragment.class.getSimpleName();

	private Item item;

	//for async task
	private HeritageImageView image;
	private Context mContext;

	public DetailFragment(Item item, Context mContext)
	{
		this.item = item;
		this.mContext = mContext;
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
		View v = (ScrollView) inflater.inflate(R.layout.info_detail, container, false);
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.info_detail);
		
		//set Image
		image = (HeritageImageView) v.findViewById(R.id.info_detail_img);
		//// get the imageUrl
		String imgUrl = item.getImageUrl();
		if(imgUrl == null || imgUrl.length() == 0 || imgUrl.contains("default"))
		{
			imgUrl = item.getListImageUrl();
		}
		new GetImage(imgUrl).execute();

		TextView tv;
		String value;

		//set name bar
		tv = (TextView) v.findViewById(R.id.info_detail_name_bar);
		value = item.getCrltsNm();
		if(value.contains("default"))
		{
			tv.setHeight(0);
			tv.setVisibility(View.INVISIBLE);
		}else
		{
			tv.setText(value);
		}

		//set code
		tv = (TextView) v.findViewById(R.id.info_detail_code);
		value = new StringBuilder(item.getItemNm()).append(" ").
				append(item.getCrltsNoNm()).append("호").toString();
		if(value.contains("default"))
		{
			tv.setHeight(0);
			tv.setVisibility(View.INVISIBLE);
		}else
		{
			tv.setText(value);
		}

		//set age
		tv = (TextView) v.findViewById(R.id.info_detail_age);
		value = item.getAgeCd();
		if(value.contains("default"))
		{
			tv.setHeight(0);
			tv.setVisibility(View.INVISIBLE);
		}else
		{
			tv.setText(value);
		}

		//set area
		tv = (TextView) v.findViewById(R.id.info_detail_area);
		value = new StringBuilder(item.getCtrdNm()).append(" ").append(item.getSignguNm()).toString();
		if(value.contains("default"))
		{
			tv.setHeight(0);
			tv.setVisibility(View.INVISIBLE);
		}else
		{
			tv.setText(value);
		}

		//set description
		tv = (TextView) v.findViewById(R.id.info_detail_desc);
		value = item.getCrltsDc();
		if(value.contains("default"))
		{
			tv.setHeight(0);
			tv.setVisibility(View.INVISIBLE);
		}else
		{
			tv.setText(value);
		}

		image.setOnClickListener(imgeViewClickListener);

		return v;
	}	

	@Override
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		outState.putSerializable("item", item);
		outState.putInt("depth", 3);
	}

	private class GetImage extends AsyncTask<Void, Void, Void>
	{
		String url;
		Bitmap bmImg;

		int width;
		
		LoadingFragment loadingFragment;

		public GetImage(String url) 
		{
			this.url = url;
		}

		@Override
		protected void onPreExecute() 
		{
			loadingFragment = new LoadingFragment();
			loadingFragment.show(getFragmentManager(), "Loading...");
		}

		@Override
		protected Void doInBackground(Void... params) 
		{
			try 
			{
				URL origin = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) origin.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream is = conn.getInputStream();
				bmImg = BitmapFactory.decodeStream(is);
				width = image.getWidth();
				Log.d(tag, "before"+item.getCrltsNm()+" >>bitmap width :" + bmImg.getWidth() + ", imageview width : " + width);
			} catch (MalformedURLException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) 
		{
			super.onProgressUpdate(values);
		}

		protected void onPostExecute(Void result) 
		{
			if(bmImg == null) return; //then set another image for empty
			
			int height = width * bmImg.getHeight() / bmImg.getWidth();
			Log.d(url, "get new width, height : " + width + "," + height);
			bmImg = Bitmap.createScaledBitmap(bmImg, width, height, true);
			image.setImageBitmap(bmImg);
			loadingFragment.dismiss();
		}
	}

	OnClickListener imgeViewClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) 
		{
			Log.d(tag, "image clicked");
			//sol1 - fail
			//			Intent intent = new Intent(getActivity().getApplicationContext(), ImageActivity.class);
			//			intent.putExtra("imageView", (Serializable) v);
			//			startActivity(intent);

			//cannot do it.... because of back pressed
			//			ViewGroup vg = (ViewGroup) image.getParent();
			//			if( vg != null)
			//			{
			//				vg.removeView(image);
			//			}
			//			FrameLayout frameLayout = new FrameLayout(getActivity().getApplicationContext());
			//			frameLayout.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.FILL_PARENT));
			//			frameLayout.addView(image);
			//			getActivity().setContentView(frameLayout);

			// not successful but not bad
			Fragment f = new ImageFragment(image);
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.replace(R.id.info_main, f);
			ft.addToBackStack("wholeImage");
			ft.commit();
		}
	};
	
	class LoadingFragment extends DialogFragment
	{
		public LoadingFragment() {	}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.progress)
                    .setTitle("Loading...")
                    .create();
        }
	}
}