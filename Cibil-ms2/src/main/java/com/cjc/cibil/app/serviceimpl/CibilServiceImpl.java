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
	public Enquiry getCustomer(int customerid ,Integer i ) 
	{
		LocalDate localDate = LocalDate.now();
		Date d = Date.valueOf(localDate);
		
		Optional<Enquiry> o = eri.findById(customerid);
		if(o.isPresent()) {
			Enquiry e = o.get();
			
			Cibil c = new Cibil();
			
			c.setDate(d);
			
			c.setCibilscore(i);	

			if (i >= 750) {
			    c.setStatus("Excellent");
			    c.setRemark("You will get loan as much as you want,Bank is Yours");
			} else if (i >= 650) {
			    c.setStatus("Good");
			    c.setRemark("Good score, You will get Loan");
			} else if (i >= 550) {
			    c.setStatus("Average");
			    c.setRemark("Score is little less, try next time");
			} else if (i >= 300) {
			    c.setStatus("Poor");
			    c.setRemark("Better luck next time ");
			} else {
			   c.setStatus("Invalid Score");
			    c.setRemark("You are Great, work hard and improve score");
			}
			
			e.setCibil(c);
						
			eri.save(e);
			
			return e;
		}
		else
		{
			throw new RuntimeException("Id not found");
		}
	}
}
