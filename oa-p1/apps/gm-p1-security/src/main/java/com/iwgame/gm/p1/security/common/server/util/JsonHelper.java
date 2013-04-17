/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： JsonHelper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.iwgame.gm.p1.security.common.shared.bean.AccountBean;
import com.iwgame.gm.p1.security.common.shared.bean.AccountDocs;
import com.iwgame.gm.p1.security.common.shared.bean.AccountResponse;
import com.iwgame.gm.p1.security.common.shared.bean.RoleBean;
import com.iwgame.gm.p1.security.common.shared.bean.RoleDocs;
import com.iwgame.gm.p1.security.common.shared.bean.RoleResponse;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-22 上午11:11:02
 */
public class JsonHelper {

	public static RoleBean buildRoleBean(String json){
		JSONObject roleObj = JSONObject.fromObject(json);
		JSONObject responseObj = roleObj.getJSONObject("response");
		JSONArray docsArray = responseObj.getJSONArray("docs");
		RoleBean role = new RoleBean();
		role.setRc(Integer.parseInt(roleObj.getString("rc")));
		List<RoleDocs> list = new ArrayList<RoleDocs>();
		int i = 0;
		for(i=0;i<docsArray.size();i++){
			RoleDocs docs = new RoleDocs();
			JSONObject docsObj = docsArray.getJSONObject(i);
			docs.setAccountid(docsObj.getString("s_accountid"));
			docs.setBankmoney(docsObj.getString("s_bankmoney"));
			docs.setCreatetime(docsObj.getString("s_createtime"));
			docs.setDbid(docsObj.getString("s_dbid"));
			docs.setDeleted(docsObj.getString("s_deleted"));
			docs.setDeletetime(docsObj.getString("s_deletetime"));
			docs.setIp(docsObj.getString("s_ip"));
			docs.setIpptime(docsObj.getString("s_ipptime"));
			docs.setIpsafe(docsObj.getString("s_ipsafe"));
			docs.setLastlevuptime(docsObj.getString("s_lastlevuptime"));
			docs.setLastupdatetime(docsObj.getString("s_lastupdatetime"));
			docs.setLevel(docsObj.getString("s_level"));
			docs.setLocked(docsObj.getString("s_locked"));
			docs.setLockedtime(docsObj.getString("s_lockedtime"));
			docs.setMoney(docsObj.getString("s_money"));
			docs.setName(docsObj.getString("s_name"));
			docs.setOnlinetime(docsObj.getString("s_onlinetime"));
			docs.setParty(docsObj.getString("s_party"));
			docs.setSvr(docsObj.getString("s_svr"));
			docs.setTotalexp(docsObj.getString("s_totalexp"));
			docs.setTotalgodexp(docsObj.getString("s_totalgodexp"));
			list.add(docs);
		}
		
		RoleResponse response = new RoleResponse();
		response.setDocsList(list);
		response.setNumFound(Integer.parseInt(responseObj.getString("numFound")));
		response.setStart(Integer.parseInt(responseObj.getString("start")));
		
		role.setResponse(response);
		
		return role;
	}
	
	public static AccountBean buildAccountBean(JSONObject accountObj){
		JSONObject responseObj = accountObj.getJSONObject("response");
		JSONArray docsArray = responseObj.getJSONArray("docs");
		AccountBean account = new AccountBean();
		account.setRc(Integer.parseInt(accountObj.getString("rc")));
		JSONObject docsObj = null;
		JSONObject registerTimeObj = null;
		List<AccountDocs> list = new ArrayList<AccountDocs>();
		int i = 0;
		for(i=0;i<docsArray.size();i++){
			AccountDocs docs = new AccountDocs();
			docsObj = docsArray.getJSONObject(i);
			docs.setAccountId(docsObj.getString("s_account_id"));
			docs.setAccountStatus(docsObj.getString("s_account_status"));
			docs.setIdcard(docsObj.getString("s_idcard"));
			docs.setIdcardStatus(docsObj.getString("s_idcard_status"));
			if(!docsObj.getString("s_max_level").isEmpty()){
				docs.setMaxLevel(Integer.parseInt(docsObj.getString("s_max_level")));
			}else{
				docs.setMaxLevel(0);
			}
			docs.setPassword4(docsObj.getString("s_password4"));
			docs.setRealName(docsObj.getString("s_real_name"));
			docs.setRegisterIp(docsObj.getString("s_register_ip"));
			docs.setRegisterSourcetype(docsObj.getString("s_register_sourcetype"));
			docs.setRegisterSourceid(docsObj.getString("s_register_sourceid"));
			docs.setRegisterSourceurl(docsObj.getString("s_register_sourceurl"));
			registerTimeObj = docsObj.getJSONObject("s_register_time");
			if(!registerTimeObj.isNullObject()){
				docs.setRegisterTime(DateHelper.format(Long.parseLong(registerTimeObj.getString("time")),"yyyy/MM/dd HH:mm:ss"));
			}else{
				docs.setRegisterTime("");
			}
			if(!docsObj.getString("s_total_paid").isEmpty()){
				docs.setTotalPaid(Integer.parseInt(docsObj.getString("s_total_paid")));
			}else {
				docs.setTotalPaid(0);
			}
			docs.setUserEmail(docsObj.getString("s_user_email"));
			docs.setUserEmailNew(docsObj.getString("s_user_email_new"));
			docs.setUserName(docsObj.getString("s_user_name"));
			list.add(docs);
		}
		
		AccountResponse response = new AccountResponse();
		response.setDocsList(list);
		response.setNumFound(Integer.parseInt(responseObj.getString("numFound")));
		response.setStart(Integer.parseInt(responseObj.getString("start")));
		
		account.setResponse(response);
		
		return account;
	}
}
