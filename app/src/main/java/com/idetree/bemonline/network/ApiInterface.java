package com.idetree.bemonline.network;

import android.database.Observable;

import com.idetree.bemonline.admin_product.AdminProductResponse;
import com.idetree.bemonline.response.AntrianModelResponse;
import com.idetree.bemonline.response.BookServiceModelResponse;
import com.idetree.bemonline.response.GeneralResponse;
import com.idetree.bemonline.response.LoginModelResponse;
import com.idetree.bemonline.response.ProductResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginModelResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("auth/signup")
    Call<GeneralResponse> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone_number") String phone,
            @Field("password") String password,
            @Field("password_confirmation") String confirm_password
    );

    @FormUrlEncoded
    @POST("auth/user/book")
    Call<GeneralResponse> bookService(
            @Header("Authorization") String token,
            @Header("Accept") String accept,
            @Field("nama") String nama,
            @Field("no_stnk") String stnk,
            @Field("tune_up") int tune_up,
            @Field("ganti_oli") int ganti_oli,
            @Field("keluhan") String keluhan
    );

    @GET("auth/user/book")
    Call<BookServiceModelResponse> getBookService(
            @Header("Authorization") String token,
            @Header("Accept") String accept
    );

    @POST("auth/checktoken")
    Call<GeneralResponse> checkToken(
            @Header("Authorization") String token,
            @Header("Accept") String accept
    );

    @GET("auth/user/book")
    Call<AntrianModelResponse> getAntrian(
            @Header("Authorization") String token,
            @Header("Accept") String accept
    );

    @GET("auth/user/product")
    Call<ProductResponse> getProduct(
            @Header("Authorization") String token,
            @Header("Accept") String accept
    );

    @GET("auth/admin/product")
    Call<AdminProductResponse> getAdminProduct(
            @Header("Authorization") String token,
            @Header("Accept") String accept
    );

    @FormUrlEncoded
    @POST("auth/admin/product/create")
    Call<GeneralResponse> createProduct(@Header("Authorization") String token,
                                        @Header("Accept") String accept,
                                        @Field("product_name") String product_name,
                                        @Field("product_description") String product_description,
                                        @Field("product_quantity") String product_quantity,
                                        @Field("image") String image);
}
