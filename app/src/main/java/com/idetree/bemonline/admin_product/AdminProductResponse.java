package com.idetree.bemonline.admin_product;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AdminProductResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<AdminProductModel> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(List<AdminProductModel> data){
		this.data = data;
	}

	public List<AdminProductModel> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}