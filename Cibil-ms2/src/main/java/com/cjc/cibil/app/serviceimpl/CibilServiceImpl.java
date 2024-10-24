package com.cjc.cibil.app.serviceimpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.cibil.app.model.Cibil;
import com.cjc.cibil.app.model.Enquiry;
import com.cjc.cibil.app.repoi.CibilRepoI;
import com.cjc.cibil.app.repoi.EnquiryRepoI;
import com.cjc.cibil.app.servicei.CibilServiceI;

@Service
public class CibilServiceImpl implements CibilServiceI{

	@Autowired
	CibilRepoI cri;
	@Autowired
	EnquiryRepoI eri;

	@Override
	public Enquiry getCustomer(int customerid , Cibil c,Integer i ) 
	{
		LocalDate localDate = LocalDate.now();
		Date d = Date.valueOf(localDate);
		
		Optional<Enquiry> o = eri.findById(customerid);
		if(o.isPresent()) {
			Enquiry e = o.get();
			
			e.setCibil(c);
			
			e.getCibil().setDate(d);
			
			e.getCibil().setCibilscore(i);
			
			eri.save(e);
			return e;
		}
		else
		{
			throw new RuntimeException("Id not found");
		}
	}
}
