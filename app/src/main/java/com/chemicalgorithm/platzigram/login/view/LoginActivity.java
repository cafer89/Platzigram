package com.chemicalgorithm.platzigram.login.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.login.presenter.LoginPresenter;
import com.chemicalgorithm.platzigram.login.presenter.LoginPresenterImpl;
import com.chemicalgorithm.platzigram.view.ContainerActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginView
{

	private static final String TAG = "LoginRepository";
	private FirebaseAuth firebaseAuth;
	private FirebaseAuth.AuthStateListener authStateListener;

	private TextInputEditText username, password;
	private Button login;
	private LoginButton loginFacebook;
	private ProgressBar progressBarLogin;
	private LoginPresenter loginPresenter;
	private CallbackManager callbackManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		FacebookSdk.sdkInitialize(getApplicationContext());
		callbackManager = CallbackManager.Factory.create();

		loginFacebook = (LoginButton) findViewById(R.id.login_facebook);
		username = (TextInputEditText) findViewById(R.id.username);
		password = (TextInputEditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		progressBarLogin = (ProgressBar) findViewById(R.id.progressbarLogin);
		loginPresenter = new LoginPresenterImpl(this);
		hideProgressBar();

		//el metodo apunta al objeto JSON de mi proyecto el cual apunta a mi consola y recibe toda
		//la configuración que yo tenga.
		firebaseAuth = FirebaseAuth.getInstance();
		authStateListener = new FirebaseAuth.AuthStateListener()
		{
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
			{
				FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
				if(firebaseUser != null)
				{
					//User logged
					Log.w(TAG, "Usuario loggeado" + firebaseUser.getEmail());
					goHome();
				}
				else
				{
					Log.w(TAG, "Usueario no logeado");
				}
			}
		};

		login.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				signIn(username.getText().toString(), password.getText().toString());
			}
		});

		loginFacebook.setReadPermissions(Arrays.asList("email"));

		loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
		{
			@Override
			public void onSuccess(LoginResult loginResult)
			{
				Log.w(TAG, "Facebook Login Succes Token: " + loginResult.getAccessToken().getToken());
				signInFacebookFirebase(loginResult.getAccessToken());

			}

			@Override
			public void onCancel()
			{
				Log.w(TAG, "Facebook Login Cancel");
			}

			@Override
			public void onError(FacebookException error)
			{
				Log.w(TAG, "Facebook Login ERROR: " + error.toString());
				error.printStackTrace();
			}
		});
	}

	private void signInFacebookFirebase(AccessToken accessToken)
	{
		AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
		firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
		{
			@Override
			public void onComplete(@NonNull Task<AuthResult> task)
			{
				if(task.isSuccessful())
				{
					showProgressBar();
					Toast.makeText(LoginActivity.this, "Login facebook sxitoso", Toast.LENGTH_SHORT).show();
					goHome();
				}
				else
				{
					Toast.makeText(LoginActivity.this, "Login NO exitoso", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	public void signIn(String username, String password)
	{
		loginPresenter.signIn(username, password, this, firebaseAuth);
	}

	public void goCreateAccount(View v)
	{
		goCreateAccount();
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

	@Override
	protected void onStop()
	{
		super.onStop();
		firebaseAuth.removeAuthStateListener(authStateListener);
		enableInputs();
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		firebaseAuth.addAuthStateListener(authStateListener);
	}


	//éste metodo captura el callbackManager de facebook inicializado en el metodo registerCallback y trae el estado de sesion.
	//éste estado se le pasa al loginManager
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		callbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

}
