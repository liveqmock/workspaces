/****************************************************************
 *  系统名称  ：  '广告系统任务处理'
 *  文件名    ： LogMonitorServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.task.bean.MediaIp;
import com.iwgame.gm.p1.task.dao.AdcollectMonitorDao;
import com.iwgame.gm.p1.task.exception.TranslateException;
import com.iwgame.gm.p1.task.service.AdcollectMonitorService;
import com.iwgame.gm.p1.task.util.Constant;
import com.iwgame.gm.p1.task.util.HttpUtil;
import com.iwgame.gm.p1.task.util.IPTranslateURL;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 类说明
 * @简述： 接口服务实现类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-8-30 下午3:47:07
 */
public class AdcollectMonitorServiceImpl implements AdcollectMonitorService {
	private static Logger logger = Logger.getLogger(Constant.LOG_IP_TRANSLATE);
	private AdcollectMonitorDao adcollectMonitorDao;
	
	@Autowired
	public void setAdcollectMonitorDao(AdcollectMonitorDao adcollectMonitorDao) {
		this.adcollectMonitorDao = adcollectMonitorDao;
	}
	
	public void tanslateIp() {
		int i = 0;
		List<MediaIp> ipList = getIpList();
		if(ipList!=null && ipList.size()>0){
			logger.info("获取网吧媒体IP数据成功,共 " + ipList.size() +" 个.");
		    for (MediaIp mediaIp : ipList) {
		    	String ipString = mediaIp.getIp();
                //校验IP地址是否有效
		    	if (isIPCorrect(ipString)) {
		    		String mapResult = getTranslateResult(mediaIp.getIp());
		    		if(mapResult != null && !("").equals(mapResult)){
		    			Map<String, String> paramMap = new HashMap<String, String>();
		    			paramMap.put("area", mapResult);
		    			paramMap.put("id", mediaIp.getId());
		    			saveIpAreaInfo(paramMap);
		    			i++;
		    		}
				} else {
					logger.info("IP无效:" + ipString);
				}
			}
		    logger.info("成功翻译了 " + i +" 个IP.");
		} else {
			logger.info("本次没有获取到需要翻译的网吧IP列表.");
		}
		
		//释放资源
		ipList = null;
	}
	
	/**
	 * 翻译IP对应的区域信息
	 * @param ip
	 * @return
	 */
	private String getTranslateResult(String ip){
		String result = "";
		try {
			result = translateIpBySina(ip);
		} catch (TranslateException e) {
			//如果新浪接口不能翻译则使用网易接口
			try {
				logger.error(e);
				result = translateIpByYouDao(ip);
			} catch (TranslateException e1) {
				logger.error(e);
			}
		}
		return result;
	}
	
	
	/**
	 * 遍历数据库中的未翻译ip,每次取200条
	 * @return
	 */
	private List<MediaIp> getIpList(){
		List<MediaIp> ipResult = null;
		try {
			ipResult = adcollectMonitorDao.getIpList();
		} catch (Exception e) {
            logger.error("取得IP列表出错," + e);
		}
		return ipResult;
	}
	
	/**
	 * 将翻译结果放入数据库
	 * @param paramMap
	 */
	private void saveIpAreaInfo(Map<String, String> paramMap){
	    try {
		    adcollectMonitorDao.addArea(paramMap);
		    logger.info("存储IP对应区域成功 !");
		} catch (Exception e) {
            logger.error("存储IP对应区域出错###########" + e);
		}
	}
	
	/**
	 * 使用新浪接口
	 * @param ip
	 * @return
	 * @throws TranslateException
	 */
	private String translateIpBySina(String ip) throws TranslateException{
		try {
			MultivaluedMap<String, String> param = new  MultivaluedMapImpl();
			param.add("format", "java");
			param.add("ip", ip);
			String areaReult = HttpUtil.getHttpRequestResult(IPTranslateURL.sinaURL, param);
			//若是无效的 新浪返回 -2
			if (("-2").equals(areaReult.trim())) {
				areaReult = "未知地区";
			}else if(areaReult.trim().startsWith("-1")) {
				areaReult = "未知地区";
			}else {
				//使用正则提取出area
				areaReult = getArea(areaReult);
			}
			logger.info("新浪接口翻译成功,ip=" + ip + ", area=" + areaReult);
			return areaReult;
		} catch (Exception e) {
			throw new TranslateException("使用新浪接口翻译失败," + e);
		}

	}
	
	/**
	 * 使用有道接口
	 * @param ip
	 * @return
	 * @throws TranslateException
	 */
	private String translateIpByYouDao(String ip) throws TranslateException{
		try {
			MultivaluedMap<String, String> param = new  MultivaluedMapImpl();
			param.add("type", "ip");
			param.add("q", ip);
			String areaReult = HttpUtil.getHttpRequestResult(IPTranslateURL.youdaoURL, param);
			//取得ip区域返回结果
	        List<String> resultList = getYouDaoContext(areaReult);
			if (resultList == null || resultList.size() == 0) {
				areaReult = "未知地区";
			}else {
				areaReult = (String) resultList.get(0);
				areaReult = getArea(areaReult);
			}
			logger.info("有道接口翻译成功,ip=" + ip + ", area=" + areaReult);
	        return areaReult;
		} catch (Exception e) {
			throw new TranslateException("使用有道接口翻译失败," + e);
		}
	}
	
    /**
     * 提取有道部分的区域地址位置
     * @param html
     * @return
     */
    private List<String> getYouDaoContext(String html) {
		List<String> resultList = new ArrayList<String>();
		//防止IP访问频繁做限制
		if (html.contains("请输入四位字母验证码")) {
			resultList.add("fail");
		}else {
			Pattern p = Pattern.compile("<location>([^</loaction>]*)");//匹配<location>开头，</location>结尾的文档
			Matcher m = p.matcher(html);//开始编译
			while (m.find()) {
				resultList.add(m.group(1));//获取被匹配的部分
			}
		}
		return resultList;
	}
	
    //判断是否是IP
    private boolean isIPCorrect(String ip) {
    	boolean result = false;
    	try {
    		Pattern p = Pattern.compile("^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$");
    		Matcher m = p.matcher(ip);
    		result = m.matches();
		} catch (Exception e) {
			logger.error(e);
			result = false;
		}
		return result;
    }
    
    //根据正则取得地址
    private String getArea(String content){
    	String regex ="北京|天津|河北|山西|内蒙古|辽宁|吉林|黑龙江|上海|江苏|浙江|安徽|福建|江西|山东|河南|湖北|湖南|广东|广西|海南|重庆|四川|贵州|云南|西藏|陕西|甘肃|青海|宁夏|新疆|香港|澳门|台湾";
    	String area = "";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		if (m.find()) {
			area = m.group();
		}else {
			area = "海外";
		}
		return area;
    }
}
