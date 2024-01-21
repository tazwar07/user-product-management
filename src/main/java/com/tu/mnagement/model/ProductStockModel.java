package com.tu.mnagement.model;

import java.util.Date;

import lombok.Data;

@Data
public class ProductStockModel {
	
	private double currentStock;
	private double availableStock;
	private double requisitionStock;
	private Date createDate;
	private String productId;
}
