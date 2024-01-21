package com.tu.mnagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="product_stock", indexes= {	@Index(name="id",columnList="id",unique=true)})

public class ProductStock {
	@Id
    @SequenceGenerator(
            name = "stock_sequence",
            sequenceName = "stock_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_sequence"
    )
    @Column(
            name = "Id",
            updatable = false
    )
    private Long id;
	private double currentStock;
	private double availableStock;
	private double requisitionStock;
	private String status;
	private Date createDate = new Date();
	
	@OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
	@JsonIgnore
    private ProductInfo productInfo;

}
