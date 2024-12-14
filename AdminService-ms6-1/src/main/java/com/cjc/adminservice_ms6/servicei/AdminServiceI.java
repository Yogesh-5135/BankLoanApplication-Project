package com.cjc.adminservice_ms6.servicei;

import java.util.List;

import com.cjc.adminservice_ms6.model.AdminService;

public interface AdminServiceI {

	void saveAdmin(AdminService a);

	AdminService getSingleAdmin(int id);

	List<AdminService> getAllAdmin();

	void deleteAdmin(int id);

	void editAdmin(int id, AdminService a);

}
