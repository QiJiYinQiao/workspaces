package com.bpms.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.InvestOrder;
import com.bpms.service.InvestApplyService;
import com.bpms.service.InvestOrderWorkFlowService;
import com.bpms.service.InvestorderAndProductsService;
import com.bpms.service.WorkFlowService;
import com.bpms.util.Constants;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 投资申请的Action
 * 作用：
 *    1.投资申请的CRUD。
 *    2.投资流程（WorkFlow）的开启，CRUD和终止等等。
 *    
 * @author 张健  2015/12/02.
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/12/02 张健 创建.
 */
@Namespace("/investApply")
@Action(value = "investApplyAction", results = {	
		// 跳转到“调整申请”任务界面	(哪个角色提交申请，就由那个角色来调整申请，在此为团队经理)。
		@Result(name = "gotoTaskForm4InvestApplyAdjustment4NewArs", location = "/jsp/investApply/taskForm4InvestApplyAdjustment4NewArs.jsp", type = "dispatcher"),		
		// 跳转到“团队经理”办理任务界面。
		@Result(name = "gotoTaskForm4TuanDuiJingLi", location = "/jsp/investApply/taskForm4TuanDuiJingLi.jsp", type = "dispatcher"),				
		// 跳转到“大团经理”办理任务界面。
		@Result(name = "gotoTaskForm4DaTuanJingLi", location = "/jsp/investApply/taskForm4DaTuanJingLi.jsp", type = "dispatcher"),				
		// 跳转到“营业部经理”办理任务界面。
		@Result(name = "gotoTaskForm4YingYeBuJingLi", location = "/jsp/investApply/taskForm4YingYeBuJingLi.jsp", type = "dispatcher"),				
		// 跳转到“城市经理”办理任务界面。
		@Result(name = "gotoTaskForm4ChengShiJingLi", location = "/jsp/investApply/taskForm4ChengShiJingLi.jsp", type = "dispatcher"),				
		// 跳转到“区域总监”办理任务界面。
		@Result(name = "gotoTaskForm4QuYuZongJian", location = "/jsp/investApply/taskForm4QuYuZongJian.jsp", type = "dispatcher"),				
		// 跳转到“总经理”办理任务界面。
		@Result(name = "gotoTaskForm4ZongJingLi", location = "/jsp/investApply/taskForm4ZongJingLi.jsp", type = "dispatcher"),				
		
		// 跳转到“调整申请”任务界面
/*		@Result(name = "gotoTaskForm4InvestApplyAdjustment02", location = "/jsp/investApply/taskForm4InvestApplyAdjustment02.jsp", type = "dispatcher"),*/		
		// 跳转到“调整申请”任务界面	(哪个角色提交申请，就由那个角色来调整申请，在此为理财经理)。
		@Result(name = "gotoTaskForm4InvestApplyAdjustment02", location = "/jsp/investApply/taskForm4LiCaiJingLi.jsp", type = "dispatcher"),
		// 跳转到“部门助理”办理任务界面。		
		@Result(name = "gotoTaskForm4BuMenZhuLi", location = "/jsp/investApply/taskForm4BuMenZhuLi.jsp", type = "dispatcher"),
		// 跳转到“销客专员”办理任务界面。
		@Result(name = "gotoTaskForm4XiaoKeZhuanYuan", location = "/jsp/investApply/taskForm4XiaoKeZhuanYuan.jsp", type = "dispatcher"),		
		// 跳转到“数据专员”办理任务界面。
		@Result(name = "gotoTaskForm4ShuJuZhuanYuan", location = "/jsp/investApply/taskForm4ShuJuZhuanYuan.jsp", type = "dispatcher"),		
		// 跳转到“结算专员”办理任务界面。
		@Result(name = "gotoTaskForm4JieSuanZhuanYuan", location = "/jsp/investApply/taskForm4JieSuanZhuanYuan.jsp", type = "dispatcher"),		
		// 跳转到“销客经理”办理任务界面。
		@Result(name = "gotoTaskForm4XiaoKeJingLi", location = "/jsp/investApply/taskForm4XiaoKeJingLi.jsp", type = "dispatcher"),		
		// 跳转到“销客主管”办理任务界面。
		@Result(name = "gotoTaskForm4XiaoKeZhuGuan", location = "/jsp/investApply/taskForm4XiaoKeZhuGuan.jsp", type = "dispatcher")		
})
public class InvestApplyAction extends BaseAction implements ModelDriven<InvestOrder>{

	private static final long serialVersionUID = -1537632050632939240L;
	
	private InvestOrder investOrder;

	@Autowired
	private InvestApplyService investApplyService; 
	
	@Autowired
	private InvestOrderWorkFlowService investOrderWorkFlowService; 
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private InvestorderAndProductsService investorderAndProductsService; 
	
	/** 备注信息. */
	private String comment;

	/** 返回值. */
	private String result;

	/** 是否通过 */
	private String processingResult;
	
	/** 理财经理投资申请重提后，存放新的理财收益率 */
	private BigDecimal newArs;	
	
			
	/****************************************************/
	/**************** 与投资申请相关的CRUD *******************/
	/****************************************************/
	/**
	 * 查询投资申请的信息
	 * @Title: findInvestApplyList 
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月2日 下午4:31:27
	 */
	public String findInvestApplyList(){
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("start", this.getFirstResult());
			param.put("end", this.getRows());
			// 获得当前,进行投资申请操作的理财经理的ID
			// 原因：理财经理A提交的投资申请，只能由理财经理A进行查看和处理，而不能由理财经理B进行查看和处理。
			param.put("investApplySubmitter",
					String.valueOf(Constants.getCurrendUser().getUserId()));
			
			GridModel gridModel = new GridModel();
			gridModel.setRows(investApplyService.findInvestApplyList(param));
			gridModel.setTotal(investApplyService.countInvestApplyList(param));
			this.OutputJson(gridModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 是否显示查看附件链接
	 */
	public void isShowAttachment(){
		boolean b = false;
		String[] postions = {"TuanDuiJingLi","DaTuanJingLi","YingYeBuJingLi","ChengShiJingLi","QuYuZongJian","ZongJingLi"};
		Map<String,Object> map = investApplyService.queryPositionName(investOrder.getTaskId());
		if(!map.isEmpty()){
			String positonname = (String) map.get("positionName");
			for(int i = 0 ; i < postions.length; i++) {
				if(postions[i].equals(positonname)) {
					b = true;
					OutputJson(b);
				}
			}
		}
		OutputJson(b);
	}
	
	/**
	 * 统计投资申请的记录条数
	 * @Title: countInvestApplyList 
	 * @author ZHANGJIAN
	 * @return Long
	 * @date 2015年12月2日 下午4:32:00
	 */
	public Long countInvestApplyList(){
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("start", this.getFirstResult());
			param.put("end", this.getRows());
			return investApplyService.countInvestApplyList(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}


	
	/****************************************************/
	/************* 与投资申请工作流WorkFlow相关的方法 ****************/
	/****************************************************/
	
	/**
	 * 作用：开启投资申请的工作流程。
	 * @Title: saveStartProcessInstance 
	 * @param @return
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月3日 下午4:36:27
	 */
	public String saveStartProcessInstance(){
		try {
			OutputJson(getMessage(investOrderWorkFlowService
					.saveStartProcessInstance(investOrder)));
		} catch (ActivitiObjectNotFoundException e) {
			OutputJson(new DataModel("提示", "您还没有部署投资申请流程!!", false));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @Title: checkWorkFlowImg
	 * @Description: 查看流程图
	 * @author ZHANGJIAN
	 * @return String
	 */
	public String checkWorkFlowImg() {
		try {
			investOrderWorkFlowService
					.checkWorkFlowImgByInvestOrderId(investOrder.getInvestOrderId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	
	/**************************************************/
	/************* 调整年化收益率的页面跳转方法       ***************/
	/**************************************************/
	
	/**
	 * 跳转到年化收益率的“调整申请”办理任务界面。
	 */	
	public String gotoTaskForm4InvestApplyAdjustment4NewArs(){
		return "gotoTaskForm4InvestApplyAdjustment4NewArs";
	}	
	
	/**
	 * 跳转到“团队经理”办理任务界面
	 */
	public String gotoTaskForm4TuanDuiJingLi() {
		return "gotoTaskForm4TuanDuiJingLi";
	}	
	
	/**
	 * 跳转到“大团经理”办理任务界面
	 */
	public String gotoTaskForm4DaTuanJingLi() {
		return "gotoTaskForm4DaTuanJingLi";
	}		

	/**
	 * 跳转到“营业部经理”办理任务界面
	 */
	public String gotoTaskForm4YingYeBuJingLi() {
		return "gotoTaskForm4YingYeBuJingLi";
	}
	
	/**
	 * 跳转到“城市经理”办理任务界面
	 */
	public String gotoTaskForm4ChengShiJingLi() {
		return "gotoTaskForm4ChengShiJingLi";
	}	
	
	/**
	 * 跳转到“区域总监”办理任务界面
	 */
	public String gotoTaskForm4QuYuZongJian() {
		return "gotoTaskForm4QuYuZongJian";
	}		
	
	/**
	 * 跳转到“总经理”办理任务界面
	 */
	public String gotoTaskForm4ZongJingLi() {
		return "gotoTaskForm4ZongJingLi";
	}		
	
	
	
	/**************************************************/
	/*********** 年化收益率调整完毕后，页面跳转方法       ************/
	/*************************************************/		
	/**
	 * 跳转到“部门助理”办理任务页面（这里描述这个方法的作用）
	 */
	public String gotoTaskForm4BuMenZhuLi() {
		return "gotoTaskForm4BuMenZhuLi";
	}	
	
	/**
	 *  跳转到“销客专员”办理任务页面
	 */
	public String gotoTaskForm4XiaoKeZhuanYuan() {
		return "gotoTaskForm4XiaoKeZhuanYuan";
	}	
				
	/**
	 *  跳转到“数据专员”办理任务页面
	 */
	public String gotoTaskForm4ShuJuZhuanYuan() {
		return "gotoTaskForm4ShuJuZhuanYuan";
	}
	
	/**
	 *  跳转到“结算专员”办理任务页面
	 */
	public String gotoTaskForm4JieSuanZhuanYuan() {
		return "gotoTaskForm4JieSuanZhuanYuan";
	}	

	/**
	 *  跳转到“销客经理”办理任务页面
	 */
	public String gotoTaskForm4XiaoKeJingLi() {
		return "gotoTaskForm4XiaoKeJingLi";
	}
		
	/**
	 *  跳转到“销客主管”办理任务页面
	 */
	public String gotoTaskForm4XiaoKeZhuGuan() {
		return "gotoTaskForm4XiaoKeZhuGuan";
	}		
	
	
	/**
	 *  跳转“调整申请”任务界面
	 */
	public String gotoTaskForm4InvestApplyAdjustment02() {
		return "gotoTaskForm4InvestApplyAdjustment02";
	}				
	
	/**
	 * 查询当前角色的代办任务列表（没有被签收的任务用）
	 * @Title: findAllMyTaskList
	 * @Description: TODO 待办任务列表
	 */
	public String findAllUnclaimedTaskList() {
		GridModel gridModel = new GridModel();
		gridModel.setRows(investOrderWorkFlowService.findAllUnclaimedTaskList(getFirstResult(), getMaxResults()));
		gridModel.setTotal(investOrderWorkFlowService.findAllUnclaimedTaskCount());
		OutputJson(gridModel);
		return null;
	}
	
	/**
	 * @author zhangjian
	 * @description 获取当前角色用户所有的受理任务
	 * @return 用户所有的受理任务的json信息
	 */
	public String findAllClaimedTask() {
		// 获取当前"角色（比如，销客专员）"已经受理的所有投资订单investOrder
		List<InvestOrder> rows = investOrderWorkFlowService.findAllClaimedTask(
				getFirstResult(), getMaxResults());
		String userId = String.valueOf(Constants.getCurrendUser().getUserId());
		// 获取当前"角色（比如，销客专员）"已经受理的所有投资订单investOrder的"总数量"
		Long total = investOrderWorkFlowService.findAllClaimedTaskCount(userId);
		this.OutputJson2(new GridModel(rows, total));
		return null;
	}	
	
	/**
	 * 当前角色的用户签收任务
	 * @Title: saveMyTask
	 * @Description: TODO 签收任务
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String pickMyTask() {
		try {
			investOrderWorkFlowService.pickMyTask(investOrder.getTaskId());
			OutputJson(new DataModel("提示", "恭喜你,签收成功!", true));
		} catch (Exception e) {
			e.printStackTrace();
			OutputJson(new DataModel("提示", "出错了,签收失败!", false));
		}
		return null;
	}	
	
	/**
	 * 办理任务
	 * @author: zhangjian
	 * @time:2015年7月228日
	 * @Title:受理任务页面中，点击办理任务时，会走这个InvestOrderAction
	 */
	public String findTaskFormKeyByTaskId() {
		OutputJson(workFlowService.findTaskFormKeyByTaskId(investOrder
				.getTaskId()));
		return null;
	}
	
	
	/**
	 * 获取财富的任务个数
	 * 
	 * @return 财富的任务个数
	 */
	public String findInvestUnclaimedAndClaimedTaskCount() {
		String userId = String.valueOf(Constants.getCurrendUser().getUserId());
		Long unClaimTaskCount = investOrderWorkFlowService.findAllUnclaimedTaskCount();
		Long claimTaskCount = investOrderWorkFlowService
				.findAllClaimedTaskCount(userId);
		Map<String, Object> taskCoutMap = new HashMap<String, Object>();
		taskCoutMap.put("unClaimTaskCount", unClaimTaskCount);
		taskCoutMap.put("claimTaskCount", claimTaskCount);
		OutputJson(taskCoutMap);
	
		return null;
	}		
	
	/**
	 * 提交任务办理，比如审批通过，审批驳回，审批拒绝
	 * @return
	 */	
	public String submitTask() {
		try {
//			//如果在“投资申请业务中”，“总经理”节点对“调整理财申请”的请求通过，或者拒绝。
//			//修改“投资业务订单与理财产品关联表(t_bp_investOrder_and_products)”中的“IS_NEW_ARS_FINALLY_APPROVED”字段，将其设置为“N”
			//如果总经理审批通过，则将“IS_NEW_ARS_FINALLY_APPROVED”字段设置为“Y”
			String investOrderId = this.investOrder.getInvestOrderId();
			if(!"".equalsIgnoreCase(result) && result.equalsIgnoreCase("ZongJingLiTongGuo") ){
				investorderAndProductsService.updateIsNewArsFinallyApproved(investOrderId, "Y");
			}
			//如果总经理审批驳回，或者审批拒绝，则将“IS_NEW_ARS_FINALLY_APPROVED”字段设置为“N”
			else if("".equalsIgnoreCase(result) || result.equalsIgnoreCase("ZongJingLiJuJue") || result.equalsIgnoreCase("ZongJingLiBoHui")){
				investorderAndProductsService.updateIsNewArsFinallyApproved(investOrderId, "N");				
			}
						
			//接着走流程
			investOrderWorkFlowService.saveSubmitTask(investOrder, comment,
					result, processingResult);
			OutputJson(getMessage(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	
	/**
	 * 理财客户投资申请订单重提，提交新的理财收益率。
	 * @return
	 */	
	public String reSubmitTask4AdjustArs() {
		try {
			//1.修改理财产品的理财收益率。
			investorderAndProductsService.updateNewArsByInvestOrderId(this.investOrder.getInvestOrderId(), this.newArs);
			//2.提交办理的任务
			investOrderWorkFlowService.saveSubmitTask(investOrder, comment,
					result, processingResult);						
			OutputJson(getMessage(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}		
	
	/**
	 * 获取有待调整理财收益率的客户，理财经理和投资产品等的相关投资信息。
	 */	
	public String findInvestApplyInfo4AdjustArs(){			
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("investOrderId", this.investOrder.getInvestOrderId());
		Map<String, Object> investInfo = this.investApplyService.findInvestApplyInfo4AdjustArs(param);
		this.OutputJson2(investInfo);   //日期数据仅仅显示年月日		
		return null;
	}
	
	
	@Override
	public InvestOrder getModel() {
		if(this.investOrder == null){
			this.investOrder = new InvestOrder();
		}
		return this.investOrder;
	}

	public InvestOrder getInvestOrder() {
		return investOrder;
	}

	public void setInvestOrder(InvestOrder investOrder) {
		this.investOrder = investOrder;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getProcessingResult() {
		return processingResult;
	}

	public void setProcessingResult(String processingResult) {
		this.processingResult = processingResult;
	}

	public BigDecimal getNewArs() {
		return newArs;
	}

	public void setNewArs(BigDecimal newArs) {
		this.newArs = newArs;
	}
	
}
