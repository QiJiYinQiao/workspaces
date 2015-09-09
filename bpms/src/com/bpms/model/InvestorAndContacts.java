package com.bpms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 投资人与联系人关联表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_bp_investor_and_contacts")
public class InvestorAndContacts implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String investorId;
	private String contactId;

	public InvestorAndContacts() {
	}

	public InvestorAndContacts(String id) {
		this.id = id;
	}

	public InvestorAndContacts(String id, String investorId, String contactId) {
		super();
		this.id = id;
		this.investorId = investorId;
		this.contactId = contactId;
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

	@Column(name = "INVESTOR_ID", length = 40)
	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	@Column(name = "CONTACT_ID", length = 40)
	public String getContactId() {
		return this.contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

}
