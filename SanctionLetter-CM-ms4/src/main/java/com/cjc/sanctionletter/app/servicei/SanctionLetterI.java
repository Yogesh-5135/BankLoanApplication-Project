package com.cjc.sanctionletter.app.servicei;

import java.util.List;

import com.cjc.sanctionletter.app.model.LoanApplication;

public interface SanctionLetterI {


	void generateLimit(int loanid, List<LoanApplication> l);

	void generateIntRate(int sanctionId);

	void getMonthlyEmi(int sanctionId);

	void generateSanctionLetter(List<LoanApplication> l, int sanctionId, int loanid);

	void loanStatusChange(String loanStatus, int loanid);



}
