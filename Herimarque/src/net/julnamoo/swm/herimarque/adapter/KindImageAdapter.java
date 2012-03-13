package net.julnamoo.swm.herimarque.adapter;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.model.Kind;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KindImageAdapter extends BaseAdapter {

	final private String tag = KindImageAdapter.class.getSimpleName();

	private Context mContext;

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
		
		Kind kind = new Kind();
		kind.setCode(Integer.valueOf(kindCode[arg0]));
		kind.setImage(kindImgs[arg0]);
		kind.setName(kindImgsCD[arg0]);
		return kind;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) 
	{
		LinearLayout view = new LinearLayout(mContext);
		ImageView image= new ImageView(mContext);
		TextView tv = new TextView(mContext);

		view.setOrientation(LinearLayout.VERTICAL);
		GridView.LayoutParams params = new GridView.LayoutParams(200, 240);
		view.setLayoutParams(params);

		image.setLayoutParams(new LinearLayout.LayoutParams(200,200));
		image.setScaleType(ImageView.ScaleType.FIT_XY);
		image.setPadding(5, 5, 5, 5);
		image.setContentDescription(kindImgsCD[arg0]);
		image.setId(arg0);
		image.setImageResource(kindImgs[arg0]);

		tv.setText(kindImgsCD[arg0]);
		tv.setGravity(Gravity.CENTER);

		view.addView(image);
		view.addView(tv);
		return view;
	}

	public String[] kindCode = {
		"11", "12", "13", "15", "16", "17", "18", "79",
		"21", "22", "23", "24", "31"
	};
	
	public String[] kindImgsCD = {
			"국보", "보물", "사적", "명승", "천연기념물", "중요 무형문화재", "중요 민속자료", "등록문화재",
			"시도 유형문화재", "시도 무형문화재", "시도 기념물", "시도 민속자료", "문화재 자료"
	};

	public int[] kindImgs = {
			R.drawable.kind_national_treasure, R.drawable.kind_treasure, R.drawable.kind_historical_site,
			R.drawable.kind_spot_place, R.drawable.kind_natural_monument, R.drawable.kind_intangible_cultural_asset,
			R.drawable.kind_folklore_asset, R.drawable.kind_registered_asset, R.drawable.kind_local_tangible_cultural_asset,
			R.drawable.kind_local_intangible_cultural_asset, R.drawable.kind_local_monument, R.drawable.kind_local_folklore_asset,
			R.drawable.kind_cultural_asset
	}; 
}
