package com.fin.homeloan.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fin.homeloan.app.model.EmailSender;
import com.fin.homeloan.app.servicei.ApplicantService;


public class EmailSendingController {

	@Autowired
	ApplicantService hls;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@PostMapping(value = "/sendEmail")
	public String sendmail(@RequestBody EmailSender e) {
		e.setFromEmail(fromEmail);
		try {
			hls.sendmail(e);

		} catch (Exception e2) {
			return "Eamil Not Send";
		}
		return "Email Send Successfully";
	}

	@PostMapping(value = "/sendEmailWithAttachement")
	public String sendEmailWithAttachement(@RequestBody EmailSender e) {
		e.setFromEmail(fromEmail);
		try {
			hls.sendEmailWithAttachement(e);

		} catch (Exception e2) {
			return "Eamil Not Send";
		}
		return "Email Send Successfully";
	}

}
