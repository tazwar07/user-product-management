package com.tu.mnagement.entity;

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
@Table(name="role_info",indexes= {@Index(name="role_id",columnList="roleId",unique=true)})
public class Role {
	
	@Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @Column(
            name = "roleId",
            updatable = false
    )
    private Long id;

	private String roleName;
	
	private String description;
	
	private String status;

}
