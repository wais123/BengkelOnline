package com.idetree.bemonline.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.idetree.bemonline.MenuActivity;
import com.idetree.bemonline.R;
import com.idetree.bemonline.network.Api;
import com.idetree.bemonline.response.GeneralResponse;
import com.idetree.bemonline.response.LoginModelResponse;
import com.idetree.bemonline.session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    EditText etNik;
    EditText etPhone;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    Button btnSubmit;

    SessionManager sessionManager;

    RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sessionManager = new SessionManager(this);
        presenter = new RegisterPresenter(this);
        initComponent();

    }

    private void initComponent(){
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnSubmit = findViewById(R.id.btn_submit);
        etNik = findViewById(R.id.et_nik);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNik.getText().toString().trim().equalsIgnoreCase(null) || etNik.getText().toString().trim().equalsIgnoreCase("")) {
                    etNik.setError("Name is Required");
                } else if (etEmail.getText().toString().trim().equalsIgnoreCase(null) || etEmail.getText().toString().trim().equalsIgnoreCase("")) {
                    etEmail.setError("Email is Required");
                } else if (etPhone.getText().toString().trim().equalsIgnoreCase(null) || etPhone.getText().toString().trim().equalsIgnoreCase("")) {
                    etPhone.setError("Phone is Required");
                } else if (etPassword.getText().toString().trim().equalsIgnoreCase(null) || etPassword.getText().toString().trim().equalsIgnoreCase("")) {
                    etPassword.setError("Password is Required");
                } else if (etConfirmPassword.getText().toString().trim().equalsIgnoreCase(null) || etConfirmPassword.getText().toString().trim().equalsIgnoreCase("")) {
                    etConfirmPassword.setError("Confirm Password is Required");
                } else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Password not same", Toast.LENGTH_SHORT).show();
                } else {
//                    registerProccess();
                    presenter.register(etNik.getText().toString(),etEmail.getText().toString(),etPhone.getText().toString(),etPassword.getText().toString(),etConfirmPassword.getText().toString());
                }

            }
        });
    }

    public void loginProses(String a, String b) {
        Api.getService().login(a, b).enqueue(new Callback<LoginModelResponse>() {
            @Override
            public void onResponse(Call<LoginModelResponse> call, Response<LoginModelResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 0) {
                        sessionManager.createLoginSession("bengkel", response.body().getData().getEmail(), response.body().getData().getToken(), response.body().getData().getLevel());
                        Intent login = new Intent(RegisterActivity.this, MenuActivity.class);
                        startActivity(login);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModelResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void successRegister(GeneralResponse response) {
        if (response.getCode() == 0) {
            loginProses(etEmail.getText().toString(), etPassword.getText().toString());
        } else {
            Toast.makeText(RegisterActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void errorRegister(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
