package com.chemicalgorithm.platzigram.login.view;

/**
 * Created by Usuario on 20/02/2018.
 */

public interface LoginView
{
	void goCreateAccount();
	void goHome();

	void showProgressBar();
	void hideProgressBar();

	void loginError(String error);

	void enableInputs();
	void disableInputs();

}
