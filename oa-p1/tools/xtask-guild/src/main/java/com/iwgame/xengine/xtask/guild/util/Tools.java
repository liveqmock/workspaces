/**      
 * Tools.java Create on 2012-6-26     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.function.CommonFunction;
import com.greenpineyu.fel.function.Function;
import com.iwgame.xengine.xtask.guild.worker.GuildTaskException;

/**
 * @ClassName: Tools
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-26 下午03:33:49
 * @Version 1.0
 * 
 */
public class Tools {

	private static final Logger logger = Logger.getLogger(Tools.class);

	public static String getProductId(final String channenId) {
		String[] xs = channenId.split("\\.");
		if (xs[xs.length - 1].length() <= 1) {
			return (xs[xs.length - 2] + "." + xs[xs.length - 1]).toUpperCase();
		} else {
			return xs[xs.length - 1].toUpperCase();
		}
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

	public static String getUsername(final String username, final boolean transform, final String encoding,
			final long offset) throws GuildTaskException {
		if (transform) {
			byte[] bytes = new byte[username.length()];
			for (int i = 0; i < username.length(); i++) {
				bytes[i] = (byte) (username.charAt(i) | offset);
			}
			try {
				return new String(bytes, encoding);
			} catch (UnsupportedEncodingException e) {
				throw new GuildTaskException("参赛者用户名解码失败！", e);
			}
		} else {
			return username;
		}
	}

	protected static Function abs = new CommonFunction() {

		@Override
		public String getName() {
			return "abs";
		}

		@Override
		public Object call(final Object[] arguments) {
			int value = 0;
			if ((arguments != null) && (arguments.length == 1)) {
				value = (Integer) arguments[0];
				value = value < 0 ? 0 : Math.abs(value);
			}
			return value;
		}
	};

	public static int calculateJoinerScore(final String formula, final Map<String, Object> params) {
		logger.info("计算积分，公式为【" + formula + "】，参数为【" + params + "】");
		FelEngine fel = new FelEngineImpl();
		logger.info("计算引擎对象为：" + fel);
		fel.addFun(abs);
		FelContext ctx = fel.getContext();
		logger.info("计算环境对象为：" + ctx);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			ctx.set(entry.getKey(), entry.getValue());
		}
		Expression compile = fel.compile(formula, ctx);
		logger.info("计算表达式对象为：" + fel);
		Object result = compile.eval(ctx);
		logger.info("计算结果对象为：" + result);
		if (result == null) {
			return 0;
		} else {
			return ((Double) result).intValue();
		}
	}

	public static void main(final String[] args) throws NumberFormatException, GuildTaskException {
		System.out.println(getUsername("\u00d6\u00d0\u00ce\u00c4\u00b2\u00e2\u00ca\u00d4", true, "GBK",
				Long.valueOf("ffffff00", 16)));
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("等级", 60);
		System.out.println(calculateJoinerScore(
				"abs(等级-39)+abs(abs(等级-49)*2+abs(等级-39))+abs(abs(等级-49)*2+abs(等级-39)+abs(等级-59))", p));
	}
}
