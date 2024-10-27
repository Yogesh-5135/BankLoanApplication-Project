package com.cjc.customerdetails.app.servicei.loanapplicationform;

import java.util.List;

import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;

public interface LoanApplyServiceI {

	LoanApplication saveLoanApplication(LoanApplication ad, int loanid);

	LoanApplication getSingleLoanApplication(int loanid);

	LoanApplication updateLoanApplication(int loanid, LoanApplication l);

	void deleteLoanApplication(int loanid);

	List<LoanApplication> getAllLoanApplication();

}
