package net.julnamoo.swm.herimarque.create;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.julnamoo.swm.herimarque.MapContainer;
import net.julnamoo.swm.herimarque.adapter.HeritageListAdapter;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import net.julnamoo.swm.herimarque.db.TrackingDataSource;
import net.julnamoo.swm.herimarque.util.Constants;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;

/** 1. select the location type (heritage/currposition) **/
public class CheckInFragment extends DialogFragment{

	private String tag = CheckInFragment.class.getSimpleName();

	private String PREF_NAME = "checkin";
	private Context mContext;
	private String tableName;
	private Location lastLocation;

	public static final int PICK_FROM_CAMERA = 0;
	public static final int PICK_FROM_ALBUM = 1;
	
	public static final int CHECKIN_PHOTO = 12;
	
	private String[] types = {"TR", "CO", "PT", "CI"};
	
	public CheckInFragment(Context mContext, Location lastLocation, String tableName)
	{
		this.mContext = mContext;
		this.lastLocation = lastLocation;
		this.tableName = tableName;
	}

	@Override
	public void onCreate(Bundle arg0) 
	{
		super.onCreate(arg0);
	}

	@Override
	public Dialog onCreateDialog(Bundle arg0) 
	{
		return new AlertDialog.Builder(mContext)
		.setTitle("발자국 남기기")
		.setPositiveButton("근처 문화유산에 남기기", setLocationTypeClickListener)
		.setNegativeButton("현재 위치에 남기기", setLocationTypeClickListener)
		.create();

	}

	DialogInterface.OnClickListener setLocationTypeClickListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) 
		{
			SharedPreferences.Editor editor = mContext.getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS).edit();

			//put meta information to the preference
			if(which == AlertDialog.BUTTON_POSITIVE)
			{
				editor.putInt("type", 0);
				Log.d(tag, "checkin-type:near(0)");
				editor.commit();

				//get near heritage
				HeritageSQLiteHelper sqlHelper = new HeritageSQLiteHelper(mContext);
				SQLiteDatabase db = sqlHelper.getReadableDatabase();

				//				double x_rad = DegreeToRadius.degToRad(lastLocation.getLongitude());
				//				String sin_x_rad = String.valueOf(Math.sin(x_rad));
				//				String cos_x_rad = String.valueOf(Math.cos(x_rad));
				//
				//				double y_rad = DegreeToRadius.degToRad(Double.valueOf(lastLocation.getLatitude()));
				//				String sin_y_rad = String.valueOf(Math.sin(y_rad));
				//				String cos_y_rad = String.valueOf(Math.cos(y_rad));
				//
				//				//(sin_lat_rad * "sin_lat_rad" + cos_lat_rad * "cos_lat_rad" * (sin_lon_rad * "sin_lon_rad" + cos_lon_rad * "cos_lon_rad")) AS "distance_acos"
				//				//build query
				//				StringBuilder queryBuilder = new StringBuilder("SELECT *, ( sin_y_rad * ").append(sin_y_rad);
				//				queryBuilder.append(" + cos_y_rad * ").append(cos_y_rad).append(" * (sin_x_rad *").append(sin_x_rad).append(" + cos_x_rad * ").append(cos_x_rad);
				//				queryBuilder.append(")) AS distance_acos");
				//				queryBuilder.append(" FROM ").append(Constants.HERITAGE_TABLE_NAME);
				//				queryBuilder.append(" ORDER BY distance_acos DESC LIMIT 4;");
				//				String query = queryBuilder.toString();
				//				Log.d(tag, "exec sql : " + query);
				//				//get data
				//				Cursor cursor = db.rawQuery(query, null);
				//				getActivity().startManagingCursor(cursor);
				//				sqlHelper.close();

				GeoPoint topleft = MapContainer.mapView.getProjection().fromPixels(0, 0);
				GeoPoint bottomright = MapContainer.mapView.getProjection().fromPixels(MapContainer.mapView.getWidth(), MapContainer.mapView.getHeight());

				double topRightLat = topleft.getLatitudeE6()/1E6;
				double bottomLeftLat = bottomright.getLatitudeE6()/1E6;
				double topRightLong = topleft.getLongitudeE6()/1E6;
				double bottomLeftLong = bottomright.getLongitudeE6()/1E6;

				//build query
				StringBuilder query = new StringBuilder("SELECT * FROM ");
				query.append(Constants.HERITAGE_TABLE_NAME);
				query.append(" WHERE XCnts > ").append(topRightLong).append(" AND XCnts < ").append(bottomLeftLong);
				query.append(" AND YCnts > ").append(bottomLeftLat).append(" AND YCnts < ").append(topRightLat);
				query.append(" LIMIT 4;");
				//get data
				Cursor cursor = db.rawQuery(query.toString(), null);
				Log.d(tag, query.toString() + "query, total size : " + cursor.getCount());
				db.close();
				sqlHelper.close();

				//show the dialog for choosing the heritage. Action type is "CI"
				new NearHeritageDialogFragment(cursor).show(getFragmentManager(), null);

			}else if(which == AlertDialog.BUTTON_NEGATIVE)
			{
				editor.putInt("type", 1);
				Log.d(tag, "checkin-type:current(1)");
				editor.putString("x", String.valueOf(lastLocation.getLatitude()));
				Log.d(tag, "put x in preference : " + lastLocation.getLatitude());
				editor.putString("y", String.valueOf(lastLocation.getLongitude()));
				Log.d(tag, "put y in preference : " + lastLocation.getLongitude());
				editor.commit();

				//call action fragment. Action type is "TR"/"CO"/PT"
				new CheckInActionFragment().show(getFragmentManager(), null);
			}
		}
	};

	/** 1-2. select a heritage from near heritages **/
	class NearHeritageDialogFragment extends DialogFragment
	{
		Cursor cursor;
		int selected;

		public NearHeritageDialogFragment(Cursor cursor) 
		{
			this.cursor = cursor;
		}

		@Override
		public Dialog onCreateDialog(Bundle arg0) 
		{
			ListView list = new ListView(mContext);
			list.setAdapter(new HeritageListAdapter(mContext, cursor));
			list.setOnItemClickListener(heritageClickListener);

			return new AlertDialog.Builder(mContext)
			.setView(list)
			.setTitle("가까운 문화유산 보기")
			.setPositiveButton("다음", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) 
				{
					SharedPreferences.Editor editor = mContext.getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS).edit();
					cursor.moveToPosition(selected);
					String name = cursor.getString(cursor.getColumnIndex("crltsNm"));
					Log.d(tag, "save the heritage to the preference :" + name);

					//set location
					String x = cursor.getString(cursor.getColumnIndex("XCnts"));
					String y = cursor.getString(cursor.getColumnIndex("YCnts"));
					editor.putString("x", x);
					Log.d(tag, "put x in preference : " + x);
					editor.putString("y", y);
					Log.d(tag, "put y in preference : " + y);

					//checkin with heritage is always "CI" type
					editor.putString("action", "CI");
					editor.commit();
					new CheckInActionFragment().show(getFragmentManager(), null);
				}
			})
			.setNegativeButton("취소", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					new CancleDialogFragment(mContext).show(getFragmentManager(), null);
				}
			}) 
			.setOnCancelListener(cancleListenr)
			.create();
		}

		OnItemClickListener heritageClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				selected = arg2;
				arg1.setPressed(true);
				Log.d(tag, "selected heritage index : " + selected);
			}
		};
	}

	/** 2. select action among taking photo, using existing photo, and only comment **/
	class CheckInActionFragment extends DialogFragment
	{
		private String[] actions = { "발자국만 남기기", "사진 찍기", "갤러리에서 선택하기", "코멘트 남기기" };
		private String[] action_type = { "TR", "PT", "PT", "CO" };

		private boolean fromHertiage;


		@Override
		public void onCreate(Bundle arg0) 
		{
			super.onCreate(arg0);
//			this.fromHertiage = arg0.getBoolean("fromheritage");
		}
		
		@Override
		public Dialog onCreateDialog(Bundle arg0) 
		{
			return new AlertDialog.Builder(mContext)
			.setItems(actions, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					Log.d(tag, "selected action:" + actions[which]);

					SharedPreferences sp = mContext.getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
					SharedPreferences.Editor editor = sp.edit();
					if(!sp.contains("action"))
					{
						editor.putString("action", action_type[which]);
					}
					editor.putString("action", action_type[which]);
					editor.commit();

					//next action following the type
					switch (which) {
					case 0:
						//Complete. dismiss the dialog
						Log.d(tag, "tracking type, finish marking");
						finishMarking();
						dialog.dismiss();
						break;
					case 1:
						//call the camera activity for taking picture
						Log.d(tag, "taking picture");
						//TODO
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(intent, PICK_FROM_CAMERA);
						Toast.makeText(mContext, "camera", Toast.LENGTH_SHORT).show();
						break;
					case 2:
						//call the gallery activity for selecting a picture
						Log.d(tag, "using gallery");
 						intent = new Intent(mContext, GalleryActivity.class);
						startActivityForResult(intent, PICK_FROM_ALBUM);
						Toast.makeText(mContext, "gallery", Toast.LENGTH_SHORT).show();
						break;
					case 3:
						//call the comment fragment for writing comment for this marking
						Log.d(tag, "writing comment");
						Toast.makeText(mContext, "comment", Toast.LENGTH_SHORT).show();
						break;
					default:
						break;
					}
				}
			})
			.create();
		}
	}

	/**
	 * get meta info from sharedpreference then convert it to timeline view and json and sending
	 */
	private void finishMarking()
	{
		Toast.makeText(mContext, "Marking 되었습니다!", Toast.LENGTH_SHORT).show();

		SharedPreferences sp = mContext.getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
		SharedPreferences.Editor editor = sp.edit();
		String type = sp.getString("action", "TR");
		editor.remove("action");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date());
		String x = sp.getString("x", "37.57273");
		editor.remove("x");
		String y = sp.getString("y", "126.9687");
		editor.remove("y");
		String content = sp.getString("content", "");
		editor.remove("content");
		String image = sp.getString("image", "");
		editor.remove("image");

		TrackingDataSource tds = new TrackingDataSource(mContext, tableName);
		tds.addCheckIn(type, x, y, time, content, image);
		Log.d(tag, "add tracking info:" + time + ","+type+","+"x:"+x+",y:"+y+","+content+","+image);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		//save the data to sharedpreference
		SharedPreferences sp = mContext.getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("x", String.valueOf(lastLocation.getLatitude()));
		editor.putString("y", String.valueOf(lastLocation.getLongitude()));
		
		String content = data.getStringExtra("content");
		String image = data.getStringExtra("image");
		Log.d(tag, "called with " + content + ", " + image);
		editor.putString("content", content);
		editor.putString("image", image);
		
		finishMarking();
	}

	/** cancle dialog for dismiss all information **/

	DialogInterface.OnCancelListener cancleListenr = new DialogInterface.OnCancelListener() {

		@Override
		public void onCancel(DialogInterface arg0) 
		{
			new CancleDialogFragment(mContext).show(getFragmentManager(), null);
		}
	};
	//		String boundary =  "*****";
	//		String twoHyphens = "--";
	//		String sendingBoundary = "--*****";
	//		String lineEnd = "\r\n";
	//		try 
	//		{
	//			URL url = new URL("http://localhost:8080/herimarque/api/c/upload/map/testId");
	//			HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
	//
	//			httpURLCon.setDefaultUseCaches(false);
	//			httpURLCon.setDoInput(true);
	//			httpURLCon.setDoOutput(true);
	//			httpURLCon.setRequestMethod("POST");
	//			httpURLCon.setRequestProperty("content-type", "multipart/form-data; boundary="+boundary);
	//
	//			/**
	//			 * ------WebKitFormBoundarysAPbx0Jb5UuEDXNf
	//			Content-Disposition: form-data; name="id"
	//
	//			testId
	//
	//
	//
	//			Content-Disposition: form-data; name="map"; filename=""
	//			Content-Type: application/octet-stream
	//
	//			 */
	//			DataOutputStream dos = new DataOutputStream(httpURLCon.getOutputStream());
	//			
	//			//set the first param
	//			dos.writeBytes(sendingBoundary);
	//			dos.writeBytes("Content-Disposition: form-data; name='id'");
	//			dos.writeBytes(lineEnd);
	//			dos.writeBytes("testId");
	//			dos.writeBytes(lineEnd);
	//			
	//			//set the second param
	//			dos.writeBytes(sendingBoundary);
	//			dos.writeBytes("Content-Disposition: form-data; name='map'; filename=''");
	//			dos.writeBytes("Content-Type: application/octet-stream");
	//			dos.writeBytes(lineEnd);
	////			dos.writeBytes("testId"); //write bytes
	//			dos.writeBytes(lineEnd);
	//			
	//			dos.writeBytes(sendingBoundary);
	//			dos.writeBytes("Content-Disposition: form-data; name='file1'; filename=''");
	//			dos.writeBytes("Content-Type: application/octet-stream");
	//			dos.writeBytes(lineEnd);
	////			dos.writeBytes("testId"); //write bytes
	//			dos.writeBytes(lineEnd);
	//			
	//		} catch (MalformedURLException e) 
	//		{
	//			e.printStackTrace();
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
}
