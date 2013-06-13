/****************************************************************
 *  文件名     ： OaSecuritySearchServiceImpl.java
 *  日期         :  2012-12-10
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.xcloud.searcher.core.dynamic.DBConnection;
import com.iwgame.xcloud.searcher.model.ParamBean;
import com.iwgame.xcloud.searcher.model.SearchResult;
import com.iwgame.xcloud.searcher.service.OASecuritySearchService;
import com.iwgame.xcloud.searcher.util.SecurityUtil;
import com.iwgame.xvalidators.Xvalidator;

/**
 * @描述:  	TODO(...)
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-12-10下午04:06:18
 * @版本:   v1.0
 */
@Service
public class OaSecuritySearchServiceImpl implements OASecuritySearchService {


	@Resource
	private DBConnection dbConnection;

	private final Logger logger = Logger.getLogger("game");

	/*
	 * @param pid
	 * @param username
	 * @param queryParam
	 * @return
	 * @see com.iwgame.xcloud.searcher.service.OASecuritySearchService#getListAccounts(java.lang.String, java.lang.String, com.iwgame.xcloud.searcher.model.ParamBean)
	 */

	@Override
	public String getOAsecurityAccount(String pid, String username, ParamBean queryParam) {

		SearchResult result = new SearchResult();

		List<Map<String, Object>> accounts = new ArrayList<Map<String,Object>>();

		if (Xvalidator.getInstance().validate(queryParam) && !"".equals(username)) {
			try {
				// 签名验证
				int rc = SecurityUtil.securityAuthority(pid, username, queryParam.getSign(), queryParam.getTs());

				if (0 == rc) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("username", username);

					// 蜀门
					if ("P-P1".equalsIgnoreCase(pid)) {
						accounts = dbConnection.selectList("oasecurity-sm","oasecurity-search.getSafeModeRecord",paramMap);
						result.setRc(0);
						result.setResult(accounts);
						logger.info("AppName:[" + queryParam.getAppname() + "]OASecurity蜀门帐号查询成功!");
					} else if ("P-P1.5".equalsIgnoreCase(pid)) { // 醉逍遥
						accounts = dbConnection.selectList("oasecurity-zxy","oasecurity-search.getSafeModeRecord",paramMap);
						result.setRc(0);
						result.setResult(accounts);
						logger.info("AppName:[" + queryParam.getAppname() + "]OASecurity醉逍遥帐号查询成功!");
					} else {
						result.setRc(-4);
						result.setResult(accounts);
						logger.error("找不到对应的产品ID!");
					}
				} else {
					result.setRc(rc);
					result.setResult(accounts);
					logger.error("AppName:[" + queryParam.getAppname() + "]签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [username:" + username + "] [sign:" + queryParam.getSign()
							+ "] [ts:" + queryParam.getTs() + "]");
				}
			} catch (Exception e) {
				result.setRc(-4);
				result.setResult(accounts);
				logger.error("AppName:[" + queryParam.getAppname() + "]帐号查询异常信息:", e);
			}
		} else {
			result.setRc(-4);
			result.setResult(accounts);
			logger.error("AppName:[" + queryParam.getAppname() + "]帐号查询出错,必要的参数存在为空或NULL!");
		}
		return JSONObject.fromObject(result).toString();
	}
}
