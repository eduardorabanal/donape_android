package com.edurabroj.donape.components.Login;

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
import com.edurabroj.donape.components.Lista.ListaActivity;
import com.edurabroj.donape.components.ListaGrupos.ListaGruposActivity;
import com.edurabroj.donape.shared.preferences.Preferences;

import static com.edurabroj.donape.shared.utils.GuiUtils.getAnimation;

/**
 * A onLoginButtonClick screen that offers onLoginButtonClick via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    private AutoCompleteTextView etUser;
    private EditText etPassword;
    private View progress;
    private View loginForm;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this, new LoginInteractor(), new Preferences(this));

        etUser = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        ImageView ivLogo = findViewById(R.id.ivLogo);
        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);

        loginForm = findViewById(R.id.login_form);
        progress = findViewById(R.id.login_progress);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            presenter.onLoginButtonClick(
                etUser.getText().toString(),
                etPassword.getText().toString()
            );
            }
        });

        ivLogo.startAnimation(getAnimation(this,R.anim.item_animation_fall_down,getResources().getInteger(R.integer.animation_duration_medium)));
        loginForm.startAnimation(getAnimation(this,R.anim.item_animation_fade_in,getResources().getInteger(R.integer.animation_duration_medium)));
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
        startActivity(new Intent(this,ListaGruposActivity.class));
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

