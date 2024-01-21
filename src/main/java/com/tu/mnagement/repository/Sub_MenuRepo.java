package com.tu.mnagement.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tu.mnagement.entity.Sub_Menu;
import com.tu.mnagement.model.SubMenuAllInterfaceModel;

@Repository
@Transactional
public interface Sub_MenuRepo extends JpaRepository<Sub_Menu, Long> {
	 
	@Query(value="SELECT  A.SM_ID AS smId,\r\n"
			+ "        A.MH_ID as mhId,\r\n"
			+ "        B.MH_NAME as mhName,\r\n"
			+ "        A.SM_NAME as smName,\r\n"
			+ "        A.SM_SEQ as smSeq,\r\n"
			+ "        A.URL as url,\r\n"
			+ "        A.STATUS as status\r\n"
			+ "FROM    SUB_MENU A,\r\n"
			+ "        MENU_HEAD B\r\n"
			+ "WHERE   A.MH_ID=B.MH_ID",nativeQuery = true) 
	List<SubMenuAllInterfaceModel> findSubMenuInformation();

}