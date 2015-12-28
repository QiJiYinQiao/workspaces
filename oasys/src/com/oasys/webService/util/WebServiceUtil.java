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
    
//    public static final QName SERVICE_NAME = new QName("http://macom.263.net/axis/xmapi", "XmapiImplService");//webservice地址

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
