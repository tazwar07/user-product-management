package com.tu.mnagement.model;

import java.util.Date;

import lombok.Data;

@Data
public class ProductPriceRequestModel {
	
	private String productId;
	private String mrp;
	private String tp;
	private String vat;
	private Date createDate;

}
