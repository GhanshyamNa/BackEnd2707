package com.fin.homeloan.app.serviceimpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fin.homeloan.app.model.EmailSender;
import com.fin.homeloan.app.model.LoanApprovedList;
import com.fin.homeloan.app.model.LoanDetails;
import com.fin.homeloan.app.model.PermanantAddress;
import com.fin.homeloan.app.model.SanctionLetter;
import com.fin.homeloan.app.repository.ApplicantRepository;
import com.fin.homeloan.app.repository.LoanApprovedListRepository;
import com.fin.homeloan.app.repository.LoanDetailsRepository;
import com.fin.homeloan.app.repository.PermanantAddressRepository;
import com.fin.homeloan.app.repository.SanctionLetterRepository;
import com.fin.homeloan.app.servicei.CreditManagerServicei;
@Service
public class CreditManagerServiceImpl implements CreditManagerServicei {

	@Autowired
	ApplicantRepository ar;
	@Autowired
	LoanApprovedListRepository lalr;
	@Autowired
	SanctionLetterRepository slr;
	@Autowired
	PermanantAddressRepository pa;
	@Autowired
	JavaMailSender jms;
	@Autowired
	LoanDetailsRepository ldr;

	// ----------------------Loan-Approved-------------------
	@Override
	public LoanApprovedList savelal(LoanApprovedList lal) {

		LoanApprovedList loanal = lalr.save(lal);
		return loanal;
	}

	@Override
	public List<LoanApprovedList> getLoanApprovedList() {
		List<LoanApprovedList> lal = lalr.findAll();
		return lal;
	}

	@Override
	public LoanApprovedList getLoanApprovedList(int id) {
		LoanApprovedList lall = lalr.findByLoanAid(id);
		return lall;
	}

//---------------------------Permanant-Address---------------------------
	@Override
	public PermanantAddress savePAddress(PermanantAddress paddr) {
		PermanantAddress paa = pa.save(paddr);
		return paa;
	}

//---------------------------Loan-Deatails-------------------------------
	@Override
	public LoanDetails saveLoanData(LoanDetails loan) {

		LoanDetails ld = ldr.save(loan);

		return null;
	}

//---------------------------About-Sanction-letter------------------------
	@Override
	public SanctionLetter savesanction(int id, LoanApprovedList lallist, SanctionLetter sl) {

			LoanApprovedList lal = lalr.findByLoanAid(id);
			System.out.println(lal.getLoanDetails().getTenureofLoan());
			sl.setLal(lal);
			
			System.out.println("Loan Required : "+lallist.getLoanDetails().getTotalLoanRequired());
			System.out.println("Loan Sanctioned : "+lallist.getSanction().getSanctionedAmount());
			System.out.println("Loan Tenure : "+lallist.getLoanDetails().getTenureofLoan());
			System.out.println("Interest Rate : "+lallist.getSanction().getRateofInterest());
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			System.out.println(formatter.format(date));
			
			double principal = lallist.getSanction().getSanctionedAmount();
			double rate = lallist.getSanction().getRateofInterest();
			double time = lallist.getLoanDetails().getTenureofLoan();
			rate=rate/(12*100); 
	        time=time*12; 
			double monthlyemi = (principal*rate*Math.pow(1+rate,time))/(Math.pow(1+rate,time)-1);
			double totalemi = (monthlyemi*time);
			double totalamount = (principal+totalemi);
			
			System.out.println(monthlyemi);
			System.out.println(totalemi);
			System.out.println(totalamount);
			
			sl.setMonthlyEMIAmount(monthlyemi);
			sl.setPrincipalAmount(principal);
			sl.setSanctionDate(formatter.format(date));
			sl.setTotalInterest(totalemi);
			sl.setTotalAmount(totalamount);
			
			if(lallist.getSanction().getSanctionedAmount() == null)
			{
				sl.setStatus("Loan Declined");
			}
			else
			{
				sl.setStatus("Loan Sanctioned");
			}
			
			return slr.save(sl);
		}

	@Override
	public List<SanctionLetter> getSanctionLetterData() {
		List<SanctionLetter> sl = slr.findAll();
		return sl;
	}

	@Override
	public SanctionLetter getDataForLetter(int id) {
		return slr.findBySanctionId(id);
	}
//	@Override
//	public void sendEmailWithAttachment(EmailSender em, MultipartFile f) {
//
//		MimeMessage mm=jms.createMimeMessage();
//		
//		try {
//			MimeMessageHelper mmh= new MimeMessageHelper(mm, true);
//			mmh.setFrom(em.getFromEmail());
//			mmh.setTo(em.getToEmail());
//			mmh.setSubject(em.getSubject());
//			mmh.setText(em.getTextMessage());
//			
//			mmh.addAttachment(f.getOriginalFilename(), f);
//			
//			jms.send(mm);
//			System.out.println("Email sent");
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Not sent");
//		}

	@Override
	public void sendEmailWithAttachment(EmailSender es) throws MessagingException {
		MimeMessage msg=jms.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(msg,true);
		helper.setTo(es.getToEmail());
		helper.setFrom(es.getFromEmail());
		helper.setSubject(es.getSubject());
		helper.setText(es.getTextMessage());
		
		FileSystemResource file=new FileSystemResource(new File("C:\\Users\\USER\\Downloads\\sanction-letter.pdf"));
		
		helper.addAttachment(file.getFilename(),file);
		jms.send(msg);
		
	}
		
	}


