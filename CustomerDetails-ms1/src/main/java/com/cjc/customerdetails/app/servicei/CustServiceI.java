package com.cjc.customerdetails.app.servicei;

import java.util.List;

import com.cjc.customerdetails.app.model.Enquiry;

public interface CustServiceI {

	Enquiry saveStudent(Enquiry s);

	
	void editCustomer(int customerid, Enquiry c);

	Enquiry getData(int customerid);

	List<Enquiry> getAllData();

}
