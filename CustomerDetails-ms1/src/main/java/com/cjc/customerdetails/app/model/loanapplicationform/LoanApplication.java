package com.cjc.customerdetails.app.model.loanapplicationform;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanid;
	
	
	private String customerName	;
	private String dob;	
	private int customerAge	;
	private int requiredTenure	;
	private int cibil;
	private String customerGender	;
	private String customerEmail ;	
	private double customerMobileNumber ;	
	private double customerAdditionalMobileNumber ;
	private double customerAmountPaidForHome ;
	private double customerTotalLoanRequired ;
	private String loanStatus	;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private AllPersonalDocuments allPersonalDocuments;
	
	@OneToOne(cascade=CascadeType.ALL)
	private CustomerAddress customerAddress	;

	@OneToOne(cascade=CascadeType.ALL)
	private AccountDetails accountdetails	;
	
	@OneToOne(cascade=CascadeType.ALL)
	private GuarantorDetails gurantordetails ;	
	
	@OneToOne(cascade=CascadeType.ALL)
	private DependentInforamtion dependentInforamtion;
	
	@OneToOne(cascade=CascadeType.ALL)
	private LoanDisbursement loanDisbursement; 
	
	@OneToOne(cascade=CascadeType.ALL)
    private SanctionLetter sanctionLetter; 
	
	@OneToOne(cascade=CascadeType.ALL)
    private CustomerVerification customerVerification; 

	@OneToMany(cascade=CascadeType.ALL)
    private List<Ledger> ledger; 

}
