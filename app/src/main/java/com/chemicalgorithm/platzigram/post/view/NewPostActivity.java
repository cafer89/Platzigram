package com.chemicalgorithm.platzigram.post.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chemicalgorithm.platzigram.PlatzigramApplication;
import com.chemicalgorithm.platzigram.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class NewPostActivity extends AppCompatActivity
{

	private static final String TAG = "NewPostActivity";
	private String photoPath;
	private Button btnCreatePost;
	private ImageView imgPhoto;
	private PlatzigramApplication app;
	private StorageReference storageReference;



	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		app = (PlatzigramApplication) getApplicationContext();
		storageReference = app.getStorageReference();



		btnCreatePost = (Button) findViewById(R.id.btnCreatePost);
		imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
		if(getIntent().getExtras() != null)
		{
			photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");
			showPhoto();
		}

		btnCreatePost.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				uploadPhoto();
			}
		});

	}

	private void uploadPhoto()
	{
		imgPhoto.setDrawingCacheEnabled(true);
		imgPhoto.buildDrawingCache();

		// tomar el cache de la foto que subimos y la pasamos a bitmap
		Bitmap bitmap = imgPhoto.getDrawingCache();
		//el output stream se usará para la escritura del archivo en firebase
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		//comprimimos la foto
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

		//
		byte[] photoByte = baos.toByteArray();
		String photoName = photoPath.substring(photoPath.lastIndexOf("/")+1, photoPath.length());

		StorageReference photoReference = storageReference.child("postimages/" + photoName);

		UploadTask uploadTask = photoReference.putBytes(photoByte);
		uploadTask.addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(@NonNull Exception e)
			{
				Log.e(TAG, "Error al subir la foto" + e.toString());
				e.printStackTrace();
				FirebaseCrash.report(e);
			}
		}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
		{
			@Override
			public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
			{
				Uri uriPhoto = taskSnapshot.getDownloadUrl();
				String photoURL = uriPhoto.toString();
				Log.w(TAG, "URL Photo > " + photoURL);
				finish();
			}
		});

	}

	private void showPhoto()
	{
		Picasso.with(this).load(photoPath).into(imgPhoto);
	}
}
