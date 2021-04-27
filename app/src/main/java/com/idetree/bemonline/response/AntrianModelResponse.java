package com.idetree.bemonline.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idetree.bemonline.model.AntrianModel;

import java.util.List;

public class AntrianModelResponse {
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
    private List<AntrianModel> data = null;

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

    public List<AntrianModel> getData() {
        return data;
    }

    public void setData(List<AntrianModel> data) {
        this.data = data;
    }
}
