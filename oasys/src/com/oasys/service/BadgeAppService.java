package com.oasys.service;


import java.util.List;

import com.oasys.model.BadgeApp;
import com.oasys.model.BadgeAppAttach;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.BadgeAppModel;
import com.oasys.viewModel.ComboBoxModel;
/**
 * 工牌申请
 * @author Administrator
 *
 */
public interface BadgeAppService 
{	
	/**
	 * 根据申请编号查询申请
	 * @Title: findBadgeAppNo 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return BadgeApp
	 * @date 2015年9月25日 下午4:36:58
	 * @throws
	 */
	BadgeApp findBadgeAppNo(String appNo);
	/**
	 * 保存申请用户
	 * @Title: saveBadgeAttach 
	 * @Description: TODO
	 * @param @param badgeAppAttach
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年9月25日 下午5:36:13
	 * @throws
	 */
	BadgeApp saveBadgeAttach(BadgeAppAttach badgeAppAttach);
	/**
	 * 查询申请下的申请人
	 * @Title: findBadgeAttList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<BadgeAppAttach>
	 * @date 2015年9月25日 下午5:53:23
	 * @throws
	 */
	List<BadgeAppAttach> findBadgeAttList(String appNo,String deptNo,PageUtil pageUtil);
	/**
	 * 申请下的申请人人数
	 * @Title: findbadgeAtttotal 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年9月30日 下午1:27:42
	 * @throws
	 */
	Long findbadgeAtttotal(String appNo,String deptNo);
	/**
	 * 根据id删除工牌申请人
	 * @Title: deleteBadgeAttList 
	 * @Description: TODO
	 * @param @param ids
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年9月28日 上午10:58:22
	 * @throws
	 */
	boolean deleteBadgeAttList(String ids);
	/**
	 * 根据工牌申请编号查询工牌申请详情信息
	 * @Title: findBadgeAppList 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<BadgeApp>
	 * @date 2015年9月28日 下午3:59:38
	 * @throws
	 */
	List<BadgeAppModel> getBadgeApp(PageUtil pageUtil,BadgeApp badgeApp);
	/**
	 * 查询工牌申请总条数
	 * @Title: total 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return int
	 * @date 2015年9月30日 下午12:54:02
	 * @throws
	 */
	Long findtotal(BadgeApp badgeApp);
	/**
	 * 根据申请编号删除申请所有信息
	 * @Title: deleteBadgeApp 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月8日 上午10:03:50
	 * @throws
	 */
	boolean deleteBadgeApp(String appNo);
	/**
	 * 修改工牌申请流程状态
	 * @Title: upBadgeProcStatus 
	 * @Description: TODO
	 * @param @param id
	 * @param @param c
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年10月8日 上午11:14:18
	 * @throws
	 */
	void upBadgeProcStatus(Integer id, String c);
	/**
	 * 更改工牌申请状态
	 * @Title: upBadgeAppStatus 
	 * @Description: TODO
	 * @param @param id
	 * @param @param appStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年10月9日 上午10:20:19
	 * @throws
	 */
	void upBadgeAppStatus(Integer id,String appStatus);
	/**
	 * 根据工牌申请id获得申请对象
	 * @Title: findBadgeByPnrId 
	 * @Description: TODO
	 * @param @param pnriId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return BadgeApp
	 * @date 2015年10月10日 上午11:41:48
	 * @throws
	 */
	BadgeApp findBadgeByPnrId(Integer pnrId);
	
	/**
	 * 根据申请编号删除空数据
	 * @Title: deletebadgeNotDate 
	 * @Description: TODO
	 * @param @param appNos
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月16日 上午10:42:43
	 * @throws
	 */
	boolean deletebadgeNotDate(String pnrIds);
	
	/**
	 * 根据申请编号和部门id查询附加表中的申请人id
	 * @Title: findDeptList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @param DeptNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年10月22日 下午1:46:35
	 * @throws
	 */
	String findDeptList(String appNo,String deptNo);
	/**
	 * 查询部门下的申请人,并排除已申请的人
	 * @Title: findDeptNoUserList 
	 * @Description: TODO
	 * @param @param deptNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<ComboBoxModel>
	 * @date 2015年10月22日 下午2:09:26
	 * @throws
	 */
	List<ComboBoxModel> findDeptNoUserList(String ids,String deptNo);
	/**
	 * 根据申请id判断流程kkey
	 * @Title: getBusinessKey 
	 * @Description: TODO
	 * @param @param pnrId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月19日 下午3:18:45
	 * @throws
	 */
	String getBusinessKey(Integer pnrId);
	
	/**
	 * 根据申请id判断流程kkey
	 * @Title: getBusinessKey 
	 * @Description: TODO
	 * @param @param pnrId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月19日 下午3:18:45
	 * @throws
	 */
	String getTaskImage(Integer pnrId);
	/**
	 * 获取当前登录人的部门id
	 * 如果是总部为null，分部有值
	 * @Title: findRegUserDeptNo 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Integer
	 * @date 2015年12月1日 下午4:45:08
	 * @throws
	 */
	Integer findRegUserDeptNo();
	
}
