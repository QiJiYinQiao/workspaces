package com.oasys.service;

import java.text.ParseException;
import java.util.List;

import com.oasys.model.PpeReg;
import com.oasys.util.PageUtil;

/**   
 * @Title: PpeRegService
 * @Package com.oasys.service
 * @Description: 固定资产登记Service
 * @author lida  
 * @date 2015/9/21
 * @version V1.0   
 */
public interface PpeRegService {
	
	/** 
	 * @Title: saveOrUpdatePpeRegEntity
	 * @Description: 新增固定资产登记信息
	 * @param ppeAttach 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/9/21
	 */
	void saveOrUpdatePpeRegEntity(PpeReg exp);
	
	/** 
	 * @Title: delPpeReg
	 * @Description: 根据ID删除固定资产登记信息 返回受影响行数
	 * @param ids 附加表id 支持多个一次删除
	 * @author lida
	 * @return Integer
	 * @date 2015/9/22
	 */
	Integer delPpeReg(String ids);
	
	/** 
	 * @Title: getPpeRegByID
	 * @Description: 根据ID加载固定资产登记记录
	 * @param id id标识
	 * @author lida
	 * @return PpeReg
	 * @date 2015/9/22
	 */
	PpeReg getPpeRegByID(Integer id);
	
	/** 
	 * @Title: findPpeRegList
	 * @Description: 根据实体对象加载固定资产登记信息
	 * @param PpeReg exp 查询实体对象
	 * @param PageUtil page 分页实体对象
	 * @author lida
	 * @return List<PpeReg>
	 * @date 2015/9/23
	 */
	List<PpeReg> findPpeRegList(PpeReg ppeReg,PageUtil page)  throws ParseException;
	
	/** 
	 * @Title: findPpeRegCount
	 * @Description: 根据实体对象加载固定资产登记信息记录数
	 * @param PpeReg exp 查询实体对象
	 * @author lida
	 * @return PpeReg
	 * @date 2015/9/23
	 */
	Long findPpeRegCount(PpeReg ppeReg)  throws ParseException;
	
	
}
