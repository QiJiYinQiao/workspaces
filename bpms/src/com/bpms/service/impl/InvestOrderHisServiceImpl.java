package com.bpms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.InvestOrderHis;
import com.bpms.model.vo.InvestOrderHisModel;
import com.bpms.model.vo.LoanOrderHisModel;
import com.bpms.service.InvestOrderHisService;
import com.bpms.util.Collections;

/**
 * 投资履历的service
 * 
 * @author liuhh
 *
 */
@Service
public class InvestOrderHisServiceImpl implements InvestOrderHisService {

	@Autowired
	private BaseDAO<InvestOrderHis> baseDAO;

	@Override
	public boolean persistenceInvestOrderHis(InvestOrderHis investOrderHis) {
		if (StringUtils.isBlank(investOrderHis.getInvOrdHisId())) {
			baseDAO.save(investOrderHis);
		} else {
			baseDAO.update(investOrderHis);
		}
		return false;
	}

	@Override
	public List<InvestOrderHisModel> findAllInvestOrderHisList(String investOrderId) {
		// 定义返回值
		List<InvestOrderHisModel> hisModels = new ArrayList<InvestOrderHisModel>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append(" tbioh.AGENT_TIME, ");
		sb.append(" tbioh.ASSIGNEE, ");
		sb.append(" tu.USER_NAME AS assigneeName, ");
		sb.append(" tbioh. COMMENT, ");
		sb.append(" tbioh.INV_ORD_HIS_ID, ");
		sb.append(" tbioh.INVEST_ORDER_ID, ");
		sb.append(" tbio.CREATE_DATE, ");
		sb.append(" tbioh.ORDER_STATUS, ");
		sb.append(" tbos.STATUS_NAME, ");
		sb.append(" tbioh.ROLE_ID, ");
		sb.append(" tr. NAME AS roleName ");
		sb.append(" FROM ");
		sb.append(" t_bp_invest_order_his tbioh ");
		sb.append(" LEFT JOIN t_bp_invest_order tbio ON tbioh.invest_order_id = tbio.invest_order_id ");
		sb.append(" LEFT JOIN t_bp_order_status tbos ON tbioh.ORDER_STATUS = tbos.STATUS_ID ");
		sb.append(" LEFT JOIN t_role tr ON tbioh.ROLE_ID = tr.ROLE_ID ");
		sb.append(" LEFT JOIN t_users tu ON tbioh.ASSIGNEE = tu.USER_ID ");
		sb.append(" WHERE ");
		sb.append(" tbioh.invest_order_id = '" + investOrderId + "'");
		String sql=sb.toString();
		List<Object> list = this.baseDAO.findBySQL(sql);
		if (Collections.listIsNotEmpty(list)) {
			for (Object l : list) {
				Object[] obj = (Object[]) l;
				InvestOrderHisModel model = new InvestOrderHisModel();
				model.setAgentTime((Date) obj[0]);
				model.setAssignee((String) obj[1]);
				model.setAssigneeName((String) obj[2]);
				model.setComment((String) obj[3]);
				model.setInvOrdHisId((String) obj[4]);
				model.setInvestOrderId((String) obj[5]);
				model.setCreateDate((Date) obj[6]);
				model.setOrderStatus((String) obj[7]);
				model.setOrderStatusName((String) obj[8]);
				model.setRoleId((String) obj[9]);
				model.setRoleName((String) obj[10]);
				hisModels.add(model);
			}
		}
		return hisModels;
	}

}
