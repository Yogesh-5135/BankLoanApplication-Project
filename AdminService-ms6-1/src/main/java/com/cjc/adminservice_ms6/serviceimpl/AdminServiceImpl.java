package com.cjc.adminservice_ms6.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.adminservice_ms6.model.AdminService;
import com.cjc.adminservice_ms6.repoi.AdminServiceRepoI;
import com.cjc.adminservice_ms6.servicei.AdminServiceI;

@Service
public class AdminServiceImpl implements AdminServiceI{
	
	@Autowired
	AdminServiceRepoI ari;

	@Override
	public void saveAdmin(AdminService a) 
	{
		ari.save(a);		
	}

	@Override
	public AdminService getSingleAdmin(int id) 
	{
		
		Optional<AdminService> o = ari.findById(id);
		AdminService a = new AdminService();
		if(o.isPresent()) {
			a = o.get();
			return a;
		}else
		{ 
			throw new RuntimeException("Id not found");
		}
	}

	@Override
	public List<AdminService> getAllAdmin() 
	{
		List<AdminService> l = ari.findAll();
		return l;
	}

	@Override
	public void deleteAdmin(int id) 
	{
		ari.deleteById(id);
	}

	@Override
	public void editAdmin(int id, AdminService a) 
	{		
		Optional<AdminService> o = ari.findById(id);
		AdminService ad = new AdminService();
		if(o.isPresent()) {
			ad = o.get();
			ad.setUsername(a.getUsername());
			ad.setPassword(a.getPassword());
			ad.setEmail(a.getEmail());
			ad.setMobileno(a.getMobileno());
			ad.setAge(a.getAge());
			ad.setDesignation(a.getDesignation());
			
			ari.save(ad);
		}
	}

}
