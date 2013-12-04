/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityLockAccountServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.iwgame.gm.p1.security.common.server.dao.IwResultTrackDao;
import com.iwgame.gm.p1.security.common.server.dao.KilledRecordDao;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.server.util.DateHelper;
import com.iwgame.gm.p1.security.common.server.util.GuidHelper;
import com.iwgame.gm.p1.security.common.shared.bean.GlobalResource;
import com.iwgame.gm.p1.security.common.shared.model.IwResultTrack;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.gm.p1.security.common.shared.model.KilledRecord;
import com.iwgame.gm.p1.security.modules.manage.server.dao.SecurityKilledCauseDao;
import com.iwgame.gm.p1.security.modules.operate.server.bean.ERData;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.LockAccountBean;
import com.iwgame.gm.p1.security.modules.operate.shared.rpc.SecurityLockAccountService;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-15 下午03:58:14
 */
public class SecurityLockAccountServiceImpl implements
		SecurityLockAccountService {
	private Logger logger4j = Logger.getLogger(SecurityLockAccountServiceImpl.class);
	
	@Resource(name = "globalResource")
	private GlobalResource globalResource;
	
	@Resource(name="securityKilledCauseDaoImpl")
	private SecurityKilledCauseDao killedCauseDao;
	
	@Resource(name = "killedRecordDaoImpl")
	private KilledRecordDao killedRecordDao;
	
	@Resource(name = "iwResultTrackDaoImpl")
	private IwResultTrackDao iwResultTrackDao;
	
	@Resource(name = "securityLockAccountErData")
	private ERData secrityLockAccountErData;
	
	@Resource(name = "securityRabbitTemplate")
	private RabbitTemplate rabbitTmeplate;
	
	@Override
	public List<KilledCauseConfig> getKilledCausesByCauseType(String productId,
			Integer causeType) throws Exception {
		return killedCauseDao.getKilledCausesByCauseType(productId,causeType);
	}
	
	@Override
	public List<KilledCauseConfig> getKilledCausesByCauseTypeAndKilledType(
			String productId, Integer causeType, Integer killedType)
			throws Exception {
		
		return killedCauseDao.getByCauseTypeAndKilledType(productId, causeType, killedType);
	}

	@Override
	public String insert(LockAccountBean bean, String operator)
			throws Exception {
		String resultStr = "";
		String batchid = "";
		int count = 0;
		String username = bean.getUsername();
		if((ConstantServer.PRODUCT_ID_ZXY).equals(bean.getPid())){
			username = username + "_zxy";
		}
		bean.setUsername(username);
		KilledRecord killedRecord = null;
		boolean isKill = true;
		try {
			killedRecord = killedRecordDao.getRecordByUsername(bean.getPid(),username);
		} catch (Exception e1) {
			logger4j.error("根据账号查询封杀记录错误："+username);
		}
		if(killedRecord!=null){
			if(bean.getOptype()==2){
				if(killedRecord.getType()==1 
						&& !(DateHelper.compareElapsedTime(DateHelper.getCurrentDate(), killedRecord.getOptime(), DateHelper.DIFF_DAY, killedRecord.getDays()))){
					//封杀有效期内
					isKill = false;	
				}else if(killedRecord.getType()==2){
					//冻结中
					isKill = false;
				}
			} else if(bean.getOptype()==1){
				if(killedRecord.getType()==1 
						&& !(DateHelper.compareElapsedTime(DateHelper.getCurrentDate(), killedRecord.getOptime(), DateHelper.DIFF_DAY, killedRecord.getDays()))){
					//封杀有效期内
					isKill = false;
				}
			}
		}
		if(isKill){
			batchid = GuidHelper.getBatchId();
			KilledRecord kr = new KilledRecord();
			kr.setZone(bean.getGuid());
			kr.setUsername(bean.getUsername());
			kr.setType(bean.getOptype());
			kr.setDays(bean.getSealtime());
			kr.setBatchid(batchid);
			kr.setOperator(operator);
			kr.setHandleStatus("1");
			kr.setCauseNote(bean.getCausenote());
			kr.setCauseType(bean.getCausetype());
			kr.setKilledType(bean.getOptype());
			
			try {
				killedRecordDao.insert(bean.getPid(),kr);
				count++;
			} catch (Exception e) {
				logger4j.error("封杀失败:"+e);
				resultStr = "封杀失败";
			}
			if(count>0){
				IwResultTrack iwrt = new IwResultTrack();
				iwrt.setBatchid(batchid);
				iwrt.setTrackType(bean.getOptype());//1:封杀,2:冻结,3:解封,4:安全模式,5:解除安全模式
				iwrt.setSubmitNum(count);
				iwrt.setOperator(operator);
				try {
					iwResultTrackDao.insert(bean.getPid(),iwrt);
				} catch (Exception e) {
					logger4j.error("封杀账号追踪记录:"+e);
					resultStr = "封杀失败";
				}
				
				bean.setSealtime(bean.getSealtime()*24*60);
				bean.setBatchid(batchid);
				rabbitTmeplate.convertAndSend(secrityLockAccountErData.getExchangeName(),
						secrityLockAccountErData.getRoutinKeyName(),
						JSONObject.fromObject(bean).toString());
			}
		}
		resultStr = "成功提交封杀账号"+count+"条";
		if(count!=0){
			resultStr = resultStr+",批次号:"+batchid;
		}
		return resultStr;
	}

	@Override
	public Map<String, Object> getGlobalResource() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("onlineMaxNum", globalResource.getOnlineMaxNum());
		map.put("mailnoticeMaxNum", globalResource.getMailnoticeMaxNum());
		return map;
	}
}
