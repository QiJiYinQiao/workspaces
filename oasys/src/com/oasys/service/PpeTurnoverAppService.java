package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.AuditProcHis;
import com.oasys.model.PpeStockInfo;
import com.oasys.model.PpeTurnoverApp;
import com.oasys.model.PpeTurnoverAttach;
import com.oasys.model.StatusNameRef;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.PpeTurnoverModel;
import com.oasys.viewModel.PurchaseAppModel;
import com.oasys.viewModel.WorkFlowTasksModel;

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

	List<PpeTurnoverAttach> findAllPpeTurnoverAppTaskList(Integer firstResult, Integer maxResults, PpeTurnoverApp ppeTurnoverApp);

	Long countAllPpeTurnoverAppTaskList();

	boolean saveHoldWorkTask(String taskId);


	/*Long countMyWorkTask();*/

	boolean saveSubmitTask(PpeTurnoverApp ppeTurnoverApp, String result,
			AuditProcHis auditProcHis);
	
	void updatePpeTurnoverappStatus(Integer ptaId,String state);

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

	String saveTask(WorkFlowTasksModel taskModel);

	String saveStartProcess(PpeTurnoverApp ppeTurnoverApp);

	PpeStockInfo findPpeStock(PpeStockInfo ppeStockInfo);

	String saveSubmitTaskBatch(WorkFlowTasksModel taskModel);
}


