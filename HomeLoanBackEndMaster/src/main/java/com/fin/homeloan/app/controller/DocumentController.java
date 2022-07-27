package com.fin.homeloan.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fin.homeloan.app.model.Applicant;
import com.fin.homeloan.app.model.Customer;
import com.fin.homeloan.app.model.Documents;
import com.fin.homeloan.app.servicei.ApplicantService;
import com.fin.homeloan.app.servicei.CustomerService;
import com.fin.homeloan.app.servicei.DocumentService;
@CrossOrigin("*")
@RestController
public class DocumentController {

	
	@Autowired
	DocumentService dis;
	
	@Autowired
	CustomerService cis;
	
	@Autowired
	ApplicantService ais;
	

	@PostMapping(value="/save/{customerId}",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public String saveDocument(@PathVariable int customerId,@RequestPart (required = true,value = "adharCard") MultipartFile f1,
			 @RequestPart(required = true,value = "photo") MultipartFile f2,
			 @RequestPart(required = true,value = "signature") MultipartFile f3,
			 @RequestPart(required = true,value = "panCard") MultipartFile f4,
			 @RequestPart(required = true,value = "addressProof") MultipartFile f5,
		     @RequestPart(required = true,value = "incomeDocument") MultipartFile f6,
			 @RequestPart(required = true,value = "propertyDocument") MultipartFile f7,
			 @RequestPart(required = true,value = "blankcheque") MultipartFile f8,
			 @RequestPart(required = true,value = "g_Stamp") MultipartFile f9,
			 @RequestPart(required = true,value = "g_PropertyDoc") MultipartFile f10)throws IOException
	
            
  {
	  Documents d=new Documents();
	 
	  d.setAadharCard(f1.getBytes());
	  d.setPhoto(f2.getBytes());
	  d.setSignature(f3.getBytes());
	  d.setPanCard(f4.getBytes());
	  d.setAddressProof(f5.getBytes());
	  d.setIncomeDocument(f6.getBytes());
	  d.setPropertyDocument(f7.getBytes());
	  d.setBlankcheque(f8.getBytes());
      d.setG_Stamp(f9.getBytes());
	  d.setG_PropertyDoc(f10.getBytes());
	  	
	 
		Customer a =cis.get(customerId);
		d.setCustomer(a);
		
		dis.saveDoc(d);
		return "done";
		

}
	@GetMapping("/getdata/{applicantId}")
	public Applicant m(@PathVariable int applicantId)
	{
		Applicant a=ais.get(applicantId);
		return a;
	}



}
