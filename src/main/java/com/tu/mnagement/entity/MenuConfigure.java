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
@Table(name="menu_configure", indexes= {	@Index(name="id",columnList="id",unique=true)})
public class MenuConfigure {
	@Id
    @SequenceGenerator(
            name = "menu_sequence",
            sequenceName = "menu_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "menu_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
	private Long id;
	private int roleId;
	private int mhId;
	private int smId;
	private String svPermission;
	private String vwPermission;
	private String dlPermission;
	private Date createDate=new Date();
}
