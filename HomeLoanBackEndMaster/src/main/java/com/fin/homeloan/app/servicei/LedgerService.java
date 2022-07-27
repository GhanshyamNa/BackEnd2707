package com.fin.homeloan.app.servicei;

import java.util.List;

import javax.mail.MessagingException;

import com.fin.homeloan.app.model.EmailSender;
import com.fin.homeloan.app.model.Ledger;

public interface LedgerService {

	List<Ledger> getLedger();

	Ledger saveLedger(Ledger l);

	void email(EmailSender e);

	void sendEmailWithAttachment(EmailSender e) throws MessagingException;

	



}
