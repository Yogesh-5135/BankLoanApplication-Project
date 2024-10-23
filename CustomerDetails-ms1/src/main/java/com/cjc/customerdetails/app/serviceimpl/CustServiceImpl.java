package com.cjc.customerdetails.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.model.Enquiry;
import com.cjc.customerdetails.app.repoi.CustRepoI;
import com.cjc.customerdetails.app.servicei.CustServiceI;
@Service
public class CustServiceImpl implements CustServiceI{

	@Autowired
	CustRepoI cri;

	@Override
	public Enquiry saveStudent(Enquiry s) {
		cri.save(s);
		return s;
	}
	
	
	@Override
	public void editCustomer(int customerid, Enquiry c)
	{		
		Optional<Enquiry> o = cri.findById(customerid);
		if(o.isPresent()) {
			Enquiry cd = o.get();
			
			cd.setFname(c.getFname());
			cd.setLname(c.getLname());
			cd.setEmail(c.getEmail());
			cd.setAge(c.getAge());
			cd.setMobileno(c.getMobileno());
			cd.setPancard(c.getPancard());
			
			cri.save(cd);			
		}	
	}

		@Override
		public Enquiry getData(int customerid) 
		{		
			Optional<Enquiry> o = cri.findById(customerid);
			if(o.isPresent()) {
				Enquiry cd = o.get();
				return cd;
			}
			else
			{
				throw new RuntimeException("Id not found");
			}
		}


		@Override
		public List<Enquiry> getAllData() 
		{
			List<Enquiry> cd = cri.findAll();
			return cd;			
		}

	

}	