package com.fin.homeloan.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documents {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer docId; 
	@Lob
	private byte[] photo; 
	@Lob
	private byte[] signature; 
	@Lob
	private byte[] panCard;
	@Lob
	private byte[] addressProof; 
	@Lob
	private byte[] incomeDocument;
	@Lob
	private  byte[] aadharCard;
	@Lob
	private byte[] propertyDocument; 
	@Lob
	private byte[] blankcheque;
	@Lob
	private byte[] g_Stamp;
	@Lob
	private byte[] g_PropertyDoc;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Customer customer;
	
}
