package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.ExpressInfoReg;
import com.oasys.service.ExpressInfoRegService;
import com.oasys.service.OrganizationService;
import com.oasys.service.UserService;
import com.oasys.util.Collections;
import com.oasys.util.Constants;

@Service("expressInfoRegService")
public class ExpressInfoRegServiceImpl implements ExpressInfoRegService {

	@Autowired
	PublicDao<ExpressInfoReg> publicDao;
	@Autowired
	UserService userService;
	@Autowired
	OrganizationService orgService;
	
	@Override
	public void saveOrUpdateExpressInfoRegEntity(ExpressInfoReg exp) {
		// TODO Auto-generated method stub
		publicDao.saveOrUpdate(exp);
	}

	@Override
	public Integer delExpressInfoReg(String ids) {
		// TODO Auto-generated method stub
		return publicDao.executeHql("delete from ExpressInfoReg where eirId in ("+ids+")");
	}

	@Override
	public ExpressInfoReg getExpressInfoRegByID(Integer id) {
		return publicDao.get(ExpressInfoReg.class, id);
	}

	@Override
	public List<ExpressInfoReg> findExpressInfoRegList(ExpressInfoReg exp) {
		@SuppressWarnings("unchecked")
		List<Object> list = publicDao.findBySQL(getSQL(exp,0));
		List<ExpressInfoReg> regList = new ArrayList<ExpressInfoReg>();
		if(Collections.listIsNotEmpty(list)){
			ExpressInfoReg exReg = new ExpressInfoReg();
			for (int i = 0; i < list.size(); i++) {
				exReg = new ExpressInfoReg();
				Object[] obj = (Object[]) list.get(i);
				exReg.setEirId(obj[0] == null?0:(Integer)obj[0]);
				exReg.setRegistrantNo(obj[1] == null?0:(Integer)obj[1]);
				exReg.setRegDatetime(obj[2] == null?"":String.valueOf(obj[2]));
				exReg.setRegistrantNo(obj[3] == null?0:(Integer)obj[3]);
				exReg.setDeptNo(obj[4] == null?0:(Integer)obj[4]);
				exReg.setNhwrDate(obj[5] == null?"":String.valueOf(obj[5]));
				exReg.setNhwrReson(obj[6] == null?"":String.valueOf(obj[6]));
				exReg.setExpNo(obj[7] == null?"":String.valueOf(obj[7]));
				exReg.setExpAmt(obj[8] == null?"":String.valueOf(obj[8]));
				exReg.setRemark(obj[9] == null?"":String.valueOf(obj[9]));
				exReg.setUserName(obj[10] == null?"":String.valueOf(obj[10]));
				exReg.setAddUserName(obj[11] == null?"":String.valueOf(obj[11]));
				exReg.setAddOrgName(obj[12] == null?"":String.valueOf(obj[12]));
				regList.add(exReg);
			}
		}
		return regList;
	}

	private String getSQL(ExpressInfoReg exp,Integer isCount){
		String sql="";
		if(isCount == 0){
			sql="SELECT REG.EIR_ID,REG.REGISTRANT_NO,REG.REG_DATETIME,REG.ADDRESSEE_NO,REG.DEPT_NO,"
					+ "REG.NHWR_DATE,REG.NHWR_RESON,REG.EXP_NO,REG.EXP_AMT,REG.REMARK,USERS.LOGIN_ACT,ADD_USERS.LOGIN_ACT "
					+ "ADD_ACCOUNT, ORG.FULL_NAME ";
		}else{
			sql="SELECT COUNT(*) ";
		}
		sql+= "FROM OASYS.T_OA_AD_EXPRESS_INFO_REG REG LEFT JOIN QQMS.T_USERS USERS ON "
			+ "REG.REGISTRANT_NO = USERS.USER_ID"
			+" LEFT JOIN QQMS.T_USERS ADD_USERS ON REG.ADDRESSEE_NO = ADD_USERS.USER_ID"
			+" LEFT JOIN QQMS.T_ORGANIZATION ORG ON ORG.ORGANIZATION_ID = REG.DEPT_NO"
			+" WHERE 1=1 AND REG.REGISTRANT_NO="+Constants.getCurrendUser().getUserId();
		if(StringUtils.isNotBlank(exp.getRegDatetimeBegin())){
			sql+=" and REG_DATETIME>='"+exp.getRegDatetimeBegin()+"'";
		}
		if(StringUtils.isNotBlank(exp.getRegDatetimeEnd())){
			sql+=" and REG_DATETIME<='"+exp.getRegDatetimeEnd()+"'";
		}
		sql +=" order by REG_DATETIME desc";
		return sql;
	}

	@Override
	public Long findExpressInfoRegCount(ExpressInfoReg exp) {
		return publicDao.findTotalCount(getSQL(exp,1));
	}
	
}
