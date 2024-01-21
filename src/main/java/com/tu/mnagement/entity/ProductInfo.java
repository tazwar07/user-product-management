package com.tu.mnagement.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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

import lombok.Data;

@Entity
@Data
@Table(name="product_info", indexes= {	@Index(name="id",columnList="id",unique=true),
										@Index(name="productName",columnList="productName",unique=true)
		})

public class ProductInfo {
	@Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
	private String productName;
	private String productType;
	private int productUnitPrice;
	private int productUnitVat;
	private double productPurchasePrice;
	private double productSellingPrice;
	private Date createdDate = new Date();
	private String status;
	private String productCode;
	private Long subId;
	
	@OneToOne(mappedBy = "productInfo", cascade = CascadeType.ALL)
	private ProductStock productStock;
}