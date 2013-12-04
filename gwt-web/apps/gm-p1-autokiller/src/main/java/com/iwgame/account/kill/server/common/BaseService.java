/**      
 * BaseService.java Create on 2012-6-2     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.account.kill.server.common;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;

/**
 * @ClassName: BaseService
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-2 下午04:14:39
 * @Version 1.0
 * 
 */
@SuppressWarnings("unchecked")
public abstract class BaseService {

	// 需要改动 配置数据源
	private static final String TARGET_SUBFIX = "-KILL";

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

	protected <X> List<X> selectList(final String productId, final String statement) {
		return (List<X>) getSqlSessionTemplate(productId).selectList(statement);
	}

	protected <X> List<X> selectList(final String productId, final String statement, final Object parameter) {
		return (List<X>) getSqlSessionTemplate(productId).selectList(statement, parameter);
	}

	protected <X> X selectOne(final String productId, final String statement) {
		return (X) getSqlSessionTemplate(productId).selectOne(statement);
	}

	protected <X> X selectOne(final String productId, final String statement, final Object parameter) {
		return (X) getSqlSessionTemplate(productId).selectOne(statement, parameter);
	}

	public int insert(final String productId, final String statement) {
		return getSqlSessionTemplate(productId).insert(statement);
	}

	public int insert(final String productId, final String statement, final Object parameter) {
		return getSqlSessionTemplate(productId).insert(statement, parameter);
	}

	public int update(final String productId, final String statement) {
		return getSqlSessionTemplate(productId).update(statement);
	}

	public int update(final String productId, final String statement, final Object parameter) {
		return getSqlSessionTemplate(productId).update(statement, parameter);
	}

	public int delete(final String productId, final String statement) {
		return getSqlSessionTemplate(productId).delete(statement);
	}

	public int delete(final String productId, final String statement, final Object parameter) {
		return getSqlSessionTemplate(productId).delete(statement, parameter);
	}
}
