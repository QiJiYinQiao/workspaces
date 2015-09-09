package com.qqms.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.qqms.model.Loaner;
import com.qqms.model.VO.AccountJournalVO;
import com.qqms.model.VO.LoanOrderVO;
import com.qqms.model.VO.LoanerInfoHisVO;
import com.qqms.service.LoanerService;
import com.qqms.util.PageUtil;
import com.qqms.viewModel.GridModel;
@Namespace("/loaner")
@Action(value = "loanerAction")
@Results({
	@Result(name="loanerDetail",location="/jsp/loaner/loanerDetail.jsp",type = "dispatcher"),
	@Result(name="orderList",location="/jsp/loaner/loanerOrderList.jsp",type = "dispatcher"),
	@Result(name="loanOrderDetail",location="/jsp/loaner/loanOrderDetail.jsp",type = "dispatcher"),
	@Result(name="loanCreditAuditReportDetail",location="/jsp/loaner/loanCreditAuditReportDetail.jsp",type = "dispatcher"),
	@Result(name="loanerHisList",location="/jsp/loaner/loanerHisList.jsp",type = "dispatcher")
})
public class LoanerAction extends BaseAction
{

	/**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1L;
	private LoanerService loanerService;
	private String name;//贷款客户姓名
	private String idNo;//身份证号
	private String genderType;//性别
	private String loanerId;//贷款人id
	private String loanOrderId;//贷款订单id
	private LoanOrderVO loanOrderVO;
	

	@Autowired
	public void setLoanerService(LoanerService loanerService) {
		this.loanerService = loanerService;
	}

	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月23日 下午4:16:14
	 * @Title:findLoanerAllList
	 * @Description:TODO 查询贷款客户信息列表（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String findLoanerList(){
		PageUtil pageUtil = new PageUtil(page,rows);
		Object[] obj=loanerService.findLoanerList(name, idNo, genderType,pageUtil);
		List<Loaner> loanerList=(List<Loaner>) obj[0];
		Long count=(Long) obj[1];
		GridModel gridModel=new GridModel();
		gridModel.setRows(loanerList);
		gridModel.setTotal(count);
		OutputJson(gridModel);
		return null;
	}
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月30日 上午11:47:33
	 * @Title:forLastLoanerDetail
	 * @Description:TODO 查看客户最新信息详情（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String forLastLoanerDetail(){
		String orderId = loanerService.findLoanerDetailById(loanerId);
		if(StringUtils.isNotBlank(orderId)){
		Object[] obj = loanerService.findLoanerDetailByOrderId(orderId);
		Object baseObj= obj[0];
		List<Object> companyList= (List<Object>) obj[1];
		List<Object> assetsList = (List<Object>) obj[2];
		Object spouseObj = obj[3];
		List<Object> emccontactList = (List<Object>) obj[4];
		ServletActionContext.getRequest().setAttribute("baseObj", baseObj);
		ServletActionContext.getRequest().setAttribute("companyList", companyList);
		ServletActionContext.getRequest().setAttribute("assetsList", assetsList);
		ServletActionContext.getRequest().setAttribute("spouseObj", spouseObj);
		ServletActionContext.getRequest().setAttribute("emccontactList", emccontactList);
		}
		return "loanerDetail";
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月24日 上午9:43:51
	 * @Title:forLoanerDetail
	 * @Description:TODO 根据订单id跳转到贷款人详情页面 （这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String forLoanerDetail(){
		Object[] obj = loanerService.findLoanerDetailByOrderId(loanOrderId);
		Object baseObj= obj[0];
		Object companyObj= obj[1];
		List<Object> assetsList = (List<Object>) obj[2];
		Object spouseObj = obj[3];
		List<Object> emccontactList = (List<Object>) obj[4];
		ServletActionContext.getRequest().setAttribute("baseObj", baseObj);
		ServletActionContext.getRequest().setAttribute("companyObj", companyObj);
		ServletActionContext.getRequest().setAttribute("assetsList", assetsList);
		ServletActionContext.getRequest().setAttribute("spouseObj", spouseObj);
		ServletActionContext.getRequest().setAttribute("emccontactList", emccontactList);
		return "loanerDetail";
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月24日 上午9:44:44
	 * @Title:forLoanerOrderList
	 * @Description:TODO 跳转到贷款人订单列表页面（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String forLoanerOrderList(){
		return "orderList";
	}
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月24日 下午8:20:29
	 * @Title:showLoanerOrderList
	 * @Description:TODO查询客户贷款订单列表（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String showLoanerOrderList(){
		PageUtil pageUtil = new PageUtil(page,rows);
		Object[] obj = loanerService.findLoanerOrderListById(loanerId,pageUtil);
		List<LoanOrderVO> loanOrderVOList = (List<LoanOrderVO>) obj[0];
		Long count = (Long) obj[1];
		GridModel gridModel=new GridModel();
		gridModel.setRows(loanOrderVOList);
		gridModel.setTotal(count);
		OutputJson(gridModel);
		return null;
	}
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月25日 下午2:27:09
	 * @Title:forLoanOrderDetail
	 * @Description:TODO 跳到贷款订单详情页面（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String forLoanOrderDetail(){
		loanOrderVO=loanerService.findLoanOrderDetailById(loanOrderId);
		return "loanOrderDetail";
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月30日 上午10:28:16
	 * @Title:forLoanCreditAuditReportDetail
	 * @Description:TODO 跳到贷款信审报告详情页面（这里描述这个方法的作用）
	 * @return
	 * @throws CloneNotSupportedException 
	 * @throws:
	 */
	public String forLoanCreditAuditReportDetail() throws Exception {
		Object[] obj = loanerService.findCreditAuditDetailByOrderId(loanOrderId);
		Object baseObj = obj[0];
		Object loanObj = obj[1];
		Object creditCardObj = obj[2];
		List<Object> creditList = (List<Object>) obj[3];
		List<AccountJournalVO> accountList = (List<AccountJournalVO>) obj[4];
		Object assetsObj = obj[5];
		Object firstAuditObj = obj[6];
		Object finalAuditObj = obj[7];
		ServletActionContext.getRequest().setAttribute("baseObj", baseObj);
		ServletActionContext.getRequest().setAttribute("loanObj", loanObj);
		ServletActionContext.getRequest().setAttribute("creditCardObj", creditCardObj);
		ServletActionContext.getRequest().setAttribute("creditList", creditList);
		ServletActionContext.getRequest().setAttribute("accountList", accountList);
		ServletActionContext.getRequest().setAttribute("assetsObj", assetsObj);
		ServletActionContext.getRequest().setAttribute("firstAuditObj", firstAuditObj);
		ServletActionContext.getRequest().setAttribute("finalAuditObj", finalAuditObj);
		return "loanCreditAuditReportDetail";
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月3日 上午10:58:24
	 * @Title:forLoanerHisList
	 * @Description:TODO 跳到贷款人变更履历列表（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String forLoanerHisList(){
		return "loanerHisList";
	}
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月3日 上午10:30:42
	 * @Title:showLoanerHisList
	 * @Description:TODO 客户履历变更详情（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String showLoanerHisList(){
		PageUtil pageUtil = new PageUtil(page,rows);
		Object[] obj = loanerService.findLoanerHisListByLoanerId(loanerId,pageUtil);
		List<LoanerInfoHisVO> loanerInfoHisList=(List<LoanerInfoHisVO>) obj[0];
		Long count=(Long) obj[1];
		GridModel gridModel=new GridModel();
		gridModel.setRows(loanerInfoHisList);
		gridModel.setTotal(count);
		OutputJson(gridModel);
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getGenderType() {
		return genderType;
	}

	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}

	public String getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public LoanOrderVO getLoanOrderVO() {
		return loanOrderVO;
	}

	public void setLoanOrderVO(LoanOrderVO loanOrderVO) {
		this.loanOrderVO = loanOrderVO;
	}
	
	
	
}
