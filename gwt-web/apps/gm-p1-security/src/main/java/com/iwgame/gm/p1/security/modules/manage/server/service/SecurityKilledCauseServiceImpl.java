/**      
 * KilledCause.java Create on 2012-11-15     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.gm.p1.security.modules.manage.server.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gwt.dev.util.collect.HashMap;
import com.iwgame.gm.p1.security.common.server.OperatorLogger;
import com.iwgame.gm.p1.security.common.server.util.LoadResultHelper;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.gm.p1.security.modules.manage.server.dao.SecurityKilledCauseDao;
import com.iwgame.gm.p1.security.modules.manage.shared.rpc.SecurityKilledCauseService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.server.SecurityUserHolder;
import com.iwgame.xportal.common.server.core.model.Operator;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;
/** 
 * @简述: 封杀原因配置实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 */
@Service
@NeedAuthorization
public class SecurityKilledCauseServiceImpl implements SecurityKilledCauseService{

	@Resource(name="securityKilledCauseDaoImpl")
	private SecurityKilledCauseDao killedCauseDao;

	@Resource(name="businessLogger")
	private OperatorLogger logger;
	
	private final Logger log4j = Logger.getLogger(SecurityKilledCauseServiceImpl.class);

	@Override
	public String loadKilledCauseData(String productId,BaseFilterPagingLoadConfig loadConfig)
			throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		if (loadConfig != null) {
			parameter.put("causeType", loadConfig.<Integer>get("causeType"));
			parameter.put("killedType", loadConfig.<Integer>get("killedType"));
			parameter.put("causeNumber", loadConfig.<Integer>get("causeNumber"));
			putParams(loadConfig, parameter);
		}
		String resultJson = StringUtils.EMPTY;
		try {
			List<KilledCauseConfig> list = killedCauseDao.getKilledCauses(productId,parameter);
			Integer total = killedCauseDao.countKilledCauses(productId,parameter);
			resultJson = LoadResultHelper.buildResult(list, (Integer)parameter.get("limit"), (Integer)parameter.get("offset"), total);
		} catch (Exception e) {
			log4j.error("loadKilledCauseData:", e);
		}
		return resultJson;

	}


	@AccessResource(name="security-manage-killedCause-add")
	@Override
	public boolean addKilledCause(String productId,KilledCauseConfig killedCause)
			throws Exception {
		if (killedCause!=null) {
			try {
				Operator operator = SecurityUserHolder.getCurrentUser();
				if (operator!=null) {
					String creator = operator.getUsername();
					killedCause.setCreator(creator);
				}
				int count = killedCauseDao.insertKilledCauses(productId,killedCause);
				logger.writeCreateLog(productId, killedCause);
				return count==1;
			} catch (Exception e) {
				log4j.error("addKilledCause", e);
			}
		}
		return false;
	}


	@Override
	public KilledCauseConfig getKilledCauseById(String productId,Integer id) throws Exception {

		return killedCauseDao.getKilledCauseById(productId,id);
	}

	@AccessResource(name="security-manage-killedCause-update")
	@Override
	public boolean updateKilledCause(String productId,KilledCauseConfig killedCause)
			throws Exception {
		if (killedCause!=null) {
			KilledCauseConfig oldCauseConfig = getKilledCauseById(productId, killedCause.getId());
			if (oldCauseConfig==null) {
				throw new Exception("[该记录已不存在,更新失败]:id"+killedCause.getId());
			}
			try {
				int count = killedCauseDao.updateKilledCause(productId,killedCause);
				logger.writeModifyLog(productId, oldCauseConfig, killedCause);
				return count==1;
			} catch (Exception e) {
				log4j.error("updateKilledCause:", e);
			}
		}
		return false;
	}

	@AccessResource(name="security-manage-killedCause-delete")
	@Override
	public boolean deleteKilledCause(String productId,List<Integer> ids) throws Exception {
		if (ids!=null&&ids.size()>0) {
			try {
				int count = killedCauseDao.deleteKilledCause(productId,ids);
				return count==ids.size();
			} catch (Exception e) {
				log4j.error("deleteKilledCause:",e);
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
