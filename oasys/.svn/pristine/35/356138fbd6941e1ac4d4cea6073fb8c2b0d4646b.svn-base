package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.service.LoginService;
import com.oasys.shiro.ShiroUser;
import com.oasys.util.Constants;
import com.oasys.viewModel.MenuModel;

@SuppressWarnings("rawtypes")
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private PublicDao baseDAO;

	/*
	 * (非 Javadoc) <p>Title: findMenuList</p> <p>Description:根据权限获取菜单 </p>
	 * 
	 * @return
	 */
	public List<MenuModel> findMenuList() {
		ShiroUser user = Constants.getCurrendUser();
		String sql = null;
		// 超级管理员默认拥有所有功能权限
		if (Constants.SYSTEM_ADMINISTRATOR.equals(user.getAccount())) {
			sql = "SELECT p.PERMISSION_ID,p.PID,p.NAME,p.ICONCLS,p.URL FROM QQMS.T_PERMISSION AS p\n"
					+ "where p.STATUS='A' and p.TYPE='F' and p.ISUSED='Y' and p.SYSTEM_TYPE='2' order by p.SORT";
		} else {
			sql = "SELECT DISTINCT p.PERMISSION_ID,p.PID,p.NAME,p.ICONCLS,p.URL FROM\n"
					+ "QQMS.T_ROLE_AND_PERMISSION AS rp\n"
					+ "INNER JOIN QQMS.T_ROLE AS r ON rp.ROLE_ID = r.ROLE_ID\n"
					+ "INNER JOIN QQMS.T_USER_AND_ROLE AS ur ON rp.ROLE_ID = ur.ROLE_ID\n"
					+ "INNER JOIN QQMS.T_USERS AS u ON u.USER_ID = ur.USER_ID\n"
					+ "INNER JOIN QQMS.T_PERMISSION AS p ON rp.PERMISSION_ID = p.PERMISSION_ID  and p.SYSTEM_TYPE='2'\n"
					+ "WHERE rp.STATUS='A' and r.STATUS='A' and ur.STATUS='A' and u.AVAILABLE='A' and p.STATUS='A' and p.TYPE='F' and p.ISUSED='Y'\n"
					+ "and u.USER_ID=" + user.getUserId() + " order by p.SORT";
		}
		List listmenu = baseDAO.findBySQL(sql);

		// 返回
		return getMenuModelList(new MenuModel(), castMenuModelList(listmenu))
				.getChildren();
	}

	/**
	 * 将结果转化为树结构
	 * 
	 * @param parentMenuModel
	 *            制定父节点递归组树
	 * @param listMenu
	 *            源数据
	 * @return 组成树结构的数据
	 */
	private MenuModel getMenuModelList(MenuModel parentMenuModel,
			List<MenuModel> listMenu) {
		List<MenuModel> list = new ArrayList<MenuModel>();
		for (MenuModel menu : listMenu) {
			String pid = String.valueOf(parentMenuModel.getId());
			String cid = String.valueOf(menu.getPid());
			if (pid.equals(cid)) {
				list.add(menu);
				getMenuModelList(menu, listMenu);
			}
		}
		parentMenuModel.setChildren(list);
		return parentMenuModel;
	}

	/**
	 * 将结果转化为menuModel类型
	 * 
	 * @param listMenu
	 *            转化的前
	 * @return menuModel的列表
	 */
	private List<MenuModel> castMenuModelList(List<?> listMenu) {
		List<MenuModel> list = new ArrayList<MenuModel>();
		for (Object object : listMenu) {
			Object[] objs = (Object[]) object;
			MenuModel menuModel = new MenuModel();
			menuModel.setId(String.valueOf(objs[0]));
			menuModel.setPid(String.valueOf(objs[1]));
			menuModel.setText(String.valueOf(objs[2]));
			menuModel.setIconCls(String.valueOf(objs[3]));
			menuModel.setUrl(String.valueOf(objs[4]));
			list.add(menuModel);
		}
		return list;
	}
}
