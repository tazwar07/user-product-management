package com.tu.mnagement.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tu.mnagement.entity.CustomerInfo;
import com.tu.mnagement.entity.MenuConfigure;
import com.tu.mnagement.entity.MenuHead;
import com.tu.mnagement.entity.ProductInfo;
import com.tu.mnagement.entity.ProductPrice;
import com.tu.mnagement.entity.ProductRequestDtl;
import com.tu.mnagement.entity.ProductRequestMst;
import com.tu.mnagement.entity.ProductStock;
import com.tu.mnagement.entity.Role;
import com.tu.mnagement.entity.RoleConfigure;
import com.tu.mnagement.entity.SignUp;
import com.tu.mnagement.entity.Sub_Menu;
import com.tu.mnagement.entity.UserInfo;
import com.tu.mnagement.model.CustomerInfoModel;
import com.tu.mnagement.model.EmailService;
import com.tu.mnagement.model.MenuConfigureModel;
import com.tu.mnagement.model.MenuHeadModel;
import com.tu.mnagement.model.ProductInfoRequestModel;
import com.tu.mnagement.model.ProductMstDtlRequestModel;
import com.tu.mnagement.model.ProductPriceRequestModel;
import com.tu.mnagement.model.RoleConfigureModel;
import com.tu.mnagement.model.RoleModel;
import com.tu.mnagement.model.SendScheduledEmail;
import com.tu.mnagement.model.SignUpModel;
import com.tu.mnagement.model.SubMenuAllInterfaceModel;
import com.tu.mnagement.model.SubMenuAllModel;
import com.tu.mnagement.model.Sub_MenuModel;
import com.tu.mnagement.model.UpdateMenuHeadModel;
import com.tu.mnagement.model.UpdateRoleInfoModel;
import com.tu.mnagement.model.UpdateUserBodyModel;
import com.tu.mnagement.model.UserInfoModel;
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

@Service
public class AdminServiceImpl implements AdminService {

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

	@Autowired
	EmailService emailService;

	@Autowired
	SendScheduledEmail sendScheduledEmail;

	// SignUpModel insert statement Start ------------------------------------

	@Override
	public BaseResponse signUp(SignUpModel request) {

		BaseResponse response = new BaseResponse();

		if (request != null) {

			if (signUpRepo.findByMail(request.getMail()) != null) {
				throw new RuntimeException("Email Address Alredy Exists !");
			}
			if (signUpRepo.findByUserName(request.getUserName()) != null) {
				throw new RuntimeException("User Name Alredy Exists !");
			}

			try {

				SignUp obj = signUpModelToTestEmpEntity(request);
				if (obj.getUserName().equals(null)) {
					response.setMessage("User Name Not Null !");
					return response;
				} else {

					signUpRepo.save(obj);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			response.setMessage("Sign up added Succesfully");
			return response;

		} else {
			response.setMessage("Sign up added Succesfully");
			return response;
		}
	}

	public SignUp signUpModelToTestEmpEntity(SignUpModel request) {

		SignUp signUp = new SignUp();
		BeanUtils.copyProperties(request, signUp);
		return signUp;
	}

	// SignUpModel insert statement End ------------------------------------

	// ==================== User Info Table data Insert Statement Start
	// ==========================

	@Override
	public BaseResponse userInfo(UserInfoModel request, MultipartFile file, MultipartFile file2) {
		BaseResponse respose = new BaseResponse();
		if (request != null) {

			try {

				UserInfo obj = signUpModelToTestEmpEntity(request);
				obj.setEmployeeId(customID()); // ---------- Custom Employee No save
				obj.setImageFilePath(obj.getEmployeeId() + "_" + StringUtils.cleanPath(file.getOriginalFilename())); // ----Image
																														// Name
																														// Save
				obj.setNidFilePath(obj.getEmployeeId() + "_" + StringUtils.cleanPath(file2.getOriginalFilename())); // ----NID
																													// Name
																													// Save

				userInfoRepo.save(obj);
				saveToPath(file, request);
				saveToPath2(file2, request);

				// System.out.println(obj);

				respose.setMessage("Sign Up Successfully");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return respose;

		} else {
			respose.setMessage("Sign Up Not Successfully");
			return respose;
		}
	}

	// ------------- Customer Employee ID Generate -----------------------

	public String customID() {

		String id = null;

		LocalDate currentDate = LocalDate.now();
		String yearMonth = currentDate.getYear() + String.format("%02d", currentDate.getMonthValue());
		Long count = userInfoRepo.findMaxEmployeeIDyYearMonth(yearMonth);
		id = yearMonth + String.format("%04d", count);

		return id;
	}

	public UserInfo signUpModelToTestEmpEntity(UserInfoModel request) {

		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(request, userInfo);
		return userInfo;
	}

	// ---------- file save statement --------------------------

	public void saveToPath(MultipartFile file, UserInfoModel request) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		Path fileStorageLocation = Paths.get("G:\\SpringBootSoft\\management\\file\\image").toAbsolutePath()
				.normalize();

		Path targetLocation = fileStorageLocation.resolve(request.getUserName() + "_" + fileName);

		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Dirrectory is not empty");
		}

	}

	public void saveToPath2(MultipartFile file2, UserInfoModel request) {

		String fileName = StringUtils.cleanPath(file2.getOriginalFilename());

		Path fileStorageLocation = Paths.get("G:\\SpringBootSoft\\management\\file\\nid").toAbsolutePath().normalize();

		Path targetLocation = fileStorageLocation.resolve(request.getUserName() + "_" + fileName);
		try (InputStream inputStream = file2.getInputStream()) {
			Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Dirrectory is not empty");
		}

	}

	// =====================User Info Table data Insert Statement End
	// ===============================

	@Override
	public String role(RoleModel request) {

		if (request != null) {
			try {
				Role obj = roleModelToTestEmpEntity(request);

				roleRepo.save(obj);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return "Success";

		} else {
			return "not success";
		}

	}

	public Role roleModelToTestEmpEntity(RoleModel request) {

		Role role = new Role();
		BeanUtils.copyProperties(request, role);
		return role;
	}

	@Override
	public BaseResponse getUserNameByName(String user_name) {
		BaseResponse response = new BaseResponse();
		// List<SignUp> signUp=new ArrayList<>();

		try {
			response.setSignups(signUpRepo.findByEmpFirstName(user_name));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

	@Override
	public String getUserPasswordByName(String userName) {
		String signUp = null;
		try {
			signUp = signUpRepo.findByUserName(userName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return signUp;
	}

	@Override
	public String deleteSignUp(long id) {

		try {
			signUpRepo.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Successfully Deleted.";
	}

	@Override
	public String deleteRole(long id) {
		try {
			roleRepo.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Successfully Deleted.";
	}

	@Override
	public String updateSignUp(SignUp request) {
		if (request != null) {
			try {
				signUpRepo.save(request);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return "Successfully Updated";

		} else {
			return "Not Successfully Updated";
		}
	}

	@Override
	public String updateRole(Role request) {
		if (request != null) {
			try {
				roleRepo.save(request);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return "Successfully Updated.";
		} else {

			return " Not Successfully Updated.";
		}

	}

// ---------------- product info start -------------------------------

	@Override
	public BaseResponse productInfo(ProductInfoRequestModel request) {

		BaseResponse respose = new BaseResponse();

		if (request != null) {

			try {

				ProductInfo obj = productInfoModelToTestEmpEntity(request);

				if (countProductType(request.getProductType()) == 0) {
					obj.setSubId(subId());
					productInfoRepo.save(obj);
					respose.setMessage("Product add Successfully");
				} else {
					obj.setSubId(ProductTypeSubId(request.getProductType()));
					productInfoRepo.save(obj);
					respose.setMessage("Product add Successfully");
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return respose;

		} else {
			respose.setMessage("Product Add not Successfully");
			return respose;
		}
	}

	public ProductInfo productInfoModelToTestEmpEntity(ProductInfoRequestModel request) {

		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(request, productInfo);

		ProductStock productStck = new ProductStock();
		BeanUtils.copyProperties(request, productStck);

		productStck.setProductInfo(productInfo);
		productInfo.setProductStock(productStck);

		return productInfo;
	}

//-------------- product info end ----------------------

	public Long subId() {

		Long id = null;
		id = productInfoRepo.findSubId();
		return id;
	}

	public Long countProductType(String productType) {

		Long count = null;
		count = productInfoRepo.findCountProductType(productType);
		return count;
	}

	public Long ProductTypeSubId(String productType) {

		Long count = null;
		count = productInfoRepo.findProductTypeSubId(productType);
		return count;
	}

	@Override
	public BaseResponse viewSignUpInfoAll() {
		BaseResponse response = new BaseResponse();
		try {
			response.setUserInfo(userInfoRepo.findAll());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	@Override
	public BaseResponse viewProductInfoAll() {
		BaseResponse response = new BaseResponse();
		try {
			response.setProductInfo(productInfoRepo.findAll());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	@Override
	public BaseResponse getSignUpInfoAll() {
		BaseResponse response = new BaseResponse();
		try {
			response.setSignups(signUpRepo.findAll());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	@Override
	public BaseResponse getSignUpInfoById(long id) {
		BaseResponse response = new BaseResponse();
		try {
			response.setSignUpOptional(signUpRepo.findById((long) id));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

	@Override
	public BaseResponse getRoleInfoAll() {
		BaseResponse response = new BaseResponse();
		try {
			response.setRoles(roleRepo.findAll());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

	@Override
	public BaseResponse getRoleInfoById(long id) {
		BaseResponse response = new BaseResponse();
		try {
			response.setRoleOptional(roleRepo.findById((long) id));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

	@Override
	public BaseResponse viewUserProductInfo() {
		BaseResponse response = new BaseResponse();
		try {
			response.setUserInfo(userInfoRepo.findAll());
			response.setProductInfo(productInfoRepo.findAll());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

//------------------------ Login Statement --------------------------

	@Override
	public String login(UserInfo request) {

		if (userInfoRepo.existsByMailAndPassword(request.getMail(), request.getPassword())) {
			return "Login Success";
		}

		else if (userInfoRepo.existsByPhoneAndPassword(request.getPhone(), request.getPassword())) {
			return "Login Success";
		}

		else if (userInfoRepo.existsByUserNameAndPassword(request.getUserName(), request.getPassword())) {
			return "Login Success";
		}

		else {
			return "Login Fail";
		}

	}

	@Override
	public BaseResponse updateUserInfo(UpdateUserBodyModel request) {
		BaseResponse response = new BaseResponse();
		try {
			userInfoRepo.updateUserInfo(request.getUserName(), request.getName());
			response.setMessage("update Successfully");
		} catch (Exception e) {
			response.setMessage("update not Successfully");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public BaseResponse deleteUserInfo(String userName) {
		BaseResponse response = new BaseResponse();
		try {
			userInfoRepo.deleteUserInfo(userName);
			response.setMessage("Delete Successfully");
		} catch (Exception e) {
			response.setMessage("Delete not Successfully");
			e.printStackTrace();
		}
		return response;
	}

// ------------------- Insert productrequestmst and productrequestdtl-------------------------
	@Override
	public BaseResponse addProductMstDtl(ProductMstDtlRequestModel request) {

		BaseResponse respose = new BaseResponse();

		if (request != null) {

			try {

				ProductRequestMst obj = productRequestMstModelToEntity(request);

				productRequestMstRepo.save(obj);
				respose.setMessage("Data add Successfully");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return respose;

		} else {
			respose.setMessage("Data Add not Successfully");
			return respose;
		}
	}

	public ProductRequestMst productRequestMstModelToEntity(ProductMstDtlRequestModel request) {

		ProductRequestMst productRequestMst = new ProductRequestMst();
		BeanUtils.copyProperties(request, productRequestMst);

		String productStrings = request.getProductId();
		String qtyStrings = request.getRequestQty();

		String[] productArray = productStrings.split(",");
		String[] qtyArray = qtyStrings.split(",");

		List<ProductRequestDtl> mList = new ArrayList<>();

		for (int j = 0; j < productArray.length; j++) {

			int productId = Integer.parseInt(productArray[j]);
			int qty = Integer.parseInt(qtyArray[j]);

			ProductRequestDtl productRequestDtl = new ProductRequestDtl();

			productRequestDtl.setProductId(productId);
			productRequestDtl.setRequestQty(qty);

			mList.add(productRequestDtl);

			productRequestDtl.setProductRequestMst(productRequestMst);

		}
		productRequestMst.setProductRequestDtls(mList);

		return productRequestMst;
	}

	@Override
	public BaseResponse viewProductRequestMstDtlAll() {
		BaseResponse response = new BaseResponse();
		try {
			response.setProductRequestMst(productRequestMstRepo.findAll());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

// --------------------------- add product price --------------------------------

	@Override
	public BaseResponse addProductPrice(ProductPriceRequestModel request) {
		BaseResponse respose = new BaseResponse();

		if (request != null) {

			try {

				List<ProductPrice> obj = productPriceModelToEntity(request);

				productPriceRepo.saveAll(obj);
				respose.setMessage("Price add Successfully");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return respose;

		} else {
			respose.setMessage("Price Add not Successfully");
			return respose;
		}
	}

	public List<ProductPrice> productPriceModelToEntity(ProductPriceRequestModel request) {

		String productString = request.getProductId();
		String mrpString = request.getMrp();
		String tpString = request.getTp();
		String vatString = request.getVat();

		String[] productArray = productString.split(",");
		String[] mrpArray = mrpString.split(",");
		String[] tpArray = tpString.split(",");
		String[] vatArray = vatString.split(",");

		List<ProductPrice> productPrices = new ArrayList<>();

		for (int j = 0; j < productArray.length; j++) {

			int productId = Integer.parseInt(productArray[j]);
			Double mrp = Double.parseDouble(mrpArray[j]);
			Double tp = Double.parseDouble(tpArray[j]);
			Double vat = Double.parseDouble(vatArray[j]);

			ProductPrice productPrice = new ProductPrice();

			productPrice.setProductId(productId);
			productPrice.setMrp(mrp);
			productPrice.setTp(tp);
			productPrice.setVat(vat);

			productPrices.add(productPrice);

		}

		return productPrices;

	}

	@Override
	public BaseResponse addCustomer(CustomerInfoModel request) {
		BaseResponse respose = new BaseResponse();

		if (request != null) {

			try {

				List<CustomerInfo> obj = customerInfoToEntity(request);

				customerInfoRepo.saveAll(obj);
				respose.setMessage("Customer add Successfully");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return respose;

		} else {
			respose.setMessage("Customer Add not Successfully");
			return respose;
		}
	}

	public List<CustomerInfo> customerInfoToEntity(CustomerInfoModel request) {

		String cutomerString = request.getCustomerName();
		String addressString = request.getAddress();
		String phoneString = request.getPhone();
		String mailString = request.getMail();
		String statuString = request.getStatus();

		String[] customerArray = cutomerString.split(",");
		String[] addressArray = addressString.split(",");
		String[] phoneArray = phoneString.split(",");
		String[] mailArray = mailString.split(",");
		String[] statusArray = statuString.split(",");

		List<CustomerInfo> mList = new ArrayList<>();

		for (int j = 0; j < customerArray.length; j++) {

			String customer = customerArray[j];
			String phone = phoneArray[j];
			String address = addressArray[j];
			String mail = mailArray[j];
			String status = statusArray[j];

			CustomerInfo customerInfo = new CustomerInfo();

			customerInfo.setCustomerName(customer);
			customerInfo.setAddress(address);
			customerInfo.setPhone(phone);
			customerInfo.setMail(mail);
			customerInfo.setStatus(status);

			mList.add(customerInfo);

		}

		return mList;
	}

	@Override
	public BaseResponse userRegistration(UserInfoModel request) {
		BaseResponse response = new BaseResponse();

		if (request != null) {
			try {
				UserInfo obj = userInfoModelToTestEmpEntity(request);
				userInfoRepo.save(obj);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			response.setMessage("Sign up added Succesfully");
			return response;

		} else {
			response.setMessage("Sign up not added Succesfully");
			return response;
		}
	}

	public UserInfo userInfoModelToTestEmpEntity(UserInfoModel request) {

		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(request, userInfo);
		return userInfo;
	}

	@Override
	public BaseResponse addMenuHead(MenuHeadModel request) {
		BaseResponse response = new BaseResponse();
		if (request != null) {
			try {
				MenuHead obj = menuHeadModelToEntity(request);
				menuHeadRepo.save(obj);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			response.setMessage("Done");
			return response;

		} else {
			response.setMessage("Not Done");
			return response;
		}

	}

	public MenuHead menuHeadModelToEntity(MenuHeadModel request) {

		MenuHead menuHead = new MenuHead();
		BeanUtils.copyProperties(request, menuHead);
		return menuHead;
	}

	@Override
	public BaseResponse addSubMenuHead(Sub_MenuModel request) {
		BaseResponse response = new BaseResponse();
		if (request != null) {
			try {
				Sub_Menu obj = subMenuHeadModelToEntity(request);
				sub_MenuRepo.save(obj);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			response.setMessage("Done");
			return response;

		} else {
			response.setMessage("Not Done");
			return response;
		}
	}

	public Sub_Menu subMenuHeadModelToEntity(Sub_MenuModel request) {

		Sub_Menu sub_Menu = new Sub_Menu();
		BeanUtils.copyProperties(request, sub_Menu);
		return sub_Menu;
	}

	@Override
	public BaseResponse getMenuHeadAll() {
		BaseResponse response = new BaseResponse();
		try {
			response.setMenuHead(menuHeadRepo.findAll());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

	@Override
	public BaseResponse addMenuConfigure(MenuConfigureModel request) {
		BaseResponse response = new BaseResponse();
		if (request != null) {
			try {
				MenuConfigure obj = menuConfigureModelToEntity(request);
				menuConfirureRepo.save(obj);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			response.setMessage("Done");
			return response;

		} else {
			response.setMessage("Not Done");
			return response;
		}
	}

	public MenuConfigure menuConfigureModelToEntity(MenuConfigureModel request) {

		MenuConfigure menuConfigure = new MenuConfigure();
		BeanUtils.copyProperties(request, menuConfigure);
		return menuConfigure;
	}

	@Override
	public BaseResponse addRoleConfigure(RoleConfigureModel request) {
		BaseResponse response = new BaseResponse();
		if (request != null) {
			try {
				RoleConfigure obj = roleConfigureModelToEntity(request);
				roleConfigureRepo.save(obj);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			response.setMessage("Done");
			return response;

		} else {
			response.setMessage("Not Done");
			return response;
		}
	}

	public RoleConfigure roleConfigureModelToEntity(RoleConfigureModel request) {

		RoleConfigure roleConfigure = new RoleConfigure();
		BeanUtils.copyProperties(request, roleConfigure);
		return roleConfigure;
	}

	@Override
	public String deleteMenuHead(long id) {
		try {
			menuHeadRepo.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Successfully Deleted.";
	}

	@Override
	@Transactional
	public BaseResponse updateRoleInfo(UpdateRoleInfoModel request) {
		BaseResponse response = new BaseResponse();
		try {
			System.out.println(request);
			roleRepo.updateRoleInfo(request.getRoleName(), request.getDescription(), request.getStatus(),
					request.getId());
			response.setMessage("update Successfully");
		} catch (Exception e) {

			response.setMessage("update not Successfully");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	@Transactional
	public BaseResponse updateMenuHead(UpdateMenuHeadModel request) {
		BaseResponse response = new BaseResponse();
		try {
			System.out.println(request);
			menuHeadRepo.updateRoleInfo(request.getMhName(), request.getMhSeq(), request.getStatus(), request.getId());
			response.setMessage("update Successfully");
		} catch (Exception e) {

			response.setMessage("update not Successfully");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public BaseResponse getSubMenuHeadAll() {
		BaseResponse response = new BaseResponse();
		try {
			response.setSub_Menu(sub_MenuRepo.findAll());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;

	}

	@Override
	public BaseResponse viewProductInfoListAll() {

//    BaseResponse response = new BaseResponse();
//    try {
//        List<ProductInfo> productInfoList = productInfoRepo.findAll();
//
//        Map<Long, List<ProductInfo>> resultMap = new HashMap<>();
//        resultMap.put("productInfoList", productInfoList);
//
//        response.setData(resultMap);
//    } catch (Exception ex) {
//        ex.printStackTrace();
//    }
//    return response;

		return null;

	}

	@Override
	public List<SubMenuAllModel> findSubMenuAllModels() {
		List<SubMenuAllModel> subMenuAllModel = new ArrayList<>();
		try {

			List<SubMenuAllInterfaceModel> obj = sub_MenuRepo.findSubMenuInformation();

			System.out.println(obj.get(1).getSmName());

			for (SubMenuAllInterfaceModel newObj : obj) {

				SubMenuAllModel subMenuAllModels = new SubMenuAllModel();

				subMenuAllModels.setMhId(newObj.getMhId());
				subMenuAllModels.setMhName(newObj.getMhName());
				subMenuAllModels.setSmId(newObj.getSmId());
				subMenuAllModels.setSmName(newObj.getSmName());
				subMenuAllModels.setSmSeq(newObj.getSmSeq());
				subMenuAllModels.setStatus(newObj.getStatus());
				subMenuAllModels.setUrl(newObj.getUrl());

				subMenuAllModel.add(subMenuAllModels);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return subMenuAllModel;
	}

	@Override
	public BaseResponse findAllBySubId() {
		BaseResponse response = new BaseResponse();
		List<ProductInfo> productList = productInfoRepo.findAll();

		Map<Long, List<ProductInfo>> productBySubId = productList.stream()
				.collect(Collectors.groupingBy(ProductInfo::getSubId));

		// Display Response
		productBySubId.forEach((pSubId, products) -> {
			response.setProducts(productBySubId);
		});

		return response;
	}

	@Override
	public BaseResponse sendEmail(List<String> toEmail, String subjects, String body) {

		BaseResponse response = new BaseResponse();

		try {

			Runnable runnable = () -> {

				emailService.sendEmail(toEmail, subjects, body);

			};

			Thread thread = new Thread(runnable);
			thread.start();

			response.setMessage("Email sent successfully!");

		} catch (Exception ex) {
			response.setMessage(ex.getMessage());
		}

		return response;

	}

//	@Override
//	public BaseResponse sendScheduledEmail(List<String> toEmail, String subjects, String body,
//			LocalDateTime scheduledDateTimeObj) {
//		BaseResponse response = new BaseResponse();
//		
//		System.out.println(scheduledDateTimeObj);
//	       
//		try {
//			
//			Runnable runnable = () -> {
//				
//				sendScheduledEmail.sendScheduledEmail(toEmail,subjects,body,scheduledDateTimeObj);
//				
//			};
//			
//			Thread thread = new Thread(runnable);
//			thread.start();
//			
//			response.setMessage("Email sent successfully!");
//			
//		}catch(Exception ex) {
//			response.setMessage(ex.getMessage());
//		}
//	    
//	    return response;
//	}

	@Override
	public BaseResponse sendScheduledEmail(List<String> toEmail, String subjects, String body,
			LocalDateTime scheduledDateTimeObj) {
		BaseResponse response = new BaseResponse();

		System.out.println(scheduledDateTimeObj);

		try {

			sendScheduledEmail.sendScheduledEmail(toEmail, subjects, body, scheduledDateTimeObj);

			response.setMessage("Email sent successfully!");

		} catch (Exception ex) {
			response.setMessage(ex.getMessage());
		}

		return response;
	}

}