package com.bpms.service;

import java.util.List;

import com.bpms.model.Company;
import com.bpms.model.vo.CompanyModel;

/**
 * 贷款人公司信息xervice
 * @author panchuanhe
 * @date 2015/6/16
 */
public interface CompanyService {
	/**
	 * 保存公司信息，并建立公司信息与订单实例
	 * @param company 公司信息实例
	 * @param loanOrder 订单实例
	 */
	public boolean saveCompany(Company company);

	/**
	 * 根据订单ID查询其公司信息
	 * @param id 贷款人id
	 * @return List<Company> 公司列表
	 */
	public List<Company> findCompanyByLoanOrderId(String loanOrderId);
	/**
	 * 根据贷款人ID查询公司信息总条数
	 * @param id 贷款人ID
	 * @return
	 */
	public Long count(Integer id);
	/**
	 * 保存公司信息，并建立公司信息与订单实例
	 * @param company 公司信息实例
	 * @param loanOrder 订单实例
	 */
	public boolean saveCompanyModel(CompanyModel companyModel);
	/**
	 * 根据贷款人ID查询公司信息
	 */
	public List<CompanyModel> findCompanyByLonaId(String loanerId);
    /**
     * 根据主键id查找实体
     */
	public Company findCompanyById(String ComId);
	/**
	 * 删除
	 */
	public boolean deleteById(String id);
	
	/**
	 * 通过订单ID，公司名称查询公司
	 * @param loanOrderId 订单ID
	 * @param name 公司名称
	 * @return
	 */
	public Company findComByON(String loanOrderId,String name);
}
