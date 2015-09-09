package com.bpms.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * 数字进行格式化的方法
 * 
 * @author liuhh
 *
 */
public class NumberFormatUtil {
	/**
	 * 精确到小数点scale位
	 * 
	 * @param scale
	 *            小数点后的位数
	 * @param obj
	 *            格式化的obj
	 * @return 四舍五入到精确到指定小数位数的BigDecimal
	 */
	public static BigDecimal formatNumber(int scale, Object obj) {
		String val = String.valueOf(obj);
		if (val == null)
			return new BigDecimal("0.00");

		val = val.replaceAll(",", "");
		if (!isNumber(val))
			return new BigDecimal("0.00");

		return new BigDecimal(val).setScale(scale, RoundingMode.HALF_UP);
	}

	/**
	 * 精确到小数点scale位
	 * 
	 * @param scale
	 *            小数点后的位数
	 * @param obj
	 *            格式化的obj
	 * @return 四舍五入到精确到指定小数位数的字符串
	 */
	public static String formatNumber2Str(int scale, Object obj) {
		String farmatStr = "#,##0";
		if (scale > 0)
			farmatStr += StringUtils.rightPad(".", scale + 1, "0");
		return new DecimalFormat(farmatStr).format(formatNumber(scale, obj));
	}

	/**
	 * 判断是否是double类型的数
	 * 
	 * @param value
	 *            待判断的数
	 * @return 是true/否false
	 */
	private static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断是否不是整数
	 * 
	 * @param value
	 *            带判断数
	 * @return 是true/否false
	 */
	private static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断是否是实数
	 * 
	 * @param value
	 *            判断的值
	 * @return 是true/否false
	 */
	private static boolean isNumber(String value) {
		return isInteger(value) || isDouble(value);
	}

}
