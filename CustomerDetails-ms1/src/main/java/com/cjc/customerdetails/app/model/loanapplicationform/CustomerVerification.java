package com.cjc.customerdetails.app.model.loanapplicationform;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CustomerVerification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer verificationID;
	    private Date verificationDate;
	    private String status;
	    private String remarks;
}
