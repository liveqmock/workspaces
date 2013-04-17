/****************************************************************
 *  系统名称  ： 'Flume-task System'
 *  文件名    ： UpdateFlumeStatusJob.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.baidu.task.process;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baidu.api.sem.sms.v2.GetProfessionalReportIdResponse;
import com.baidu.api.sem.sms.v2.GetReportFileUrlResponse;
import com.iwgame.iwcloud.baidu.task.model.AdsConfigBean;
import com.iwgame.iwcloud.baidu.task.model.AdsHistoryBean;
import com.iwgame.iwcloud.baidu.task.model.AdsLogBean;
import com.iwgame.iwcloud.baidu.task.service.impl.KeywordReportService;
import com.iwgame.iwcloud.baidu.task.util.DateTimeUtil;
import com.iwgame.iwcloud.baidu.task.util.ReportUtil;

/**
 * 
 * @描述:  	百度数据抓取定时任务
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-29上午11:40:39
 * @版本:   v1.0
 */
public class BaiduAutoFetcherJobKeyId extends BaiduAuto{

	private final Logger logger = Logger.getLogger(BaiduAutoFetcherJobKeyId.class);

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public void work() {

		if (null == fetcherConfig.getFetcherBegin() || null == fetcherConfig.getFetcherEnd() || "".equals(fetcherConfig.getFetcherBegin())
				|| "".equals(fetcherConfig.getFetcherEnd())) {
			Date yesterday = DateTimeUtil.getYesterday();
			Calendar calendar = Calendar.getInstance();
			calendar.set(1900 + yesterday.getYear(), yesterday.getMonth(), yesterday.getDate(), 23, 59, 59);
			fetcherConfig.setFetcherBeginTime(yesterday);
			fetcherConfig.setFetcherEndTime(calendar.getTime());
		}

		List<AdsHistoryBean> adsHistoryBeans = new ArrayList<AdsHistoryBean>();

		try {
			logger.info("[ KeyId 百度数据抓取任务] 开始。。。");
			logger.info(" KeyId 当前任务抓取时间范围：[ " + DateTimeUtil.formatDate3DateTimeStr(fetcherConfig.getFetcherBeginTime()) + " ]---[ "
					+ DateTimeUtil.formatDate3DateTimeStr(fetcherConfig.getFetcherEndTime()) + " ]");
			List<AdsConfigBean> adsconfigs = adsService.getAdsConfigs();
			if (null != adsconfigs && adsconfigs.size() > 0) {
				for (int i = 0; i < adsconfigs.size(); i++) {
					// 状态
					int status = 0;
					// 成功记录数
					int successcount = 0;
					// 获取记录数
					int fetchecount = 0;
					// 备注信息
					String info = "";
					AdsHistoryBean adsHistoryBean = new AdsHistoryBean();
					AdsConfigBean adsConfig = null;
					try {

						adsConfig = adsconfigs.get(i);
						adsHistoryBean.setUsername(adsConfig.getUsername());
						adsHistoryBean.setProductid(adsConfig.getProductid());
						adsHistoryBean.setBegintime(DateTimeUtil.formatDate3DateTimeStr(fetcherConfig.getFetcherBeginTime()));
						adsHistoryBean.setEndtime(DateTimeUtil.formatDate3DateTimeStr(fetcherConfig.getFetcherEndTime()));
						String savePath = generateBackupPath(adsConfig);

						KeywordReportService service = null;
						GetProfessionalReportIdResponse reprotIdResponse  = null;
						
						//webservice连接超时,重试3次
						int trycount = 3;
						
						while(trycount-- > 0){
							try {
								service = new KeywordReportService(adsConfig, fetcherConfig);
								reprotIdResponse = service.getProfessionalReportIdByKeyID(fetcherConfig);
								break;
							} catch (Exception e) {
								logger.error("KeyId 获取ReportId失败,webservice连接超时,我们将重试一次,请稍候...  \t 异常:",e);
								Thread.sleep(10000);
							}
						}
						

						if (reprotIdResponse != null) {
							GetReportFileUrlResponse fileUrlResponse = null;
							
							//webservice连接超时,重试3次
							int trynum = 3;
							while(trynum-- >0){
								try {
									fileUrlResponse = ReportUtil.getReportFileUrl(service.service, reprotIdResponse.getReportId(), 20);
									break;
								} catch (Exception e) {
									//尝试一次停止30秒
									logger.error("获取fileUrlResponse失败,webservice连接超时,我们将重试一次,请稍候...  \t 异常:",e);
								}
							}
							

							if (null != fileUrlResponse) {
								// 获取文本并保存文本文件
								String content = ReportUtil.getFileContent(fileUrlResponse.getReportFilePath(), savePath);
								if (content != null && !"".equals(content)) {
									List<AdsLogBean> list = contentResolving(content);
									fetchecount = list.size();
									for (int j = 0; j < list.size(); j++) {
										Map map = null;
										int reslut = -1;
										AdsLogBean adslog = null;
										try {
											adslog = list.get(j);
											// 设置参数
											map = new HashMap();
											map.put("datetime", adslog.getDatetime());
											map.put("accountid", adslog.getAccountid());
											map.put("account", adslog.getAccount());
											map.put("projectid", adslog.getProjectid());
											map.put("project", adslog.getProject());
											map.put("unitid", adslog.getUnitid());
											map.put("unit", adslog.getUnit());
											map.put("keywordid", adslog.getKeywordid());
											map.put("keyword", adslog.getKeyword());
											map.put("method", adslog.getMethod());
											map.put("consume", adslog.getConsume().equals("-") ? "0" : adslog.getConsume());
											map.put("avgclickprice", adslog.getAvgclickprice().equals("-") ? "0" : adslog.getAvgclickprice());
											map.put("clicknum", adslog.getClicknum().equals("-") ? "0" : adslog.getClicknum());
											map.put("shownum", adslog.getShownum().equals("-") ? "0" : adslog.getShownum());
											map.put("clickrate", adslog.getClickrate().equals("-") ? "0%" : adslog.getClickrate());
											map.put("qczxconsume", adslog.getQczxconsume().equals("-") ? "0" : adslog.getQczxconsume());
											map.put("avgrank", adslog.getAvgrank().equals("-") ? "0" : adslog.getAvgrank());
											// 调存储过程
											reslut = adsService.sp_store_baidu_keyid_ads(map, adsConfig.getProductid());
											if (reslut == 0) {
												info = "[SYNC SUCCESS]" + adsConfig.getProductid() + "->" + adslog.getAccount();
												successcount++;
											}
										} catch (Exception e) {
											logger.error("调用存储过程异常！", e);
											info = "[SYNC FAILED]" + adsConfig.getProductid() + "->" + adslog.getAccount() + ",ResultCode:" + reslut;
											logger.error(info);
											throw e;
										}
									}
								}
							}else{
								status = -1;
								info += " KeyId 获取广告失败,当前帐号: [ " + adsConfig.getUsername() + " ]!  百度帐号是否正确?,百度的webservice 是否连接超时?";
								logger.error(info);
							}
						} else {
							status = -1;
							info = " KeyId reprotIdResponse 对象为空，请检测百度帐号配置！";
							logger.error(info);
						}
					} catch (Exception e) {
						status = -1;
						info += "KeyId 获取广告失败,当前帐号: [ " + adsConfig.getUsername() + " ]!  百度帐号是否正确?,百度的webservice 是否连接超时?";
						logger.error("KeyId Ignore Fetcher for Account : [ " + adsConfig.getUsername() + " ] !");
						logger.error(info, e);
					}
					adsHistoryBean.setStatus(status);
					adsHistoryBean.setFetchecount(fetchecount);
					adsHistoryBean.setSavecount(successcount);
					adsHistoryBean.setInfo("KeyID:\t"+info);
					adsHistoryBeans.add(adsHistoryBean);
					// 存入历史记录
					adsService.insertAdshistory(adsHistoryBean, null);
					logger.info("-----------End fetcher KeyId [Account:" + adsconfigs.get(i).getUsername() + "]-------------\n\n\n");
				}
			} else {
				logger.info("Get Baidu-ADS Account for Zero!");
			}
		} catch (Exception e) {
			logger.error("获取百度帐号配置失败!", e);
		}
		logger.info("KeyId详细信息：");
		logger.info("-------------------------------------");
		for (int j = 0; j < adsHistoryBeans.size(); j++) {
			AdsHistoryBean bean = adsHistoryBeans.get(j);
			String success = bean.getStatus() == 0 ? "成功" : "失败";
			logger.info("KeyId帐号: [" + bean.getUsername() + "]  抓取：[" + bean.getFetchecount() + "] 保存：[" + bean.getSavecount() + "] 是否成功?：[" + success + "]");
		}
		logger.info("--------------------------------------");
		logger.info("[KeyId百度数据抓取任务] 结束。。。\n\n\n\n\n");

	}
}
