package net.julnamoo.swm.herimarque.create;

import java.io.File;

import net.julnamoo.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddPhotoAndCommentActivity extends Activity {

	private String tag = AddPhotoAndCommentActivity.class.getSimpleName();

	int REQUEST_CODE = 1;
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

		//check the gallery or camera
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int width = display.getWidth() * 4 / 5;
		fname = getIntent().getStringExtra("fname");
		if(fname == null)
		{
			Log.d(tag, "from camera");
			fname = getLastImageId();
			Bitmap bmImg = (Bitmap) getIntent().getExtras().get("data");
			int height = width * bmImg.getHeight() / bmImg.getWidth();
			bmImg = Bitmap.createScaledBitmap(bmImg, width, height, true);
			imageView.setImageBitmap(bmImg);
		}else
		{
			Log.d(tag, "from gallery");
			Log.d(tag, "fname : " + fname);
			Bitmap bmImg = BitmapFactory.decodeFile(fname);
			int height = width * bmImg.getHeight() / bmImg.getWidth();
			bmImg = Bitmap.createScaledBitmap(bmImg, width, height, true);
			imageView.setImageBitmap(bmImg);
		}

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
		Toast.makeText(AddPhotoAndCommentActivity.this, "Heritage Mark를 생성합니다", Toast.LENGTH_SHORT).show();
		okClickListner.onClick(ok);
	}

	private void hideKeyboard()
	{
		InputMethodManager imm = (InputMethodManager) AddPhotoAndCommentActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(comment.getApplicationWindowToken(), 0);
	}
}
