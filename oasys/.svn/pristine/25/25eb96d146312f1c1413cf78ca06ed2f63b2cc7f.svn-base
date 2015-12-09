package com.oasys.service;


import java.util.List;

import com.oasys.model.PpeBorrowReg;
import com.oasys.model.UseReg;
import com.oasys.util.PageUtil;
/**
 * 用章申请
 * @author Administrator
 *
 */
public interface UseRegService extends WorkFlowBaseService
{	
	/**
	 * 查询固定资产领用登记
	 * @Title: findPpeBorrowRegAttList 
	 * @Description: TODO
	 * @param @param PpeBorrowReg
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<PpeBorrowReg>
	 * @date 2015年10月10日 下午4:05:58
	 * @throws
	 */
	List<UseReg> findUseRegList(UseReg useReg,PageUtil pageUtil);
	
	/**
	 * 查询固定资产领用登记的数量
	 * @Title: getTotal 
	 * @Description: TODO
	 * @param @param useReg
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年10月15日 下午8:41:34
	 * @throws
	 */
	Long getTotal(UseReg useReg);
	
	/**
	 * 保存固定资产领用
	 * @Title: saveOrUpdPpeBorrowReg 
	 * @Description: TODO
	 * @param @param ppeBorrowReg
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月15日 下午2:54:48
	 * @throws
	 */
	boolean saveOrUpdUseReg(UseReg useReg);
	/**
	 * 批量删除固定资产领用
	 * @Title: deletePpeBorrowRegList 
	 * @Description: TODO
	 * @param @param ids
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月15日 下午4:48:50
	 * @throws
	 */
	boolean deleteUseRegList(String ids);
	
	
	
	
	
}
