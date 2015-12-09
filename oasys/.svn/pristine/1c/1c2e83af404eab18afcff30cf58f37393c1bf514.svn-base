package com.oasys.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * @ClassName: UniqueIdUtil
 * @Description: TODO 生成唯一编号util类
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年9月16日 上午9:10:54
 *
 */
public class UniqueIdUtil {
	private static AtomicInteger atomic = new AtomicInteger(0);

	public static synchronized String generate(String prefix) {
		return new StringBuffer()
				.append(StringUtils.isNotBlank(prefix) ? prefix : "")
				.append(Long.toHexString(System.currentTimeMillis()))
				.append(atomic.getAndIncrement()).toString();
	}
}
