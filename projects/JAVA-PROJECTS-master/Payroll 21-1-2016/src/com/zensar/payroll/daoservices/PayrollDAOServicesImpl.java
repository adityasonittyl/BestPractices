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
		if(map.put(associate.getAssociateId(), associate)!=null)
			return true;
		else
			return false;
	}

	@Override
	public Associate getAssociate(int associateId) {
		if(map.get(associateId)!=null)
			return map.get(associateId);
		else
			return null;
	}

	@Override
	public ArrayList<Associate> getAssociates() {
		ArrayList<Associate> associates = new ArrayList<Associate>(map.values());
		if(associates.isEmpty())
			return null;
		else
			return associates;
	}

	@Override
	public boolean closePayrollDAOServices() {
		// TODO Auto-generated method stub
		return false;
	}

}
