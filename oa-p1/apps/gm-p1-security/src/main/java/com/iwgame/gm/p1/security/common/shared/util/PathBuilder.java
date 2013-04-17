/** * PathBuilder.java Create on 2012-5-6 * * Copyright (c) 2012 by GreenShore Network * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.) */
package com.iwgame.gm.p1.security.common.shared.util;

/**
 * @简述： 路径构建
 * @作者： 张 扬
 * @版本： 1.0
 * @邮箱： zhang_yang@iwgame.com
 * @修改时间 2012-5-6 下午04:29:44
 */
public class PathBuilder {
	/**
	 * 参数头标识符
	 */
	private final static String PARAMS_HEADER = "?";
	private final static String PARAMS_VAL_LINKER = "=";
	private final static String PARAMS_LINKER = "&";

	/**
	 * @简述: 连接字符串
	 * @param strs
	 * @return String
	 */
	public static String joinLine(String... strs) {
		if (strs.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String str : strs) {
				sb.append(str);
			}
			return sb.toString();
		}
		return "";
	}

	/**
	 * @简述: 以flag连接传入的字符串
	 * @param linker
	 * @param strs
	 * @return String
	 */
	public static String joinLineWithFlag(String linker, String... strs) {
		if (strs != null && strs.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < strs.length; i++) {
				sb.append(strs[i]);
				if (i < strs.length - 1) {
					sb.append(linker);
				}
			}
			return sb.toString();
		}
		return "";
	}

	/**
	 * @简述: 连接请求参数字符串
	 * @param params
	 *            "k1", null, "k2", "v2", "k3"
	 * @return String ?k1=null&k2=v2&k3
	 */
	public static String joinParams(String... params) {
		if (params.length > 0) {
			StringBuilder sb = new StringBuilder();
			// 控制是否添加？
			boolean head = true;
			// 控制是否添加&和=
			boolean flag = false;
			for (String param : params) {
				if (flag) {
					sb.append(PARAMS_VAL_LINKER);
					flag = false;
				} else {
					flag = true;
					if (head) {
						head = false;
						sb.append(PARAMS_HEADER);
					} else {
						sb.append(PARAMS_LINKER);
					}
				}
				sb.append(param);
			}
			return sb.toString();
		}
		return "";
	}
}
