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

import lombok.Data;

@Entity
@Data
@Table(name="menu_head", indexes= {	@Index(name="mh_id",columnList="mh_id",unique=true)})
public class MenuHead {
	@Id
    @SequenceGenerator(
            name = "mh_sequence",
            sequenceName = "mh_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mh_sequence"
    )
    @Column(
            name = "mh_id",
            updatable = false
    )
	
	private Long mhId;
	private String mhName;
	private int mhSeq;
	private String status;
	private Date createdDate = new Date();

}
