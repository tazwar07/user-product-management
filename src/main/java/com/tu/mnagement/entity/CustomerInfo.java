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

import lombok.Data;

@Entity
@Data
@Table(name="customer_info", indexes= {	@Index(name="id",columnList="id",unique=true)})
public class CustomerInfo {
	@Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(
            name = "Id",
            updatable = false
    )
    private Long id;
	private String customerName;
	private String address;
	private String phone;
	private String mail;
	private String status;
	
	@CreationTimestamp
	private Date createDate;

}
