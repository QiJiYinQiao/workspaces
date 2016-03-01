package test.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import test.TestBase;

import com.bpms.dao.BaseDAO;
import com.bpms.model.InvestOrder;
import com.bpms.model.Role;
import com.bpms.model.RoleAndPermission;
import com.bpms.model.UserAndRole;
import com.bpms.model.Users;
import com.bpms.service.RoleAndPermissionService;
import com.bpms.service.RoleService;
import com.bpms.service.UserAndRoleService;
import com.bpms.service.UserService;
import com.bpms.util.ChineseToPinYinUtil;
import com.bpms.util.Collections;
import com.bpms.util.ReadExcel;

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
public class InitData4InvestBusinessVer0 extends TestBase{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAndRoleService userAndRoleService;
	
	@Autowired
	private RoleAndPermissionService roleAndPermissionService;
	
	@Autowired
	private BaseDAO<InitData4InvestBusinessVer0> baseDAO;
	
	private static Map<String, Users> liCaiJingLiMap = new HashMap<String, Users>();
	
	private String excelPath4None263Users = "/OA系统用户数据登记表（263未见用户）-.xls";
	
	private String excelPath4AllRoleName4Invest = "/财富端涉及角色.xlsx";
	
	/**
	 * 给每个角色
	 * @Title: testAddPermissionsToRole 
	 * @Description: TODO
	 * @param 
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2016年1月13日 下午8:23:27
	 * @throws
	 */
//	@Transactional
//	public void addPermissionsToRole() {
//		// 权限相关SQL语句   
//		// select * from t_permission where name like '%财富业务管理%'     -- permission_id = 104
//		// select * from t_permission where name like '%客户管理%'        -- permission_id = 148, pid = 104		
//		// “业务管理”的菜单权限Id(根菜单)       --->   110
//		// “财富业务管理”的菜单权限Id       --->   104
//		// “理财产品”的菜单权限Id                --->   147
//		// ”客户管理 ”的菜单权限Id               --->   148
//		// “投资申请管理”的菜单权限Id             --->   149
//		// “投资申请任务办理”的菜单权限Id           --->   150
//		// “待办任务”的菜单权限Id                --->   151
//		// “受理任务”的菜单权限Id                --->   152
//		// “投资赎回管理”的菜单权限Id             --->   238
//		// “投资赎回任务办理”的菜单权限Id           --->   235
//		// “待办任务”的菜单权限Id                --->   236
//		// “受理任务”的菜单权限Id                --->   237				
//		// “债权匹配”的菜单权限Id                --->   153				
////		String permissionIDs[] = {"110", "104","147","148","149","150","151", "152", "238", "235", "236", "237", "153"};
//		
//		try {
//			String permissionIDsCommon[] = {"110","104", "150","151", "152", "235", "236", "237"};
//			String permissionIDs4LiCaiJingLi[] = {"147","148","149", "238"};
//			String permissionID4XiaoKeZhuGuan = "153";
//			String resource = InitData4Test.class.getResource("").getPath() + excelPath4AllRoleName4Invest;
//			Workbook oneWorkbookInst = ReadExcel.createworkbook(resource.substring(1));
//			Sheet sheetAt = oneWorkbookInst.getSheetAt(0);
//			Row row = null;
//			int rowIndex = 1;
//			do { // 循环遍历RodeCode -- 开始 
//				row = sheetAt.getRow(rowIndex);
//				if( null != row ){
//					Cell cell = row.getCell(0);
//					if( StringUtils.isNotBlank(cell.getStringCellValue()) ){
//						
//						// 获取角色的中文名字	
//						String roleNameChinese = cell.getStringCellValue();
//						// 获取角色的汉语拼音，即RoleCode	
//						String roleCode = ChineseToPinYinUtil.getUpEname1(roleNameChinese);					
//						
//						System.out.println("No."+rowIndex+",   ChinseName = "+roleNameChinese + ",   RodeCode = "+roleCode);
//							
//						Role roleInst = roleService.findRoleByCode(roleCode);
//						
//						// 如果角色是“理财经理”，理财经理除了通用权限（“投资申请任务办理、投资赎回任务办理”）外，
//						// 还应当具有“理财产品(147)”，“客户管理(148)”,"投资申请管理(149)", "投资赎回管理(238)"的功能。					
//						if(roleCode.equalsIgnoreCase("LiCaiJingLi")){
//							for(String permissionId : permissionIDs4LiCaiJingLi){							
//								System.out.println("保存  理财经理 特有权限菜单ID： "+permissionId);
//								RoleAndPermission roleAndPermission  = new RoleAndPermission(Integer.parseInt(permissionId), roleInst.getRoleId(), "A", new Date(), new Date(), 1, 1);
//								roleAndPermissionService.saveRoleAndPermissions(roleAndPermission);	
//							}						
//						}
//						
//						//如果角色是“销客主管”，销客经理具有债券匹配的权限，“permissionId”为153
//						if(roleCode.equalsIgnoreCase("XiaoKeZhuGuan")){
//								System.out.println("保存  销客主管 特有权限菜单ID： 153");
//								RoleAndPermission roleAndPermission  = new RoleAndPermission(Integer.parseInt(permissionID4XiaoKeZhuGuan), roleInst.getRoleId(), "A", new Date(), new Date(), 1, 1);
//								roleAndPermissionService.saveRoleAndPermissions(roleAndPermission);	
//						}					
//						
//						//保留其他通用权限
//						for(String permissionId : permissionIDsCommon){													
//							System.out.println("保存通用 权限菜单ID： "+ permissionId);
//							RoleAndPermission roleAndPermission  = new RoleAndPermission(Integer.parseInt(permissionId), roleInst.getRoleId(), "A", new Date(), new Date(), 1, 1);
//							roleAndPermissionService.saveRoleAndPermissions(roleAndPermission);																			
//						}
//						
//						logger.info("++++++++++++++++++++++++++++++++++++++++++");
//						logger.info("   " + roleNameChinese + "  的权限配置成功。");
//						logger.info("++++++++++++++++++++++++++++++++++++++++++");						
//						
//						++rowIndex;				
//					} 			
//				} else {
//					break;
//				}
//				
//			} while( null != row );   // 循环遍历RodeCode -- 结束 		
//			
//			
//		} catch ( Exception  e) {
//			e.printStackTrace();
//		}		
//	}

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
	 * 给每个用户添加角色
	 * @Title: addRoleToUser 
	 * @Description: TODO
	 * @param @throws java.lang.Exception
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2016年1月13日 下午8:26:47
	 * @throws
	 */
	@Transactional
	public void addRoleToUser() throws java.lang.Exception {
		String resource = InitData4InvestBusinessVer0.class.getResource("").getPath() + excelPath4None263Users;
		Workbook oneWorkbookInst = ReadExcel.createworkbook(resource.substring(1));
		int counter4LiCaiJingLi = 0;
		int counter4TuanDuiJingLi = 0;
		int counter4ChengShiJingLi = 0;
		int counter4QuYuJingLi = 0;
		int counter4DaTuanJingLi = 0;
		int counter4YingYeBuJingLi = 0;
		int counter4QuYuZongJian = 0;
		int counter4BuMenZhuLi = 0;
		
		
		Sheet sheet = oneWorkbookInst.getSheetAt(0);
		int rowIndex = 1;
		Row oneRow = null;
		
		do{
			oneRow = sheet.getRow(rowIndex);
			if( oneRow != null ){				
				
				Cell cell4Name = oneRow.getCell(0);
				Cell cell4MobilePhone = oneRow.getCell(3);								
				Cell cell4RoleName = oneRow.getCell(5);
				Cell cell4Division = oneRow.getCell(6);							
								
				//获取 相关数据的汉语拼音，或者转换后的字符串。
				// 获取用户名
				String username = null;
				String userNameChinese = null;
				if(StringUtils.isNotBlank(cell4Name.getStringCellValue())){
					userNameChinese = cell4Name.getStringCellValue();
					username = ChineseToPinYinUtil.getUpEname1(cell4Name.getStringCellValue());
				}
				
				// 获取电话
				String mobilePhone = null;
				if(  null != cell4MobilePhone ){					
					DecimalFormat df = new DecimalFormat("0");						
					try {		
						// 将电话号码，由科学计数法转变为字符串。
						mobilePhone = df.format(cell4MobilePhone.getNumericCellValue());													
					} catch (Exception e) {
						mobilePhone = cell4MobilePhone.getStringCellValue();
					}					
				}
				
				// 获取角色名
				String roleName  = null;
				if(  StringUtils.isNotBlank(cell4RoleName.getStringCellValue()) ){
					roleName = ChineseToPinYinUtil.getUpEname1(cell4RoleName.getStringCellValue());
				}

				// 获取部门名称
				String divisionName = null;								
				if(  StringUtils.isNotBlank(cell4Division.getStringCellValue()) ){
					divisionName = ChineseToPinYinUtil.getUpEname1(cell4Division.getStringCellValue());
				}
				
				// 给员工配置“理财经理”角色，这些员工在Excel文件中职位为“财富管理中心”的“客户经理”、“客户顾问”
				if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(divisionName)){					
					// “财富管理中心”的“客户经理”或者“财富管理中心”的“客户顾问”					
					if( divisionName.contains("CaiFuGuanLiZhongXin") && 
					  ( roleName.contains("KeHuJingLi") || roleName.contains("KeHuGuWen") )  ){						
						// 如果值不为空，则说明当前用户已经存在了，
						Users user = userService.findUserByNameAndPoneAndEmail(userNameChinese, mobilePhone, null);
						if(null != user){
							Role role = roleService.findRoleByCode("LiCaiJingLi");
							if(null != role){								
								UserAndRole userAndRole = new  UserAndRole( role.getRoleId(), user.getUserId(), "A", new Date(), new Date(), 1, 1);							
								// 判断用户和角色的关系没有存在过才进行编写
								if(null == userAndRoleService.isExistUserAndRoles(userAndRole)){
									userAndRoleService.saveUserAndRoles(userAndRole);
								}							
							}else{
								logger.info("角色理财经理不存在!!!\n");
							}
						}else{
							// 如果用户不存在进行记录日志
							logger.info(("行:"+(rowIndex+1)+"\t姓名:"+userNameChinese+"  电话:"+ mobilePhone+"  没有找到该用户信息!!!!\n"));
						}																										
						++counter4LiCaiJingLi;							
					}
				}
				
//				// 给员工配置“团队经理”的角色
				if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(divisionName)){
					if( divisionName.contains("CaiFuGuanLiZhongXin") && roleName.contains("TuanDuiJingLi")  ){						
						// 如果值不为空，则说明当前用户已经存在了，
						Users user = userService.findUserByNameAndPoneAndEmail(userNameChinese, mobilePhone, null);
						if(null != user){
							Role role = roleService.findRoleByCode("CaiFuDuanTuanDuiJingLi");
							if( null != role){
								UserAndRole userAndRole = new  UserAndRole( role.getRoleId(), user.getUserId(), "A", new Date(), new Date(), 1, 1);								
								// 判断用户和角色的关系没有存在过才进行编写
								if(null == userAndRoleService.isExistUserAndRoles(userAndRole)){
									userAndRoleService.saveUserAndRoles(userAndRole);
								}								
							}else{
								logger.info("角色财富端团队经理不存在!!!\n");
							}
						}else{
							// 如果用户不存在进行记录日志
							logger.info(("行:"+(rowIndex+1)+"\t姓名:"+userNameChinese+"  电话:"+ mobilePhone+"  没有找到该用户信息!!!!\n"));
						}
						++ counter4TuanDuiJingLi;
					}
				}				
//				
//				// 给员工配置“大团经理”的角色
				if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(divisionName)){
					if( divisionName.contains("CaiFuGuanLiZhongXin") && roleName.contains("DaTuanJingLi")  ){						
						// 如果值不为空，则说明当前用户已经存在了，
						Users user = userService.findUserByNameAndPoneAndEmail(userNameChinese, mobilePhone, null);
						if(null != user){
							Role role = roleService.findRoleByCode("CaiFuDuanDaTuanJingLi");
							
							if( null != role ){
								UserAndRole userAndRole = new  UserAndRole( role.getRoleId(), user.getUserId(), "A", new Date(), new Date(), 1, 1);
								
								// 判断用户和角色的关系没有存在过才进行编写
								if(null == userAndRoleService.isExistUserAndRoles(userAndRole)){
									userAndRoleService.saveUserAndRoles(userAndRole);
								}								
							}else{
								logger.info("角色财富端大团经理不存在!!!\n");
							}
						}else{
							// 如果用户不存在进行记录日志
							logger.info(("行:"+(rowIndex+1)+"\t姓名:"+userNameChinese+"  电话:"+ mobilePhone+"  没有找到该用户信息!!!!\n"));
						}
						++counter4DaTuanJingLi;
					}
				}
				
				
//				// 给员工配置“营业部经理”的角色
				if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(divisionName)){
					if( divisionName.contains("CaiFuGuanLiZhongXin") && roleName.contains("YingYeBuJingLi")  ){						
						// 如果值不为空，则说明当前用户已经存在了，
						Users user = userService.findUserByNameAndPoneAndEmail(userNameChinese, mobilePhone, null);
						if(null != user){
							Role role = roleService.findRoleByCode("CaiFuDuanYingYeBuJingLi");
							
							if(null != role){
								UserAndRole userAndRole = new  UserAndRole( role.getRoleId(), user.getUserId(), "A", new Date(), new Date(), 1, 1);
								
								// 判断用户和角色的关系没有存在过才进行编写
								if(null == userAndRoleService.isExistUserAndRoles(userAndRole)){
									userAndRoleService.saveUserAndRoles(userAndRole);
								}								
							}else{
								logger.info("角色财富端营业部经理不存在!!!\n");
							}
							
						}else{
							// 如果用户不存在进行记录日志
							logger.info(("行:"+(rowIndex+1)+"\t姓名:"+userNameChinese+"  电话:"+ mobilePhone+"  没有找到该用户信息!!!!\n"));
						}
						++counter4YingYeBuJingLi;
					}
				}				

//				// 给员工配置“城市经理”的角色
				if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(divisionName)){
					if( divisionName.contains("CaiFuGuanLiZhongXin") && roleName.contains("ChengShiJingLi")  ){						
						// 如果值不为空，则说明当前用户已经存在了，
						Users user = userService.findUserByNameAndPoneAndEmail(userNameChinese, mobilePhone, null);
						if(null != user){
							Role role = roleService.findRoleByCode("CaiFuDuanChengShiJingLi");
							
							if( null != role ){
								UserAndRole userAndRole = new  UserAndRole( role.getRoleId(), user.getUserId(), "A", new Date(), new Date(), 1, 1);
								
								// 判断用户和角色的关系没有存在过才进行编写
								if(null == userAndRoleService.isExistUserAndRoles(userAndRole)){
									userAndRoleService.saveUserAndRoles(userAndRole);
								}								
							}else{
								logger.info("角色财富端城市经理不存在!!!\n");
							}
							
							
						}else{
							// 如果用户不存在进行记录日志
							logger.info(("行:"+(rowIndex+1)+"\t姓名:"+userNameChinese+"  电话:"+ mobilePhone+"  没有找到该用户信息!!!!\n"));
						}
						++counter4ChengShiJingLi;
					}
				}	
				
				
//				// 给员工配置“区域经理”的角色
				if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(divisionName)){
					if( divisionName.contains("CaiFuGuanLiZhongXin") && roleName.contains("QuYuJingLi")  ){						
						// 如果值不为空，则说明当前用户已经存在了，
						Users user = userService.findUserByNameAndPoneAndEmail(userNameChinese, mobilePhone, null);
						if(null != user){
							Role role = roleService.findRoleByCode("CaiFuDuanQuYuJingLi");
							
							if( null != role ){
								UserAndRole userAndRole = new  UserAndRole( role.getRoleId(), user.getUserId(), "A", new Date(), new Date(), 1, 1);
								
								// 判断用户和角色的关系没有存在过才进行编写
								if(null == userAndRoleService.isExistUserAndRoles(userAndRole)){
									userAndRoleService.saveUserAndRoles(userAndRole);
								}								
							}else{
								logger.info("角色财富端区域经理不存在!!!\n");
							}
							
						}else{
							// 如果用户不存在进行记录日志
							logger.info(("行:"+(rowIndex+1)+"\t姓名:"+userNameChinese+"  电话:"+ mobilePhone+"  没有找到该用户信息!!!!\n"));
						}
						++ counter4QuYuJingLi;
					}					
				}
//				
//				
//				// 给员工配置“区域总监”的角色
				if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(divisionName)){
					if( divisionName.contains("CaiFuGuanLiZhongXin") && roleName.contains("QuYuZongJian")  ){						
						// 如果值不为空，则说明当前用户已经存在了，
						Users user = userService.findUserByNameAndPoneAndEmail(userNameChinese, mobilePhone, null);
						if(null != user){
							Role role = roleService.findRoleByCode("CaiFuDuanQuYuZongJian");
							
							if( null != role ){
								UserAndRole userAndRole = new  UserAndRole( role.getRoleId(), user.getUserId(), "A", new Date(), new Date(), 1, 1);
								
								// 判断用户和角色的关系没有存在过才进行编写
								if(null == userAndRoleService.isExistUserAndRoles(userAndRole)){
									userAndRoleService.saveUserAndRoles(userAndRole);
								}								
							}else{
								logger.info("角色财富端区域总监不存在!!!\n");
							}
							
						}else{
							// 如果用户不存在进行记录日志
							logger.info(("行:"+(rowIndex+1)+"\t姓名:"+userNameChinese+"  电话:"+ mobilePhone+"  没有找到该用户信息!!!!\n"));
						}
						++counter4QuYuZongJian;
					}					
				}				
//				
//				// 给员工配置“部门助理”的角色
				if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(divisionName)){
					if( divisionName.contains("CaiFuGuanLiZhongXin") && roleName.contains("BuMenZhuLi")  ){						
						// 如果值不为空，则说明当前用户已经存在了，
						Users user = userService.findUserByNameAndPoneAndEmail(userNameChinese, mobilePhone, null);
						if(null != user){
							Role role = roleService.findRoleByCode("BuMenZhuLi");
							if( null != role ){
								UserAndRole userAndRole = new  UserAndRole( role.getRoleId(), user.getUserId(), "A", new Date(), new Date(), 1, 1);
								
								// 判断用户和角色的关系没有存在过才进行编写
								if(null == userAndRoleService.isExistUserAndRoles(userAndRole)){
									userAndRoleService.saveUserAndRoles(userAndRole);
								}								
							}else{
								logger.info("角色部门助理不存在!!!\n");
							}
							
						}else{
							// 如果用户不存在进行记录日志
							logger.info(("行:"+(rowIndex+1)+"\t姓名:"+userNameChinese+"  电话:"+ mobilePhone+"  没有找到该用户信息!!!!\n"));
						}
						++counter4BuMenZhuLi;
					}
				}				
				
				System.out.println(username+", "+mobilePhone+", "+roleName+", "+divisionName);				
																
				// 若数据正确，则获取下一行。
				++rowIndex;
			}
		} while(oneRow != null);
		
		System.out.println("=======================================================================");
		System.out.println(" 共 "+(rowIndex-1)+"行。");
		System.out.println(" 统计“财富管理中心”的“理财经理”的个数： " + counter4LiCaiJingLi);
		System.out.println(" 统计“财富管理中心”的“团队经理”的个数： " + counter4TuanDuiJingLi);
		System.out.println(" 统计“财富管理中心”的“大团经理”的个数： " + counter4DaTuanJingLi);
		System.out.println(" 统计“财富管理中心”的“营业部经理”的个数： " + counter4YingYeBuJingLi);
		System.out.println(" 统计“财富管理中心”的“区域总监”的个数： " + counter4QuYuZongJian);
		System.out.println(" 统计“财富管理中心”的“城市经理”的个数： " + counter4ChengShiJingLi);
		System.out.println(" 统计“财富管理中心”的“城市经理”的个数： " + counter4BuMenZhuLi);
		System.out.println(" 统计“财富管理中心”的“区域经理”的个数： " + counter4QuYuJingLi);
		System.out.println("=======================================================================");
	}

	
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
	
	//添加“财富端特有的角色”。
	// 理财经理，销客经理，销客专员，销客主管，部门助理等角色没有改动，有待手工修改。
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
			//待定：(6)给t_users中，具有“结算专员”角色的用户，不要CF过滤，添加角色“财富端结算专员”，共X人，待定（先不赋予权限，结算专员是分CF端和JK端的）
			//待定：(6)给t_users中，具有“数据专员”角色的用户，不要CF过滤，添加角色“财富端数据专员”，共X人，待定（先不赋予权限，数据专员是分CF端和JK端的）					
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
		testAddCFOnlyRolesToCFUsers();
		
		// 4. 其他有待赋予投资权限的角色
		// (1) 配置销客专员角色。
		// (1) 给“岳霞”配置“销客经理”的角色。
		// (2) 配置销客主管角色。
		
	}





//
//	-- -----------------------------------------------------------------
//	-- 删除角色与权限关系表中，与"投资业务"有关的权限和角色信息
//	-- -----------------------------------------------------------------
//	DELETE FROM t_role_and_permission 
//	WHERE
//		permission_id IN (
//			'110',
//			'104',
//			'147',
//			'148',
//			'149',
//			'150',
//			'151',
//			'152',
//			'238',
//			'235',
//			'236',
//			'237',
//			'153'
//		) AND role_id <> 1
//	AND role_id IN (
//		select t2.role_id from t_role t2 where t2.role_code in (
//		'LiCaiJingLi', 'TuanDuiJingLi', 'DaTuanJingLi', 'YingYeBuJingLi', 'ChengShiJingLi', 'QuYuJingLi','QuYuZongJian', 'ZongJingLi',
//		'BuMenZhuLi', 'XiaoKeZhuanYuan', 'ShuJuZhuanYuan', 'JieSuanZhuanYuan', 'XiaoKeJingLi', 'XiaoKeZhuGuan')
//	)	

	
	//补充：1、删除新建角色时，要首先解除关系：角色与权限的关系，用户与角色的关系。
		
	
}
