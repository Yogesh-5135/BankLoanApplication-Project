package com.cjc.cibil.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanid;
	
	
	private String customerName	;
	private String dob;	;
	private int customerAge	;
	private int requiredTenure	;
	private String customerGender	;
	private String customerEmail ;
	private int cibil;
	private long customerMobileNumber ;	
	private long customerAdditionalMobileNumber ;
	private double customerAmountPaidForHome ;
	private double customerTotalLoanRequired ;
	private String loanStatus	;
	
	@OneToOne(cascade=CascadeType.ALL)
	private AllPersonalDocuments allPersonalDocuments;
	
	
}
