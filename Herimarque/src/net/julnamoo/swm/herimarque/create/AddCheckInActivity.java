package net.julnamoo.swm.herimarque.create;

import java.io.File;

import net.julnamoo.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddCheckInActivity extends Activity {
	
	private String tag = AddCheckInActivity.class.getSimpleName();
	
	int REQUEST_CODE = 1;
	private Bitmap bitmap;
	private ImageView imageView;
	private EditText comment;
	private Button ok;

	private String fname;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcomment);
		imageView = (ImageView) findViewById(R.id.myphoto);
		comment = (EditText) findViewById(R.id.myphotocomment);
		ok = (Button) findViewById(R.id.create_comment_ok);
		ok.setOnClickListener(okClickListner);
		
		Bitmap bmImg = (Bitmap) getIntent().getExtras().get("data");
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int width = display.getWidth() * 4 / 5;
		int height = width * bmImg.getHeight() / bmImg.getWidth();
		bmImg = Bitmap.createScaledBitmap(bmImg, width, height, true);
		imageView.setImageBitmap(bmImg);
		
		//store the file to the external storage
		//get the tablename for directory
		String tableName = getIntent().getStringExtra("tablename");
		File dir = Environment.getExternalStorageDirectory();
		File file = new File(Environment.getExternalStorageDirectory(), "1");
		String path = dir.getAbsolutePath() + File.separatorChar + tableName;
		Log.d(tag, "tablename is " + tableName + ", dir path is " + path);
		fname = getLastImageId();
		Log.d(tag, "fname : " + fname);
	}
	
	OnClickListener okClickListner = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String content = comment.getText().toString();
			Log.d(tag, "ok clicked, fname is " + fname + ", content:" + content);
			Intent intent = new Intent();
			intent.putExtra("photo", fname);
			intent.putExtra("content", content);
			setResult(Activity.RESULT_OK, intent);
			finish();
		}
	};
	
	private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
	
	private String getLastImageId(){
	    final String[] imageColumns = { MediaStore.Images.Media._ID, MediaStore.Images.Media.TITLE };
	    final String imageOrderBy = MediaStore.Images.Media._ID+" DESC";
//	    CursorLoader cl = new CursorLoader(AddCheckInActivity.this, MediaStore.Images.Media.INTERNAL_CONTENT_URI, imageColumns, null, null, imageOrderBy);
//	    Cursor imageCursor = cl.loadInBackground();
	    Cursor imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageColumns, null, null, imageOrderBy);
	    
	    if(imageCursor.moveToFirst()){
	        int id = imageCursor.getInt(imageCursor.getColumnIndex(MediaStore.Images.Media._ID));
	        String fullPath = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.TITLE));
	        Log.d(tag, "getLastImageId::id " + id);
	        Log.d(tag, "getLastImageId::title " + fullPath);
	        imageCursor.close();
	        return fullPath;
	    }else{
	    	Log.d(tag, "empty");
	        return null;
	    }
	}
	
	public void onBackPressed() 
	{
		Toast.makeText(AddCheckInActivity.this, "Heritage Mark를 생성합니다", Toast.LENGTH_SHORT).show();
		okClickListner.onClick(ok);
	}
}
