/****************************************************************
 *  系统名称  ：  '消息任务系统-公共服务-业务通道'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.sms.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.sms.model.Sms;

/**
 * 
 * 类说明
 * 
 * @简述： 短信发送辅助类
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间：2012-6-18 下午01:55:04
 */
public class CommonUtils {

	private static Logger logger = Logger.getLogger(CommonUtils.class);

	/**
	 * 将普通字符串转换成Hex编码字符串
	 * 
	 * @param dataCoding
	 *            编码格式，15表示GBK编码，其他默认ISO8859-1编码
	 * @param realStr
	 *            普通字符串
	 * @return Hex编码字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeHexStr(int dataCoding, String message) {
		String hexStr = "";
		if (message != null) {
			try {
				if (dataCoding == 15) {
					hexStr = new String(Hex.encodeHex(message.getBytes("GBK")));
				} else {
					hexStr = new String(Hex.encodeHex(message.getBytes("ISO8859-1")));
				}
			} catch (Exception e) {
				logger.error("字符串转换成Hex编码异常：" + e.toString());
			}
		}
		return hexStr;
	}

	/**
	 * 将Hex编码字符串转换成普通字符串
	 * 
	 * @param dataCoding
	 *            反编码格式，15表示GBK编码，其他默认ISO8859-1编码
	 * @param hexStr
	 *            Hex编码字符串
	 * @return 普通字符串
	 */
	public static String decodeHexStr(int dataCoding, String hexStr) {
		String realStr = null;
		try {
			if (hexStr != null) {
				if (dataCoding == 15) {
					realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "GBK");
				} else {
					realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "ISO8859-1");
				}
			}
		} catch (Exception e) {
			logger.error("Hex编码转换字符串异常：" + e.toString());
		}

		return realStr;
	}

	/**
	 * 将 短信下行 请求响应字符串解析到一个HashMap中
	 * 
	 * @param resStr
	 * @return
	 */
	public static Map<String, Object> parseResStr(String resStr) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] ps = resStr.split("&");
			for (String element : ps) {
				int ix = element.indexOf("=");
				if (ix != -1) {
					result.put(element.substring(0, ix), element.substring(ix + 1));
				}
			}
		} catch (Exception e) {
			logger.error("解析请求响应字符串出错：" + e);
		}
		return result;
	}

	/**
	 * 
	 * @param reg
	 *            正则表达式
	 * @param str
	 *            验证的字符串
	 * @return
	 */
	public static boolean regularExpCommon(String reg, String str) {
		if (reg != null) {
			Pattern pattern = Pattern.compile(reg);
			Matcher isNum = pattern.matcher(str.trim());
			if (!isNum.matches()) {
				logger.error("正则验证失败!不合法参数: [" + str + " ],忽略此条消息....");
				return false;
			}
			return true;
		} else {
			logger.error("正则验证失败，没有找到配置文件中正则表达式！ 请求验证资源:" + str);
			return false;
		}

	}

	/**
	 * 说明：生成单条下行实例URL
	 * 
	 * @param pid
	 *            SP编号
	 * @param password
	 *            SP密码
	 * @param phone
	 *            手机号
	 * @param message
	 *            短信内容
	 * @return
	 */
	public static String generateSingleSmsMtUrl(String pid, String password, String phone, String message) {
		// command操作命令、spid编号、sppassword密码，必填参数
		String command = ConfigProperties.getString("mt", "MT_REQUEST");
		String spid = ConfigProperties.getString(pid);
		String sppassword = ConfigProperties.getString(password);
		String spsc = ConfigProperties.getString("spsc");
		String sa = ConfigProperties.getString("sa");
		String da = phone;
		int dc = Integer.valueOf(ConfigProperties.getString("dc", "15"));
		String sm = CommonUtils.encodeHexStr(dc, message);// 消息转换为16进制数据
		StringBuilder smsUrl = new StringBuilder();// 组成url字符串
		smsUrl.append(ConfigProperties.getString("sms_mturl"));
		smsUrl.append("?command=" + command);
		smsUrl.append("&spid=" + spid);
		smsUrl.append("&sppassword=" + sppassword);
		smsUrl.append("&spsc=" + spsc);
		smsUrl.append("&sa=" + sa);
		smsUrl.append("&da=" + da);
		smsUrl.append("&sm=" + sm);
		smsUrl.append("&dc=" + dc);
		return smsUrl.toString();
	}

	/**
	 * 说明：生成相同内容群发下行实例URL
	 * 
	 * @param phone
	 *            电话号码字符串格式
	 *            <p>
	 *            "8613811111111,8613011111111"
	 *            </p>
	 * @param message
	 *            消息
	 * @return
	 */
	public static String generateSmsMultiMtUrl(String pid, String password, String phone, String message) {
		// command操作命令、spid编号、sppassword密码，必填参数
		String command = ConfigProperties.getString("multi_mt", "MULTI_MT_REQUEST");
		String spid = ConfigProperties.getString(pid);
		String sppassword = ConfigProperties.getString(password);
		String spsc = ConfigProperties.getString("spsc", "00");
		String sa = ConfigProperties.getString("sa");
		String das = phone;
		int dc = Integer.valueOf(ConfigProperties.getString("dc", "15"));
		String sm = CommonUtils.encodeHexStr(dc, message);// 下行内容以及编码格式，必填参数
		StringBuilder smsUrl = new StringBuilder();// 组成url字符串
		smsUrl.append(ConfigProperties.getString("sms_mturl"));
		smsUrl.append("?command=" + command);
		smsUrl.append("&spid=" + spid);
		smsUrl.append("&sppassword=" + sppassword);
		smsUrl.append("&spsc=" + spsc);
		smsUrl.append("&sa=" + sa);
		smsUrl.append("&das=" + das);
		smsUrl.append("&sm=" + sm);
		smsUrl.append("&dc=" + dc);

		return smsUrl.toString();
	}

	/**
	 * 说明：生成不相同内容群发下行实例URL
	 * 
	 * @param Smss
	 *            List集合
	 * @return
	 */
	public static String generateSmsMultiXMtUrl(String pid, String password, List<Sms> Smss) {
		// command操作命令、spid编号、sppassword密码，必填参数
		String command = ConfigProperties.getString("multix_mt", "MULTIX_MT_REQUEST");
		String spid = ConfigProperties.getString(pid);
		String sppassword = ConfigProperties.getString(password);
		String spsc = ConfigProperties.getString("spsc", "00");
		String sa = ConfigProperties.getString("sa");
		int dc = Integer.valueOf(ConfigProperties.getString("dc", "15"));
		StringBuffer dasm = new StringBuffer();// 消息转换为16进制数据
		for (int i = 0; i < Smss.size(); i++) {
			dasm.append(Smss.get(i).getPhone());
			dasm.append("/");
			dasm.append(CommonUtils.encodeHexStr(15, Smss.get(i).getMessage()));
			if (i < (Smss.size() - 1)) {
				dasm.append(",");
			}
		}
		StringBuilder smsUrl = new StringBuilder();// 组成url字符串
		smsUrl.append(ConfigProperties.getString("sms_mturl"));
		smsUrl.append("?command=" + command);
		smsUrl.append("&spid=" + spid);
		smsUrl.append("&sppassword=" + sppassword);
		smsUrl.append("&spsc=" + spsc);
		smsUrl.append("&sa=" + sa);
		smsUrl.append("&dasm=" + dasm.toString());
		smsUrl.append("&dc=" + dc);
		return smsUrl.toString();
	}

}
