package com.fin.homeloan.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fin.homeloan.app.model.Customer;
import com.fin.homeloan.app.model.EmailSender;
import com.fin.homeloan.app.model.LoanDetails;
import com.fin.homeloan.app.servicei.CustomerService;
import com.fin.homeloan.app.servicei.EmailSendService;
import com.fin.homeloan.app.servicei.LoanService;
import com.fin.homeloan.app.serviceimpl.EmailSendServiceImpl;
@CrossOrigin("*")
@RestController
public class EmailSenderController {
	@Autowired
	CustomerService cis;
	
	@Autowired
    LoanService eis;
	
	@Autowired
	EmailSendServiceImpl esimpl;
	
	@Value("${spring.mail.username}")
	private String fromEmail;

	

	@PostMapping(value ="/sendemail/{customerId}")
	public String sendEmail(@PathVariable int customerId, @RequestBody LoanDetails ld)
	{
		
		Customer c = cis.get(customerId);

		Float roi = ld.getAnnualInterest();
		System.out.println(roi);
		
		//ld.setTotalLoanRequired(c.getLoanAmount());)
		long loanrequired = c.getLoanAmount();

		System.out.println(loanrequired);

		ld.setTotalLoanRequired(loanrequired);
		Integer tenure = ld.getTenureofLoan();
		System.out.println(tenure);
		
		
		
		 Float outstandingAmount = (loanrequired + (loanrequired * (roi/100)*tenure));
		// Float emi = outstandingAmount/tenure;
		 System.out.println(outstandingAmount);
		 
		 ld.setPayableLoan(outstandingAmount);

		 ld.setCustomer(c);
		//LoanDetails l =  eis.saveLoanDetails(ld);
		 
		LoanDetails l = eis.saveLoanDetails(ld);
			
		System.out.println( l.getCustomer().getEmail() + l.getCustomer().getLoanAmount()  +l.getCustomer().getFullName());
		
		EmailSender emailsender = new EmailSender();
		
		emailsender.setFromEmail(fromEmail);
	
		esimpl.sendEmail(l, emailsender);
	
		return "email sent" ;
	}
}
