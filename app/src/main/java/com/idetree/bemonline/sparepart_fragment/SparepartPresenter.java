package com.idetree.bemonline.sparepart_fragment;

import com.idetree.bemonline.network.Api;
import com.idetree.bemonline.response.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SparepartPresenter {
    private SparePartView sparePartView;

    public SparepartPresenter(SparePartView sparePartView) {
        this.sparePartView = sparePartView;
    }

    void getListProduct(String token, String accept){
        sparePartView.showProgress();
        Api.getService().getProduct(token,accept).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    sparePartView.hideProgress();
                    if (response.body().getCode() == 0){
                        sparePartView.getSparePartSuccess(response.body().getData().getData());
                    }else {
                        sparePartView.getSparePartError(response.body().getMessage());
                    }
                }else {
                    sparePartView.hideProgress();
                    sparePartView.getSparePartError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                sparePartView.hideProgress();
                sparePartView.getSparePartError(t.getMessage());
            }
        });
    }
}
