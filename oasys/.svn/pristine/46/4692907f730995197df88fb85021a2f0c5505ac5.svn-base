package com.oasys.serviceImpl;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.PpeBorrowReg;
import com.oasys.model.UseReg;
import com.oasys.service.OrganizationService;
import com.oasys.service.UseRegService;
import com.oasys.service.UserService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;

@Service("useRegService")
@SuppressWarnings("unchecked")
public class UseRegServiceImpl  extends WorkFlowBaseServiceImpl  implements UseRegService
{

	
	
	@Autowired
	private PublicDao<UseReg> useRegDao;
	
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	
	@Override
	public List<UseReg> findUseRegList(UseReg useReg,PageUtil pageUtil) {
		try {
			String hql="from UseReg where 1=1 ";
			//判断是否是超级管理人
			if(Constants.getCurrendUser().getUserId()!=1){
				hql+=" and registrantNo="+Constants.getCurrendUser().getUserId();
			}
			
			if(StringUtils.isNotBlank(useReg.getPpeNo())){
				hql+=" and ppeNo='"+useReg.getPpeNo()+"' ";
			}
			if(StringUtils.isNotBlank(useReg.getAppDateS())){
					hql+=" and regDatetime >='" + useReg.getAppDateS()+"' ";  //申请开始日期
			}
			if(StringUtils.isNotBlank(useReg.getAppDateE())){
				hql+=" and regDatetime <='" + useReg.getAppDateE()+"' ";  //申请结束日期
			}
			hql+=" order by urId desc ";
			
			List<UseReg> useRegList = useRegDao.find(hql, pageUtil);
			for (UseReg useR : useRegList) {
				//登记人姓名
				useR.setRegistrantName(userService.findUserOrgById(useR.getRegistrantNo()).getName());
				//领用人姓名
				useR.setUserName(userService.findUserOrgById(useR.getUser()).getName());
				//领用人部门
				useR.setDeptNoName(organizationService.findOrganizationByUserId(useR.getDeptNo()).getFullName());
			}
			return useRegList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean saveOrUpdUseReg(UseReg useReg) {
		try {
			if(useReg.getUrId()==null){
				useReg.setRegDatetime(new Date());
				useRegDao.save(useReg);
			}else{
				useRegDao.saveOrUpdate(useReg);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUseRegList(String ids) {
		try {
			useRegDao.executeHql("delete from UseReg where id in ("+ids+")");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Long getTotal(UseReg useReg) {
		try {
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT COUNT(*) FROM t_oa_ad_use_reg WHERE 1=1 ");
			//判断是否是超级管理人
			if(Constants.getCurrendUser().getUserId()!=1){
				sql.append(" AND REGISTRANT_NO="+Constants.getCurrendUser().getUserId());
			}
			
			if(StringUtils.isNotBlank(useReg.getPpeNo())){
				sql.append(" AND PPE_NO='"+useReg.getPpeNo()+"' ");
			}else{
				if(StringUtils.isNotBlank(useReg.getAppDateS())){
					sql.append(" AND REG_DATETIME >='" + useReg.getAppDateS()+"' ");
				}
				if(StringUtils.isNotBlank(useReg.getAppDateE())){
					sql.append(" and REG_DATETIME <='" + useReg.getAppDateE()+"' ");
				}
			}
			Long totalCount = useRegDao.findTotalCount(sql.toString());
			return totalCount;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0L;
	}
	
	

	
	
	
	
	
	
	
	
}
