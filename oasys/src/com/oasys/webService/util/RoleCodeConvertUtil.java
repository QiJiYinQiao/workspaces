package com.oasys.webService.util;

import java.util.HashMap;
import java.util.Map;

public class RoleCodeConvertUtil {
	
	public static final String parseRoleNameByEmOffice(String EmOffice){
		return getRoleNameByEmOffice().get(EmOffice) == null ? EmOffice : getRoleNameByEmOffice().get(EmOffice); 
	}
	
	/***
	 * 263职位与oasys角色对应关系
	 * @return
	 */
	public static final Map<String,String> getRoleNameByEmOffice(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("Java工程师","java开发工程师");
		map.put("java开发工程师","java开发工程师");
		map.put("java工程师","java开发工程师");
		map.put("JAVA开发工程师","java开发工程师");
		map.put("车贷部经理","部门经理");
		map.put("IT经理","部门经理");
		map.put("市场部经理","部门经理");
		map.put("销客经理","部门经理");
		map.put("网络运营主管","部门主管");
		map.put("Java开发主管","部门主管");
		map.put("销客主管","部门主管");
		map.put("IPC调查部主管","部门主管");
		map.put("初审主管","部门主管");
		map.put("贷后数据主管","部门主管");
		map.put("IPC电核部主管","部门主管");
		map.put("外勤主管","部门主管");
		map.put("结算主管","部门主管");
		map.put("终审主管","部门主管");
		map.put("IT运维部主管","部门主管");
		map.put("外访部主管","部门主管");
		map.put("车贷部主管","部门主管");
		map.put("渠道拓展主管","部门主管");
		map.put("IPC审贷部主管","部门主管");
		map.put("催收","催收专员");
		map.put("大团队经理","大团经理");
		map.put("大团经理一级","大团经理");
		map.put("行政借款对接主管","行政对接主管");
		map.put("行政财富对接主管","行政对接主管");
		map.put("行政财富对接助理","行政对接助理");
		map.put("行政对接","行政对接助理");
		map.put("东北区培训主管","培训主管");
		map.put("结算","结算专员");
		map.put("行政对接","行政对接专员");
		map.put("黑龙江、吉林省级区域经理","区域经理");
		map.put("华东区区域经理","区域经理");
		map.put("吉林省区域经理","区域经理");
		map.put("山东省区域经理","区域经理");
		map.put("河北省区域经理","区域经理");
		map.put("华中区区域经理", "区域经理");
		map.put("东北区域总监", "区域总监");
		map.put("团队经理一级","团队经理");
		map.put("见习团队经理","团队经理");
		map.put("团队经理三级","团队经理");
		map.put("团队经理二级","团队经理");
		map.put("初级团队经理","团队经理");
		map.put("团队经理 一级","团队经理");
		map.put("代理团队经理","团队经理");
		map.put("二级团队经理","团队经理");
		map.put("高级营业部经理","营业部经理");
		map.put("初级营业部经理","营业部经理");
		map.put("IPC信用管理中心经理","中心经理");
		map.put("华东区副总监兼总裁办助理","总裁办助理");
		map.put("总裁助理","总裁办助理");
		map.put("总裁","总经理");
		map.put("客户经理一级","客户经理");
		map.put("客户经理二级","客户经理");
		map.put("兼职客户经理","客户经理");
		map.put("客户经理三级","客户经理");
		map.put("高级客户经理","客户经理");
		map.put("银牌客户经理","客户经理");
		map.put("金牌客户经理","客户经理");
		map.put("高级客户顾问","客户顾问");
		map.put("见习客户顾问","客户顾问");
		map.put("会计主管","财务主管");
		map.put("行政前台","行政专员");
		map.put("IPC审核专员","IPC专员");
		map.put("总监助理","借款总监助理");
		map.put("招聘经理","部门经理");
		map.put("借款代理总监","区域总监");
		return map;
	}

	/**
	 * 角色与组织机构对应关系
	 * @return
	 */
	public static final Map<String,String> getRoleOrgByRoleName(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("人事专员","人力资源管理中心");
		map.put("行政专员","行政管理中心");
		return map;
	}
	
	/**
	 * 组织机构简称
	 * @return
	 */
	public static final Map<String,String> getOrgEnameByOrgName(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("人力资源管理中心","HR Manage Center");
		map.put("行政管理中心","Admin Manage Center");
		return map;
	}
	
	/**
	 * 		-- IPC专员 数据专员 IPC项目组组员 外访专员 初审 车辆评估师 催收专员 车贷外勤 车贷面审 培训师 客服专员
		-- 稽核专员 车贷评估师 终审 GPS安装时 面前专员 外勤专员 合同管理员 渠道专员 seo专员 客户经理 客户顾问
	 * */
	public static final Map<String,String> getRoleParentByRoleName(){
		Map<String,String> map = new HashMap<String, String>();
		/** 风控系统角色直属上级匹配 */
		map.put("信审初审","信审审批经理");
		map.put("信审初审","信审审批经理");
		map.put("信审外访","信审审批经理");
		map.put("信审终审","信审审批经理");
		map.put("信审审批主管","信审审批经理");
		map.put("信审数据岗","信审审批经理");
		
		/** OASYS系统直属上级匹配*/
		map.put("部门主管","部门经理");
		map.put("行政对接主管","行政经理");
		map.put("对接主管","人力资源经理");
		map.put("招聘经理","人力资源经理");
		map.put("财务主管","财务经理");
		map.put("人事专员","人力资源经理");
		map.put("团队经理","大团经理");
		map.put("行政助理","行政经理");
		map.put("营业部经理","城市经理");
		map.put("区域总监","总裁");
		map.put("运维工程师","部门主管");
		map.put("区域经理","区域总监");
		map.put("行政专员","行政经理");
		map.put("大团经理","营业部经理");
		map.put("部门助理","部门经理");
		map.put("对接专员","对接主管");
		map.put("对接助理","对接主管");
		map.put("行政外勤","行政经理");
		map.put("城市经理","区域经理");
		map.put("网页制作","部门主管");
		map.put("培训讲师","部门主管");
		map.put("java开发工程师","部门主管");
		map.put("结算专员","部门主管");
		map.put("平面设计美编","部门主管");
		map.put("招聘专员","部门经理");
		map.put("会计","财务主管");
		map.put("财务总监","总裁");
		map.put("高级培训讲师","部门主管");
		map.put("逍客专员","部门主管");
		map.put("人力资源经理","人力资源总监");
		map.put("网站编辑","部门主管");
		map.put("IOS开发工程师","部门主管");
		map.put("行政经理","行政总监");
		map.put("总裁助理","总裁");
		map.put("行政对接助理","行政经理");
		map.put("固定资产管理员","行政经理");
		map.put("信审经理","总裁");
		map.put("市场专员","部门主管");
		map.put("网页设计","部门主管");
		map.put("出纳","财务主管");
		map.put("Linux运维工程师","部门主管");
		map.put("员工关系专员","人力资源经理");
		map.put("行政内勤","行政经理");
		map.put("培训专员","部门主管");
		map.put("培训主管","部门经理");
		map.put("车贷客服","部门主管");
		map.put("安卓开发工程师","部门主管");
		map.put("行政对接专员","行政经理");
		map.put("副总裁","总裁");
		map.put("高级培训专员","部门主管");
		map.put("平面美编","部门主管");
		map.put("软件测试工程师","部门主管");
		map.put("法务部负责人","总裁");
		map.put("中心经理","中心总监");
		map.put("培训师","部门主管");
		map.put("部门经理","人力资源经理");
		return map;
	}

	/***
	 * 根据角色名称获取角色roleType类型
	 * @return
	 */
	public static final Map<String,String> getRoleTypeByRoleName(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("信审外访","1");
		map.put("车贷客服","1");
		map.put("SEO专员","1");
		map.put("外访专员","1");
		map.put("渠道专员","1");
		map.put("客服专员","1");
		map.put("车贷外勤","1");
		map.put("车贷评估师","1");
		map.put("大团经理","1");
		map.put("市场专员","1");
		map.put("车贷面审","1");
		map.put("营业部经理","1");
		map.put("销客专员","1");
		map.put("城市经理","1");
		map.put("团队经理","1");
		map.put("催收专员","1");
		map.put("客户经理","1");
		map.put("客户顾问","1");
		map.put("车辆评估师","1");
		map.put("签约人","1");
		map.put("行政经理","2");
		map.put("总裁助理","2");
		map.put("行政对接助理","2");
		map.put("员工关系专员","2");
		map.put("稽核专员","2");
		map.put("平面设计美编","2");
		map.put("招聘专员","2");
		map.put("IOS开发工程师","2");
		map.put("会计","2");
		map.put("财务总监","2");
		map.put("网站编辑","2");
		map.put("人力资源经理","2");
		map.put("高级培训讲师","2");
		map.put("区域经理","2");
		map.put("培训师","2");
		map.put("固定资产管理员","2");
		map.put("信审经理","2");
		map.put("财务经理","2");
		map.put("安卓开发工程师","2");
		map.put("行政对接专员","2");
		map.put("副总裁","2");
		map.put("高级培训专员","2");
		map.put("平面美编","2");
		map.put("华东区区域经理","2");
		map.put("软件测试工程师","2");
		map.put("法务部负责人","2");
		map.put("培训主管","2");
		map.put("培训专员","2");
		map.put("行政内勤","2");
		map.put("终审","2");
		map.put("GPS安装师","2");
		map.put("面签专员","2");
		map.put("外勤专员","2");
		map.put("合同管理员","2");
		map.put("网页设计","2");
		map.put("总裁办助理","2");
		map.put("出纳","2");
		map.put("Linux运维工程师","2");
		map.put("中心经理","2");
		map.put("结算专员","2");
		map.put("系统管理员","2");
		map.put("IPC当地组长","2");
		map.put("IPC审贷部主管","2");
		map.put("IPC审核专员","2");
		map.put("IPC调查专员","2");
		map.put("IPC调查部主管","2");
		map.put("IPC审贷委","2");
		map.put("IPC信用管理中心经理","2");
		map.put("车贷负责人","2");
		map.put("车贷数据专员","2");
		map.put("IPC小额调查","2");
		map.put("IPC电核部主管","2");
		map.put("IPC数据岗","2");
		map.put("业务员","2");
		map.put("贷款结算专员","2");
		map.put("贷款结算放款","2");
		map.put("信审初审","2");
		map.put("信审初审组长","2");
		map.put("信审终审","2");
		map.put("信审审批主管","2");
		map.put("信审审批经理","2");
		map.put("信审数据岗","2");
		map.put("招聘经理","2");
		map.put("部门主管","2");
		map.put("部门经理","2");
		map.put("IPC项目组组员","2");
		map.put("对接专员","2");
		map.put("初审","2");
		map.put("对接助理","2");
		map.put("行政外勤","2");
		map.put("IPC电核专员","2");
		map.put("网页制作","2");
		map.put("培训讲师","2");
		map.put("java开发工程师","2");
		map.put("部门助理","2");
		map.put("数据专员","2");
		map.put("行政专员","2");
		map.put("行政对接主管","2");
		map.put("对接主管","2");
		map.put("法务主管","2");
		map.put("财务主管","2");
		map.put("IPC专员","2");
		map.put("人事专员","2");
		map.put("行政助理","2");
		map.put("区域总监","2");
		map.put("运维工程师","2");
		map.put("总经理","2");
		return map;
	}
}
