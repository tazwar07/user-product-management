package com.tu.mnagement.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tu.mnagement.model.AuthRequestModel;
import com.tu.mnagement.model.UserInfoModel;
import com.tu.mnagement.repository.UserInfoRepo;
import com.tu.mnagement.service.AdminService;
import com.tu.mnagement.util.BaseResponse;
import com.tu.mnagement.util.JwtUtil;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*")
@RestController
public class AuthController {
	
	@Autowired
    private JwtUtil jwtUtil;
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
	@Autowired
	AdminService adminService;
    
	@Autowired
	UserInfoRepo userInfoRepo;
    
    
    //------------------- Login With Token generate ------------------------------------------
    
    @PostMapping("/authenticate")
    public HashMap<String,String> generateToken(@RequestBody AuthRequestModel authRequest) throws Exception {
    	HashMap <String,String> hashmap = new HashMap<>();
    	
        try {
        		authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
                );
                hashmap.put("login", "success");
                hashmap.put("token", jwtUtil.generateToken(authRequest.getUserName()));
        	
            
        } catch (Exception ex) {
        	hashmap.put("login", "failed");
            System.out.println("inavalid username/password");
        }
        
        return hashmap;
    }
    
    
    // ------------------------ User Registration Without Token Generate -------------------------------
    
	@PostMapping("/userSignUp")
	BaseResponse userInfo(@RequestParam String request, @RequestParam(required = false, name="uploadImage") MultipartFile file,@RequestParam(required = false, name="uploadNid")MultipartFile file2) throws JsonMappingException, JsonProcessingException {

        ObjectMapper mapper= new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        UserInfoModel userInfoModel=mapper.readValue(request, UserInfoModel.class);
        
		return adminService.userInfo(userInfoModel,file,file2);
	}
	
	
	@PostMapping("/userRegistration")
	BaseResponse userRegistration(@RequestBody UserInfoModel request) {			
		return adminService.userRegistration(request);
	}
    
    
    

}