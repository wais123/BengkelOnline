package com.idetree.bemonline.admin_product;

import com.google.gson.annotations.SerializedName;

public class AdminProductModel {

	@SerializedName("product_status_raw")
	private int productStatusRaw;

	@SerializedName("product_image")
	private String productImage;

	@SerializedName("product_id")
	private int productId;

	@SerializedName("product_status")
	private String productStatus;

	@SerializedName("product_description")
	private String productDescription;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("product_quantity")
	private int productQuantity;

	public void setProductStatusRaw(int productStatusRaw){
		this.productStatusRaw = productStatusRaw;
	}

	public int getProductStatusRaw(){
		return productStatusRaw;
	}

	public void setProductImage(String productImage){
		this.productImage = productImage;
	}

	public String getProductImage(){
		return productImage;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setProductStatus(String productStatus){
		this.productStatus = productStatus;
	}

	public String getProductStatus(){
		return productStatus;
	}

	public void setProductDescription(String productDescription){
		this.productDescription = productDescription;
	}

	public String getProductDescription(){
		return productDescription;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductQuantity(int productQuantity){
		this.productQuantity = productQuantity;
	}

	public int getProductQuantity(){
		return productQuantity;
	}
}