package com.chemicalgorithm.platzigram.login.interactor;

import com.chemicalgorithm.platzigram.login.presenter.LoginPresenter;
import com.chemicalgorithm.platzigram.login.repository.LoginRepository;
import com.chemicalgorithm.platzigram.login.repository.LoginRepositoryImpl;

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
	public void signIn(String username, String password)
	{
		loginRepository.signIn(username, password);
	}
}
