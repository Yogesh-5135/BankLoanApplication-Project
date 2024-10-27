package com.cjc.customerdetails.app.servicei.loanapplicationform;

import com.cjc.customerdetails.app.model.loanapplicationform.PermanentAddress;

public interface PermanentAddressServiceI {

	public PermanentAddress savePermanentAddress(PermanentAddress a, int loanid);

	public PermanentAddress getSinglePermanentAddress(int permanentAddressId);

	public PermanentAddress updatePermanentAddress(int permanentAddressId, PermanentAddress a);

	public void deletePermanentAddress(int permanentAddressId);

}
