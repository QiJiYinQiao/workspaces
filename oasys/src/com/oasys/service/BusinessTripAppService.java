package com.oasys.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.BusinessTripApp;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;

public interface BusinessTripAppService {

	List<BusinessTripApp> findBusinessTripAppList(PageUtil pageUtil,
			BusinessTripApp businessTripApp);

	Long findBusinessTripAppCount(BusinessTripApp businessTripApp);

	boolean saveBusinessTripApp(BusinessTripApp businessTripApp);

	void delBusinessTripApp(String appNo);

	void updateBusinessTripAppProceStatus(Integer id, String string);

	List<BusinessTripApp> findBusinessTripAppTask(PageUtil pageUtil,
			BusinessTripApp businessTripApp);

	Long findBusinessTripAppTaskCount(BusinessTripApp businessTripApp);

	String saveStartProcess(BusinessTripApp businessTripApp);

	String getProcessKey(Integer btaId);

	void updateBusinessTripAppStatus(Integer id, String state);

	void getDiagramResourceByPaId(HttpServletResponse httpServletResponse,
			Integer btaId);

	String saveTask(WorkFlowTasksModel taskModel);

	BigDecimal sumDays(String endTime, String beginTime);

}
