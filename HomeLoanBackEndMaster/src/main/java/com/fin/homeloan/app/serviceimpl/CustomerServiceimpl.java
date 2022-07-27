package com.fin.homeloan.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.homeloan.app.model.Customer;
import com.fin.homeloan.app.repository.CustomerRepository;
import com.fin.homeloan.app.servicei.CustomerService;

@Service
public class CustomerServiceimpl implements CustomerService {

	@Autowired
	CustomerRepository repository;
	
	@Override
	public void saveCustomer(Customer cus) {
		repository.save(cus);
	}

	@Override
	public Customer get(int customerId) {
		
		return repository.findByCustomerId(customerId);
	}

	@Override
	public List<Customer> getall() {
	
		return repository.findAll();
	}

}
