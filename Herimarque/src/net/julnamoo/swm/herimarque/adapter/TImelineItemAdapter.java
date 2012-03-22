package net.julnamoo.swm.herimarque.adapter;

import java.io.File;
import java.util.List;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.model.TimeLineItem;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TImelineItemAdapter extends BaseAdapter{

	private String tag = TImelineItemAdapter.class.getSimpleName();

	private String[] types = {"TR", "CO", "PT", "CI"};
	private Context mContext;
	private List<TimeLineItem> timeline;

	public TImelineItemAdapter(Context mContext, List<TimeLineItem> timeline)
	{
		this.mContext = mContext;
		this.timeline = timeline;
	}

	@Override
	public int getCount() 
	{
		return timeline.size();
	}
	@Override
	public TimeLineItem getItem(int position) 
	{
		return timeline.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		TimeLineItem item = timeline.get(position);
		RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.cicontainer, parent, false);

		//set myphoto
		ImageView myphoto = (ImageView) view.findViewById(R.id.myphoto);
		myphoto.setContentDescription(item.getImage());
		myphoto.setId(position);
		if(item.getImage() != null && item.getImage().length() > 0)
		{
			BitmapFactory.Options bounds = new BitmapFactory.Options();
			bounds.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(item.getImage(), bounds);
			if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
			{
				File file = new File(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString(), item.getImage());
				Uri uri = Uri.fromFile(file);
				myphoto.setImageURI(uri);
			}else
			{
				int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
						: bounds.outWidth;

				BitmapFactory.Options opts = new BitmapFactory.Options();
				opts.inSampleSize = originalSize / 100;
				myphoto.setImageBitmap(BitmapFactory.decodeFile(item.getImage(), opts));
			}
			
//			File file = new File(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString(), item.getImage());
//			Uri uri = Uri.fromFile(file);
//			myphoto.setImageURI(uri);
		}else
		{
			myphoto.setImageResource(R.drawable.pin1);
		}

		TextView content = (TextView) view.findViewById(R.id.content);
		content.setText(item.getContent());

		TextView pos = (TextView) view.findViewById(R.id.location);
		StringBuilder loc = new StringBuilder("(");
		loc.append(item.getX()).append(",").append(item.getY()).append(")");
		pos.setText(loc.toString());
		
		return view;
	}

	/**
	 * File file = new File(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString(), fname);
			if(file.exists())
			{
				file.delete();
				Log.d(tag, "delete the file");
			}
	 */
}
