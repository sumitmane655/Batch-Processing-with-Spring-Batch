package com.springbatch.config;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.entity.CustomerDetails;

public class CustomerProcessor implements ItemProcessor<CustomerDetails, CustomerDetails> {

	@Override
	public CustomerDetails process(CustomerDetails customerDetails) throws Exception {
		// TODO Auto-generated method stub
		return customerDetails;
	}

}
