package edu.etime.cms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 * @author 1
 *
 */
public class DateTools {
	/**
	 * 将日期转换为format格式的字符串
	 * @param date
	 * @return
	 */
	public static String getStringByDate(String format ,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * date格式的字符串转化为format格式的相应的日期
	 * @param format 需要转换为的日期格式
	 * @param date 需要转换的字符串日期
	 * @return
	 */
	public static Date getDateByDateString(String format,String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
