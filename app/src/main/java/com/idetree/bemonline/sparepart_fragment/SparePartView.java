package com.idetree.bemonline.sparepart_fragment;

import com.idetree.bemonline.model.JsonArrayProductModel;
import com.idetree.bemonline.response.GeneralResponse;

import java.util.List;

public interface SparePartView {
    void showProgress();
    void hideProgress();
    void getSparePartSuccess(List<JsonArrayProductModel> response);
    void getSparePartError(String error);
}
