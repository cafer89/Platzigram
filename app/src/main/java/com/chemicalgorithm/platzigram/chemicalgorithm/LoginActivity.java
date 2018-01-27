package com.chemicalgorithm.platzigram.chemicalgorithm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.chemicalgorithm.view.ContainerActivity;
import com.chemicalgorithm.platzigram.chemicalgorithm.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity
{

	Button btn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		btn_login = (Button) findViewById(R.id.login);
		btn_login.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(LoginActivity.this, ContainerActivity.class);
				startActivity(i);
			}
		});
	}

	public void goCreateAccount(View view)
	{
		//Si el intent comunica clases en diferentes folder o paquetes, usar el nombre completo de la activity.
		Intent intent = new Intent(LoginActivity.this,
				CreateAccountActivity.class);
		startActivity(intent);
	}
}
