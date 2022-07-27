package com.fin.homeloan.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class LoanDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer loanDetailId; 
	private Long totalLoanRequired; 
	private Integer tenureofLoan= 10; 
	private Float annualInterest = 7.5f;
	private Float payableLoan;
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
}
