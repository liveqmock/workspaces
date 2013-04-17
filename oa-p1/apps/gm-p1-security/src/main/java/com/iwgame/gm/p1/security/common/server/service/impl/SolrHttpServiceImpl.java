/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SolrHttpServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.security.common.server.service.SolrHttpService;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.server.util.JsonHelper;
import com.iwgame.gm.p1.security.common.shared.bean.AccountBean;
import com.iwgame.gm.p1.security.common.shared.bean.AccountParam;
import com.iwgame.gm.p1.security.common.shared.bean.AccountResponse;
import com.iwgame.gm.p1.security.common.shared.bean.RoleBean;
import com.iwgame.gm.p1.security.common.shared.bean.RoleDocs;
import com.iwgame.gm.p1.security.common.shared.bean.RoleParam;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.service.GMRestHttpServiceConnection;
import com.iwgame.xportal.common.server.SecurityUserHolder;

/**
 * 类说明
 * @简述： Sorl数据查询
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 下午03:53:22
 */
public class SolrHttpServiceImpl implements SolrHttpService{
	private Logger logger4j = Logger.getLogger(SolrHttpServiceImpl.class);
	private GMRestHttpServiceConnection restHttpServiceConnectorConnection;
	
	@Autowired
	public void setRestHttpServiceConnectorConnection(GMRestHttpServiceConnection restHttpServiceConnectorConnection) {
		this.restHttpServiceConnectorConnection = restHttpServiceConnectorConnection;
	}

	@Override
	public RoleBean getRolesByParam(RoleParam role) {
		StringBuilder q=new StringBuilder();
		if(StringUtils.isNotEmpty(role.getAccountid())){
			q.append("s_accountid:");
			q.append(role.getAccountid());
		}
		return getRoleByParam(q.toString(),role);
	}
	
	@Override
	public RoleBean getRolesByDbid(RoleParam role) {
		
		return null;
	}
	
	/**
	 * @简述: 角色模糊查询
	 * @param role
	 * @return
	 * String
	 */
	public RoleBean getRoleByParam(String q,RoleParam role){
		String responseResult ="";
		RoleBean bean = null;
		try {
			String solrServiceURL = ConstantServer.getRoleByParamSolrURL(role.getProductId());
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			param.put("pid", role.getProductId());
			param.put("q", q);
			param.put("sort", role.getSortColumn()+","+role.getSortType());
			param.put("start", role.getStart());
			param.put("row", role.getRow());
			param.put("ip", SecurityUserHolder.getCurrentUser().getUser().getLoginIp());
			responseResult = restHttpServiceConnectorConnection.get(TargetType.GAME,role.getProductId(),role.getProductId() + ConstantServer.PREFIX_SOLR,solrServiceURL,param);
			if(StringUtils.isNotEmpty(responseResult)){
				JSONObject responseObject = JSONObject.fromObject(responseResult);
				int resultMsg = Integer.parseInt(responseObject.getString("rc"));
				if (resultMsg == 0) {
					bean = JsonHelper.buildRoleBean(responseResult);
				}else {
					logger4j.info("result: " + responseResult);
				}
			}
		} catch (Exception e) {
			logger4j.info("查询结果: " + responseResult);
			logger4j.error("角色模糊查询:"+e);
			return bean;
		}finally{
			responseResult = null;
		}
		return bean;
	}
	
	/**
	 * @简述: 角色精确查询
	 * @param role
	 * @return
	 * String
	 */
	private RoleBean getRoleByDbid(RoleParam role) {
		String responseResult ="";
		RoleBean bean = null;
		try {
			String solrServiceURL = ConstantServer.getRoleBydbidSolrURL(role.getProductId(), role.getDbid());
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			param.put("pid", role.getProductId());
			param.put("dbid", role.getDbid());
			param.put("ip", SecurityUserHolder.getCurrentUser().getUser().getLoginIp());
			responseResult = restHttpServiceConnectorConnection.get(TargetType.GAME,role.getProductId(),role.getProductId() + ConstantServer.PREFIX_SOLR,solrServiceURL,param);
			if(StringUtils.isNotEmpty(responseResult)){
				JSONObject responseObject = JSONObject.fromObject(responseResult);
				int resultMsg = Integer.parseInt(responseObject.getString("rc"));
				if (resultMsg == 0) {
					bean = JsonHelper.buildRoleBean(responseResult);
				}else {
					logger4j.info("result: " + responseResult);
				}
			}
		} catch (Exception e) {
			logger4j.info("查询结果: " + responseResult);
			logger4j.error("角色精确查询:"+e);
			return bean;
		}finally{
			responseResult = null;
		}
		return bean;
	}

	@Override
	public RoleDocs getRoleName(RoleParam role) {
		RoleBean bean = getRoleByDbid(role);
		RoleDocs docs = null;
		if(bean!=null){
			if(bean.getRc()==0){
				if(bean.getResponse()!=null){
					List<RoleDocs> list = bean.getResponse().getDocsList();
					if(list.size()>0){
						docs = list.get(0);
						String svr = docs.getSvr();
						int index = svr.indexOf("game");
						if(index>0){
							docs.setGuid(svr.substring(0, index));
						}
					}
				}
			}
		}
		return docs;
	}

	@Override
	public AccountBean getAccountListByUsername(AccountParam options) {
		StringBuilder q=new StringBuilder();
		q.append("s_user_name:");
		if (StringUtils.isNotEmpty(options.getUserName())){
			if("1".equals(options.getIsfuzzy())){
				q.append(options.getUserName());
			}else{
				q.append("*");
				q.append(options.getUserName());
				q.append("*");
			}
		}else {
			q.append("*");
		}
		if (options.getRegisterTimeStart()!=null && options.getRegisterTimeEnd()!=null) {
			q.append("+AND+");
			q.append("s_register_time:[");
			q.append(options.getRegisterTimeStart());
			q.append("+TO+");
			q.append(options.getRegisterTimeEnd());
			q.append("]");
		}
		return this.getAccountByQ(q.toString(), options);
	}

	@Override
	public AccountBean getAccountListByRegisIp(AccountParam options) {
		StringBuilder q=new StringBuilder();
		if (StringUtils.isNotEmpty(options.getRegisterIp())){
			String[] ips = options.getRegisterIp().split(",");
			if(ips.length>1){
				q.append("(");
			}
			q.append("s_register_ip:");
			int i = 0;
			for(i=0;i<ips.length;i++){
				if(i>0){
					q.append("+OR+");
					q.append("s_register_ip:");
				}
				q.append(ips[i]);
				q.append("*");
			}
			if(ips.length>1){
				q.append(")");
			}
		}else {;
			q.append("s_register_ip:");
			q.append("*");
		}
		if (options.getRegisterTimeStart()!=null && options.getRegisterTimeEnd()!=null) {
				q.append("+AND+");
				q.append("s_register_time:[");
				q.append(options.getRegisterTimeStart());
				q.append("+TO+");
				q.append(options.getRegisterTimeEnd());
				q.append("]");
			}
		return this.getAccountByQ(q.toString(), options);
	}

	@Override
	public AccountBean getAccountListByRegisInfo(AccountParam options) {
		StringBuilder q=new StringBuilder();
		q.append("s_real_name:");
		if (StringUtils.isNotEmpty(options.getRealName())){
			q.append("*");
			q.append(options.getRealName());
			q.append("*");
		}else {
			q.append("*");
		}
		q.append("+AND+");
		if (StringUtils.isNotEmpty(options.getIdcard())){
			q.append("s_idcard:");
			q.append("*");
			q.append(options.getIdcard());
			q.append("*");
		}else {
			q.append("s_idcard:");
			q.append("*");
		}
		q.append("+AND+");
		if (StringUtils.isNotEmpty(options.getUserEmail())){
			q.append("s_user_email:");
			q.append("*");
			q.append(options.getUserEmail());
			q.append("*");
		}else {
			q.append("s_user_email:");
			q.append("*");
		}
		if (options.getRegisterTimeStart()!=null && options.getRegisterTimeEnd()!=null) {
			q.append("+AND+");
			q.append("s_register_time:[");
			q.append(options.getRegisterTimeStart());
			q.append("+TO+");
			q.append(options.getRegisterTimeEnd());
			q.append("]");
		}
		return this.getAccountByQ(q.toString(), options);
	}
	
	private AccountBean getAccountByQ(String q,AccountParam options){
		String responseResult ="";
		AccountBean bean = new AccountBean();
		try {
			String productId = options.getProductId();
			String solrServiceURL = ConstantServer.getAccountByParamSolrURL(productId);
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			param.put("pid", productId);
			param.put("q", q);
			param.put("sort", options.getSortColumn()+","+options.getSortType());
			param.put("start", options.getStart());
			param.put("row", options.getRow());
			param.put("ip", SecurityUserHolder.getCurrentUser().getUser().getLoginIp());
			responseResult = restHttpServiceConnectorConnection.get(TargetType.GAME,productId,productId + ConstantServer.PREFIX_SOLR,solrServiceURL,param);
			if(StringUtils.isNotEmpty(responseResult)){
				JSONObject responseObject = JSONObject.fromObject(responseResult);
				int resultMsg = Integer.parseInt(responseObject.getString("rc"));
				if (resultMsg == 0) {
					bean = JsonHelper.buildAccountBean(responseObject);
				}else{
					logger4j.info("result: " + responseResult);
				}
			}
		} catch (Exception e) {
			logger4j.info("查询结果: " + responseResult);
			logger4j.error("账号模糊查询:"+e);
			return bean;
		}finally{
			responseResult = null;
		}
		return bean;
	}

	@Override
	public String getAccountNameByAccountId(String productId,String accountId) {
		AccountBean bean = getAccountByAccountId(productId,accountId);
		AccountResponse ar = bean.getResponse();
		if(ar.getNumFound()>0){
			return ar.getDocsList().get(0).getUserName();
		}
		return "";
	}
	
	private AccountBean getAccountByAccountId(String productId,String accountId){
		String responseResult ="";
		AccountBean bean = new AccountBean();
		try {
			String solrServiceURL = ConstantServer.getAccountByAccountIdSolrURL(productId,accountId);
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			param.put("pid", productId);
			param.put("accountId", accountId);
			param.put("ip", SecurityUserHolder.getCurrentUser().getUser().getLoginIp());
			responseResult = restHttpServiceConnectorConnection.get(TargetType.GAME,productId,productId + ConstantServer.PREFIX_SOLR,solrServiceURL,param);
			if(StringUtils.isNotEmpty(responseResult)){
				JSONObject responseObject = JSONObject.fromObject(responseResult);
				int resultMsg = Integer.parseInt(responseObject.getString("rc"));
				if (resultMsg == 0) {
					bean = JsonHelper.buildAccountBean(responseObject);
				}else{
					logger4j.info("result: " + responseResult);
				}
			}
		} catch (Exception e) {
			logger4j.info("查询结果: " + responseResult);
			logger4j.error("账号精确查询:"+e);
			return bean;
		}finally{
			responseResult = null;
		}
		return bean;
	}
}
