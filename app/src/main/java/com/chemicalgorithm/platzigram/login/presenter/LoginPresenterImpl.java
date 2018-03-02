package com.chemicalgorithm.platzigram.login.presenter;

import android.app.Activity;

import com.chemicalgorithm.platzigram.login.interactor.LoginInteractor;
import com.chemicalgorithm.platzigram.login.interactor.LoginInteractorImpl;
import com.chemicalgorithm.platzigram.login.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Usuario on 20/02/2018.
 */

public class LoginPresenterImpl implements LoginPresenter
{
	private LoginView loginView;
	private LoginInteractor loginInteractor;

	public LoginPresenterImpl(LoginView loginView)
	{
		this.loginView = loginView;
		this.loginInteractor = new LoginInteractorImpl(this);

	}

	@Override
	public void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth)
	{
		loginView.disableInputs();
		loginView.showProgressBar();
		loginInteractor.signIn(username, password, activity, firebaseAuth);
	}

	@Override
	public void loginSuccess()
	{
		//cuando el login es exitoso, le decimos a la vista que vaya al Home.
		loginView.goHome();

		loginView.hideProgressBar();
	}

	@Override
	public void loginError(String error)
	{
		loginView.enableInputs();
		loginView.hideProgressBar();

		loginView.loginError(error);
	}
}
