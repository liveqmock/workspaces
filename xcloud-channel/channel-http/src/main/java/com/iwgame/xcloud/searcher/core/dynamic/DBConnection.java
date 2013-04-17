/****************************************************************
 *  文件名     ： DBConnection.java
 *  日期         :  2012-10-31
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.core.dynamic;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.iwgame.xcloud.searcher.core.dynamic.datasource.DynamicDataSourceHandler;
import com.iwgame.xcloud.searcher.core.factory.ConnectorSqlSessionFactory;

/** 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-12-11下午03:18:20 
 * @版本:   v1.0 
 */
@Component
public class DBConnection {
	
	@Resource
	private ConnectorSqlSessionFactory connectorSqlSessionFactory;
	
	public SqlSessionTemplate getCilent(String bdName){
		
		//得到sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = connectorSqlSessionFactory.getSqlSessionFactory(bdName);
		
		//更换连接池
		DynamicDataSourceHandler.settingDataSource(bdName);
		
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
		return template;
	}

}
