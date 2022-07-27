package com.fin.homeloan.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fin.homeloan.app.model.EmailSender;
import com.fin.homeloan.app.model.LoanDetails;

@Service
public class EmailSendServiceImpl {
	@Autowired
	JavaMailSender j;


	public void sendEmail(LoanDetails l, EmailSender emailsender) {
			
			SimpleMailMessage smm = new SimpleMailMessage();
			
			smm.setTo(l.getCustomer().getEmail());
			
			smm.setSubject("HomeLoan process" );
			
			smm.setText(" Heyy" + " " + l.getCustomer().getFullName() +"\n"+  " You are eligible for interior loan . Below is the estimated payable amount calculated with rate of interest"+ "\n" + 
			"Requiored Loan Amount :" +  l.getCustomer().getLoanAmount() + "\n" + "ROI :" + l.getAnnualInterest() + "\n"+ "Total Payable Loan :"+ l.getPayableLoan() 
			+ "\n" + "Tenure :"  + l.getTenureofLoan() + "\n"+ "If acceptable ,you can reply back with required documents." + "\n"+ "Thanks & Regards," + "\n" + " Loan Company");		
			
			
			j.send(smm);
		}
}
