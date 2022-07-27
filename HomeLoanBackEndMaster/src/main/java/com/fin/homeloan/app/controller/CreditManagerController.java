package com.fin.homeloan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fin.homeloan.app.model.EmailSender;
import com.fin.homeloan.app.model.LoanApprovedList;
import com.fin.homeloan.app.model.SanctionLetter;
import com.fin.homeloan.app.servicei.CreditManagerServicei;

@CrossOrigin("*")
@RestController
public class CreditManagerController {

	@Autowired
	CreditManagerServicei cms;
//-----------------------------------loan-Approve-list-Ghanshyam-----------------------------------------
	@PostMapping("/saveloanapprovelist")
	public LoanApprovedList saveData(@RequestBody LoanApprovedList lal) {
		return cms.savelal(lal);
	}

	@GetMapping("/getloanapprovelist")
	public List<LoanApprovedList> getListLoanApprovedData() {
		List<LoanApprovedList> lal = cms.getLoanApprovedList();
		return lal;
	}

	@GetMapping("/getLoanApprovedapplicant/{loanAid}")
	public LoanApprovedList getLoanApprovedListData(@PathVariable("loanAid") int id) {
		LoanApprovedList lal = cms.getLoanApprovedList(id);
		return lal;
	}
//-----------------------------------Snctionletter-Ghanshyam---COMMENT--Calculate Emi work is Pending-----------------------------------------
	@PostMapping("/calculateEMI/{loanAid}")
	public SanctionLetter saveData(@PathVariable ("loanAid") int id,@RequestBody LoanApprovedList lallist)
	{
		SanctionLetter sl=new SanctionLetter();
		return cms.savesanction(id,lallist,sl);
	}
	@GetMapping("/getAllSanctionLetterData")
	public List<SanctionLetter> getAllSanctionLetterData()
	{
		List<SanctionLetter> sl = cms.getSanctionLetterData();
		return sl;
	}
	
	@GetMapping("/getSanctionLetterData/{sanctionId}")
	public SanctionLetter getDataForLetter(@PathVariable("sanctionId") int id)
	{
		System.out.println(id);
		return cms.getDataForLetter(id);
	}
//-----------------------------Email Attach And Send------------------------------------------------------
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@PostMapping(value="/emailsendwithattachment")
	public String emailsendwithattachment(@RequestBody EmailSender es)
	{
		es.setFromEmail(fromEmail);
		System.out.println(es.getFromEmail());
		System.out.println(es.getToEmail());
		System.out.println(es.getSubject());
		System.out.println(es.getTextMessage());
		//System.out.println(es.getAttachment());
		System.out.println(es.getClass());
		try {
			cms.sendEmailWithAttachment(es);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Email can not be send";
		}
		return "Email send Successfully";
	}

//	public String emailsendwithattachment(@RequestPart (required = true,value = "attachment") MultipartFile f,
//			@RequestPart ("email") String email)
//	{
//		try {
//			EmailSender em= new EmailSender();
//			em.setFromEmail(fromEmail);
//			ObjectMapper o= new ObjectMapper();
//			EmailSender e1=o.readValue(email, EmailSender.class);
//			
//			em.setSubject(e1.getSubject());
//			em.setTextMessage(e1.getTextMessage());
//			em.setToEmail(e1.getToEmail());
//			
//			cms.sendEmailWithAttachment(em,f);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return "Email can't be sent..!";
//		}
//		
//		return "Email sent Successfully...!";
//	}

}
