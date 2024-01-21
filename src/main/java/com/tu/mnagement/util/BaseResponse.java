package com.tu.mnagement.util;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tu.mnagement.entity.MenuHead;
import com.tu.mnagement.entity.ProductInfo;
import com.tu.mnagement.entity.ProductRequestMst;
import com.tu.mnagement.entity.Role;
import com.tu.mnagement.entity.SignUp;
import com.tu.mnagement.entity.Sub_Menu;
import com.tu.mnagement.entity.UserInfo;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
	
	String message;
	
    List<SignUp> signups;
	SignUp signup;
	Optional<SignUp> signUpOptional;
	
	String role;
	List<Role> roles;
	Optional<Role> roleOptional;
	
	List <UserInfo> userInfo;
	
	List<ProductInfo> productInfo;
	
	List<ProductRequestMst> productRequestMst; 
	
    List<MenuHead> menuHead;
    
    List<Sub_Menu> sub_Menu;
    

	Map<Long, List<ProductInfo>> products;
	
}
