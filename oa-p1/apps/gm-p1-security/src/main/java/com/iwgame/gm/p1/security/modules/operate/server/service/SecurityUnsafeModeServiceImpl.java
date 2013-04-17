/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityUnsafeModeServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.server.service;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.iwgame.gm.p1.security.common.server.dao.IwResultTrackDao;
import com.iwgame.gm.p1.security.common.server.dao.SafeModeRecordDao;
import com.iwgame.gm.p1.security.common.server.service.SolrHttpService;
import com.iwgame.gm.p1.security.common.server.util.GuidHelper;
import com.iwgame.gm.p1.security.common.shared.bean.RoleDocs;
import com.iwgame.gm.p1.security.common.shared.bean.RoleParam;
import com.iwgame.gm.p1.security.common.shared.model.IwResultTrack;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeRecord;
import com.iwgame.gm.p1.security.common.shared.util.RegexGwtHelper;
import com.iwgame.gm.p1.security.modules.operate.server.bean.ERData;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.SafeModeBean;
import com.iwgame.gm.p1.security.modules.operate.shared.rpc.SecurityUnsafeModeService;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午11:14:25
 */
public class SecurityUnsafeModeServiceImpl implements SecurityUnsafeModeService {
	private Logger logger4j = Logger.getLogger(SecurityUnsafeModeServiceImpl.class);
	
	@Resource(name = "iwResultTrackDaoImpl")
	private IwResultTrackDao iwResultTrackDao;
	
	@Resource(name = "safeModeRecordDaoImpl")
	private SafeModeRecordDao safeModeRecordDao;
	
	@Resource(name = "securityUnsafeModeErData")
	private ERData securityUnsafeModeErData;
	
	@Resource(name = "securityRabbitTemplate")
	private RabbitTemplate rabbitTmeplate;
	
	@Resource(name = "solrHttpServiceImpl")
	private SolrHttpService solrHttpService;
	
	@Override
	public String insert(SafeModeBean bean, String operator) throws Exception {
		String resultStr = "";
		int count = 0;
		int delcount = 0;
		//批次号
		String batchid = "";
		if(bean.getDbid().matches(RegexGwtHelper.STR_NUM_)){
			batchid = GuidHelper.getBatchId();
			RoleParam roleParam = new RoleParam();
			roleParam.setProductId(bean.getPid());
			roleParam.setDbid(bean.getDbid());
			RoleDocs docs = solrHttpService.getRoleName(roleParam);
			String username = "";
			if(docs!=null){
				username = solrHttpService.getAccountNameByAccountId(bean.getPid(),docs.getAccountid());
				SafeModeRecord smr = new SafeModeRecord();
				smr.setId(0);
				smr.setRolename(docs.getName());
				smr.setDbid(bean.getDbid());
				smr.setOperator(operator);
				smr.setModeType(2);
				smr.setCauseNote(bean.getCausenote());
				smr.setGroupname(docs.getSvr());
				smr.setGuid(docs.getGuid());
				smr.setUsername(username);
				try {
					safeModeRecordDao.insert(bean.getPid(),smr);
					count++;
				} catch (Exception e) {
					logger4j.error("解除安全模式:"+e);
					delcount++;
				}
			}else{
				logger4j.error("获取角色信息返回空,DBID:"+bean.getDbid());
				delcount++;
			}
			if(count>0){
				IwResultTrack iwrt = new IwResultTrack();
				iwrt.setBatchid(batchid);
				iwrt.setTrackType(5);//1:封杀,2:冻结,3:解封,4:安全模式,5:解除安全模式
				iwrt.setSubmitNum(count);
				iwrt.setOperator(operator);
				try {
					iwResultTrackDao.insert(bean.getPid(),iwrt);
				} catch (Exception e) {
					logger4j.error("解除安全模式追踪记录:"+e);
				}
				bean.setAccountid(docs.getAccountid());
				bean.setGroupname(docs.getSvr());
				bean.setRolename(docs.getName());
				bean.setGuid(docs.getGuid());
				bean.setBatchid(batchid);
				bean.setUsername(username);
				rabbitTmeplate.convertAndSend(securityUnsafeModeErData.getExchangeName(),
						securityUnsafeModeErData.getRoutinKeyName(),
						JSONObject.fromObject(bean).toString());
			}
		}else{
			logger4j.info("dbid校验不合法,DBID:"+bean.getDbid());
		}
		resultStr = "成功提交解除安全模式账号"+count+"条";
		if(count!=0){
			resultStr = resultStr+",批次号:"+batchid;
		}
		return resultStr;
	}

}
