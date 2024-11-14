package com.cjc.ledger_loan_ms5.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LoanDisbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer agreementId;
	    private Integer loanNo;
	   
	    private Date agreementDate;
	    private String amountPayType;
	    private Double totalAmount;
	    private String bankName;
	    private Long accountNumber;
	    private String IFSCCode;
	    private String accountType;
	    private Double transferAmount;
	    private String paymentStatus;
	    private Date amountPaidDate;

}
