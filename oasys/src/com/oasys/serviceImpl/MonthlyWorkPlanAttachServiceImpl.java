package com.oasys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.MonthlyWorkPlanAttach;
import com.oasys.service.MonthlyWorkPlanAttachService;
/**
 * @ClassName: MonthlyWorkPlanAttachServiceImpl 
 * @Description: TODO 物料附加表ServiceImpl
 * @author PANCHUANHE
 * @date 2015年9月16日 下午3:21:17
 */
@Service(value="monthlyWorkPlanAttachService")
public class MonthlyWorkPlanAttachServiceImpl implements MonthlyWorkPlanAttachService {

	@Autowired
	private PublicDao<MonthlyWorkPlanAttach> publicDao;
	
	@Override
	public boolean saveMonthlyWorkPlanAttach(MonthlyWorkPlanAttach monthlyWorkPlanAttach) {
		try {
			if(monthlyWorkPlanAttach.getMwpaId() == null){
				publicDao.save(monthlyWorkPlanAttach);
			}else{
				publicDao.update(monthlyWorkPlanAttach);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delMonthlyWorkPlanAttachByMwpaIds(String ids) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM t_oa_ad_monthly_work_plan_attach WHERE MWPA_ID in ("+ids+")");
			publicDao.executeBySql(sql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public MonthlyWorkPlanAttach findMonthlyWorkPlanAttachByMwpaId(Integer mwpaId){
		return publicDao.get(MonthlyWorkPlanAttach.class, mwpaId);
	}


	@Override
	public List<MonthlyWorkPlanAttach> findMonthlyWorkPlanAttachList(Integer mwpId){
		StringBuffer hql = new StringBuffer();
		hql.append("from MonthlyWorkPlanAttach m where m.mwpId = '"+mwpId+"' order by m.mwpaId desc");
		return publicDao.find(hql.toString());
	}

	@Override
	public boolean delMonthlyWorkPlanAttachByMwpId(String mwpIds) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM t_oa_ad_monthly_work_plan_attach WHERE MWP_ID in ("+mwpIds+")");
			publicDao.executeBySql(sql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
