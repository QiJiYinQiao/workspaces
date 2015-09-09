package com.qqms.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqms.dao.PublicDao;
import com.qqms.model.LoanOrder;
import com.qqms.service.LoanOrderService;
import com.qqms.util.PageUtil;

/**
 * 贷款订单信息service
 * @author 
 * @data 2015/6/23
 */
@Namespace("/loanOrder")
@Service("loanOrderService")
public class LoanOrderServiceImpl extends BaseServiceImpl implements LoanOrderService {
	
	@Autowired
	private PublicDao<LoanOrder> publicDao;	
	

	@Override
	public Object[] findAllLoanOrderList(Map<String, Object> map, PageUtil pageUtil ) {			
		
		//获取客户端传来的参数值
		String loanOrderId = (String) map.get("loanOrderId");
		String loanerName = (String) map.get("loanerName");
		String idNo = (String) map.get("idNo");
		String loanType = (String) map.get("loanType");
		String orderStatus = (String) map.get("orderStatus");
		Date applyDate = (Date) map.get("applyDate");
		Date applyDate02 = (Date) map.get("applyDate02");
		String loanAmount = (String) map.get("loanAmount");
		String loanMin = (String) map.get("loanMin");

		
		String hql = "from LoanOrder t where 1=1 and t.processStatus = '1'";
		
		//动态拼接hql语句。
		if(StringUtils.isNotBlank(loanOrderId)){
			hql+=" and t.loanOrderId = '"+loanOrderId+"'";
		}
		if(StringUtils.isNotBlank(loanerName)){
			hql+=" and t.name like '%"+loanerName+"%'";
		}
		if(StringUtils.isNotBlank(idNo)){
			hql+=" and t.idNo = '"+idNo+"'";
		}	
		if(StringUtils.isNotBlank(loanType)){
			hql+=" and t.loanType = '"+loanType+"'";
		}	
		if(StringUtils.isNotBlank(orderStatus)){
			hql+=" and t.orderStatus = '"+orderStatus+"'";
		}	
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
//		//日期字段匹配暂且保留。
		if(applyDate !=null){
			hql+=" and t.applyDate >= '"+df.format(applyDate)+"'";
		}
		if(applyDate02 != null){
			hql+=" and t.applyDate <= '"+df.format(applyDate02)+"'";
		}
		
		if(StringUtils.isNotBlank(loanAmount)){
			hql+=" and t.loanAmount = '"+loanAmount+"'";
		}	
		if(StringUtils.isNotBlank(loanMin)){
			hql+=" and t.loanMin = '"+loanMin+"'";
		}	
				
		List<LoanOrder> loanOrderList = publicDao.find(hql, pageUtil);		

		Long count = publicDao.count("select count(*) " + hql);
		
		Object[] obj = new Object[2];
		obj[0]=loanOrderList;
		obj[1]=count;
		return obj;		
	}

}
