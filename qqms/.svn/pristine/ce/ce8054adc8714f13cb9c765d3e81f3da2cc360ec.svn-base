package com.qqms.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.hibernate.Session;

import com.qqms.model.Log;

/**
 * 
 * 记录日志的工具类
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public class LogUtil {

	/** 对象插入. */
	public static final String LOGS_INSERT = "insert:";

	/** 对象插入标题. */
	public static final String LOGS_INSERT_TEXT = "插入:";

	/** 对象插入名称. */
	public static final String LOGS_INSERT_NAME = "insertLogs";

	/** 对象更新. */
	public static final String LOGS_UPDATE = "update:";

	/** 对象更新标题. */
	public static final String LOGS_UPDATE_TEXT = "更新:";

	/** 对象更新名称. */
	public static final String LOGS_UPDATE_NAME = "updateLogs";

	/** 对象删除. */
	public static final String LOGS_DELETE = "delete:";

	/** 对象删除的标题. */
	public static final String LOGS_DELETE_TEXT = "删除:";

	/** 对象删除名称. */
	public static final String LOGS_DELETE_NAME = "deleteLogs";

	/** 日志的数据库的名称. */
	private static final String LOGS_TB_NAME = "Log";

	/**
	 * 私有的构造方法,工具类不支持实例化
	 */
	private LogUtil() {

	}

	/**
	 * 记录日志
	 * 
	 * @param session
	 *            hibernate会话session
	 * @param o
	 *            操作对象
	 * @param eventName
	 *            操作名称
	 * @param er
	 *            操作中文名称
	 * @param name
	 *            操作的日志类型
	 */
	public static <T> void getLogs(Session session, T o, String eventName,
			String er, String name) {
		if (!LOGS_TB_NAME.equals(o.getClass().getSimpleName())) {
			String ip = ClientUtil.getIpAddr();
			String mac = ClientUtil.getMacAddr();
			String[] sdf = getFiledName(o);
			String id = getFieldValueByName(sdf[1], o).toString();
			Log l = new Log();
			l.setUserId(Constants.getCurrendUser().getUserId());
			l.setName(Constants.getCurrendUser().getAccount());
			l.setLogDate(new Date());
			l.setEventName(eventName + o.getClass().getSimpleName());
			l.setEventRecord(er + o.getClass().getName());
			l.setObjectId(id);
			l.setType(2);
			l.setIp(ip);
			l.setMac(mac);
			session.save(l);
		}
	}

	/**
	 * 获取对象的属性
	 * 
	 * @param o
	 *            对象
	 * @return 对象的属性名称
	 */
	public static String[] getFiledName(Object o) {
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			String[] fieldNames = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				fieldNames[i] = fields[i].getName();
			}
			return fieldNames;
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据字段的名称获取字段的值
	 * 
	 * @param fieldName
	 *            字段的名称
	 * @param o
	 *            对象
	 * @return 字段名称对应的字段的值
	 */
	public static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("属性不存在");
			return "";
		}
	}
}
