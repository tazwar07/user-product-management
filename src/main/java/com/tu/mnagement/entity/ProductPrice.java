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
@Table(name="product_price", indexes= {	@Index(name="id",columnList="id",unique=true)})
public class ProductPrice {
	
	@Id
    @SequenceGenerator(
            name = "price_sequence",
            sequenceName = "price_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "price_sequence"
    )
    @Column(
            name = "Id",
            updatable = false
    )
    private Long id;
	private int productId;
	private Double mrp;
	private Double tp;
	private Double vat;
	
	@CreationTimestamp
	private Date createDate;

}
