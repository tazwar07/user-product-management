package com.tu.mnagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="product_request_dtl", indexes= {@Index(name="dtlId",columnList="dtlId",unique=true)})
public class ProductRequestDtl {
	@Id
    @SequenceGenerator(
            name = "product_req_dtl_seq",
            sequenceName = "product_req_dtl_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_req_dtl_seq"
    )
    @Column(
            name = "dtlId",
            updatable = false
    )
    private Long id;
	private int requestQty;
	private String status;
	private Date createDate=new Date();
	private String dtlRemarks;
	private int productId;
	
	@ManyToOne
	@JoinColumn(name = "mst_id",referencedColumnName = "mstId")
	@JsonIgnore
	private ProductRequestMst productRequestMst; 

}
