/**      
 * SecurityDangerIdCardServiceImpl.java Create on 2012-11-19     
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
import com.iwgame.gm.p1.security.common.shared.model.DangerIdCard;
import com.iwgame.gm.p1.security.modules.manage.server.dao.SecurityDangerIdCardDao;
import com.iwgame.gm.p1.security.modules.manage.shared.rpc.SecurityDangerIdCardService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/** 
 * @简述: 高危身份证服务实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-19 下午02:52:20 
 * 
 */
@Service
@NeedAuthorization
public class SecurityDangerIdCardServiceImpl implements
SecurityDangerIdCardService {

	@Resource(name="securityDangerIdCardDaoImpl")
	private SecurityDangerIdCardDao dangerIdCardDao;
	@Resource(name="businessLogger")
	private OperatorLogger logger;
	
	private final Logger log4j = Logger.getLogger(SecurityDangerIdCardServiceImpl.class);
	
	@Override
	public String loadDangerIdCardData(String productId,BaseFilterPagingLoadConfig loadConfig)
			throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		if (loadConfig != null) {
			parameter.put("operator", loadConfig.get("operator"));
			parameter.put("idCard", loadConfig.get("idCard"));
			Date startDate = loadConfig.<Date>get("startDate");
			Date endDate = loadConfig.<Date>get("endDate");
			if (startDate!=null&&endDate!=null) {
				parameter.put("startDate", DateHelper.getDateString(startDate, "yyyy-MM-dd"));
				parameter.put("endDate", DateHelper.getDateString(endDate, "yyyy-MM-dd"));
			}
			
			putParams(loadConfig, parameter);
		}
		String resultJson = StringUtils.EMPTY;

		try {
			List<DangerIdCard> list = dangerIdCardDao.getDangerIdCards(productId,parameter);

			Integer total = dangerIdCardDao.countDangerIdCards(productId,parameter);
			resultJson = LoadResultHelper.buildResult(list, (Integer)parameter.get("limit"), (Integer)parameter.get("offset"), total);
		} catch (Exception e) {
			log4j.error("loadDangerIdCardData：", e);
		}
		return resultJson;
	}

	@Override
	public DangerIdCard getDangerIdCardById(String productId,Integer id) throws Exception {

		return dangerIdCardDao.getById(productId,id);
	}

	@Override
	@AccessResource(name="security-manage-dangerIdCard-update")
	public boolean updateDangerIdCard(String productId,DangerIdCard dangerIdCard)
			throws Exception {
		if (dangerIdCard!=null&&dangerIdCard.getId()!=0) {
			DangerIdCard oldIdCard = getDangerIdCardById(productId, dangerIdCard.getId());
			if (oldIdCard==null) {
				throw new Exception("[该记录已不存在,更新失败]-id"+dangerIdCard.getId());
			}
			try {
				int count = dangerIdCardDao.update(productId,dangerIdCard);
				//记录操作日志
				logger.writeModifyLog(productId, oldIdCard, dangerIdCard);
				return count==1;
			} catch (Exception e) {
				log4j.error("updateDangerIdCard：",e);
			}
		}
		return false;
	}

	@Override
	@AccessResource(name="security-manage-dangerIdCard-add")
	public boolean addDangerIdCard(String productId,List<DangerIdCard> idCards) throws Exception {
		if (idCards!=null&&idCards.size()>0) {
			try {
				int count=0;
				for (DangerIdCard dangerIdCard : idCards) {
					dangerIdCard.setStatus(0);
					count += dangerIdCardDao.insert(productId, dangerIdCard);
					//记录操作日志
					logger.writeCreateLog(productId, dangerIdCard);
				}
				return count==idCards.size();
			} catch (Exception e) {
				log4j.error("addDangerIdCard：",e);
			}finally{
				idCards.clear();
				idCards=null;
			}
		}
		return false;
	}

	@Override
	@AccessResource(name="security-manage-dangerIdCard-delete")
	public boolean deleteDangerIdCard(String productId,List<Integer> ids) throws Exception {
		if (ids!=null&&ids.size()>0) {
			try {
				int count = dangerIdCardDao.delete(productId,ids);
				return count==ids.size();
			} catch (Exception e) {
				log4j.error("deleteDangerIdCard：",e);
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
		String sortColumn = loadConfig.getSortField();
		if (sortColumn==null) {
			sortColumn="id";
		}else if (sortColumn.equals("idCard")) {
			sortColumn="id_card";
		}else if (sortColumn.equals("createTime")) {
			sortColumn="create_time";
		}
		String sortType = loadConfig.getSortDir().name();
		if (sortType.equals("NONE")) {
			sortType="desc";
		}
		parameter.put("sortColumn",sortColumn);
		parameter.put("sortType", sortType);
	}
}
