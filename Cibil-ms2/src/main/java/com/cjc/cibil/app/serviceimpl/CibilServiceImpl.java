package com.cjc.cibil.app.serviceimpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cjc.cibil.app.exception.CustomerNotFoundException;
import com.cjc.cibil.app.model.Cibil;
import com.cjc.cibil.app.model.Enquiry;
import com.cjc.cibil.app.repoi.CibilRepoI;
import com.cjc.cibil.app.repoi.EnquiryRepoI;
import com.cjc.cibil.app.servicei.CibilServiceI;

@Service
public class CibilServiceImpl implements CibilServiceI {

	@Autowired
	CibilRepoI cri;

	@Autowired
	EnquiryRepoI eri;

	@Autowired
	JavaMailSender jms;

	@Value("${spring.mail.username}")
	public static String FROM_MAIL;

	private static final Logger log = LoggerFactory.getLogger(CibilServiceImpl.class);

	@Override
	public Enquiry getCustomer(int customerid, Integer i) {
		LocalDate localDate = LocalDate.now();
		Date d = Date.valueOf(localDate);

		Optional<Enquiry> o = eri.findById(customerid);
		if (o.isPresent()) {
			Enquiry e = o.get();

			Cibil c = new Cibil();

			c.setDate(d);

			c.setCibilscore(i);

			if (i >= 750) {
				c.setStatus("Excellent");
				c.setRemark("You will get loan as much as you want,Bank is Yours");
				e.setEnquiryStatus("Approved");
			} else if (i >= 650) {
				c.setStatus("Good");
				c.setRemark("Good score, You will get Loan");
				e.setEnquiryStatus("Approved");
			} else if (i >= 550) {
				c.setStatus("Average");
				c.setRemark("Score is little less, try next time");
				e.setEnquiryStatus("Rejected");
			} else if (i >= 300) {
				c.setStatus("Poor");
				c.setRemark("Better luck next time ");
				e.setEnquiryStatus("Rejected");
			} else {
				c.setStatus("Invalid Score");
				c.setRemark("You are Great, work hard and improve score");
				e.setEnquiryStatus("Rejected");
			}
              
			String customeremail = e.getEmail();
			String enquirystatus = e.getEnquiryStatus();
			String remark = e.getCibil().getRemark();
		
			if(e.getEnquiryStatus().equals("Approved")) {
				
				SimpleMailMessage sm = new SimpleMailMessage();
				sm.setTo(customeremail);
				sm.setFrom(FROM_MAIL);
				sm.setSubject("Enquiry Approved !!");
				sm.setText("Your Loan status is: " + enquirystatus + "\n" + remark);
				e.setEnquiryStatus("Approved for ApplyLoan");
				jms.send(sm);
			}
			else {
				SimpleMailMessage sm = new SimpleMailMessage();
				sm.setTo(customeremail);
				sm.setFrom(FROM_MAIL);
				sm.setSubject("Enquiry Rejected !!");
				sm.setText("Your Loan status is: " + enquirystatus + "\n" + remark);
				e.setEnquiryStatus("Closed");
				jms.send(sm);
			}
			e.setCibil(c);
			eri.save(e);
			
			return e;
		} else {
			log.error("Please Enter Valid Customer Id");
			throw new CustomerNotFoundException("Id not found");
		}
	}
}
