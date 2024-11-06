package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.EmailNotValidException;
import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidDataException;
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
	
	private static final Logger log = LoggerFactory.getLogger(LoanApplyServiceImpl.class);

	@Override
	public void saveLoanApplication(LoanApplication ld) 
	{
		LoanApplication la = new LoanApplication();
		
		String a = ld.getCustomerName();
		char[]b = a.toCharArray();
		for(int i=0;i<b.length;i++)
		{
		if (b[i]>='a'&& b[i]<='z'|| b[i]>='A'&& b[i]<='Z'|| b[i]==32)
		{
			la.setCustomerName(a); 	
		}
		else
		{
			log.error("please Use Letters Only");
			throw new InvalidDataException("Customer Name does not contain any special character or Number");
		}
		}
		
		String c = ld.getDob();
		if(c!=null)
		{
			la.setDob(c);
		}
		else
		{ 
			log.error("Please Enter valid Dob");
			throw new InvalidDataException("Please Enter Valid Dob");
		}
		
		int d = ld.getCustomerAge();
		if(d >= 21 && d <= 58)
		{
			la.setCustomerAge(d);
		}
		else
		{
			log.error("Please Enter valid Age");
			throw new InvalidDataException("Customer Age is between 21 to 58 years");
		}
		
		int e = ld.getRequiredTenure();
		if(e>=1 && e<=60)
		{
			la.setRequiredTenure(e);
		}
		else
		{
			log.error("Please Enter valid Tenure");
			throw new InvalidDataException("You paid Your Insatallments between 1 to 30");
		}

		String f = ld.getCustomerGender();
		if(f.equals("Male")||f.equals("Female")||f.equals("male")||f.equals("female")||f.equals("M")||f.equals("F")
		  ||f.equals("m")||f.equals("f")||f.equals("MALE")||f.equals("FEMALE"))
		{
			la.setCustomerGender(f);
		}
		else
		{
			log.error("Please Enter Valid Gender");
			throw new InvalidDataException("Please Enter Valid Gender");
		}
		
		String g = ld.getCustomerEmail();
		if(g.endsWith("@gmail.com") || g.endsWith("@yahoo.com")) 
		{
			la.setCustomerEmail(g);
		}
		else 
		{
		    log.error("Please Enter Valid Email");
			throw new EmailNotValidException(" Email should end with @gmail.com or @yahoo.com ");
			
		}
		
		double h = ld.getCustomerMobileNumber();
		int count = 0;
		for (double no = h; no > 0; no = no / 10) 
		{
			count++;
		}
		if (count > 10 || count<10) 
		{
			log.error("Please Enter Valid Mobile No");
			throw new MobNoException("Mobno invalid ..Enter only 10 numbers");
		}
		else 
		{
			la.setCustomerMobileNumber(h);
		}
	
		double k = ld.getCustomerAdditionalMobileNumber();
        int count1 = 0;
		for (double no = k; no > 0; no = no / 10) 
		{
			count1++;
		}
		if (count1 > 10 || count1<10) 
		{
			log.error("Please Enter Valid Mobile No");
			throw new MobNoException("Mobno invalid ..Enter only 10 numbers");
		}
		else 
		{
			la.setCustomerAdditionalMobileNumber(k);
		}
		
		double l = ld.getCustomerAmountPaidForHome();
		if(l>=1000000 && l<=5000000)
		{
			la.setCustomerAmountPaidForHome(l);
		}
		else
		{
			log.error("Please Enter Valid Amount");
			throw new InvalidDataException("Customer must paid between 1000000 to 5000000 for home");
		}
		
		double m = ld.getCustomerTotalLoanRequired();
		if(m>=1000000 && m<=4000000)
		{
			la.setCustomerAmountPaidForHome(m);
		}
		else
		{
			log.error("Please Enter Valid Amount");
			throw new InvalidDataException(" Customer is Eligible to get loan between 1000000 to 4000000 ");
		}
		List<Enquiry> en = csi.getAllApprovedData();
		for (Enquiry enquiry : en) {
			if(enquiry.getMobileno() == la.getCustomerMobileNumber() ) 
			{		
				int i = enquiry.getCibil().getCibilscore();
				la.setCibil(i);
				la.setLoanStatus("Submitted");
				lri.save(la);					
			}	
			else 
			{
				log.error("Please Enter Valid Mobile No");
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
			log.error("Please Enter Valid Id");
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
			return l;
			
		}
		else
		{
			log.error("Please Enter Valid Id");
			throw new IdNotFountException("LoanApplication Not Found");
		}
		
		
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
