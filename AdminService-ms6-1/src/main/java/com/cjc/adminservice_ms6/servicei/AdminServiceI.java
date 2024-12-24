package com.cjc.adminservice_ms6.servicei;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cjc.adminservice_ms6.model.EmployeeDetails;

public interface AdminServiceI {

	void saveAdmin(String json, MultipartFile empImage, MultipartFile empPancard);

	EmployeeDetails getSingleAdmin(int id);

	List<EmployeeDetails> getAllAdmin();

	void deleteAdmin(int id);

	void editAdmin(int empId, String json, MultipartFile empImage, MultipartFile empPancard);

	EmployeeDetails getEmployee(String username, String password);

	

}
