package com.tu.mnagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tu.mnagement.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository <Role,Long>  {
	
	@Modifying
	@Query ("update Role ui set ui.roleName=:roleName,ui.description=:description,ui.status=:status where ui.id=:id")
	void updateRoleInfo (String roleName, String description, String status, Long id); 

}