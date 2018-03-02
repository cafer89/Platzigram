package com.chemicalgorithm.platzigram.login.repository;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Usuario on 20/02/2018.
 */

public interface LoginRepository
{
	void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
}
