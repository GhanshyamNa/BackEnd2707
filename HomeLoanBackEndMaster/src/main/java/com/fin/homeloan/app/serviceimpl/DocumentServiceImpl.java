package com.fin.homeloan.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.homeloan.app.model.Documents;
import com.fin.homeloan.app.repository.DocumentRepository;
import com.fin.homeloan.app.servicei.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {
	@Autowired
	DocumentRepository repository;

	@Override
	public void saveDoc(Documents d) {
		repository.save(d);
	}

		
	}

