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
import com.cjc.customerdetails.app.repoi.loanapplicationform.LocalAddressRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.LocalAddressServiceI;

@Service
public class LocalAddressServiceImpl implements LocalAddressServiceI
{
  @Autowired
  LocalAddressRepoI lar;

  @Autowired
  CustomerAddressRepoI car;
  
@Override
public LocalAddress saveLocalAddress(LocalAddress a,int customerAddressId) 
{
	CustomerAddress l = null;
	
	Optional<CustomerAddress> ol = car.findById(customerAddressId);
	if(ol.isPresent())
	{
		 l = ol.get();
	}
	else
	{
		throw new IdNotFountException("Id Not Found");
	}
	
	LocalAddress la = null;
	
	try {
		la.setAreaname(a.getAreaname());
		la.setCityname(a.getCityname());
		la.setDistrict(a.getDistrict());
		la.setState(a.getState());
		la.setPincode(a.getPincode());
		la.setHouseNumber(a.getHouseNumber());
		la.setStreetName(a.getStreetName());
		
	    } 
	 catch (Exception e) 
	    {
		  e.printStackTrace();
	    }
	lar.save(la);
	l.setLocalAddress(la);
	car.save(l);
	
	return la;
}

@Override
public LocalAddress getSingleLocalAddress(int localAddressId) 
{
	Optional<LocalAddress> ol = lar.findById(localAddressId);
	if(ol.isPresent())
	{
		LocalAddress a = ol.get();
		
		return a;
	}
	else
	{
		throw new IdNotFountException("Local Address Not Found");
	}
	
}

@Override
public LocalAddress updateLocalAddress(int localAddressId, LocalAddress a) 
{
	Optional<LocalAddress> ol = lar.findById(localAddressId);
	if(ol.isPresent())
	{
		LocalAddress a1 = ol.get();
		a1.setAreaname(a.getAreaname());
		a1.setCityname(a.getCityname());
		a1.setDistrict(a.getDistrict());
		a1.setState(a.getState());
		a1.setPincode(a.getPincode());
		a1.setHouseNumber(a.getHouseNumber());
		a1.setStreetName(a.getStreetName());
		
		lar.save(a1);
	
		return a1;
	}
	else
	{
		throw new IdNotFountException("Local Address Not Found");
	}
}

@Override
public void deleteLocalAddress(int localAddressId) 
{
	
	lar.deleteById(localAddressId);
}
}
