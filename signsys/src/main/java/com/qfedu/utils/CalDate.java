package com.qfedu.utils;

import java.util.Date;

public class CalDate {

	public static int getIntervalDays(Date fDate, Date oDate) {
		if (null == fDate || null == oDate) {
			return -1;
		}

		long intervalMilli = oDate.getTime() - fDate.getTime();

		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}
}
