package com.chemicalgorithm.platzigram.chemicalgorithm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.chemicalgorithm.platzigram.R;

public class CreateAccountActivity extends AppCompatActivity
{
	@Override
	//Bundle es el estado de la actividad anterior.
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		//implementamos título de toolbar desde values/string
		showToolbar(getResources().getString(R.string.toolbar_title_create_account),true);


	}

	public void showToolbar(String title , boolean upButton)
	{
		Toolbar toolbar = (Toolbar) findViewById(R.id.toobar);
		//soporte para versiones anteriores a Lollipop
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(title);
		//en caso de que tenga botón de regreso o subir
		getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
	}
}
