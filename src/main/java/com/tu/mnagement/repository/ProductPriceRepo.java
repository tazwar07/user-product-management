package com.tu.mnagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tu.mnagement.entity.ProductPrice;

public interface ProductPriceRepo extends JpaRepository<ProductPrice, Long> {
	

}
