package com.oasys.serviceImpl.webServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.oasys.dao.PublicDao;
import com.oasys.model.Department;
import com.oasys.model.Organization;
import com.oasys.model.OrganizationRole;
import com.oasys.model.Permission;
import com.oasys.model.Role;
import com.oasys.model.User;
import com.oasys.model.UserRole;
import com.oasys.model.Users;
import com.oasys.service.RoleService;
import com.oasys.service.webService.EmWebService;
import com.oasys.util.ChineseToPinYinUtil;
import com.oasys.util.Constants;
import com.oasys.webService.util.CityNameUtil;
import com.oasys.webService.util.RoleCodeConvertUtil;
import com.oasys.webService.util.WebServiceUtil;

@Service("emWebService")
public class EmWebServiceImpl implements EmWebService {

	@Autowired
	private PublicDao<User> emUserDao;
	
	@Autowired
	private PublicDao<Users> userDao;
	
	@Autowired
	private PublicDao<Department> deptDao;
	
	@Autowired
	private PublicDao<Role> roleDao;
	
	@Autowired
	private PublicDao<Permission> perDao;
	
	@Autowired
	private PublicDao<Organization> orgDao;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public void saveEmUser(User user) {
		// TODO Auto-generated method stub
		emUserDao.save(user);
	}

	@Override
	public void saveEmDepartment(Department dept) {
		// TODO Auto-generated method stub
		deptDao.save(dept);
	}
	
	@Override
	public void saveRolePermission(){
		String sql = "from Role";
		List<Role> roleList = roleDao.find(sql);
		sql = "from Permission where name in(".concat(WebServiceUtil.OA_SYS_INIT_PERMISSION).concat(")").concat(" and systemType='2'").concat(" and status='A'");
		List<Permission> perList = perDao.find(sql);
		for (Role role : roleList) {
			if(role.getRolePermissions().size()==0){
				for (Permission permission : perList) {
					try {
						sql ="INSERT INTO qqms.t_role_and_permission (PERMISSION_ID,ROLE_ID,STATUS)"
								+ " VALUES ("+permission.getPermissionId().toString()+","+role.getRoleId().toString()+",'A')";
						roleDao.executeBySql(sql);
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
			}
		}
		roleList = null;
		perList = null;
		sql = "";
	}
	
	/***
	 * 同步角色
	 * @return
	 */
	@Override
	public int syncEmRole() {
		String str = "from User";
		List<Role> roleList = com.oasys.webService.model.Role.parse(emUserDao.find(str));//角色列表
		int i = 0;
		for (Role role : roleList) {
			try {
				if(null == roleService.findRoleByCode(role.getRoleCode())){
					roleDao.save(role);		
					i++;
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		//ID序列从1000开始
		str = "UPDATE qqms.`t_role` SET role_id= role_id+1000 where role_id != 1";
		roleDao.executeBySql(str);
		str = "";
		roleList = null;
		saveRoleOrg();//增加角色与组织机构对应关系
		return i;
	}
	
	/***
	 * 同步组织机构
	 * @return
	 */
	@Override
	public int syncDepartment() {
		// TODO Auto-generated method stub
		String str = "from Department";
		List<Department> deptList = deptDao.find(str);
		List<Organization> orgList = new ArrayList<Organization>();
		List<Organization> emOrgList = new ArrayList<Organization>();
		Organization org;
		int i = 0;
		for (Department department : deptList) {
			str = "from Organization where id="+department.getDepartmentId();
			emOrgList = orgDao.find(str);
			if(CollectionUtils.isEmpty(emOrgList)){
				//组织机构不存在 新增组织机构
				org = new Organization();
				org.setStatus("A");
				org.setCreated(new Date());
				org.setLastmod(new Date());
				org.setId(department.getDepartmentId());
				org.setCreater(Constants.getCurrendUser().getUserId());
			}else{
				//组织机构若存在 则更新组织机构信息
				org = emOrgList.get(0);
			}
			try {
				org.setFullName(department.getDepartmentName());
				org.setDescription(department.getDepartmentName());
				org.setEname(RoleCodeConvertUtil.getOrgEnameByOrgName().get(org.getFullName()));//eName翻译 
				//263接口中top节点pid为0 割接到oasys系统中需转化为null
				org.setPid(department.getDeptId() == 0 ? null : department.getDeptId());
				orgDao.saveOrUpdate(org);//保存信息 
				orgList.add(org);
				i++;
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		deptList = null;
		orgList = null;
		emOrgList = null;
		org = null;
		str = "";
		saveOrgPid();//重新计算organizationId与pid之间的关系
		saveOrgField();//更新组织机构其他字段
		return i;
	}
	
	/**
	 * 导入excel同步用户
	 * */
	@Override
	public List<String> startImportExcelToDB(File file) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("0");  
		String hql="";
		List<String> parentList = new ArrayList<String>();
		List<Row> rowList = new ArrayList<Row>();
		try {
			FileInputStream fin = new FileInputStream(file);
			Workbook workbook = create(fin);
			Sheet sheet = workbook.getSheetAt(0);
			int last = sheet.getLastRowNum();
			int index;
			String userName = "";
			for (index =1;index <= last;index++) {
				User user = new User();
				Row row = sheet.getRow(index);
				/*
				 * 循环读取每一列的数据并存入数据库
				 */
				try {
					Cell cell = row.getCell((short) 0);// 用户姓名
					if (cell != null) {
						userName = cell.getStringCellValue().trim();
						user.setName(userName);
						userName = userName.concat(userName.substring(0, 1)).substring(1);//将姓放在后面
						user.setUserId(ChineseToPinYinUtil.getEname(userName));//转化为小写英文 userID
					}
					cell = row.getCell((short) 3);//电话号码
					if (cell != null) {
						try {
							user.setMobile(df.format(cell.getNumericCellValue()));
						} catch (Exception e) {
							user.setMobile(cell.getStringCellValue());
						}
					}
					cell = row.getCell((short) 5);//职位
					if (cell != null) {
						user.setOffice(cell.getStringCellValue().trim());
					}
					cell = row.getCell((short) 6);//部门名称
					if (cell != null) {
						user.setOrgName(cell.getStringCellValue());
					}
					cell = row.getCell((short) 7);//组织机构
					if (cell != null) {
						parentList.add(cell.getStringCellValue());
						user.setParent(cell.getStringCellValue());
						user.setDepartmentId(getDeptByParent(cell.getStringCellValue().trim()));
					}
					//组织机构如果为空
					if(null == user.getDepartmentId() && !parentList.contains(user.getParent())){
						rowList.add(row);
						break;
					}
					hql = "from User where (userId='"+user.getUserId()+"' or userId='"+user.getUserId()+"@"+WebServiceUtil.WS_DOMAIN+"')"
							+" and (mobile='?' OR phone ='?')";//人名拼音与电话都相同 则视为同一人
					hql = hql.replace("?",user.getMobile());
					if(emUserDao.find(hql).size()==0){//防止重复添加
						int userCount = 0;
						String userIdChar = "";
						//如果userId相同 电话信息不相同 则将userId后拼接数字
						hql ="from User where (userId='"+user.getUserId()+"' or userId='"+user.getUserId()+"@"+WebServiceUtil.WS_DOMAIN+"')";
						while(emUserDao.find(hql).size() != 0){//while循环 直到userId不重复为止
							userIdChar = user.getUserId().substring(user.getUserId().length()-1);//取userId最后字符 如果为数字 则递增 否则在userId后拼数字 从1开始
							if(Character.isDigit(userIdChar.charAt(0))){//判断是否为数字
								userCount = Integer.valueOf(userIdChar)+1;
								user.setUserId(user.getUserId().substring(0,user.getUserId().length()-1).concat(String.valueOf(userCount)));//将userId重新赋值 原有userId后拼接数字 1 2 3 以此类推
							}else{
								user.setUserId(user.getUserId().concat("1"));//将userId重新赋值 原有userId后拼接数字 1 2 3 以此类推
							}
							//user.setUserId(user.getUserId().concat(j));//将userId重新赋值 原有userId后拼接数字 1 2 3 以此类推
							//将重新组合的userId到数据库中进行查找 知道查找不到再退出循环
							hql ="from User where (userId='"+user.getUserId()+"' or userId='"+user.getUserId()+"@"+WebServiceUtil.WS_DOMAIN+"')";
						}
						emUserDao.save(user);//保存或更新操作
					}
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
			fin.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return findUserParent();
	}
	
	/***
	 * 查找用户的直属上级组织机构为空的用户 将对应excel的行放入新的excel的sheet中
	 * @return
	 */
	protected List<String> findUserParent(){
		String hql = "from User where userId not like '%@qqjrbj.com%' and departmentId is null";
		List<User> userList = emUserDao.find(hql);
		List<String> userParentList = new ArrayList<String>();
		for (User user : userList) {
			if(!userList.contains(user.getParent())){
				userParentList.add(user.getParent());
			}
		}
		return userParentList;
	}
	
	/***
	 * 计算组织机构的myid字段 
	 * @param orgName
	 * @param myid
	 */
	@SuppressWarnings("unchecked")
	protected void saveOrgMyId(String orgName,String myid){
		String sql = "SELECT organization_id FROM qqms.t_organization WHERE full_name LIKE '%"+orgName+"%' LIMIT 1";
		List<Object> list = orgDao.findBySQL(sql);
		String id = "";
		if(!CollectionUtils.isEmpty(list)){
			for (Object object : list) {
				if(null != object){
					id = object.toString();
					orgDao.executeHql("update Organization org set org.myid='"+myid+"',deptLevel='1' where organizationId = "+id);//更新借款或者财富节点的myid
				}
			}
		}
		sql = "SELECT organization_id FROM qqms.t_organization WHERE pid="+id;//查询指定组织下的所有组织信息
		list = orgDao.findBySQL(sql);
		if(!CollectionUtils.isEmpty(list)){
			List<Object> childList;
			String ids = "";
			for (Object object : list) {
				if(null != object){
					sql = "SELECT GROUP_CONCAT(organization_id) FROM qqms.t_organization WHERE FIND_IN_SET(organization_id, oasys.get_org_child("+object.toString()+"))";//查询组织下树形结构 所有子节点ID
					childList = orgDao.findBySQL(sql);
					if(!CollectionUtils.isEmpty(childList)){
						ids = childList.toString().replace("[", "").replace("]", "").concat(",").concat(object.toString());
						orgDao.executeHql("update Organization org set org.myid='"+myid+"',deptLevel='1' where organizationId in("+ids+")");
					}
				}
			}
		}
	}

	
	/***
	 * 同步用户表
	 * @return
	 */
	@Override
	public int syncEmUsers() {
		// TODO Auto-generated method stub
		List<User> emUserList = emUserDao.find("from User");
		List<Users> uList = new ArrayList<Users>();
		Users u;
		Organization org = new Organization();
		String loginAct = "";
		int i = 0;
		for (User user : emUserList) {
			try {
				uList = userDao.find("from Users where account='"+user.getUserId().trim()+"'");//控制重复添加账号信息
				if(CollectionUtils.isEmpty(uList)){
					u = new Users();
					loginAct = user.getUserId().split("@") == null ? user.getUserId() : user.getUserId().split("@")[0];
					u.setName(user.getName());
					u.setEname(loginAct);
					u.setMyid(loginAct);
					//如果用户的部门与角色名称相同 则将用户配置到改机构的父级节点上 否则保持之前的配置
					org = orgDao.find("from Organization where id = ".concat(user.getDepartmentId().toString())).get(0);
					u.setOrganizeId(getOrgParentByOrgRoleName(org));
					u.setAccount(loginAct);
					u.setPassword(WebServiceUtil.encryption(WebServiceUtil.OA_SYS_INIT_PWD).toUpperCase());//32位md5加密 大写
					u.setTel(user.getPhone());
					u.setMobile(user.getMobile());
					u.setEmail(user.getUserId());
					u.setStatus("A");
					u.setCreated(new Date());
					u.setCreater(1);
					u.setUserRoles(getUserRoleByOffice(u,user.getOffice()));
					userDao.save(u);
					i++;
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		//删除与角色名称相同的组织机构
		orgDao.executeBySql("DELETE FROM qqms.`t_organization` WHERE full_name IN(SELECT NAME FROM qqms.`t_role`)");
		//删除与配置Map中需删除的组织机构
		orgDao.executeBySql("DELETE FROM qqms.`t_organization` WHERE full_name IN(".concat(WebServiceUtil.OA_SYS_INIT_ORG_PARENT).concat(")"));
		return i;
	}
	
	/***
	 * 获取用户与角色对应关系
	 * @param user
	 * @param office
	 * @return
	 */
	protected Set<UserRole> getUserRoleByOffice(Users user,String office) {
		List<Role> role = roleDao.find("from Role where name='".concat(RoleCodeConvertUtil.parseRoleNameByEmOffice(office.trim())).concat("'"));
		Set<UserRole> setUR = new HashSet<UserRole>();
		UserRole ur;
		for (Role r : role) {
			ur = new UserRole();
			ur.setCreated(new Date());
			ur.setCreater(1);
			ur.setRole(r);
			ur.setStatus("A");
			ur.setUsers(user);
			setUR.add(ur);
		}
		return setUR;
	}
	
	//返回组织机构中与角色名称相同的组织机构的父级节点
	protected Integer getOrgParentByOrgRoleName(Organization org){
		String orgName = RoleCodeConvertUtil.parseRoleNameByEmOffice(org.getFullName().trim());
		if(roleDao.find("from Role where name='".concat(orgName).concat("'")).size() == 0 && WebServiceUtil.OA_SYS_INIT_ORG_PARENT.
				indexOf("'".concat(orgName).concat("'"))==-1){
			return org.getOrganizationId();
		}else{
			return org.getPid();
		}
	}
	
	/***
	 * 计算组织机构中pid字段
	 */
	protected void saveOrgPid(){
		String hql="from Organization";
		List<Organization> orgList = orgDao.find(hql);
		List<Organization> orgChlidList;
		for (Organization organization : orgList) {
			hql = "from Organization where id="+organization.getPid();
			orgChlidList = orgDao.find(hql);
			if(!CollectionUtils.isEmpty(orgChlidList)){
				organization.setPid(orgChlidList.get(0).getOrganizationId());
				orgDao.saveOrUpdate(organization);
			}
		}
	}
	
	/***
	 * 更新组织机构表的tree_level字段
	 */
	@SuppressWarnings("unchecked")
	protected void saveOrgTreeLevel(){
		String sql = "from Organization";
		List<Organization> orgList = orgDao.find(sql);
		List<Object> tlList = new ArrayList<Object>();
		for (Organization organization : orgList) {
			try {
				sql="SELECT COUNT(organization_id) FROM qqms.t_organization WHERE FIND_IN_SET(organization_id,oasys.get_org_parent(?))";
				sql = sql.replace("?", organization.getOrganizationId().toString());
				tlList = orgDao.findBySQL(sql);
				if(!CollectionUtils.isEmpty(tlList)){
					organization.setTreeLevel("0".concat(String.valueOf(tlList.get(0))));
					orgDao.saveOrUpdate(organization);
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
	
	/***
	 * 更新组织机构表中regionType字段
	 */
	@Override
	public void saveOrgRegionType(){
		String sql ="from Organization where myid in(".concat(WebServiceUtil.QQMS_SYS_INIT_ORG_REGION_TYPE).concat(")");
		List<Organization> orgList = orgDao.find(sql);
		int regionType = 0;
		for (Organization organization : orgList) {
			try {
				if(getOrgNameIsMDU(organization)){//如果为直辖市 直接更新为直辖市的标识
					organization.setRegionType("1");
				}else if(getOrgNameIsCity(organization)){//判断是否为城市
					organization.setRegionType("3");
				}else if(Integer.parseInt(organization.getTreeLevel())<=3){
					regionType = Integer.parseInt(organization.getTreeLevel()) - 2;//treeLevel与regionType的关系为 treeLevel - 2为regionType的级别
					regionType = regionType <= 3 ? regionType : 4;//如果regionType为3以外的值 则更新为其他 4
					regionType = regionType == 1 ? 2 : regionType;//普通省份也会转化为1 这里是将普通省份的regionType强制转化为2
					organization.setRegionType(String.valueOf(regionType));
				}else{
					organization.setRegionType("4");
				}
				orgDao.saveOrUpdate(organization);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	/***
	 * 更新组织机构相关字段
	 */
	@Override
	public void saveOrgField() {
		//更新财富管理中心与借款管理中心的myID
		saveOrgMyId("财富管理中心","CF");
		saveOrgMyId("借款管理中心","JK");
		saveOrgTreeLevel();//更新tree_level字段
		saveOrgRegionType();//计算regionType
		orgDao.executeHql("update Organization org set org.myid='QQJRBJ' where pid is null");//根节点钱钱金融的myID为 QQJRBJ
		orgDao.executeHql("update Organization org set org.deptLevel='0' where dept_level is null");//除财富与借款管理中心外 其他节点的dept_level全部更新为0
	}
	
	//判断组织机构名称是否为直辖市
	protected boolean getOrgNameIsMDU(Organization org){
		for (String mduName : WebServiceUtil.QQMS_SYS_INIT_MDU.split(",")) {
			//如果为直辖市 并且treeLevel为03级 则定位为直辖市 否则不为直辖市
			if(org.getFullName().trim().indexOf(mduName) != -1 && "03".equals(org.getTreeLevel())){
				return true;
			}
		}
		return false;
	}

	protected boolean getOrgNameIsCity(Organization org){
		for (String cityName : CityNameUtil.cityName.split(",")) {
			if(StringUtils.isNotBlank(cityName) && org.getFullName().indexOf(cityName) != -1 && "04".equals(org.getTreeLevel())){//判断是否为城市 层级限定为城市层级
				return true;
			}
		}
		return false;
	}
	
	/***
	 * 获取组织机构直属上级部门信息
	 * @param cellString
	 * @return
	 */
	protected Integer getDeptByParent(String cellString){
		String hql = "from User where userId like '%".concat(cellString).concat("@").concat(WebServiceUtil.WS_DOMAIN).concat("%'");
		List<User> emUserList = emUserDao.find(hql);
		if(!CollectionUtils.isEmpty(emUserList)){
			hql = "from Department where departmentId = ".concat(emUserList.get(0).getDepartmentId().toString());
			List<Department> deptList = deptDao.find(hql);
			if(!CollectionUtils.isEmpty(deptList)){
				return deptList.get(0).getDeptId();
			}
		}
		return null;
	}
	
	//创建excel工作集
	protected static Workbook create(InputStream inp) throws IOException,
			InvalidFormatException {
		if (!inp.markSupported()) {
			inp = new PushbackInputStream(inp, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(inp)) {//.xls
			return new HSSFWorkbook(inp);
		}
		if (POIXMLDocument.hasOOXMLHeader(inp)) {//.xlsx
			return new XSSFWorkbook(OPCPackage.open(inp));
		}
		throw new IllegalArgumentException("你的excel版本目前poi解析不了");
	}
	
	/***
	 * 保存组织机构与角色中间表
	 * @param role
	 * @return
	 */
	protected void saveRoleOrg(){
		String roleOrg = "";
		Set<OrganizationRole> orgRoleSetList = new HashSet<OrganizationRole>();
		List<Organization> ogList;
		List<Role> roleList = roleDao.find("from Role");
		for (Role role : roleList) {
			roleOrg = RoleCodeConvertUtil.getRoleOrgByRoleName().get(role.getName());
			//判断角色是否配置组织机构
			if(StringUtils.isNotBlank(roleOrg)){
				ogList = orgDao.find("from Organization where fullName='"+roleOrg+"'");
				if(!CollectionUtils.isEmpty(ogList)){
					OrganizationRole or = new OrganizationRole();
					or.setCreated(new Date());
					or.setCreater(1);
					or.setOrganization(ogList.get(0));
					or.setRole(role);
					or.setStatus("A");
					orgRoleSetList.add(or);
					role.setOrganizationRoles(orgRoleSetList);
					roleDao.save(role);
				}
			}
		}
	}

	@Override
	public int saveRoleParent() {
		List<Role> roleList = roleDao.find("from Role");
		String parentName = "";
		int i = 0;
		for (Role role : roleList) {
			try {
				parentName = RoleCodeConvertUtil.getRoleParentByRoleName().get(role.getName());
				if(StringUtils.isNotBlank(parentName)){
					role.setPid(roleService.findRoleByCode(ChineseToPinYinUtil.getUpEname1(parentName)).getRoleId());
					roleDao.saveOrUpdate(role);
					i++;
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return i;
	}

	@Override
	public void saveRoleType() {
		List<Role> roleList = roleDao.find("from Role");
		for (Role role : roleList) {
			if(RoleCodeConvertUtil.getRoleTypeByRoleName().containsKey(role.getName())){
				roleDao.executeHql("update Role set roleType ="+RoleCodeConvertUtil.getRoleTypeByRoleName().get(role.getName())+" where roleId="+role.getRoleId());
			}
		}
	}
}
