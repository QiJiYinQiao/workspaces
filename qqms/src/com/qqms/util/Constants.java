package com.qqms.util;

import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.qqms.shiro.ShiroUser;

/**
 * 
 * 系统常用的常量
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public class Constants {

	/** session中的用户的信息. */
	public static final String LOGIN_SESSION_DATANAME = "users";

	/** 登录用户的地址. */
	public static final String LOGIN_URL = "login";

	/** 成功页面. */
	public static final String LOGIN_SUCCESS_URL = "index";

	/** 退出登录的地址. */
	public static final String LOGIN_LOGIN_OUT_URL = "loginout";

	/** 登录信息提示的消息的key. */
	public static final String LOGIN_MSG = "loginMsg";

	/** 登录时用户名为空的时候,提示信息. */
	public static final String USERNAME_IS_NULL = "用户名为空!";

	/** 用户已经登录的提示信息. */
	public static final String LOGIN_IS_EXIST = "该用户已登录!";

	/** 异常回话的提示消息. */
	public static final String UNKNOWN_SESSION_EXCEPTION = "异常会话!";

	/** 账号错的提示消息. */
	public static final String UNKNOWN_ACCOUNT_EXCEPTION = "账号错误!";

	/** 登录时候,密码错误. */
	public static final String INCORRECT_CREDENTIALS_EXCEPTION = "密码错误!";

	/** 登录时候,账号被锁定. */
	public static final String LOCKED_ACCOUNT_EXCEPTION = "账号已被锁定，请与系统管理员联系!";

	/** 登录的时候,验证码输入错误的提示信息. */
	public static final String INCORRECT_CAPTCHA_EXCEPTION = "验证码错误!";

	/** 　没有授权的提示信息． */
	public static final String AUTHENTICATION_EXCEPTION = "您没有授权!";

	/** 出现未知情况的时候,提示的信息. */
	public static final String UNKNOWN_EXCEPTION = "出现未知异常,请与系统管理员联系!";

	/** 增加树节点的状态标示. */
	public static final String TREE_GRID_ADD_STATUS = "add";

	/** 更新数据成功的标示. */
	public static final String POST_DATA_SUCCESS = "数据更新成功!";

	/** 提交数据失败的标示. */
	public static final String POST_DATA_FAIL = "提交失败了!";

	/** 模糊查询的拼接符. */
	public static final String GET_SQL_LIKE = "%";

	/** 是不是菜单类型的权限 */
	public static final String IS_FUNCTION = "F";

	/** 显示数据的标示. */
	public static final String PERSISTENCE_STATUS = "A";

	/** 逻辑删除的标示. */
	public static final String PERSISTENCE_DELETE_STATUS = "I";

	/** 系统admin. */
	public static final String SYSTEM_ADMINISTRATOR = "admin";

	/** 返回response的头部的信息文本. */
	public static final String TEXT_TYPE_PLAIN = "text/plain";

	/** 返回response的头部的信息页面. */
	public static final String TEXT_TYPE_HTML = "text/html";

	/** 是不是操作类型的权限(增删改). */
	public static final String FUNCTION_TYPE_O = "O";

	/** 展开状态. */
	public static final String TREE_STATUS_OPEN = "open";

	/** 折叠状态. */
	public static final String TREE_STATUS_CLOSED = "closed";

	/** 是否含有子菜单的提示信息. */
	public static final String IS_EXT_SUBMENU = " 或可能包含菜单!";

	/** 当用户的key. */
	public static final String SHIRO_USER = "shiroUser";

	/** sql文件的后缀名称. */
	public static final String FILE_SUFFIX_SQL = ".sql";

	/** 压缩包的后缀. */
	public static final String FILE_SUFFIX_ZIP = ".zip";

	/**
	 * 生成UUID主键
	 * 
	 * @return 主键
	 */
	public static String getPrimaryKeyByUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获取当前登录的用户的信息
	 * 
	 * @return 当前登录的用户的信息
	 */
	public static ShiroUser getCurrendUser() {
		Subject subject = SecurityUtils.getSubject();
		return (ShiroUser) subject.getSession().getAttribute(SHIRO_USER);
	}

	/**
	 * 私有的构造方法,工具类不支持实例化操作
	 */
	private Constants() {

	}
}
