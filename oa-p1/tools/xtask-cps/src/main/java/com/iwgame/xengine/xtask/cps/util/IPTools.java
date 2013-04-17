/**      
 * IPTools.java Create on 2012-5-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.util;

/**
 * @ClassName: IPTools
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-28 上午09:00:54
 * @Version 1.0
 * 
 */
public class IPTools {

	/**
	 * 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
	 * 通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1
	 */
	public static long ipToLong(final String ipAddress) {
		long[] ip = new long[4];
		// 先找到IP地址字符串中.的位置
		int position1 = ipAddress.indexOf(".");
		int position2 = ipAddress.indexOf(".", position1 + 1);
		int position3 = ipAddress.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型
		ip[0] = Long.parseLong(ipAddress.substring(0, position1));
		ip[1] = Long.parseLong(ipAddress.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(ipAddress.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(ipAddress.substring(position3 + 1));
		return (ip[0] << 24L & 0xFF000000L) | (ip[1] << 16L & 0xFF0000L)
				| (ip[2] << 8L & 0xFF00L) | (ip[3] << 0L & 0xFFL);
	}

	/**
	 * 将十进制整数形式转换成127.0.0.1形式的ip地址 将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。
	 * 通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。
	 * 通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。 通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP。
	 */
	public static String longToIP(final long ipAddress) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位
		sb.append(String.valueOf((ipAddress & 0xFFFFFFFF >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((ipAddress & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((ipAddress & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((ipAddress & 0x000000FF)));
		return sb.toString();
	}

	public static void main(final String[] args) {
		long ip = Long.valueOf("-566078646");
		System.out.println(longToIP(ip));
		System.out.println(ipToLong("127.0.0.1"));
	}
}
