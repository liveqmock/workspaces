package com.iwgame.gm.p1.security.common.shared.util;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

/** * test.java Create on 2012-4-20 * * Copyright (c) 2012 by GreenShore Network * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.) */

/**
 * @ClassName: RegularHelper
 * @Description: 90%的验证都调用了Regular方法 但是本类也可删除大部分方法 涉及到正则的判断都直接穿参数和正则表达式
 *               但是为了方便业务类调用和有更直观的含义 建议不要这么做 Pattern的matcher已经被同步synchronized 所以
 *               此类的任何使用正则验证的方法都不需要同步
 * @author Jarvis -from network
 * @date 2012-4-20 下午04:26:31
 * @Version 1.0
 */
public class RegexGwtHelper {
	public RegexGwtHelper() {

	}

	// ------------------常量定义
	/**
	 * 密码正则表达式=
	 */
	public static final String PASSWORD = "[0-9]+";

	/**
	 * Email正则表达式=^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$
	 */
	public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

	/**
	 * 电话号码正则表达式=
	 * (^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|
	 * (^0?1[35]\d{9}$)
	 */
	public static final String PHONE = "(^(\\d{3,4}[-]?)?(\\d{7,8})$)";
	/**
	 * 手机号码正则表达式=^(13[0-9]|15[0|3|6|7|8|9]|18[0,5-9])\d{8}$
	 */
	public static final String MOBILE = "^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\\d{8}$";
	/**
	 * 联系方式
	 */
	public static final String PHONENUMBER = "(^(\\d{3,4}[-]?)?(\\d{7,8})$)|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\\d{8}$)";
	/**
	 * IP地址正则表达式
	 * ((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\
	 * d|[01]?\\d?\\d))
	 */
	public static final String IPADDRESS = "((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))";

	/**
	 * Integer正则表达式 ^-?(([1-9]\d*$)|0)
	 */
	public static final String INTEGER = "^-?(([1-9]\\d*$)|0)";
	/**
	 * 负整数正则表达式 <0 ^-[1-9]\d*$
	 */
	public static final String INTEGER_NEGATIVE = "^-[1-9]\\d*$";
	/**
	 * 正整数正则表达式 >0 ^[1-9]\d*$
	 */
	public static final String INTEGER_POSITIVE = "^[1-9]\\d*$";
	
	/**
	 * 1~5000的正整数
	 */
	public static final String SEALTIME_REGX = "^[1-4][0-9]{3}$|^5[0]{3}$|^[1-9][0-9]{2}$|^[1-9][0-9]$|^[1-9]$";
	/**
	 * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$
	 */
	public static final String DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";
	/**
	 * Number正则表达式 ^\\d+$
	 */
	public static final String NUMBER = "^\\d+$";
	/**
	 * 正Double正则表达式 >=0 ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　
	 */
	public static final String DOUBLE_NEGATIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";
	/**
	 * 负Double正则表达式 <= 0 ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$
	 */
	public static final String DOUBLE_POSITIVE = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";
	/**
	 * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁
	 */
	public static final String AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
	/**
	 * 邮编正则表达式 [1-9]\d{5}(?!\d) 国内6位邮编
	 */
	public static final String CODE = "[0-9]\\d{5}(?!\\d)";
	/**
	 * 匹配由数字、26个英文字母或者下划线组成的字符串 ^\w+$
	 */
	public static final String STR_ENG_NUM_ = "^\\w+$";
	/**
	 * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$
	 */
	public static final String STR_ENG_NUM = "^[A-Za-z0-9]+$";
	/**
	 * 匹配由数字和-组成的字符串 ^[0-9-]+$
	 */
	public static final String STR_NUM_ = "^[-0-9]+$";
	/**
	 * 匹配由26个英文字母组成的字符串 ^[A-Za-z]+$
	 */
	public static final String STR_ENG = "^[A-Za-z]+$";
	/**
	 * 匹配中文字符 ^[\Α-\￥]+$
	 */
	public static final String STR_CHINA = "^[\\Α-\\￥]+$";
	/**
	 * 过滤特殊字符串正则 regEx=
	 * "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	 */
	public static final String STR_SPECIAL = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	/**
	 * 只能输英文 数字 中文 ^[a-zA-Z0-9\一-\龥]+$
	 */
	public static final String STR_ENG_CHA_NUM = "^[a-zA-Z0-9\\一-\\龥]+$";
	/** 
     *   
     */
	/***
	 * 日期正则 支持： YYYY-MM-DD YYYY/MM/DD YYYY_MM_DD YYYYMMDD YYYY.MM.DD的形式
	 */
	public static final String DATE_ALL = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)"
			+ "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)"
			+ "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)"
			+ "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)"
			+ "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)"
			+ "(0?2)([-\\/\\._]?)(29)$)"
			+ "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)"
			+ "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|"
			+ "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";

	/**
	 * URL正则表达式 匹配 http www ftp
	 */
	public static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?"
			+ "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*"
			+ "(\\w*:)*(\\w*\\+)*(\\w*\\.)*"
			+ "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";

	/**
	 * 身份证正则表达式
	 */
	public static final String IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})"
			+ "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}"
			+ "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
	
	public static final String ID_CARD="^(^\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
	/**
	 * 1.匹配科学计数 e或者E必须出现有且只有一次 不含Dd 正则
	 * ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]
	 * ?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$
	 */
	public final static String SCIENTIFIC_A = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$";
	/**
	 * 2.匹配科学计数 e或者E必须出现有且只有一次 结尾包含Dd 正则
	 * ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012
	 * ]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$
	 */
	public final static String SCIENTIFIC_B = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$";
	/**
	 * 3.匹配科学计数 是否含有E或者e都通过 结尾含有Dd的也通过（针对Double类型） 正则
	 * ^[-+]?(\d+(\.\d*)?|\.\d+)([
	 * eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$
	 */
	public final static String SCIENTIFIC_C = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$";
	/**
	 * 4.匹配科学计数 是否含有E或者e都通过 结尾不含Dd 正则
	 * ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?
	 * \d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$
	 */
	public final static String SCIENTIFIC_D = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$";

	/**
	 * 匹配小数点前的数字或者没有点的全部数字
	 */
	public final static String BEFORE_RADIX_POINT = "^(\\d+)|(\\d+).\\d*$";

	/**
	 * 是否包含数据库关键字
	 */
	public final static String SQL_INJECT = "^[\\W|\\w]*(insert|select|delete|update|alter|where)[\\W|\\w]*$";
	
	/**
	 * 匹配html代码中<b></b>的内容
	 */
	public final static String HTML_B_TEXT="<b>([\\W|\\w]*)</b>";
	
	public final static String CATALOG_DEFAULT="^9{2}\\d*$";
	// //------------------验证方法
	/**
	 * 判断字段是否为空 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return null == str || str.trim().length() <= 0 ? true : false;
	}

	/**
	 * 判断字段是非空 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isSqlInject(String str) {
		return Regular(str, SQL_INJECT);
	}

	/**
	 * 字符串null转空
	 * 
	 * @param str
	 * @return boolean
	 */
	public static String nulltoStr(String str) {
		return isEmpty(str) ? "" : str;
	}

	/**
	 * 字符串null赋值默认值
	 * 
	 * @param str
	 *            目标字符串
	 * @param defaut
	 *            默认值
	 * @return String
	 */
	public static String nulltoStr(String str, String defaut) {
		return isEmpty(str) ? defaut : str;
	}

	public static boolean isPw(String str, String email) {
		if (str.length() < 6 && str.length() > 24) {
			return false;
		} else {

			return true;
		}
	}

	/**
	 * 判断字段是否为Email 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmail(String str) {
		if (str.length() > 64) {
			return false;
		} else {
			return Regular(str, EMAIL);
		}
	}

	public static boolean isName(String str) {
		if (str.length() > 64) {
			return false;
		} else {
			return Regular(str, STR_ENG_CHA_NUM);
		}
	}

	/**
	 * 判断是否为联系号码符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isPhoneNumber(String str) {
		if (str.length() > 24) {
			return false;
		} else {
			return Regular(str, PHONENUMBER);
		}
	}

	/**
	 * 判断是否为电话号码 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isPhone(String str) {
		if (str.length() > 24) {
			return false;
		} else {
			return Regular(str, PHONE);
		}
	}

	/**
	 * 判断是否为手机号码 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(String str) {
		if (str.length() > 11) {
			return false;
		} else {
			return Regular(str, MOBILE);
		}
	}

	/**
	 * 判断是否为Url 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isUrl(String str) {
		return Regular(str, URL);
	}

	/**
	 * 判断是否为IP地址 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIpaddress(String str) {
		return Regular(str, IPADDRESS);
	}

	/**
	 * 判断字段是否全部为数字 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumber(String str) {
		return Regular(str, NUMBER);
	}

	/**
	 * 判断字段是否为INTEGER 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isInteger(String str) {
		return Regular(str, INTEGER);
	}

	/**
	 * 判断字段是否为负整数正则表达式 <0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isINTEGER_NEGATIVE(String str) {
		return Regular(str, INTEGER_NEGATIVE);
	}

	/**
	 * 判断字段是否为正整数正则表达式 >0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isINTEGER_POSITIVE(String str) {
		return Regular(str, INTEGER_POSITIVE);
	}

	/**
	 * 判断字段是否为DOUBLE 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDouble(String str) {
		return Regular(str, DOUBLE);
	}

	/**
	 * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDOUBLE_NEGATIVE(String str) {
		return Regular(str, DOUBLE_NEGATIVE);
	}

	/**
	 * 判断字段是否为负浮点数正则表达式 <=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDOUBLE_POSITIVE(String str) {
		return Regular(str, DOUBLE_POSITIVE);
	}

	/**
	 * 判断字段是否为日期 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDate(String str) {
		return Regular(str, DATE_ALL);
	}

	/**
	 * 判断字段是否为年龄 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isAge(String str) {
		return Regular(str, AGE);
	}

	/**
	 * 判断字段是否超长 字串为空返回fasle, 超过长度{leng}返回ture 反之返回false
	 * 
	 * @param str
	 * @param leng
	 * @return boolean
	 */
	public static boolean isLengOut(String str, int leng) {
		return isEmpty(str) ? false : str.trim().length() > leng;
	}

	/**
	 * 判断字段是否为身份证 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIdCard(String str) {
		if (isEmpty(str))
			return false;
		if (str.trim().length() == 15 || str.trim().length() == 18) {
			return Regular(str, ID_CARD);
		} else {
			return false;
		}

	}

	/**
	 * 判断字段是否为邮编 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isCode(String str) {
		return Regular(str, CODE);
	}

	/**
	 * 判断字符串是不是全部是汉字
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isChina(String str) {
		return Regular(str, STR_CHINA);
	}

	/**
	 * 判断字符串是不是全部是英文字母
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEnglish(String str) {
		return Regular(str, STR_ENG);
	}

	/**
	 * 判断字符串是不是全部是英文字母+数字
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isENG_NUM(String str) {
		return Regular(str, STR_ENG_NUM);
	}

	/**
	 * @简述: 判断字符串中是否包含空白
	 * @param str
	 * @return boolean
	 */
	public static boolean isContainWhiteSpace(String str) {
		return Regular(str, "");
	}

	/**
	 * @简述: 是否是商品分类目录的默认分类
	 * @param str
	 * @return boolean
	 */
	public static boolean isCatalogDefault(String str) {
		return Regular(str, CATALOG_DEFAULT);
	}
	/**
	 * 判断字符串是不是全部是英文字母+数字+下划线
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isENG_NUM_(String str) {
		return Regular(str, STR_ENG_NUM_);
	}

	/**
	 * @简述: 提取小数点签名的数字
	 * @param str
	 * @return String
	 */
	public static String extractBeforePoint(String str) {
		return Extract(str, BEFORE_RADIX_POINT);
	}
	/**
	 * @简述: 提取<b></b>之间的文本内容
	 * @param str
	 * @return
	 * String
	 */
	public static String extractTextB(String str){
		return Extract(str, HTML_B_TEXT);
	}
	/**
	 * 匹配是否符合正则表达式pattern 匹配返回true
	 * 
	 * @param str
	 *            匹配的字符串
	 * @param pattern
	 *            匹配模式
	 * @return boolean
	 */
	private static boolean Regular(String str, String pattern) {
		if (null == str || str.trim().length() <= 0)
			return false;
		RegExp regExp = RegExp.compile(pattern);
		return regExp.test(str);
	}

	private static String Extract(String str, String pattern) {
		if (null == str || str.trim().length() <= 0)
			return "";
		RegExp regExp = RegExp.compile(pattern);
		MatchResult exec = regExp.exec(str);
		if (exec != null) {
			return exec.getGroup(1);
		} else {
			return "";
		}
	}

	/**
	 * 判断是不是科学计数法 如果是 返回true 匹配科学计数 e或者E必须出现有且只有一次 结尾不含D 匹配模式可参考本类定义的
	 * SCIENTIFIC_A，SCIENTIFIC_B,SCIENTIFIC_C,SCIENTIFIC_D 若判断为其他模式可调用
	 * Regular(String str,String pattern)方法
	 * 
	 * @param str
	 *            科学计数字符串
	 * @return boolean
	 */
	public static boolean isScientific(String str) {
		if (isEmpty(str))
			return false;
		return Regular(str, RegexGwtHelper.SCIENTIFIC_A);
	}

	public static void main(String[] args) {
		System.out.println(isCatalogDefault("991"));
	}
}
