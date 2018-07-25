package com.edurabroj.donape.Login;

import com.edurabroj.donape.entidades.LoginRespuesta;
import com.edurabroj.donape.preferences.IPreferences;

import static com.edurabroj.donape.data.PreferencesData.TOKEN_KEY;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Interactor.OnLoginFinishedListener {
    private LoginContract.View view;
    private IPreferences preferences;
    private LoginContract.Interactor interactor;

    LoginPresenter(
            LoginContract.View view,
            LoginContract.Interactor interactor,
            IPreferences preferences) {
        this.view = view;
        this.interactor = interactor;
        this.preferences = preferences;
    }

    @Override
    public void onLoginButtonClick(String user, String password) {
        if (user.isEmpty()) {
            view.errorUserRequired();
            return;
        }
        if (password.isEmpty()) {
            view.errorPasswordRequired();
            return;
        }

        view.showProgress();
        interactor.getLoginRespuesta(user, password,this);
    }

    @Override
    public void onSuccessLogin(LoginRespuesta loginRespuesta) {
        preferences.setStringPreference(TOKEN_KEY, loginRespuesta.getToken());
        view.launchHome();
    }

    @Override
    public void onFailedLogin() {
        view.hideProgress();
        view.errorWrongCredentials();
    }

    @Override
    public void onFailure(Throwable t) {
        view.hideProgress();
        view.errorNetwork();
    }
}
