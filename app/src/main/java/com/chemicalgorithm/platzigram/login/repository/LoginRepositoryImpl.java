package com.chemicalgorithm.platzigram.login.repository;

import com.chemicalgorithm.platzigram.login.presenter.LoginPresenter;

/**
 * Created by Usuario on 20/02/2018.
 */

public class LoginRepositoryImpl implements LoginRepository
{
	private LoginPresenter loginPresenter;

	public LoginRepositoryImpl(LoginPresenter loginPresenter)
	{
		this.loginPresenter = loginPresenter;
	}

	@Override
	public void signIn(String username, String password)
	{
		boolean success = true;

		if(success)
		{
			loginPresenter.loginSuccess();
		}
		else
		{
			loginPresenter.loginError("Ocurrió un Error");
		}
	}
}
