package com.cjc.customerdetails.app.servicei;

import java.util.List;

import com.cjc.customerdetails.app.model.Enquiry;

public interface CustServiceI {

	
	void editCustomer(int customerid, Enquiry c);

	Enquiry getData(int customerid);

	List<Enquiry> getAllData();
    
	Enquiry getCustomer(String username,String password);

	void deleteData(int customerid);


	void deleteAllCustomer();


	List<Enquiry> getAllApprovedData();


	List<Enquiry> getAllRejectedData();


	Enquiry saveCustomer(Enquiry s);


}
