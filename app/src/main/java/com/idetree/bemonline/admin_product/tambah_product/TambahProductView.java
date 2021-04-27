package com.idetree.bemonline.admin_product.tambah_product;

import com.idetree.bemonline.model.JsonArrayProductModel;
import com.idetree.bemonline.response.GeneralResponse;

import java.util.List;

public interface TambahProductView {
    void showProgress();
    void hideProgress();
    void getAdminProductSuccess(GeneralResponse response);
    void getAdminProductError(String error);
}
