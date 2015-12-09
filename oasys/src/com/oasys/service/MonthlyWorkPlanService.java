package com.oasys.service;

import java.util.List;

import com.oasys.model.MonthlyWorkPlan;
import com.oasys.model.VO.MonthlyWorkPlanModel;
import com.oasys.model.VO.MonthlyWorkPlanModel;
import com.oasys.util.PageUtil;

/**
 * 管理中心月度工作计划表Service
 * @ClassName: MonthlyWorkPlanService 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:20:24
 */
public interface MonthlyWorkPlanService {
	/**
	 * 新增保存MonthlyWorkPlan
	 * @Title: saveMonthlyWorkPlan 
	 * @Description: TODO
	 * @param @param axpensesApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月12日 上午11:57:48
	 * @throws
	 */
	boolean saveMonthlyWorkPlan(MonthlyWorkPlan eonthlyWorkPlan);
	/**
	 * 查询list列表
	 * @Title: findMonthlyWorkPlanList 
	 * @Description: TODO
	 * @param @param pageUtil
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<MonthlyWorkPlan>
	 * @date 2015年10月12日 下午2:49:17
	 * @throws
	 */
	List<MonthlyWorkPlanModel> findMonthlyWorkPlanList(PageUtil pageUtil,MonthlyWorkPlanModel monthlyWorkPlanModel);
	/**
	 * 查询总条数
	 * @Title: countFindMonthlyWorkPlanList 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return Long
	 * @date 2015年10月12日 下午3:21:26
	 * @throws
	 */
	Long countFindMonthlyWorkPlanList();
	/**
	 * 根据ids删除
	 * @Title: delMonthlyWorkPlanbyMwpId 
	 * @Description: TODO
	 * @param @param btaId
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月12日 下午4:41:00
	 * @throws
	 */
    boolean delMonthlyWorkPlanbyMwpId(String ids);
}
