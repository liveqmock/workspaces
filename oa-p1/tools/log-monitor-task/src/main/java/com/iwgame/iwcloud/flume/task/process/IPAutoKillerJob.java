/****************************************************************
 *  系统名称  ： '业务日志监控-预警任务系统'
 *  文件名    ： BizLogMonitorTrackJob.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.task.process;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 类说明
 * 
 * @简述： 帐号自动封杀
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-8-30 下午4:02:33
 */
public class IPAutoKillerJob {
	private static Logger logger = Logger.getLogger(IPAutoKillerJob.class);

	private DataSource pisMonitorDBDataSource;
	private DataSource autoKillerDBDataSource;
	private String rule1_ip_axcount;
	private String rule2_ip_axcount;
	private String rule3_ip_axcount;
	private String rule4_ip_b_axcount;
	private String rule5_ip_c_axcount;

	public void setAutoKillerDBDataSource(DataSource autoKillerDBDataSource) {
		this.autoKillerDBDataSource = autoKillerDBDataSource;
	}

	public void setPisMonitorDBDataSource(DataSource pisMonitorDBDataSource) {
		this.pisMonitorDBDataSource = pisMonitorDBDataSource;
	}

	public void setRule1_ip_axcount(String rule1_ip_axcount) {
		this.rule1_ip_axcount = rule1_ip_axcount;
	}

	public void setRule2_ip_axcount(String rule2_ip_axcount) {
		this.rule2_ip_axcount = rule2_ip_axcount;
	}

	public void setRule3_ip_axcount(String rule3_ip_axcount) {
		this.rule3_ip_axcount = rule3_ip_axcount;
	}
	
	public void setRule4_ip_b_axcount(String rule4_ip_b_axcount) {
		this.rule4_ip_b_axcount = rule4_ip_b_axcount;
	}

	public void setRule5_ip_c_axcount(String rule5_ip_c_axcount) {
		this.rule5_ip_c_axcount = rule5_ip_c_axcount;
	}

	public void work() {
		logger.info("自动封杀检查开始...");
		try {
			int count = 0;
			//仅针对登录的数据
			String sql = "";
			//规则1：单个IP，1分钟内10个帐号
			sql = "select date_format(request_time,'%Y-%m-%d %H:%i:00') as dates,request_ip,count(distinct request_user_name) as counts ";
			sql += " from monitor_server_log";
			sql += " where request_type='登录'";
			sql += " and request_time >= date_sub(now(),interval 5 minute)";
			sql += " group by dates,request_ip having counts >= " + rule1_ip_axcount;
			count = queryIpList(sql);
			logger.info("单个IP，1分钟内"+rule1_ip_axcount+"个帐号, 检测完成。。共有：" + count + "个");
			logger.info("=====================================================\n\n");
			//规则2：单个IP，1小时内50个帐号
			sql = "";
			sql = "select date_format(request_time,'%Y-%m-%d %H:00:00') as dates,request_ip,count(distinct request_user_name) as counts ";
			sql += " from monitor_server_log";
			sql += " where request_type='登录'";
			sql += " and request_time >= date_sub(now(),interval 1 hour)";
			sql += " group by dates,request_ip having counts >= " + rule2_ip_axcount;
			count = queryIpList(sql);
			logger.info("单个IP，1小时内"+rule2_ip_axcount+"个帐号, 检测完成。。共有：" + count + "个");
			logger.info("=====================================================\n\n");
			//规则3:当天当同IP下登入失败次数大于等于100
			sql = "";
			sql = "select date(request_time) as dates,request_ip,count(*) as counts ";
			sql += " from monitor_server_log";
			sql += " where request_type='登录'";
			sql += " and request_status = 1";
			sql += " and request_time >= curdate()";
			sql += " group by dates,request_ip having counts >= " + rule3_ip_axcount;
			count = queryIpList(sql);
			logger.info("当天当同IP下登入失败次数大于等于"+rule3_ip_axcount+", 检测完成。。共有：" + count + "个");
			logger.info("=====================================================\n\n");
			
			
			//规则4：B段封杀规则: >=50个登录失败的帐号/小时
			count = queryIpList("show_rpt_ip_b",Integer.valueOf(rule4_ip_b_axcount));
			logger.info("B段封杀规则: >="+rule4_ip_b_axcount+"个登录失败的帐号/小时, 检测完成。。共有：" + count + "个");
			logger.info("=====================================================\n\n");
			
			//规则5：C段封杀规则: >=50个登录失败的帐号/小时
			count = queryIpList("show_rpt_ip_c",Integer.valueOf(rule5_ip_c_axcount));
			logger.info("C段封杀规则: >="+rule5_ip_c_axcount+"个登录失败的帐号/小时, 检测完成。。共有：" + count + "个");
			logger.info("=====================================================\n\n");
			
		} catch (Exception e) {
			logger.error("[IPAutoKillerJob]" + e);
		}
		logger.info("自动封杀检查结束...\n");
	}

	/**
	 * 查询IP列表
	 * @param sql
	 * @return
	 */
	private int queryIpList(String sql) {
		int count = 0;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String ip = null;
		try {
			// 封装数据库连接异常
			dbConnection = pisMonitorDBDataSource.getConnection();

			if (dbConnection == null || dbConnection.isClosed()) {
				throw new SQLException("连接已经关闭！");
			}
			// 执行
			statement = dbConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				ip = resultSet.getString("request_ip");
				if (ip != null && ip.length() > 0 && !ip.equalsIgnoreCase("127.0.0.1")) {
					addIp2MonitorTable(ip);
					count++;
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				statement = null;
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
				}
			}
		}
		return count;
	}
	
	/**
	 * 查询IP列表(存储过程)
	 * @param procName
	 * @param maxCount
	 * @return
	 */
	private int queryIpList(String procName,int maxCount) {
		int count = 0;
		Connection dbConnection = null;
		CallableStatement cstmt = null;
		ResultSet resultSet = null;
		String ip = null;
		try {
			// 封装数据库连接异常
			dbConnection = pisMonitorDBDataSource.getConnection();

			if (dbConnection == null || dbConnection.isClosed()) {
				throw new SQLException("连接已经关闭！");
			}
			// 执行
			String sql = "{call "+procName+"(?)}";
			cstmt = dbConnection.prepareCall(sql);
			//设置入参和出参
			cstmt.setInt(1, maxCount);
			
			resultSet = cstmt.executeQuery();
			while (resultSet.next()) {
				ip = resultSet.getString("request_ip");
				if (ip != null && ip.length() > 0 && !ip.equalsIgnoreCase("127.0.0.1")) {
					addIp2MonitorTable(ip);
					count++;
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				cstmt = null;
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
				}
			}
		}
		return count;
	}
	
	/**
	 * 添加可疑IP到数据库
	 * 
	 * @param ip
	 */
	private void addIp2MonitorTable(String ip) {
		Connection dbConnection = null;
		Statement statement = null;
		try {
			// 封装数据库连接异常
			dbConnection = autoKillerDBDataSource.getConnection();

			if (dbConnection == null || dbConnection.isClosed()) {
				throw new SQLException("连接已经关闭！");
			}
			// 执行
			String sql = "insert t_ipkill(ip,ctime) values ('" + ip
					+ "',now())";
			statement = dbConnection.createStatement();
			statement.executeUpdate(sql);
			logger.info("添加可疑IP： " + ip + ", 到封杀列表成功!");
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				statement = null;
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
				}
			}
		}

	}
}
