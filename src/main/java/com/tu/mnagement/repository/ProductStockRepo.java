package com.tu.mnagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tu.mnagement.entity.ProductStock;

public interface ProductStockRepo extends JpaRepository<ProductStock, Long> {

}
