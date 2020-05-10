package com.zensar.payroll.daoservices;

import java.util.ArrayList;

import com.zensar.payroll.beans.Associate;

public interface PayrollDAOServices {

	int insertAssociate(Associate associate);

	boolean updateAssociate(Associate associate);

	Associate getAssociate(int associateId);

	ArrayList<Associate> getAssociates();

	boolean closePayrollDAOServices();
}
