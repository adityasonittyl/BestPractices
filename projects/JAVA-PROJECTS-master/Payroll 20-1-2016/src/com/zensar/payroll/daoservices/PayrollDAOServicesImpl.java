package com.zensar.payroll.daoservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zensar.payroll.beans.Associate;

public class PayrollDAOServicesImpl implements PayrollDAOServices {

	static int associateId = 100;

	Map<Integer, Associate> map = new HashMap<Integer, Associate>();

	@Override
	public int insertAssociate(Associate associate) {
		associate.setAssociateId(associateId);
		map.put(associateId, associate);
		associateId++;
		return associate.getAssociateId();
	}

	@Override
	public boolean updateAssociate(Associate associate) {
		map.put(associate.getAssociateId(), associate);
		return false;
	}

	@Override
	public Associate getAssociate(int associateId) {
		return map.get(associateId);
	}

	@Override
	public ArrayList<Associate> getAssociates() {
		ArrayList<Associate> associates = (ArrayList<Associate>) map.values();
		return associates;
	}

	@Override
	public boolean classPayrollDAOServices() {
		// TODO Auto-generated method stub
		return false;
	}

}
