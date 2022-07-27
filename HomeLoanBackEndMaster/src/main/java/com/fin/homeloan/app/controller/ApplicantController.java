package com.fin.homeloan.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fin.homeloan.app.model.Applicant;
import com.fin.homeloan.app.model.Customer;
import com.fin.homeloan.app.servicei.ApplicantService;
import com.fin.homeloan.app.servicei.CustomerService;

@CrossOrigin("*")
@RestController
public class ApplicantController {

	
	@Autowired
	ApplicantService servi;
	
	@Autowired
	CustomerService servicec;
	
  @PostMapping(value="/registerApplicant/{customerId}")
  public void saveApplicant(@RequestBody Applicant a, @PathVariable int customerId) {
	
		Customer c =servicec.get(customerId);
        a.setCustomer(c);
        servi.saveApplicant(a);
  }
  @PostMapping(value="/reg")
  public String save(@RequestBody Applicant a) {
	  servi.saveApplicant(a);
	return "done";
	  
  }

	
}
