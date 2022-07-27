package com.fin.homeloan.app.servicei;

import java.util.List;

import com.fin.homeloan.app.model.Cibil;

public interface CibilService {

	List getAll();

	void saveData(Cibil c);

	void updateData(Cibil c);

}
