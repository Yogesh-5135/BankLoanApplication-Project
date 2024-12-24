package com.cjc.adminservice_ms6.repoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.adminservice_ms6.model.EmployeeDetails;

@Repository
public interface AdminServiceRepoI extends JpaRepository<EmployeeDetails, Integer>{

	EmployeeDetails findByUsernameAndPassword(String username, String password);

}
