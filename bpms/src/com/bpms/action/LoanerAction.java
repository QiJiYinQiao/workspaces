package com.bpms.action;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.Address;
import com.bpms.model.Loaner;
import com.bpms.model.vo.LoanerModel;
import com.bpms.service.AddressService;
import com.bpms.service.CommonService;
import com.bpms.service.LoanerService;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 个人贷款业务的申请单请求处理器的action
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
@Namespace("/loaner")
@Action(value = "loanerAction")
public class LoanerAction extends BaseAction implements
		ModelDriven<LoanerModel> {
	private static final long serialVersionUID = 3658084064057123814L;
	@Autowired
	private LoanerService loanerService;
	@Autowired
	private AddressService addressServiceImpl;
	@Autowired
	private CommonService commonService;
	
	private LoanerModel loanModel = new LoanerModel();
	/** 模糊查询到参数. */
	private String q;

	/** 下拉表格的获取. */
	public void findCombgridLoaner() {
		try {
			List<LoanerModel> rows = loanerService.findCombogridLoaner(q);
			OutputJson(new GridModel(rows, (long) (rows != null ? rows.size()
					: 0)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 保存或修改用户的基本信息. */
	public void persistenceLoaner() {
		OutputJson(this.loanerService.persistenceLoaner(loanModel));
	}
	
	/**
	 * 查询一个实体
	 * @return
	 */
	public String queryLoaner(){
		try {
			LoanerModel lm = new LoanerModel();
			Loaner loaner = loanerService.findById(loanModel.getLoanerId());
			if(loaner!=null){
				PropertyUtils.copyProperties(lm, loaner);
				//户籍地址
				Address hukouAddress = addressServiceImpl.findById(loaner.getHukouAddr());
				if(hukouAddress!=null){
					lm.setHukouProvinceId(hukouAddress.getProvinceId());
					lm.setHukouCityId(hukouAddress.getCityId());
					lm.setHukouAreaId(hukouAddress.getAreaId());
					lm.setHukouAddrDetails(hukouAddress.getAddrDetails());
					lm.setHukouAddress(addressServiceImpl.addressName(loaner.getHukouAddr()));
				}
				//现住地址
				Address curAddress = addressServiceImpl.findById(loaner.getCurAddr());
				if(curAddress!=null){
					lm.setCurProvinceId(curAddress.getProvinceId());
					lm.setCurCityId(curAddress.getCityId());
					lm.setCurAreaId(curAddress.getAreaId());
					lm.setCurAddrDetails(curAddress.getAddrDetails());
					lm.setCurAddress(addressServiceImpl.addressName(loaner.getCurAddr()));
				}
				lm.setGenderTypeName(commonService.findDictName("gender_type", lm.getGenderType()));
				lm.setHasChildName(commonService.findDictName("has_child", lm.getHasChild()));
				lm.setMarriageTypeName(commonService.findDictName("marriage_type", lm.getMarriageType()));
				lm.setMortgageStatusName(commonService.findDictName("mortgage_status", lm.getMortgageStatus()));
			}
			//转为json
			OutputJson(lm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public LoanerModel getModel() {
		return loanModel;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

}
