package com.chemicalgorithm.platzigram.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.chemicalgorithm.platzigram.R;

public class CreateAccountActivity extends AppCompatActivity
{

	@Override
	//Bundle es el estado de la actividad anterior.
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
	}
}
