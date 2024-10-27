package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
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
			
			DependentInforamtion di = null;
			
			di.setDependentMember(s.getDependentMember());
			di.setFamilyIncome(s.getFamilyIncome());
			di.setMaritalStatus(s.getMaritalStatus());
			di.setNoOfChild(s.getNoOfChild());
			di.setDependentInfoId(s.getNoOfFamilyMember());
			
			 dir.save(di);
			l.setDependentInforamtion(di);
			 lri.save(l);
			 
			 return di;
		}
		
		@Override
		public void editData(int dependentInfoId, DependentInforamtion c) 		
		{
			Optional<DependentInforamtion> o = dir.findById(dependentInfoId);
			if(o.isPresent()) {
				DependentInforamtion cd = o.get();
				
				cd.setDependentMember(c.getDependentMember());
				cd.setFamilyIncome(c.getFamilyIncome());
				cd.setMaritalStatus(c.getMaritalStatus());
				cd.setNoOfChild(c.getNoOfChild());
				cd.setNoOfFamilyMember(c.getNoOfFamilyMember());
				
				dir.save(cd);			
			}else
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
