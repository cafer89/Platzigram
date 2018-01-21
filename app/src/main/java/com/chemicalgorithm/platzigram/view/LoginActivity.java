package com.chemicalgorithm.platzigram.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chemicalgorithm.platzigram.R;

public class LoginActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	public void goCreateAccount(View view)
	{
		//Si el intent comunica clases en diferentes folder o paquetes, usar el nombre completo de la activity.
		Intent intent = new Intent(LoginActivity.this,
				com.chemicalgorithm.platzigram.view.CreateAccountActivity.class);
		startActivity(intent);
	}
}
