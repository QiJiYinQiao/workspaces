package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.PenaltyNoticeSubmitApp;
import com.oasys.model.PenaltyNoticeSubmitAppAttach;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;

public interface PenaltyNoticeSubmitAppService {

	List<PenaltyNoticeSubmitAppAttach> findPenaltyNoticeSubmitAppList(
			PageUtil pageUtil, PenaltyNoticeSubmitApp penaltyNoticeSubmitApp);

	Long findPenaltyNoticeSubmitAppCount(
			PenaltyNoticeSubmitApp penaltyNoticeSubmitApp);

	boolean savePenaltyNoticeSubmitApp(
			PenaltyNoticeSubmitApp penaltyNoticeSubmitApp);

	void delPenaltyNoticeSubmitApp(String appNo);

	String saveStartProcess(PenaltyNoticeSubmitApp penaltyNoticeSubmitApp);

	void updatePenaltyNoticeSubmitAppStatus(Integer id, String state);

	void updatePenaltyNoticeSubmitAppProceStatus(Integer id, String status);

	List<PenaltyNoticeSubmitAppAttach> findPenaltyNoticeSubmitAppTask(
			PageUtil pageUtil, PenaltyNoticeSubmitApp penaltyNoticeSubmitApp);

	Long findPenaltyNoticeSubmitAppTaskCount(
			PenaltyNoticeSubmitApp penaltyNoticeSubmitApp);

	String saveTask(WorkFlowTasksModel taskModel);

	String saveSubmitTaskBatch(WorkFlowTasksModel taskModel);

	void getDiagramResourceByPaId(HttpServletResponse httpServletResponse,
			Integer pnrId);


}
