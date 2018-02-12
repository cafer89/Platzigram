package com.chemicalgorithm.platzigram.view;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.chemicalgorithm.platzigram.R;

public class PictureDetailActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_detail);
		showToolbar(getResources().getString(R.string.hint_user),true);

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

