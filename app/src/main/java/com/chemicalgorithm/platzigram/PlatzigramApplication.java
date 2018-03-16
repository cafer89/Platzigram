package com.chemicalgorithm.platzigram;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Usuario on 26/02/2018.
 */

public class PlatzigramApplication extends Application
{
	private FirebaseStorage firebaseStorage;
	private FirebaseAuth firebaseAuth;
	private FirebaseAuth.AuthStateListener authStateListener;
	private final String TAG = "PlatzigramApplication";

	//comentario de prueba en android studio 3.0.1


	@Override
	public void onCreate()
	{
		super.onCreate();
		FirebaseCrash.log("Inicializando variables en platzigram");

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
					FirebaseCrash.logcat(Log.WARN,TAG,"usuario logueado");
				}
				else
				{
					FirebaseCrash.logcat(Log.WARN,TAG,"usuario NO logueado");
				}
			}
		};

		firebaseStorage = FirebaseStorage.getInstance();
	}

	public StorageReference getStorageReference()
	{
		return firebaseStorage.getReference();
	}
}
