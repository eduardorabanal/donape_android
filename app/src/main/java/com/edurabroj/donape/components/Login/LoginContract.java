package com.edurabroj.donape.components.Login;

import com.edurabroj.donape.shared.entidades.LoginRespuesta;

public class LoginContract {
    public interface View {
        void showProgress();
        void hideProgress();

        void errorPasswordRequired();
        void errorUserRequired();
        void errorNetwork();
        void errorWrongCredentials();

        void launchHome();
    }

    public interface Presenter {
        void onLoginButtonClick(String user, String password);
    }

    public interface Interactor {
        interface OnLoginFinishedListener {
            void onSuccessLogin(LoginRespuesta loginRespuesta);
            void onFailedLogin();
            void onFailure(Throwable t);
        }

        void getLoginRespuesta(String user, String password, OnLoginFinishedListener onLoginFinishedListener);
    }
}
