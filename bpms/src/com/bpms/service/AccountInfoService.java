package com.bpms.service;

import java.util.List;

import com.bpms.model.AccountInfo;

/**
 * 开户行service
 * @author panchuanhe
 * 2015/6/30
 */
public interface AccountInfoService {
    /**
     * 保存
     * @param accountInfo
     * @return
     */
	boolean saveAccountInfo(AccountInfo accountInfo);
	/**
	 * 根据贷款人id查询开户行信息列表
	 * @param loanerId 贷款人id
	 * @return
	 */
	List<AccountInfo> findListByLoanerId(String loanerId);
	/**
	 * 根据id查询实体对象
	 * @param id
	 * @return
	 */
	AccountInfo findById(String id); 
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	boolean deleteById(String id);
}
