package com.tu.mnagement.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Sub_MenuModel {
	
	private String smName;
	private int mhId;
	private int smSeq;
	private String url;
	private Date createDate;

}
