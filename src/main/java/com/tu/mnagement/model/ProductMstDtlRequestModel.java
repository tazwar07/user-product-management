package com.tu.mnagement.model;

import lombok.Data;

@Data
public class ProductMstDtlRequestModel {
	
	private String productId;
	private String requestNo;
	private String mstRemarks;
    private String status;
	private String requestQty;
	private String dtlRemarks;
    

}
