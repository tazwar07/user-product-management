package com.tu.mnagement.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ProductInfoModel {
	private String productCode;
	private String productName;
	private String productType;
	private int productUnitPrice;
	private int productUnitVat;
	private double productPurchasePrice;
	private double productSellingPrice;
	private Date createdDate;
	private String status;
	private Long subId;
}