package com.bpms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Organization;
import com.bpms.service.OrganizationService;
import com.bpms.util.Constants;
import com.bpms.util.HqlUtil;
import com.bpms.view.model.ComboBoxModel;
import com.bpms.view.model.TreeModel;


@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private BaseDAO<Organization> organizationDao;

	@Override
	public Organization findOrganizationById(Integer id) {
		return organizationDao.get(Organization.class, id);
	}
	@Override
	public List<TreeModel> findOrganizationList() {
		String hql = "from Organization o where o.status='A'";
		List<Organization> tempList = organizationDao.find(hql);
		List<TreeModel> list = new ArrayList<TreeModel>();
		for (Organization o : tempList) {
			TreeModel treeModel = new TreeModel();
			treeModel.setId(o.getOrganizationId() + HqlUtil.NULL_STRING);
			treeModel.setPid(o.getPid() == null ? null : o.getPid().toString());
			treeModel.setName(o.getFullName());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			treeModel.setIconCls(o.getIconcls());
			list.add(treeModel);
		}
		return list;
	}
	@Override
	public List<ComboBoxModel> findOrganizationCombo() {
		String sql = "SELECT tz.ORGANIZATION_ID code,tz.FULL_NAME text FROM t_organization tz WHERE tz.REGION_TYPE in (1,3)";
		List<Object> list = organizationDao.findBySQL(sql);
		List<ComboBoxModel> comboxList = new ArrayList<ComboBoxModel>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] g = (Object[]) list.get(i);
				comboxList.add(new ComboBoxModel((Integer)g[0]+"",String.valueOf(g[1])));
			}
		}
		return comboxList;
	}

}
