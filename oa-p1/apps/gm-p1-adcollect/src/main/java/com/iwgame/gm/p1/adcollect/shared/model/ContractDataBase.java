/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ContractDataBase.java
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
 * @简述： 合同
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-11 上午10:58:10
 */
@BizLog(value = "CONTRACT", createTemplate = "创建合同", relId = "id")
public class ContractDataBase implements Serializable {

	private static final long serialVersionUID = 7757653464483267525L;

	private int id;
	@BizLogField("合同LOGO")
	private int logoId;// 合同LOGO
	@BizLogField("合同抬头")
	private int titleId;// 合同抬头
	private String itemno;// 合同编号
	private Date signed;// 合同日期
	@BizLogField("合同日期")
	private String signedString;// 合同日期
	@BizLogField("合同名称")
	private String name;

	@BizLogField("申请人")
	private String applyman;// 申请人
	@BizLogField("所属部门")
	private String section;// 所属部门
	@BizLogField("合同等级")
	private String level;// 合同等级
	@BizLogField("合同媒体")
	private String mediaId; // 合同媒体
	@BizLogField("合同主旨")
	private String content;// 合同主旨

	@BizLogField("是否有附件")
	private String isatt;// 是否有附件
	@BizLogField("合同附件地址")
	private String path;// 合同附件地址
	@BizLogField("合作概述")
	private String description;// 合作概述
	@BizLogField("说明")
	private String note;
	@BizLogField("上次投放情况")
	private String adsituation;
	@BizLogField("合同金额")
	private double total;// 合同金额

	@BizLogField("注册成本")
	private double stdRegcost;// 注册成本
	@BizLogField(" 登录成本标准")
	private double stdLogincost;// 登录成本标准
	@BizLogField(" 折扣标准")
	private double stdDiscount;// 折扣标准
	@BizLogField(" 折扣")
	private double discount;// 折扣
	private Date payed;
	@BizLogField(" 付款日期")
	private String payedString;// 付款日期

	@BizLogField(" 广告数")
	private int adamt;
	private String remarks;
	private Date createTime;
	private Date updateTime;
	private String appraisal;

	private String logoName;
	private String titleName;
	private String mediaName;
	@BizLogField("合同类型")
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return 获取 signedString
	 */
	public String getSignedString() {
		return signedString;
	}

	/**
	 * @param signedString
	 *            设置 signedString
	 */
	public void setSignedString(final String signedString) {
		this.signedString = signedString;
	}

	/**
	 * @return 获取 payedString
	 */
	public String getPayedString() {
		return payedString;
	}

	/**
	 * @param payedString
	 *            设置 payedString
	 */
	public void setPayedString(final String payedString) {
		this.payedString = payedString;
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
	 * @return 获取 adsituation
	 */
	public String getAdsituation() {
		return adsituation;
	}

	/**
	 * @param adsituation
	 *            设置 adsituation
	 */
	public void setAdsituation(final String adsituation) {
		this.adsituation = adsituation;
	}

	/**
	 * @return 获取 logoName
	 */
	public String getLogoName() {
		return logoName;
	}

	/**
	 * @param logoName
	 *            设置 logoName
	 */
	public void setLogoName(final String logoName) {
		this.logoName = logoName;
	}

	/**
	 * @return 获取 titleName
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * @param titleName
	 *            设置 titleName
	 */
	public void setTitleName(final String titleName) {
		this.titleName = titleName;
	}

	/**
	 * @return 获取 mediaName
	 */
	public String getMediaName() {
		return mediaName;
	}

	/**
	 * @param mediaName
	 *            设置 mediaName
	 */
	public void setMediaName(final String mediaName) {
		this.mediaName = mediaName;
	}

	/**
	 * @return 获取 id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            设置 id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return 获取 logoId
	 */
	public int getLogoId() {
		return logoId;
	}

	/**
	 * @param logoId
	 *            设置 logoId
	 */
	public void setLogoId(final int logoId) {
		this.logoId = logoId;
	}

	/**
	 * @return 获取 titleId
	 */
	public int getTitleId() {
		return titleId;
	}

	/**
	 * @param titleId
	 *            设置 titleId
	 */
	public void setTitleId(final int titleId) {
		this.titleId = titleId;
	}

	/**
	 * @return 获取 itemno
	 */
	public String getItemno() {
		return itemno;
	}

	/**
	 * @param itemno
	 *            设置 itemno
	 */
	public void setItemno(final String itemno) {
		this.itemno = itemno;
	}

	/**
	 * @return 获取 signed
	 */
	public Date getSigned() {
		return signed;
	}

	/**
	 * @param signed
	 *            设置 signed
	 */
	public void setSigned(final Date signed) {
		this.signed = signed;
	}

	/**
	 * @return 获取 applyman
	 */
	public String getApplyman() {
		return applyman;
	}

	/**
	 * @param applyman
	 *            设置 applyman
	 */
	public void setApplyman(final String applyman) {
		this.applyman = applyman;
	}

	/**
	 * @return 获取 section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section
	 *            设置 section
	 */
	public void setSection(final String section) {
		this.section = section;
	}

	/**
	 * @return 获取 level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            设置 level
	 */
	public void setLevel(final String level) {
		this.level = level;
	}

	/**
	 * @return 获取 mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId
	 *            设置 mediaId
	 */
	public void setMediaId(final String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return 获取 content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            设置 content
	 */
	public void setContent(final String content) {
		this.content = content;
	}

	/**
	 * @return 获取 isatt
	 */
	public String getIsatt() {
		return isatt;
	}

	/**
	 * @param isatt
	 *            设置 isatt
	 */
	public void setIsatt(final String isatt) {
		this.isatt = isatt;
	}

	/**
	 * @return 获取 path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            设置 path
	 */
	public void setPath(final String path) {
		this.path = path;
	}

	/**
	 * @return 获取 description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            设置 description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return 获取 note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            设置 note
	 */
	public void setNote(final String note) {
		this.note = note;
	}

	/**
	 * @return 获取 total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            设置 total
	 */
	public void setTotal(final double total) {
		this.total = total;
	}

	/**
	 * @return 获取 stdRegcost
	 */
	public double getStdRegcost() {
		return stdRegcost;
	}

	/**
	 * @param stdRegcost
	 *            设置 stdRegcost
	 */
	public void setStdRegcost(final double stdRegcost) {
		this.stdRegcost = stdRegcost;
	}

	/**
	 * @return 获取 stdLogincost
	 */
	public double getStdLogincost() {
		return stdLogincost;
	}

	/**
	 * @param stdLogincost
	 *            设置 stdLogincost
	 */
	public void setStdLogincost(final double stdLogincost) {
		this.stdLogincost = stdLogincost;
	}

	/**
	 * @return 获取 stdDiscount
	 */
	public double getStdDiscount() {
		return stdDiscount;
	}

	/**
	 * @param stdDiscount
	 *            设置 stdDiscount
	 */
	public void setStdDiscount(final double stdDiscount) {
		this.stdDiscount = stdDiscount;
	}

	/**
	 * @return 获取 discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            设置 discount
	 */
	public void setDiscount(final double discount) {
		this.discount = discount;
	}

	/**
	 * @return 获取 payed
	 */
	public Date getPayed() {
		return payed;
	}

	/**
	 * @param payed
	 *            设置 payed
	 */
	public void setPayed(final Date payed) {
		this.payed = payed;
	}

	/**
	 * @return 获取 adamt
	 */
	public int getAdamt() {
		return adamt;
	}

	/**
	 * @param adamt
	 *            设置 adamt
	 */
	public void setAdamt(final int adamt) {
		this.adamt = adamt;
	}

	/**
	 * @return 获取 remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            设置 remarks
	 */
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return 获取 createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            设置 createTime
	 */
	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
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

	/**
	 * @return 获取 appraisal
	 */
	public String getAppraisal() {
		return appraisal;
	}

	/**
	 * @param appraisal
	 *            设置 appraisal
	 */
	public void setAppraisal(final String appraisal) {
		this.appraisal = appraisal;
	}

}
