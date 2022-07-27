package com.fin.homeloan.app.serviceimpl;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fin.homeloan.app.model.Applicant;
import com.fin.homeloan.app.model.Customer;
import com.fin.homeloan.app.model.EmailSender;
import com.fin.homeloan.app.repository.ApplicantRepository;
import com.fin.homeloan.app.repository.CustomerRepository;
import com.fin.homeloan.app.repository.DocumentRepository;
import com.fin.homeloan.app.servicei.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService {

	
	@Autowired
	CustomerRepository cr;
	
	@Autowired
	DocumentRepository dr;
	
	@Autowired
	ApplicantRepository ar;
	
	@Autowired
	JavaMailSender sender;

	@Override
	public Customer saveApplicantData(Customer a) {
		return cr.save(a);
	}

	@Override
	public Iterable<Customer> geteApplicantData() {
		return cr.findAll();
	}

	@Override
	public Applicant saveApplicantDataApp(Integer customerId,Applicant a) {
		Customer	c=cr.findByCustomerId(customerId);
		a.setCustomer(c);
		return ar.save(a);
		
	}

	@Override
	public void saveApplicant(Applicant a) {
		ar.save(a);

}

	@Override
	public Applicant get(int applicationId) {
		Applicant a=ar.findByapplicationId(applicationId);
		return a;
	}

	@Override
	public void sendmail(EmailSender e) {
		SimpleMailMessage massage = new SimpleMailMessage();
		massage.setFrom(e.getFromEmail());
		massage.setTo(e.getToEmail());
		massage.setSubject(e.getSubject());
		massage.setText(e.getTextMessage());
		sender.send(massage);
		
	}

	@Override
	public void sendEmailWithAttachement(EmailSender e) throws MessagingException {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setFrom(e.getFromEmail());
		helper.setTo(e.getToEmail());
		helper.setSubject(e.getSubject());
		helper.setText(e.getTextMessage());

		FileSystemResource file = new FileSystemResource("E:\\\\abc.pdf");
		helper.addAttachment(file.getFilename(), file);
		sender.send(msg);
		
	}

	@Override
	public void deletedata(int applicationId) {
		
	ar.deleteById(applicationId);	
	}
	

	
	

	
}
