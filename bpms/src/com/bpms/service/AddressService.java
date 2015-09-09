package com.bpms.service;

import com.bpms.model.Address;

/**
 * 地址service
 * @author panchuanhe
 * 2015/6/26
 */
public interface AddressService {
	/**
	 * 保存
	 */
	public boolean saveAddress(Address address);
	/**
	 * 根据id查一个实体
	 */
	public Address findById(String id);
	/**
	 * 根据ID查询详细地址 包括省市县
	 * @param id
	 * @return
	 */
	public String addressName(String id);
}
