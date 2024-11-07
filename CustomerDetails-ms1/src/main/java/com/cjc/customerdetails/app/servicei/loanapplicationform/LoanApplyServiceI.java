package com.cjc.customerdetails.app.servicei.loanapplicationform;

import java.util.List;

import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;

public interface LoanApplyServiceI {

	void saveLoanApplication(LoanApplication ad);

	LoanApplication getSingleLoanApplication(int loanid);

	LoanApplication updateLoanApplication(int loanid, LoanApplication l);

	void deleteLoanApplication(int loanid);

	List<LoanApplication> getAllLoanApplication();

	List<LoanApplication> getAccepted();

}
