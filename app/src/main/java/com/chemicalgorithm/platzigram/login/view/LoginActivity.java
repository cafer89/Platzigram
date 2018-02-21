package com.chemicalgorithm.platzigram.login.view;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.login.presenter.LoginPresenter;
import com.chemicalgorithm.platzigram.login.presenter.LoginPresenterImpl;
import com.chemicalgorithm.platzigram.view.ContainerActivity;
import com.chemicalgorithm.platzigram.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity implements LoginView
{

	private TextInputEditText username, password;
	private Button login;
	private ProgressBar progressBarLogin;
	private LoginPresenter loginPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		username = (TextInputEditText) findViewById(R.id.username);
		password = (TextInputEditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		progressBarLogin = (ProgressBar) findViewById(R.id.progressbarLogin);
		loginPresenter = new LoginPresenterImpl(this);
		hideProgressBar();

		login.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				loginPresenter.signIn(username.getText().toString(), password.getText().toString());
			}
		});


	}

	@Override
	public void goCreateAccount()
	{
		Intent intent = new Intent(LoginActivity.this,
				CreateAccountActivity.class);
		startActivity(intent);

	}

	@Override
	public void goHome()
	{
		Intent i = new Intent(LoginActivity.this, ContainerActivity.class);
		startActivity(i);
	}

	@Override
	public void showProgressBar()
	{
		progressBarLogin.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgressBar()
	{
		progressBarLogin.setVisibility(View.GONE);
	}

	@Override
	public void loginError(String error)
	{
		Toast.makeText(this, "ocurrió un error" + error, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void enableInputs()
	{
		username.setEnabled(true);
		password.setEnabled(true);
		login.setEnabled(true);
	}

	@Override
	public void disableInputs()
	{
		username.setEnabled(false);
		password.setEnabled(false);
		login.setEnabled(false);
	}
}
