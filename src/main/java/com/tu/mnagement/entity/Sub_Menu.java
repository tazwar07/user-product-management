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
@Table(name="Sub_Menu", indexes= {	@Index(name="sm_id",columnList="sm_id",unique=true)})
public class Sub_Menu {
	@Id
    @SequenceGenerator(
            name = "sub_menu_sequence",
            sequenceName = "sub_menu_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sub_menu_sequence"
    )
    @Column(
            name = "sm_id",
            updatable = false
    )
    private Long sm_id;
	private String smName;
	private int mhId;
	private int smSeq;
	private String url;
	private Date createDate= new Date();

}
