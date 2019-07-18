package com.ju.drmostafizur.presentation.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.executor.impl.ThreadExecutor;
import com.ju.drmostafizur.presentation.doctor.ui.activities.MainActivity;
import com.ju.drmostafizur.threading.MainThreadImpl;
import com.ju.drmostafizur.utills.AeSimpleSHA1;
import com.ju.drmostafizur.utills.MostafizurConstraint;
import com.ju.drmostafizur.utills.MySharePreferences;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends Activity implements LoginPresenter.View{

    private EditText mEtUserName;
    private EditText mEtPassword;
    private Button mBtnLogin;

    private ProgressDialog mProgressDialog;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtUserName = (EditText) findViewById(R.id.editTextUserName);
        mEtPassword = (EditText) findViewById(R.id.editTextPassword);
        mBtnLogin = (Button) findViewById(R.id.buttonLogin);

        mPresenter = new LoginPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this,this);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                boolean flag = checkValidation(mEtUserName, mEtPassword);
                if (flag){
                    mPresenter.validateCredentials(mEtUserName.getText().toString(), convert(mEtPassword.getText().toString()));
                }
            }
        });
    }

    @Override
    public boolean checkValidation(EditText etUserName, EditText etPassword) {

        boolean check = false;
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        if(userName.isEmpty()){
            hideProgress();
            etUserName.setError(MostafizurConstraint.EMPTY_USER_NAME);
        }
        else if(password.isEmpty()){
            hideProgress();
            etPassword.setError(MostafizurConstraint.EMPTY_PASSWORD);
        }
        else {
            if (password.length() < 5 ){
                hideProgress();
                etPassword.setError(MostafizurConstraint.PASSWORD_LENGTH);
            }
            else {
                check = true;
            }
        }


        return check;
    }

    @Override
    public void loginSuccess() {
        new MySharePreferences(this).setBoolean(MySharePreferences.CHECKED, true);
        startActivity(new Intent(this, MainActivity.class));
        showError("Login success");
    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void showProgress() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }


    private String convert(String s) {

        String hash = null;

        try {
            hash = AeSimpleSHA1.SHA1(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Log.e("Hashing", hash);

        return hash;

    }
}
