/**      
 * AccountServiceImpl.java Create on 2012-7-13     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.service.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xengine.xtask.killer.service.AccountService;

/**
 * @ClassName: AccountServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-13 上午11:53:41
 * @Version 1.0
 * 
 */
public class AccountServiceImpl implements AccountService {

	private String productId;

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	// 需要改动 配置数据源
	private static final String TARGET_SUBFIX = "-ACCOUNT";

	private DBConnection dbConnectorConnection;

	@Autowired
	public void setDbConnection(final DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	protected SqlSessionTemplate getSqlSessionTemplate(final String productId) {
		String targetId = productId + TARGET_SUBFIX;
		SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId, targetId,
				null);
		return sqlSessionTemplate;
	}

	@Override
	public Integer getTotalPaid(final String accountname) {
		return (Integer) getSqlSessionTemplate(productId).selectOne("killer.xtask.account.queryTotalPaid", accountname);
	}

	@Override
	public Integer getMaxLevel(final String accountname) {
		return (Integer) getSqlSessionTemplate(productId).selectOne("killer.xtask.account.queryMaxLevel", accountname);
	}

}
