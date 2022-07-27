package com.fin.homeloan.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.homeloan.app.model.Cibil;
import com.fin.homeloan.app.repository.CibilRepository;
import com.fin.homeloan.app.servicei.CibilService;
@Service
public class CibilServiceImpl implements CibilService {

	@Autowired
	CibilRepository cr;


	@Override
	public List getAll() {
		return (List) cr.findAll();
	}

	@Override
	public void saveData(Cibil c) {

		cr.save(c);
		
	}

	@Override
	public void updateData(Cibil c) {
		
		cr.save(c);
		
	}
	
}
