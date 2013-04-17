/****************************************************************
 *  文件名     ： XConnectorTest.java
 *  日期         :  2012-11-14
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xconnector;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-14下午01:55:18
 * @版本: v1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/config/applicationContext.xml")
public class XConnectorTest {

	@Resource
	private DBConnection dbConnectorConnection;

	@Test
	public void test() throws SQLException {
		int i = 0;
		for (int j = 1; j <= 6; j++) {
			try {
				SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, "P-P1", "P-P1-dx" + j, null);
				ProxoolDataSource dataSource = (ProxoolDataSource) sqlSessionTemplate.getConfiguration().getEnvironment().getDataSource();
				System.out.println(dataSource.getDriverUrl());
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("type", 3);
				params.put("name", "wujunjie");
				params.put("str1", "NO1");
				params.put("str2", "PWD");
				params.put("i1", 0);
				params.put("i2", 1);
				params.put("i3", 0);
				int reslut = sqlSessionTemplate.insert("game-sqlmap.itemcardActivate", params);
				System.out.println(++i + ":  " + reslut);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	

}


class RunTest implements Runnable{

	/*
	  * 
	  * @see java.lang.Runnable#run()
	  */
	@Override
	public void run() {
		
		
		
	}
	
}
