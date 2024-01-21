package com.tu.mnagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tu.mnagement.entity.CustomerInfo;

public interface CustomerInfoRepo extends JpaRepository<CustomerInfo, Long> {

}
