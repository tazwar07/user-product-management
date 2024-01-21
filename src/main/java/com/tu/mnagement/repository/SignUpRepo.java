package com.tu.mnagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tu.mnagement.entity.SignUp;

@Repository
public interface SignUpRepo extends JpaRepository<SignUp, Long> {
	
  //SignUp findByEmpFirstName(String empFirstName); // JPQL
  
  //@Query("select te from SignUp te where te.empFirstName=:empFirstName") // Normal Query
  //SignUp findByEmpFirstName (String empFirstName); 
  
  @Query(value="select * from employee_info  where emp_first_name=:empFirstName",nativeQuery = true) // Native Query
  List<SignUp> findByEmpFirstName (String empFirstName);   
	
  @Query(value="select password from employee_info  where user_name=:userName",nativeQuery = true) // Native Query
  String findByUserName (String userName);  
  
  SignUp findByMail(String mail);
  
  SignUp findByPassword(String password);
}
