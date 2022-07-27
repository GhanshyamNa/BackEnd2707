package com.fin.homeloan.app.servicei;

import java.util.Optional;

import com.fin.homeloan.app.model.LoanApprovedList;
import com.fin.homeloan.app.model.LoanDetails;

public interface LoanService {

	Iterable<LoanApprovedList> getData();

	Optional<LoanApprovedList> getloanbyid(int id);

	LoanApprovedList saveData(LoanApprovedList loanApprovesList);
	
	public LoanDetails saveLoanDetails(LoanDetails l);

	public LoanDetails getByLoanId(int loanDetailId);

}
