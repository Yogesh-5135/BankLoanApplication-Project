package com.cjc.cibil.app.repoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cjc.cibil.app.model.Enquiry;
@Repository
public interface EnquiryRepoI extends JpaRepository<Enquiry, Integer>{

}
