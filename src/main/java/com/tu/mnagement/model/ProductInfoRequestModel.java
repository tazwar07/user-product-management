package com.tu.mnagement.model;

import com.tu.mnagement.util.BaseResponse;

import lombok.Data;

@Data
public class ProductInfoRequestModel {
	
	private String productCode;
	private String productName;
	private String productType;
	private int productUnitPrice;
	private int productUnitVat;
	private double productPurchasePrice;
	private double productSellingPrice;
	private String status;
	private double currentStock;
	private double availableStock;
	private double requisitionStock;
	public static BaseResponse addProduct(ProductInfoModel reqModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
