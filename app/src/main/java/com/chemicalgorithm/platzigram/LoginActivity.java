package com.chemicalgorithm.platzigram;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chemicalgorithm.platzigram.view.ContainerActivity;
import com.chemicalgorithm.platzigram.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity
{

	Button btn_login;
	ImageView img_logo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		img_logo = (ImageView) findViewById(R.id.logo);
		img_logo.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				webSearch("https://github.com/cafer89/Platzigram");
			}
		});


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

	public void webSearch(String link)
	{
		Uri  webPage = Uri.parse(link);
		Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);

		if(webIntent.resolveActivity(getPackageManager()) !=null)
		{
			startActivity(webIntent);
		}
	}
}
