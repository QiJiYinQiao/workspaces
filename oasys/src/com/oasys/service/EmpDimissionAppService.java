package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.EmpDimissionApp;
import com.oasys.model.EmpDimissionTakeoverInfo;
import com.oasys.model.StatusNameRef;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;

public interface EmpDimissionAppService {

	List<EmpDimissionTakeoverInfo> findEmpDimissionTakeoverList(PageUtil pageUtil,
			EmpDimissionApp empDimissionApp);

	Long findDimissionAppCount(EmpDimissionApp empDimissionApp);

	boolean saveEmpDimissionApp(EmpDimissionApp empDimissionApp);

	void delEmpDimissionApp(String appNo);

	void updateEmpDimissionAppStatus(Integer id, String status);

	void updateEmpDimissionAppProceStatus(Integer id, String status);
	
	boolean findZongBuOrFenBu(EmpDimissionApp empDimissionApp);

	EmpDimissionApp getEmpDimissionAppByID(Integer id);

	StatusNameRef findAppStatusByID(String appStatus);

	void getDiagramResourceByPaId(HttpServletResponse httpServletResponse,
			Integer edaId);

	List<EmpDimissionTakeoverInfo> findEmpDimissionTask(PageUtil pageUtil,
			EmpDimissionApp empDimissionApp);

	Long findEmpDimissionListTaskCount(EmpDimissionApp empDimissionApp);

	void saveSubmitTask(EmpDimissionApp empDimissionApp);

	String saveStartProcess(EmpDimissionApp empDimissionApp);

	String saveTask(WorkFlowTasksModel taskModel);

	String getProcessKey(Integer edaid);

}
