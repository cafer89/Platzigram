package com.chemicalgorithm.platzigram.login.presenter;

import com.chemicalgorithm.platzigram.login.interactor.LoginInteractor;
import com.chemicalgorithm.platzigram.login.interactor.LoginInteractorImpl;
import com.chemicalgorithm.platzigram.login.view.LoginView;

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
	public void signIn(String username, String password)
	{
		loginInteractor.signIn(username, password);
	}

	@Override
	public void loginSuccess()
	{
	}

	@Override
	public void loginError()
	{

	}
}
