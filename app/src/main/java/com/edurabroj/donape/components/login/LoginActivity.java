package com.edurabroj.donape.components.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.edurabroj.donape.R;
import com.edurabroj.donape.components.publicacion.lista.ListaPublicacionActivity;
import com.edurabroj.donape.shared.preferences.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.edurabroj.donape.shared.utils.GuiUtils.getAnimation;

/**
 * A onLoginButtonClick screen that offers onLoginButtonClick via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    @BindView(R.id.email)  AutoCompleteTextView etUser;
    @BindView(R.id.password) EditText etPassword;
    @BindView(R.id.login_progress) View progress;
    @BindView(R.id.login_form) View loginForm;
    @BindView(R.id.ivLogo) ImageView ivLogo;
    @BindView(R.id.btnLogin) Button btnLogin;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenter(this, new LoginInteractor(), new Preferences(this));

        ivLogo.startAnimation(getAnimation(this,R.anim.item_animation_fall_down,getResources().getInteger(R.integer.animation_duration_medium)));
        loginForm.startAnimation(getAnimation(this,R.anim.item_animation_fade_in,getResources().getInteger(R.integer.animation_duration_medium)));
    }

    @OnClick(R.id.btnLogin) void btnLoginClick(){
        presenter.onLoginButtonClick(
            etUser.getText().toString(),
            etPassword.getText().toString()
        );
    }

    @Override
    public void errorNetwork() {
        etUser.setError(getString(R.string.error_internet));
        etUser.requestFocus();
    }

    @Override
    public void errorWrongCredentials() {
        etUser.setError(getString(R.string.error_failed_login));
        etUser.requestFocus();
    }

    @Override
    public void errorPasswordRequired() {
        etPassword.setError(getString(R.string.error_field_required));
        etPassword.requestFocus();
    }

    @Override
    public void errorUserRequired() {
        etUser.setError(getString(R.string.error_field_required));
        etUser.requestFocus();
    }

    @Override
    public void launchHome() {
        startActivity(new Intent(this,ListaPublicacionActivity.class));
        finish();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        loginForm.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
        loginForm.setVisibility(View.VISIBLE);
    }
}

