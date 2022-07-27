package com.fin.homeloan.app.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.homeloan.app.model.LoanApprovedList;
import com.fin.homeloan.app.model.LoanDetails;
import com.fin.homeloan.app.repository.LoanApprovedListRepository;
import com.fin.homeloan.app.repository.LoanDetailsRepository;
import com.fin.homeloan.app.servicei.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	
	@Autowired
	LoanApprovedListRepository lal;
	

	@Autowired
	LoanDetailsRepository rep;
	
	@Override
	public Iterable<LoanApprovedList> getData() {
	
		return lal.findAll();
	}

	@Override
	public Optional<LoanApprovedList> getloanbyid(int id) {
	
		return lal.findById(id);
	}

	@Override
	public LoanApprovedList saveData(LoanApprovedList loanApprovesList) {
		
 		return lal.save(loanApprovesList);
	}

	@Override
	public LoanDetails saveLoanDetails(LoanDetails l) {

		return rep.save(l);
	}

	@Override
	public LoanDetails getByLoanId(int loanDetailId) {
		LoanDetails l = rep.findByloanDetailId(loanDetailId);
		return l;
	}

}
