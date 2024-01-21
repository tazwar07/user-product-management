package com.tu.mnagement.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="product_request_mst", indexes= {@Index(name="mstId",columnList="mstId",unique=true)})
public class ProductRequestMst {
	@Id
    @SequenceGenerator(
            name = "product_req_mst_seq",
            sequenceName = "product_req_mst_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_req_mst_seq"
    )
    @Column(
            name = "mstId",
            updatable = false
    )
    private Long id;
	private String requestNo;
	private Date createdDate = new Date();
	private String mstRemarks;
    private String status;
    
    @OneToMany(mappedBy = "productRequestMst", cascade = CascadeType.ALL)
    private List<ProductRequestDtl> productRequestDtls;
  

}
