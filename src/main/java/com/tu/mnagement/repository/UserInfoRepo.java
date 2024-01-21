package com.tu.mnagement.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.tu.mnagement.entity.UserInfo;


@Transactional
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
	
	UserInfo findByUserName(String userName);
	
	String findByMail(String mail);
	String findByPassword (String password);
		
	public boolean existsByMailAndPassword(String mail, String password);
	
	public boolean existsByPhoneAndPassword(String phone, String password);
	
	public boolean existsByUserNameAndPassword(String userName, String password);
	
	@Query(value="select nvl(max(cast(substr(employee_id,7,4)as integer)),0)+1 from user_info",nativeQuery = true) 
    Long findMaxEmployeeIDyYearMonth(String yearMonth);
	
	@Modifying
	@Query ("update UserInfo ui set ui.name=:name where ui.userName=:userName")
	void updateUserInfo (String userName, String name); 
	
	@Modifying
	@Query ("delete from UserInfo ui where ui.userName=:userName")
	void deleteUserInfo (String userName);
	
}
