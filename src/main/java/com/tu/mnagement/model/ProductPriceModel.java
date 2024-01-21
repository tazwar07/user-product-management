package com.tu.mnagement.model;

import java.util.Date;

import lombok.Data;

@Data
public class ProductPriceModel {
	
	private int productId;
	private Double mrp;
	private Double tp;
	private Double vat;
	private Date createDate;

}
