package com.oasys.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.StampStatisticsReg;
import com.oasys.service.StampStatisticsService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.viewModel.ComboBoxModel;

@Service("stampStatisticsService")
@SuppressWarnings("unchecked")
public class StampStatisticsServiceImpl  extends WorkFlowBaseServiceImpl  implements StampStatisticsService
{

	
	
	@Autowired
	private PublicDao<StampStatisticsReg> stampDao; 
	
	/**
	 * 根据印章类型和部门id查询印章
	 */
	@Override
	public List<ComboBoxModel> findStampStatisticsList(String stampType,
			String organizeId,String ids) {
		try {
			
			
			String sql=" SELECT SSR_ID,STAMP_NAME FROM t_oa_ad_stamp_statistics_reg WHERE 1=1 AND SURPLUS_QTY>0 ";
			if(StringUtils.isNotBlank(stampType)){
				sql+=" AND STAMP_NATURE='"+stampType+"' ";
			}
			if(StringUtils.isNotBlank(organizeId)){
				sql+=" AND BELONG_DEPT='"+organizeId+"' ";
			}
			if(StringUtils.isNotBlank(ids)){
				sql+=" AND SSR_ID NOT IN ("+ids+")";
			}
			
			
			List list = stampDao.findBySQL(sql);
			List<ComboBoxModel> comList=new ArrayList<ComboBoxModel>();
			
			if(Collections.listIsNotEmpty(list)){
				for (int i = 0; i < list.size(); i++) {
					Object[] obj=(Object[]) list.get(i);
					String code=String.valueOf(obj[0]);
					String text=String.valueOf(obj[1]);
					
					ComboBoxModel boxModel=new ComboBoxModel(code, text);
					comList.add(boxModel);
				}
			}
			return comList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<ComboBoxModel>();
	}

	/**
	 * 根据id查询印章的名字
	 */
	@Override
	public String findStampNameById(Integer taaId) {
		try {
			String sql="SELECT STAMP_NAME FROM t_oa_ad_stamp_statistics_reg WHERE 1=1 AND SSR_ID="+taaId;
			List list = stampDao.findBySQL(sql);
			if(Collections.listIsNotEmpty(list)){
				return String.valueOf(list.get(0));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改印章剩余数量及最后修改人
	 */
	@Override
	public void updateStampQTYANDBorrow(Integer taaId, Integer applicanton) {
		StampStatisticsReg stampStatisticsReg = stampDao.get(StampStatisticsReg.class, taaId);
		stampStatisticsReg.setSurplusQty(stampStatisticsReg.getSurplusQty()+1);
		if(applicanton!=null && applicanton!=0 && stampStatisticsReg.getLastBorrower()==applicanton){
			stampStatisticsReg.setLastBorrower(null);
		}
		stampDao.saveOrUpdate(stampStatisticsReg);
	}
	
	
	




	
	
	
	
}
