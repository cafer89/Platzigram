package com.chemicalgorithm.platzigram.login.interactor;

import com.chemicalgorithm.platzigram.login.presenter.LoginPresenter;

/**
 * Created by Usuario on 20/02/2018.
 */

public class LoginInteractorImpl implements LoginInteractor
{

	private LoginPresenter loginPresenter;

	public LoginInteractorImpl(LoginPresenter loginPresenter)
	{
		this.loginPresenter = loginPresenter;

	}

	@Override
	public void signIn(String username, String password)
	{

	}
}
