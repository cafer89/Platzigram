package com.chemicalgorithm.platzigram.login.presenter;

/**
 * Created by Usuario on 20/02/2018.
 */

public interface LoginPresenter
{
	void signIn(String username, String password);
	void loginSuccess();
	void loginError();
}
