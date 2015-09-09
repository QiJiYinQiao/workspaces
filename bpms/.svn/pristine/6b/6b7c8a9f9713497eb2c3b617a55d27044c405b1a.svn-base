package com.bpms.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.InvestorderAndProducts;
import com.bpms.model.Users;
import com.bpms.model.vo.InvestPerformanceModel;
import com.bpms.service.InvestOrderWorkFlowService;
import com.bpms.service.InvestorderAndProductsService;
import com.bpms.util.Collections;
import com.bpms.util.DateUtils;
import com.bpms.util.PageUtil;
import com.bpms.util.ReadExcel;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * @ClassName: InvestOrderAndProductsAction
 * @Description: TODO 投资订单与产品关系Action
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年7月24日 下午2:56:25
 *
 */
@Namespace("/investorderAndProducts")
@Action("investorderAndProductsAction")
@Results({
	@Result(name="gotoCompleteOrderInfo",location="/jsp/investOrder/investOrderEditForm.jsp",type = "dispatcher")
})
public class InvestorderAndProductsAction  extends BaseAction implements ModelDriven<InvestorderAndProducts> {
 /**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InvestorderAndProductsService investorderAndProductsService;
	@Autowired
	private InvestOrderWorkFlowService investOrderWorkFlowService;//投资工作流service
	
	private InvestorderAndProducts investorderAndProducts;
	
	private String orderId;//投资订单id
	
	private String ids;//投资订单组
	
	//////查询理财业绩报表用到的字段
	private String queryDate;//理财业绩查询日期
	private String orgId;//理财业绩查询机构id
	private String orgName;//理财业绩查询机构名称
	
	//////建立订单与理财产品关系计算计息日用到的字段
	private String huakouDate;//划扣日期
	private String lendingCycle;//出借周期
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月24日 下午3:18:04
	 * @Title:findInvestorderAndProductsList
	 * @Description:TODO 投资订单与理财产品关系列表（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String findInvestorderAndProductsList(){
		GridModel gridModel = new GridModel();
		gridModel.setRows(investorderAndProductsService.findInvestorderAndProductsList(orderId));
		gridModel.setTotal(investorderAndProductsService.counts(orderId));
		OutputJson2(gridModel);
		return null;
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月24日 下午3:18:37
	 * @Title:persistenceInvestorderAndProducts
	 * @Description:TODO 持久化投资订单与理财产品关系（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String persistenceInvestorderAndProducts(){
		//若果已有理财产品替换掉原来的
		List<InvestorderAndProducts> list = investorderAndProductsService.findInvestorderAndProductsList(getModel().getInvestOrderId());
		if(list.size()>0){
			for(InvestorderAndProducts o:list){
				investorderAndProductsService.deleteInvestorderAndProducts(o.getId());
			}
		}
		OutputJson2(getMessage(investorderAndProductsService.persistenceInvestorderAndProducts(investorderAndProducts)));
		return null;
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月27日 上午11:18:25
	 * @Title:deleteInvestorderAndProducts
	 * @Description:TODO 合同里取消理财产品（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String deleteInvestorderAndProducts(){
		OutputJson2(getMessage(investorderAndProductsService.deleteInvestorderAndProducts(ids)));
		return null;
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月27日 下午5:04:00
	 * @Title:gotoCompleteOrderInfo
	 * @Description:TODO 跳到完善客户及合同信息页面（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String gotoCompleteOrderInfo(){
		Users u = investOrderWorkFlowService.getFinancingManagerByOrderId(orderId);
		System.out.println(u.getName());
		ServletActionContext.getRequest().setAttribute("users", u);
		return "gotoCompleteOrderInfo";
	}
	
	/**
	 * 
	 * @time:2015年8月6日 下午1:26:01
	 * @Title:findInvestPerformanceReportListByDate
	 * @Description:TODO 查询理财业绩报表（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String findInvestPerformanceReportListByDate(){
		PageUtil pageUtil = new PageUtil(page,rows);
		Object[] obj = investorderAndProductsService.findInvestPerformanceReportListByDate(queryDate,orgId, pageUtil);
		@SuppressWarnings("unchecked")
		List<InvestPerformanceModel> ipList = (List<InvestPerformanceModel>) obj[0];
		Long count =(Long) obj[1];
		GridModel gridModel = new GridModel();
		gridModel.setRows(ipList);
		gridModel.setTotal(count);
		OutputJson(gridModel);		
		return null;
	}
	
	/**
	 * 
	 * @time:2015年8月7日 上午11:39:25
	 * @Title:doExportExcel
	 * @Description:TODO 导出理财业绩报表（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String doExportExcel(){
			try {
				PageUtil pageUtil = new PageUtil(0,0);
				@SuppressWarnings("unchecked")
				List<InvestPerformanceModel> modelList = (List<InvestPerformanceModel>) investorderAndProductsService.findInvestPerformanceReportListByDate(queryDate, orgId, pageUtil)[0];
				if(modelList!=null && modelList.size()>0){
					String srcXlsxPath = ServletActionContext.getServletContext().getRealPath("/excel")+"\\钱钱金融财富中心x月业绩报表.xlsx";//模板路径
					Calendar nowDate = Calendar.getInstance(); //获取当前时间
					Integer year = nowDate.get(Calendar.YEAR);
					Integer month = nowDate.get(Calendar.MONTH) + 1;
					String title = "钱钱金融财富中心"+year+"年"+month+"月业绩报表";
					Workbook workbook  = ReadExcel.openExcleFile(srcXlsxPath);//获取工作簿7
					Sheet sheet = workbook.getSheetAt(0);//获取页签
					Row row = sheet.getRow(0);//获取第一行
					Cell cell = row.getCell(0);
					cell.setCellValue(title);//设置标题
					Row row1 = sheet.getRow(2);//获取第三行
					Cell cell1 = row1.getCell(0);
					String stringCellValue = cell1.getStringCellValue();
					if(StringUtils.isBlank(orgId)){
						orgName="全部";
					}
					if(StringUtils.isBlank(queryDate)){
						queryDate = DateUtils.getFormatDateString("yyyy-MM-dd",Calendar.getInstance());
					}
					String format = MessageFormat.format(stringCellValue,orgName,queryDate);
					cell1.setCellValue(format);
					BigDecimal countInvestMoneyDay = new BigDecimal(0);
					int countCountDay = 0;
					BigDecimal countInvestEduMonth = new BigDecimal(0);
					BigDecimal countInvestEduMonthOfYear = new BigDecimal(0);
					if(Collections.listIsNotEmpty(modelList)){
						for (int i = 0; i < modelList.size(); i++) {
							//第一行不用copy
							if(i!=0){
								ReadExcel.copyRows(sheet,5,5,4+i);
							}
							InvestPerformanceModel im = modelList.get(i);
							Row rowx = sheet.getRow(4+i);
							rowx.getCell(0).setCellValue(i+1);
							rowx.getCell(1).setCellValue(im.getOrgName());
							rowx.getCell(2).setCellValue(im.getInvestMoneyDay());
							rowx.getCell(3).setCellValue(im.getCountDay());
							rowx.getCell(4).setCellValue(im.getInvestEduMonth());
							rowx.getCell(5).setCellValue(im.getInvestEduMonthOfYear());
							countInvestMoneyDay = countInvestMoneyDay.add(BigDecimal.valueOf(Double.valueOf(im.getInvestMoneyDay())));
							countCountDay += Integer.parseInt(im.getCountDay());
							countInvestEduMonth = countInvestEduMonth.add(BigDecimal.valueOf(Double.valueOf(im.getInvestEduMonth())));
							countInvestEduMonthOfYear = countInvestEduMonthOfYear.add(BigDecimal.valueOf(Double.valueOf(im.getInvestEduMonthOfYear())));
						}
						//添加合计行
						ReadExcel.copyRows(sheet,5,5,4+modelList.size());
						Row rowlast = sheet.getRow(4+modelList.size());
				        //在sheet里增加合并单元格  
				        sheet.addMergedRegion(new CellRangeAddress(rowlast.getRowNum(), rowlast.getRowNum(), 0, 1));
				        rowlast.getCell(0).setCellValue("合计");
						rowlast.getCell(2).setCellValue(String.valueOf(countInvestMoneyDay));
						rowlast.getCell(3).setCellValue(String.valueOf(countCountDay));
						rowlast.getCell(4).setCellValue(String.valueOf(countInvestEduMonth));
						rowlast.getCell(5).setCellValue(String.valueOf(countInvestEduMonthOfYear));
						HttpServletResponse response = ServletActionContext.getResponse();
						response.setHeader("Content-disposition", "attachment;filename="+new String((title+".xlsx").getBytes("gb2312"),"iso8859-1"));
						OutputStream ouputStream = response.getOutputStream(); 
						workbook.write(ouputStream);
						ouputStream.flush();
						ouputStream.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
	/**
	 * 
	 * @time:2015年8月10日 上午10:57:38
	 * @Title:getInterestDateAndEndDateByBeginDate
	 * @Description:TODO 根据理财产品的划扣日期计算计息日和到期日（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String findInterestDateAndEndDateByBeginDate(){
		Object[] obj = investorderAndProductsService.findInterestDateAndEndDateByBeginDate(huakouDate, lendingCycle);
		String interestDate = String.valueOf(obj[0]);
		String endDate = String.valueOf(obj[1]);
		List<Object> list=new ArrayList<Object>();
		list.add(interestDate);
		list.add(endDate);
		OutputJson(list);
		return null;
	}
	
	@Override
	public InvestorderAndProducts getModel() {
		// TODO Auto-generated method stub
		if(investorderAndProducts==null){
			investorderAndProducts=new InvestorderAndProducts();
		}
		return investorderAndProducts;
	}
	
	public InvestorderAndProducts getInvestorderAndProducts() {
		return investorderAndProducts;
	}

	public void setInvestorderAndProducts(
			InvestorderAndProducts investorderAndProducts) {
		this.investorderAndProducts = investorderAndProducts;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getHuakouDate() {
		return huakouDate;
	}

	public void setHuakouDate(String huakouDate) {
		this.huakouDate = huakouDate;
	}

	public String getLendingCycle() {
		return lendingCycle;
	}

	public void setLendingCycle(String lendingCycle) {
		this.lendingCycle = lendingCycle;
	}
	
	
}
