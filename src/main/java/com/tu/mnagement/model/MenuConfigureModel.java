package com.tu.mnagement.model;

import java.sql.Date;

import lombok.Data;

@Data
public class MenuConfigureModel {
	
	private int roleId;
	private int mhId;
	private int smId;
	private String svPermission;
	private String vwPermission;
	private String dlPermission;
	private Date createDate;


}
