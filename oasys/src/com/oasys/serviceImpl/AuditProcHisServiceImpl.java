package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.AuditProcHis;
import com.oasys.model.StatusNameRef;
import com.oasys.model.VO.AuditProcHisModel;
import com.oasys.service.AuditProcHisService;
import com.oasys.util.Collections;
/**
 * 审批流程履历表ServiceImpl
 * @ClassName: AuditProcHisServiceImpl 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月24日 下午1:17:16
 */
@Service(value="auditProcHisService")
public class AuditProcHisServiceImpl implements AuditProcHisService{

	@Autowired
	public PublicDao<AuditProcHis> publicDao;
	@Autowired
	public PublicDao<StatusNameRef> publicDaoRef;
	
	@Override
	public boolean saveAuditProcHis(AuditProcHis auditProcHis) {
		try {
			publicDao.saveOrUpdate(auditProcHis);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AuditProcHisModel> findAllAuditProcHisList(String appNo) {
		// TODO Auto-generated method stub
		List<AuditProcHisModel> hisList  =new ArrayList<AuditProcHisModel>();
		// 定义返回值
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT t1.APP_NO,t2.APP_STATUS_NAME,t3.USER_NAME as '用户名',t4.`NAME` as '角色名称',t1.HANDLE_DATE,t5.DICT_NAME,t1.REMARK,t1.`HANDLER` AS '处理人' ");
		sb.append(" FROM ");
		sb.append(" t_oa_audit_proc_his t1 ");
		sb.append(" LEFT JOIN t_oa_app_status_name_ref t2 ON t1.APP_STATUS = t2.REF_ID ");
		sb.append(" LEFT JOIN qqms.t_users t3 ON t1.`HANDLER` = t3.USER_ID ");
		sb.append(" LEFT JOIN qqms.t_role t4 ON t1.HANDLER_ROLE = t4.ROLE_CODE ");
		sb.append(" LEFT JOIN ( ");
		sb.append(" SELECT DICT_CODE,DICT_NAME ");
		sb.append(" FROM ");
		sb.append(" qqms.t_sys_dict ");
		sb.append(" WHERE ");
		sb.append(" PARENT_ID IN ( ");
		sb.append(" SELECT ");
		sb.append(" CODE_ID ");
		sb.append(" FROM ");
		sb.append(" qqms.t_sys_dict ");
		sb.append(" WHERE ");
		sb.append(" DICT_CODE = 'handle_result' ");
		sb.append(" ) ");
		sb.append(" ) t5 ON t1.HANDLE_RESULT = t5.DICT_CODE ");
		sb.append(" WHERE t1.APP_NO = '"+appNo+"'");
		List<Object> list = publicDao.findBySQL(sb.toString());
		if (Collections.listIsNotEmpty(list)) {
			for (Object l : list) {
				Object[] obj = (Object[]) l;
				AuditProcHisModel model = new AuditProcHisModel();
				model.setAppNO(obj[0]!=null?String.valueOf(obj[0]):"");
				model.setAppStatus(obj[1]!=null?String.valueOf(obj[1]):"");
				model.setHandlerName(obj[2]!=null?String.valueOf(obj[2]):"");
				model.setRoleName(obj[3]!=null?String.valueOf(obj[3]):"");
				model.setHandleDate(obj[4]!=null?String.valueOf(obj[4]):"");
				model.setHandleResult(obj[5]!=null?String.valueOf(obj[5]):"");
				model.setRemark(obj[6]!=null?String.valueOf(obj[6]):"");
				model.setHandler(obj[7]!=null?(Integer)obj[7]:null);
				hisList.add(model);
			}
		}
		return hisList;
	}

	@Override
	public StatusNameRef getModelByID(int id) {
		// TODO Auto-generated method stub
		return publicDaoRef.get(StatusNameRef.class, id);
	}

}
