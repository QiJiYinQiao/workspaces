package com.oasys.service;

import java.util.List;

import com.oasys.model.PpeScrapAppAttach;

/**   
 * @Title: PPEScrapAppAttachService
 * @Package com.oasys.service
 * @Description: 固定资产报废附加表Service
 * @author lida  
 * @date 2015/9/21
 * @version V1.0   
 */
public interface PPEScrapAppAttachService {
	
	/** 
	 * @Title: saveOrUpdatePpeEntity
	 * @Description: 新增固定资产报废附加信息
	 * @param ppeAttach 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/9/21
	 */
	void saveOrUpdatePpeEntity(PpeScrapAppAttach ppeAttach);
	
	/** 
	 * @Title: delPpeScrapAttach
	 * @Description: 根据ID删除固定资产报废附加表信息 返回受影响行数
	 * @param ids 附加表id 支持多个一次删除
	 * @author lida
	 * @return Integer
	 * @date 2015/9/22
	 */
	Integer delPpeScrapAttach(String ids);
	
	/** 
	 * @Title: getPpeScrapAttachByID
	 * @Description: 根据ID加载固定资产报废记录
	 * @param id id标识
	 * @author lida
	 * @return PpeScrapAppAttach
	 * @date 2015/9/22
	 */
	PpeScrapAppAttach getPpeScrapAttachByID(Integer id);
	
	/** 
	 * @Title: findPpeAttachList
	 * @Description: 根据appNo加载固定资产报废附加信息
	 * @param String appNo 申请编号
	 * @author lida
	 * @return PpeScrapAppAttach
	 * @date 2015/9/23
	 */
	List<PpeScrapAppAttach> findPpeAttachList(String appNo);
	
}
