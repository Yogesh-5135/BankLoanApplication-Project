package com.cjc.ledger_loan_ms5.servicei;

import java.util.List;

import com.cjc.ledger_loan_ms5.model.Ledger;

public interface LedgerServiceI 
{

	List<Ledger> updateLedger(int loanid);

	List<Ledger> updateLoanStatus(int loanid, int ledgerId);

	List<Ledger> getLedger(int loanid);

	List<Ledger> getLedgerOnlyEmiPaid(int loanid);

}
