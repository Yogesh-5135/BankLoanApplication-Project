package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.model.loanapplicationform.DependentInforamtion;
import com.cjc.customerdetails.app.model.loanapplicationform.GuarantorDetails;
import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.repoi.loanapplicationform.GuarantorDetailsRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.GuarantorDetailsServiceI;

@Service
public class GuarantorDetailsServiceImpl implements GuarantorDetailsServiceI
{
  @Autowired
  GuarantorDetailsRepoI gdr;
  @Autowired
  LoanApplyRepoI lri;
  
	@Override
	public GuarantorDetails saveData(GuarantorDetails s ,int loanid) 
	{
		LoanApplication l = null;
		
		Optional<LoanApplication> oa = lri.findById(loanid);
		if(oa.isPresent())
		{
			l = oa.get();
		}
		else
		{
			throw new IdNotFountException("Id Not Found");
		}
		
		GuarantorDetails di = new GuarantorDetails();
		
		di.setGuarantorAdharCardNo(s.getGuarantorAdharCardNo());
		di.setGuarantorDateOfBirth(s.getGuarantorDateOfBirth());
		di.setGuarantorJobDetails(s.getGuarantorJobDetails());
		di.setGuarantorLoaclAddress(s.getGuarantorLoaclAddress());
		di.setGuarantorMobileNumber(s.getGuarantorMobileNumber());
		di.setGuarantorMortgageDetails(s.getGuarantorMortgageDetails());
		di.setGuarantorName(s.getGuarantorName());
		di.setGuarantorPermanentAddress(s.getGuarantorPermanentAddress());
		di.setGuarantorRelationshipwithCustomer(s.getGuarantorRelationshipwithCustomer());
		
		gdr.save(di);
		l.setGurantordetails(di);
		lri.save(l);
		
		
		return di;
	}
	
	@Override
	public void editData(int GuarantorId, GuarantorDetails c) 		
	{
		Optional<GuarantorDetails> o = gdr.findById(GuarantorId);
		if(o.isPresent()) {
			GuarantorDetails cd = o.get();
			
			cd.setGuarantorAdharCardNo(c.getGuarantorAdharCardNo());
			cd.setGuarantorDateOfBirth(c.getGuarantorDateOfBirth());
			cd.setGuarantorJobDetails(c.getGuarantorJobDetails());
			cd.setGuarantorLoaclAddress(c.getGuarantorLoaclAddress());
			cd.setGuarantorMobileNumber(c.getGuarantorMobileNumber());
			cd.setGuarantorMortgageDetails(c.getGuarantorMortgageDetails());
			cd.setGuarantorName(c.getGuarantorName());
			cd.setGuarantorPermanentAddress(c.getGuarantorPermanentAddress());
			cd.setGuarantorRelationshipwithCustomer(c.getGuarantorRelationshipwithCustomer());
			
			gdr.save(cd);			
		}	else
		{
			throw new RuntimeException("Id not found");
		}
		
	}
	
	@Override
	public GuarantorDetails getData(int GuarantorId)
	{
		GuarantorDetails cd = new GuarantorDetails();
		Optional<GuarantorDetails> o = gdr.findById(GuarantorId);
		if(o.isPresent()) {
			cd = o.get();
			return cd;
		}
		else
		{
			throw new RuntimeException("Id not found");
		}
		
	}
	
	@Override
	public void deleteData(int GuarantorId) 
	{
		gdr.deleteById(GuarantorId);
		
	}
	 
  
}
