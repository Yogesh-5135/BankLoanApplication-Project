package com.cjc.customerdetails.app.model.loanapplicationform;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AllPersonalDocuments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int documentID;
	private byte[] addressProof ;	
	private byte[] panCard ;
	private byte[] IncomeTax;
	private byte[] addharCard	;
	private byte[] photo;
	private byte[] signature;
	private byte[]	bankCheque	;
	private byte[] salarySlips	;

	
}
