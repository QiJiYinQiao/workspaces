package com.bpms.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.InvestProduct;
import com.bpms.service.InvestProductService;
import com.bpms.util.PageUtil;
import common.Logger;

/**
 * 
 * 理财产品的Service实现类
 * 
 * @author 张健 2015/07/15
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/07/15 张健 创建.
 */
@Service("investProductService")
public class InvestProductServiceImpl implements InvestProductService {
	
	private Logger log = Logger.getLogger(InvestProductServiceImpl.class);

	@Autowired
	private BaseDAO<InvestProduct> baseDao;	
	
	@Override
	public List<InvestProduct> getAllInvestProducts() {
		String hql = "from com.bpms.model.InvestProduct t where 1=1";
		
		List<InvestProduct> investProductList = this.baseDao.find(hql);		
		
		return investProductList != null? investProductList : null;
	}
	
	@Override
	public List<InvestProduct> getAllInvestProductsAvailable() {
		String hql = "from com.bpms.model.InvestProduct t where 1=1 and t.prodStatus = 'A'";		
		
		List<InvestProduct> investProductList = this.baseDao.find(hql);		
		
		return investProductList != null? investProductList : null;
	}
	
	public Object[] getAllInvestProducts(String queryItem, String operType,
			String userInputCondition, PageUtil pageUtil) {
		
		String hql = "from com.bpms.model.InvestProduct t where 1=1";
		
		//若用户选择的查询项目是“产品名称”，则必须使用模糊查询
		if(StringUtils.isNotBlank(queryItem) && queryItem.equalsIgnoreCase("prodName")){
			if(StringUtils.isNotBlank(operType) && operType.toString().equalsIgnoreCase("ne")){
				hql += " and t.prodName not like '%"+userInputCondition.toString()+"%'";
			}else{				
				hql += " and t.prodName like '%"+userInputCondition.toString()+"%'";
			}
		}
		
		//若用户选择的查询项目是“产品状态”，则必须使用“等于”查询	
		if(StringUtils.isNotBlank(queryItem) && queryItem.equalsIgnoreCase("prodStatus")){
			if(StringUtils.isNotBlank(operType) && operType.toString().equalsIgnoreCase("ne")){				
				hql += " and t.prodStatus != '"+userInputCondition.toString()+"'";
			}else{
				hql += " and t.prodStatus = '"+userInputCondition.toString()+"'";				
			}
		}
		
		else if(StringUtils.isNotBlank(queryItem) && !queryItem.equalsIgnoreCase("prodName") && !queryItem.equalsIgnoreCase("prodStatus")){	
			if(StringUtils.isNotBlank(operType)){				
				switch(operType.toString()){
					case "eq": hql += " and t."+queryItem.toString()+" = '"+userInputCondition.toString()+"'"; break;
					case "ge": hql += " and t."+queryItem.toString()+" >= '"+userInputCondition.toString()+"'"; break;
					case "gt": hql += " and t."+queryItem.toString()+" > '"+userInputCondition.toString()+"'"; break;
					case "le": hql += " and t."+queryItem.toString()+" <= '"+userInputCondition.toString()+"'"; break;
					case "lt": hql += " and t."+queryItem.toString()+" < '"+userInputCondition.toString()+"'"; break;
					case "ne": hql += " and t."+queryItem.toString()+" != '"+userInputCondition.toString()+"'"; break;				
				}			
			}
		}		
		
		List<InvestProduct> investProductList = null;
		
		if(pageUtil == null){
			investProductList = baseDao.find(hql, pageUtil);
		}else{
			investProductList = baseDao.find(hql);
		}		
		
		Long totalRows = baseDao.count("select count(*) " + hql);		
		Object[] objArray = new Object[2];
		objArray[0] = investProductList;
		objArray[1] = totalRows;				
		return objArray;
	}


	@Override
	public boolean deleteInvestProductById(String investProductId) {		
		try {
			StringBuffer hql = new StringBuffer("delete from com.bpms.model.InvestProduct t where 1=1 and t.prodId = '"+investProductId+"'");			
			Integer effectedRowNum = baseDao.executeHql(hql.toString());
			if(effectedRowNum != 0){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;				
	}


	@Override
	public InvestProduct findInvestProductDetailsById(
			String investProductId) {		
		String hql = "from com.bpms.model.InvestProduct t where 1=1 and t.prodId = '"+investProductId+"'";
		List<InvestProduct> result = baseDao.find(hql);
		return (InvestProduct) ((result.size()>0) ? result.get(0) : null);
	}


	@Override
	public boolean persistenceInvestProduct(InvestProduct investProduct) {
		try {
			//ID为NULL，说明用户选择了 “新增理财产品”。
			if(StringUtils.isBlank(investProduct.getProdId())){

				investProduct.setProdId(null);								
				log.info("\n------------------->    理财产品ID为空，您点击了(新增)方法，新增的理财产品为："+investProduct.toString()+"\n");
				baseDao.save(investProduct);
				return true;
			}
			//ID不为NULL，说明用户选择了 “修改理财产品”。		
			else{			
				log.info("\n------------------->    理财产品ID为"+investProduct.getProdId()+"，您点击了(修改)方法。\n");
				baseDao.update(investProduct);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
}