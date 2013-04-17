/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityRegisInfoQueryServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.query.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.gm.p1.security.common.server.service.SolrHttpService;
import com.iwgame.gm.p1.security.common.server.util.DateHelper;
import com.iwgame.gm.p1.security.common.server.util.LoadResultHelper;
import com.iwgame.gm.p1.security.common.shared.bean.AccountBean;
import com.iwgame.gm.p1.security.common.shared.bean.AccountDocs;
import com.iwgame.gm.p1.security.common.shared.bean.AccountParam;
import com.iwgame.gm.p1.security.common.shared.bean.GlobalResource;
import com.iwgame.gm.p1.security.common.shared.bean.RoleBean;
import com.iwgame.gm.p1.security.common.shared.bean.RoleDocs;
import com.iwgame.gm.p1.security.common.shared.bean.RoleParam;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityRegisInfoQueryService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-27 上午10:41:37
 */
@Service
@NeedAuthorization
public class SecurityRegisInfoQueryServiceImpl implements SecurityRegisInfoQueryService {
	
	@Resource(name = "globalResource")
	private GlobalResource globalResource;
	
	@Resource(name="solrHttpServiceImpl")
	private SolrHttpService solrHttpService;
	
	private final Logger log4j = Logger.getLogger(SecurityRegisInfoQueryServiceImpl.class);
	@Override
	@AccessResource(name="security-query-regisInfoQuery-select")
	public String loadAccounts(BaseFilterPagingLoadConfig loadConfig)
			throws Exception {
		String resultJson = StringUtils.EMPTY;
		AccountBean accountBean = null;
		try {
			AccountParam param = new AccountParam();
			if (loadConfig != null) {
				Date startDate = loadConfig.<Date>get("startDate",null);
				Date endDate = loadConfig.<Date>get("endDate",null);
				if (startDate!=null && endDate!=null) {
					param.setRegisterTimeStart(DateHelper.getDateString(startDate, "yyyy-MM-dd")+" 00:00:00".replace(" ", "T")+"Z");
					param.setRegisterTimeEnd(DateHelper.getDateString(endDate, "yyyy-MM-dd")+" 23:59:59".replace(" ", "T")+"Z");
				}
				param.setProductId(loadConfig.<String>get("pid"));
				String options = loadConfig.<String>get("options");
				if("username".equals(options)){
					param.setUserName(loadConfig.<String>get("username"));
					param.setIsfuzzy(loadConfig.<String>get("isfuzzy"));
					putParams(loadConfig, param);
					accountBean  = solrHttpService.getAccountListByUsername(param);
				}else if("registerip".equals(options)){
					param.setRegisterIp(loadConfig.<String>get("registerIp"));
					putParams(loadConfig, param);
					accountBean  = solrHttpService.getAccountListByRegisIp(param);
				}else if("registerinfo".equals(options)){
					param.setRealName(loadConfig.<String>get("realName"));
					param.setIdcard(loadConfig.<String>get("idcard"));
					param.setUserEmail(loadConfig.<String>get("userEmail"));
					putParams(loadConfig, param);
					accountBean  = solrHttpService.getAccountListByRegisInfo(param);
				}
				
				List<AccountDocs> list = new ArrayList<AccountDocs>();
				int total = 0;
				if (accountBean!=null) {
					if(accountBean.getResponse()!=null){
						list = accountBean.getResponse().getDocsList();
						total = accountBean.getResponse().getNumFound();
					}
				}
				resultJson = LoadResultHelper.buildResult(list,param.getRow(), param.getStart(), total);
				}
		} catch (Exception e) {
			log4j.error("查询账户信息失败:", e);
			throw e;
		}finally{
			accountBean = null;
		}
		return resultJson;
	}
	
	@Override
	@AccessResource(name="security-query-regisInfoQuery-export")
	public List<AccountDocs> loadAccounts4Export(Map<String, String> paramater)
			throws Exception {
		AccountParam param = new AccountParam();
		if (paramater != null) {
				AccountBean accountBean = null;
				param.setProductId(paramater.get("pid"));
				if(paramater.containsKey("username")){
					param.setUserName(paramater.get("username"));
				}
				if(paramater.containsKey("isfuzzy")){
					param.setIsfuzzy(paramater.get("isfuzzy"));
				}
				if(paramater.containsKey("registerIp")){
					param.setRegisterIp(paramater.get("registerIp"));
				}
				if(paramater.containsKey("realName")){
					param.setRealName(paramater.get("realName"));
				}
				if(paramater.containsKey("idcard")){
					param.setIdcard(paramater.get("idcard"));
				}
				if(paramater.containsKey("userEmail")){
					param.setUserEmail(paramater.get("userEmail"));
				}
				Date startDate =null;
				Date endDate = null;
				if (paramater.containsKey("startDate")) {
					String startDateStr = paramater.get("startDate");
					startDate = new Date(Long.parseLong(startDateStr));
					param.setRegisterTimeStart(DateHelper.getDateString(startDate, "yyyy-MM-dd")+" 00:00:00".replace(" ", "T")+"Z");
				}
				if (paramater.containsKey("endDate")) {
					String endDateStr = paramater.get("endDate");
					endDate = new Date(Long.parseLong(endDateStr));
					param.setRegisterTimeEnd(DateHelper.getDateString(endDate, "yyyy-MM-dd")+" 23:59:59".replace(" ", "T")+"Z");
				}
				putSortParam(paramater.get("sortColumn"), paramater.get("sortType"), param);
				param.setStart(0);
				param.setRow(globalResource.getExportMaxNum());
				String options = paramater.get("options");
				if("username".equals(options)){
					accountBean  = solrHttpService.getAccountListByUsername(param);
				}else if("registerip".equals(options)){
					accountBean  = solrHttpService.getAccountListByRegisIp(param);
				}else if("registerinfo".equals(options)){
					accountBean  = solrHttpService.getAccountListByRegisInfo(param);
				}
				try {
					if (accountBean!=null) {
						if (accountBean.getResponse()!=null) {
							List<AccountDocs> accountDocs = accountBean.getResponse().getDocsList();
							return accountDocs;
						}
					}
				} catch (Exception e) {
					log4j.error("查询账户信息失败:", e);
					throw e;
				}
			}
		return null;
	}
	
	 /*
	  * 追加通用参数
	  */
	private void putParams(BaseFilterPagingLoadConfig loadConfig,
			AccountParam param) {
		param.setRow(loadConfig.<Integer> get("limit"));
		param.setStart(loadConfig.<Integer> get("offset"));
		String sortColumn = loadConfig.getSortField();
		String sortType = loadConfig.getSortDir().name();
		putSortParam(sortColumn,sortType, param);
	}
	/**
	 * @param loadConfig
	 * @param param
	 */
	private void putSortParam(String sortColumn,String sortType,AccountParam param) {
		if (sortColumn==null) {
			sortColumn = "s_register_time";
		}else if ("registerTime".equalsIgnoreCase(sortColumn)) {
			sortColumn = "s_register_time";
		}else if ("totalPaid".equalsIgnoreCase(sortColumn)) {
			sortColumn = "s_total_paid";
		}else if ("registerIp".equalsIgnoreCase(sortColumn)) {
			sortColumn = "s_register_ip";
		}else if ("maxLevel".equalsIgnoreCase(sortColumn)){
			sortColumn = "s_max_level";
		}
		
		if (sortType.equals("NONE")) {
			sortType="desc";
		}
		param.setSortColumn(sortColumn);
		param.setSortType(sortType);
	}

	@Override
	public String loadRoles(BaseFilterPagingLoadConfig loadConfig)
			throws Exception {
		RoleParam param = new RoleParam();
		param.setAccountid(loadConfig.<String>get("accountId"));
		param.setProductId(loadConfig.<String>get("pid"));
		param.setSortColumn("s_level");
		param.setSortType("desc");
		param.setStart(0);
		param.setRow(100);
		RoleBean roleBean = solrHttpService.getRolesByParam(param);
		List<RoleDocs> list = roleBean.getResponse().getDocsList();
		int total = 0;
		if(roleBean.getResponse()!=null){
			list = roleBean.getResponse().getDocsList();
			total = roleBean.getResponse().getNumFound();
		}
		String resultJson = LoadResultHelper.buildResult(list,param.getRow(), param.getStart(), total);
		
		return resultJson;
	}
	
	@Override
	public AccountDocs getAccountDocsByUserName(String userName,String productId)
			throws Exception {
		if (StringUtils.isNotEmpty(productId)) {
			if (StringUtils.isNotEmpty(userName)) {
				AccountBean accountBean = null;
				AccountDocs accountDocs = null;
				AccountParam param = new AccountParam();
				param.setUserName(userName);
				//精确查询账号
				param.setIsfuzzy("1");
				param.setProductId(productId);
				//putSortParam(null,"NONE",param);
				param.setSortColumn("s_register_time");
				param.setSortType("desc");
				param.setStart(0);
				param.setRow(10);
				try {
					accountBean  = solrHttpService.getAccountListByUsername(param);
					if (accountBean!=null) {
						if(accountBean.getResponse()!=null){
							List<AccountDocs> list = accountBean.getResponse().getDocsList();
							if (list!=null&&list.size()>0) {
								accountDocs = list.get(0);
								return accountDocs;
							}
						}
					}
				} catch (Exception e) {
					log4j.error("[getAccountDocsByUserName]-", e);
					e.printStackTrace();
				}
			}
		}else {
			log4j.error("[getAccountDocsByUserName]-产品ID为空");
		}
		return null;
	}
}
