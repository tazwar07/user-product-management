package com.tu.mnagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Entity
@Data
@Table(name="user_info", indexes= {	@Index(name="id",columnList="userId",unique=true), 
									@Index(name="userName",columnList="userName",unique=true)
								})
public class UserInfo {
	@Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "userId",
            updatable = false
    )
    private Long id;
	private String name;
	private String phone;
	private String mail;
	private String address;
	private String blood;
	private String gender;
	
	@JsonFormat(pattern="yyyy-MM-dd",shape = Shape.STRING)
	private String birthday;
	
	@CreationTimestamp
	private Date createdDate;
	
	private String userName;
	private String password;
	private String status;
	private String employeeId;
	private String imageFilePath;
	private String nidFilePath;
	private String nid;
	private String designation;
		
	
}
