package com.cjc.ledger_loan_ms5.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ledger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer ledgerId;
	    private Date ledgerCreatedDate;
	    private Double totalLoanAmount;
	    private Double payableAmountWithInterest;
	    private Integer tenure;
	    private double rateOfInterest;
	    private Double monthlyEMI;
	    private Double amountPaidTillDate;
	    private Double remainingAmount;
	    private String nextEmiDateStart;
	    private String nextEmiDateEnd;
	    private Integer defaulterCount;
	    private String previousEmiStatus;
	    private String currentMonthEmiStatus;
	    private String loanEndDate;
	    private String loanStatus;
	
}
