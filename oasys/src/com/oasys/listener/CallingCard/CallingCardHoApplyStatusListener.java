package com.oasys.listener.CallingCard;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.el.FixedValue;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.CardApplyService;

public class CallingCardHoApplyStatusListener implements ExecutionListener{
	private FixedValue status;
	
	public FixedValue getStatus() {
		return status;
	}

	public void setStatus(FixedValue status) {
		this.status = status;
	}

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		CardApplyService cardApplyService = (CardApplyService) ctx.getBean("cardApplyService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		Integer id = null;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			System.err.println(execution.getProcessBusinessKey()+"---");
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = Integer.valueOf(execution.getProcessBusinessKey().split("\\.")[1]);
		}
		if(status!=null){
			if("start".equals(status.getValue(null))){
				//修改流程状态 审批中
				cardApplyService.updateStatus(id,"2");
			}else if("end".equals(status.getValue(null))){
				//修改流程状态 已完成
				cardApplyService.updateStatus(id,"3");
				ExecutionEntity entity = (ExecutionEntity) execution;
				if (entity.getTransition() != null) {
					// 获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
					String sid = "";
					if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
						// 截取字符串，取buniness_key小数点的第2个值
						sid = execution.getProcessBusinessKey().split("\\.")[1];
					}
					cardApplyService.updateApplyStatus(Integer.parseInt(sid), entity.getTransition().getId());
				}
			}else if("reject".equals(status.getValue(null))){
				//修改流程状态 初始状态
//				cardApplyService.updateStatus(id,"1");
				ExecutionEntity entity = (ExecutionEntity) execution;
				if (entity.getTransition() != null) {
					// 获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
					String sid = "";
					if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
						// 截取字符串，取buniness_key小数点的第2个值
						sid = execution.getProcessBusinessKey().split("\\.")[1];
					}
					cardApplyService.updateApplyStatus(Integer.parseInt(sid), entity.getTransition().getId());
				}
			}else if("cancel".equals(status.getValue(null))){
				ExecutionEntity entity = (ExecutionEntity) execution;
				if (entity.getTransition() != null) {
					// 获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
					String sid = "";
					if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
						// 截取字符串，取buniness_key小数点的第2个值
						sid = execution.getProcessBusinessKey().split("\\.")[1];
					}
					cardApplyService.updateApplyStatus(Integer.parseInt(sid), entity.getTransition().getId());
				}
				//修改流程状态 已撤销
				cardApplyService.updateStatus(id,"5");
			}
		}
		if(status!=null){
			if(!"start".equals(status.getValue(null))){
				ExecutionEntity entity = (ExecutionEntity) execution;
				if (entity.getTransition() != null) {
					// 获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
					String sid = "";
					if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
						// 截取字符串，取buniness_key小数点的第2个值
						sid = execution.getProcessBusinessKey().split("\\.")[1];
					}
					cardApplyService.updateApplyStatus(Integer.parseInt(sid), entity.getTransition().getId());
				}
			}
		}
	}

}
	