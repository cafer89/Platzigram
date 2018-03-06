package com.chemicalgorithm.platzigram.post.view;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.chemicalgorithm.platzigram.PlatzigramApplication;
import com.chemicalgorithm.platzigram.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity
{

	private static final String PHOTONAME = "JPEG_20180306_10-25-20_1101544045.jpg" ;
	private ImageView imageHeader;
	private PlatzigramApplication app;
	private StorageReference storageReference;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_detail);
		showToolbar(getResources().getString(R.string.hint_user),true);

		app = (PlatzigramApplication) getApplicationContext();
		storageReference = app.getStorageReference();
		imageHeader = (ImageView) findViewById(R.id.imageHeader);

		showData();

	}

	private void showData()
	{
		storageReference.child("postimages/" + PHOTONAME)
				.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
		{
			@Override
			public void onSuccess(Uri uri)
			{
				Picasso.with(PictureDetailActivity.this).load(uri).into(imageHeader);
			}
		}).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(@NonNull Exception e)
			{
				Toast.makeText(app, "Ocurrió un error al traer la foto", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		});

	}

	public void showToolbar(String title , boolean upButton)
	{
		Toolbar toolbar = (Toolbar) findViewById(R.id.detailToolbar);
		//soporte para versiones anteriores a Lollipop
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(title);
		//en caso de que tenga botón de regreso o subir
		getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
		CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
	}
}

