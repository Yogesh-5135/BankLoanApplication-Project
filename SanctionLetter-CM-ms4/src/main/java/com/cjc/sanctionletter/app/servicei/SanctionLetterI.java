package com.cjc.sanctionletter.app.servicei;

import java.util.List;

import com.cjc.sanctionletter.app.model.LoanApplication;
import com.cjc.sanctionletter.app.model.SanctionLetter;

public interface SanctionLetterI {


	SanctionLetter generateLimit(int loanid, List<LoanApplication> l);

	void generateIntRate(int sanctionId);

	void getMonthlyEmi(int sanctionId);

	SanctionLetter generateSanctionLetter(List<LoanApplication> l, int sanctionId, int loanid);

	void loanStatusChange(String loanStatus, int loanid);



}
