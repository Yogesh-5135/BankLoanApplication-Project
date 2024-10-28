package com.cjc.customerdetails.app.model.loanapplicationform;

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
	    private String agreementDate;
	    private String amountPayType;
	    private Double totalAmount;
	    private String bankName;
	    private Long accountNumber;
	    private String IFSCCode;
	    private String accountType;
	    private Double transferAmount;
	    private String paymentStatus;
	    private String amountPaidDate;
}
