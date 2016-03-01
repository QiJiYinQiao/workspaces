package test.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import test.TestBase;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Role;
import com.bpms.model.RoleAndPermission;
import com.bpms.model.UserAndRole;
import com.bpms.service.RoleAndPermissionService;
import com.bpms.service.RoleService;
import com.bpms.service.UserAndRoleService;
import com.bpms.service.UserService;
import com.bpms.util.Collections;

/**
 * 作用：底数据程序。
 * 
 * 财富端特有角色汇总：	
  "LiCaiJingLi",                //“理财经理”角色（）              
  "XiaoKeJingLi",               //“销客经理”角色
  "XiaoKeZhuanYuan",            //“销客专员”角色
  "XiaoKeZhuGuan",              //销客主管      
  "BuMenZhuLi",                 //部门助理
  
  "CaiFuDuanTuanDuiJingLi",     //财富端团队经理
  "CaiFuDuanDaTuanJingLi",      //财富端大团经理
  "CaiFuDuanYingYeBuJingLi",    //财富端营业部经理     
  "CaiFuDuanChengShiJingLi",    //财富端城市经理
  "CaiFuDuanQuYuZongJian",      //财富端区域总监
  "CaiFuDuanJieSuanZhuanYuan",  //财富端结算专员
  "CaiFuDuanShuJuZhuanYuan",    //财富端数据专员
  
  "ZongJingLi"        //财富端的总经理	  
		 
	财富端角色调整零碎：
	数据专员：宁阳
	 
 *
 */
public class InitData4InvestBusinessVer1 extends TestBase{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAndRoleService userAndRoleService;
	
	@Autowired
	private RoleAndPermissionService roleAndPermissionService;
	
	@Autowired
	private BaseDAO<InitData4InvestBusinessVer1> baseDAO;
		
	
	/**
	 * 删除“第一版”底数据中的冲突角色。
	 * 删除投资业务中，原来角色绑定的权限，这些角色包含与借款端冲突的角色。
	 * @Title: deleteAllInvestRelatedRoleAndPermission 
	 * @Description: TODO
	 * @param 
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2016年1月26日 下午1:07:55
	 * @throws
	 */
	@Transactional
	public void deleteAllPreviousInvestRelatedRoleAndPermission(){
		String[] allInvestRoleCodes = {
				"LiCaiJingLi", 
				"TuanDuiJingLi", 
				"DaTuanJingLi",
				"YingYeBuJingLi",
				"ChengShiJingLi",
				"QuYuZongJian",
				"QuYuJingLi",
				"ZongJingLi",
				"BuMenZhuLi",
				"XiaoKeZhuanYuan",
				"ShuJuZhuanYuan", 
				"JieSuanZhuanYuan", 
				"XiaoKeJingLi", 
				"XiaoKeZhuGuan"				
		};
		for(String roleCode : allInvestRoleCodes){			
			roleAndPermissionService.delRoleAndPermissionOfInvestBusinessByRoleCode(roleCode);
		}
	}
	
	
	/**
	 * 添加“财富端特有的角色”。
	 * 理财经理，销客经理，销客专员，销客主管，部门助理等角色没有改动，有待手工修改。 
	 * @Title: addAllRolesNeeded4InvestBusiness 
	 * @Description: TODO
	 * @param 
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2016年2月1日 上午10:01:29
	 * @throws
	 */
	@Transactional
	public void addAllRolesNeeded4InvestBusiness() {
		
		String[] cFOnlyRoleCode = {				
								  "CaiFuDuanTuanDuiJingLi",     //财富端团队经理
				                  "CaiFuDuanDaTuanJingLi",      //财富端大团经理
				                  "CaiFuDuanYingYeBuJingLi",    //财富端营业部经理     
				                  "CaiFuDuanChengShiJingLi",       //财富端城市经理
				                  "CaiFuDuanQuYuZongJian",       //财富端区域总监
				                  "CaiFuDuanJieSuanZhuanYuan",   //财富端结算专员
				                  "CaiFuDuanShuJuZhuanYuan"       //财富端数据专员				                  				                  
		                         }; 		

		// 创建新的角色，这些角色与“借款端”相冲突的（即，借款端也具有相同的角色，从而可以操作财富端的业务，但是，这是不被允许的）。
		// 财富端与借款端冲突的角色：
		// 团队经理，大团经理，营业部经理，城市经理，区域经理，区域总监，结算专员，数据专员
		for(String roleCode : cFOnlyRoleCode){
			deleteAllRelationAndRoleObj(roleCode);
			addRoleByRoleCode(roleCode);	
		}									
	}		
	
	/**
	 * 给“所有财富端相关”的角色添加权限
	 * @Title: addPermissionsToRoleVersion2 
	 * @Description: TODO
	 * @param 
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2016年2月1日 上午10:02:16
	 * @throws
	 */
	@Transactional
	public void addPermissionsToRoleVersion2() {
				
		try {			
			String[] allInvestRoles = {
					  "LiCaiJingLi",                //“理财经理”角色              
					  "XiaoKeJingLi",               //“销客经理”角色
					  "XiaoKeZhuanYuan",            //“销客专员”角色
					  "XiaoKeZhuGuan",              //销客主管      
					  "BuMenZhuLi",                 //部门助理
					  
					  "CaiFuDuanTuanDuiJingLi",     //财富端团队经理
	                  "CaiFuDuanDaTuanJingLi",      //财富端大团经理
	                  "CaiFuDuanYingYeBuJingLi",    //财富端营业部经理     
	                  "CaiFuDuanChengShiJingLi",    //财富端城市经理
	                  "CaiFuDuanQuYuZongJian",      //财富端区域总监
	                  "CaiFuDuanJieSuanZhuanYuan",  //财富端结算专员
	                  "CaiFuDuanShuJuZhuanYuan",    //财富端数据专员
	                  
	      			  "ZongJingLi"        //财富端的总经理	    			  
               };			
									
			String permissionIDsCommon[] = {"110","104", "150","151", "152", "235", "236", "237"};
			String permissionIDs4LiCaiJingLi[] = {"148","149", "238"};
			String permissionID4XiaoKeZhuGuan = "153";
			String permissionID4XiaoKeJingLi = "147"; 					
			
			for(String roleCode : allInvestRoles){  //遍历 财富端所有角色开始
				
				Role roleInst = roleService.findRoleByCode(roleCode);
				
				if( null != roleInst){
					// 如果角色是“理财经理”，理财经理除了通用权限（“投资申请任务办理、投资赎回任务办理”）外，
					// 还应当具有“客户管理(148)”,"投资申请管理(149)", "投资赎回管理(238)"的功能。					
					if(roleCode.equalsIgnoreCase("LiCaiJingLi")){
						for(String permissionId : permissionIDs4LiCaiJingLi){							
							System.out.println("保存  理财经理 特有权限菜单ID： "+permissionId);
							RoleAndPermission roleAndPermission  = new RoleAndPermission(Integer.parseInt(permissionId), roleInst.getRoleId(), "A", new Date(), new Date(), 1, 1);
							roleAndPermissionService.saveRoleAndPermissions(roleAndPermission);	
						}						
					}

					//如果角色是“销客经理”，销客经理还应当具有“理财产品(147)”
					if(roleCode.equalsIgnoreCase("XiaoKeJingLi")){
						System.out.println("保存 销客经理 特有权限菜单ID： 147，管理理财产品的权限");
						RoleAndPermission roleAndPermission  = new RoleAndPermission(Integer.parseInt(permissionID4XiaoKeJingLi), roleInst.getRoleId(), "A", new Date(), new Date(), 1, 1);
						roleAndPermissionService.saveRoleAndPermissions(roleAndPermission);	
					}					
										
					//如果角色是“销客主管”，销客经理具有债权匹配的权限，“permissionId”为153
					if(roleCode.equalsIgnoreCase("XiaoKeZhuGuan")){
							System.out.println("保存  销客主管 特有权限菜单ID： 153");
							RoleAndPermission roleAndPermission  = new RoleAndPermission(Integer.parseInt(permissionID4XiaoKeZhuGuan), roleInst.getRoleId(), "A", new Date(), new Date(), 1, 1);
							roleAndPermissionService.saveRoleAndPermissions(roleAndPermission);	
					}					
					
					//除了与当前角色相关的特殊权限外，当前角色还应当具有其他通用权限。
					for(String permissionId : permissionIDsCommon){													
						System.out.println("保存通用 权限菜单ID： "+ permissionId);
						RoleAndPermission roleAndPermission  = new RoleAndPermission(Integer.parseInt(permissionId), roleInst.getRoleId(), "A", new Date(), new Date(), 1, 1);
						roleAndPermissionService.saveRoleAndPermissions(roleAndPermission);																			
					}					
					logger.info("++++++++++++++++++++++++++++++++++++++++++");
					logger.info("   " + roleInst.getName() + "  的权限配置成功。");
					logger.info("++++++++++++++++++++++++++++++++++++++++++");										

				}else{					
					logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
					logger.info("   角色" + roleCode + "不存在，不能为其分配权限");
					logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");										
				}
				
			} // --- 遍历 财富端所有角色结束
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
	

	/**
	 * 将财富端的专属角色付给相关财富端的用户
	 * @Title: addCFOnlyRolesToCFUsers 
	 * @Description: TODO
	 * @param 
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2016年1月27日 下午2:18:08
	 * @throws
	 */
	@Transactional
	public void testAddCFOnlyRolesToCFUsers() {
		
		
		String[] cFRoleCodes = {
				"CaiFuDuanTuanDuiJingLi",     //财富端团队经理
				"CaiFuDuanDaTuanJingLi",      //财富端大团经理
				"CaiFuDuanYingYeBuJingLi",    //财富端营业部经理     
				"CaiFuDuanChengShiJingLi",    //财富端城市经理
				"CaiFuDuanQuYuZongJian"      //财富端区域总监
				//"CaiFuDuanJieSuanZhuanYuan",  //财富端结算专员
				//"CaiFuDuanShuJuZhuanYuan",    //财富端数据专员				
		}; 
		
		
		for(String roleCode : cFRoleCodes){
			//(1)给t_users中，具有“团队经理”角色的用户，添加角色“财富端团队经理”，共316人
			//(2)给t_users中，具有“大团经理”角色的用户，添加角色“财富端大团经理”，共12人
			//(3)给t_users中，具有“营业部经理”角色的用户，添加角色“财富端营业部经理”，共45人		
			//(4)给t_users中，具有“城市经理”角色的用户，添加角色“财富端城市经理”，共22人		
			//(5)给t_users中，具有“区域总监”角色的用户，添加角色“财富端区域总监”，共1人
			//(6)给t_users中，具有“结算专员”角色的用户，不要CF过滤，添加角色“财富端结算专员”，共X人，待定（先不赋予权限，结算专员是分CF端和JK端的）
			//(7)给t_users中，具有“数据专员”角色的用户，不要CF过滤，添加角色“财富端数据专员”，共X人，待定（先不赋予权限，数据专员是分CF端和JK端的）					
			addCaiFuRoleToUser(roleCode);
		}						
	}
	
	@Transactional
	public void addCaiFuRoleToUser(String cfRoleCode) {
		
		//获取新的角色
		Role cfNewRoleObj = this.roleService.findRoleByCode(cfRoleCode);		

		//获取原来的角色
		String oldRoleCode = cfRoleCode.substring(9);						
		Role oldRoleObj = this.roleService.findRoleByCode(oldRoleCode);
		
		if( null != oldRoleObj && null != cfNewRoleObj){
			
			String odlRoleName = oldRoleObj.getName();
			
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT t_u.user_id AS 'userId',  t_u.USER_ENAME AS 'userEName',t_u.USER_NAME AS 'userName' ");
			sql.append("FROM t_users t_u LEFT JOIN t_organization t2 ON t2.ORGANIZATION_ID = t_u.organization_id LEFT JOIN t_user_and_role t_u_r ON t_u_r.USER_ID = t_u.USER_ID LEFT JOIN t_role t_r  ON t_r.ROLE_ID = t_u_r.ROLE_ID ");
			sql.append("WHERE t_r.name LIKE '"+odlRoleName+"' AND t2.myid='CF'");
			sql.append("ORDER BY t_u.user_id ASC");
			
			List<Object> tmpResults = this.baseDAO.findBySQL(sql.toString());
			
			if(Collections.listIsNotEmpty(tmpResults)){
				
				for(int i=0; i<tmpResults.size(); i++){
					Object[] obj = (Object[]) tmpResults.get(i);
					String tmpUserId = ( obj[0]==null?"":String.valueOf(obj[0]) );//用户ID
					int userId = Integer.parseInt(tmpUserId);
					UserAndRole userAndRole = new UserAndRole( cfNewRoleObj.getRoleId(), userId, "A", new Date(), new Date(), 1, 1);
					this.userAndRoleService.saveUserAndRoles(userAndRole);								
				}
				
				logger.info("++++++++++++++++++++++++++++++++++++++++++++");
				logger.info("    新的角色："+cfNewRoleObj.getName()+"  添加完毕。");
				logger.info("++++++++++++++++++++++++++++++++++++++++++++");				
								
			}else{
				logger.info("++++++++++++++++++++++++++++++++++++++++++++");
				logger.info("   查询老的角色的结果有问题   ");
				logger.info("++++++++++++++++++++++++++++++++++++++++++++");
			}
						
		}else{
			logger.info("角色"+oldRoleCode+"不存在，没有找到");
		}
	}	
	
	
	
	// ==========================================================================
	// ==========================================================================
	// ==========================================================================

	
	
	
	@Transactional
	public void deleteRoleObjAndEachPermission(String roleCode){		
		Role role = roleService.findRoleByCode(roleCode);
		if(null != role){			
			System.out.println("role.getRoleId() = "+role.getRoleId());
			//1. 删除角色权限关系表中，与当前角色相关的权限数据。		
			roleAndPermissionService.delRoleAndPermissionByRoleCode(role);
//			roleAndPermissionService.delRoleAndPermissionOfInvestBusinessByRoleCode(roleCode);
			
			//2. 删除角色
			roleService.delRole(role);
			
			logger.info("======================================================");
			logger.info("----------------->  角色" + role.getName() + "和相关权限已经删除成功了。");
			logger.info("======================================================");
		}else{
			logger.info("=======================================================");
			logger.info("----------------->  角色" + roleCode + "，不存在，为NULL，不能执行相关角色和权限的删除。");
			logger.info("=======================================================");
		}		
	}
	
	
	//删除“角色与权限”的关系，“用户与角色的”关系，以及“角色对象”。
	@Transactional
	public void deleteAllRelationAndRoleObj(String roleCode){		
		Role role = roleService.findRoleByCode(roleCode);
		if(null != role){
			//如果是理财经理(财富端特有的角色)
			roleAndPermissionService.delRoleAndPermissionByRoleCode(role);   //删除关系：角色与权限的关系
			userAndRoleService.deleteUserAndRoleByRole(role);                //删除关系：用户与角色的关系
			this.roleService.delRole(role);                                  //删除角色  
			
			logger.info("======================================================");
			logger.info("----------------->  角色" + role.getName() + "和相关权限已经删除成功了。");
			logger.info("======================================================");
		}else{
			logger.info("=======================================================");
			logger.info("----------------->  角色" + roleCode + "，不存在，为NULL，不能执行相关角色和权限的删除。");
			logger.info("=======================================================");
		}		
	}
	
	@Transactional
	private void addRoleByRoleCode(String roleCode) {
		
		Role roleObj = this.roleService.findRoleByCode(roleCode);
		
		// 如果角色不存在，则创建新的角色
		if( null == roleObj){
			// 根据roleCode，创建新的角色。
			
			//判断新添加角色的中文名称
			String roleChineseName = null;
			if(roleCode.equalsIgnoreCase("LiCaiJingLi")){
				roleChineseName = "理财经理";
			}else if(roleCode.equalsIgnoreCase("XiaoKeJingLi")){
				roleChineseName = "销客经理";
			}else if(roleCode.equalsIgnoreCase("XiaoKeZhuanYuan")){
				roleChineseName = "销客专员";
			}else if(roleCode.equalsIgnoreCase("XiaoKeZhuGuan")){
				roleChineseName = "销客主管";
			}else if(roleCode.equalsIgnoreCase("CaiFuDuanTuanDuiJingLi")){
				roleChineseName = "财富端团队经理";
			}else if(roleCode.equalsIgnoreCase("CaiFuDuanDaTuanJingLi")){
				roleChineseName = "财富端大团经理";
			}else if(roleCode.equalsIgnoreCase("CaiFuDuanYingYeBuJingLi")){
				roleChineseName = "财富端营业部经理";
			}else if(roleCode.equalsIgnoreCase("CaiFuDuanChengShiJingLi")){
				roleChineseName = "财富端城市经理";
			}else if(roleCode.equalsIgnoreCase("CaiFuDuanQuYuZongJian")){
				roleChineseName = "财富端区域总监";
			}else if(roleCode.equalsIgnoreCase("CaiFuDuanJieSuanZhuanYuan")){
				roleChineseName = "财富端结算专员";
			}else if(roleCode.equalsIgnoreCase("CaiFuDuanShuJuZhuanYuan")){
				roleChineseName = "财富端数据专员";
			}						
			
			Role newRole = new Role(roleCode, roleChineseName, "这是"+roleChineseName+"的描述", "A", new Date(), new Date(), Integer.parseInt("1"), Integer.parseInt("1"), Integer.parseInt("1"));
			
			this.roleService.saveRole(newRole);
			
			logger.info("=======================================");
			logger.info("   "+roleChineseName+"的角色创建成功。");
			logger.info("=======================================");						
		}else{
			logger.info("=======================================");
			logger.info("   "+roleCode+"的角色已经存在，不用创建了。");
			logger.info("=======================================");									
		}
	}	
	

	//2-、删除“财富端特有的角色”
	@Transactional
	private void delAllRolesNeeded4InvestBusiness() {
		String[] allInvestRoleCodes = {
				  "CaiFuDuanTuanDuiJingLi",     //财富端团队经理
                "CaiFuDuanDaTuanJingLi",      //财富端大团经理
                "CaiFuDuanYingYeBuJingLi",    //财富端营业部经理     
                "CaiFuDuanChengShiJingLi",       //财富端城市经理
                "CaiFuDuanQuYuZongJian",       //财富端区域总监
                "CaiFuDuanJieSuanZhuanYuan",   //财富端结算专员
                "CaiFuDuanShuJuZhuanYuan"       //财富端数据专员                                
               }; 		
		for(String roleCode : allInvestRoleCodes){			
			deleteAllRelationAndRoleObj(roleCode);
		}		
		
	}	
	

	@Test
	@Transactional
	public void testSaveInitData() throws java.lang.Exception{		
		// ////////////////////////////////////////////
		// 第二次铺垫底数据： 在李达的基础上修改的
		// ////////////////////////////////////////////		
		
		//1、删除投资业务中，删除“第一版”底数据中的冲突角色
		//原来角色绑定的权限，这些角色包含与借款端冲突的角色。
//		deleteAllPreviousInvestRelatedRoleAndPermission();
		
//		//2、添加“财富端特有的角色”。	
//		addAllRolesNeeded4InvestBusiness();		
//		
//		//3、给“所有财富端相关”的角色添加权限		
//		addPermissionsToRoleVersion2();
		
		// 4. 将财富端的专属角色付给相关财富端的用户
//		testAddCFOnlyRolesToCFUsers();		
		
	}
}
