package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.CredentialsApp;
import com.oasys.model.CredentialsAppAttach;
import com.oasys.service.CredentialsAppAttachService;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.CredentialsAppModel;

@Service("credentialsAppAttachService")
public class CredentialsAppAttachServiceImpl implements
		CredentialsAppAttachService {
	@Autowired
	private PublicDao<CredentialsAppAttach> publicDao;
	@Autowired
	private PublicDao<CredentialsApp> publicDaoCredentialsApp;
	
	@Override
	public boolean saveCredentialsAppAttach(
			CredentialsAppAttach credentialsAppAttach) {
		boolean flag=false;
		try {
			publicDao.saveOrUpdate(credentialsAppAttach);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<CredentialsAppModel> getList(String appNo,PageUtil pageUtil) {
		StringBuffer stringBuffer = new StringBuffer("SELECT a.APP_NO '编号',");
		stringBuffer.append("CASE a.CS_TYPE WHEN 1 THEN '财务章' WHEN 2 THEN '法人章' WHEN 3 THEN '法人身份证' WHEN 4 THEN '资质' WHEN 5 THEN '合同' WHEN 6 THEN '其他' END,");
		stringBuffer.append("a.CS_DESC '证章描述',CASE a.IS_ORIGINAL WHEN 0 THEN '是' ELSE '否' END '是否原件',a.PLAN_USE_DATE '计划使用日期',a.REAL_USE_DATE '实际使用日期',");
		stringBuffer.append("a.CS_PURPOSE '证章用途',CASE a.IS_LETOUT WHEN 0 THEN '是' ELSE '否' END '是否外借',CASE a.IS_RESTORED WHEN 0 THEN '是' ELSE '否' END '是否已返还',a.PLAN_REST_DATE '计划归还时间',");
		stringBuffer.append("a.REAL_REST_DATE '实际归还时间',a.CS_TYPE '证章类型',a.IS_ORIGINAL '是否原件值',a.IS_LETOUT '是否借出值',a.UQA_ID 'Id',c.UQA_ID 'cId'");
		stringBuffer.append(" FROM t_oa_fd_credentials_stamp_app_attach a,t_oa_fd_credentials_stamp_app c");
		stringBuffer.append(" WHERE c.APP_NO = a.APP_NO AND a.APP_NO='"+appNo+"'");
		List<Object> list = publicDao.findBySql(stringBuffer.toString(), pageUtil);
		List<CredentialsAppModel> list2 = new ArrayList<CredentialsAppModel>();
		for (int i = 0; i < list.size(); i++) {
			CredentialsAppModel credentialsAppAttachModel = new CredentialsAppModel();
			Object[] item = (Object[]) list.get(i);
			credentialsAppAttachModel.setAppNo(item[0]+"");
			credentialsAppAttachModel.setCsTypeName(item[1]==null?"":item[1]+"");
			credentialsAppAttachModel.setCsDesc(item[2]==null?"":item[2]+"");
			credentialsAppAttachModel.setIsOriginalName(item[3]==null?"":item[3]+"");
			credentialsAppAttachModel.setPlanUseDate(DateUtils.parse(item[4]+"",DateUtils.DATE_SMALL_STR));
			credentialsAppAttachModel.setRealUseDateStr(item[5]==null?"":item[5]+"");
			credentialsAppAttachModel.setCsPurpose(item[6]==null?"":item[6]+"");
			credentialsAppAttachModel.setIsLetOutName(item[7]==null?"":item[7]+"");
			credentialsAppAttachModel.setIsRestoredName(item[8]==null?"":item[8]+"");
			credentialsAppAttachModel.setPlanRestDate(DateUtils.parse(item[9]+"",DateUtils.DATE_SMALL_STR));
			credentialsAppAttachModel.setRealRestDateStr(item[10]==null?"":item[10]+"");
			credentialsAppAttachModel.setCsType(Integer.parseInt(item[11]+""));
			credentialsAppAttachModel.setIsOriginal(Integer.parseInt(item[12]+""));
			credentialsAppAttachModel.setIsLetOut(Integer.parseInt(item[13]+""));
			credentialsAppAttachModel.setAuqaId(Integer.parseInt(item[14]+""));
			credentialsAppAttachModel.setUqaId(Integer.parseInt(item[15]+""));
			list2.add(credentialsAppAttachModel);
		}
		return list2;
	}

	@Override
	public Long getCount(String appNo) {
		Long count=publicDao.findTotalCount("SELECT COUNT(1) FROM t_oa_fd_credentials_stamp_app_attach WHERE APP_NO = '"+appNo+"'");
		return count;
	}

	@Override
	public boolean delCredentialsAppAttach(String ids) {
		boolean flag=false;
		try {
			publicDao.executeHql("delete CredentialsAppAttach where uqaId in ("+ids+")");
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delCredentialsAppAttachByAppNo(Integer id) {
		boolean flag=false;
		try {
			publicDao.executeHql("delete CredentialsAppAttach where appNo = '"+publicDaoCredentialsApp.get(CredentialsApp.class, id).getAppNo()+"'");
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
