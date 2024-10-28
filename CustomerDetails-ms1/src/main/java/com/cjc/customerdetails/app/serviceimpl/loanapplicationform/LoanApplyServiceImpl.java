package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.MobNoException;
import com.cjc.customerdetails.app.model.Enquiry;
import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.CustServiceI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.LoanApplyServiceI;

@Service
public class LoanApplyServiceImpl implements LoanApplyServiceI{
	
	@Autowired
	LoanApplyRepoI lri;
	@Autowired
	CustServiceI csi;

	@Override
	public void saveLoanApplication(LoanApplication ld) 
	{
		List<Enquiry> en = csi.getAllApprovedData();
		LoanApplication la = ld;
		for (Enquiry enquiry : en) {
			if(enquiry.getMobileno() == la.getCustomerMobileNumber() ) 
			{		
				la.setLoanStatus("Submitted");
				lri.save(la);					
			}	
			else {
				throw new MobNoException("Invalid Mobile no..It should match with customer mobno");
			}
		}
		
	}
		
	

	@Override
	public LoanApplication getSingleLoanApplication(int loanid) 
	{
		Optional<LoanApplication> oa = lri.findById(loanid);
		if(oa.isPresent())
		{
			LoanApplication a = oa.get();
			
			return a;
		}
		else
		{
			throw new IdNotFountException("LoanApplication Not Found");
		}
	}

	@Override
	public LoanApplication updateLoanApplication(int loanid, LoanApplication ld) 
	{
		Optional<LoanApplication> oa = lri.findById(loanid);
		if(oa.isPresent())
		{
			LoanApplication l = oa.get();
			
			l.setCustomerName(ld.getCustomerName());
			l.setCustomerAge(ld.getCustomerAge());
			l.setCustomerEmail(ld.getCustomerEmail());
			l.setCustomerGender(ld.getCustomerGender());
			l.setCustomerMobileNumber(ld.getCustomerMobileNumber());
			l.setCustomerTotalLoanRequired(ld.getCustomerTotalLoanRequired());
			l.setCustomerAmountPaidForHome(ld.getCustomerAmountPaidForHome());
			l.setDob(ld.getDob());
			l.setLoanStatus("Submitted");
			l.setRequiredTenure(ld.getRequiredTenure());
			l.setCustomerAdditionalMobileNumber(ld.getCustomerAdditionalMobileNumber());
			
			lri.save(l);
			
		}
		return ld;
		
	}

	@Override
	public void deleteLoanApplication(int loanid) 
	{
		lri.deleteById(loanid);		
	}

	@Override
	public List<LoanApplication> getAllLoanApplication()
	{
		List<LoanApplication> l = lri.findAll();
		return l;
	}

	
	
}
