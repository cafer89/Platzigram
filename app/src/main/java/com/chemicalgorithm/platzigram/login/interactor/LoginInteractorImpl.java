package com.chemicalgorithm.platzigram.login.interactor;

import android.app.Activity;

import com.chemicalgorithm.platzigram.login.presenter.LoginPresenter;
import com.chemicalgorithm.platzigram.login.repository.LoginRepository;
import com.chemicalgorithm.platzigram.login.repository.LoginRepositoryImpl;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Usuario on 20/02/2018.
 */

public class LoginInteractorImpl implements LoginInteractor
{

	private LoginRepository loginRepository;
	private LoginPresenter loginPresenter;

	public LoginInteractorImpl(LoginPresenter loginPresenter)
	{
		this.loginPresenter = loginPresenter;
		loginRepository = new LoginRepositoryImpl(loginPresenter);

	}

	@Override
	public void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth)
	{
		loginRepository.signIn(username, password, activity, firebaseAuth);
	}
}
