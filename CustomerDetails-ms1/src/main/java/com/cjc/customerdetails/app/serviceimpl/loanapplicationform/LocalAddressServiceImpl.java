package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidDataException;
import com.cjc.customerdetails.app.model.loanapplicationform.CustomerAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.LocalAddress;
import com.cjc.customerdetails.app.repoi.loanapplicationform.CustomerAddressRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LocalAddressRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.LocalAddressServiceI;

@Service
public class LocalAddressServiceImpl implements LocalAddressServiceI
{
  @Autowired
  LocalAddressRepoI lar;

  @Autowired
  CustomerAddressRepoI car;
  
  private static final Logger log = LoggerFactory.getLogger(LocalAddressServiceImpl.class);
  
  @Override
  public void saveLocalAddress(LocalAddress a, int customerAddressId) {
      
      
      CustomerAddress ca = new CustomerAddress();
      Optional<CustomerAddress> ol = car.findById(customerAddressId);
      if (ol.isPresent()) {
          ca = ol.get();
         
      } else {
          log.error("CustomerAddress not found with id: {}", customerAddressId);
          throw new IdNotFountException("Id Not Found");
      }

      LocalAddress la = new LocalAddress();
      
      
      log.info("Validating area name: {}", a.getAreaname());
      String a1 = a.getAreaname();
      char[] b = a1.toCharArray();
      for (int i = 0; i < b.length; i++) {
          if (Character.isLetter(b[i]) || b[i] == 32) {
              la.setAreaname(a1);
          } else {
              log.error("Invalid character in area name: {}", a1);
              throw new InvalidDataException("Areaname does not contain any special character or Number");
          }
      }

      
      String c = a.getCityname();
      char[] d = c.toCharArray();
      for (int i = 0; i < d.length; i++) {
          if (Character.isLetter(d[i]) || d[i] == 32) {
              la.setCityname(c);
          } else {
              log.error("Invalid character in city name: {}", c);
              throw new InvalidDataException("Cityname does not contain any special character or Number");
          }
      }

      
      String e = a.getDistrict();
      char[] f = e.toCharArray();
      for (int i = 0; i < f.length; i++) {
          if (Character.isLetter(f[i]) || f[i] == 32) {
              la.setDistrict(e);
          } else {
              log.error("Invalid character in district name: {}", e);
              throw new InvalidDataException("District Name does not contain any special character or Number");
          }
      }

      
      String g = a.getState();
      char[] h = g.toCharArray();
      for (int i = 0; i < h.length; i++) {
          if (Character.isLetter(h[i]) || h[i] == 32) {
              la.setState(g);
          } else {
              log.error("Invalid character in state name: {}", g);
              throw new InvalidDataException("State Name does not contain any special character or Number");
          }
      }

      
      long i = a.getPincode();
      int count = 0;
      for (long no = i; no > 0; no = no / 10) {
          count++;
      }
      if (count != 6) {
          log.error("Invalid pincode: {}", i);
          throw new InvalidDataException("Pincode is invalid. It must be 6 digits.");
      } else {
          la.setPincode(i);
      }

     
      int j = a.getHouseNumber();
      if (j <= 1 || j >= 99) {
          log.error("Invalid house number: {}", j);
          throw new InvalidDataException("House number must be between 1 and 99.");
      } else {
          la.setHouseNumber(j);
      }

     
      String k = a.getStreetName();
      char[] m = k.toCharArray();
      for (int i1 = 0; i1 < m.length; i1++) {
          if (Character.isLetter(m[i1]) || m[i1] == 32) {
              la.setStreetName(k);
          } else {
              log.error("Invalid character in street name: {}", k);
              throw new InvalidDataException("StreetName does not contain any special character or Number");
          }
      }

    
      lar.save(la);
      ca.setLocalAddress(la);
      car.save(ca);

     
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
		log.error("Please Enter Valid Id");
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
		log.error("Please Enter Valid Id");
		throw new IdNotFountException("Local Address Not Found");
	}
}

@Override
public void deleteLocalAddress(int localAddressId) 
{
	
	lar.deleteById(localAddressId);
}
}
