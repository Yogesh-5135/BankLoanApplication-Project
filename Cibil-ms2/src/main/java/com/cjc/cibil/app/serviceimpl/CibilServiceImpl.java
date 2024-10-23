package com.cjc.cibil.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.cibil.app.repoi.CibilRepoI;
import com.cjc.cibil.app.servicei.CibilServiceI;

@Service
public class CibilServiceImpl implements CibilServiceI{

	@Autowired
	CibilRepoI cri;
}
