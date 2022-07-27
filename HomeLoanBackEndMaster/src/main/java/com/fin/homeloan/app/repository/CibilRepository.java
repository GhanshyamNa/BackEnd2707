package com.fin.homeloan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.homeloan.app.model.Cibil;

@Repository
public interface CibilRepository extends JpaRepository<Cibil, Integer> {

}
