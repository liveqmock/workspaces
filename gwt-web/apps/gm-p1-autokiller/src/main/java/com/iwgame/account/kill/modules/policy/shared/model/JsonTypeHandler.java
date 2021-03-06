/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： JsonTypeHandler.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.shared.model;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-10 下午04:42:26
 */
public class JsonTypeHandler implements TypeHandler {

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		Policy p = new Policy();
		JSONObject jsonBonus = JSONObject.fromObject(rs.getString(columnName));
		DynaBean bean= (DynaBean) JSONObject.toBean(jsonBonus);
		p.setPaid((Integer)bean.get("paid"));
		p.setLevel((Integer)bean.get("level"));
		p.setLevelOpt((String)bean.get("levelOpt"));
		p.setDelay((Integer)bean.get("delay"));
		p.setReason((Integer)bean.get("reason"));
		p.setDays((Integer)bean.get("days"));
		p.setAccounts((Integer)bean.get("accounts"));
		p.setDueDays((Integer)bean.get("dueDays"));
		return p;
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
