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
@Table(name="role_configure", indexes= {	@Index(name="id",columnList="id",unique=true)})
public class RoleConfigure {
	@Id
    @SequenceGenerator(
            name = "role_conf_sequence",
            sequenceName = "role_conf_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_conf_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
	private int roleId;
	private int userId;
	private String status;

}
