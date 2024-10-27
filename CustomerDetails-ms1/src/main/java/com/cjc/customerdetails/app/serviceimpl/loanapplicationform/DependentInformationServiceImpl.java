package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.model.Enquiry;
import com.cjc.customerdetails.app.model.loanapplicationform.DependentInforamtion;
import com.cjc.customerdetails.app.repoi.loanapplicationform.DependentInformationRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.DependentInformationServiceI;

@Service
public class DependentInformationServiceImpl implements DependentInformationServiceI
{
 @Autowired
 DependentInformationRepoI dir;

		@Override
		public DependentInforamtion saveData(DependentInforamtion s) 
		{
			return dir.save(s);
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
