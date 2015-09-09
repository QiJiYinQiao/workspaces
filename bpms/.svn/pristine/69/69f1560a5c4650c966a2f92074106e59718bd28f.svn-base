package com.bpms.shiro;

import java.io.Serializable;
import java.util.List;

public class ShiroUser implements Serializable {
	private static final long serialVersionUID = -1748602382963711884L;
	private Integer userId;
	private String account;
	private String name;
	private List<String> roleCodes;

	public ShiroUser(Integer userId, String account) {
		super();
		this.userId = userId;
		this.account = account;
	}

	public ShiroUser(Integer userId, String account, String name) {
		super();
		this.userId = userId;
		this.account = account;
		this.name = name;
	}

	public ShiroUser(Integer userId, String account, String name,
			List<String> roleCodes) {
		super();
		this.userId = userId;
		this.account = account;
		this.name = name;
		this.roleCodes = roleCodes;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	/*
	 * public String toString() { return account; }
	 */

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

}
