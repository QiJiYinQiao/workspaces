package com.bpms.model;

// Generated 2015-6-16 10:13:19 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * LoanorderAndContacts generated by hbm2java
 */
@Entity
@Table(name = "t_bp_loanorder_and_contacts")
public class LoanorderAndContacts implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String contactId;
	private String loanOrderId;

	public LoanorderAndContacts() {
	}

	public LoanorderAndContacts(String id) {
		this.id = id;
	}

	public LoanorderAndContacts(String id, String contactId, String loanOrderId) {
		super();
		this.id = id;
		this.contactId = contactId;
		this.loanOrderId = loanOrderId;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CONTACT_ID", length = 40)
	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	@Column(name = "LOAN_ORDER_ID")
	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

}
