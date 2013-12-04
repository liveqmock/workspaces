/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： RegisOptinos.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.bean;
/**
 * 类说明
 * @简述： 账号参数
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-27 下午02:32:17
 */
public class AccountParam extends AccountDocs{
	private static final long serialVersionUID = 703237641402489727L;

	private String sortColumn;
	private String sortType;
	private Integer start;
	private Integer row;
	private String registerTimeStart;
	private String registerTimeEnd;
	private String productId;
	private String loginIp;
	private String isfuzzy;
	
	public String getIsfuzzy() {
		return isfuzzy;
	}
	public void setIsfuzzy(String isfuzzy) {
		this.isfuzzy = isfuzzy;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public String getRegisterTimeStart() {
		return registerTimeStart;
	}
	public void setRegisterTimeStart(String registerTimeStart) {
		this.registerTimeStart = registerTimeStart;
	}
	public String getRegisterTimeEnd() {
		return registerTimeEnd;
	}
	public void setRegisterTimeEnd(String registerTimeEnd) {
		this.registerTimeEnd = registerTimeEnd;
	}
	@Override
	public String toString() {
		return "RegisterParam [sortColumn=" + sortColumn + ", sortType="
				+ sortType + ", start=" + start + ", row=" + row
				+ ", registerTimeStart=" + registerTimeStart
				+ ", registerTimeEnd=" + registerTimeEnd + ", productId="
				+ productId + ", loginIp=" + loginIp + "]";
	}
	
}
