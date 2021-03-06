package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.PenaltyNoticeReg;
import com.oasys.service.OrganizationService;
import com.oasys.service.PenaltyNoticeRegService;
import com.oasys.service.UserService;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.PenaltyNoticeRegModel;

@Service("penaltyNoticeReg")
public class PenaltyNoticeRegServiceImpl implements PenaltyNoticeRegService {
	@Autowired
	private PublicDao<PenaltyNoticeRegModel> publicDao;
	@Autowired
	private PublicDao<PenaltyNoticeReg> publicDaoPen;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	
	//展示数据
	@Override
	public List<PenaltyNoticeRegModel> getList(Map<String, Object> map,
			PageUtil pageUtil) {
		StringBuffer stringBuffer = new StringBuffer("SELECT r.PNR_ID '编号',u.USER_NAME '登记人',r.REG_DATETIME '登记日期',r.PT_NO '受罚对象编号',IFNULL(r.PT_TYPE,'') '受罚对象类型',r.PENALTY_DATE '受罚日期',IFNULL(r.PENALTY_RESON,'') '受罚原因',r.PENALTY_RESON_OTHER '其他受罚原因',r.PENALTY_AMT '罚款金额',o.FULL_NAME '部门名称',r.REMARK '备注信息',"
				+ "USER_ID '用户编号',o.ORGANIZATION_ID '部门编号',u.USER_NAME '用户名称',r.SIGN_DEPT_NO '签发编号'");
		stringBuffer.append(" FROM t_oa_ad_penalty_notice_reg r LEFT JOIN qqms.t_users u ON u.USER_ID = r.REGISTRANT_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = r.SIGN_DEPT_NO WHERE 1=1");
		if(StringUtils.isNotBlank(map.get("beginTime")+"") && map.get("beginTime")!=null){
			stringBuffer.append(" AND r.PENALTY_DATE>='"+map.get("beginTime")+"'");
		}
		if(StringUtils.isNotBlank(map.get("endTime")+"") && map.get("endTime")!=null){
			stringBuffer.append(" AND r.PENALTY_DATE<='"+map.get("endTime")+"'");
		}
		if(StringUtils.isNotBlank(map.get("fineInfo")+"") && map.get("fineInfo")!=null){
			stringBuffer.append(" AND r.PENALTY_RESON = "+map.get("fineInfo")+"");
		}
		if(StringUtils.isNotBlank(map.get("fineType")+"") && map.get("fineType")!=null){
			stringBuffer.append(" AND r.PT_TYPE = "+map.get("fineType")+"");
		}
		if(StringUtils.isNotBlank(map.get("fineUserDept")+"") && map.get("fineUserDept")!=null){
			stringBuffer.append(" AND r.PT_NO = "+map.get("fineUserDept")+"");
		}
		stringBuffer.append(" ORDER BY r.REG_DATETIME DESC");
		List<Object[]> list = publicDao.findBySQL(stringBuffer.toString());
		List<PenaltyNoticeRegModel> list2 = new ArrayList<PenaltyNoticeRegModel>();
		for (int i = 0; i < list.size(); i++) {
			Object[] object = list.get(i);
			PenaltyNoticeRegModel pnm = new PenaltyNoticeRegModel();
			pnm.setPnrId(Integer.parseInt(object[0]+""));
			pnm.setRegistrant(object[1]+"");
			pnm.setRegDateTime(DateUtils.parse(object[2]+""));
			pnm.setPtTypeName(object[4]+"");//罚款类型编号
			pnm.setPtType("1".equals(object[4]+"")?"受罚部门":"受罚人");//罚款类型
			if("1".equals(object[4])){//罚款部门
				pnm.setPt(organizationService.getOrgNameByID(Integer.parseInt(object[3]+"")));
				pnm.setPtName(object[3]+"");//罚款部门编号
				pnm.setOrganizationId(object[14]+"");//部门编号
			}else{
				pnm.setPt(userService.getUserByID(Integer.parseInt(object[3]+"")).getName());//受罚人
				pnm.setPtName(object[3]+"");//受罚人编号11
				pnm.setUserId(Integer.parseInt(object[11]+""));//用户编号
				pnm.setOrganizationId(object[14]+"");//用户编号11
			}
			pnm.setSignDept(object[9]+"");//部门名称
			pnm.setPenaltyDate(DateUtils.parse((object[5]+""), DateUtils.DATE_SMALL_STR));
			pnm.setPenaltyResonName(object[6]+"");//罚款编号
			pnm.setPenaltyReson("1".equals(object[6]+"")?"未带工牌":"2".equals(object[6]+"")?"未按公司要求着装":"3".equals(object[6]+"")?"办公桌摆放凌乱":"4".equals(object[6]+"")?"公共区吸烟":"5".equals(object[6]+"")?"上班时间看电影、睡觉":"其他");
			pnm.setPenaltyResonOther(object[7]==null?"":object[7]+"");
			pnm.setPenaltyAMT(Double.parseDouble(object[8]+""));
			pnm.setRemark(object[10]+"");
			list2.add(pnm);
		}
		return list2;
	}

	//统计总条数
	@Override
	public Long getCount(Map<String, Object> map) {
		StringBuffer stringBuffer = new StringBuffer("SELECT COUNT(1)");
		stringBuffer.append(" FROM t_oa_ad_penalty_notice_reg r LEFT JOIN qqms.t_users u ON u.USER_ID = r.REGISTRANT_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = r.SIGN_DEPT_NO");
		Long count=publicDao.findTotalCount(stringBuffer.toString());
		return count;
	}

	//删除罚款记录
	@Override
	public boolean delFineInfo(String id) {
		int count=publicDao.executeHql("delete PenaltyNoticeReg where pnrId in("+id+")");
		if(count>=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean saveFine(PenaltyNoticeReg penaltyNoticeReg) {
		boolean flag=false;
		try {
			publicDaoPen.saveOrUpdate(penaltyNoticeReg);
			flag =true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
