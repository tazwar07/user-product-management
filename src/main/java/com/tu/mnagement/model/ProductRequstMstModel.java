package com.tu.mnagement.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ProductRequstMstModel {
	
	private int productId;
	private String requestNo;
	private Date createdDate;
	private String mstRemarks;
    private String status;
    
}
