package com.idetree.bemonline.login;

import com.idetree.bemonline.response.LoginModelResponse;

public interface LoginView {
    void showProgressBar();
    void hideProgressBar();
    void successLogin(LoginModelResponse response);
    void errorLogin(String error);
}
