package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.EmpRosterReg;
import com.oasys.model.VO.EmpRosterRegModel;
import com.oasys.service.EmpRosterRegService;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;

@Service("empRosterRegService")
public class EmpRosterRegServiceImpl implements EmpRosterRegService {

	@Autowired
	PublicDao<EmpRosterReg> publicDao;
	@Override
	public boolean saveOrUpdateEmpRosterRegEntity(EmpRosterReg empRosterReg) {
		// TODO Auto-generated method stub
		if(empRosterReg.getErrId()==null ||"".equals(empRosterReg.getErrId())){
			//新增
			empRosterReg.setCreator(Constants.getCurrendUser().getUserId());
			empRosterReg.setCrtDtime(new Date());
			publicDao.save(empRosterReg);
		}else{
			//修改
			empRosterReg.setUpdater(Constants.getCurrendUser().getUserId());
			empRosterReg.setUpdDtime(new Date());
			publicDao.update(empRosterReg);
		}
		return true;
	}
	@Override
	public Integer delEmpRosterReg(String ids) {
		// TODO Auto-generated method stub
		return publicDao.executeHql("delete from EmpRosterReg where errId in ("+ids+")");
	}
	@Override
	public EmpRosterReg getEmpRosterRegByID(Integer id) {
		// TODO Auto-generated method stub
		return publicDao.get(EmpRosterReg.class, id);
	}
	@Override
	public List<EmpRosterRegModel> findEmpRosterRegList(PageUtil pageUtil,EmpRosterRegModel empRosterReg) {
		// TODO Auto-generated method stub
		List<Object> list = publicDao.findBySql(getSql(empRosterReg), pageUtil);
		List<EmpRosterRegModel> regList = new ArrayList<EmpRosterRegModel>();
		if(Collections.listIsNotEmpty(list)){
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				EmpRosterRegModel emp = new EmpRosterRegModel();
				emp.setDeptName(obj[0]!=null?String.valueOf(obj[0]):"");
				emp.setUserId(obj[1]!=null?String.valueOf(obj[1]):"");
				emp.setUserName(obj[2]!=null?String.valueOf(obj[2]):"");
				emp.setMobile(obj[3]!=null?String.valueOf(obj[3]):"");
				emp.setIdCard(obj[4]!=null?String.valueOf(obj[4]):"");
				emp.setIdCardAddr(obj[5]!=null?String.valueOf(obj[5]):"");
				emp.setSalCardNo(obj[6]!=null?String.valueOf(obj[6]):"");
				emp.setDuty(obj[7]!=null?String.valueOf(obj[7]):"");
				emp.setTrialBaseSal(obj[8]!=null?String.valueOf(obj[8]):"");
				emp.setTrialPostSal(obj[9]!=null?String.valueOf(obj[9]):"");
				emp.setTrialPerfSal(obj[10]!=null?String.valueOf(obj[10]):"");
				emp.setRegularBaseSal(obj[11]!=null?String.valueOf(obj[11]):"");
				emp.setRegularPostSal(obj[12]!=null?String.valueOf(obj[12]):"");
				emp.setRegularPerfSal(obj[13]!=null?String.valueOf(obj[13]):"");
				emp.setCurMonthSalCnt(obj[14]!=null?String.valueOf(obj[14]):"");
				emp.setContractNo(obj[15]!=null?String.valueOf(obj[15]):"");
				emp.setContractPeriod(obj[16]!=null?String.valueOf(obj[16]):"");
				emp.setContractBgDate(obj[17]!=null?String.valueOf(obj[17]):"");
				emp.setContractEdDate(obj[18]!=null?String.valueOf(obj[18]):"");
				emp.setEndowmentIns(obj[19]!=null?String.valueOf(obj[19]):"");
				emp.setMedicalIns(obj[20]!=null?String.valueOf(obj[20]):"");
				emp.setUnemploymentIns(obj[21]!=null?String.valueOf(obj[21]):"");
				emp.setWorkInjuryIns(obj[22]!=null?String.valueOf(obj[22]):"");
				emp.setMaternityIns(obj[23]!=null?String.valueOf(obj[23]):"");
				emp.setHousingFund(obj[24]!=null?String.valueOf(obj[24]):"");
				emp.setIsChg(obj[25]!=null?String.valueOf(obj[25]):"");
				emp.setIsRegular(obj[26]!=null?String.valueOf(obj[26]):"");
				emp.setIsSignContract(obj[27]!=null?String.valueOf(obj[27]):"");
				emp.setDeptId(obj[28]!=null?String.valueOf(obj[28]):"");
				emp.setErrId(obj[29]!=null?String.valueOf(obj[29]):"");
				emp.setDutyBgDate(obj[30]!=null?String.valueOf(obj[30]):"");
				emp.setCommendInfo(obj[31]!=null?String.valueOf(obj[31]):"");
				emp.setEntryDate(obj[32]!=null?String.valueOf(obj[32]):"");
				emp.setTrialTlimit(obj[33]!=null?String.valueOf(obj[33]):"");
				emp.setTrialEdDate(obj[34]!=null?String.valueOf(obj[34]):"");
				emp.setRegularDate(obj[35]!=null?String.valueOf(obj[35]):"");
				emp.setIsMakeOverall(obj[36]!=null?String.valueOf(obj[36]):"");
				emp.setOverallsMakeDate(obj[37]!=null?String.valueOf(obj[37]):"");
				emp.setResignDate(obj[38]!=null?String.valueOf(obj[38]):"");
				emp.setResignReason(obj[39]!=null?String.valueOf(obj[39]):"");
				emp.setWorkedDays(obj[40]!=null?String.valueOf(obj[40]):"");
				emp.setCreator(obj[41]!=null?String.valueOf(obj[41]):"");
				emp.setCrtDtime(obj[42]!=null?String.valueOf(obj[42]):"");
				emp.setRemark(obj[43]!=null?String.valueOf(obj[43]):"");
				regList.add(emp);
			}
		}
		return regList;
	}
	@Override
	public Long findEmpRosterRegCount(EmpRosterRegModel empRosterReg) {
		// TODO Auto-generated method stub
		return publicDao.findTotalCount1(getSql(empRosterReg));
	}
	
	private String getSql(EmpRosterRegModel empRosterReg){
		String sql="select "
				+" o.FULL_NAME,u.USER_ID,u.USER_NAME,u.MOBILE,u.ID_CARD,u.ID_CARD_ADDR,u.SAL_CARD_NO,r.DUTY,r.TRIAL_BASE_SAL,r.TRIAL_POST_SAL,r.TRIAL_PERF_SAL,r.REGULAR_BASE_SAL,r.REGULAR_POST_SAL,r.REGULAR_PERF_SAL,r.CUR_MONTH_SAL_CNT,r.CONTRACT_NO,r.CONTRACT_PERIOD,r.CONTRACT_BG_DATE,r.CONTRACT_ED_DATE,r.ENDOWMENT_INS,r.MEDICAL_INS,r.UNEMPLOYMENT_INS,r.WORK_INJURY_INS,r.MATERNITY_INS,r.HOUSING_FUND,IFNULL(r.IS_CHG,''),IFNULL(r.IS_REGULAR,''),IFNULL(r.IS_SIGN_CONTRACT,''),o.ORGANIZATION_ID,r.ERR_ID,r.DUTY_BG_DATE,r.COMMEND_INFO,r.ENTRY_DATE,r.TRIAL_TLIMIT,r.TRIAL_ED_DATE,r.REGULAR_DATE,IFNULL(r.IS_MAKE_OVERALL,''),r.OVERALLS_MAKE_DATE,r.RESIGN_DATE,r.RESIGN_REASON,r.WORKED_DAYS,r.CREATOR,r.CRT_DTIME,r.REMARK "
				+" from  t_oa_hr_emp_roster_reg r "
				+" LEFT JOIN qqms.t_users u ON r.USER_ID = u.USER_ID"
				+" LEFT JOIN qqms.t_organization o ON u.ORGANIZATION_ID = o.ORGANIZATION_ID where 1=1";
		if(StringUtils.isNotBlank(empRosterReg.getDeptId())){
			sql+=" and o.ORGANIZATION_ID= "+empRosterReg.getDeptId();
		}
		if(StringUtils.isNotBlank(empRosterReg.getUserName())){
			sql+=" and u.USER_NAME like '%"+empRosterReg.getUserName()+"%'";
		}
		return sql;
	}
	
}
