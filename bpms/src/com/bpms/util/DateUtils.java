package com.bpms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
	/** 定义常量 **/
	public static final String DATE_JFP_STR = "yyyyMM";
	public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_SMALL_STR = "yyyy-MM-dd";
	public static final String DATE_KEY_STR = "yyMMddHHmmss";

	private DateUtils() {
	}

	/**
	 * 使用预设格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @return
	 */
	public static Date parse(String strDate) {
		return parse(strDate, DATE_FULL_STR);
	}

	/**
	 * 使用用户格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 两个时间比较
	 * 
	 * @param date
	 * @return
	 */
	public static int compareDateWithNow(Date date1) {
		Date date2 = new Date();
		int rnum = date1.compareTo(date2);
		return rnum;
	}

	/**
	 * 两个时间比较(时间戳比较)
	 * 
	 * @param date
	 * @return
	 */
	public static int compareDateWithNow(long date1) {
		long date2 = dateToUnixTimestamp();
		if (date1 > date2) {
			return 1;
		} else if (date1 < date2) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
		return df.format(new Date());
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getNowTime(String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(new Date());
	}

	/**
	 * 获取系统当前计费期
	 * 
	 * @return
	 */
	public static String getJFPTime() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR);
		return df.format(new Date());
	}

	/**
	 * 将指定的日期转换成Unix时间戳
	 * 
	 * @param String
	 *            date 需要转换的日期 yyyy-MM-dd HH:mm:ss
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp(String date) {
		long timestamp = 0;
		try {
			timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date)
					.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	/**
	 * 将指定的日期转换成Unix时间戳
	 * 
	 * @param String
	 *            date 需要转换的日期 yyyy-MM-dd
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp(String date, String dateFormat) {
		long timestamp = 0;
		try {
			timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	/**
	 * 将当前日期转换成Unix时间戳
	 * 
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp() {
		long timestamp = new Date().getTime();
		return timestamp;
	}

	/**
	 * 将Unix时间戳转换成日期
	 * 
	 * @param long timestamp 时间戳
	 * @return String 日期字符串
	 */
	public static String unixTimestampToDate(long timestamp) {
		SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
		sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sd.format(new Date(timestamp));
	}

	/**
	 * 得到指定格式的日期字符串
	 * 
	 * @param format
	 * @param cal
	 * @return
	 */
	public static String getFormatDateString(String format, Calendar cal) {
		if (cal != null) {
			SimpleDateFormat df = new SimpleDateFormat(format);
			String s = df.format(cal.getTime());
			df = null;
			return s;
		} else {
			return "";
		}
	}

	/**
	 * 根据输入字符串得到日期，例2007-04-09，或2007年4月9日
	 * 
	 * @param tmpDate
	 * @return
	 */
	public static Calendar getCalendar(String tmpDate) {
		if (tmpDate != null) {
			if (!tmpDate.trim().equals("")) {
				String[] yyyymmdd = tmpDate.trim().split("-|年|月|日|\\s");
				if (yyyymmdd.length >= 3) {
					return getCalendar(yyyymmdd[0], yyyymmdd[1], yyyymmdd[2]);
				}
			}
		}
		return null;
	}

	/**
	 * 根据输入的年月日字符串得到日期,例 2007,04,09
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Calendar getCalendar(String year, String month, String day) {
		if (!year.equals("")) {
			Calendar tmpDate = Calendar.getInstance();
			int y = Integer.parseInt(year);
			if (y < 1900 || y > 2050)
				return null;
			int m = 0;
			int d = 1;
			if (!month.equals("")) {
				m = Integer.parseInt(month) - 1;
			}
			if (!day.equals("")) {
				d = Integer.parseInt(day);
			}
			tmpDate.set(y, m, d);
			return tmpDate;
		} else {
			return null;
		}
	}

	/**
	 * 获取每月的第一天
	 */
	public static String getBeginDateStr(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(getBeginDate(date));
	}

	/**
	 * 获取每月的月末
	 */
	public static String getEndDateStr(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(getEndDate(date));
	}

	/**
	 * 获取每月的第一天
	 */
	public static Date getBeginDate(Date date) {
		Calendar begin = Calendar.getInstance();
		begin.setTime(date);
		begin.set(Calendar.DAY_OF_MONTH,
				begin.getActualMinimum(Calendar.DAY_OF_MONTH));
		return begin.getTime();
	}

	/**
	 * 获取每月的月末
	 */
	public static Date getEndDate(Date date) {
		Calendar end = Calendar.getInstance();
		end.setTime(date);
		end.set(Calendar.DAY_OF_MONTH,
				end.getActualMaximum(Calendar.DAY_OF_MONTH));
		return end.getTime();
	}

	/**
	 * 获取每月的30号，如果是二月则获取月末
	 */
	private static Date getMonth30Day(Date date, int num) {
		Calendar end = Calendar.getInstance();
		end.setTime(date);
		end.add(Calendar.MONTH, num);
		int maxiNum = end.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (maxiNum > 30)
			maxiNum--;
		end.set(Calendar.DAY_OF_MONTH, maxiNum);
		return end.getTime();
	}

	/**
	 * 获取本月的30号
	 */
	public static Date get30Day(Date date) {
		return getMonth30Day(date, 0);
	}

	/**
	 * 获取上个月的30号
	 */
	public static Date getPrev30Day(Date date) {
		return getMonth30Day(date, -1);
	}

	/**
	 * 获取下个月的30号
	 */
	public static Date getNext30Day(Date date) {
		return getMonth30Day(date, 1);
	}

	/**
	 * 获取本月的15号
	 */
	public static Date get15Day(Date date) {
		return getMonth15Day(date, 0);
	}

	/**
	 * 获取下个月的15号
	 */
	public static Date getNextMonth15Day(Date date) {
		return getMonth15Day(date, 1);
	}

	/**
	 * 获取上个月的15号
	 */
	public static Date getPrev15Day(Date date) {
		return getMonth15Day(date, -1);
	}

	/**
	 * 获取每月的15号
	 */
	private static Date getMonth15Day(Date date, int num) {
		Calendar end = Calendar.getInstance();
		end.setTime(date);
		end.add(Calendar.MONTH, num);
		end.set(Calendar.DAY_OF_MONTH, 15);
		return end.getTime();
	}

	/**
	 * 增加月份
	 */
	public static Date addMonth(Date date, int num) {
		Calendar end = Calendar.getInstance();
		end.setTime(date);
		end.add(Calendar.MONTH, num);
		return end.getTime();
	}

	/**
	 * 计算两个时间的差,主要是对日进行求差,不考虑时分秒
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 */
	public static Integer timeDifferenceDay(Date begin, Date end) {
		Long between = (end.getTime() - begin.getTime()) / 1000;
		Long day = (long) Math.ceil(between / (24 * 3600.0));
		return day.intValue();
	}

	/**
	 * 据当前日期的天数
	 */
	public static Integer currentTimeDifferenceDay(Date begin) {
		return timeDifferenceDay(begin, new Date());
	}

	public static void main(String[] args) {
		// Calendar calendar = Calendar.getInstance();
		// Calendar calendar2 = getCalendar("2015", "08", "20");
		// calendar2.set(Calendar.HOUR, 23);
		// calendar2.set(Calendar.MINUTE, 59);
		// System.out.println(getEndDate(time));
		// System.out.println(get30Day(time));
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd")
		// .format(get15Day(time)));
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd")
		// .format(getNextMonth15Day(time)));
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd")
		// .format(getPrev15Day(time)));
		// System.out.println(currentTimeDifferenceDay(calendar2.getTime()));

		// 先判断日期是在15之前，还是在15之后
		Date date = DateUtils.getCalendar("2015", "2", "27").getTime();
		Date day15 = DateUtils.get15Day(date);
		DateFormat df = new SimpleDateFormat(DATE_FULL_STR);
		Integer differenceDay15 = DateUtils.timeDifferenceDay(day15, date);
		if (differenceDay15 < -1) {
			System.out.println("15 号之前，则取上个月的月末");
			System.out.println(df.format(DateUtils.getPrev30Day(date)));
		} else {
			Date day30 = DateUtils.get30Day(date);
			Integer differenceDay30 = DateUtils.timeDifferenceDay(day30, date);
			if (differenceDay30 < -1) {
				System.out.println("15号之后，则取15号");
				System.out.println(df.format(DateUtils.get15Day(date)));
			} else {
				System.out.println("本月的月末");
				System.out.println(df.format(DateUtils.get30Day(date)));
			}
		}
	}
	
	
	/**
	 * 获取“指定月份”的 回息日期
	 * @Title: getLastDayOfCurrentMonth 
	 * @Description: TODO 获取每个月返息日的日期，即当前月30号对应的日期
	 *  （注意：由于二月比较特殊，对于闰年，二月的返息日为29号；非闰年的二月返息日为28号）
	 * @param 
	 * @return Date
	 * @author ZHANGJIAN
	 * @throws
	 */	
	public static Date getMonthlyInterestPaidDateBySpecifiedDate(Date specifiedDate){
	
		Calendar specifiedCal = Calendar.getInstance();
		specifiedCal.setTime(specifiedDate);
		
		//获取指定日期所在的年份
		int year = specifiedCal.get(Calendar.YEAR);
		
		//如果是闰年
		if( (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ){
			//如果当前月份是2月
			if(2 == (specifiedCal.get(Calendar.MONTH) + 1)){			
				specifiedCal.set(Calendar.DAY_OF_MONTH,29);		
			}else{
				specifiedCal.set(Calendar.DAY_OF_MONTH, 30);
			}
			
		}
		//如果不是闰年
		else {
			//如果当前月份是2月
			if(2 == (specifiedCal.get(Calendar.MONTH) + 1)){			
				specifiedCal.set(Calendar.DAY_OF_MONTH,28);		
			}else{
				specifiedCal.set(Calendar.DAY_OF_MONTH, 30);
			}				
		}	
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date rtnDate = new Date();
		rtnDate.setTime(specifiedCal.getTimeInMillis());
		return rtnDate;
//		return df.format(specifiedCal.getTime()).toString();
//		return df.parse(df.format(specifiedCal.getTime()).toString());
	}	
	
	/**
	 * 获得两个Date之间的月份“净差值”（不包含两个Date参数所在的月份）
	 * <br>例如：若较早月份为：2015-10-29，较晚月份为：2015-12-14，则该函数的运算结果为：1
	 * <br>(1)若两个日期在同一年，直接相减(大日期 - 小日期)
	 * <br>(2)若两个日期的年份相差1
	 * <br>(3)若两个日期的年份相差 > 1
	 * @Title: getMonthDiffByTwoDates 
	 * @param youngDate
	 * @param oldDate
	 * @author ZHANGJIAN
	 * @return -1 说明两个Date参数在同一年的同一个月。
	 * <br> 0    说明两个Date参数在同一年的紧邻的2个月。
	 * <br> >=1   返回两个月份之间的差值 。
	 * @date 2015年12月29日 下午5:38:57
	 */
	public static int getMonthDiffByTwoDates(Date youngDate, Date oldDate){
		
		int monthsDiff = 0;
		
		if( null != youngDate && null != oldDate){
			Calendar youngCal = Calendar.getInstance();
			youngCal.setTime(youngDate);
			int monthOfYoungDate = youngCal.get(Calendar.MONTH) + 1;	
			int yearOfYoungDate = youngCal.get(Calendar.YEAR);
					
			Calendar oldCal = Calendar.getInstance();
			oldCal.setTime(oldDate);
			int monthOfOldDate = oldCal.get(Calendar.MONTH) + 1;		
			int yearOfOldDate = oldCal.get(Calendar.YEAR);
			
			//若两个日期在同一年，直接相减(大日期 - 小日期)
			if(yearOfYoungDate == yearOfOldDate){			
				monthsDiff =  (monthOfOldDate - monthOfYoungDate -1);
			}
			//若两个日期的年份相差1
			else if( yearOfOldDate - yearOfYoungDate == 1  ){			
				monthsDiff =  (12 - monthOfYoungDate) + (monthOfOldDate -1); 			
			}
			//若两个日期的年份相差 > 1
			else if( yearOfOldDate - yearOfYoungDate > 1 ){
				monthsDiff =  (12 - monthOfYoungDate) + (monthOfOldDate -1) + 12 * (yearOfOldDate - yearOfYoungDate - 1); 
			}			
		}
		
		return monthsDiff;
	}	
	
}
