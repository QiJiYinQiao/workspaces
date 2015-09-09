package com.bpms.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.InvestProduct;
import com.bpms.service.InvestProductService;
import com.bpms.util.Collections;
import com.bpms.util.PageUtil;
import com.bpms.view.model.ComboBoxModel;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 理财产品的Action
 * 
 * @author 张健 2015/07/15
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/07/15 张健 创建.
 */
@Namespace("/investProduct")
@Action(value = "investProductAction", results = {
	@Result(name = "showInvestProductDetails", location = "/jsp/investproducts/showInvestProductDetails.jsp" ,type = "dispatcher")	
})
public class InvestProductAction extends BaseAction implements ModelDriven<InvestProduct>{
	
	
	private static final long serialVersionUID = -3811004041460111343L;
	
	private String queryItem;	//接受页面传来的高级查询条件中的“queryItem”。
	private String operType;	//接受页面传来的高级查询条件中的“operType”。 	
	private String userInputCondition;   //接受页面传来的高级查询条件中的用户输入的条件。	

	//已经舍弃，改为利用模型驱动ModalDriven来获得ID参数
//	private String prodId;      //当修改，删除，查看理财产品时，页面上的超链接，获得相应理财产品的ID
	private InvestProduct investProduct = new InvestProduct();
	
	@Autowired
	private InvestProductService investProductService;

	/**
	 * 获得所有的理财产品信息列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllInvestProducts(){
		PageUtil pageUtil = new PageUtil(page,rows);
		Object[] allInvestProductsAndRowCount = investProductService.getAllInvestProducts(queryItem, operType, userInputCondition, pageUtil);
		List<InvestProduct> investProductList = (List<InvestProduct>) allInvestProductsAndRowCount[0];
		Long total = (Long) allInvestProductsAndRowCount[1];
		GridModel gridModel = new GridModel();
		gridModel.setRows(investProductList);
		gridModel.setTotal(total);
		this.OutputJson(gridModel);		
		return null;
	}
	
	/**
	 * 查询投资产品数据列表，将每个投资产品的prodId和prodName封装到ComboxBoxModel中。
	 * @return
	 */
	public String getAllInvestProducts4Combobox(){
		
		List<InvestProduct> investProductList = investProductService.getAllInvestProducts();
		List<ComboBoxModel> resultList = new ArrayList<ComboBoxModel>();
				
		if(Collections.listIsNotEmpty(investProductList)){
			for(InvestProduct product : investProductList ){
				ComboBoxModel comboBoxModel = new ComboBoxModel(product.getProdId().trim(), product.getProdName().trim());
				resultList.add(comboBoxModel);
			}			
		}		
		this.OutputJson(resultList);				
		return null;
	}
	
	/**
	 * 查询有效的投资产品数据列表
	 * @return
	 */
	public String getAllInvestProductsCombogrid(){
		
		List<InvestProduct> investProductList = investProductService.getAllInvestProductsAvailable();
		
		this.OutputJson(investProductList);				
		return null;
	}
	
	/**
	 * 根据理财产品的ID，删除相应的理财产品
	 */
	public String deleteInvestProductById(){
		DataModel json = null;
		//利用“模型驱动”获取用户输入的有待删除的InvestProduct的ID。
		String prodId = investProduct.getProdId();
		//判断前台传过来的，有待删除的理财产品订单 investProductId是否为空。
		if (StringUtils.isNotBlank(prodId)){
			if(investProductService.deleteInvestProductById(prodId)){
				json = new DataModel("提示","恭喜你删除成功!",true);
			}else{
				json = new DataModel("提示","抱歉，删除失败。",false);
			}					
		}else{
			json = new DataModel("提示","删除失败，投资订单的ID为空。",false);												
		}			
		OutputJson(json);
		return null;			
	}
	
	/**
	 * 根据理财产品的ID，查看相应的的理财产品的详细信息
	 */	
	public String findInvestProductDetailsById(){
		//利用“模型驱动”获取用户输入的有待查询的InvestProduct的ID。		
		String prodId = investProduct.getProdId();
		
		if (StringUtils.isNotBlank(prodId)){
			InvestProduct investProduct = investProductService.findInvestProductDetailsById(prodId);
			if(investProduct != null){
				ServletActionContext.getRequest().setAttribute("investProduct", investProduct);	
			}
		}			
		return "showInvestProductDetails";
	}
	
	/**
	 *根据id查找产品
	 */
	public String findInvestProductById(){
		Object obj=ServletActionContext.getRequest().getParameter("productId");
		String productId= obj!=null?obj.toString():"";
		if (StringUtils.isNotBlank(productId)){
			InvestProduct investProduct = investProductService.findInvestProductDetailsById(productId);
			OutputJson(investProduct);
		}			
		return null;
	}
	
	/**
	 * 保存理财产品的数据
	 * @return
	 */
	public String persistenceInvestProduct(){

		DataModel json = new DataModel();	
		boolean bl = investProductService.persistenceInvestProduct(getModel());
		if(bl){
			json.setStatus(true);
			json.setMessage("恭喜你，保存成功!");
			json.setTitle("提示");
			json.setData(getModel().getProdId());
		}else{
			json = new DataModel("提示","出错了,保存失败!",false);
		}
		OutputJson(json);
		return null;		
				
	}
	
	
	public InvestProductService getInvestProductService() {
		return investProductService;
	}

	public void setInvestProductService(InvestProductService investProductService) {
		this.investProductService = investProductService;
	}	

	public String getQueryItem() {
		return queryItem;
	}

	public void setQueryItem(String queryItem) {
		this.queryItem = queryItem;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getUserInputCondition() {
		return userInputCondition;
	}

	public void setUserInputCondition(String userInputCondition) {
		this.userInputCondition = userInputCondition;
	}

	public InvestProduct getModel() {
		if(investProduct==null){
			investProduct=new InvestProduct();
		}
		return investProduct;		
	}

	public InvestProduct getInvestProduct() {
		return investProduct;
	}


	public void setInvestProduct(InvestProduct investProduct) {
		this.investProduct = investProduct;
	}
	
}
