package com.tu.mnagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tu.mnagement.entity.ProductInfo;

public interface ProductInfoRepo extends JpaRepository<ProductInfo, Long>{
	
	@Query(value="SELECT NVL(MAX(SUB_ID),0)+1 FROM PRODUCT_INFO",nativeQuery = true) 
    Long findSubId();
	
	@Query(value="SELECT count(*) FROM PRODUCT_INFO WHERE PRODUCT_TYPE=:productType",nativeQuery = true) 
    Long findCountProductType(String productType);
	
	@Query(value="SELECT SUB_ID FROM PRODUCT_INFO WHERE PRODUCT_TYPE=:productType",nativeQuery = true) 
    Long findProductTypeSubId(String productType);

}