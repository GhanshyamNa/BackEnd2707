package com.fin.homeloan.app.serviceimpl;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fin.homeloan.app.model.EmailSender;
import com.fin.homeloan.app.model.Ledger;
import com.fin.homeloan.app.repository.LedgerRepository;
import com.fin.homeloan.app.servicei.LedgerService;

@Service
public class LedgerServiceImpl implements LedgerService {

	
	@Autowired
	LedgerRepository lr;
	
	@Autowired
	JavaMailSender jms;
	
	@Override
	public List<Ledger> getLedger() {
		
		return lr.findAll();
	}

	@Override
	public Ledger saveLedger(Ledger l) {
	
		return lr.save(l);
	}

	@Override
	public void email(EmailSender e) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(e.getToEmail());
		msg.setFrom(e.getFromEmail());
		msg.setText(e.getTextMessage());
		msg.setSubject(e.getSubject());
		jms.send(msg);
		System.out.println("mail Send..!");
	}

	@Override
	public void sendEmailWithAttachment(EmailSender e) throws MessagingException {
		MimeMessage msg1 = jms.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg1, true);
		helper.setTo(e.getToEmail());
		helper.setFrom(e.getFromEmail());
		helper.setSubject(e.getSubject());
		helper.setText(e.getTextMessage());

		FileSystemResource file = new FileSystemResource(
				new File("C:\\Users\\rames\\Documents\\Inheritance_Rules.docx"));
		helper.addAttachment(file.getFilename(), file);
		jms.send(msg1);
		
	}

	

	
	

}
