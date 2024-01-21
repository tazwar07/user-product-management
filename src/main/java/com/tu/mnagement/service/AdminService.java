package com.tu.mnagement.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import com.tu.mnagement.model.UserInfoModel;
import com.tu.mnagement.util.BaseResponse;

@Service
public interface AdminService {
	
	BaseResponse signUp(SignUpModel request);
	
	BaseResponse userInfo(UserInfoModel request,MultipartFile file,MultipartFile file2);

	String role(RoleModel request);
	
    BaseResponse getUserNameByName (String user_name);

	String getUserPasswordByName(String userName);

	String deleteSignUp(long id);

	String deleteRole(long id);

	String updateSignUp(SignUp request);

	String updateRole(Role request);

	BaseResponse productInfo(ProductInfoRequestModel request);

	BaseResponse viewSignUpInfoAll();

	BaseResponse viewProductInfoAll();

	BaseResponse getSignUpInfoAll();

	BaseResponse getSignUpInfoById(long id);

	BaseResponse getRoleInfoAll();

	BaseResponse getRoleInfoById(long id);

	BaseResponse viewUserProductInfo();

	String login(UserInfo request);

	BaseResponse updateUserInfo(UpdateUserBodyModel request);

	BaseResponse deleteUserInfo(String userName);

	BaseResponse addProductMstDtl(ProductMstDtlRequestModel request);

	BaseResponse viewProductRequestMstDtlAll();

	BaseResponse addProductPrice(ProductPriceRequestModel request);

	BaseResponse addCustomer(CustomerInfoModel request);

	BaseResponse userRegistration(UserInfoModel request);

	BaseResponse addMenuHead(MenuHeadModel request);

	BaseResponse addSubMenuHead(Sub_MenuModel request);

	BaseResponse getMenuHeadAll();

	BaseResponse addMenuConfigure(MenuConfigureModel request);

	BaseResponse addRoleConfigure(RoleConfigureModel request);

	String deleteMenuHead(long id);

	BaseResponse updateRoleInfo(UpdateRoleInfoModel request);

	BaseResponse updateMenuHead(UpdateMenuHeadModel request);

	BaseResponse getSubMenuHeadAll();

	BaseResponse viewProductInfoListAll();

	List<SubMenuAllModel> findSubMenuAllModels();

	BaseResponse findAllBySubId();

	BaseResponse sendEmail(List<String> toEmail, String subjects, String body);

	BaseResponse sendScheduledEmail(List<String> toEmail, String subjects, String body,
			LocalDateTime scheduledDateTimeObj);
	
}