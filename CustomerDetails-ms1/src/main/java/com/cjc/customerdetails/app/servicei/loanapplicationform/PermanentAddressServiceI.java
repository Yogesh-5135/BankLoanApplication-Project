package com.cjc.customerdetails.app.servicei.loanapplicationform;

import com.cjc.customerdetails.app.model.loanapplicationform.PermanentAddress;

public interface PermanentAddressServiceI {

	public void savePermanentAddress(PermanentAddress a, int customerAddressId);

	public PermanentAddress getSinglePermanentAddress(int permanentAddressId);

	public PermanentAddress updatePermanentAddress(int permanentAddressId, PermanentAddress a);

	public void deletePermanentAddress(int permanentAddressId);

}
