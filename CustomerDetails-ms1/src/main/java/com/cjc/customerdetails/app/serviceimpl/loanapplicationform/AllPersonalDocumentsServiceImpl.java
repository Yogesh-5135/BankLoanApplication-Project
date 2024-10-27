package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.model.Enquiry;
import com.cjc.customerdetails.app.model.loanapplicationform.AllPersonalDocuments;
import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.repoi.CustRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.AllPersonalDocumentsRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.AllPersonalDocumentsServiceI;

@Service
public class AllPersonalDocumentsServiceImpl implements AllPersonalDocumentsServiceI
{
  @Autowired
  AllPersonalDocumentsRepoI apd;
  
  @Autowired
	LoanApplyRepoI lri;


  @Override
	public void saveDocument(MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile addharCard, MultipartFile photo, MultipartFile signature, MultipartFile bankCheque,
			MultipartFile salarySlips ,int  loanid) 
  {
	  LoanApplication l = null;
	  
	  Optional<LoanApplication> o = lri.findById(loanid);
		if(o.isPresent()) {
			l = o.get();
		}
		else
		{
			throw new RuntimeException("Id not found");
		}

	  
	AllPersonalDocuments ap = null;
	  			
	try {
			ap.setAddressProof(addressProof.getBytes());
			ap.setPanCard(panCard.getBytes());
			ap.setIncomeTax(incomeTax.getBytes());
			ap.setAddharCard(addharCard.getBytes());
			ap.setPhoto(photo.getBytes());
			ap.setSignature(signature.getBytes());
			ap.setBankCheque(bankCheque.getBytes());
			ap.setSalarySlips(salarySlips.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			}		
	
	l.setAllPersonalDocuments(ap);
	lri.save(l);
  		
	}
	  
	
	@Override
	public void editDocument(int documentID ,int  loanid , MultipartFile addressProof) 
	{
		LoanApplication l = null;
		  
		  Optional<LoanApplication> o = lri.findById(loanid);
			if(o.isPresent()) {
				l = o.get();
			}
			else
			{
				throw new RuntimeException("Id not found");
			}

		try {	
		l.getAllPersonalDocuments().setAddressProof(addressProof.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			}	
	}
	
	@Override
	public AllPersonalDocuments getDocument(int documentID ) 
	{
		 AllPersonalDocuments l = null;

		 Optional<AllPersonalDocuments> o = apd.findById(documentID);
		 if(o.isPresent()) {
				l = o.get();
			}
			else
			{
				throw new RuntimeException("Id not present");
			}
			return l;

	}
	
	@Override
	public void deleteDocument(int documentID) 
	{		
			apd.deleteById(documentID);
			
	}
	  
  
}
