package com.bpms.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.InvestOrder;
import com.bpms.model.InvestRedeem;
import com.bpms.model.vo.InvestRedeemFinanceDetails4ClientVO;
import com.bpms.service.InvestOrderService;
import com.bpms.service.InvestRedeemService;
import com.bpms.service.InvestRedeemWorkFlowService;
import com.bpms.service.WorkFlowService;
import com.bpms.util.Collections;
import com.bpms.util.Constants;
import com.bpms.util.ReadExcel;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 投资赎回的Action
 * 
 * @author 张健 2015/12/16.
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/12/16 张健 创建.
 */
@Namespace("/investRedeem")
@Action(value = "investRedeemAction", results = {
// 跳转到“调整申请”办理任务界面，在本业务中，一般是申请提交者有权进行调整申请操作，申请者一般是理财经理。
		@Result(name = "gotoTaskForm4InvestRedeemAdjustment", location = "/jsp/investRedeem/taskForm4InvestRedeemAdjustment.jsp", type = "dispatcher"),

		// 跳转到“团队经理”办理任务界面。
		@Result(name = "gotoTaskForm4TuanDuiJingLi", location = "/jsp/investRedeem/taskForm4TuanDuiJingLi.jsp", type = "dispatcher"),
		// 跳转到“大团经理”办理任务界面。
		@Result(name = "gotoTaskForm4DaTuanJingLi", location = "/jsp/investRedeem/taskForm4DaTuanJingLi.jsp", type = "dispatcher"),
		// 跳转到“营业部经理”办理任务界面。
		@Result(name = "gotoTaskForm4YingYeBuJingLi", location = "/jsp/investRedeem/taskForm4YingYeBuJingLi.jsp", type = "dispatcher"),
		// 跳转到“城市经理”办理任务界面。
		@Result(name = "gotoTaskForm4ChengShiJingLi", location = "/jsp/investRedeem/taskForm4ChengShiJingLi.jsp", type = "dispatcher"),
		// 跳转到“区域总监”办理任务界面。
		@Result(name = "gotoTaskForm4QuYuZongJian", location = "/jsp/investRedeem/taskForm4QuYuZongJian.jsp", type = "dispatcher"),
		// 跳转到“销客经理”办理任务界面。
		@Result(name = "gotoTaskForm4XiaoKeJingLi", location = "/jsp/investRedeem/taskForm4XiaoKeJingLi.jsp", type = "dispatcher"),
		// 跳转到“总经理”办理任务界面。
		@Result(name = "gotoTaskForm4ZongJingLi", location = "/jsp/investRedeem/taskForm4ZongJingLi.jsp", type = "dispatcher"),
		// 跳转到“销客专员”办理任务界面。
		@Result(name = "gotoTaskForm4XiaoKeZhuanYuan", location = "/jsp/investRedeem/taskForm4XiaoKeZhuanYuan.jsp", type = "dispatcher"),
		// 跳转到“数据专员”办理任务界面。
		@Result(name = "gotoTaskForm4ShuJuZhuanYuan", location = "/jsp/investRedeem/taskForm4ShuJuZhuanYuan.jsp", type = "dispatcher"),
		// 跳转到“结算专员”办理任务界面。
		@Result(name = "gotoTaskForm4JieSuanZhuanYuan", location = "/jsp/investRedeem/taskForm4JieSuanZhuanYuan.jsp", type = "dispatcher") })
public class InvestRedeemAction extends BaseAction implements
		ModelDriven<InvestOrder> {

	private static final long serialVersionUID = 3680229897378856564L;

	private InvestOrder investOrder = new InvestOrder();

	@Autowired
	private InvestRedeemService investRedeemService;

	@Autowired
	private InvestRedeemWorkFlowService investRedeemWorkFlowService;

	@Autowired
	private InvestOrderService investOrderService;

	@Autowired
	private WorkFlowService workFlowService;

	/** 备注信息. */
	private String comment;

	/** 返回值. */
	private String result;

	/** 是否通过 */
	private String processingResult;

	/** 导出赎回客户明细报表时，存储用户选择的一个或者多个investOrderId */
	private String[] investOrderIds;

	// /** 银行同期活期存款利率计算 (投资期限不满45T)*/
	// private BigDecimal curDepositInterestRate;
	//
	// /** 回款日期*/
	// private Date huiKuanDate;
	//
	// /** 从计息日期到回款日期之间实际的天数*/
	// private Integer actualInvestDays;

	private InvestRedeem investRedeem;

	// ********************************************************//
	// **************** 与投资赎回数据信息相关的方法 **************//
	// ********************************************************//
	/**
	 * 
	 * @Title: findAlreadyApprovedInvestApplyList
	 * @param @return
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月16日 上午11:27:59
	 */
	public String findAlreadyApprovedInvestApplyList() {
		// 封装传入后台的参数。
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("start", this.getFirstResult());
			param.put("end", this.getRows());
			// 获得当前,投资赎回操作的理财经理的ID
			// 原因：理财经理A提交的投资申请，只能由理财经理A进行赎回操作，其不能对理财经理B提交的投资订单进行操作。
			param.put("investApplySubmitter",
					String.valueOf(Constants.getCurrendUser().getUserId()));

			GridModel gridModel = new GridModel();
			gridModel.setRows(investRedeemService
					.findAlreadyApprovedInvestApplyList(param));
			gridModel.setTotal(investRedeemService
					.countAlreadyApprovedInvestApplyList(param));
			this.OutputJson(gridModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询投资赎回信息数据List
	 */
	public String findInvestRedeemList() {
		// 封装传入后台的参数。
		Map<String, Object> param = new HashMap<String, Object>();
		try {

			param.put("start", this.getFirstResult());
			param.put("end", this.getRows());
			param.put("investorName4AdvancedQuery",
					this.investOrder.getInvestorName()); // 封装高级查询中的“财富客户姓名”
			param.put("contractNo4AdvancedQuery",
					this.investOrder.getContractNo()); // 封装高级查询中的“合同编号”
			// 获得当前,投资赎回操作的理财经理的ID
			// 原因：理财经理A提交的投资申请，只能由理财经理A进行赎回操作，其不能对理财经理B提交的投资订单进行操作。
			param.put("investApplySubmitter",
					String.valueOf(Constants.getCurrendUser().getUserId()));

			GridModel gridModel = new GridModel();
			gridModel.setRows(investRedeemService.findInvestRedeemList(param));
			gridModel
					.setTotal(investRedeemService.countInvestRedeemList(param));
			this.OutputJson(gridModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增一笔投资赎回申请。 将投资申请订单修改为投资赎回订单 1.修改投资订单类型
	 * 修改该投资业务订单表(t_bp_invest_order)表中“INVEST_PROCESS_TYPE
	 * ”字段，将其由“1(代表：投资申请流程)”修改为“2(代表：投资赎回流程)” 2.修改投资流程状态
	 * 修改该投资业务订单表(t_bp_invest_order)表中“PROCESS_STATUS”字段，将其修改为“0(代表：流程未开启)”
	 * 
	 * @Title: toAddNewInvestRedeem
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月23日 下午7:22:12
	 * @throws
	 */
	public String addNewInvestRedeem() {
		boolean isSuccess = investRedeemService
				.addNewInvestRedeem(this.investOrder);
		if (isSuccess) {
			OutputJson(getMessage(true));
		} else {
			OutputJson(getMessage(false));
		}
		return null;
	}

	/**
	 * 删除一笔投资赎回信息。 注意：删除投资赎回记录并没有数据库底层t_bp_invest_order表中的数据InvestOrder信息删除，
	 * 而是修改该表中一些字段。 (1)“INVEST_PROCESS_TYPE”字段，将由原来的“2(投资赎回流程)”其设置为“1(投资申请流程)”
	 * (2)“PROCESS_STATUS”字段，暂时修改为“2(已完成状态)”。（待定）
	 * 
	 * @Title: deleteInvestRedeemRecord
	 * @Description: TODO
	 * @param @return
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月23日 下午7:51:54
	 * @throws
	 */
	public String deleteInvestRedeemRecord() {
		boolean isSuccess = investRedeemService
				.deleteInvestRedeemRecord(investOrder);
		if (isSuccess) {
			OutputJson(getMessage(true));
		} else {
			OutputJson(getMessage(false));
		}
		return null;
	}

	/**
	 * 获取有待调整理财收益率的客户，理财经理和投资产品等的相关投资信息。
	 */
	public String findInvestRedeemInfo4HandleTask() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("investOrderId", this.investOrder.getInvestOrderId());
		Map<String, Object> investRedeemInfo = this.investRedeemService
				.findInvestRedeemInfo4HandleTask(param);
		this.OutputJson(investRedeemInfo); // 日期数据仅仅显示年月日
		return null;
	}

	@Override
	public InvestOrder getModel() {
		return this.investOrder;
	}

	/**
	 * 导出赎回客户明细报表
	 */
	public String exportExcel4RedeemInvestorFinancialDetails() {						

		try {
			//根据investOrderId，获取指定客户的赎回客户明细数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("investOrderId", this.investOrder.getInvestOrderId());
			List<Map<String, Object>> redeemInvestorInfoList = investRedeemService.exportExcel4RedeemInvestorFinancialDetails(param);		 
			 
			//excel模板的路径
			String excelTemplatePath = ServletActionContext.getServletContext().getRealPath("/excel/investRedeem")+"\\赎回客户明细表模板.xlsx";//模板路径
			
			//获取当前时间
			Calendar nowDate = Calendar.getInstance(); 
			Integer year = nowDate.get(Calendar.YEAR);
			
			String title = year+"年赎回客户明细报表";
			 
			Workbook workbook = ReadExcel.openExcleFile(excelTemplatePath);//获取工作簿
			
			Sheet sheet = workbook.getSheetAt(0);  //获取页签
			Row row_00 = sheet.getRow(0);          //获取第1行
			Cell cell_00_00 = row_00.getCell(0);   //获取第1行第1列
			cell_00_00.setCellValue(title);        //设置标题
			Row row_01 = sheet.getRow(1);          //获取第2行
			Cell cell_01_00 = row_01.getCell(0);   //获取第2行第1列
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat decimalformatter = new DecimalFormat("#.00");
			
			if(Collections.listIsNotEmpty(redeemInvestorInfoList)){
				 for ( int index = 0; index < redeemInvestorInfoList.size(); index++ ) {
					 
					 Map<String, Object> rtnObj = redeemInvestorInfoList.get(index);
					 
					 Row row_x = sheet.getRow( (2 + index) );                                 //获取(2+i)行(行数索引从0开始)
					 row_x.getCell(0).setCellValue((String)rtnObj.get("yingYeBu"));           //设置(2+i)行第1列
					 row_x.getCell(1).setCellValue((String)rtnObj.get("contractNo"));         //设置(2+i)行第2列
					 row_x.getCell(2).setCellValue((String)rtnObj.get("chName"));             //设置(2+i)行第3列
					 row_x.getCell(3).setCellValue((String)rtnObj.get("prodName"));           //设置(2+i)行第4列
					 row_x.getCell(4).setCellValue(decimalformatter.format(rtnObj.get("investEdu")));          //设置(2+i)行第5列
					 row_x.getCell(5).setCellValue(decimalformatter.format(rtnObj.get("defaultPenalty")));     //设置(2+i)行第6列
					 row_x.getCell(6).setCellValue(decimalformatter.format(rtnObj.get("serviceCharge")));      //设置(2+i)行第7列
					 					 
					 BigDecimal interestAlreadyPaid = (BigDecimal) rtnObj.get("interestAlreadyPaid");					 					 
					 if( interestAlreadyPaid.compareTo(new BigDecimal("0")) == 0 ){
						 row_x.getCell(7).setCellValue("无");//设置(2+i)行第8列
					 }else{						 
						 row_x.getCell(7).setCellValue(decimalformatter.format(rtnObj.get("interestAlreadyPaid")));//设置(2+i)行第8列
					 }
					 					 
					 row_x.getCell(8).setCellValue(decimalformatter.format(rtnObj.get("moneyReturned")));      //设置(2+i)行第9列
					 row_x.getCell(9).setCellValue(df.format(rtnObj.get("beginDate")));          //设置(2+i)行第10列
					 row_x.getCell(10).setCellValue(df.format(rtnObj.get("interestDate")));      //设置(2+i)行第11列
					 row_x.getCell(11).setCellValue(df.format(rtnObj.get("redeemBeginDate")));   //设置(2+i)行第12列
					 row_x.getCell(12).setCellValue(df.format(rtnObj.get("huiKuanDate")));       //设置(2+i)行第13列
					 row_x.getCell(13).setCellValue(rtnObj.get("actualInvestDays").toString());  //设置(2+i)行第14列
					 row_x.getCell(14).setCellValue((String)rtnObj.get("userName"));             //设置(2+i)行第15列
					 row_x.getCell(15).setCellValue((String)rtnObj.get("tuanDuiJingLi"));        //设置(2+i)行第16列
					 row_x.getCell(16).setCellValue((String)rtnObj.get("daTuanJingLi"));         //设置(2+i)行第17列
				 }			
			}
			 
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename="+new
			String((title+".xlsx").getBytes("gb2312"),"iso8859-1"));
			OutputStream ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return null;
	}
	

	// ********************************************************//
	// **************** 与投资赎回流程页面跳转相关的方法 **************//
	// ********************************************************//
	/**
	 * 跳转到“调整申请”的办理任务界面。
	 */
	public String gotoTaskForm4InvestRedeemAdjustment() {
		return "gotoTaskForm4InvestRedeemAdjustment";
	}

	/**
	 * 跳转到“团队经理”办理任务界面。
	 */
	public String gotoTaskForm4TuanDuiJingLi() {
		return "gotoTaskForm4TuanDuiJingLi";
	}

	/**
	 * 跳转到“大团经理”办理任务界面。
	 */
	public String gotoTaskForm4DaTuanJingLi() {
		return "gotoTaskForm4DaTuanJingLi";
	}

	/**
	 * 跳转到“营业部经理”办理任务界面。
	 */
	public String gotoTaskForm4YingYeBuJingLi() {
		return "gotoTaskForm4YingYeBuJingLi";
	}

	/**
	 * 跳转到“城市经理”办理任务界面。
	 */
	public String gotoTaskForm4ChengShiJingLi() {
		return "gotoTaskForm4ChengShiJingLi";
	}

	/**
	 * 跳转到“区域总监”办理任务界面。
	 */
	public String gotoTaskForm4QuYuZongJian() {
		return "gotoTaskForm4QuYuZongJian";
	}

	/**
	 * 跳转到“销客经理”办理任务界面。
	 */
	public String gotoTaskForm4XiaoKeJingLi() {
		return "gotoTaskForm4XiaoKeJingLi";
	}

	/**
	 * 跳转到“总经理”办理任务界面。
	 */
	public String gotoTaskForm4ZongJingLi() {
		return "gotoTaskForm4ZongJingLi";
	}

	/**
	 * 跳转到“销客专员”办理任务界面。
	 */
	public String gotoTaskForm4XiaoKeZhuanYuan() {
		return "gotoTaskForm4XiaoKeZhuanYuan";
	}

	/**
	 * 跳转到“数据专员”办理任务界面。
	 */
	public String gotoTaskForm4ShuJuZhuanYuan() {
		return "gotoTaskForm4ShuJuZhuanYuan";
	}

	/**
	 * 跳转到“结算专员”办理任务界面。
	 */
	public String gotoTaskForm4JieSuanZhuanYuan() {
		return "gotoTaskForm4JieSuanZhuanYuan";
	}

	// ********************************************************//
	// **************** 与投资赎回流程，流程处理相关的方法 **************//
	// ********************************************************//

	/**
	 * 启动投资赎回的工作流程。
	 * 
	 * @Title: saveStartProcess4InvestRedeem
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月16日 下午4:42:59
	 */
	public String saveStartProcess4InvestRedeem() {
		try {
			// 1.启动流程
			boolean flag01 = investRedeemWorkFlowService
					.saveStartProcess4InvestRedeem(this.investOrder);

			// 2.创建投资赎回流程的申请日期
			Date redeemBeginDate = new Date(System.currentTimeMillis());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("investOrderId", this.investOrder.getInvestOrderId());
			param.put("redeemBeginDate", df.format(redeemBeginDate));
			boolean flag02 = false;
			int effectedRows = investRedeemService
					.updateInvestRedeemBeginDate(param);
			if (effectedRows > 0) {
				flag02 = true;
			} else {
				flag02 = false;
			}

			if (true == flag01 && true == flag02) {
				OutputJson(getMessage(true));
			} else {
				OutputJson(getMessage(false));
			}
		} catch (ActivitiObjectNotFoundException e) {
			OutputJson(new DataModel("提示", "投资赎回流程开启 或者 投资赎回申请日期初始化发生异常", false));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 提交任务办理，比如审批通过，审批驳回，审批拒绝
	 * 
	 * @return
	 */
	public String submitTask() {
		try {
			boolean flag = investRedeemService.saveSubmitTask4InvestRedeem(
					investRedeem, investOrder, comment, result,
					processingResult);
			if (flag) {
				// 说明此时是“销客专员”角色的用户 办理任务，因为，根据业务要求，目前只有销客专员的角色才会输入“回款日期(HuiKuanDate)”。
				if (null != investRedeem
						&& null != investRedeem.getHuiKuanDate()) {
					InvestRedeemFinanceDetails4ClientVO redeemClientFinancialDetail = investRedeemService
							.getRedeemClientFinancialDetail(investRedeem,
									investOrder);
				}
				OutputJson(getMessage(true));
			} else {
				OutputJson(getMessage(false));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 投资赎回申请订单重提。
	 * 
	 * @return
	 */
	public String reSubmitTask() {
		try {
			// 2.提交办理的任务
			investRedeemWorkFlowService.saveSubmitTask(investOrder, comment,
					result, processingResult);
			OutputJson(getMessage(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// /**
	// * 修改投资订单的 “流程类型”，将其类型在“投资申请流程”与“投资赎回类型”之间变换。
	// *
	// * @Title: updateInvestOrderProcessType
	// * @Description: TODO
	// * @param @return
	// * @author ZHANGJIAN
	// * @return String
	// * @date 2015年12月16日 下午9:01:39
	// * @throws
	// */
	// public String updateInvestOrderProcessType() {
	// try {
	// OutputJson(getMessage(investOrderService
	// .updateInvestOrderProcessType(
	// this.investOrder.getInvestOrderId(),
	// this.investOrder.getInvestProcessType())));
	// } catch (ActivitiObjectNotFoundException e) {
	// OutputJson(new DataModel("提示", "您还没有部署投资申请流程!!", false));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	// /**
	// * 更改投资订单的流程状态，将其由“2(已结束状态)”更改为“0(未开启状态)”
	// *
	// * @Title: updateInvestOrderProcessStatusToWeiKaiQi
	// * @author ZHANGJIAN
	// * @return String
	// * @date 2015年12月17日 上午10:00:09
	// */
	// public String updateInvestRedeemProcessStatus() {
	// try {
	// OutputJson(getMessage(investOrderService
	// .updateInvestRedeemProcessStatus(
	// this.investOrder.getInvestOrderId(),
	// this.investOrder.getProcessStatus())));
	// } catch (ActivitiObjectNotFoundException e) {
	// OutputJson(new DataModel("提示",
	// "投资订单的流程状态由“2(已结束状态)”更改为“0(未开启状态)时出错”!!", false));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * 根据investOrderId，获取投资赎回的流程图
	 * 
	 * @Title: checkWorkFlowImg4InvestRedeemByInvestOrderId
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月17日 下午1:29:43
	 */
	public String checkWorkFlowImg4InvestRedeemByInvestOrderId() {
		try {
			investRedeemWorkFlowService
					.checkWorkFlowImg4InvestRedeemByInvestOrderId(this.investOrder
							.getInvestOrderId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询当前角色的代办任务列表（没有被签收的任务用）
	 * 
	 * @Title: findAllUnclaimedTaskList
	 * @Description: TODO 待办任务列表
	 */
	public String findAllUnclaimedTaskList() {
		GridModel gridModel = new GridModel();
		gridModel.setRows(investRedeemWorkFlowService.findAllUnclaimedTaskList(
				getFirstResult(), getMaxResults()));
		gridModel.setTotal(investRedeemWorkFlowService
				.findAllUnclaimedTaskCount());
		OutputJson(gridModel);
		return null;
	}

	/**
	 * 当前角色的用户签收任务
	 * 
	 * @Title: saveMyTask
	 * @Description: TODO 签收任务
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String pickMyTask() {
		try {
			investRedeemWorkFlowService.pickMyTask(investOrder.getTaskId());
			OutputJson(new DataModel("提示", "恭喜你,签收成功!", true));
		} catch (Exception e) {
			e.printStackTrace();
			OutputJson(new DataModel("提示", "出错了,签收失败!", false));
		}
		return null;
	}

	/**
	 * 办理任务
	 * 
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
	 * @author zhangjian
	 * @description 获取当前角色用户所有的受理任务
	 * @return 用户所有的受理任务的json信息
	 */
	public String findAllClaimedTask() {
		// 获取当前"角色（比如，销客专员）"已经受理的所有投资订单investOrder
		List<InvestOrder> rows = investRedeemWorkFlowService
				.findAllClaimedTask(getFirstResult(), getMaxResults());
		String userId = String.valueOf(Constants.getCurrendUser().getUserId());
		// 获取当前"角色（比如，销客专员）"已经受理的所有投资订单investOrder的"总数量"
		Long total = investRedeemWorkFlowService
				.findAllClaimedTaskCount(userId);
		this.OutputJson2(new GridModel(rows, total));
		return null;
	}

	/**
	 * 获取投资赎回的任务个数
	 */
	public String findInvestUnclaimedAndClaimedTaskCount() {
		String userId = String.valueOf(Constants.getCurrendUser().getUserId());
		Long unClaimTaskCount = investRedeemWorkFlowService
				.findAllUnclaimedTaskCount();
		Long claimTaskCount = investRedeemWorkFlowService
				.findAllClaimedTaskCount(userId);
		Map<String, Object> taskCoutMap = new HashMap<String, Object>();
		taskCoutMap.put("unClaimTaskCount", unClaimTaskCount);
		taskCoutMap.put("claimTaskCount", claimTaskCount);
		OutputJson(taskCoutMap);
		return null;
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

	public String[] getInvestOrderIds() {
		return investOrderIds;
	}

	public void setInvestOrderIds(String[] investOrderIds) {
		this.investOrderIds = investOrderIds;
	}

	public InvestRedeem getInvestRedeem() {
		return investRedeem;
	}

	public void setInvestRedeem(InvestRedeem investRedeem) {
		this.investRedeem = investRedeem;
	}

}
