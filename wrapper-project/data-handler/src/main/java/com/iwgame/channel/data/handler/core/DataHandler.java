/****************************************************************
 * + * 文件名 ： DataHandler.java
 * 日期 : 2013-1-17
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.channel.data.handler.core;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.channel.data.handler.model.DataModel;
import com.iwgame.channel.data.handler.tools.ConfigModel;
import com.iwgame.xvalidators.Xvalidator;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @类名: DataHandler
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮件: wujunjie@iwgame.com
 * @日期: 2013-1-17下午5:00:30
 * @版本: 1.0
 */
@Service
public class DataHandler {

	private final Logger logger = Logger.getLogger(DataHandler.class);

	@Resource
	private ComboPooledDataSource dataSource3307;

	@Resource
	private ComboPooledDataSource dataSource3306;

	@Resource
	private ConfigModel configModel;

	public void handlerData() {
		List<DataModel> datas = getRoleNameByList(getDataModelsBy3307());
		if (datas != null && datas.size() > 0) {
			update3307Data(datas);
		} else {
			logger.info("停止更新...");
		}
	}

	private List<DataModel> getDataModelsBy3307() {
		Connection connection = null;
		PreparedStatement statement = null;
		List<DataModel> results = new ArrayList<DataModel>();
		try {
			connection = dataSource3307.getConnection();
			statement = connection.prepareStatement("select dbid,lastupdatetime,svr from t_game_role where lastupdatetime > ? order by lastupdatetime asc limit ?");
			statement.setInt(1, readLastUpdateTime());
			statement.setInt(2, configModel.getLimit());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				DataModel result = new DataModel();
				result.setDbid(resultSet.getLong("dbid"));
				result.setServ(handlerTableName(resultSet.getString("svr")));
				result.setLastupdatetime(resultSet.getInt("lastupdatetime"));
				results.add(result);
			}
			logger.info("查询3307角色数据库,成功取出数据" + results.size() + "条!");
		} catch (Exception e) {
			logger.error("查询3307数据库出错,异常信息:", e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
				statement = null;
			}
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
				connection = null;
			}
		}
		return results;
	}

	public List<DataModel> getRoleNameByList(List<DataModel> params) {
		List<DataModel> results = new ArrayList<DataModel>();
		for (DataModel dataModel : params) {
			results.add(getRoleName(dataModel));
		}
		logger.info("3006取出gamedb数据成功,转换中文替换角色名称!");
		return results;
	}

	public DataModel getRoleName(DataModel dataModel) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource3306.getConnection();
			statement = connection.prepareStatement("select name from t_game_role where dbid = ? limit 1");
			statement.setLong(1, dataModel.getDbid());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				dataModel.setName(new String(resultSet.getBytes("name"), "gbk"));
			}
		} catch (Exception e) {
			logger.error("查询角色名字出错,异常信息:", e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
				statement = null;
			}
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
				connection = null;
			}
		}
		return dataModel;
	}

	/**
	 * 更新3307表
	 * 
	 * @param params
	 * @return
	 */
	private int update3307Data(List<DataModel> params) {
		int resutl = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource3307.getConnection();
			for (DataModel dataModel : params) {
				if (Xvalidator.getInstance().validate(dataModel)) {
					try {
						StringBuilder sql = new StringBuilder("update ");
						sql.append(dataModel.getServ());
						sql.append(" set name = ");
						sql.append("'" + dataModel.getName() + "' ");
						sql.append("where dbid=");
						sql.append(dataModel.getDbid());
						statement = connection.prepareStatement(sql.toString());
						resutl += statement.executeUpdate();
					} catch (Exception e) {
						logger.error("更新出错,表和字段是否设置为utf8?或者表未找到,异常信息:" + e.getMessage());
					}
				} else {
					logger.error("必要参数为空,此条数据未更新! 请求:" + dataModel.toString());
				}
			}
		} catch (Exception e) {
			logger.error("更新3307数据库出错,异常信息:", e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
				statement = null;
			}
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
				connection = null;
			}
		}

		DataModel dataModel = params.get(params.size() - 1);
		// 存入最后更新时间
		saveLastUpdateTime(dataModel);
		logger.info("更新3307数成功记录:" + resutl);
		return resutl;
	}

	/**
	 * 处理表的名字
	 * 
	 * @param srv
	 * @return
	 */
	private String handlerTableName(String srv) {
		if (srv.indexOf("game") > 0) {
			StringBuilder tableName = new StringBuilder();
			String guid = srv.substring(0, srv.indexOf("game"));
			String group = srv.substring(srv.indexOf("game"));
			tableName.append(guid);
			tableName.append("_");
			tableName.append(group);
			tableName.append("_");
			tableName.append("t");
			tableName.append("_");
			tableName.append("user");
			return tableName.toString();
		} else {
			return null;
		}
	}

	/**
	 * 保存最后读取的时间
	 * 
	 * @param dataModel
	 */
	private synchronized void saveLastUpdateTime(DataModel dataModel) {
		try {
			File file = new File(configModel.getPath());
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(configModel.getPath() + "lastupdatetime");
			file.createNewFile();
			FileUtils.writeStringToFile(file, "" + dataModel.getLastupdatetime());
		} catch (Exception e) {
			logger.error("创建读取记录文件失败! 异常信息:", e);
		}
	}

	/**
	 * 读取上次记录的最后时间
	 * 
	 * @return
	 */
	private synchronized int readLastUpdateTime() {
		try {
			File file = new File(configModel.getPath() + "lastupdatetime");
			if (file.exists()) {
				List<String> lines = FileUtils.readLines(file);
				if (lines != null && lines.size() > 0) {
					return Integer.valueOf(lines.get(0));
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (Exception e) {
			logger.error("创建读取记录文件失败! 异常信息:", e);
			return 0;
		}
	}
}
