package com.idetree.bemonline.login;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.idetree.bemonline.MenuActivity;
import com.idetree.bemonline.network.Api;
import com.idetree.bemonline.response.LoginModelResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    void loginProccess(String username, String password){
        view.showProgressBar();
        Api.getService().login(username, password).enqueue(new Callback<LoginModelResponse>() {
            @Override
            public void onResponse(Call<LoginModelResponse> call, Response<LoginModelResponse> response) {
                if (response.isSuccessful()) {
                    view.hideProgressBar();
                    view.successLogin(response.body());

                } else {
                    view.hideProgressBar();
                    view.errorLogin(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginModelResponse> call, Throwable t) {
                view.hideProgressBar();
                view.errorLogin(t.getMessage());
            }
        });
    }
}
