package com.cjc.customerdetails.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.CustRepoI;
import com.cjc.customerdetails.app.servicei.CustServiceI;
@Service
public class CustServiceImpl implements CustServiceI{

	@Autowired
	CustRepoI cri;
	
	
	
}	