package com.melonlee.api.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

	static SimpleDateFormat dateFomaFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String getFormat(Date date) {

		return dateFomaFormat.format(date);

	}

	public static String getNow() {

		return dateFomaFormat.format(new Date());

	}

	public static String subDate(String date) {

		return date.substring(0, 10);

	}

	public static String enCoding(String str)  {
		try {
			return new String(str.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getTimeStamp("2013-03-14 10:21:20"));
	}

	public static String subNow() {
		return getNow().substring(0, 10);
	}

	public static String getTimeStamp(String string) {

		return string.substring(11, 19);
	}
}
