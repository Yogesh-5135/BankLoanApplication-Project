package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidDataException;
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
	
	LocalAddress la = new LocalAddress();
	
	String a1 = a.getAreaname();
	char[]b = a1.toCharArray();
	for(int i=0;i<b.length;i++)
	{
	if (b[i]>='a'&& b[i]<='z'|| b[i]>='A'&& b[i]<='Z'|| b[i]==32)
	{
		la.setAreaname(a1); 	
	}
	else
	{
		throw new InvalidDataException("Areaname does not contain any special character or Number");
	}
	}
	
	String c = a.getCityname();
	char[]d = c.toCharArray();
	for(int i=0;i<d.length;i++)
	{
	if (d[i]>='a'&& d[i]<='z'|| d[i]>='A'&& d[i]<='Z'|| d[i]==32)
	{
		la.setCityname(c); 	
	}
	else
	{
		throw new InvalidDataException("Cityname does not contain any special character or Number");
	}
	}
    
	String e = a.getDistrict();
	char[]f = e.toCharArray();
	for(int i=0;i<f.length;i++)
	{
	if (f[i]>='a'&& f[i]<='z'|| f[i]>='A'&& f[i]<='Z'|| f[i]==32)
	{
		la.setDistrict(e); 	
	}
	else
	{
		throw new InvalidDataException("District Name does not contain any special character or Number");
	}
	}
    
	String g = a.getState();
	char[]h = g.toCharArray();
	for(int i=0;i<h.length;i++)
	{
	if (h[i]>='a'&& h[i]<='z'|| h[i]>='A'&& h[i]<='Z'|| h[i]==32)
	{
		la.setState(g); 	
	}
	else
	{
		throw new InvalidDataException("State Name does not contain any special character or Number");
	}
	}
	
	long i = a.getPincode();
	int count = 0;
	for (long no = i; no > 0; no = no / 10) 
	{
		count++;
	}
	if (count > 6 || count<6) 
	{
		throw new InvalidDataException("Pincode Is Invalid ..Enter only 6 numbers");
	}
	else 
	{
		la.setPincode(i);
	}
	
	int j = a.getHouseNumber();
	if (j <= 1 || j >= 99) 
	{ 
        throw new InvalidDataException("House number must be between 1 and 99.");
    }
	else
	{
		la.setHouseNumber(j);
	}
	
	String k = a.getStreetName();
	char[]m = k.toCharArray();
	for(int i1=0;i1<m.length;i++)
	{
	if (m[i1]>='a'&& m[i1]<='z'|| m[i1]>='A'&& m[i1]<='Z'|| m[i1]==32)
	{
		la.setStreetName(k); 	
	}
	else
	{
		throw new InvalidDataException("StreetName does not contain any special character or Number");
	}
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
