package net.julnamoo.swm.herimarque.adapter;

import net.julnamoo.R;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class KindImageAdapter extends BaseAdapter {

	private Context mContext;

	int[] kindImgs = {
			R.drawable.kind_national_treasure, R.drawable.kind_treasure, R.drawable.kind_historical_site,
			R.drawable.kind_spot_place, R.drawable.kind_natural_monument, R.drawable.kind_intangible_cultural_asset,
			R.drawable.kind_folklore_asset, R.drawable.kind_registered_asset, R.drawable.kind_local_tangible_cultural_asset,
			R.drawable.kind_local_intangible_cultural_asset, R.drawable.kind_local_monument, R.drawable.kind_local_folklore_asset,
			R.drawable.kind_cultural_asset
	}; 

	String[] kindImgsCD = {
			"국보", "보물", "사적", "명승", "천연기념물", "무형문화재", "민속문화재", "등록문화재",
			"시도 유형문화재", "시도 무형문화재", "시도 기념물", "시도 민속문화재", "문화재 자료"
	};

	public KindImageAdapter(Context c)
	{
		mContext = c;
	}

	@Override
	public int getCount() {
		return 13;
	}

	@Override
	public Object getItem(int arg0) {
		return kindImgs[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) 
	{
		LinearLayout view = new LinearLayout(mContext);;
		ImageView image= new ImageView(mContext);;
		TextView tv = new TextView(mContext);
		
		view.setOrientation(LinearLayout.VERTICAL);
		GridView.LayoutParams params = new GridView.LayoutParams(100, 150);
		view.setLayoutParams(params);

		image.setLayoutParams(new LinearLayout.LayoutParams(100,100));
		image.setScaleType(ImageView.ScaleType.FIT_XY);
		image.setPadding(8, 8, 8, 8);
		image.setContentDescription(kindImgsCD[arg0]);
		image.setId(arg0);
		image.setImageResource(kindImgs[arg0]);
		
		tv.setText(kindImgsCD[arg0]);
		tv.setGravity(Gravity.CENTER);

		view.addView(image);
		view.addView(tv);
		return view;
	}

}
