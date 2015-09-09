package com.bpms.util;

import java.util.List;

/**
 * @author chenfl
 * @fileName Collections.java
 * @createTime 2015年5月15日 上午10:31:16
 * @description 集合工具类，功能完善中
 */
public class Collections {

	public static boolean listIsEmpty(List<?> list) {
		return list == null || list.size() == 0;
	}

	public static boolean listIsNotEmpty(List<?> list) {
		return !listIsEmpty(list);
	}
}
