package com.tu.mnagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(name="employee_info", indexes= {@Index(name="emp_id",columnList="empId",unique=true), @Index(name="userName",columnList="userName",unique=true)})
//uniqueConstraints = @UniqueConstraint(name="uk_phone_nid_mail_username",columnNames = {"phone","NID","username"}))
public class SignUp {
	
	@Id
    @SequenceGenerator(
            name = "emp_sequence",
            sequenceName = "emp_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "emp_sequence"
    )
    @Column(
            name = "empId",
            updatable = false
    )
    private Long id;
	private String empFirstName;
	private String empLastName;
	private String phone;
	private String NID;
	private String address;
	private String mail;
	private int age;
	private String gender;
	private String userName;
	private String password;
	private String status;
	
}


