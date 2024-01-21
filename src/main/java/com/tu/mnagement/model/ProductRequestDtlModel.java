package com.tu.mnagement.model;

import java.util.Date;

import lombok.Data;
@Data
public class ProductRequestDtlModel {
	
	private int requestQty;
	private String status;
	private Date createDate;
	private String dtlRemarks;
	private Long productId;

}
