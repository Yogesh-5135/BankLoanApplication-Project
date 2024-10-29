package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidDataException;
import com.cjc.customerdetails.app.model.Enquiry;
import com.cjc.customerdetails.app.model.loanapplicationform.DependentInforamtion;
import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.repoi.loanapplicationform.DependentInformationRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.DependentInformationServiceI;

@Service
public class DependentInformationServiceImpl implements DependentInformationServiceI
{
 @Autowired
 DependentInformationRepoI dir;
 
 @Autowired
 LoanApplyRepoI lri;
 
 private static final Logger log = LoggerFactory.getLogger(DependentInformationServiceImpl.class);

		@Override
		public DependentInforamtion saveData(DependentInforamtion s ,int loanid) 
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
			
			DependentInforamtion di = new DependentInforamtion();
			
			

			String dependentMember = s.getDependentMember();
			char[] dependentChars = dependentMember.toCharArray();
			boolean validDependent = true;

			for (char c : dependentChars) {
			    if (!(Character.isLetter(c) || Character.isWhitespace(c))) {
			        validDependent = false;
			        break;
			    }
			}

			if (validDependent) {
			    di.setDependentMember(dependentMember);
			} else {
			    log.error("Please Use Letters Only");
			    throw new InvalidDataException("Dependent member should not contain any special character or Number");
			}

			
			double familyIncome = s.getFamilyIncome();
			if (familyIncome < 0) {
			    log.error("Family income must be a non-negative value");
			    throw new InvalidDataException("Family income should be non-negative");
			}
			di.setFamilyIncome(familyIncome);

			
			String maritalStatus = s.getMaritalStatus();
			char[] maritalChars = maritalStatus.toCharArray();
			boolean validMarital = true;

			for (char c : maritalChars) {
			    if (!(Character.isLetter(c) || Character.isWhitespace(c))) {
			        validMarital = false;
			        break;
			    }
			}

			if (validMarital) {
			    di.setMaritalStatus(maritalStatus);
			} else {
			    log.error("Please Use Letters Only");
			    throw new InvalidDataException("Marital status should not contain any special character or Number");
			}

			
			int noOfChild = s.getNoOfChild();
			if (noOfChild < 0) {
			    log.error("Number of children must be a non-negative value");
			    throw new InvalidDataException("Number of children should be non-negative");
			}
			di.setNoOfChild(noOfChild);

			
			int noOfFamilyMember = s.getNoOfFamilyMember();
			if (noOfFamilyMember < 1) { 
			    log.error("Number of family members must be at least 1");
			    throw new InvalidDataException("Number of family members should be at least 1");
			}
			di.setNoOfFamilyMember(noOfFamilyMember);

						
			 dir.save(di);
			l.setDependentInforamtion(di);
			 lri.save(l);
			 
			 return di;
		}
		
		@Override
		public void editData(int dependentInfoId, DependentInforamtion c) 		
		{
			Optional<DependentInforamtion> o = dir.findById(dependentInfoId);
			if(o.isPresent()) 
			{
				DependentInforamtion cd = o.get();
				
				cd.setDependentMember(c.getDependentMember());
				cd.setFamilyIncome(c.getFamilyIncome());
				cd.setMaritalStatus(c.getMaritalStatus());
				cd.setNoOfChild(c.getNoOfChild());
				cd.setNoOfFamilyMember(c.getNoOfFamilyMember());
				
				dir.save(cd);			
			}
			else
			{
				throw new RuntimeException("Id not found");
			}	
			
		}
		
		@Override
		public DependentInforamtion getData(int dependentInfoId)
		{
			DependentInforamtion cd = new DependentInforamtion();
			Optional<DependentInforamtion> o = dir.findById(dependentInfoId);
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
		public void deleteData(int dependentInfoId) 
		{
			dir.deleteById(dependentInfoId);
			
		}
		 	
}
