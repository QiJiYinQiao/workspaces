package com.oasys.service;

import java.util.List;

import com.oasys.model.PurchaseAppAttach;
import com.oasys.viewModel.PurchaseAppAttachModel;

/**
 * @ClassName: PurchaseAppAttachService 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月16日 下午3:18:55
 */
public interface PurchaseAppAttachService {

	/**
	 * @Title: savePurchaseAppAttach 
	 * @Description: TODO 新增
	 * @param @param purchaseAppAttach
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年9月16日 下午3:20:03
	 * @throws
	 */
	boolean savePurchaseAppAttach(PurchaseAppAttach purchaseAppAttach);
	/**
	 * 删除
	 * @Title: delPurchaseAppAttach 
	 * @Description: TODO 
	 * @param @param paId
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年9月18日 上午11:38:08
	 * @throws
	 */
	boolean delPurchaseAppAttachByPsaId(String ids);
	/**
	 * 根据id查询单个对象
	 * @Title: findPurchaseAppAttachBypsaId 
	 * @Description: TODO
	 * @param @param psaId
	 * @param @return
	 * @author PANCHUANHE
	 * @return PurchaseAppAttach
	 * @date 2015年9月18日 下午4:29:08
	 * @throws
	 */
	PurchaseAppAttach findPurchaseAppAttachBypsaId(Integer psaId);
	/**
	 * 根据申请编号查询PurchaseAppAttach的集合
	 * @Title: findPurchaseAppAttachList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<PurchaseAppAttach>
	 * @date 2015年9月21日 上午10:57:24
	 * @throws
	 */
	List<PurchaseAppAttachModel> findPurchaseAppAttachList(String appNo);
	/**
	 * 根据appNO删除
	 * @Title: findPurchaseAppAttachList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<PurchaseAppAttach>
	 * @date 2015年9月21日 上午10:57:24
	 * @throws
	 */
	boolean delPurchaseAppAttachByAppNo(String appNo);
}
