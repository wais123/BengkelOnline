package com.idetree.bemonline.admin_product;

import com.idetree.bemonline.model.JsonArrayProductModel;

import java.util.List;

public interface AdminProductView {
    void showProgress();
    void hideProgress();
    void getAdminProductSuccess(List<AdminProductModel> response);
    void getAdminProductError(String error);
}
