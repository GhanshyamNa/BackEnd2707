package com.fin.homeloan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.homeloan.app.model.Sanction;

@Repository
public interface SanctionRepository extends JpaRepository<Sanction, Integer> {

}
