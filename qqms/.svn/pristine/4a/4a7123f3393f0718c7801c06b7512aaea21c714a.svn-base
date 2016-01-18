package com.qqms.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.property.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.text.impl.ChineseTextProducer;
import com.qqms.dao.PublicDao;
import com.qqms.model.Role;
import com.qqms.model.UserRole;
import com.qqms.model.Users;
import com.qqms.service.UserService;
import com.qqms.shiro.ShiroUser;
import com.qqms.util.ChineseToPinYinUtil;
import com.qqms.util.Constants;
import com.qqms.util.HqlUtil;
import com.qqms.util.PageUtil;
import com.qqms.viewModel.Json;
import com.qqms.viewModel.UserRoleModel;

@Service("userService")
@SuppressWarnings("rawtypes")
public class UserServiceImpl implements UserService {
	private PublicDao<Users> publicDao;
	private PublicDao publicDaoSQL;

	@Autowired
	public void setPublicDao(PublicDao<Users> publicDao) {
		this.publicDao = publicDao;
	}

	@Autowired
	public void setPublicDaoSQL(PublicDao publicDaoSQL) {
		this.publicDaoSQL = publicDaoSQL;
	}

	public boolean persistenceUsers(Map<String, List<Users>> map) {
		this.addUsers(map.get("addList"));
		this.updUsers(map.get("updList"));
		this.delUsers(map.get("delList"));
		return true;
	}

	/*
	 * 查询列表
	 */
	public List<Users> findAllUserList(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "from Users u where u.status='A' ";
		hql += HqlUtil.getSearchConditionsHQL("u", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("u", pageUtil);
		List<Users> list = publicDao.find(hql, map, pageUtil.getPage(),
				pageUtil.getRows());
		for (Users users : list) {
			users.setUserRoles(null);
		}
		return list;
	}

	/**
	 * 查询总条数
	 */
	public Long getCount(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "select count(*) from Users  u where u.status='A' ";
		hql += HqlUtil.getSearchConditionsHQL("u", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("u", pageUtil);
		return publicDao.count(hql, map);
	}

	private boolean addUsers(List<Users> addList) {
		if (addList != null && addList.size() != 0) {
			ShiroUser user = Constants.getCurrendUser();
			for (Users users : addList) {
				users.setCreated(new Date());
				users.setLastmod(new Date());
				users.setLastVisits(new Date());
				users.setCreater(user.getUserId());
				users.setModifyer(user.getUserId());
				users.setStatus(Constants.PERSISTENCE_STATUS);
				publicDao.save(users);
			}
		}
		return true;
	}

	private boolean updUsers(List<Users> updList) {
		if (updList != null && updList.size() != 0) {
			ShiroUser user = Constants.getCurrendUser();
			for (Users users : updList) {
				users.setLastmod(new Date());
				users.setModifyer(user.getUserId());
				publicDao.update(users);
			}
		}
		return true;
	}

	private boolean delUsers(List<Users> delList) {
		ShiroUser user = Constants.getCurrendUser();
		if (delList != null && delList.size() != 0) {
			for (Users users : delList) {
				users.setLastmod(new Date());
				users.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
				users.setModifyer(user.getUserId());
				publicDao.update(users);
			}
		}
		return true;
	}

	/**
	 * 新增和修改
	 */
	public Json persistenceUsers(Users u) {
		Integer userId = Constants.getCurrendUser().getUserId();// 获取当前用户的ID
		String msg = "";
		if (null == u.getUserId() || "".equals(u.getUserId())) {
			try {
				u.setEname(ChineseToPinYinUtil.getUpEname(u.getName()));
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			u.setPassword(u.getPassword());
			u.setCreated(new Date());
			u.setLastmod(new Date());
			u.setCreater(userId);
			u.setModifyer(userId);
			u.setStatus(Constants.PERSISTENCE_STATUS);
			publicDao.save(u);
			msg = "数据添加成功!";
		} else {
			try {
				u.setEname(ChineseToPinYinUtil.getUpEname(u.getName()));
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			u.setPassword(u.getPassword());
			u.setLastmod(new Date());
			u.setModifyer(userId);
			publicDao.update(u);
			msg = "数据修改成功!";
		}
		return new Json("提示", msg, true);
	}

	/**
	 * 批量删除多个用户
	 */
	public boolean delUsers(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] idsArray = ids.split(",");
			for (int i = 0; i < idsArray.length; i++) {
				int id = Integer.parseInt(idsArray[i]);
				Users users = publicDao.get(Users.class, id);
				users.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
				users.setLastmod(new Date());
				users.setModifyer(Constants.getCurrendUser().getUserId());
				publicDao.deleteToUpdate(users);
			}
			return true;
		}
		return false;
	}

	public List<UserRoleModel> findUsersRolesList(Integer userId) {
		String sql = "SELECT ur.USER_ID,ur.ROLE_ID FROM\n"
				+ "T_USER_AND_ROLE AS ur where ur.STATUS ='A' and ur.USER_ID="
				+ userId;
		List list = publicDaoSQL.findBySQL(sql);
		List<UserRoleModel> listm = getUserRoleModelList(userId, list);
		return listm;
	}

	private List<UserRoleModel> getUserRoleModelList(Integer userId, List list) {
		List<UserRoleModel> listm = new ArrayList<UserRoleModel>();
		for (Object object : list) {
			Object[] obj = (Object[]) object;
			UserRoleModel userRoleModel = new UserRoleModel();
			userRoleModel.setUserId(userId);
			userRoleModel.setRoleId(obj[1] == null ? null : Integer
					.valueOf(obj[1].toString()));
			listm.add(userRoleModel);
		}
		return listm;
	}

	@SuppressWarnings("unchecked")
	public boolean saveUserRoles(Integer userId, String isCheckedIds) {
		Users user = publicDao.get(Users.class, userId);
		Set<UserRole> set = user.getUserRoles();
		Map<Integer, UserRole> map = new HashMap<Integer, UserRole>();
		for (UserRole userRole : set) {
			map.put(userRole.getRole().getRoleId(), userRole);
			userRole.setLastmod(new Date());
			userRole.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
			publicDaoSQL.deleteToUpdate(userRole);
		}
		if (!"".equals(isCheckedIds) && isCheckedIds.length() != 0) {
			String[] ids = isCheckedIds.split(",");
			ShiroUser currUser = Constants.getCurrendUser();
			for (String id : ids) {
				Integer tempId = Integer.valueOf(id);
				Role role = (Role) publicDaoSQL.get(Role.class,
						Integer.valueOf(id));
				UserRole userRole = null;
				if (map.containsKey(tempId)) {
					userRole = map.get(tempId);
					userRole.setStatus(Constants.PERSISTENCE_STATUS);
					userRole.setCreater(currUser.getUserId());
					userRole.setModifyer(currUser.getUserId());
					publicDaoSQL.update(userRole);
				} else {
					userRole = new UserRole();
					userRole.setCreated(new Date());
					userRole.setLastmod(new Date());
					userRole.setRole(role);
					userRole.setUsers(user);
					userRole.setCreater(currUser.getUserId());
					userRole.setModifyer(currUser.getUserId());
					userRole.setStatus(Constants.PERSISTENCE_STATUS);
					publicDaoSQL.save(userRole);
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @time:2015年9月8日 下午2:15:32
	 * @Title:findCountByAccount
	 * @Description:TODO 查询数据库里是否有重名账户（这里描述这个方法的作用）
	 * @param account
	 * @throws:
	 */
	public boolean isExistsAccount(Users u){
		boolean flag = false;
		String hql = "from Users u where u.status='A' and u.account = '"+u.getAccount()+"'";
		Long count = publicDao.count("select count(*)" +hql);
		if(null==u.getUserId() ||"".equals(u.getUserId())){
			//新增操作
			if(count>0){
				flag=true;
			}
		}else{
			//修改操作
			hql+=" and u.userId <>'"+u.getUserId()+"'";
			Long count1 = publicDao.count("select count(*)"+hql);
			if(count1>=1){
				flag=true;
			}
		}
		return flag;
	}
}
