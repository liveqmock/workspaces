/****************************************************************
 *  文件名     ： BaiduAuto.java
 *  日期         :  2012-10-29
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.baidu.task.process;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.iwgame.iwcloud.baidu.task.model.AdsConfigBean;
import com.iwgame.iwcloud.baidu.task.model.AdsLogBean;
import com.iwgame.iwcloud.baidu.task.model.FetcherConfig;
import com.iwgame.iwcloud.baidu.task.service.AdsService;
import com.iwgame.iwcloud.baidu.task.util.DateTimeUtil;

/** 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-29上午10:59:11
 * @版本:   v1.0 
 */
public abstract class BaiduAuto {
	
	private final Logger logger = Logger.getLogger(BaiduAuto.class);
	
	@Resource
	protected FetcherConfig fetcherConfig;
	
	@Resource
	protected AdsService adsService;
	
	/**
	 * generate backup path
	 * 
	 * @param adsConfig
	 * @return
	 */
	protected String generateBackupPath(AdsConfigBean adsConfig) {
		StringBuilder savePath = new StringBuilder();
		savePath.append(fetcherConfig.getSaveFilePath());
		savePath.append("/");
		savePath.append(adsConfig.getProductid().trim());
		savePath.append("/");
		savePath.append(adsConfig.getUsername().trim());
		savePath.append("/");
		savePath.append(DateTimeUtil.formatDate2DateStr(fetcherConfig.getFetcherBeginTime()) + "--" + DateTimeUtil.formatDate2DateStr(fetcherConfig.getFetcherEndTime()));
		savePath.append(".txt");
		return savePath.toString();
	}

	/**
	 * Resolvin content by AdsLogBean
	 * 
	 * @param content
	 * @return
	 */
	protected List<AdsLogBean> contentResolving(String content) {

		List<AdsLogBean> asdBeans = new ArrayList<AdsLogBean>();

		if (null != content && !"".equals(content)) {
			String[] rowstr = content.split("\n");
			if (rowstr != null) {
				for (int j = 1; j < rowstr.length; j++) {
					AdsLogBean adslog = new AdsLogBean();
					String[] colums = rowstr[j].split("\t");
					if (colums.length == 17) {
						adslog.setDatetime(colums[0] == null ? "" : colums[0]);
						adslog.setAccountid(colums[1] == null ? "" : colums[1]);
						adslog.setAccount(colums[2] == null ? "" : colums[2]);
						adslog.setProjectid(colums[3] == null ? "" : colums[3]);
						adslog.setProject(colums[4] == null ? "" : colums[4]);
						adslog.setUnitid(colums[5] == null ? "" : colums[5]);
						adslog.setUnit(colums[6] == null ? "" : colums[6]);
						adslog.setKeywordid(colums[7] == null ? "" : colums[7]);
						adslog.setKeyword(colums[8] == null ? "" : colums[8]);
						adslog.setMethod(colums[9] == null ? "" : colums[9]);
						adslog.setConsume(colums[10] == null ? "" : colums[10]);
						adslog.setAvgclickprice(colums[11] == null ? "" : colums[11]);
						adslog.setClicknum(colums[12] == null ? "" : colums[12]);
						adslog.setShownum(colums[13] == null ? "" : colums[13]);
						adslog.setClickrate(colums[14] == null ? "" : colums[14]);
						adslog.setQczxconsume(colums[15] == null ? "" : colums[15]);
						adslog.setAvgrank(colums[16] == null ? "" : colums[16]);
						asdBeans.add(adslog);
					} else {
						logger.error("文本一行没有17个参数，请检查！" + colums);
					}
				}
			}
		}
		logger.info("获得广告数据记录:" + asdBeans.size());
		return asdBeans;
	}
}
