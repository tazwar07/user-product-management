package com.tu.mnagement.model;

import lombok.Data;

@Data
public class UpdateRoleInfoModel {
	public String roleName;
	public String description;
	public String status;
	public Long id;

}