package com.fin.homeloan.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.homeloan.app.model.Applicant;
import com.fin.homeloan.app.model.Sanction;
import com.fin.homeloan.app.repository.ApplicantRepository;
import com.fin.homeloan.app.repository.SanctionRepository;
import com.fin.homeloan.app.servicei.SanctionService;

@Service
public class SanctionServiceImpl implements SanctionService {

	
	@Autowired
	ApplicantRepository ar;
	
	
	@Autowired
	SanctionRepository sr;
	
	
	@Override
	public Sanction getsanctiondata(String applicationId, Sanction s) {
		return s;
		
		

	}

}
