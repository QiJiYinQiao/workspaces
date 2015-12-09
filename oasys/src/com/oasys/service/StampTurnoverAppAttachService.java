package com.oasys.service;

import java.util.List;

import com.oasys.model.StampTurnoverAppAttach;

/**   
 * @Title: PPEScrapAppAttachService
 * @Package com.oasys.service
 * @Description: 印章移交附加表Service
 * @author lida  
 * @date 2015/9/21
 * @version V1.0   
 */
public interface StampTurnoverAppAttachService {
	
	/** 
	 * @Title: saveOrUpdatePpeEntity
	 * @Description: 新增印章移交附加信息
	 * @param ppeAttach 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/9/21
	 */
	void saveOrUpdatePpeEntity(StampTurnoverAppAttach ppeAttach);
	
	/** 
	 * @Title: delStampTurnoverAttach
	 * @Description: 根据ID删除印章移交附加表信息 返回受影响行数
	 * @param ids 附加表id 支持多个一次删除
	 * @author lida
	 * @return Integer
	 * @date 2015/9/22
	 */
	Integer delStampTurnoverAttach(String ids);
	
	/** 
	 * @Title: getStampTurnoverAttachByID
	 * @Description: 根据ID加载印章移交记录
	 * @param id id标识
	 * @author lida
	 * @return StampTurnoverAppAttach
	 * @date 2015/9/22
	 */
	StampTurnoverAppAttach getStampTurnoverAttachByID(Integer id);
	
	/** 
	 * @Title: findPpeAttachList
	 * @Description: 根据appNo加载印章移交附加信息
	 * @param String appNo 申请编号
	 * @author lida
	 * @return StampTurnoverAppAttach
	 * @date 2015/9/23
	 */
	List<StampTurnoverAppAttach> findPpeAttachList(String appNo);
	
}
