package com.oasys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.EmpDimissionTakeoverInfo;
import com.oasys.model.PenaltyNoticeSubmitAppAttach;
import com.oasys.service.OrganizationService;
import com.oasys.service.PenaltyNoticeSubmitAppAttachService;
import com.oasys.service.UserService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
@Service(value="penaltyNoticeSubmitAppAttachService")
public class PenaltyNoticeSubmitAppAttachServiceImpl extends
		WorkFlowBaseServiceImpl implements PenaltyNoticeSubmitAppAttachService {
	@Autowired
	private PublicDao<PenaltyNoticeSubmitAppAttach> publicDao;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService orgService;
	@Override
	public List<PenaltyNoticeSubmitAppAttach> findPenaltyNoticeSubmitAppAttachList(
			String appNo) {
		// TODO Auto-generated method stub
		String hql = "from PenaltyNoticeSubmitAppAttach where appNo = '"+appNo+"'";
		List<PenaltyNoticeSubmitAppAttach> list = publicDao.find(hql);
		return list;
	}
	@Override
	public void savePenaltyNoticeSubmitAppAttach(
			PenaltyNoticeSubmitAppAttach penaltyNoticeSubmitAppAttach) {
		// TODO Auto-generated method stub
		publicDao.saveOrUpdate(penaltyNoticeSubmitAppAttach);
	}
	@Override
	public List<PenaltyNoticeSubmitAppAttach> findPenaltyNoticeSubmitAppAttachList2(
			String appNo) {
		String hql = "from PenaltyNoticeSubmitAppAttach where appNo = '"+appNo+"'";
		List<PenaltyNoticeSubmitAppAttach> list = publicDao.find(hql);
		for (PenaltyNoticeSubmitAppAttach penaltyNoticeSubmitAppAttach : list) {
			penaltyNoticeSubmitAppAttach.setPtName(userService.getUserByID(penaltyNoticeSubmitAppAttach.getPtNo()).getName());
			penaltyNoticeSubmitAppAttach.setPtDeptName(orgService.getOrgNameByID(penaltyNoticeSubmitAppAttach.getPtDept()));
			penaltyNoticeSubmitAppAttach.setSignDeptName(orgService.getOrgNameByID(penaltyNoticeSubmitAppAttach.getSignDeptNo()));
		}
		return list;
	}
	@Override
	public void doDeleteById(
			PenaltyNoticeSubmitAppAttach penaltyNoticeSubmitAppAttach) {
		// TODO Auto-generated method stub
		publicDao.delete(penaltyNoticeSubmitAppAttach);
	}

}
