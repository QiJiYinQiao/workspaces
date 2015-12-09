package com.oasys.service;

import java.util.List;

import com.oasys.model.MonthlyWorkPlanAttach;

/**
 * 管理中心月度计划附件表Service 
 * @ClassName: MonthlyWorkPlanAttachService 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月16日 下午3:18:55
 */
public interface MonthlyWorkPlanAttachService {

	/**
	 * @Title: saveMonthlyWorkPlanAttach 
	 * @Description: TODO 新增
	 * @param @param purchaseAppAttach
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年9月16日 下午3:20:03
	 * @throws
	 */
	boolean saveMonthlyWorkPlanAttach(MonthlyWorkPlanAttach monthlyWorkPlanAttach);
	/**
	 * 删除
	 * @Title: delMonthlyWorkPlanAttach 
	 * @Description: TODO 
	 * @param @param paId
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年9月18日 上午11:38:08
	 * @throws
	 */
	boolean delMonthlyWorkPlanAttachByMwpaIds(String mwpaIds);
	/**
	 * 根据id查询单个对象
	 * @Title: findMonthlyWorkPlanAttachByMwpaId 
	 * @Description: TODO
	 * @param @param psaId
	 * @param @return
	 * @author PANCHUANHE
	 * @return MonthlyWorkPlanAttach
	 * @date 2015年9月18日 下午4:29:08
	 * @throws
	 */
	MonthlyWorkPlanAttach findMonthlyWorkPlanAttachByMwpaId(Integer mwpaId);
	/**
	 * 根据申请编号查询MonthlyWorkPlanAttach的集合
	 * @Title: findMonthlyWorkPlanAttachList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<MonthlyWorkPlanAttach>
	 * @date 2015年9月21日 上午10:57:24
	 * @throws
	 */
	List<MonthlyWorkPlanAttach> findMonthlyWorkPlanAttachList(Integer mwpId);
	/**
	 * 根据mwpId删除
	 * @Title: delMonthlyWorkPlanAttachByAppNo 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<MonthlyWorkPlanAttach>
	 * @date 2015年9月21日 上午10:57:24
	 * @throws
	 */
	boolean delMonthlyWorkPlanAttachByMwpId(String mwpIds);
}
