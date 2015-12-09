package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.AuditProcHis;
import com.oasys.model.PpeTurnoverApp;
import com.oasys.model.PpeTurnoverAttach;
import com.oasys.model.StatusNameRef;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.PpeTurnoverModel;
import com.oasys.viewModel.PurchaseAppModel;

/**
 *author:yuanzhongqiu
 *Time:2015年9月18日  下午5:16:27
 */
public interface PpeTurnoverAppService {
	List<PpeTurnoverModel> findAllPpeTurnover(PageUtil pageUtil,PpeTurnoverModel ppeTurnoverModel);

	boolean savePpeTurnoverApp(PpeTurnoverApp ppeTurnoverApp);

	Long findAllPpeTurnoverCount();

	boolean delPpeTurnoverByPaId(String ids);

	List<PpeTurnoverApp> findAllPpeTurnover2(PageUtil pageUtil, PpeTurnoverApp ppeTurnoverApp);

	boolean startWorkflow(PpeTurnoverApp ppeTurnoverApp);

	List<PpeTurnoverAttach> findAllPpeTurnoverAppTaskList(Integer firstResult, Integer maxResults);

	Long countAllPpeTurnoverAppTaskList();

	boolean saveHoldWorkTask(String taskId);


	/*Long countMyWorkTask();*/

	boolean saveSubmitTask(PpeTurnoverApp ppeTurnoverApp, String result,
			AuditProcHis auditProcHis);
	
	void updatePurchaseAppStatus(Integer ptaId,String state);

	PpeTurnoverApp findPpeTurnoverAppByID(String ptaId);

	boolean findZongBuOrFenBu(PpeTurnoverApp ppeTurnoverApp);

	List<PpeTurnoverAttach> findPpeList(PageUtil pageUtil,
			PpeTurnoverApp ppeTurnoverApp);

	Long findPpeListCount(PpeTurnoverApp ppeTurnoverApp);
	
	PpeTurnoverApp findPpeTurnoverAppByAppNo(String appNo);

	void updatePpeTurnoverappProceStatus(Integer id, String state);

	void getDiagramResourceByPaId(HttpServletResponse httpServletResponse,
			Integer paId);

	StatusNameRef findAppStatusByID(String appStatus);

	void delPpeTurnover(String appNo);
}


