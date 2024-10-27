package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.model.loanapplicationform.CustomerAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.model.loanapplicationform.LocalAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.PermanentAddress;
import com.cjc.customerdetails.app.repoi.loanapplicationform.CustomerAddressRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.CustomerAddressServiceI;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressServiceI
{
 @Autowired
 CustomerAddressRepoI car;

 @Autowired
 LoanApplyRepoI lri;
 
@Override
public CustomerAddress saveCustomerAddress(CustomerAddress ca,int loanid) 
{
LoanApplication l = null;
	
	Optional<LoanApplication> ol = lri.findById(loanid);
	if(ol.isPresent())
	{
		 l = ol.get();
	}
	else
	{
		throw new IdNotFountException("Id Not Found");
	}
	
	CustomerAddress ca1=null;
	
	try {
		ca1.setPermanentAddress(ca.getPermanentAddress());
		ca1.setLocalAddress(ca.getLocalAddress());
	    } 
	 catch (Exception e) 
	    {
		  e.printStackTrace();
	    }
	
	l.setCustomerAddress(ca1);
	lri.save(l);
	
	return ca1;
}

@Override
public CustomerAddress getSingleCustomerAddress(int customerAddressId) 
{
	Optional<CustomerAddress> oc = car.findById(customerAddressId);
	if(oc.isPresent())
	{
		CustomerAddress ca = oc.get();
		
		return ca;
	}
	else
	{
		throw new IdNotFountException("Customer Address Not Found");
	}
	
}

@Override
public CustomerAddress updateCustomerAddress(int customerAddressId, CustomerAddress ca) 
{
	Optional<CustomerAddress> oc = car.findById(customerAddressId);
	if(oc.isPresent())
	{
		CustomerAddress c = oc.get();
		c.setLocalAddress(ca.getLocalAddress());
		c.setPermanentAddress(ca.getPermanentAddress());
		car.save(c);
		
		return c;
	}
	else
	{
		throw new IdNotFountException("Customer Address Not Found");
	}
	
}

@Override
public void deleteCustomerAddress(int customerAddressId) 
{
	car.deleteById(customerAddressId);
	
}
}
