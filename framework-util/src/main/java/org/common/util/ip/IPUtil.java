package org.common.util.ip;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class IPUtil {
	
	public static long ip2Long(String ip) {

		long nIP = 0;
		String[] seg = ip.split("\\.");
		if (seg.length != 4)
			return -1;
		nIP = (Long.valueOf(seg[0].trim()).longValue() << 24)
				| (Long.valueOf(seg[1].trim()).longValue() << 16)
				| (Long.valueOf(seg[2].trim()).longValue() << 8)
				| (Long.valueOf(seg[3].trim()).longValue() << 0);
		return nIP;
	
	}

	public static String getRealIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		String realIp = ip;
		if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
			String[] ipArray = ip.split(",");
			realIp = ipArray[0];
		}
		return realIp.trim();
	}

	// 将十进制整数形式转换成127.0.0.1形式的ip地址
	public static String long2IP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(ip2Long("180.168.220.50"));
		System.out.println(long2IP(new Long("3030965298")));
	}
}
