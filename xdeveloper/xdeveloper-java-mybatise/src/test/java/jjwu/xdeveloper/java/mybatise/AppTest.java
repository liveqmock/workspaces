package jjwu.xdeveloper.java.mybatise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jjwu.xdeveloper.java.mybatise.core.dynamic.DBConnection;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "/conf/applicationContext.xml" })
public class AppTest {

	private final Logger logger = Logger.getLogger(AppTest.class);

	@Resource
	private DBConnection dbConnection;

	@Resource
	private ComboPooledDataSource dataSources;

	@Test
	public void testApp() throws SQLException {
		final Connection connection = dataSources.getConnection();
		final PreparedStatement statement = connection.prepareStatement("select * from test");
		final ResultSet datas = statement.executeQuery();
		while (datas.next()) {
			System.out.println(datas.getString("name"));
		}
	}

	@Test
	public void test() throws SQLException {
		final List<Map<String,String>> datas = dbConnection.selectList("test","test.select");
		logger.info(datas.size());
	}

}
