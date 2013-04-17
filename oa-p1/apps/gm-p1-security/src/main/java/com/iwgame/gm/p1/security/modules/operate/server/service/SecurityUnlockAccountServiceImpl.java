/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityUnlockAccountServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.server.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import net.sf.json.JSONObject;

import com.iwgame.gm.p1.security.common.server.dao.IwResultTrackDao;
import com.iwgame.gm.p1.security.common.server.dao.KilledRecordDao;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.server.util.GuidHelper;
import com.iwgame.gm.p1.security.common.shared.model.IwResultTrack;
import com.iwgame.gm.p1.security.common.shared.model.KilledRecord;
import com.iwgame.gm.p1.security.modules.operate.server.bean.ERData;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.LockAccountBean;
import com.iwgame.gm.p1.security.modules.operate.shared.rpc.SecurityUnlockAccountService;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-15 下午04:30:36
 */
public class SecurityUnlockAccountServiceImpl implements
		SecurityUnlockAccountService {
	private Logger logger4j = Logger.getLogger(SecurityUnlockAccountServiceImpl.class);
	
	@Resource(name = "killedRecordDaoImpl")
	private KilledRecordDao killedRecordDao;
	
	@Resource(name = "iwResultTrackDaoImpl")
	private IwResultTrackDao iwResultTrackDao;
	
	@Resource(name = "securityUnlockAccountErData")
	private ERData secrityUnlockAccountErData;
	
	@Resource(name = "securityRabbitTemplate")
	private RabbitTemplate rabbitTmeplate;
	
	@Override
	public String insert(LockAccountBean bean, String operator)
			throws Exception {
		String resultStr = "";
		int count = 0;
		String batchid = GuidHelper.getBatchId();
		String username = bean.getUsername();
		if((ConstantServer.PRODUCT_ID_ZXY).equals(bean.getPid())){
			username = username + "_zxy";
		}
		bean.setUsername(username);
		KilledRecord kr = new KilledRecord();
		kr.setZone(bean.getGuid());
		kr.setUsername(bean.getUsername());
		kr.setType(3);
		kr.setDays(0);
		kr.setBatchid(batchid);
		kr.setOperator(operator);
		kr.setHandleStatus("1");
		kr.setCauseNote(bean.getCausenote());
		kr.setCauseType(4);
		kr.setKilledType(3);
		
		try {
			killedRecordDao.insert(bean.getPid(),kr);
			count++;
		} catch (Exception e) {
			logger4j.error("解封账号:"+e);
			resultStr = "封杀失败";
		}
		if(count>0){
			IwResultTrack iwrt = new IwResultTrack();
			iwrt.setBatchid(batchid);
			iwrt.setTrackType(3);//1:封杀,2:冻结,3:解封,4:安全模式,5:解除安全模式
			iwrt.setSubmitNum(count);
			iwrt.setOperator(operator);
			try {
				iwResultTrackDao.insert(bean.getPid(),iwrt);
			} catch (Exception e) {
				logger4j.error("解封账号追踪记录:"+e);
				resultStr = "解封失败";
			}
			
			bean.setSealtime(0);
			bean.setShowtime(0);
			bean.setOnline(0);
			bean.setMailnotice(0);
			bean.setOptype(3);
			bean.setCausetype(4);
			bean.setBatchid(batchid);
			rabbitTmeplate.convertAndSend(secrityUnlockAccountErData.getExchangeName(),
					secrityUnlockAccountErData.getRoutinKeyName(),
					JSONObject.fromObject(bean).toString());
		}
		resultStr = "成功提交解封账号"+count+"条";
		if(count!=0){
			resultStr = resultStr+",批次号:"+batchid;
		}
		
		return resultStr;
	}

}
