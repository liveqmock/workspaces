/**
 * PlayerSearchServiceImpl.java Create on 2012-3-21
 * 
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xcloud.searcher.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.xcloud.searcher.core.dynamic.DBConnection;
import com.iwgame.xcloud.searcher.model.ParamBean;
import com.iwgame.xcloud.searcher.model.SearchResult;
import com.iwgame.xcloud.searcher.service.AccountSearchService;
import com.iwgame.xcloud.searcher.util.LatinConverter;
import com.iwgame.xcloud.searcher.util.SecurityUtil;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
 * @类名: AccountSearchServiceImpl
 * @描述: 查询玩家的帐号信息实现
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-24下午01:54:16
 * @版本: 1.0
 */

@Service
@SuppressWarnings("unchecked")
public class AccountSearchServiceImpl implements AccountSearchService {

	@Resource
	private DBConnection dbConnection;

	private final Logger logger = Logger.getLogger(AccountSearchServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xcloud.searcher.service.AccountSearchService#getListAccounts
	 * (java.lang.String, java.lang.String,
	 * com.iwgame.xcloud.searcher.model.QueryParamBean)
	 */
	@Override
	public String getListAccounts(String pid, String accountid, ParamBean queryParam) {

		String rs = "";

		SearchResult result = new SearchResult();

		List<Map<String, Object>> accounts = new ArrayList<Map<String, Object>>();

		if (Xvalidator.getInstance().validate(queryParam)) {
			try {
				// 签名验证
				int rc = SecurityUtil.securityAuthority(pid, accountid, queryParam.getSign(), queryParam.getTs());

				if (0 == rc) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("accountid", Long.valueOf(accountid));

					// 蜀门
					if ("P-P1".equalsIgnoreCase(pid)) {
						accounts = dbConnection.selectList("account-sm","account-search.getAccounts", paramMap);
						result.setRc(0);
						result.setResult(accounts);
						logger.info("AppName:[" + queryParam.getAppname() + "]蜀门帐号查询成功!");
					} else if ("P-P1.5".equalsIgnoreCase(pid)) { // 醉逍遥
						accounts = dbConnection.selectList("account-zxy","account-search.getAccounts", paramMap);
						result.setRc(0);
						result.setResult(accounts);
						logger.info("AppName:[" + queryParam.getAppname() + "]醉逍遥帐号查询成功!");
					} else {
						result.setRc(-4);
						logger.error("找不到对应的产品ID!");
					}
					// 拉丁转中文
					JsonConfig config = new JsonConfig();
					config.registerJsonValueProcessor("name", new LatinConverter());
					rs = JSONObject.fromObject(result, config).toString();
				} else {
					result.setRc(rc);
					result.setResult(accounts);
					logger.error("AppName:[" + queryParam.getAppname() + "]签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [accountid:" + accountid + "] [sign:" + queryParam.getSign()
							+ "] [ts:" + queryParam.getTs() + "]");
				}
			} catch (Exception e) {
				result.setRc(-4);
				result.setResult(accounts);
				logger.error("AppName:[" + queryParam.getAppname() + "]帐号查询异常信息:", e);
				rs = JSONObject.fromObject(result).toString();
			}
		} else {
			result.setRc(-4);
			result.setResult(accounts);
			logger.error("AppName:[" + queryParam.getAppname() + "]帐号查询出错,必要的参数存在为空或NULL!");
			rs = JSONObject.fromObject(result).toString();
		}
		return rs;
	}

	/*
	 * @param pid
	 * 
	 * @param accountid
	 * 
	 * @param queryParam
	 * 
	 * @return
	 * 
	 * @see
	 * com.iwgame.xcloud.searcher.service.AccountSearchService#getAccountBydbid
	 * (java.lang.String, java.lang.String,
	 * com.iwgame.xcloud.searcher.model.ParamBean)
	 */
	@Override
	public String getAccountBydbid(String pid, String dbid, ParamBean queryParam) {
		String rs = "";

		SearchResult result = new SearchResult();

		Map<String, Object> accounts = new HashMap<String, Object>();

		if (Xvalidator.getInstance().validate(queryParam)) {
			try {
				// 签名验证
				int rc = SecurityUtil.securityAuthority(pid, dbid, queryParam.getSign(), queryParam.getTs());

				if (0 == rc) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("dbid", Long.valueOf(dbid));

					// 蜀门
					if ("P-P1".equalsIgnoreCase(pid)) {
						accounts = (Map<String, Object>) dbConnection.selectOne("account-sm","account-search.getAccountBydbid", paramMap);
						result.setRc(0);
						result.setResult(accounts);
						logger.info("AppName:[" + queryParam.getAppname() + "]蜀门帐号查询成功!");
					} else if ("P-P1.5".equalsIgnoreCase(pid)) { // 醉逍遥
						accounts = (Map<String, Object>) dbConnection.selectOne("account-zxy","account-search.getAccountBydbid", paramMap);
						result.setRc(0);
						result.setResult(accounts);
						logger.info("AppName:[" + queryParam.getAppname() + "]醉逍遥帐号查询成功!");
					} else {
						result.setRc(-4);
						result.setResult(accounts);
						logger.error("找不到对应的产品ID!");
					}
					// 拉丁转中文
					JsonConfig config = new JsonConfig();
					config.registerJsonValueProcessor("name", new LatinConverter());
					rs = JSONObject.fromObject(result, config).toString();
				} else {
					result.setRc(rc);
					result.setResult(accounts);
					rs = JSONObject.fromObject(result).toString();
					logger.error("AppName:[" + queryParam.getAppname() + "]签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [accountid:" + dbid + "] [sign:" + queryParam.getSign()
							+ "] [ts:" + queryParam.getTs() + "]");
				}
			} catch (Exception e) {
				result.setRc(-4);
				result.setResult(accounts);
				rs = JSONObject.fromObject(result).toString();
				logger.error("AppName:[" + queryParam.getAppname() + "]帐号查询异常信息:", e);
			}
		} else {
			result.setRc(-4);
			result.setResult(accounts);
			rs = JSONObject.fromObject(result).toString();
			logger.error("AppName:[" + queryParam.getAppname() + "]帐号查询出错,必要的参数存在为空或NULL!");
		}
		return rs;
	}
}
