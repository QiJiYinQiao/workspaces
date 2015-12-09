package com.oasys.service;

import java.util.List;

import com.oasys.model.ConsumablesAppAttach;
import com.oasys.model.PpeScrapAppAttach;

/**   
 * @Title: ConsumablesAppAttachService.java 
 * @Package com.oasys.service 
 * @Description: TODO
 * @author PANCHUANHE  
 * @date 2015年10月12日 上午9:16:46 
 * @version V1.0   
 */

public interface ConsumablesAppAttachService {

	List<ConsumablesAppAttach> findConsumablesAppAttachList(String appNo);

	void saveOrUpdateConsumablesAppAttach(
			ConsumablesAppAttach consumablesAppAttach);

	ConsumablesAppAttach getConsumablesAppAttachByID(Integer caaId);

	Integer delConsumablesAppAttach(String ids, String appNo);

}


