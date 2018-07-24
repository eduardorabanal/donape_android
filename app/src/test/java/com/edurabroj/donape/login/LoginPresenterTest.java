package com.edurabroj.donape.login;

import com.edurabroj.donape.preferences.IPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class LoginPresenterTest {
    private LoginPresenter loginPresenter;
    private LoginContract.View view;
    private LoginContract.Interactor interactor;

    @Before
    public void setUp() throws Exception {
        view = Mockito.mock(LoginContract.View.class);
        interactor = Mockito.mock(LoginContract.Interactor.class);
        IPreferences prefs = Mockito.mock(IPreferences.class);
        loginPresenter = new LoginPresenter(view, interactor, prefs);
    }

    @Test
    public void usuarioVacioMuestraError() {
        loginPresenter.onLoginButtonClick("","123");
        Mockito.verify(view).errorUserRequired();
    }

    @Test
    public void passVacioMuestraError() {
        loginPresenter.onLoginButtonClick("ola","");
        Mockito.verify(view).errorPasswordRequired();
    }

    @Test
    public void credencialesCompletasMuestraProgress() {
        loginPresenter.onLoginButtonClick("mimo","123");
        Mockito.verify(view).showProgress();
    }

    @Test
    public void credencialesCompletasLlamaGetLoginRespuesta() {
        String user = "mimo", pass = "123";
        loginPresenter.onLoginButtonClick(user,pass);
        Mockito.verify(interactor).getLoginRespuesta(user,pass,loginPresenter);
    }
}