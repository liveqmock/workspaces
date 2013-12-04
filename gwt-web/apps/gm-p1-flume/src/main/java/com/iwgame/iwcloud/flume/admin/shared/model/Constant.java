/**      
 * Constant.java Create on 2012-4-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.shared.model;

/**
 * @ClassName: Constant
 * @Description: redis常数字串
 * @author 吴江晖
 * @date 2012-4-11 下午05:33:06
 * @Version 1.0
 * 
 */
public interface Constant {

	/**
	 * [产品ID]:group:list
	 */
	static final String GROUP_LIST_KEY = "{0}:group:list";

	/**
	 * [组ID]:groupid:[通道ID]:channel:list
	 */
	static final String CHANNEL_LIST_KEY = "{0}:groupid:{1}:channel:list";

	/**
	 * groupid:[组ID]:info
	 */
	static final String GROUP_INFO_KEY = "groupid:{0}:info";

	/**
	 * t:status:groupid:[组ID]:day:[查询日期(yyyyMMdd)]
	 */
	static final String GROUP_DATA_KEY = "t:status:groupid:{0}:day:{1}";

	/**
	 * c:status:channelid:[通道ID]:day:[查询日期(yyyyMMdd)]
	 */
	static final String CHANNEL_DATA_KEY = "c:status:channelid:{0}:day:{1}";

	/**
	 * groupid:*:info->[字段名]
	 */
	static final String SORT_GROUP_INFO_KEY = "groupid:*:info->{0}";

	/**
	 * channelid:*:info->[字段名]
	 */
	static final String SORT_CHANNEL_INFO_KEY = "channelid:*:info->{0}";

	/**
	 * t:status:groupid:*:day:[查询日期(yyyyMMdd)]->[字段名]
	 */
	static final String SORT_GROUP_DATA_KEY = "t:status:groupid:*:day:{0}->{1}";

	/**
	 * c:status:channelid:*:day:[查询日期(yyyyMMdd)]->[字段名]
	 */
	static final String SORT_CHANNEL_DATA_KEY = "c:status:channelid:*:day:{0}->{1}";

	/**
	 * m:channelid:[通道ID]:time:[查询日期(yyyyMMdd)]*->[字段名]
	 */
	static final String SORT_TIME_DATA_KEY_CHANNEL = "m:channelid:{0}:time:{1}*->{2}";

	/**
	 * m:groupid:[组ID]:time:[查询日期(yyyyMMdd)]*->[字段名]
	 */
	static final String SORT_TIME_DATA_KEY_GROUP = "m:groupid:{0}:time:{1}*->{2}";

	public static final String FETCHER_COUNT = "fetcher_count";// 采集汇总数
	public static final String FETCHER_LAST_TIME = "fetcher_last_time";// 采集最后更新时间
	public static final String FETCHER_STATUS = "fetcher_status";// 采集状态
	public static final String SINK_COUNT = "sink_count";// 收集汇总数
	public static final String SINK_LAST_TIME = "sink_last_time";// 收集最后更新时间
	public static final String SINK_STATUS = "sink_status";// 收集状态
	public static final String SINK_COST_TIME = "sink_cost_time";// 收集耗时
	public static final String MONITOR_STATUS = "monitor_status";// 监控状态
}
