package com.fin.homeloan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.homeloan.app.model.EmailSender;
import com.fin.homeloan.app.model.Ledger;
import com.fin.homeloan.app.servicei.LedgerService;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ledger")
public class LedgerController {

	@Autowired
	LedgerService ls;
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@GetMapping(value = "/get")
	public ResponseEntity<List<Ledger>> getLedger() {
		return new ResponseEntity<List<Ledger>>(ls.getLedger(), HttpStatus.OK);

	}
	@PostMapping(value = "/save")
	public ResponseEntity<Ledger> save3Ledger(@RequestBody Ledger l) {
		return new ResponseEntity<Ledger>(ls.saveLedger(l), HttpStatus.CREATED);

	}
	@PutMapping(value = "/update/{ledgerId}")
	public Ledger updateLedger(@PathVariable("ledgerId") int ledgerId, @RequestBody Ledger l1) {

		return ls.saveLedger(l1);
	}

	@PostMapping(value = "/emailsend")
	public String SendEmail(@RequestBody EmailSender e) {
		e.setFromEmail(fromEmail);
		try {
			ls.email(e);
		} catch (Exception e2) {
			e2.printStackTrace();
			return "Email Can not be sent..!!";
		}
		return "Email Send SuccessFully!!";

	}
	@PostMapping(value = "/emailSendWithAttachment")
	public String emailSendWithAttachment(@RequestBody EmailSender e) {
		e.setFromEmail(fromEmail);

		try {
			ls.sendEmailWithAttachment(e);
		} catch (Exception e2) {
			e2.printStackTrace();
			return "Email Can not be sent..!!";
		}
		return "Email Send SuccessFully!!";

	}
	
}
