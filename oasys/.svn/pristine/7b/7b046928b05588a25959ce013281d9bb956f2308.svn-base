package com.oasys.service;

import java.util.List;

import com.oasys.model.ExpressInfoReg;
import com.oasys.model.PPEScrapApp;

/**   
 * @Title: ExpressInfoRegService
 * @Package com.oasys.service
 * @Description: 快递信息登记信息表Service
 * @author lida  
 * @date 2015/9/21
 * @version V1.0   
 */
public interface ExpressInfoRegService {
	
	/** 
	 * @Title: saveOrUpdateExpressInfoRegEntity
	 * @Description: 新增快递信息登记信息信息
	 * @param ppeAttach 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/9/21
	 */
	void saveOrUpdateExpressInfoRegEntity(ExpressInfoReg exp);
	
	/** 
	 * @Title: delExpressInfoReg
	 * @Description: 根据ID删除快递信息登记信息表信息 返回受影响行数
	 * @param ids 附加表id 支持多个一次删除
	 * @author lida
	 * @return Integer
	 * @date 2015/9/22
	 */
	Integer delExpressInfoReg(String ids);
	
	/** 
	 * @Title: getExpressInfoRegByID
	 * @Description: 根据ID加载快递信息登记信息记录
	 * @param id id标识
	 * @author lida
	 * @return ExpressInfoReg
	 * @date 2015/9/22
	 */
	ExpressInfoReg getExpressInfoRegByID(Integer id);
	
	/** 
	 * @Title: findExpressInfoRegList
	 * @Description: 根据appNo加载快递信息登记信息信息
	 * @param String appNo 申请编号
	 * @author lida
	 * @return ExpressInfoReg
	 * @date 2015/9/23
	 */
	List<ExpressInfoReg> findExpressInfoRegList(ExpressInfoReg exp);
	
	
	/** 
	 * @Title: findExpressInfoRegCount
	 * @Description: 查询快递登记信息记录数
	 * @param String appNo 申请编号
	 * @author lida
	 * @return ExpressInfoReg
	 * @date 2015/10/15
	 */
	Long findExpressInfoRegCount(ExpressInfoReg exp);
	
}
