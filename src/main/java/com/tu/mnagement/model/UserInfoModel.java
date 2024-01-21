package com.tu.mnagement.model;

import java.sql.Date;

import lombok.Data;

@Data
public class UserInfoModel {
	private String name;
	private String phone;
	private String mail;
	private String address;
	private String blood;
	private String birthday;
	private Date createdDate;
	private String userName;
	private String password;
	private String status;
	private String employeeId;
	private String imageFilePath;
	private String nidFilePath;
	private String gender;
	private String nid;
	private String designation;
}
