package com.cjc.sanctionletter.app.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.sanctionletter.app.model.LoanApplication;
import com.cjc.sanctionletter.app.model.SanctionLetter;
import com.cjc.sanctionletter.app.repoi.SanctionLetterRepoI;
import com.cjc.sanctionletter.app.servicei.SanctionLetterI;

@Service
public class SanctionLetterServiceImpl implements SanctionLetterI {
	@Autowired
	SanctionLetterRepoI slr;
	
	@Override
	public void generateLimit(int loanid, List<LoanApplication> l) 
	{
		SanctionLetter sl = new SanctionLetter();
		
		 for(LoanApplication la:l)
		  {
			if( la.getLoanid() == loanid)
			{
			   if(la.getCibil()>=750)
			   {
				   sl.setLoanAmtSanctioned(la.getCustomerTotalLoanRequired());
			   }
			   else if(la.getCibil()>=700)
			   {
				   double d = la.getCustomerTotalLoanRequired()-50000;
				   sl.setLoanAmtSanctioned(d);
			   }
			   else if(la.getCibil()>=650)
			   {
				   double s = la.getCustomerTotalLoanRequired()-100000;
				   sl.setLoanAmtSanctioned(s);
			   }
			   
			}
			
			sl.setSanctionDate(new Date());
			sl.setApplicantName(la.getCustomerName());
			sl.setContactDetails(la.getCustomerMobileNumber());
			sl.setInterestType("Simple");
						
			sl.setLoanTenureInMonth(la.getRequiredTenure());
			sl.setModeOfPayment("In Cash");
			sl.setRemarks("Ok");
			sl.setTermsCondition("The loan must be repaid in full by [repayment date].");
			sl.setStatus("Offered");
			
			slr.save(sl);
		  }		
	}

	@Override
	public void generateIntRate( int sanctionId) 
	{		
		Optional<SanctionLetter> o = slr.findById(sanctionId);
		
		SanctionLetter sl = new SanctionLetter();
		if(o.isPresent()) {
			sl = o.get();
		}
			
		if (sl.getLoanAmtSanctioned() >= 2500000)
		  {    
			    if (sl.getLoanTenureInMonth() <= 36) 
			    {
			        sl.setRateOfInterest(7.2f);  
			    } 
			    else 
			    {
			        sl.setRateOfInterest(8.2f);
			    }
		  }
		  else if (sl.getLoanAmtSanctioned() >= 1000000) 
		  {
			    if (sl.getLoanTenureInMonth() <= 36)
			    {
			        sl.setRateOfInterest(9.2f);  
			    } 
			    else 
			    {
			        sl.setRateOfInterest(10.2f);  
			    }
		  } 
		  else 
		  {
			    
			    if (sl.getLoanTenureInMonth() <= 36) 
			    {
			        sl.setRateOfInterest(11.2f); 
			    }
			    else 
			    {
			        sl.setRateOfInterest(12.2f);  
			    }
		  }
		 slr.save(sl);
	}

	@Override
	public void getMonthlyEmi( int sanctionId) 
	{
		Optional<SanctionLetter> o = slr.findById(sanctionId);
				
		SanctionLetter sl = new SanctionLetter();
		if(o.isPresent()) {
			sl = o.get();
		}
				
		float monthlyInterestRate = sl.getRateOfInterest() / 12 / 100;
				 
		double emi = (sl.getLoanAmtSanctioned() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, sl.getLoanTenureInMonth())) /
			                (Math.pow(1 + monthlyInterestRate, sl.getLoanTenureInMonth()) - 1);
			        
		sl.setMonthlyEmiAmount(emi);
		slr.save(sl);
		}
		  
	}
	

