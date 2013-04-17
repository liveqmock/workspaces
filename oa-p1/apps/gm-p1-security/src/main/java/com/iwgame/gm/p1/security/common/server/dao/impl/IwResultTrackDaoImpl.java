/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： IwResultTrackDaoImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iwgame.gm.p1.security.common.server.dao.IwResultTrackDao;
import com.iwgame.gm.p1.security.common.server.db.BaseService;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.shared.model.IwResultTrack;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-20 上午11:58:38
 */
@Service
public class IwResultTrackDaoImpl extends BaseService implements IwResultTrackDao {	
	@Override
	public Integer insert(String productId,IwResultTrack iwrt) throws Exception {
		return insert(productId, 
				ConstantServer.SQL_MAPPER_IW_RESULT_TRACK_DAO+ConstantServer.SQL_ID_BASE_INSERT, iwrt);
	}

	@Override
	public Integer updateIwResultTrack(String productId, IwResultTrack iwrt)
			throws Exception {
		return update(productId, ConstantServer.SQL_MAPPER_IW_RESULT_TRACK_DAO+ConstantServer.SQL_ID_BASE_INSERT, iwrt);
	}

	@Override
	public List<IwResultTrack> getRecords(String productId,
			Map<String, Object> parameter) throws Exception {
		return selectList(productId, ConstantServer.SQL_MAPPER_IW_RESULT_TRACK_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}

	@Override
	public Integer countRecords(String productId, Map<String, Object> parameter)
			throws Exception {
		return selectOne(productId, ConstantServer.SQL_MAPPER_IW_RESULT_TRACK_DAO+ConstantServer.SQL_ID_BASE_SELECT_COUNT, parameter);
	}

}
