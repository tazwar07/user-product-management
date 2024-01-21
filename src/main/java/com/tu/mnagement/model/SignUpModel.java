package com.tu.mnagement.model;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignUpModel {
	
	@NotEmpty (message = "First Name can not be Blank !")
	@Column(nullable = false)
	@NotBlank
	private String empFirstName;
	
	@NotEmpty (message = "Last Name can not be Blank !")
	private String empLastName;
	
	private String address;
	
	@Email (message = "Please Enter valid Mail Address !")
	@Column(unique = true)
	@NotBlank
	private String mail;
	
	private String phone;
	private String NID;
	private int age;
	private String gender;
	
	@NotEmpty (message = "User Name can not be Blank !")
	@Column(nullable = false,unique = true)
	@NotBlank
	@NotNull
	private String userName;
	
	@NotEmpty (message = "Password can not be Blank !")
	@Size (min = 4,max = 20,message = "Password must be minimum 4 and maximum 20 !")
	@NotBlank
	@NotNull
	private String password;
	
	private String status;
}
