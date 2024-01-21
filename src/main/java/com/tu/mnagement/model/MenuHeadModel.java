package com.tu.mnagement.model;

import java.sql.Date;

import lombok.Data;

@Data
public class MenuHeadModel {
	private String mhName;
	private int mhSeq;
	private String status;
	private Date createdDate;

}
