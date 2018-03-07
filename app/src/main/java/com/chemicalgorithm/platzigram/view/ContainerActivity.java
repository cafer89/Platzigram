package com.chemicalgorithm.platzigram.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.login.view.LoginActivity;
import com.chemicalgorithm.platzigram.post.view.HomeFragment;
import com.chemicalgorithm.platzigram.fragment.ProfileFragment;
import com.chemicalgorithm.platzigram.fragment.SearchFragment;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;

public class ContainerActivity extends AppCompatActivity
{
	private FirebaseAuth firebaseAuth;
	private FirebaseAuth.AuthStateListener authStateListener;

	private final String TAG = "ContainerActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		FirebaseCrash.log("Inicializando" + TAG);
		firebaseInitialize();

		BottomNavigationView bottomBar = (BottomNavigationView) findViewById(R.id.bottomBar);
		selectFragment(new HomeFragment());

		bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
		{
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item)
			{
				switch (item.getItemId())
				{
					case R.id.search:
						selectFragment(new SearchFragment());
						break;

					case R.id.home:
						selectFragment(new HomeFragment());
						break;

					case R.id.profile:
						selectFragment(new ProfileFragment());
						break;
				}
				return true;
			}
		});
	}

	private void selectFragment(Fragment fragment)
	{
		getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment)
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.addToBackStack(null)
				.commit();
	}

	private void firebaseInitialize()
	{
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_opciones, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.mSignOut:
				firebaseAuth.signOut();
				if(LoginManager.getInstance() != null)
				{
					LoginManager.getInstance().logOut();
				}
				Toast.makeText(this, "Se cerró la sesión", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(ContainerActivity.this, LoginActivity.class);
				startActivity(i);
				break;

			case R.id.mAbout:
				Toast.makeText(this, "App hecha por Carlos F.", Toast.LENGTH_SHORT).show();
				break;
		}

		return true;
	}
}
