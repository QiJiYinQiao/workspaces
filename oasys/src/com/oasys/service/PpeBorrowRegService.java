package com.oasys.service;


import java.util.List;

import com.oasys.model.PpeBorrowReg;
import com.oasys.util.PageUtil;
/**
 * 用章申请
 * @author Administrator
 *
 */
public interface PpeBorrowRegService extends WorkFlowBaseService
{	
	/**
	 * 查询固定资产借用登记列表
	 * @Title: findPpeBorrowRegAttList 
	 * @Description: TODO
	 * @param @param PpeBorrowReg
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<PpeBorrowReg>
	 * @date 2015年10月10日 下午4:05:58
	 * @throws
	 */
	List<PpeBorrowReg> findPpeBorrowRegList(PpeBorrowReg  ppeBorrowReg,PageUtil pageUtil);
	/**
	 * 查询用章申请数量
	 * @Title: getTatol 
	 * @Description: TODO
	 * @param @param ppeBorrowReg
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年10月16日 上午9:36:55
	 * @throws
	 */
	Long getTatol(PpeBorrowReg  ppeBorrowReg);
	
	/**
	 * 保存固定资产借用登记
	 * @Title: saveOrUpdPpeBorrowReg 
	 * @Description: TODO
	 * @param @param ppeBorrowReg
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月15日 下午2:54:48
	 * @throws
	 */
	boolean saveOrUpdPpeBorrowReg(PpeBorrowReg ppeBorrowReg);
	/**
	 * 批量删除固定资产借用
	 * @Title: deletePpeBorrowRegList 
	 * @Description: TODO
	 * @param @param ids
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月15日 下午4:48:50
	 * @throws
	 */
	boolean deletePpeBorrowRegList(String ids);
	
	
	
	
	
}
