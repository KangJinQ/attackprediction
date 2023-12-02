package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			//("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Date to String
	 * @param date ���ڶ���
	 * @return ���ڶ�����ַ�����ʽ
	 */
	public static String getString(Date date) {
		return sdf.format(date);
	}
	
	/**
	 * String to Date
	 * @param str �����ַ�����ʽ
	 * @return ���ڶ���
	 * @throws ParseException �������ִ���ʱ�׳����쳣
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
