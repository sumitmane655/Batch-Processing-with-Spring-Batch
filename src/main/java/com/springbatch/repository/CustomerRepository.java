package com.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbatch.entity.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer> {

}
