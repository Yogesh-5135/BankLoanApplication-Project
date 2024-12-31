package com.cjc.customerdetails.app.repoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.customerdetails.app.model.Enquiry;

@Repository
public interface CustRepoI extends JpaRepository<Enquiry, Integer> {

    Enquiry findByUsernameAndPassword(String username, String password);

  
}
