package com.chemicalgorithm.platzigram;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Usuario on 26/02/2018.
 */

public class PlatzigramApplication extends Application
{
	private FirebaseAuth firebaseAuth;
	private FirebaseAuth.AuthStateListener authStateListener;
	private final String TAG = "PlatzigramApplication";


	@Override
	public void onCreate()
	{
		super.onCreate();

		FacebookSdk.sdkInitialize(getApplicationContext());

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
}
