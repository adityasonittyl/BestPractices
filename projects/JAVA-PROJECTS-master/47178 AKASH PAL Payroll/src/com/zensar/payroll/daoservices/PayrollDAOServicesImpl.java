package com.zensar.payroll.daoservices;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.zensar.payroll.beans.Associate;

public class PayrollDAOServicesImpl implements PayrollDAOServices {

	static int associateId=1000;
	Map<Integer, Associate> map = new HashMap<Integer, Associate>();
	
	public PayrollDAOServicesImpl() {
		ObjectInputStream objectInputStream = null;
		try {
			FileInputStream fos = new FileInputStream(".\\src\\resources\\storeAssociates.txt");
			objectInputStream = new ObjectInputStream(fos);
			map = (Map<Integer, Associate>) objectInputStream.readObject();
			associateId = 1000 + map.size();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();		
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}finally{
			if(objectInputStream!=null)
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public int insertAssociate(Associate associate) {
		associate.setAssociateId(associateId);
		map.put(associateId, associate);
		System.out.println(associateId);
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
		ObjectOutputStream objectOutputStream = null;
		try {
			FileOutputStream fos = new FileOutputStream(".\\src\\resources\\storeAssociates.txt");
			objectOutputStream = new ObjectOutputStream(fos);
			objectOutputStream.writeObject(map);
			return true;
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		finally{
			if(objectOutputStream!=null)
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
		}
		return false;
	} 
}
