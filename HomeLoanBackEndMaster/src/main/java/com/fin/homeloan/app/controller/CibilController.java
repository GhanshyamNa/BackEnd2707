package com.fin.homeloan.app.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fin.homeloan.app.model.Cibil;
import com.fin.homeloan.app.model.Customer;
import com.fin.homeloan.app.repository.CustomerRepository;
import com.fin.homeloan.app.servicei.CibilService;

@CrossOrigin("*")
@RestController
public class CibilController {
	@Autowired
	CibilService cs;
	@Autowired
	CustomerRepository er;

	@GetMapping("/getall")
	public List getAll() {
		return cs.getAll();
	}

	@PostMapping("/adddata")
	public String addData(@RequestBody Customer e) {

		er.save(e);
		return "Data Saved!!!";
	}

	@GetMapping("/getcibil")
	public int getData() {
		Random objGenerator = new Random();

		int CibilScore = objGenerator.nextInt(1000);
		if (CibilScore >= 350 && CibilScore <= 900) {
			System.out.println("CibilScore : " + CibilScore);
			return CibilScore;
		}
		return 500;

	}

	

	@PostMapping("/addcibil")
	public String adddata(@RequestBody Cibil c) {

		if (c.getCibilScore() >= 750) {
			c.setCibilStatus("Valid");
		} else {
			c.setCibilStatus("Invalid");
		}
		cs.saveData(c);
		return "Data Saved!!";

	}

	@GetMapping("/getalldata")
	public List getAllData() {
		return (List) er.findAll();
	}

	

}
