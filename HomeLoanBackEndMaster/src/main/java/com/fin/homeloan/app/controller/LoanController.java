package com.fin.homeloan.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.homeloan.app.model.LoanApprovedList;
import com.fin.homeloan.app.servicei.LoanService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/sanction")
public class LoanController {
	@Autowired
	LoanService ls;
//------------------------------------------------Loan------------------------------------------------
	@PostMapping(value = "/post")
	public ResponseEntity<LoanApprovedList> postData(@RequestBody LoanApprovedList loanApprovesList)
	{
		return new ResponseEntity<LoanApprovedList>(ls.saveData(loanApprovesList),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get")
	public ResponseEntity<Iterable<LoanApprovedList>> getAllData() {
		return new ResponseEntity<Iterable<LoanApprovedList>>(ls.getData(), HttpStatus.OK);

	}

	@GetMapping(value = "/getloan/{id}")
	public ResponseEntity<Optional<LoanApprovedList>> getloanById(@PathVariable int id) {
		
		return new ResponseEntity<Optional<LoanApprovedList>>(ls.getloanbyid(id), HttpStatus.OK);
	}

}
