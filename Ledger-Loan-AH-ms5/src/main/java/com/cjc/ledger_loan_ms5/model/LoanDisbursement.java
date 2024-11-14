package com.cjc.ledger_loan_ms5.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
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
