package com.oasys.service;

import java.util.List;

import com.oasys.model.PpeTurnoverAttach;
import com.oasys.viewModel.PurchaseAppAttachModel;

/**   
 * @Title: PpeTurnoverAppAttachService.java 
 * @Package com.oasys.service 
 * @Description: TODO
 * @author PANCHUANHE  
 * @date 2015年9月24日 上午11:08:48 
 * @version V1.0   
 */

public interface PpeTurnoverAppAttachService {

	boolean savePurchaseAppAttach(PpeTurnoverAttach ppeTurnoverAttach);

	boolean doDeleteById(PpeTurnoverAttach turnoverAttach);

	List<PpeTurnoverAttach> findListByAppNo(String appNo);

	boolean doDeleteByAppNo(PpeTurnoverAttach turnoverAttach);

	List<PpeTurnoverAttach> findPpeAttachList(String appNo);

    void totalCount(String appNo);

	PpeTurnoverAttach findPpeAttachList(String appNo, String ppeCode);
}


