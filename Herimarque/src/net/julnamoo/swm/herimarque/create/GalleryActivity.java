package net.julnamoo.swm.herimarque.create;

import net.julnamoo.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GalleryActivity extends Activity {

	private String tag = GalleryActivity.class.getSimpleName();

	Cursor cursor;
	Uri uri[];
	int[] id;
	int count;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid);

		cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
		if (cursor != null) 
		{
			count = cursor.getCount();
			Log.d(tag, "cursor count : " + count);
			uri = new Uri[cursor.getCount()];
			id = new int[cursor.getCount()];
			new AsyncTask<Void, Void, Void>() 
			{

				@Override
				protected Void doInBackground(Void... params) 
				{
					for (int i = 0; i < cursor.getCount(); i++) 
					{
						cursor.moveToPosition(i);
						uri[i] = Uri.parse(cursor.getString(1));
						id[i] = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID));
						Log.d(tag, "URI:"+uri[i]);
					}
					return null;
				};
			}.execute();

		}
		GridView gridview = (GridView) findViewById(R.id.grid);
		gridview.setAdapter(new ImageAdapter(GalleryActivity.this, count));
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent i = new Intent(GalleryActivity.this, AddPhotoAndCommentActivity.class);
				Log.e("intent : ", ""+position);
				i.putExtra("fname", uri[position].getEncodedPath());
				startActivityForResult(i, 0);
			}
		});
	}

	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		Intent intent = new Intent();
		intent.putExtras(data);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}
	
	class ImageAdapter extends BaseAdapter
	{
		Context c;
		int count;

		public ImageAdapter(Context c, int count) 
		{
			this.c= c;
			this.count = count;
		}
		@Override
		public int getCount() {
			return count;
		}

		@Override
		public Object getItem(int position) {
			return uri[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			LinearLayout view = new LinearLayout(c);
			view.setOrientation(LinearLayout.VERTICAL);
			GridView.LayoutParams params = new GridView.LayoutParams(200, 240);
			view.setLayoutParams(params);

			ImageView image = new ImageView(c);
			image.setLayoutParams(new LinearLayout.LayoutParams(200,200));
			image.setScaleType(ImageView.ScaleType.FIT_XY);
			image.setPadding(5, 5, 5, 5);
			image.setContentDescription(uri[position].toString());
			image.setId(position);

			//			image.setImageURI(uri[position]);

			BitmapFactory.Options bounds = new BitmapFactory.Options();
			bounds.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(uri[position].getEncodedPath(), bounds);
			if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
			{
				image.setImageURI(uri[position]);
			}else
			{
				int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
						: bounds.outWidth;

				BitmapFactory.Options opts = new BitmapFactory.Options();
				opts.inSampleSize = originalSize / 100;
				image.setImageBitmap(BitmapFactory.decodeFile(uri[position].getPath(), opts));

			}
			Log.d(tag, uri[position].toString());
			view.addView(image);
			return view;
		}
	}
	
}