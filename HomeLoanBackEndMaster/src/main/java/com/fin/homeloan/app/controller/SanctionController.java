package com.fin.homeloan.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.homeloan.app.model.Sanction;
import com.fin.homeloan.app.servicei.SanctionService;


@RestController
public class SanctionController {
	@Autowired
	SanctionService ss;

	@PostMapping(value="/generatesanctionLoan/{applicationId}")
	public Sanction postdata(@PathVariable String applicationId,Sanction s )
	{
		return ss.getsanctiondata(applicationId,s);
	}
	

}
