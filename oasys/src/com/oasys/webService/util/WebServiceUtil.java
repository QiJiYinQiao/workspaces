package com.oasys.webService.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * WebService地址端口工具类
 * 
 * @author 李达 2015/12/18.
 * 
 * @version V1.00.
 * 
 */
public class WebServiceUtil {
	
	public static final String WS_USERID = "dali";//用户ID（不包含@和域名）
    public static final String WS_DOMAIN = "qqjrbj.com";//域名，不能为空，最大长度为40字节
    public static final String WS_ACCOUNT = "qqjrbj.com";//接口账号
    public static final String WS_PASSWORD="D45Bn2xverNf";//接口密钥
    public static final String WS_PATH = "http://macom.263.net/axis/xmapi";
    public static final String WS_SERVICE="http://XmapiImplService";
    public static final String WS_GETDEPARTMENT="getDepartment";//获取部门列表接口
    public static final String WS_GET_DOMAINUSERLIST_BY_STATUS="getDomainUserlistByStatus";//获取指定状态用户列表接口
    
    public static final String OA_SYS_INIT_PWD="123456";
    //初始化角色时默认配置菜单的 菜单名称
    public static final String OA_SYS_INIT_PERMISSION="'员工考勤','财务管理','财务报销','人力资源规划','行政办公','任务管理','人事手续管理',"
    		+ "'员工关系','忘打卡申请','员工转正申请','固定资产报废申请',"
    		+ "'外出申请','固定资产移交申请','员工离职申请','休假申请','借款申请','差旅费报销申请',"
    		+ "'支出凭单报销申请','任务办理','查看已办理任务','用章申请'";
    //组织机构如果在map中存在 则删除该组织机构并将人员信息配置在该组织机构父级节点
    public static final String OA_SYS_INIT_ORG_PARENT="'IPC调查','贷款支持','储备城市经理','数据','行政专员','行政前台'";
    
    public static final String OA_SYS_INIT_ROLE_TYPE="'行政专员','人事专员'";//分部中职能端的角色名称
    
    //组织机构中regionType计算区域 分为 借款 'JK' 和 财富 'CF' 字符串中配置的为进行计算的类型
    public static final String QQMS_SYS_INIT_ORG_REGION_TYPE="'JK'";
	/***
	 * 直辖市标识
	 * @return 0－地区，1－直辖市，2－省份，3－城市，4－其他
	 */
    public static final String QQMS_SYS_INIT_MDU="上海,重庆,北京,天津";

	/**
	 * 私有的构造方法,工具类不能进行实例化
	 */
	private WebServiceUtil() {

	}
	
	/**
	 * 获取加密标识 
	 * sign = 32位MD5小写 （ userid + domain + 接口账号 + 接口密钥 ）   /* 等式中的“+”号表示字符串连接 * /
	 * */
	public static final String getSign(){
		return encryption(WS_USERID.concat(WS_DOMAIN).concat(WS_ACCOUNT).concat(WS_PASSWORD));
	}
	
	/**
    *
    * @param plainText
    *            明文
    * @return 32位密文
    */
   public static final String encryption(String plainText) {
       String re_md5 = new String();
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(plainText.getBytes());
           byte b[] = md.digest();

           int i;

           StringBuffer buf = new StringBuffer("");
           for (int offset = 0; offset < b.length; offset++) {
               i = b[offset];
               if (i < 0)
                   i += 256;
               if (i < 16)
                   buf.append("0");
               buf.append(Integer.toHexString(i));
           }

           re_md5 = buf.toString();

       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
       return re_md5;
   }
   
}
