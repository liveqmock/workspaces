/****************************************************************
 *  文件名     ： XhttpSolrServiceImpl.java
 *  日期         :  2012-11-26
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.service.impl;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.gm.p1.task.bean.SecurityAccount;
import com.iwgame.gm.p1.task.bean.SecurityConfig;
import com.iwgame.gm.p1.task.service.XhttpSolrService;
import com.iwgame.gm.p1.task.util.MD5Util;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-26下午04:59:22
 * @版本: v1.0
 */
@Service
public class XhttpSolrServiceImpl implements XhttpSolrService {

	private final Logger logger = Logger.getLogger("oasecurity");

	private final String ip = "127.0.0.1";

	private final String start = "0";

	private final String row = "1";

	private final String sort = "";

	@Resource
	private SecurityConfig securityConfig;

	/**
	 * 根据玩家帐号查询出玩家的email地址
	 */
	@Override
	public String getEmailAddressByAccountId(SecurityAccount securityAccount) {
		try {
			// url
			String pid = securityAccount.getPid().toUpperCase();
			String q = "s_user_name:" + securityAccount.getUsername();
			String ts = String.valueOf(System.currentTimeMillis());
			String sign = getSign(pid, q, sort, start, row, ip, securityConfig.getXhttpservice_key(), ts);
			StringBuilder builder = new StringBuilder(securityConfig.getXhttpservice_url_solr());
			builder.append("/account/").append(pid).append("/").append("getAccounts");
			MultivaluedMap<String, String> map = new MultivaluedMapImpl();
			map.add("q", q);
			map.add("start", start);
			map.add("row", row);
			map.add("sort", sort);
			map.add("ip", ip);
			map.add("ts", ts);
			map.add("sign", sign);
			String result = senderHttpRequest(builder.toString(), map);
			if(!"".equals(result)){
				return reslutConvertByJsonToEmail(result);
			}else{
				return "";
			}
		} catch (Exception e) {
			logger.error("根据玩家帐号得到邮箱地址异常!", e);
			return "";
		}
	}

	/**
	 * 解析返回值Email
	 */
	protected String reslutConvertByJsonToEmail(String result) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(result);
			Integer ret = jsonObject.getInt("rc");
			if (0 == ret) {
				JSONObject response = jsonObject.getJSONObject("response");
				JSONArray docs = response.getJSONArray("docs");
				if (docs.size() > 0) {
					JSONObject userinfo = docs.getJSONObject(0);
					String email = userinfo.getString("s_user_email_new");
					if ("".equals(email)) {
						email = userinfo.getString("s_user_email");
					}
					return email;
				} else {
					return "";
				}
			} else {
				return "";
			}
		} catch (Exception e) {
			logger.error("xhttpservice 返回值解析错误!", e);
			return "";
		}
	}

	/**
	 * @说明: 生成签名 sgin
	 * @return: void
	 */
	private static String getSign(Object... params) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < params.length; i++) {
			builder.append(params[i].toString());
			if (i < (params.length - 1)) {
				builder.append("&");
			}
		}
		return MD5Util.md5sum(builder.toString());
	}

	/**
	 * @说明: 发送Htpp请求
	 * @return: int
	 */
	protected String senderHttpRequest(String url, MultivaluedMap<String, String> map) {
		try {
			WebResource client = Client.create().resource("");
			WebResource wr = client.path(url);
			String result = wr.queryParams(map).accept(MediaType.APPLICATION_JSON).get(String.class);
			return result;
		} catch (Exception e) {
			logger.error("调用xhttpservice_solr失败! 请求:" + map.toString(), e);
			return "";
		}
	}
}
