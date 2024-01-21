package com.tu.mnagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.tu.mnagement.entity.Role;
import com.tu.mnagement.entity.SignUp;
import com.tu.mnagement.entity.UserInfo;
import com.tu.mnagement.model.CustomerInfoModel;
import com.tu.mnagement.model.MenuConfigureModel;
import com.tu.mnagement.model.MenuHeadModel;
import com.tu.mnagement.model.ProductInfoRequestModel;
import com.tu.mnagement.model.ProductMstDtlRequestModel;

import com.tu.mnagement.model.ProductPriceRequestModel;
import com.tu.mnagement.model.RoleConfigureModel;
import com.tu.mnagement.model.RoleModel;
import com.tu.mnagement.model.SignUpModel;
import com.tu.mnagement.model.SubMenuAllModel;
import com.tu.mnagement.model.Sub_MenuModel;
import com.tu.mnagement.model.UpdateMenuHeadModel;
import com.tu.mnagement.model.UpdateRoleInfoModel;
import com.tu.mnagement.model.UpdateUserBodyModel;
import com.tu.mnagement.repository.CustomerInfoRepo;
import com.tu.mnagement.repository.MenuConfirureRepo;
import com.tu.mnagement.repository.MenuHeadRepo;
import com.tu.mnagement.repository.ProductInfoRepo;
import com.tu.mnagement.repository.ProductPriceRepo;
import com.tu.mnagement.repository.ProductRequestDtlRepo;
import com.tu.mnagement.repository.ProductRequestMstRepo;
import com.tu.mnagement.repository.ProductStockRepo;
import com.tu.mnagement.repository.RoleConfigureRepo;
import com.tu.mnagement.repository.RoleRepo;
import com.tu.mnagement.repository.SignUpRepo;
import com.tu.mnagement.repository.Sub_MenuRepo;
import com.tu.mnagement.repository.UserInfoRepo;
import com.tu.mnagement.service.AdminService;
import com.tu.mnagement.util.BaseResponse;

@CrossOrigin(origins = "*")
@Validated
@RestController
@RequestMapping("admin/api/v1")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired 
	SignUpRepo signUpRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	UserInfoRepo userInfoRepo;
	
	@Autowired
	ProductInfoRepo productInfoRepo;
	
	@Autowired
	ProductStockRepo productStockRepo;
	
	@Autowired
	ProductRequestMstRepo productRequestMstRepo;
	
	@Autowired
	ProductRequestDtlRepo productRequestDtlRepo;
	
	@Autowired
	ProductPriceRepo productPriceRepo;
	
	@Autowired
	CustomerInfoRepo customerInfoRepo;
	
	@Autowired
	MenuHeadRepo menuHeadRepo;
	
	@Autowired
	Sub_MenuRepo sub_MenuRepo;
	
	@Autowired
	MenuConfirureRepo menuConfirureRepo;
	
	@Autowired
	RoleConfigureRepo roleConfigureRepo;
	
	
	//---------------------------POST MAPPING for Sign UP -------------------------
	
	@PostMapping("/signUp")
	BaseResponse signUp(@Valid @RequestBody SignUpModel request) {			
		return adminService.signUp(request);
	}
	
	//==================================================================
	
	       //------- raw data
	 
//	@PostMapping("/userSignUp")
//	BaseResponse userInfo(@RequestBody UserInfoModel request) {			
//		return adminService.userInfo(request);
//	}
	
	      // --------form data
	
//	@PostMapping("/userSignUp")
//	BaseResponse userInfo(@RequestParam String request, @RequestParam(required = false, name="uploadImage") MultipartFile file,@RequestParam(required = false, name="uploadNid")MultipartFile file2) throws JsonMappingException, JsonProcessingException {
//
//        ObjectMapper mapper= new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        UserInfoModel userInfoModel=mapper.readValue(request, UserInfoModel.class);
//        
//		return adminService.userInfo(userInfoModel,file,file2);
//	}
	
	//====================================================================
	
	
	@PostMapping("/addProduct")
	BaseResponse productInfo(@RequestBody ProductInfoRequestModel request) {			
		return adminService.productInfo(request);
	}
	
	
	@GetMapping("/viewSignUpInfoAll")
	BaseResponse viewSignUpInfoAll() {
		return adminService.viewSignUpInfoAll();
	}
	
	
	@GetMapping("/viewProductInfoAll")
	BaseResponse viewProductInfoAll() {
		return adminService.viewProductInfoAll();
	}
	
	@GetMapping("/viewProductInfoListAll")
	BaseResponse viewProductInfoListAll() {
		return adminService.viewProductInfoListAll();
	}
	
	@GetMapping("/viewUserProductInfo")
	BaseResponse viewUserProductInfo() {
		return adminService.viewUserProductInfo();
	}
	
	
	//-------------------------- GET MAPPING for Sign Up (All) Data ------------------------
	
	@GetMapping("/getSignUpInfo")
	BaseResponse getSignUpInfoAll() {
		return adminService.getSignUpInfoAll();
	}
	
	//-------------------------- GET MAPPING for Sign Up (Search By ID) Data ------------------------
	
		@GetMapping("/getSignUpInfoById/{id}")
		BaseResponse getSignUpInfoById(@PathVariable long id) {
			return adminService.getSignUpInfoById((long) id); 
		}
		   
		//-------------------------- GET MAPPING for Sign Up (Search By Name) Data ------------------------
				
		
		@GetMapping("/getSignUpInfoByName/{empFirstname}")
		BaseResponse getSignUpInfoByName(@PathVariable("empFirstname") String empFirstname) {
			return adminService.getUserNameByName(empFirstname);
		}
		
		@GetMapping("/getSignUpPasswordByName/{userName}")
		String getSignUpPasswordByName(@PathVariable("userName") String userName) {
			return adminService.getUserPasswordByName(userName);
		}
	
		
		//---------------------------POST MAPPING for Role Entry -------------------------
		@PostMapping("/role")
		String role(@Valid @RequestBody RoleModel request) {		
		return adminService.role(request); 
		}
		
		//-------------------------- GET MAPPING for Role Info (All) Data ------------------------
		
		@GetMapping("/getRoleInfoAll")
		BaseResponse getRoleInfoAll() {
			return adminService.getRoleInfoAll();
		}
		
		//-------------------------- GET MAPPING for Role Info (Search by ID) Data ------------------------
		
		@GetMapping("/getRoleInfoById/{id}")
		BaseResponse getRoleInfoById(@PathVariable long id) {
			return adminService.getRoleInfoById((long) id);
		} 
		
		
		//------------------ Delete Sign Data  ---------------------
		
		@DeleteMapping("/deleteSignUp/{id}")
        public String deleteSignUp(@PathVariable long id) {
			return adminService.deleteSignUp(id);
		}
		
		// ----------------- Delete Role data -------------------------
		
		@DeleteMapping("/deleteRole/{id}")
		public String deleteRole(@PathVariable long id) {
			return adminService.deleteRole(id);
		}
		
		// ------------------ update Sign Up data-------------
		
		@PutMapping("/updateSignUp")
		public String updateSignUp(@RequestBody SignUp request) {
			return adminService.updateSignUp(request);
		}
		
		// ------------------Update Role Data -------------
		
		@PutMapping("/updateRole")
		public String updateRole(@RequestBody Role request) {
			return adminService.updateRole(request);
		}
		
		@PostMapping("/updateRoleInfo")
		BaseResponse updateRoleInfo (@RequestBody UpdateRoleInfoModel request) {
			return adminService.updateRoleInfo(request);
		}

        //---------------- Login API ------------------------
		
		@PostMapping("/login")
		public String userInfo (@RequestBody UserInfo request) {
			return adminService.login(request);
		}
		
		
		//-------------------- Update user info table -------------------------
		
		@PostMapping("/updateUserInfo")
		BaseResponse updateUserInfo (@RequestBody UpdateUserBodyModel request) {
			return adminService.updateUserInfo(request);
		}
		
		
		//------------------ Delete user info table ------------------------
		
		@PostMapping("/deleteUserInfo/{userName}")
		BaseResponse deleteUserInfo (@PathVariable String userName) {
			return adminService.deleteUserInfo(userName);
		}
		
		
		//-------------------------- Insert productrequestmst and productrequestdtl table 
		@PostMapping("/addProductMstDtl")
		BaseResponse addProductMstDtl(@RequestBody ProductMstDtlRequestModel request) {			
			return adminService.addProductMstDtl(request);
		}
		
		@GetMapping("/viewProductRequestMstDtlAll")
		BaseResponse viewProductRequestMstDtlAll() {
			return adminService.viewProductRequestMstDtlAll();
		}
		
		// ------------------------ Insert product price --------------------------------
		
		@PostMapping("/addProductPrice")
		BaseResponse addProductPrice(@RequestBody ProductPriceRequestModel request) {
			return adminService.addProductPrice(request);
		}
		
		// ----------------- Insert Customer Info ---------------------------------
		
		@PostMapping("/addCustomer")
		BaseResponse addCustome(@RequestBody CustomerInfoModel request) {
			return adminService.addCustomer(request);
		}
		
		
		@PostMapping("/addMenuHead")
		BaseResponse addMenuHead(@RequestBody MenuHeadModel request) {
			return adminService.addMenuHead(request);
		}
		
		
		@GetMapping("/getMenuHeadAll")
		BaseResponse getMenuHeadAll() {
			return adminService.getMenuHeadAll();
		}
		
		@DeleteMapping("/deleteMenuHead/{id}")
		public String deleteMenuHead(@PathVariable long id) {
			return adminService.deleteMenuHead(id);
		}
		
		@PostMapping("/updateMenuHead")
		BaseResponse updateMenuHead (@RequestBody UpdateMenuHeadModel request) {
			return adminService.updateMenuHead(request);
		}

		
			
		@PostMapping("/addSubMenuHead")
		BaseResponse addSubMenuHead(@RequestBody Sub_MenuModel request) {
			return adminService.addSubMenuHead(request);
		}
		
		
		//------------- Sub Menu Search Start -------------------------------------
		
		@GetMapping("/getSubMenuHeadAll")
		BaseResponse getSubMenuHeadAll() {
			return adminService.getSubMenuHeadAll();
		}
		
		@GetMapping("/getSubMenuAllInfo")
		List<SubMenuAllModel> findSubMenuAllModels (){
			return adminService.findSubMenuAllModels ();
		}
		
		
		// ---------------- Sub Menu Search End ------------------------------------- 
		
		@PostMapping("/addMenuConfigure")
		BaseResponse addMenuConfigure(@RequestBody MenuConfigureModel request) {
			return adminService.addMenuConfigure(request);
		}
		
		@PostMapping("/addRoleConfigure")
		BaseResponse addRoleConfigure(@RequestBody RoleConfigureModel request) {
			return adminService.addRoleConfigure(request);
		}
		
		
		@GetMapping("/findAllBySubId")
		BaseResponse findAllBySubId() {
			return adminService.findAllBySubId();
		}
				
		
//		 @PostMapping("/send")
//		    public BaseResponse sendEmail(
//		    		@RequestParam List<String> toEmail,
//		    		@RequestParam String subjects,
//		    		@RequestParam String body
//		    		) {	
//		    			    	
//		    	return adminService.sendEmail(toEmail, subjects, body);
//
//		    }
		
		@PostMapping("/send")
		public BaseResponse sendEmail(
		        @RequestParam List<String> toEmail,
		        @RequestParam String subjects,
		        @RequestParam String body
		) {
		    // Call the method in AdminService to send the email immediately
		    return adminService.sendEmail(toEmail, subjects, body);
		}

		@PostMapping("/schedule")
		public BaseResponse scheduleEmail(
		        @RequestParam List<String> toEmail,
		        @RequestParam String subjects,
		        @RequestParam String body,
		        @RequestParam String scheduledDateTime
		) {
		    LocalDateTime scheduledDateTimeObj = LocalDateTime.parse(scheduledDateTime);
		    
		    return adminService.sendScheduledEmail(toEmail, subjects, body, scheduledDateTimeObj);
		}

		 

		
				
		
}

