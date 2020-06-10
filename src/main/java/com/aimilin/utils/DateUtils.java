package com.aimilin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间操作工具类
 * 
 * @author LiuJunGuang
 * @date 2015年9月25日上午10:02:56
 */
public class DateUtils {
	/**
	 * @fields STR_SHORT_DATE yyyyMMdd
	 */
	public static final String STR_SHORT_DATE = "yyyyMMdd";
	/**
	 * @fields STR_DATE yyyy-MM-dd
	 */
	public static final String STR_DATE = "yyyy-MM-dd";
	/**
	 * @fields STR_DATE yyyy年MM月dd日
	 */
	public static final String STR_CHINESE_DATE = "yyyy年MM月dd日";

	/**
	 * @fields STR_SHORT_TIME HHmmss
	 */
	public static final String STR_SHORT_TIME = "HHmmss";

	/**
	 * @fields STR_TIME HHmmss
	 */
	public static final String STR_TIME = "HH:mm:ss";

	/**
	 * @fields STR_SHORT_DATE_TIME yyyyMMddHHmmss
	 */
	public static final String STR_SHORT_DATE_TIME = "yyyyMMddHHmmss";

	/**
	 * @fields STR_DATE_TIME yyyy-MM-dd HH:mm:ss
	 */
	public static final String STR_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * @fields STR_SHORT_DATE_TIME_MILLISECOND yyyyMMddHHmmssSSS
	 */
	public static final String STR_SHORT_DATE_TIME_MILLISECOND = "yyyyMMddHHmmssSSS";

	/**
	 * @fields STR_DATE_TIME_MILLISECOND yyyy-MM-dd HH:mm:ss SSS
	 */
	public static final String STR_DATE_TIME_MILLISECOND = "yyyy-MM-dd HH:mm:ss SSS";

	/**
	 * @fields DF_DATE_TIME yyyy-MM-dd HH:mm:ss
	 */
	public static final SimpleDateFormat SDF_DATE_TIME = new SimpleDateFormat(STR_DATE_TIME);

	/**
	 * @fields FM_SHORT_DATE yyyyMMdd
	 */
	public static final DateTimeFormatter FM_SHORT_DATE = DateTimeFormatter.ofPattern(STR_SHORT_DATE);

	/**
	 * @fields FM_DATE yyyy-MM-dd
	 */
	public static final DateTimeFormatter FM_DATE = DateTimeFormatter.ofPattern(STR_DATE);

	/**
	 * @fields FM_SHORT_TIME HHmmss
	 */
	public static final DateTimeFormatter FM_SHORT_TIME = DateTimeFormatter.ofPattern(STR_SHORT_TIME);

	/**
	 * @fields FM_TIME HH:mm:ss
	 */
	public static final DateTimeFormatter FM_TIME = DateTimeFormatter.ofPattern(STR_TIME);

	/**
	 * @fields FM_SHORT_DATE_TIME yyyyMMddHHmmss
	 */
	public static final DateTimeFormatter FM_SHORT_DATE_TIME = DateTimeFormatter.ofPattern(STR_SHORT_DATE_TIME);

	/**
	 * @fields FM_DATE_TIME yyyy-MM-dd HH:mm:ss
	 */
	public static final DateTimeFormatter FM_DATE_TIME = DateTimeFormatter.ofPattern(STR_DATE_TIME);

	/**
	 * @fields FM_SHORT_DATE_TIME_MILLISECOND yyyyMMddHHmmssSSS
	 */
	public static final DateTimeFormatter FM_SHORT_DATE_TIME_MILLISECOND = DateTimeFormatter
			.ofPattern(STR_SHORT_DATE_TIME_MILLISECOND);

	/**
	 * @fields FM_DATE_TIME_MILLISECOND yyyy-MM-dd HH:mm:ss SSS
	 */
	public static final DateTimeFormatter FM_DATE_TIME_MILLISECOND = DateTimeFormatter
			.ofPattern(STR_DATE_TIME_MILLISECOND);

	/**
	 * 获取系统当前短日期时间
	 * 
	 * @author LiuJunGuang
	 * @return yyyyMMddHHmmss
	 * @date 2015年9月24日下午6:03:02
	 */
	public static String getShortDateTime() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(FM_SHORT_DATE_TIME);
	}

	/**
	 * 获取系统日期时间
	 * 
	 * @author LiuJunGuang
	 * @return yyyy-MM-dd HH:mm:ss
	 * @date 2015年9月24日下午6:15:08
	 */
	public static String getDateTime() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(FM_DATE_TIME);
	}

	/**
	 * 根据指定的格式获取当前时间
	 * 
	 * @author WangJie
	 * @param format
	 * @return
	 * @date Sep 4, 201411:07:39 AM
	 */
	public static String getDateTime(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 获取系统当前短日期时间毫秒
	 * 
	 * @author LiuJunGuang
	 * @return yyyyMMddHHmmssSSS
	 * @date 2015年9月24日下午6:06:41
	 */
	public static String getShortDateTimeMillisecond() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(FM_SHORT_DATE_TIME_MILLISECOND);
	}

	/**
	 * 获取系统当前日期时间毫秒
	 * 
	 * @author LiuJunGuang
	 * @return yyyy-MM-dd HH:mm:ss SSS
	 * @date 2015年9月24日下午6:15:58
	 */
	public static String getDateTimeMillisecond() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(FM_DATE_TIME_MILLISECOND);
	}

	/**
	 * 获取系统短日期
	 * 
	 * @author LiuJunGuang
	 * @return yyyyMMdd
	 * @date 2015年9月24日下午6:16:34
	 */
	public static String getShortDate() {
		LocalDate date = LocalDate.now();
		return date.format(FM_SHORT_DATE);
	}

	/**
	 * 获取系统日期
	 * 
	 * @author LiuJunGuang
	 * @return yyyy-MM-dd
	 * @date 2015年9月24日下午6:17:42
	 */
	public static String getDate() {
		LocalDate date = LocalDate.now();
		return date.format(FM_DATE);
	}

	/**
	 * 获取系统当前短时间
	 * 
	 * @author LiuJunGuang
	 * @return HHmmss
	 * @date 2015年9月24日下午6:18:33
	 */
	public static String getShortTime() {
		LocalTime time = LocalTime.now();
		return time.format(FM_SHORT_TIME);
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @author LiuJunGuang
	 * @return HH:mm:ss
	 * @date 2015年9月24日下午6:18:35
	 */
	public static String getTime() {
		LocalTime time = LocalTime.now();
		return time.format(FM_TIME);
	}

	/**
	 * 日期转成秒数
	 * 
	 * @author LiuJunGuang
	 * @return Long 秒数
	 * @date 2015年6月29日下午8:01:02
	 */
	public static long dateToSeconds(Date date) {
		return date.getTime() / 1000;
	}

	/**
	 * 日期字符串转成秒数
	 * 
	 * @author LiuJunGuang
	 * @param dateStr
	 *            日期字符串
	 * @param pattern
	 *            日期字符串格式化类型
	 * @return long 秒数
	 * @date 2015年9月25日上午10:14:35
	 */
	public static long dateToSeconds(String dateStr, String pattern) {
		return parse(dateStr, pattern).getTime() / 1000;
	}

	public static long dateToSeconds(String dateStr) {
		return dateToSeconds(dateStr, STR_DATE_TIME);
	}

	/**
	 * 秒数转日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @author LiuJunGuang
	 * @param seconds
	 *            秒数
	 * @return 日期字符串 yyyy-MM-dd HH:mm:ss
	 * @date 2015年6月29日下午8:04:32
	 */
	public static String secondsToDate(long seconds) {
		return format(new Date(seconds * 1000), STR_DATE_TIME);
	}

	/**
	 * 秒数转日期对象
	 * 
	 * @author LiuJunGuang
	 * @param seconds
	 *            秒数
	 * @return
	 * @date 2015年12月8日下午1:56:11
	 */
	public static Date toDate(long seconds) {
		return new Date(seconds * 1000);
	}

	/**
	 * 秒数转成日期字符串，转换之后的日期使用 指定的格式
	 * 
	 * @author LiuJunGuang
	 * @param seconds
	 *            秒数
	 * @param pattern
	 *            日期格式化
	 * @return 格式化之后的日期字符串
	 * @date 2015年9月25日上午10:22:40
	 */
	public static String secondsToDate(long seconds, String pattern) {
		return format(new Date(seconds * 1000), pattern);
	}

	/**
	 * 根据毫秒数转换成对应格式的日期
	 * 
	 * @author LiuJunGuang
	 * @param millisecond
	 *            毫秒数
	 * @param pattern
	 *            日期格式
	 * @return 格式化之后的日期字符串
	 * @date 2015年9月24日下午7:12:29
	 */
	public static String millisecondToDate(long millisecond, String pattern) {
		return format(new Date(millisecond), pattern);
	}

	/**
	 * 将日期格式化成指定格式的字符串
	 * 
	 * @author LiuJunGuang
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式化字符串
	 * @return 格式化之后的日期字符串
	 * @date 2015年9月25日上午10:25:28
	 */
	public static String format(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	/**
	 * 根据指定的日期格式将时间格式化为只有时间或者只有日期的时间进行比较
	 * 例:将 yyyy-MM-dd HH:mm:ss格式的时间通过HH:mm:ss转换后的时间为HH:mm:ss
	 * @author wuxq
	 * @param date 日期
	 * @param pattern 日期格式化字符串
	 * @return 格式化之后的日期
	 */
	public static Date formatToDate(Date date, String pattern) {
		return parse(format(date, pattern), pattern);
	}
	

	/**
	 * 将日期格式化成 yyyy-MM-dd HH:mm:ss 字符串
	 * 
	 * @author LiuJunGuang
	 * @param date
	 *            日期
	 * @return
	 * @date 2015年9月25日上午10:31:35
	 */
	public static String format(Date date) {
		return format(date, STR_DATE_TIME);
	}

	/**
	 * 将字符串解析成日期
	 * 
	 * @author LiuJunGuang
	 * @param dateStr
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return Date
	 * @date 2015年9月25日上午10:27:44
	 */
	public static Date parse(String dateStr, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			throw new DateTimeParseException("Text cannot be parsed to a Date", pattern, 0);
		}
	}

	/**
	 * 将字符串(yyyy-MM-dd HH:mm:ss)解析成日期
	 * 
	 * @author LiuJunGuang
	 * @param dateStr
	 *            日期字符串，必须是 yyyy-MM-dd HH:mm:ss 格式的日期字符串
	 * @return
	 * @date 2015年9月25日上午10:33:04
	 */
	public static Date parse(String dateStr) {
		try {
			return parse(dateStr, STR_DATE_TIME);
		} catch (Exception e) {
			return parse(dateStr, STR_DATE);
		}
	}
	
	/**
	 * 添加指定年数
	 * 
	 * @author GengBaiHui
	 * @param date
	 * @param addYear
	 * @return
	 * @date 2016年12月16日下午2:15:15
	 */
	public static Date addYear(Date date,int addYear){
		return add(date,Calendar.YEAR,addYear);
	}

	/**
	 * 将日期增加指定的天数
	 * 
	 * @author LiuJunGuang
	 * @param dateStr
	 *            日期字符串
	 * @param daysToAdd
	 *            增加的天数，整数 是添加，负数 是减少
	 * @param pattern
	 *            日期字符串格式
	 * @date 2015年9月25日上午10:34:26
	 */
	public static String addDays(String dateStr, int daysToAdd, String pattern) {
		Date date = parse(dateStr, pattern);
		return addDays(date, daysToAdd, pattern);
	}

	/**
	 * 将日期增加指定的天数
	 * 
	 * @author LiuJunGuang
	 * @param dateStr
	 *            日期字符串
	 * @param daysToAdd
	 *            增加的天数，正数 是添加，负数 是减少
	 * @param pattern
	 *            日期字符串格式
	 * @date 2015年9月25日上午10:34:26
	 */
	public static String addDays(Date date, int daysToAdd, String pattern) {
		return format(addDays(date, daysToAdd), pattern);
	}

	/**
	 * 将日期增加指定的天数
	 * 
	 * @author LiuJunGuang
	 * @param dateStr
	 *            日期字符串
	 * @param daysToAdd
	 *            增加的天数，整数 是添加，负数 是减少
	 * @param pattern
	 *            日期字符串格式
	 * @date 2015年9月25日上午10:34:26
	 */
	public static Date addDays(Date date, int daysToAdd) {
		return add(date, Calendar.DAY_OF_MONTH, daysToAdd);
	}

	/**
	 * 日期增加或减少指定的小时数
	 * 
	 * @author LiuJunGuang
	 * @param date
	 * @param hoursToAdd
	 *            小时增量
	 * @return
	 * @date 2014年8月22日下午6:27:15
	 */
	public static Date addHours(Date date, int hoursToAdd) {
		return add(date, Calendar.HOUR, hoursToAdd);
	}

	/**
	 * 日期增加或减少指定的分钟数
	 * 
	 * @author LiuJunGuang
	 * @param date
	 * @param minutesToAdd
	 *            分钟增量
	 * @return
	 * @date 2014年8月22日下午6:27:15
	 */
	public static Date addMinutes(Date date, int minutesToAdd) {
		return add(date, Calendar.MINUTE, minutesToAdd);
	}

	/**
	 * 日期增加或减少指定的秒数
	 * 
	 * @author LiuJunGuang
	 * @param date
	 *            日期
	 * @param secondsToAdd
	 *            秒增量
	 * @return
	 * @date 2014年8月22日下午6:27:15
	 */
	public static Date addSeconds(Date date, int secondsToAdd) {
		return add(date, Calendar.SECOND, secondsToAdd);
	}

	/**
	 * 日期增加或减少指定的周
	 * 
	 * @author LiuJunGuang
	 * @param date
	 *            日期
	 * @param secondsToAdd
	 *            周增量
	 * @return
	 * @date 2014年8月22日下午6:27:15
	 */
	public static Date addWeeks(Date date, int weeksToAdd) {
		return add(date, Calendar.WEEK_OF_MONTH, weeksToAdd);
	}

	/**
	 * 日期增加或减少指定的周
	 * 
	 * @author LiuJunGuang
	 * @param date
	 *            日期
	 * @param secondsToAdd
	 *            月增量
	 * @return
	 * @date 2014年8月22日下午6:27:15
	 */
	public static Date addMonths(Date date, int monthsToAdd) {
		return add(date, Calendar.WEEK_OF_MONTH, monthsToAdd);
	}

	/**
	 * 日期指定的属性 增加或减少指定的值
	 * 
	 * @author LiuJunGuang
	 * @param date
	 *            日期
	 * @param field
	 *            属性
	 * @param amount
	 *            增量
	 * @return 修改之后的日期
	 * @date 2015年9月25日上午11:46:19
	 */
	public static Date add(Date date, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, amount);
		return c.getTime();
	}

	/**
	 * 获取今天的开始时间秒数,及今天 00:00:00 的秒数
	 * 
	 * @author LiuJunGuang
	 * @return long 秒数
	 * @date 2014年9月9日下午5:44:12
	 */
	public static long getSecondsStartOfToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis() / 1000;
	}

	/**
	 * 格式化map中的日期，要求Map中的日期为秒数
	 * 
	 * @param map
	 * @param dateParameter
	 *            属性名称
	 * @param pattern
	 *            格式化类型
	 */
	public static void formatDate(Map<String, Object> map, String pattern, String[] dateParameter) {
		try {
			if (map == null || map.isEmpty())
				return;
			for (String param : dateParameter) {
				String time = map.get(param).toString();
				if (time == null || "".equals(time))
					return;
				String date = secondsToDate(Long.valueOf(time), pattern);
				map.put(param, date);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 格式化map中日期属性，属性必须是秒数，日期格式化格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param map
	 * @param dateParameter
	 */
	public static void formatDate(Map<String, Object> map, String... dateParameter) {
		formatDate(map, STR_DATE_TIME, dateParameter);
	}

	/**
	 * 格式化列表类型的日期，日期要求为秒数，日期格式化格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param list
	 * @param dateParameter
	 */
	public static void formatDate(List<Map<String, Object>> list, String... dateParameter) {
		formatDate(list, STR_DATE_TIME, dateParameter);
	}

	/**
	 * 格式化列表类型的日期，日期要求为秒数
	 * 
	 * @param list
	 * @param dateParameter
	 * @param pattern
	 *            格式化模式
	 */
	public static void formatDate(List<Map<String, Object>> list, String pattern, String[] dateParameter) {
		if (list == null || list.isEmpty())
			return;
		for (Map<String, Object> map : list) {
			formatDate(map, pattern, dateParameter);
		}
	}

	/**
	 * 产生指定日期范围内的日期
	 * 
	 * @author LiuJunGuang
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 * @date 2014年7月11日下午2:43:14
	 */
	public static String randomDate(Date startDate, Date endDate) {
		if (startDate.getTime() >= endDate.getTime()) {
			return null;
		}
		long date = RandomUtils.nextLong(startDate.getTime(), endDate.getTime());
		return millisecondToDate(date, STR_DATE_TIME);
	}

	/**
	 * 获取当前时间秒数 1397361733
	 * 
	 * @author LiuJunGuang
	 * @return
	 * @date 2014年8月11日上午11:11:34
	 */
	public static long getDateTimeSeconds() {
		return new Date().getTime() / 1000;
	}

	/**
	 * 获取当前时间秒数 1397361733
	 * 
	 * @author LiuJunGuang
	 * @return
	 * @date 2014年8月11日上午11:11:34
	 */
	public static long getSeconds() {
		return getDateTimeSeconds();
	}

	/**
	 * 获取指定日期的当前开始时间，返回未秒数
	 * 
	 * @author LiuJunGuang
	 * @param date
	 *            指定日期字符串
	 * @param pattern
	 *            日期格式
	 * @return
	 * @date 2014年8月24日下午3:11:50
	 */
	public static long getStartTimeOfDay(String date, String pattern) {
		SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
		try {
			Date time = formatDate.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			return c.getTimeInMillis() / 1000;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取一天的结束时间返回秒数，每天的结束时间为 23:59:59
	 * 
	 * @author LiuJunGuang
	 * @param date
	 * @param pattern
	 * @return
	 * @date 2014年8月24日下午3:14:05
	 */
	public static long getEndTimeOfDay(String date, String pattern) {
		SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
		try {
			Date time = formatDate.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			return c.getTimeInMillis() / 1000;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 比较两个日期大小
	 * 
	 * @author LiuJunGuang
	 * @param startDate
	 * @param endDate
	 * @param pattern
	 *            日期格式
	 * @return startDate > endDate 返回 1， startDate = endDate 返回0，startDate < endDate 返回 -1
	 * @date 2015年3月5日下午6:08:29
	 */
	public static int compareDate(String startDate, String endDate, String pattern) {
		Date date1 = parse(startDate, pattern);
		Date date2 = parse(endDate, pattern);
		if (date1.before(date2))
			return -1;
		if (date1.after(date2))
			return 1;
		return 0;
	}
	

	/**
	 * 指定的时间是否在 日期范围之内
	 * 
	 * @author LiuJunGuang
	 * @param time
	 *            指定时间，单位为秒
	 * @param startTime
	 *            开始时间(包含)
	 * @param endTime
	 *            结束时间(不包含)
	 * @return 如果在之间 则返回true，否则返回false
	 * @date 2015年4月14日下午7:16:11
	 */
	public static boolean isBetween(Long time, String startTime, String endTime) {
		if (time == null)
			throw new NullPointerException("time must not be null!");
		try {
			Date startDate = parse(startTime, STR_DATE_TIME);
			Date endDate = parse(endTime, STR_DATE_TIME);
			long timeTemp = time * 1000;
			return startDate.getTime() <= timeTemp && timeTemp < endDate.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 判断指定的时间是否在开始和结束时间范围内
	 * @param time 指定时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public static boolean isBetween(Date time, Date startTime, Date endTime) {
		return startTime.before(time) && endTime.after(time);
	}

	/**
	 * 计算两个日期之间的间隔天数, <br>
	 * 另外也可以使用：DurationFormatUtils.formatPeriod(long startMillis,long endMillis,String "y-M-d"); 支付自定义格式
	 * 
	 * @author LiuJunGuang
	 * @param startDate
	 * @param endDate
	 * @return
	 * @date 2015年12月8日下午12:14:28
	 */
	public static long betweenDays(Date startDateInclusive, Date endDateExclusive) {
		LocalDate startDate = toLocalDate(startDateInclusive);
		LocalDate endDate = toLocalDate(endDateExclusive);
		return endDate.toEpochDay() - startDate.toEpochDay();
	}

	/**
	 * 将日期转换成LocalDate
	 * 
	 * @author LiuJunGuang
	 * @param date
	 *            日期
	 * @return
	 * @date 2015年12月8日下午12:27:45
	 */
	public static LocalDate toLocalDate(Date date) {
		Objects.nonNull(date);
		ZonedDateTime zdt = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		return zdt.toLocalDate();
	}

	/**
	 * 获取上周开始时间，日期为上周周一，时间为 00:00:00
	 * 
	 * @author LiuJunGuang
	 * @return
	 * @date 2016年3月28日下午3:24:14
	 */
	public static String getLastWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.WEEK_OF_YEAR, -1); // back to last week
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return DateFormatUtils.format(cal, "yyyy-MM-dd 00:00:00");
	}

	/**
	 * 获取上周结束时间，日期为上周周日，结束时间为 23:59:59
	 * 
	 * @author LiuJunGuang
	 * @return
	 * @date 2016年3月28日下午3:25:07
	 */
	public static String getLastWeekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.WEEK_OF_YEAR, -1); // back to last week
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return DateFormatUtils.format(cal, "yyyy-MM-dd 23:59:59");
	}

	/**
	 * 日期转换 转换为MM月dd日格式
	 * 
	 * @author ChenYe
	 * @param time
	 *            格式为yyyy-MM-dd HH:mm:ss
	 * @return
	 * @date 2016年6月17日下午12:02:09
	 */
	public static String subsringMonthAndDay(String time) {
		try {
			SimpleDateFormat sdf = null;
			String month = time.substring(5, 7);
			String day = time.substring(8, 10);
			if (month.startsWith("0")) {
				if (day.startsWith("0")) {
					sdf = new SimpleDateFormat("M月d日");
				} else {
					sdf = new SimpleDateFormat("M月dd日");
				}

			} else {
				if (day.startsWith("0")) {
					sdf = new SimpleDateFormat("MM月d日");
				} else {
					sdf = new SimpleDateFormat("MM月dd日");
				}
			}
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf2.parse(time);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * 获取当天开始时间
	 * 
	 * @author ChenYe
	 * @return
	 * @date 2017年1月3日下午5:14:48
	 */
	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		return todayStart.getTime();
	}

	/**
	 * 获取当天结束时间
	 * 
	 * @author ChenYe
	 * @return
	 * @date 2017年1月3日下午5:15:14
	 */
	public static Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		return todayEnd.getTime();
	}

	/**
	 * 获取指定日期的结束时间
	 * 
	 * @author ChenYe
	 * @param time
	 * @return
	 * @date 2017年8月2日下午5:56:39
	 */
	public static Date getEndTime(Date time) {
		String date = format(time, STR_DATE) + " 23:59:59";
		return parse(date);
	}
	
    /**  
     * 两个时间之间的天数  
     *   
     * @param date1  
     * @param date2  
     * @return  
     */    
    public static long getDays(String date1, String date2) {    
        if (date1 == null || date1.equals(""))    
            return 0;    
        if (date2 == null || date2.equals(""))    
            return 0;    
        // 转换为标准时间    
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");    
        java.util.Date date = null;    
        java.util.Date mydate = null;    
        try {    
            date = myFormatter.parse(date1);    
            mydate = myFormatter.parse(date2);    
        } catch (Exception e) {    
        }    
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);    
        return day;    
    }  

}
