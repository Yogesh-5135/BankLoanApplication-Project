package com.cjc.ledger_loan_ms5.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.ledger_loan_ms5.model.LoanApplication;
import com.cjc.ledger_loan_ms5.model.LoanDisbursement;
import com.cjc.ledger_loan_ms5.repoi.LoanAppRepoI;
import com.cjc.ledger_loan_ms5.repoi.LoanDisbursementRepoI;
import com.cjc.ledger_loan_ms5.servicei.LoanDisbursementI;

@Service
public class LoanDisbursementServiceImpl implements LoanDisbursementI  {
	@Autowired
	LoanDisbursementRepoI ldi;
	@Autowired
	LoanAppRepoI lri;
	
	@Override
	public void updateLoan(int loanid ) 
	{
		LoanApplication l = new LoanApplication();
		Optional<LoanApplication> o = lri.findById(loanid);
		if(o.isPresent()) {
			l = o.get();
		}
		
		LoanDisbursement ld = new LoanDisbursement();
		
			ld.setLoanNo(l.getLoanid());
			ld.setAgreementDate(new Date());
			ld.setTotalAmount(l.getSanctionLetter().getLoanAmtSanctioned());
			ld.setTransferAmount(l.getSanctionLetter().getLoanAmtSanctioned());
			ld.setAmountPaidDate(new Date());
			ld.setAccountNumber(l.getAccountdetails().getAccountNumber());
			ld.setAccountType(l.getAccountdetails().getAccounType());
			ld.setBankName("Axis Bank");
			ld.setIFSCCode("AXIS2011");
			ld.setAmountPayType("By Cash");
			ld.setPaymentStatus("Paid");
			
			ldi.save(ld);
			l.setLoanDisbursement(ld);
			lri.save(l);
			}
		
}
