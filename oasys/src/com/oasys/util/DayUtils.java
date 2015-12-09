package com.oasys.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class DayUtils {
	
	public final static String FILE_NAME = "Holiday.xlsx";

	public static List<Date> festival = new ArrayList<Date>();// 节假日

	public static List<Date> workDay = new ArrayList<Date>();// 工作日

	public DayUtils() {
		File excel = this.getExcel();
		try {
			FileInputStream fin = new FileInputStream(excel);

			XSSFWorkbook xssfworkbook = new XSSFWorkbook(fin);

			XSSFSheet sheet = xssfworkbook.getSheetAt(0);

			int last = sheet.getLastRowNum();

			int index = 1;

			Date dt = null;

			while (index <= last) {

				XSSFRow row = sheet.getRow(index);

				/*
				 * 
				 * 读取法定节假日
				 */

				XSSFCell cell = row.getCell((short) 0);

				if (cell != null) {

					if (HSSFDateUtil.isCellDateFormatted(cell)) {

						dt = HSSFDateUtil.getJavaDate(cell
								.getNumericCellValue());

						if (dt != null && dt.getTime() > 0) {

							this.festival.add(dt);
						}
					}
				}

				/*
				 * 
				 * 读取特殊工作日
				 */

				cell = row.getCell((short) 1);

				if (cell != null) {

					if (HSSFDateUtil.isCellDateFormatted(cell)) {

						dt = HSSFDateUtil.getJavaDate(cell
								.getNumericCellValue());

						if (dt != null && dt.getTime() > 0) {

							// System.out.println(this.getDate(dt));

							this.workDay.add(dt);
						}
					}
				}
				index++;
			}

			fin.close();

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public File getExcel() {

		File excel = null;

		try {

			URL url =DayUtils.class.getResource("/");

			url = new URL(url,FILE_NAME);

			excel = new File(url.getPath());

			return excel;

		} catch (MalformedURLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return excel;

	}

	/**
	 * 从EXCEL文件中读取节假日
	 * 
	 * @return
	 */
	public static List<Date> getFestival() {

		return festival;
	}

	public List<Date> getSpecialWorkDay() {

		return this.workDay;

	}

	/**
	 * 
	 *  判断一个日期是否日节假日   法定节假日只判断月份和天，不判断年
	 * @param date
	 * @return
	 */

	public static boolean isFestival(Date date) {

		boolean festival = false;

		Calendar fcal = Calendar.getInstance();

		Calendar dcal = Calendar.getInstance();

		dcal.setTime(date);

		List<Date> list = getFestival();

		for (Date dt : list) {

			fcal.setTime(dt);

			// 法定节假日判断

			if (fcal.get(Calendar.MONTH) == dcal.get(Calendar.MONTH) &&

			fcal.get(Calendar.DATE) == dcal.get(Calendar.DATE))

			{

				festival = true;

			}

		}

		return festival;

	}

	/**
	 * 
	 * 周六周日判断
	 * @param date
	 * 
	 * @return
	 */

	public static boolean isWeekend(Date date) {

		boolean weekend = false;

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||

		cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

			weekend = true;
		}
		return weekend;

	}

	/**
	 * 
	 * 
	 * 是否是工作日  法定节假日和周末为非工作日
	 * @param date
	 * 
	 * @return
	 */

	public static boolean isWorkDay(Date date) {

		boolean workday = true;

		if (isFestival(date) || isWeekend(date)) {

			workday = false;
		}

		/*
		 * 
		 * 特殊工作日判断
		 */

		Calendar cal1 = Calendar.getInstance();

		cal1.setTime(date);

		Calendar cal2 = Calendar.getInstance();

		for (Date dt : workDay) {

			cal2.setTime(dt);

			if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&

			cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&

			cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)

			) {
				// 年月日相等为特殊工作日
				workday = true;
			}
		}
		return workday;

	}

	public static boolean getDate(Date date) {
		String dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		dt = df.format(date);
		return isWorkDay(date);

	}
	

	/**
	* 根据开始时间和结束时间返回时间段内的时间集合
	* @param beginDate
	* @param endDate
	* @return List
	*/
	public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);//把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		//使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			//根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);//把结束时间加入集合
		return lDate;
	}
	
	public static void main(String[] args) throws ParseException {
		DayUtils util = new DayUtils();
		System.out.println(util.isWorkDay(new SimpleDateFormat("yyyy-MM-dd").parse("2015-10-8")));
	}
	
}
