package com.cjc.customerdetails.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerid;
	private String name;
	private String username;
	private String password;
	private int age;
	private String email;
	private long mobileno;
	private String pancard;
	private String  enquiryStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cibil;
	
}

