package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.PpeStockInfo;
import com.oasys.model.PpeUsedConfirmApp;
import com.oasys.model.PpeUsedConfirmAppAttach;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.GridModel;


/**
 * 固定资产使用申请
 * @author Administrator
 *
 */
public interface PpeUsedConfirmService extends WorkFlowBaseService
{	
	
	/**
	 * 查询固定资产使用申请列表
	 * @Title: findPpeUsedConfirmList 
	 * @Description: TODO
	 * @param @param ppeUsedConfirmApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return GridModel
	 * @date 2015年12月4日 上午11:51:38
	 * @throws
	 */
	GridModel findPpeUsedConfirmList(PpeUsedConfirmApp ppeUsedConfirmApp,PageUtil pageUtil);
	
	/**
	 * 查询固定资产使用列表基本sql语句
	 * @Title: getPpeUsedConSql 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return StringBuffer
	 * @date 2015年12月4日 上午11:52:57
	 * @throws
	 */
	StringBuffer getPpeUsedConSql();
	/**
	 * 保存固定资产使用申请主表信息
	 * @Title: saveOrUpdPpeUsedConfirm 
	 * @Description: TODO
	 * @param @param ppeUsedConfirmApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return PpeUsedConfirmApp
	 * @date 2015年12月4日 下午3:39:49
	 * @throws
	 */
	PpeUsedConfirmApp saveOrUpdPpeUsedConfirm(PpeUsedConfirmApp ppeUsedConfirmApp);
	/**
	 * 根据申请编号查询固定资产使用申请主表信息
	 * @Title: findPpeUsedConfirmByAppNo 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return PpeUsedConfirmApp
	 * @date 2015年12月4日 下午3:51:08
	 * @throws
	 */
	PpeUsedConfirmApp findPpeUsedConfirmByAppNo(String appNo);
	/**
	 * 根据申请编号删除固定资产使用申请
	 * @Title: deletePpeusedConfirm 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年12月7日 下午1:25:32
	 * @throws
	 */
	boolean deletePpeusedConfirm(String appNo);
	/**
	 * 根据申请编号查询固定资产使用申请
	 * @Title: findPpeUsedByAppNo 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return PpeUsedConfirmApp
	 * @date 2015年12月7日 下午1:51:31
	 * @throws
	 */
	PpeUsedConfirmApp findPpeUsedByAppNo(String appNo);
	/**
	 * 当固定资产为领用时，更新固定资产的保管人
	 * @Title: updatePpeStock 
	 * @Description: TODO
	 * @param @param psaId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月7日 下午3:47:00
	 * @throws
	 */
	void updatePpeStock(Integer psaId);
	/**
	 * 查询流程图
	 * @Title: getDiagramResourceByPaId 
	 * @Description: TODO
	 * @param @param response
	 * @param @param psaId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月7日 下午5:04:48
	 * @throws
	 */
	void getDiagramResourceByPaId(HttpServletResponse response,
			Integer psaId);
	
	//-----------------------------------------附加表-----------------------------------------------------------------
	
	/**
	 * 保存固定资产使用申请附加表信息
	 * @Title: saveOrUpdPpeUsedConAtt 
	 * @Description: TODO
	 * @param @param ppeUsedConfirmAppAttach
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月7日 上午10:47:23
	 * @throws
	 */
	String saveOrUpdPpeUsedConAtt(PpeUsedConfirmAppAttach ppeUsedConfirmAppAttach);
	/**
	 * 根据申请编号查询固定资产列表
	 * @Title: findPpeUsedConAttList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<PpeUsedConfirmAppAttach>
	 * @date 2015年12月7日 上午11:03:27
	 * @throws
	 */
	GridModel findPpeUsedConAttList(String appNo,PageUtil pageUtil);
	/**
	 * 删除该申请的固定资产，附加表信息
	 * @Title: deletePpeUsedconAtt 
	 * @Description: TODO
	 * @param @param ids
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年12月7日 下午1:36:55
	 * @throws
	 */
	boolean deletePpeUsedconAtt(String ids,String appNo);
	
	/**
	 * 根据申请编号查询所申请的固定资产数量
	 * @Title: findPpeUsedConAttTotal 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Integer
	 * @date 2015年12月7日 下午1:56:26
	 * @throws
	 */
	Integer findPpeUsedConAttTotal(String appNo);
	/**
	 * 根据固定资产编号查询固定资产
	 * @Title: findPpeStockPpeNo 
	 * @Description: TODO
	 * @param @param ppeNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return PpeStockInfo
	 * @date 2015年12月7日 下午2:40:36
	 * @throws
	 */
	String findPpeStockPpeNo(String ppeNo);
	/**
	 * 查询该申请下的固定资产确认附加表
	 * @Title: findPpeUsedAttList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<PpeUsedConfirmAppAttach>
	 * @date 2015年12月7日 下午4:26:02
	 * @throws
	 */
	List<PpeUsedConfirmAppAttach> findPpeUsedAttList(String appNo);
	/**
	 * 更新申请状态
	 * @Title: updateAppStatus 
	 * @Description: TODO
	 * @param @param psaId
	 * @param @param appStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月7日 下午4:32:19
	 * @throws
	 */
	void updateAppStatus(Integer psaId,String appStatus);
	/**
	 * 更新流程状态
	 * @Title: updateProcStatus 
	 * @Description: TODO
	 * @param @param psaId
	 * @param @param procStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月7日 下午4:32:55
	 * @throws
	 */
	void updateProcStatus(Integer psaId,String procStatus);
	/**
	 * 根据深情编号查询附加表中的id集合
	 * @Title: findPpeUsedAttPsaIdList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<String>
	 * @date 2015年12月8日 下午2:35:43
	 * @throws
	 */
	List<String> findPpeUsedAttPsaIdList(String appNo);
	
	
	
	
}
