package com.idetree.bemonline.antrian_fragment;

import com.idetree.bemonline.network.Api;
import com.idetree.bemonline.response.AntrianModelResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarAntrianPresenter {
    private DaftarAntrianView antrianView;

    public DaftarAntrianPresenter(DaftarAntrianView antrianView) {
        this.antrianView = antrianView;
    }

    void getListAntrian(String token, String accept){
        antrianView.showProgress();
        Api.getService().getAntrian(token,accept).enqueue(new Callback<AntrianModelResponse>() {
            @Override
            public void onResponse(Call<AntrianModelResponse> call, Response<AntrianModelResponse> response) {
                if (response.isSuccessful()){
                    antrianView.hideProgress();
                    if (response.body().getCode() == 0){
                        antrianView.getAntrianSuccess(response.body().getData());
                    }else {
                        antrianView.getAntrianError(response.body().getMessage());
                    }
                }else {
                    antrianView.hideProgress();
                    antrianView.getAntrianError(response.message());
                }
            }

            @Override
            public void onFailure(Call<AntrianModelResponse> call, Throwable t) {
                antrianView.hideProgress();
                antrianView.getAntrianError(t.getMessage());
            }
        });
    }
}
