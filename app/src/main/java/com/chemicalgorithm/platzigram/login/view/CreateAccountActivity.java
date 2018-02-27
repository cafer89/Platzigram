package com.chemicalgorithm.platzigram.login.view;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chemicalgorithm.platzigram.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity
{

	private static final String TAG = "CreateAccountActivity";
	private FirebaseAuth firebaseAuth;
	private FirebaseAuth.AuthStateListener authStateListener;
	private Button btnJoinUs;
	private TextInputEditText edtEmail, edtPassword;

	@Override
	//Bundle es el estado de la actividad anterior.
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		//implementamos título de toolbar desde values/string
		showToolbar(getResources().getString(R.string.toolbar_title_create_account),true);

		btnJoinUs = (Button) findViewById(R.id.join_us);
		edtEmail = (TextInputEditText) findViewById(R.id.email);
		edtPassword = (TextInputEditText) findViewById(R.id.password);

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
				}
				else
				{
					Log.w(TAG, "Usueario no logeado");
				}
			}
		};

		btnJoinUs.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				createAccount();
			}
		});
	}

	private void createAccount()
	{
		String email = edtEmail.getText().toString();
		String password = edtPassword.getText().toString();

		firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
		{
			@Override
			public void onComplete(@NonNull Task<AuthResult> task)
			{
				if(task.isSuccessful())
				{
					Toast.makeText(CreateAccountActivity.this, "Cuenta creada completamente", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(CreateAccountActivity.this, "Ocurrió un error al crear la cuenta", Toast.LENGTH_SHORT).show();
				}
			}
		}).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(@NonNull Exception e)
			{
				String error = e.getLocalizedMessage();
				Toast.makeText(CreateAccountActivity.this, error, Toast.LENGTH_SHORT).show();
			}
		});
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

	@Override
	protected void onStart()
	{
		super.onStart();
		firebaseAuth.addAuthStateListener(authStateListener);
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		firebaseAuth.removeAuthStateListener(authStateListener);
	}
}

