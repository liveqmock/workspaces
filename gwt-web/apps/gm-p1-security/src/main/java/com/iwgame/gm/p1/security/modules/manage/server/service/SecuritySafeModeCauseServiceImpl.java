/**      
 * SecuritySafeModeCauseServiceImpl.java Create on 2012-11-16     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */ 

package com.iwgame.gm.p1.security.modules.manage.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gwt.dev.util.collect.HashMap;
import com.iwgame.gm.p1.security.common.server.OperatorLogger;
import com.iwgame.gm.p1.security.common.server.util.DateHelper;
import com.iwgame.gm.p1.security.common.server.util.LoadResultHelper;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;
import com.iwgame.gm.p1.security.modules.manage.server.dao.SecuritySafeModeCauseDao;
import com.iwgame.gm.p1.security.modules.manage.shared.rpc.SecuritySafeModeCauseService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.server.SecurityUserHolder;
import com.iwgame.xportal.common.server.core.model.Operator;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;

/** 
 * @简述: 安全模式备注服务实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-16 下午04:03:17 
 */
@Service
public class SecuritySafeModeCauseServiceImpl implements
SecuritySafeModeCauseService {

	@Resource(name="securitySafeModeCauseDaoImpl")
	private SecuritySafeModeCauseDao safeModeCauseDao;

	@Resource(name="businessLogger")
	private OperatorLogger logger;

	private final Logger log4j = Logger.getLogger(SecuritySafeModeCauseServiceImpl.class);
	
	@Override
	public String loadSafeModeCauseData(String productId,BaseFilterPagingLoadConfig loadConfig)
			throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		if (loadConfig != null) {
			parameter.put("creator", loadConfig.get("creator"));
			Date startDate = loadConfig.<Date>get("startDate");
			Date endDate = loadConfig.<Date>get("endDate");
			if (startDate!=null && endDate!=null) {
				parameter.put("startDate", DateHelper.getDateString(startDate, "yyyy-MM-dd"));
				parameter.put("endDate", DateHelper.getDateString(endDate, "yyyy-MM-dd"));
			}
			putParams(loadConfig, parameter);
		}
		String resultJson = StringUtils.EMPTY;
		try {
			List<SafeModeCauseConfig> list = safeModeCauseDao.getSafeModeCauses(productId,parameter);
			Integer total = safeModeCauseDao.countSafeModeCauses(productId,parameter);
			resultJson = LoadResultHelper.buildResult(list, (Integer)parameter.get("limit"), (Integer)parameter.get("offset"), total);
		} catch (Exception e) {
			log4j.error("loadSafeModeCauseData:", e);
		}
		return resultJson;
	}

	@Override
	public SafeModeCauseConfig getSafeModeCauseById(String productId,Integer id)
			throws Exception {
		return safeModeCauseDao.getSafeModeCauseById(productId,id);
	}

	@AccessResource(name="security-manage-safeModeCause-update")
	@Override
	public boolean updateSafeModeCause(String productId,SafeModeCauseConfig safeModeCause)
			throws Exception {
		if (safeModeCause!=null) {
			SafeModeCauseConfig oldCauseConfig = getSafeModeCauseById(productId, safeModeCause.getId());
			if (oldCauseConfig==null) {
				throw new Exception("[该记录已不存在,更新失败]:id"+safeModeCause.getId());
			}
			try {
				int count = safeModeCauseDao.updateSafeModeCause(productId,safeModeCause);
				logger.writeModifyLog(productId, oldCauseConfig, safeModeCause);
				return count==1;
			} catch (Exception e) {
				log4j.error("updateSafeModeCause:", e);
			}
		}
		return false;
	}

	@AccessResource(name="security-manage-safeModeCause-add")
	@Override
	public boolean addSafeModeCause(String productId,SafeModeCauseConfig safeModeCause)
			throws Exception {
		if (safeModeCause!=null) {
			Operator operator = SecurityUserHolder.getCurrentUser();
			if (operator!=null) {
				String creator = operator.getUsername();
				safeModeCause.setCreator(creator);
			}
			try {
				int count = safeModeCauseDao.insertSafeModeCauses(productId,safeModeCause);
				logger.writeCreateLog(productId, safeModeCause);
				return count==1;
			} catch (Exception e) {
				log4j.error("addSafeModeCause:", e);
			}
		}
		return false;
	}

	@AccessResource(name="security-manage-safeModeCause-delete")
	@Override
	public boolean deleteSafeModeCause(String productId,List<Integer> ids) throws Exception {
		if (ids!=null&&ids.size()>0) {
			try {
				int count = safeModeCauseDao.deleteSafeModeCause(productId,ids);
				return count==ids.size();
			} catch (Exception e) {
				log4j.error("deleteSafeModeCause:", e);
			}
		}
		return false;
	}
	/**
	 * @简述: 追加通用参数
	 * @param loadConfig
	 * @param parameter
	 */
	private void putParams(BaseFilterPagingLoadConfig loadConfig,
			Map<String, Object> parameter) {
		parameter.put("offset", loadConfig.<Integer> get("offset"));
		parameter.put("limit", loadConfig.<Integer> get("limit"));
		String sortColumn = "id";
		String sortType = "desc";
		parameter.put("sortColumn",sortColumn);
		parameter.put("sortType", sortType);
	}
}
