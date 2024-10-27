package com.cjc.customerdetails.app.servicei.loanapplicationform;

import org.springframework.web.multipart.MultipartFile;

import com.cjc.customerdetails.app.model.loanapplicationform.AllPersonalDocuments;

public interface AllPersonalDocumentsServiceI {


	void editDocument(int documentID, int loanid, MultipartFile addressProof);

	AllPersonalDocuments getDocument(int documentID);

	void deleteDocument(int documentID);

	void saveDocument(MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile addharCard, MultipartFile photo, MultipartFile signature, MultipartFile bankCheque,
			MultipartFile salarySlips,int customerid);

}
