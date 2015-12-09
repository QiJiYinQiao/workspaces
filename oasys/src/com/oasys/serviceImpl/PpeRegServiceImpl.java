package com.oasys.serviceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.PpeReg;
import com.oasys.service.OrganizationService;
import com.oasys.service.PpeRegService;
import com.oasys.service.UserService;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;

@Service("ppeRegService")
public class PpeRegServiceImpl implements PpeRegService {

	@Autowired
	PublicDao<PpeReg> publicDao;
	@Autowired
	UserService userService;
	@Autowired
	OrganizationService orgService;
	
	@Override
	public void saveOrUpdatePpeRegEntity(PpeReg ppe) {
		publicDao.saveOrUpdate(ppe);
	}

	@Override
	public Integer delPpeReg(String ids) {
		return publicDao.executeHql("delete from PpeReg where prId in ("+ids+")");
	}

	@Override
	public PpeReg getPpeRegByID(Integer id) {
		return publicDao.get(PpeReg.class, id);
	}

	@Override
	public List<PpeReg> findPpeRegList(PpeReg ppe,PageUtil page) throws ParseException {
		return getPpeRegList(getFindSQL(ppe,0),page);
	}


	//获取固定资产报废HQL通用语句方法
	private String getFindSQL(PpeReg ppeReg,Integer isCount){
		String hql ="";
		if(isCount == 0){
				hql+="SELECT PR_ID,"
				  +"PPE_REG.REGISTRANT_NO,"
				  +"PPE_REG.REG_DATETIME,"
				  +"PPE_REG.PPE_NO,"
				  +"PPE_REG.PPE_NAME,"
				  +"PPE_REG.PPE_MODEL,"
				  +"PPE_REG.QTY,"
				  +"PPE_REG.PRICE,"
				  +"PPE_REG.USE_DEPT,"
				  +"PPE_REG.USER,"
				  +"PPE_REG.REMARK,"
				  +"USERS.LOGIN_ACT USER_NAME,"
				  +"USE_USER.LOGIN_ACT USE_USER_NAME,"
				  +"ORG.FULL_NAME USE_ORG_NAME ";
		}else{
			hql+="SELECT COUNT(*) ";
		}
		hql +=" FROM OASYS.T_OA_AD_PPE_REG PPE_REG" 
				  +" LEFT JOIN QQMS.T_USERS USERS "
				  +" ON PPE_REG.REGISTRANT_NO = USERS.USER_ID" 
				  +" LEFT JOIN QQMS.T_USERS USE_USER "
				  +" ON PPE_REG.USER = USE_USER.USER_ID" 
				  +" LEFT JOIN QQMS.T_ORGANIZATION ORG" 
				  +" ON ORG.ORGANIZATION_ID = PPE_REG.USE_DEPT"
				  + " WHERE 1=1 AND REGISTRANT_NO="+Constants.getCurrendUser().getUserId();
		//判断办理任务页面 根据id加载列表 则不添加申请人查询条件
		if(StringUtils.isNotBlank(ppeReg.getRegDatetimeBegin())){
			hql +=" AND  STR_TO_DATE(PPE_REG.REG_DATETIME,'%Y-%m-%d')>='"+ppeReg.getRegDatetimeBegin()+"'";
		}
		if(StringUtils.isNotBlank(ppeReg.getRegDatetimeEnd())){
			hql +=" AND  STR_TO_DATE(PPE_REG.REG_DATETIME,'%Y-%m-%d')<='"+ppeReg.getRegDatetimeEnd()+"'";
		}
		if(StringUtils.isNotBlank(ppeReg.getPpeName())){
			hql +=" AND PPE_REG.PPE_NAME like '%"+ppeReg.getPpeName()+"%'";
		}
		if(StringUtils.isNotBlank(ppeReg.getPpeModel())){
			hql +=" AND PPE_REG.PPE_MODEL like '%"+ppeReg.getPpeModel()+"%'";
		}
		hql += "  ORDER BY REG_DATETIME DESC";
		return hql;
	}
	
	//通用填充实体对象方法
	private List<PpeReg> getPpeRegList(String hql,PageUtil pageUtil) throws ParseException{
		List<Object> list = publicDao.findBySql(hql, pageUtil);
		List<PpeReg> ppeRegList = new ArrayList<PpeReg>();
		if(Collections.listIsNotEmpty(list)){
			PpeReg ppeReg = new PpeReg();
			for (int i = 0; i < list.size(); i++) {
				ppeReg = new PpeReg();
				Object[] obj = (Object[]) list.get(i);
				ppeReg.setPrId(obj[0] == null?0:(Integer)obj[0]);
				ppeReg.setRegistrantNo(obj[1] == null?0:(Integer)obj[1]);
				ppeReg.setRegDatetime(obj[2] == null?"":String.valueOf(obj[2]));
				ppeReg.setPpeNo(obj[3] == null?"":String.valueOf(obj[3]));
				ppeReg.setPpeName(obj[4] == null?"":String.valueOf(obj[4]));
				ppeReg.setPpeModel(obj[5] == null?"":String.valueOf(obj[5]));
				ppeReg.setQty(obj[6] == null?0:(Integer)obj[6]);
				ppeReg.setPrice(obj[7] == null?new BigDecimal(0):new BigDecimal(String.valueOf(obj[7])));
				ppeReg.setUseDept(obj[8] == null?0:(Integer)obj[8]);
				ppeReg.setUser(obj[9] == null?0:(Integer)obj[9]);
				ppeReg.setRemark(obj[10] == null?"":String.valueOf(obj[10]));
				ppeReg.setUserName(obj[11] == null?"":String.valueOf(obj[11]));
				ppeReg.setPpeUserName(obj[12] == null?"":String.valueOf(obj[12]));
				ppeReg.setPpeOrgName(obj[13] == null?"":String.valueOf(obj[13]));
				ppeRegList.add(ppeReg);
			}
		}
		return ppeRegList;
	}

	@Override
	public Long findPpeRegCount(PpeReg ppeReg) throws ParseException {
		return publicDao.findTotalCount(getFindSQL(ppeReg, 1));
	}
	
	
}
