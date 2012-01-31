package net.julnamoo.swm.herimarque.adapter;

import java.util.ArrayList;
import java.util.List;

import net.julnamoo.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

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
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ImageView image;
		if(arg1 == null)
		{
			image = new ImageView(mContext);
			image.setAdjustViewBounds(false);
			image.setScaleType(ImageView.ScaleType.CENTER_CROP);
			image.setPadding(10, 10, 10, 10);
			image.setContentDescription(kindImgsCD[arg0]);
		}else
		{
			image = (ImageView) arg1;
		}

		image.setImageResource(kindImgs[arg0]);
		return image;
	}

}
