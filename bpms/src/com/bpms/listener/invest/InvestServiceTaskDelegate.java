package com.bpms.listener.invest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.model.InvestorderAndProducts;
import com.bpms.service.InvestorderAndProductsService;
import com.bpms.util.Constants;

public class InvestServiceTaskDelegate implements Serializable,JavaDelegate {

	private static final long serialVersionUID = 1151257593312546295L;
	
	private static Logger logger = Logger.getLogger(InvestServiceTaskDelegate.class); 
	
	//系统自动判断投资客户是否申请调整理财收益率
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		//1.获得Spring容器对象——WebApplicationContext。
		WebApplicationContext webAppContext = ContextLoader.getCurrentWebApplicationContext();
		//2.获得InvestorderAndProductsService对象——investorderAndProductsService。
		InvestorderAndProductsService investorderAndProductsService = (InvestorderAndProductsService) webAppContext.getBean("investorderAndProductsService");		
		//3.根据获取investOrderId。
		String investOrderId = execution.getProcessBusinessKey().split("\\.")[1];
		if(null != investOrderId){
			//4.根据investOrderId，查询“IF_ADJUST_ARS”字段和“NEW_ARS”字段。
			InvestorderAndProducts investorderAndProducts = investorderAndProductsService.findInvestorderAndProductsList(investOrderId).get(0);			
			String ifAdjustArs = investorderAndProducts.getIfAdjustArs();
			BigDecimal newArs = investorderAndProducts.getNewArs();
			
			//存放流程变量
			Map<String, Object> variables = new HashMap<String, Object>();			
			
			//5.判断系统流程，并且将判断结果翻入Execution流程中去。
			//(1)判断投资客户进行理财收益率的调整。
			if(null != ifAdjustArs && StringUtils.isNotBlank(newArs == null ? "" : newArs.toString())){
				logger.info("------------------->  流程进入到系统自动判断是否进行理财收益率的调整 ");
				if(newArs.compareTo(BigDecimal.ZERO) > 0){					
					logger.info("------------------->  流程确认要进行理财收益率的调整，流程进入到团队经理。 ");
					variables.put("result", Constants.INVEST_CONST_ADJUST_ARS); //投资客户进行理财收益率的调整
				}
			}else{
				logger.info("------------------->  流程确认不进行理财收益率的调整，流程进入到部门助理。 ");
				variables.put("result", Constants.INVEST_CONST_NOT_ADJUST_ARS); //投资客户不进行理财收益率的调整				
			}
			//(2)将判断结果放入流程变量中去。
			execution.setVariables(variables);
		}		
	}
	
}
