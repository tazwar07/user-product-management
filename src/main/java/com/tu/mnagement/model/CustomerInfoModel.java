package com.tu.mnagement.model;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerInfoModel {
	private String customerName;
	private String address;
	private String phone;
	private String mail;
	private String status;
	private Date createDate;

}
