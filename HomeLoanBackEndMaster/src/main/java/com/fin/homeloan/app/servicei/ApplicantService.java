package com.fin.homeloan.app.servicei;

import java.util.List;

import javax.mail.MessagingException;

import com.fin.homeloan.app.model.Applicant;
import com.fin.homeloan.app.model.Customer;
import com.fin.homeloan.app.model.EmailSender;


public interface ApplicantService {

	Customer saveApplicantData(Customer a);

	Iterable<Customer> geteApplicantData();

	Applicant saveApplicantDataApp(Integer customerId, Applicant a);

	Applicant get(int applicantId);

	void saveApplicant(Applicant a);

	void sendmail(EmailSender e);

	void sendEmailWithAttachement(EmailSender e) throws MessagingException;

	void deletedata(int applicationId);
	


}
