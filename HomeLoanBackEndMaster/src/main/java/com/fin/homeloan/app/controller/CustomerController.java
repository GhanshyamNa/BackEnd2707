package com.fin.homeloan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.homeloan.app.model.Customer;
import com.fin.homeloan.app.servicei.CustomerService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	CustomerService service;
	
	@PostMapping("/registerCustomer")
	  public String saveCustomer(@RequestBody Customer cus) {
		  
		  service.saveCustomer(cus);
		  return"Done c";
	  }
	@GetMapping("/get/{customerId}")
	  public Customer get(@PathVariable int customerId )
	  {
		  Customer c =service.get(customerId);
		  return c;
	  }
	@GetMapping("/getall")
	public List<Customer>getall()
	{
	  return service.getall();
	}

}
