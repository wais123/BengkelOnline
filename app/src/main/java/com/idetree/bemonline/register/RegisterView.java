package com.idetree.bemonline.register;

import com.idetree.bemonline.response.GeneralResponse;

public interface RegisterView {
    void showProgressBar();
    void hideProgressBar();
    void successRegister(GeneralResponse response);
    void errorRegister(String error);
}
