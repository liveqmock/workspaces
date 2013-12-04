/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FlumeManageServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.sever.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.cloudera.flume.master.Command;
import com.cloudera.flume.master.CommandStatus;
import com.cloudera.flume.util.AdminRPC;
import com.cloudera.flume.util.AdminRPCThrift;
import com.iwgame.iwcloud.flume.admin.sever.service.FlumeManageService;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeAnodeConfigForServer;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeCnodeConfigForServer;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;

/**
 * 类说明
 * 
 * @简述： 实现类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-25 下午9:32:56
 */
public class FlumeManageServiceImpl implements FlumeManageService {
	private static Logger logger = Logger.getLogger(FlumeManageServiceImpl.class);

	private StringRedisTemplate flumeRedisTemplate;
	private StringRedisTemplate flumeRedisDataTemplate;
	private DBConnection dbConnectorConnection;
	private String masterHost;
	private int masterPort;

	@Autowired
	public void setDbConnection(final DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	@Autowired
	public void setFlumeRedisTemplate(final StringRedisTemplate flumeRedisTemplate) {
		this.flumeRedisTemplate = flumeRedisTemplate;
	}

	@Autowired
	public void setFlumeRedisDataTemplate(final StringRedisTemplate flumeRedisDataTemplate) {
		this.flumeRedisDataTemplate = flumeRedisDataTemplate;
	}

	public void setMasterHost(final String masterHost) {
		this.masterHost = masterHost;
	}

	public void setMasterPort(final int masterPort) {
		this.masterPort = masterPort;
	}

	@Override
	public String unconfigAnode(final String configJson) {
		String msg = "";
		try {
			// 发送 unconfig命令到 flume master
			FlumeAnodeConfigForServer anodeConfig = FlumeAnodeConfigForServer.make(configJson);

			msg = unconfigFlumeNode(anodeConfig.getAnodeId());
		} catch (Exception e) {
			logger.error(e);
			msg = e.getMessage();
			if ((msg == null) || msg.isEmpty()) {
				msg = "未知错误,请查看后台日志。";
			}
		}
		return msg;
	}

	@Override
	public String configAnode(final String configJson) {
		String msg = "";
		try {
			AdminRPC adminClient = new AdminRPCThrift(masterHost, masterPort);
			// 发送 config命令到 flume master
			FlumeAnodeConfigForServer anodeConfig = FlumeAnodeConfigForServer.make(configJson);

			Command command = new Command("config", anodeConfig.getAnodeId(), anodeConfig.getFlowId(),
					anodeConfig.getSource(), anodeConfig.getSink());
			long cmdId = adminClient.submit(command);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			CommandStatus status = adminClient.getCommandStatus(cmdId);

			msg = status.getMessage();
		} catch (Exception e) {
			logger.error(e);
			msg = e.getMessage();
			if ((msg == null) || msg.isEmpty()) {
				msg = "未知错误,请查看后台日志。";
			}
		}
		return msg;
	}

	@Override
	public String unconfigCnode(final String configJson) {
		String msg = "";
		try {
			// 发送 unconfig命令到 flume master
			FlumeCnodeConfigForServer cnodeConfig = FlumeCnodeConfigForServer.make(configJson);

			msg = unconfigFlumeNode(cnodeConfig.getCnodeId());
		} catch (Exception e) {
			logger.error(e);
			msg = e.getMessage();
			if ((msg == null) || msg.isEmpty()) {
				msg = "未知错误,请查看后台日志。";
			}
		}

		return msg;
	}

	@Override
	public String configCnode(final String configJson) {
		String msg = "";
		try {
			AdminRPC adminClient = new AdminRPCThrift(masterHost, masterPort);
			// 发送 config命令到 flume master
			FlumeCnodeConfigForServer cnodeConfig = FlumeCnodeConfigForServer.make(configJson);

			Command command = new Command("config", cnodeConfig.getCnodeId(), cnodeConfig.getFlowId(),
					cnodeConfig.getSource(), cnodeConfig.getSink());
			long cmdId = adminClient.submit(command);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			CommandStatus status = adminClient.getCommandStatus(cmdId);

			msg = status.getMessage();
		} catch (Exception e) {
			logger.error(e);
			msg = e.getMessage();
			if ((msg == null) || msg.isEmpty()) {
				msg = "未知错误,请查看后台日志。";
			}
		}
		return msg;

	}

	@Override
	public String openChannel(final String channelId, final String physicalAnode, final String physicalCnode) {
		String msg = "";
		try {
			// 取消通道配置
			String anodeId = channelId;
			anodeId = anodeId.replace("flow", "anode");
			unconfigFlumeNode(anodeId); // 取消采集通道

			// String cnodeId = channelId;
			// cnodeId = cnodeId.replace("flow", "cnode");
			// unconfigFlumeNode(cnodeId); // 取消收集通道

			// 开启通道
			mapFlumeNode(physicalAnode, anodeId);
			// mapFlumeNode(physicalCnode, cnodeId);

			// 设置数据库的状态
			String productId = getProuctIdFromChannelId(channelId);
			// 获取连接，查询
			String targetId = productId + "-FlumeMonitor";
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId,
					targetId, null);

			// 构建参数&查询
			logger.info("[closeChannel]修改通道状态,channelId:" + channelId);

			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("channelId", channelId);
			parameter.put("status", 0);// 1：表示通道为关闭状态
			sqlSessionTemplate.insert("flume-monitor.updateChannelStatus", parameter);

			msg = "开通通道成功!";
		} catch (Exception e) {
			logger.error(e);
			msg = e.getMessage();
			if ((msg == null) || msg.isEmpty()) {
				msg = "未知错误,请查看后台日志。";
			}
		}

		return msg;
	}

	@Override
	public String closeChannel(final String channelId, final String physicalAnode, final String physicalCnode) {
		String msg = "";
		try {
			// 取消通道配置
			String anodeId = channelId;
			anodeId = anodeId.replace("flow", "anode");
			unconfigFlumeNode(anodeId); // 取消采集通道
			//
			// String cnodeId = channelId;
			// cnodeId = cnodeId.replace("flow", "cnode");
			// unconfigFlumeNode(cnodeId); // 取消收集通道

			// 关闭通道
			unMapFlumeNode(physicalAnode, anodeId);
			// unMapFlumeNode(physicalCnode, cnodeId);

			// 刷新通道表
			refreshFLumetable(anodeId);
			// refreshFLumetable(cnodeId);

			// 设置数据库的状态
			String productId = getProuctIdFromChannelId(channelId);
			// 获取连接，查询
			String targetId = productId + "-FlumeMonitor";
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId,
					targetId, null);

			// 构建参数&查询
			logger.info("[closeChannel]修改通道状态,channelId:" + channelId);

			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("channelId", channelId);
			parameter.put("status", 1);// 1：表示通道为关闭状态
			sqlSessionTemplate.insert("flume-monitor.updateChannelStatus", parameter);

			// 删除Redis中的key
			String channelFlag = getChannelFlagFromChannelId(channelId);
			for (String channelListKey : flumeRedisTemplate.keys("*" + channelFlag + "*channel:list")) {

				flumeRedisTemplate.boundSetOps(channelListKey).remove(channelId); // 删除Redis中的key
			}

			msg = "关闭通道成功!";
		} catch (Exception e) {
			logger.error(e);
			msg = e.getMessage();
			if ((msg == null) || msg.isEmpty()) {
				msg = "未知错误,请查看后台日志。";
			}
		}

		return msg;
	}

	// 仅适应于特定的channelid约定
	private String getProuctIdFromChannelId(final String channelId) {
		try {
			String[] tmp = channelId.toUpperCase().split("\\.");
			String productId = tmp[tmp.length - 1];
			if (productId.startsWith("P")) {
				return productId;
			} else {
				return tmp[tmp.length - 2] + "." + productId;
			}
		} catch (Exception e) {
			return "unkonwn";
		}
	}

	private String getChannelFlagFromChannelId(final String channelId) {
		try {
			String[] tmp = channelId.split("\\.");
			return tmp[0];
		} catch (Exception e) {
			return "unkonw";
		}
	}

	/**
	 * 根据通道节点id重置配置
	 * 
	 * @param nodeId
	 * @return
	 * @throws IOException
	 */
	private String unconfigFlumeNode(final String nodeId) throws IOException {
		AdminRPC adminClient = new AdminRPCThrift(masterHost, masterPort);
		Command command = new Command("unconfig", nodeId);
		long cmdId = adminClient.submit(command);
		CommandStatus status = adminClient.getCommandStatus(cmdId);

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return status.getMessage();
	}

	/**
	 * 根据通道节点id开启通道
	 * 
	 * @param nodeId
	 * @return
	 * @throws IOException
	 */
	private String mapFlumeNode(final String physicalnode, final String nodeId) throws IOException {
		AdminRPC adminClient = new AdminRPCThrift(masterHost, masterPort);
		Command command = new Command("map", physicalnode, nodeId);
		long cmdId = adminClient.submit(command);
		CommandStatus status = adminClient.getCommandStatus(cmdId);

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status.getMessage();
	}

	/**
	 * 根据通道节点id关闭通道
	 * 
	 * @param nodeId
	 * @return
	 * @throws IOException
	 */
	private String unMapFlumeNode(final String physicalnode, final String nodeId) throws IOException {
		AdminRPC adminClient = new AdminRPCThrift(masterHost, masterPort);
		Command command = new Command("unmap", physicalnode, nodeId);
		long cmdId = adminClient.submit(command);
		CommandStatus status = adminClient.getCommandStatus(cmdId);

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status.getMessage();
	}

	/**
	 * 刷新通道表
	 * 
	 * @param nodeId
	 * @return
	 * @throws IOException
	 */
	private String refreshFLumetable(final String nodeId) throws IOException {
		AdminRPC adminClient = new AdminRPCThrift(masterHost, masterPort);
		Command command = new Command("purge", nodeId);
		long cmdId = adminClient.submit(command);
		CommandStatus status = adminClient.getCommandStatus(cmdId);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status.getMessage();
	}

	@Override
	public int getNextRecommendPort(final String productId, final String channelId) {
		try {
			// 获取连接，查询
			String targetId = productId + "-FlumeMonitor";
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId,
					targetId, null);

			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("channelFlag", getChannelFlagFromChannelId(channelId));
			parameter.put("productId", productId);
			int port = (Integer) sqlSessionTemplate.selectOne("flume-monitor.queryNextRecommendPort", parameter);

			return port + 1;
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public void confirmDataIntegrity(final String channelId, final String date) {
		try {
			// 获取sinkcount计数
			Object totalSinkCount = flumeRedisDataTemplate.boundHashOps(
					"t:status:groupid:" + channelId + ":day:" + date).get("fetcher_count");
			flumeRedisDataTemplate.boundHashOps("t:status:groupid:" + channelId + ":day:" + date).put("sink_count",
					totalSinkCount);
			// 循环,把对应的通道的数据都计平

			for (String channelkey : flumeRedisDataTemplate.keys("*:groupid:" + channelId + ":channel:list")) {
				for (String channel : flumeRedisDataTemplate.boundSetOps(channelkey).members()) {
					logger.debug("Redis中通道名称：" + channel);
					Object fetcherCount = flumeRedisDataTemplate.boundHashOps(
							"c:status:channelid:" + channel + ":day:" + date).get("fetcher_count");

					flumeRedisDataTemplate.boundHashOps("c:status:channelid:" + channel + ":day:" + date).put(
							"sink_count", fetcherCount);
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void validateChannel(final String productId) {
		try {
			long startTime = System.currentTimeMillis();
			Calendar calendar = Calendar.getInstance();
			String day = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
			String time = new SimpleDateFormat("yyyyMMddHH").format(calendar.getTime());
			logger.info("开始检测flume节点数据采集状态；时间为：" + day);

			for (String groupkey : flumeRedisTemplate.keys(productId.toLowerCase() + ":group:list")) {
				for (String group : flumeRedisTemplate.boundSetOps(groupkey).members()) {
					String warning = "0";
					for (String channelkey : flumeRedisTemplate.keys("*:groupid:" + group + ":channel:list")) {
						for (String channel : flumeRedisTemplate.boundSetOps(channelkey).members()) {
							// 检测某通道当前时间点是否有数据
							Object fetcherCount = flumeRedisTemplate.boundHashOps(
									"m:channelid:" + channel + ":time:" + time).get("fetcher_count");

							if ((fetcherCount == null) || fetcherCount.equals("0")) {
								warning = "1";
								// 单个通道的状态
								flumeRedisTemplate.boundHashOps("c:status:channelid:" + channel + ":day:" + day).put(
										"monitor_status", "1");
							} else {
								// 单个通道的状态
								flumeRedisTemplate.boundHashOps("c:status:channelid:" + channel + ":day:" + day).put(
										"monitor_status", "0");
							}

							Thread.sleep(1);
						}
					}
					flumeRedisTemplate.boundHashOps("t:status:groupid:" + group + ":day:" + day).put("monitor_status",
							warning);

				}
			}
			long endTime = System.currentTimeMillis();
			logger.info("本次检测完成,耗时：" + (endTime - startTime) / 1000 + "秒! \n");
		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Override
	public void unlockChannelGroupWarning(final String groupId) {
		try {
			Calendar calendar = Calendar.getInstance();
			String day = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
			flumeRedisTemplate.boundHashOps("t:status:groupid:" + groupId + ":day:" + day).put("monitor_status", "0");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void unlockChannelWarning(final String channelId) {
		try {
			Calendar calendar = Calendar.getInstance();
			String day = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
			flumeRedisTemplate.boundHashOps("c:status:channelid:" + channelId + ":day:" + day).put("monitor_status",
					"0");
			flumeRedisTemplate.boundHashOps("c:status:channelid:" + channelId + ":day:" + day).put("fetcher_last_time",
					String.valueOf(System.currentTimeMillis()));
			flumeRedisTemplate.boundHashOps("c:status:channelid:" + channelId + ":day:" + day).put("sink_last_time",
					String.valueOf(System.currentTimeMillis()));

		} catch (Exception e) {
			logger.error(e);
		}
	}

}
