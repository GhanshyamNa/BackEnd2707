package com.fin.homeloan.app.servicei;

import java.util.List;

import com.fin.homeloan.app.model.Customer;

public interface CustomerService {

	Customer get(int customerId);
	
	void saveCustomer(Customer cus);

	List<Customer> getall();



}
