/**      
 * TimeTypeHandler.java Create on 2012-6-7     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.shared.model;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * @ClassName: TimeTypeHandler
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-7 下午06:01:41
 * @Version 1.0
 * 
 */
public class TimeTypeHandler implements TypeHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement
	 * , int, java.lang.Object, org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setParameter(final PreparedStatement ps, final int i,
			final Object parameter, final JdbcType jdbcType)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet,
	 * java.lang.String)
	 */
	@Override
	public Object getResult(final ResultSet rs, final String columnName)
			throws SQLException {
		String time = rs.getString(columnName);
		SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMddHH");
		try {
			return dformat.parse(time);
		} catch (ParseException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement,
	 * int)
	 */
	@Override
	public Object getResult(final CallableStatement cs, final int columnIndex)
			throws SQLException {
		String time = cs.getString(columnIndex);
		SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMddHH");
		try {
			return dformat.parse(time);
		} catch (ParseException e) {
			return null;
		}
	}

}
