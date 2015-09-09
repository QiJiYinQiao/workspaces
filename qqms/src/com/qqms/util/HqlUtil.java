package com.qqms.util;

import java.util.Map;

/**
 * 
 * 获取客户端IP地址和Mac地址工具类
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public class HqlUtil {

	/** 逗号. */
	public static final String IS_DOT = ".";

	/** 模糊查询like关键字. */
	public static final String HQL_LIKE = "like";

	/** 空串. */
	public static final String NULL_STRING = "";

	/**
	 * 私有的构造方法,工具类不支持实例化
	 */
	private HqlUtil() {

	}

	/**
	 * hql的高级查询
	 * 
	 * @param asName
	 *            别名
	 * @param pageUtil
	 *            分页工具
	 * @return 拼接好的hql
	 */
	public static String getGradeSearchConditionsHQL(String asName,
			PageUtil pageUtil) {
		String searchAnds = pageUtil.getSearchAnds();
		String searchColumnNames = pageUtil.getSearchColumnNames();
		String searchConditions = pageUtil.getSearchConditions();
		String searchVals = pageUtil.getSearchVals();
		if (null != searchColumnNames && searchColumnNames.trim().length() > 0) {
			StringBuffer sb = new StringBuffer();
			String[] searchColumnNameArray = searchColumnNames.split("\\,");
			String[] searchAndsArray = searchAnds.split("\\,");
			String[] searchConditionsArray = searchConditions.split("\\,");
			String[] searchValsArray = searchVals.split("\\,");
			for (int i = 0; i < searchColumnNameArray.length; i++) {
				if (searchColumnNameArray[i].trim().length() > 0
						&& searchConditionsArray[i].trim().length() > 0) {

					String temp = searchValsArray[i].trim().replaceAll("\\'",
							"");
					if (HQL_LIKE.equals(searchConditionsArray[i].trim())) {
						sb.append(" " + searchAndsArray[i].trim() + " "
								+ asName + IS_DOT
								+ searchColumnNameArray[i].trim() + " "
								+ searchConditionsArray[i].trim() + " " + "'%"
								+ temp + "%'");

					} else {
						sb.append(" " + searchAndsArray[i].trim() + " "
								+ asName + IS_DOT
								+ searchColumnNameArray[i].trim() + " "
								+ searchConditionsArray[i].trim() + " " + "'"
								+ temp + "'");
					}
				}
			}
			if (sb.length() > 0) {
				return sb.toString();
			}
		}
		return NULL_STRING;
	}

	/**
	 * 简单条件查询
	 * 
	 * @param asName
	 *            别名
	 * @param param
	 *            参数
	 * @return 拼接好的hql语句
	 */
	public static String getSearchConditionsHQL(String asName,
			Map<String, Object> param) {
		StringBuffer sb = new StringBuffer();
		if (param != null && !param.isEmpty()) {
			for (String name : param.keySet()) {
				sb.append(" and " + asName + IS_DOT + name + " like :" + name
						+ "");
			}
		}
		return sb.toString();
	}
}
