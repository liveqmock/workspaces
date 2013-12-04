/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FlumeService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.baidu.task.service;

import java.util.List;
import java.util.Map;

import com.iwgame.iwcloud.baidu.task.model.AdsConfigBean;
import com.iwgame.iwcloud.baidu.task.model.AdsHistoryBean;
import com.iwgame.pconf.exception.PconfNotFoundException;
import com.iwgame.xconnector.exeception.DBConnectFailedException;

/**
 * 
 * 类说明
 * 
 * @compend： interface
 * @author： jjwu
 * @version： 1.0
 * @email： wujunjie@iwgame.com
 * @time：2012-7-3 上午10:22:13
 */
public interface AdsService {
	/**
	 * get ads Account config
	 * @return
	 * @throws PconfNotFoundException
	 * @throws DBConnectFailedException
	 * @throws Exception
	 */
	public List<AdsConfigBean> getAdsConfigs() throws PconfNotFoundException,DBConnectFailedException,Exception ;
	
	/**
	 * save Adslog 
	 * @param map
	 * @param productid
	 * @return
	 * @throws PconfNotFoundException
	 * @throws DBConnectFailedException
	 * @throws Exception
	 */
	public int sp_store_baidu_ads(@SuppressWarnings("rawtypes") Map map,String productid) throws PconfNotFoundException,DBConnectFailedException,Exception;

	/**
	 * save Adslog 
	 * @param map
	 * @param productid
	 * @return
	 * @throws PconfNotFoundException
	 * @throws DBConnectFailedException
	 * @throws Exception
	 */
	public int sp_store_baidu_ads_Local(@SuppressWarnings("rawtypes") Map map,String productid) throws PconfNotFoundException,DBConnectFailedException,Exception;
	
	/**
	 * save Adslog 
	 * @param map
	 * @param productid
	 * @return
	 * @throws PconfNotFoundException
	 * @throws DBConnectFailedException
	 * @throws Exception
	 */
	public int sp_store_baidu_keyid_ads_Local(@SuppressWarnings("rawtypes") Map map,String productid) throws PconfNotFoundException,DBConnectFailedException,Exception;
	
	/**
	 * 
	 * @param map
	 * @param productid
	 * @return
	 * @throws PconfNotFoundException
	 * @throws DBConnectFailedException
	 * @throws Exception
	 */
	public int sp_store_baidu_keyid_ads(@SuppressWarnings("rawtypes") Map map,String productid) throws PconfNotFoundException,DBConnectFailedException,Exception;
	
	/**
	 * save history
	 * @param adsHistoryBean
	 * @param productid
	 * @throws PconfNotFoundException
	 * @throws DBConnectFailedException
	 * @throws Exception
	 */
	public void insertAdshistory(AdsHistoryBean adsHistoryBean,String productid) throws PconfNotFoundException,DBConnectFailedException,Exception;
	
}
