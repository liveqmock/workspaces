package jjwu.xdeveloper.java.mybatise;

import java.sql.SQLException;

import javax.annotation.Resource;

import jjwu.xdeveloper.java.mybatise.core.dynamic.DBConnection;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "/conf/applicationContext.xml" })
public class AppTest {
	
	private final Logger logger = Logger.getLogger(AppTest.class);
	
	@Resource
	private DBConnection dbConnection;

	@Test
	public void testApp() throws SQLException {
//		logger.info("=>"+dbConnection.getCilent("datasource").getConnection().getAutoCommit());
//		logger.info(dbConnection.getCilent("datasource").selectOne("select count(*) from test"));
	}
}
