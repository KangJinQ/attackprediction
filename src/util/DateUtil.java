package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			//("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Date to String
	 * @param date 日期对象
	 * @return 日期对象的字符串形式
	 */
	public static String getString(Date date) {
		return sdf.format(date);
	}
	
	/**
	 * String to Date
	 * @param str 日期字符串形式
	 * @return 日期对象
	 * @throws ParseException 解析出现错误时抛出的异常
	 */
	public static Date getDate(String str)  {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
