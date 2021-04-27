package com.idetree.bemonline.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idetree.bemonline.model.JsonObjectProductModel;

public class ProductResponse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private JsonObjectProductModel data;
    @SerializedName("asset_path")
    @Expose
    private String assetPath;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonObjectProductModel getData() {
        return data;
    }

    public void setData(JsonObjectProductModel data) {
        this.data = data;
    }

    public String getAssetPath() {
        return assetPath;
    }

    public void setAssetPath(String assetPath) {
        this.assetPath = assetPath;
    }
}
