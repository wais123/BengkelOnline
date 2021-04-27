package com.idetree.bemonline.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idetree.bemonline.model.BookServiceModel;

import java.util.List;

public class BookServiceModelResponse {
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
    private List<BookServiceModel> data = null;

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

    public List<BookServiceModel> getData() {
        return data;
    }

    public void setData(List<BookServiceModel> data) {
        this.data = data;
    }
}
