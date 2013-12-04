/**      
 * BusinessLogger.java Create on 2012-9-27     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.common.server.log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.common.shared.model.LogEntity;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;

/**
 * @ClassName: BusinessLogger
 * @Description: 业务日志读写工具
 * @author 吴江晖
 * @date 2012-9-27 下午12:18:52
 * @Version 1.0
 * 
 */
public class BusinessLogger {

	private static final Logger logger = Logger.getLogger(BusinessLogger.class);

	private String targetSubfix;

	private String namespace = "p1.common.log";

	private String writerLogMethod = "addLog";

	private DBConnection dbConnectorConnection;

	@Autowired
	public void setTargetSubfix(final String targetSubfix) {
		this.targetSubfix = targetSubfix;
	}

	public void setNamespace(final String namespace) {
		this.namespace = namespace;
	}

	public void setWriterLogMethod(final String writerLogMethod) {
		this.writerLogMethod = writerLogMethod;
	}

	@Autowired
	public void setDbConnection(final DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	/**
	 * 写创建日志
	 * 
	 * @param productId
	 *            产品ID
	 * @param refId
	 *            引用ID
	 * @param note
	 *            日志内容
	 */
	public void writeCreateLog(final String productId, final Object object) {
		final Class<?> clazz = object.getClass();
		// 在类中查找是否有输出日志的注释
		final BizLog bizLog = clazz.getAnnotation(BizLog.class);
		if (bizLog != null) {
			// 根据注释获取引用ID在类中的对应字段
			final String refId = bizLog.relId();
			// 构造引用字段的Getter方法名称
			final String getter = buildGetter(refId);
			try {
				// 获取引用字段的Getter方法的声明
				final Method getMethod = clazz
						.getMethod(getter, new Class[] {});
				final LogEntity log = new LogEntity();
				log.setTypes(bizLog.value());
				// 根据日志输出模板构造日志内容
				log.setNote(bizLog.createTemplate());
				// 调用引用字段的Getter方法获取引用值
				log.setRelId(getMethod.invoke(object, new Object[] {}));
				writeLog(productId, log);
			} catch (final Exception e) {
				logger.error("生成创建日志失败");
				logger.error(e.getMessage());
			}
		} else {
			logger.warn("类" + clazz.getCanonicalName() + "没有标注BizLog，不生成日志");
		}
	}

	private String buildGetter(final String property) {
		return "get" + property.substring(0, 1).toUpperCase()
				+ property.substring(1);
	}

	/**
	 * 写修改日志
	 * 
	 * @param productId
	 *            产品ID
	 * @param oldObj
	 *            原对象
	 * @param newObj
	 *            新对象
	 * @param refId
	 *            引用ID
	 */
	public void writeModifyLog(final String productId, final Object oldObj,
			final Object newObj) {
		final Class<?> clazz = oldObj.getClass();
		// 在类中查找是否有输出日志的注释
		final BizLog bizLog = clazz.getAnnotation(BizLog.class);
		if (bizLog != null) {
			try {
				// 根据注释获取引用ID在类中的对应字段
				final String refId = bizLog.relId();
				// 构造引用字段的Getter方法名称
				String getter = buildGetter(refId);
				// 获取引用字段的Getter方法的声明
				final Method refGetMethod = clazz.getMethod(getter,
						new Class[] {});
				for (final Field field : oldObj.getClass().getDeclaredFields()) {
					// 在类中查找是否有输出日志的字段
					final BizLogField logField = field
							.getAnnotation(BizLogField.class);
					if (logField != null) {
						// 构造这个字段的Getter方法名称
						getter = buildGetter(field.getName());
						// 获取这个字段的Getter方法的声明
						final Method getMethod = clazz.getMethod(getter,
								new Class[] {});
						// 调用Getter方法获取原值
						Object oldValue = getMethod.invoke(oldObj,
								new Object[] {});
						if (oldValue == null) {
							oldValue = new String("");
						}
						// 调用Getter方法获取新值
						Object newValue = getMethod.invoke(newObj,
								new Object[] {});
						if (newValue == null) {
							newValue = new String("");
						}
						// 判断新旧值是否相等，如果不等，写修改日志
						if (!oldValue.equals(newValue)) {
							final LogEntity log = new LogEntity();
							// 从类日志注释中获取日志类型标识码
							log.setTypes(bizLog.value());
							// 根据日志输出模板构造日志内容
							log.setNote(MessageFormat.format(
									logField.template(), logField.value(),
									oldValue, newValue, log.getInsertUser()));
							// 调用引用字段的Getter方法获取引用值
							log.setRelId(refGetMethod.invoke(oldObj,
									new Object[] {}));
							writeLog(productId, log);
						}
					}
				}
			} catch (final Exception e) {
				logger.error("生成修改日志失败");
				logger.error(e.getMessage());
			}
		} else {
			logger.warn("类" + clazz.getCanonicalName() + "没有标注BizLog，不生成日志");
		}
	}

	protected SqlSessionTemplate getSqlSessionTemplate(final String productId) {
		final String targetId = productId + "-" + targetSubfix;
		final SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection
				.getClient(TargetType.GAME, productId, targetId, null);
		return sqlSessionTemplate;
	}

	protected int writeLog(final String productId, final LogEntity log) {
		return getSqlSessionTemplate(productId).insert(
				namespace + "." + writerLogMethod, log);
	}

	/**
	 * 查询操作日志
	 * 
	 * @param productId
	 *            产品ID
	 * @param types
	 *            操作类型
	 * @param relId
	 *            操作引用ID
	 * @param limit
	 *            最大条数（分页）
	 * @param offset
	 *            偏移值（分页）
	 */
	@SuppressWarnings("unchecked")
	public List<LogEntity> getLogs(final String productId, String types,
			String relId, Integer limit, Integer offset) {
		// 构建参数&查询
		final Map<String, Object> parameter = new HashMap<String, Object>();
		// 分页&排序参数
		parameter.put("limit", limit);
		parameter.put("offset", offset);
		// 业务参数
		parameter.put("relId", relId);
		parameter.put("types", types);

		return (List<LogEntity>) getSqlSessionTemplate(productId).selectList(
				namespace + "." + "getLogs", parameter);
	}

	/**
	 * 查询操作日志总条数
	 * 
	 * @param productId
	 *            产品ID
	 * @param types
	 *            操作类型
	 * @param relId
	 *            操作引用ID
	 */
	public Integer getLogsTotal(final String productId, String types,
			String relId) {
		// 构建参数&查询
		final Map<String, Object> parameter = new HashMap<String, Object>();
		// 业务参数
		parameter.put("relId", relId);
		parameter.put("types", types);

		return (Integer) getSqlSessionTemplate(productId).selectOne(
				namespace + "." + "getLogsTotal", parameter);
	}

	/**
	 * 返回一个格式化为分页样式的JSON数据
	 * 
	 * @param loadConfig
	 *            查询条件，必须包含
	 * 
	 *            <pre>
	 * relId=操作引用ID
	 * types=操作类型
	 * productId=产品ID
	 * limit=最大条数（分页）
	 * offset=偏移值（分页）
	 * </pre>
	 * 
	 * @return
	 */
	public String getLogs(final BaseFilterPagingLoadConfig loadConfig) {
		final String productId = loadConfig.<String> get("productId");

		if (StringUtils.isEmpty(productId)) {
			throw new RuntimeException("生成日志时，产品ID不能为空！");
		}

		String relId = loadConfig.<String> get("relId");
		String types = loadConfig.<String> get("types");
		Integer limit = loadConfig.<Integer> get("limit");
		Integer offset = loadConfig.<Integer> get("offset");

		int totalCount = getLogsTotal(productId, types, relId);
		List<LogEntity> logs = getLogs(productId, types, relId, limit, offset);

		// 返回结果
		final PagingLoadResult<LogEntity> result = new PagingLoadResult<LogEntity>();
		result.setRows(logs);
		result.setLimit(limit);
		result.setTotal(totalCount);
		result.setOffset(offset);

		return GridHelper.buildResults(result);
	}

}
