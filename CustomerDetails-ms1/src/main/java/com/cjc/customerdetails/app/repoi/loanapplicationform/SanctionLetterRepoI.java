package com.cjc.customerdetails.app.repoi.loanapplicationform;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cjc.customerdetails.app.model.loanapplicationform.SanctionLetter;

@Repository
public interface SanctionLetterRepoI extends JpaRepository<SanctionLetter,Integer>
{

}
