package test.service;

import java.io.IOException;
import java.util.Date;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import test.TestBase;

import com.bpms.model.Role;
import com.bpms.model.RoleAndPermission;
import com.bpms.model.UserAndRole;
import com.bpms.model.Users;
import com.bpms.service.RoleAndPermissionService;
import com.bpms.service.RoleService;
import com.bpms.service.UserAndRoleService;
import com.bpms.service.UserService;
import com.bpms.util.ChineseToPinYinUtil;
import com.bpms.util.ReadExcel;
import com.sun.star.uno.Exception;

public class InitDataService extends TestBase{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAndRoleService userAndRoleService;
	
	@Autowired
	private RoleAndPermissionService roleAndPermissionService;
	
//	private String temp = "/人员角色关系(汇总).xlsx";
//	private String temp = "/人员角色关系(IPC).xlsx";
	private String temp = "/人员角色关系(吉林长春营业部).xlsx";
	
	/**
	 * 增加用户角色信息
	 */
	@Test
	@Transactional
	public void addRole() throws InvalidFormatException, IOException, BadHanyuPinyinOutputFormatCombination {
		StringBuffer messageBuffer = new StringBuffer();
		String resource = TestLoanOrderHisService.class.getResource("").getPath() + temp;
		Workbook createworkbook = ReadExcel.createworkbook(resource.substring(1));
		Sheet sheetAt = createworkbook.getSheetAt(1);
		Row row = null;
		int rowIndex = 1;
		do {
			row = sheetAt.getRow(rowIndex);
			if(null!=row){
				// 判断单元格是否为空
				Cell roleNameCell = row.getCell(0);
				if(null == roleNameCell){
					row = null;
					break;
				}
				
				// 判断角色名称是否为空,如果为空则说明到了结束位置
				String roleName = row.getCell(0).getStringCellValue();
				if(StringUtils.isBlank(roleName)){
					row = null;
					break;
				}else{
					roleName = roleName.trim();
				}
				
				// 判断角色信息是否存在,如果不存在则进行增加角色信息
				String roleCode = ChineseToPinYinUtil.getUpEname1(roleName);
				Role roleF = roleService.findRoleByCode(roleCode);
				if(null==roleF){
					Role nRole = new Role(roleCode, roleName, roleName, "A", new Date(), new Date(), 1, 1, 1);
					roleService.saveRole(nRole);
				}else{
					messageBuffer.append("行:"+(rowIndex+1)+"\t角色名称:"+roleName+"  已经存在!!!\n");
				}
			}
			rowIndex++;
		} while (null!=row);
		System.out.println("======================================================================");
		System.out.println(messageBuffer.toString());
		System.out.println("======================================================================");
	}

	/**
	 * 绑定角色的权限菜单
	 */
	@Test
	@Transactional
	public void addRoleAndPermssion() throws InvalidFormatException, IOException, BadHanyuPinyinOutputFormatCombination {
		// 角色绑定的权限菜单
		String permissionIDs[] = {"110","107","108","167","168","169"};
		StringBuffer messageBuffer = new StringBuffer();
		String resource = TestLoanOrderHisService.class.getResource("").getPath() + temp;
		Workbook createworkbook = ReadExcel.createworkbook(resource.substring(1));
		Sheet sheetAt = createworkbook.getSheetAt(1);
		Row row = null;
		int rowIndex = 1;
		do {
			row = sheetAt.getRow(rowIndex);
			if(null!=row){
				// 判断单元格是否为空
				Cell roleNameCell = row.getCell(0);
				if(null == roleNameCell){
					row = null;
					break;
				}
				
				// 判断角色名称是否为空,如果为空则说明到了结束位置
				String roleName = row.getCell(0).getStringCellValue();
				if(StringUtils.isBlank(roleName)){
					row = null;
					break;
				}else{
					roleName = roleName.trim();
				}
				
				// 判断角色信息是否存在,如果不存在则进行增加角色信息
				String roleCode = ChineseToPinYinUtil.getUpEname1(roleName);
				Role roleF = roleService.findRoleByCode(roleCode);
				if(null!=roleF){
					for (String permission : permissionIDs) {
						// 如果角色不是业务员的话只写除去申请业务的菜单
						if(permission.equals("108")){
							if(roleCode.equals("YeWuYuan")){ 
								RoleAndPermission roleAndPermission  = new RoleAndPermission(Integer.parseInt(permission), roleF.getRoleId(), "A", new Date(), new Date(), 1, 1);
								if(null == roleAndPermissionService.isExistRoleAndPermissions(roleAndPermission)){
									roleAndPermissionService.saveRoleAndPermissions(roleAndPermission);
								}
							}
						}else{
							// 如果是业务员的话全部写入
							RoleAndPermission roleAndPermission  = new RoleAndPermission(Integer.parseInt(permission), roleF.getRoleId(), "A", new Date(), new Date(), 1, 1);
							if(null == roleAndPermissionService.isExistRoleAndPermissions(roleAndPermission)){
								roleAndPermissionService.saveRoleAndPermissions(roleAndPermission);
							}
						}
					}
				}else{
					messageBuffer.append("行:"+(rowIndex+1)+"\t角色名称:"+roleName+"  角色不存在!!!\n");
				}
			}
			rowIndex++;
		} while (null!=row);
		System.out.println("======================================================================");
		System.out.println(messageBuffer.toString());
		System.out.println("======================================================================");
		
	}
	
	
	/**
	 * 删除用户之前绑定用户的角色信息
	 */
	@Test
	@Transactional
	public void deleteUserAndRoles() throws InvalidFormatException, IOException, Exception {
		StringBuffer messageBuffer = new StringBuffer();
		String resource = TestLoanOrderHisService.class.getResource("").getPath() + temp;
		Workbook createworkbook = ReadExcel.createworkbook(resource.substring(1));
		Sheet sheetAt = createworkbook.getSheetAt(0);
		Row row = null;
		int rowIndex = 1;
		do {
			row = sheetAt.getRow(rowIndex);
			if(null!=row){
				Cell nameCell = row.getCell(0);
				Cell phoneCell =  row.getCell(1);
				Cell emailCell = row.getCell(2);
				// 判断单元格是否存在
				boolean isExists = null != nameCell && null !=phoneCell && null !=emailCell;
				if(!isExists){
					row = null;
					break;
				}
				// 单元格存在的时候
				String  name = nameCell.getStringCellValue();
				String  phone = phoneCell.getStringCellValue();
				String  email = emailCell.getStringCellValue();
				// 判断单元是否为空
				boolean isBlank = StringUtils.isBlank(name) && StringUtils.isBlank(phone)&& StringUtils.isBlank(email);
				// 如果姓名电话邮箱都为空的时候则说明没有数据了
				if(isBlank){
					row  = null;
					break;
				}else{
					name = name.trim();
					phone = phone.trim();
					email = email.trim();
				}
				
				
				// 如果值不为空则说明存在
				Users user = userService.findUserByNameAndPoneAndEmail(name, phone, email);
				if(null != user){
					userAndRoleService.deleteUserAndRoles(user.getUserId());
				}else{
					// 如果用户不存在进行记录日志
					messageBuffer.append("行:"+(rowIndex+1)+"\t姓名:"+name+"  电话:"+ phone+"  没有找到该用户信息!!!!\n");
				}
			}
			rowIndex++;
		} while (null!=row);
		System.out.println("======================================================================");
		System.out.println(messageBuffer.toString());
		System.out.println("======================================================================");
	}
	/**
	 * 绑定用户角色信息关系
	 * @throws Exception 
	 */
	@Test
	@Transactional
	public void addUserAndRoles() throws InvalidFormatException, IOException, Exception {
		StringBuffer messageBuffer = new StringBuffer();
		String resource = TestLoanOrderHisService.class.getResource("").getPath() + temp;
		Workbook createworkbook = ReadExcel.createworkbook(resource.substring(1));
		Sheet sheetAt = createworkbook.getSheetAt(0);
		Row row = null;
		int rowIndex = 1;
		do {
			row = sheetAt.getRow(rowIndex);
			if(null!=row){
				Cell nameCell = row.getCell(0);
				Cell phoneCell =  row.getCell(1);
				Cell emailCell = row.getCell(2);
				// 判断单元格是否存在
				boolean isExists = null != nameCell && null !=phoneCell && null !=emailCell;
				if(!isExists){
					row = null;
					break;
				}
				// 单元格存在的时候
				String  name = nameCell.getStringCellValue();
				String  phone = phoneCell.getStringCellValue();
				String  email = emailCell.getStringCellValue();
				// 判断单元是否为空
				boolean isBlank = StringUtils.isBlank(name) && StringUtils.isBlank(phone)&& StringUtils.isBlank(email);
				// 如果姓名电话邮箱都为空的时候则说明没有数据了
				if(isBlank){
					row  = null;
					break;
				}else{
					name = name.trim();
					phone = phone.trim();
					email = email.trim();
				}
				
				
				// 如果值不为空则说明存在
				Users user = userService.findUserByNameAndPoneAndEmail(name, phone, email);
				if(null != user){
					String roleName = row.getCell(3).getStringCellValue();
					Role role = roleService.findRoleByName(roleName);
					UserAndRole userAndRole = new  UserAndRole( role.getRoleId(), user.getUserId(), "A", new Date(), new Date(), 1, 1);
					
					// 判断用户和角色的关系没有存在过才进行编写
					if(null == userAndRoleService.isExistUserAndRoles(userAndRole)){
						userAndRoleService.saveUserAndRoles(userAndRole);
					}
				}else{
					// 如果用户不存在进行记录日志
					messageBuffer.append("行:"+(rowIndex+1)+"\t姓名:"+name+"  电话:"+ phone+"  没有找到该用户信息!!!!\n");
				}
			}
			rowIndex++;
		} while (null!=row);
		System.out.println("======================================================================");
		System.out.println(messageBuffer.toString());
		System.out.println("======================================================================");
	}
	
	@Test
	@Transactional(rollbackFor={InvalidFormatException.class,IOException.class,BadHanyuPinyinOutputFormatCombination.class,Exception.class,RuntimeException.class})
	public void saveInitData() throws InvalidFormatException, IOException, BadHanyuPinyinOutputFormatCombination, Exception{
//		addRole();
//		addRoleAndPermssion();
//		deleteUserAndRoles();
		addUserAndRoles();
	}
}
