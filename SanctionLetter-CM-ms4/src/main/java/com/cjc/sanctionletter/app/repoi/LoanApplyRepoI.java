package com.cjc.sanctionletter.app.repoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.sanctionletter.app.model.LoanApplication;

@Repository
public interface LoanApplyRepoI extends JpaRepository<LoanApplication, Integer>{

}
