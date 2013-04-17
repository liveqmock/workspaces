package com.iwgame.security.indexer.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.iwgame.security.indexer.bean.Account;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbUtils {
	
	private static DataSource dataSource = getDataSource();
	private static final String JDBC_URL="jdbc:mysql://192.168.10.181/gamedb?autoReconnect=true&createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8";
	private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
	private static final String DB_USER="root";
	private static final String DB_PASSWORD="root";
	
	
	private static final String ZONE_NAME="dx1";
	/*
	 * 获取c3p0数据源
	 */
	public static DataSource getDataSource()
	{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setJdbcUrl(JDBC_URL);
			dataSource.setDriverClass(DRIVER_CLASS);
			dataSource.setUser(DB_USER);
			dataSource.setPassword(DB_PASSWORD);
			dataSource.setMaxPoolSize(200);
			dataSource.setInitialPoolSize(10);
			dataSource.setAcquireIncrement(1);
			dataSource.setPreferredTestQuery("select now()");
			return dataSource;
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Account> query(String sql ,Object...params)
	{
		QueryRunner runner = new QueryRunner(dataSource);
		BeanListHandler<Account> resultSetHandler = new BeanListHandler<Account>(Account.class);
		List<Account> accounts;
		try {
			accounts = runner.query(sql, resultSetHandler, params);
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 批量插入测试数据
	 */
	public static int insert(int batchCount)
	{
        PreparedStatement ps = null;
        ResultSet keys = null;
        Connection connection = null;
        HashSet<Long> ids = new HashSet<Long>();
		try {
    		String sql = "INSERT INTO f_account (username, user_reg_time, user_ip, user_email,user_status)VALUES(?,now(),?,?,?)";
    		connection = dataSource.getConnection();
			ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			StringBuilder ip = new StringBuilder();
			StringBuilder email = new StringBuilder();
			for (int i = 0; i < batchCount; i++) {
				ps.setString(1, RandomStringUtils.randomAlphabetic(10));
				
				ip.append(RandomUtils.nextInt(255));
				ip.append(".");
				ip.append(RandomUtils.nextInt(255));
				ip.append(".");
				ip.append(RandomUtils.nextInt(255));
				ip.append(".");
				ip.append(RandomUtils.nextInt(255));
				ps.setString(2,ip.toString());
				ip.delete(0, ip.length());
				
				
				email.append(RandomStringUtils.randomAlphabetic(10));
				email.append("@sina.com");
				ps.setString(3, email.toString());
				
				
				email.delete(0, email.length());
				ps.setInt(4, 1);
				ps.addBatch();
			}
			ps.executeBatch();
			keys = ps.getGeneratedKeys();
			int count = 0;
			while (keys.next()) {
				//System.out.println("id:"+keys.getInt(1));
				long id = keys.getInt(1);
				ids.add(id);
				count++;
			}
			//插入角色信息
			insertRole(ids);
			//插入充值信息
			insertPaid(ids);
			System.out.println("共插入:"+count+"账户条数据");
			return count;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			try {
				if(keys!=null) 
					keys.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if(ps!=null) 
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	public static void insertRole(Set<Long> ids)
	{
		PreparedStatement ps =null;
		Connection conn =null;
		try {
			String sql = "INSERT INTO s_account_role (accountid, zone, LEVEL, class, lastday)VALUES(?, ?, ?, ?, now());";
			conn = dataSource.getConnection();
			ps=conn.prepareStatement(sql);
			if (ids!=null) {
				for (Long id : ids) {
					ps.setLong(1, id);
					ps.setString(2, ZONE_NAME);
					ps.setInt(3, RandomUtils.nextInt(100));
					ps.setInt(4, 3);
					ps.addBatch();
				}
				ps.executeBatch();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void insertPaid(Set<Long> ids)
	{
		PreparedStatement ps =null;
		Connection conn = null;
		try {
			String sql = "INSERT INTO s_account_paid (accountid, zone, paid, times, last_paid,last_day,first_paid,first_day)VALUES(?, ?, ?, ?,?, now(),?,now());";
			conn = dataSource.getConnection();
			ps=conn.prepareStatement(sql);
			if (ids!=null) {
				for (Long id : ids) {
					ps.setLong(1, id);
					ps.setString(2, ZONE_NAME);
					ps.setInt(3, RandomUtils.nextInt(10000000));
					ps.setInt(4, RandomUtils.nextInt(1000));
					ps.setInt(5, RandomUtils.nextInt(300000));
					ps.setInt(6, RandomUtils.nextInt());
					
					ps.addBatch();
				}
				ps.executeBatch();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn!=null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		}
	}
}
