/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： Basicservice.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.server;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-8-22 下午05:17:53
 */
@SuppressWarnings("unchecked")
public abstract class BaseService {

	// 需要改动 配置数据源
	private static final String TARGET_SUBFIX = "-logmonitor";

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
