/**      
 * AccountServiceImpl.java Create on 2012-10-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.adcollect.modules.baidu.account.shared.model.BaiduAccount;
import com.iwgame.gm.p1.adcollect.modules.baidu.account.shared.rpc.BaiduAccountService;
import com.iwgame.security.key.MasterKeyUtil;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @ClassName: AccountServiceImpl
 * @Description: 百度帐号密码修改服务
 * @author 吴江晖
 * @date 2012-10-11 下午03:27:17
 * @Version 1.0
 * 
 */
@NeedAuthorization
public class BaiduAccountServiceImpl implements BaiduAccountService {

	private DBConnection dbConnectorConnection;

	@Autowired
	public void setDbConnection(final DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	// 此处固定为P-P1-BAIDU
	protected SqlSessionTemplate getSqlSessionTemplate() {
		final SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection
				.getClient(TargetType.GAME, "P-P1", "P-P1-BAIDU", null);
		return sqlSessionTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.baidu.account.shared.rpc.AccountService
	 * #getAccounts(com.iwgame.ui.core.client.data.PagingLoadConfig)
	 */
	@Override
	public String getAccounts(final String productId) {
		@SuppressWarnings("unchecked")
		final List<BaiduAccount> accounts = (List<BaiduAccount>) getSqlSessionTemplate()
				.selectList("baidu.account.getAccounts", productId);
		final PagingLoadResult<BaiduAccount> result = new PagingLoadResult<BaiduAccount>();
		result.setTotal(accounts.size());
		result.setRows(accounts);
		return GridHelper.buildResults(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.baidu.account.shared.rpc.AccountService
	 * #modifyPassword(java.lang.String, java.lang.Integer, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@AccessResource(name = "ad-baidu-account-modifypwd")
	public String modifyPassword(final Integer id, final String username,
			final String token, final String inputPassword,
			final String oldPassword, final String newPassword,
			final String modifier) {
		if (MasterKeyUtil.decKey(oldPassword).equals(inputPassword)) {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("id", id);
			if (StringUtils.isBlank(newPassword)) {
				parameter.put("password", null);
			} else {
				parameter.put("password", MasterKeyUtil.encKey(newPassword));
			}
			parameter.put("modifier", modifier);
			parameter.put("username", username);
			parameter.put("token", token);
			getSqlSessionTemplate().update("baidu.account.modifyPassword",
					parameter);
			return null;
		} else {
			return "您输入的原始密码不正确！";
		}

	}

}
