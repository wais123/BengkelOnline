package com.idetree.bemonline.admin_product;

import com.idetree.bemonline.network.Api;
import com.idetree.bemonline.response.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminProductPresenter {
    private AdminProductView adminProductView;

    public AdminProductPresenter(AdminProductView adminProductView) {
        this.adminProductView = adminProductView;
    }

    void getListAdminProduct(String token, String accept) {
        adminProductView.showProgress();
        Api.getService().getAdminProduct(token, accept).enqueue(new Callback<AdminProductResponse>() {
            @Override
            public void onResponse(Call<AdminProductResponse> call, Response<AdminProductResponse> response) {
                if (response.isSuccessful()) {
                    adminProductView.hideProgress();
                    if (response.body().getCode() == 0) {
                        adminProductView.getAdminProductSuccess(response.body().getData());
                    } else {
                        adminProductView.getAdminProductError(response.body().getMessage());
                    }
                } else {
                    adminProductView.hideProgress();
                    adminProductView.getAdminProductError(response.message());
                }
            }

            @Override
            public void onFailure(Call<AdminProductResponse> call, Throwable t) {
                adminProductView.hideProgress();
                adminProductView.getAdminProductError(t.getMessage());
            }
        });
    }
}
