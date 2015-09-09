package com.qqms.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqms.dao.PublicDao;
import com.qqms.model.Organization;
import com.qqms.service.OrganizationService;
import com.qqms.util.Constants;
import com.qqms.util.HqlUtil;
import com.qqms.viewModel.TreeModel;

/**
 * 
 * 组织机构管理ServiceImpl
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
@Service("organizationService")
@SuppressWarnings("unchecked")
public class OrganizationServiceImpl implements OrganizationService {

	/** 自动注入实体共同dao */
	@SuppressWarnings("rawtypes")
	@Autowired
	private PublicDao publicDao;

	public List<TreeModel> findOrganizationList() {
		String hql = "from Organization o where o.status='A'";
		List<Organization> tempList = publicDao.find(hql);
		List<TreeModel> list = new ArrayList<TreeModel>();
		for (Organization o : tempList) {
			TreeModel treeModel = new TreeModel();
			treeModel.setId(o.getOrganizationId() + HqlUtil.NULL_STRING);
			treeModel.setPid(o.getPid() == null ? null : o.getPid().toString());
			treeModel.setName(o.getFullName());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			treeModel.setIconCls(o.getIconCls());
			list.add(treeModel);
		}
		return list;
	}

	public List<Organization> findOrganizationList(Integer id) {
		String hql = "from Organization o where o.status='A' ";
		if (null == id || "".equals(id)) {
			hql += " and o.pid is null";
		} else {
			hql += " and o.pid=" + id;
		}
		return publicDao.find(hql);
	}
	
	public List<Organization> findOrganizationByIdList(){
		String hql = "from Organization o where o.status='A'";
		List<Organization> tempList = publicDao.find(hql);
		return tempList;
	}

	public boolean persistenceOrganization(Organization o) {
		Integer userId = Constants.getCurrendUser().getUserId();
		if (null == o.getOrganizationId() || "".equals(o.getOrganizationId())) {
			o.setCreated(new Date());
			o.setLastmod(new Date());
			o.setCreater(userId);
			o.setModifyer(userId);
			o.setStatus(Constants.PERSISTENCE_STATUS);
			publicDao.save(o);
		} else {
			o.setLastmod(new Date());
			o.setModifyer(userId);
			publicDao.update(o);
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public boolean delOrganization(Integer id) {
		String hql = "from Organization o where o.status='A' and o.pid=" + id;
		List<Organization> list = publicDao.find(hql);
		if (list.size() != 0) {
			return false;
		} else {
			String hql2 = "from Users t where t.organizeId=" + id;
			List list2 = publicDao.find(hql2);
			if (list2.size() != 0) {
				return false;
			} else {
				Organization o = (Organization) publicDao.get(
						Organization.class, id);
				o.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
				o.setLastmod(new Date());
				o.setModifyer(Constants.getCurrendUser().getUserId());
				publicDao.deleteToUpdate(o);
			}
			return true;
		}
	}

	@Override
	public String getRegionTypeById(Integer id) {
		// TODO Auto-generated method stub
		String hql = "select t.regionType from Organization t where t.status='A' and t.id="+id;
		String regionType=null;
		List<Object> orgList = publicDao.find(hql);
		if(orgList!=null&&orgList.size()>0){
			regionType = orgList.get(0).toString();
		}
		return regionType;
	}
	
}
