/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MeidaDataBase.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.iwgame.gm.p1.common.server.log.BizLog;
import com.iwgame.gm.p1.common.server.log.BizLogField;

/**
 * 类说明
 * 
 * @简述： 硬广媒体
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-26 下午05:15:22
 */
@BizLog(value = "MEDIA", createTemplate = "创建媒体", relId = "id")
public class MeidaDataBase implements Serializable {

	private static final long serialVersionUID = -4094780299949418549L;

	private String id;// //媒体ID
	private String name;// 媒体名称
	private int type;// 媒体分类
	
	private int sort;// 媒体类型
	@BizLogField("媒体子类")
	private String subclass;// 媒体子类
	@BizLogField("地址")
	private String adds;// 地址
	@BizLogField("数据1")
	private int data1;// 数据1
	@BizLogField("数据2")
	private int data2;// 数据2
	@BizLogField("数据3")
	private int data3;// 数据3
	@BizLogField("数据4")
	private int data4;// 数据4
	private int count;// 广告位数量
	private int contractCount;// 广告数量
	private Date addTime;// 添加时间
	private Date updateTime;// 最后更新时间
	private String typeStr;
	@BizLogField("媒体类型")
	private String sortStr;

	/**
	 * @return 获取 typeStr
	 */
	public String getTypeStr() {
		return typeStr;
	}

	/**
	 * @param typeStr
	 *            设置 typeStr
	 */
	public void setTypeStr(final String typeStr) {
		this.typeStr = typeStr;
	}

	/**
	 * @return 获取 sortStr
	 */
	public String getSortStr() {
		return sortStr;
	}

	/**
	 * @param sortStr
	 *            设置 sortStr
	 */
	public void setSortStr(final String sortStr) {
		this.sortStr = sortStr;
	}

	/**
	 * @return 获取 id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            设置 id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return 获取 name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            设置 name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return 获取 type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            设置 type
	 */
	public void setType(final int type) {
		this.type = type;
	}

	/**
	 * @return 获取 sort
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            设置 sort
	 */
	public void setSort(final int sort) {
		this.sort = sort;
	}

	/**
	 * @return 获取 媒体子类
	 */
	public String getSubclass() {
		return subclass;
	}

	/**
	 * @param subclass
	 *            设置 媒体子类
	 */
	public void setSubclass(final String subclass) {
		this.subclass = subclass;
	}

	/**
	 * @return 获取 地址
	 */
	public String getAdds() {
		return adds;
	}

	/**
	 * @param adds
	 *            设置 地址
	 */
	public void setAdds(final String adds) {
		this.adds = adds;
	}

	/**
	 * @return 获取 data1
	 */
	public int getData1() {
		return data1;
	}

	/**
	 * @param data1
	 *            设置 data1
	 */
	public void setData1(final int data1) {
		this.data1 = data1;
	}

	/**
	 * @return 获取 data2
	 */
	public int getData2() {
		return data2;
	}

	/**
	 * @param data2
	 *            设置 data2
	 */
	public void setData2(final int data2) {
		this.data2 = data2;
	}

	/**
	 * @return 获取 data3
	 */
	public int getData3() {
		return data3;
	}

	/**
	 * @param data3
	 *            设置 data3
	 */
	public void setData3(final int data3) {
		this.data3 = data3;
	}

	/**
	 * @return 获取 data4
	 */
	public int getData4() {
		return data4;
	}

	/**
	 * @param data4
	 *            设置 data4
	 */
	public void setData4(final int data4) {
		this.data4 = data4;
	}

	/**
	 * // * @return 获取 广告数
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            设置 广告数
	 */
	public void setCount(final int count) {
		this.count = count;
	}

	/**
	 * @return 获取 合同数
	 */
	public int getContractCount() {
		return contractCount;
	}

	/**
	 * @param contractCount
	 *            设置 合同数
	 */
	public void setContractCount(final int contractCount) {
		this.contractCount = contractCount;
	}

	/**
	 * @return 获取 addTime
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime
	 *            设置 addTime
	 */
	public void setAddTime(final Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * @return 获取 updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            设置 updateTime
	 */
	public void setUpdateTime(final Date updateTime) {
		this.updateTime = updateTime;
	}

}
