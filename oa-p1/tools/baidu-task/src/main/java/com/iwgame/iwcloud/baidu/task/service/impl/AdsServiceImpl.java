/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FlumeServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.baidu.task.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.iwgame.iwcloud.baidu.task.model.AdsConfigBean;
import com.iwgame.iwcloud.baidu.task.model.AdsHistoryBean;
import com.iwgame.iwcloud.baidu.task.service.AdsService;
import com.iwgame.iwcloud.baidu.task.service.sm.SmAdsDao;
import com.iwgame.iwcloud.baidu.task.service.sq.SqAdsDao;
import com.iwgame.iwcloud.baidu.task.service.zxy.ZxyAdsDao;
import com.iwgame.pconf.exception.PconfNotFoundException;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xconnector.exeception.DBConnectFailedException;

/**
 * 
 * 类说明
 * 
 * @compend： access DB
 * @author： jjwu
 * @version： 1.0
 * @email： wujunjie@iwgame.com
 * @time：2012-7-3 上午10:20:55
 */
@Service
@SuppressWarnings("rawtypes")
public class AdsServiceImpl implements AdsService {
	private static Logger logger = Logger.getLogger(AdsServiceImpl.class);

	@Resource
	private DBConnection dbConnectorConnection;
	
	@Resource
	private SmAdsDao smAdsDao;
	
	@Resource
	private ZxyAdsDao zxyAdsDao;
	
	@Resource
	private SqAdsDao sqAdsDao;
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<AdsConfigBean> getAdsConfigs() throws PconfNotFoundException, DBConnectFailedException, Exception {

		List<AdsConfigBean> adsConfigs = null;

		try {

			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, "P-P1", "P-P1-BAIDU", null);

			adsConfigs = (List<AdsConfigBean>) sqlSessionTemplate.selectList("ads-sqlmap.getAdsConfigs");

		} catch (PconfNotFoundException e) {
			logger.error("not find Pconf config info get Ads Configs Failed !", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("DB connect Failed!", e);
			throw e;
		} catch (Exception e) {
			logger.error("DB query Ads Account Exception!", e);
			throw e;
		}
		return adsConfigs;
	}

	@Override
	public int sp_store_baidu_ads(Map map, String productid) throws PconfNotFoundException, DBConnectFailedException, Exception {

		int reslut = -1;

		try {

			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productid, productid + "-ADS", null);

			sqlSessionTemplate.insert("ads-sqlmap.insertAdsLogProc", map);

			reslut = (Integer) map.get("result");

		} catch (PconfNotFoundException e) {
			logger.error("not find Pconf config info insertAdsLogProc Failed!", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("DB connect Failed! insertAdsLogProc Failed! ", e);
			throw e;
		} catch (Exception e) {
			logger.info("call Proc Failed！ insertAdsLogProc Failed!", e);
			throw e;
		}
		
		return reslut;
	}
	
	
	@Override
	public int sp_store_baidu_ads_Local(Map map, String productid) throws PconfNotFoundException, DBConnectFailedException, Exception {

		int reslut = -1;
		
		try {
			if("P-P1".equalsIgnoreCase(productid)){
				smAdsDao.sp_store_baidu_ads(map);
				reslut =  (Integer) map.get("result");
			}else if("P-P1.5".equalsIgnoreCase(productid)){
				zxyAdsDao.sp_store_baidu_ads(map);
				reslut =  (Integer) map.get("result");
			}else if("P-WG1".equalsIgnoreCase(productid)){
				sqAdsDao.sp_store_baidu_ads(map);
				reslut =  (Integer) map.get("result");
			}
		} catch (Exception e) {
			logger.info("call Proc Failed！ insertAdsLogProc Failed!", e);
			throw e;
		}
		return reslut;
	}
	
	@Override
	public int sp_store_baidu_keyid_ads_Local(Map map, String productid) throws PconfNotFoundException, DBConnectFailedException, Exception {
		int reslut = -1;
		try {
			if("P-P1".equalsIgnoreCase(productid)){
				smAdsDao.sp_store_baidu_keyid_ads(map);
				reslut =  (Integer) map.get("result");
			}else if("P-P1.5".equalsIgnoreCase(productid)){
				zxyAdsDao.sp_store_baidu_keyid_ads(map);
				reslut =  (Integer) map.get("result");
			}else if("P-WG1".equalsIgnoreCase(productid)){
				sqAdsDao.sp_store_baidu_keyid_ads(map);
				reslut =  (Integer) map.get("result");
			}
		} catch (Exception e) {
			logger.info("call Proc Failed！ insertAdsLogProc Failed!", e);
			throw e;
		}
		return reslut;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.iwcloud.baidu.task.service.AdsService#sp_store_baidu_keyid_ads
	 * (java.util.Map, java.lang.String)
	 */
	@Override
	public int sp_store_baidu_keyid_ads(Map map, String productid) throws PconfNotFoundException, DBConnectFailedException, Exception {
		int reslut = -1;
		try {
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productid, productid + "-ADS", null);

			sqlSessionTemplate.insert("ads-sqlmap.sp_store_baidu_keyid_ads", map);

			reslut = (Integer) map.get("result");

		} catch (PconfNotFoundException e) {
			logger.error("not find Pconf config info sp_store_baidu_keyid_ads Failed!", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("DB connect Failed! sp_store_baidu_keyid_ads Failed! ", e);
			throw e;
		} catch (Exception e) {
			logger.info("call Proc Failed！ sp_store_baidu_keyid_ads Failed!", e);
			throw e;
		}
		return reslut;
	}
	
	
	@Override
	public void insertAdshistory(AdsHistoryBean adsHistoryBean, String productid) throws PconfNotFoundException, DBConnectFailedException, Exception {
		int reslut = 0;
		try {
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, "P-P1", "P-P1-BAIDU", null);
			reslut = sqlSessionTemplate.insert("ads-sqlmap.insertAdshistory", adsHistoryBean);
			if (reslut > 0) {
				logger.info("Save AdsLog history Success!");
			}
		} catch (PconfNotFoundException e) {
			logger.error("not find Pconf config info save history failed!", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("DB connect Failed save history failed!", e);
			throw e;
		} catch (Exception e) {
			logger.error("save history Failed Save history failed!", e);
			throw e;
		}
	}
}