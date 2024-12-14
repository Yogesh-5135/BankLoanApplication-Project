package com.cjc.adminservice_ms6.repoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.adminservice_ms6.model.AdminService;

@Repository
public interface AdminServiceRepoI extends JpaRepository<AdminService, Integer>{

}
