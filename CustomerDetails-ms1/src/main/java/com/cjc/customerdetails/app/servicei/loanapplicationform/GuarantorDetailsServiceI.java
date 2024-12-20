package com.cjc.customerdetails.app.servicei.loanapplicationform;

import com.cjc.customerdetails.app.model.loanapplicationform.GuarantorDetails;

public interface GuarantorDetailsServiceI {

	void saveData(GuarantorDetails s, int loanid);

	void editData(int guarantorId, GuarantorDetails c);

	GuarantorDetails getData(int guarantorId);

	void deleteData(int guarantorId);

}
