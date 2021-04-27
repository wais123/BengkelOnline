package com.idetree.bemonline.register;

import android.widget.Toast;

import com.idetree.bemonline.network.Api;
import com.idetree.bemonline.response.GeneralResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private RegisterView view;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
    }

    void register(String name, String email, String phone, String password, String confirmPassword) {
        view.showProgressBar();
        Api.getService().register(name, email, phone, password, confirmPassword).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.isSuccessful()) {
                    view.hideProgressBar();
                    view.successRegister(response.body());
                } else {
                    view.hideProgressBar();
                    view.errorRegister(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                view.hideProgressBar();
                view.errorRegister(t.getMessage());
            }
        });
    }
}
