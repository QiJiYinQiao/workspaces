package com.oasys.service;

import java.util.List;

import com.oasys.model.StampStatisticsReg;
import com.oasys.service.workFlow.WorkFlowBaseService;
import com.oasys.viewModel.ComboBoxModel;


/**
 * 印章管理
 * @Title: StampStatisticsService.java 
 * @Package com.oasys.service 
 * @Description: TODO
 * @author WANGXINCHENG  
 * @date 2015年12月14日 下午1:51:09 
 * @version V1.0
 */
public interface StampStatisticsService extends WorkFlowBaseService
{	
	
	/**
	 * 根据印章类型和部门id查询印章,已经被申请的印章
	 * @Title: findStampStatisticsList 
	 * @Description: TODO
	 * @param @param stampType
	 * @param @param organizeId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<StampStatisticsReg>
	 * @date 2015年12月14日 下午1:52:51
	 * @throws
	 */
	List<ComboBoxModel> findStampStatisticsList(String stampType,String organizeId,String ids);
	
	/**
	 * 根据id】查询印章的名字
	 * @Title: findStampNameById 
	 * @Description: TODO
	 * @param @param ssaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月15日 上午9:38:15
	 * @throws
	 */
	String findStampNameById(Integer taaId);
	/**
	 * 根据id更新印章剩余数量及最后修改人
	 * @Title: updateStampQTYANDBorrow 
	 * @Description: TODO
	 * @param @param taaId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月15日 下午8:09:23
	 * @throws
	 */
	void updateStampQTYANDBorrow(Integer taaId,Integer applicanton);
	
	
}
