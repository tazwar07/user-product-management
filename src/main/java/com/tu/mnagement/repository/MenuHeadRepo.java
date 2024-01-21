package com.tu.mnagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tu.mnagement.entity.MenuHead;

public interface MenuHeadRepo extends JpaRepository<MenuHead, Long> {

	@Modifying
	@Query ("delete from MenuHead ui where ui.mhName=:mhName")
	void deleteMenuHead (String mhName);

	@Modifying
	@Query ("update MenuHead ui set ui.mhName=:mhName,ui.mhSeq=:mhSeq,ui.status=:status where ui.id=:id")
	void updateRoleInfo(String mhName, int mhSeq, String status, Long id);

}