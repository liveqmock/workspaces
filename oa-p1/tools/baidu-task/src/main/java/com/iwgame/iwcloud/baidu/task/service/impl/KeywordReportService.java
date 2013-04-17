/**
 * ReportServiceExamples.java
 *
 * Copyright 2010 Baidu.com, Inc.
 *
 * Baidu.com licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.iwgame.iwcloud.baidu.task.service.impl;

import java.net.ConnectException;

import org.apache.log4j.Logger;

import com.baidu.api.sem.common.v2.ResHeader;
import com.baidu.api.sem.sms.v2.GetProfessionalReportIdRequest;
import com.baidu.api.sem.sms.v2.GetProfessionalReportIdResponse;
import com.baidu.api.sem.sms.v2.ReportRequestType;
import com.baidu.api.sem.sms.v2.ReportService;
import com.iwgame.iwcloud.baidu.task.core.ClientBusinessException;
import com.iwgame.iwcloud.baidu.task.core.ObjToStringUtil;
import com.iwgame.iwcloud.baidu.task.core.ResHeaderUtil;
import com.iwgame.iwcloud.baidu.task.core.ServiceFactory;
import com.iwgame.iwcloud.baidu.task.core.VersionService;
import com.iwgame.iwcloud.baidu.task.model.AdsConfigBean;
import com.iwgame.iwcloud.baidu.task.model.FetcherConfig;

/**
 * 
 * 类说明
 * 
 * @compend： webservice
 * @author： jjwu
 * @version： 1.0
 * @email： wujunjie@iwgame.com
 * @time：2012-6-29 下午12:36:35
 */
public class KeywordReportService {

	protected final Logger logger = Logger.getLogger(VersionService.class);

	public ReportService service;

	public KeywordReportService(AdsConfigBean adsConfig,FetcherConfig fetcherConfig) throws ConnectException {
		// 得到服务工厂。你的身份验证信息将从数据库获取
		VersionService factory = ServiceFactory.getInstance(adsConfig ,fetcherConfig);
		// 从服务接口得到服务实例.
		this.service = factory.getService(ReportService.class,3);
	}
	
	public GetProfessionalReportIdResponse getProfessionalReportId(FetcherConfig fetcherDateTimeConfig) {
		GetProfessionalReportIdRequest parameter = new GetProfessionalReportIdRequest();
		
		// 设置报表参数
		ReportRequestType request = new ReportRequestType();
		
		// 报表类型
		request.setReportType(9);
		
		// 开始日期
		request.setStartDate(fetcherDateTimeConfig.getFetcherBeginTime());
		
		// 结束日期
		request.setEndDate(fetcherDateTimeConfig.getFetcherEndTime());
		
		// (关键词keywordid粒度)
		request.setLevelOfDetails(6);
		
		// statRange 2, 3, 5, 6(账户范围，计划范围，单元范围，关键词wordid范围)
		request.setStatRange(6);
		
		// 1,3,4,5,8 (年报，月报，周报，日报，请求时间段汇总)
		request.setUnitOfTime(5);
		
		// 1,0 (关键词报告不支持platform=网盟推广)
		request.setPlatform(1);
		request.getPerformanceData().add("cost");
		request.getPerformanceData().add("cpc");
		request.getPerformanceData().add("click");
		request.getPerformanceData().add("impression");
		request.getPerformanceData().add("ctr");
		request.getPerformanceData().add("cpm");
		request.getPerformanceData().add("position");
		// dOnly不是必需的,默认值是false,这意味着你可以得到文字信息的材料在返回的结果.
		// 当文字信息不是对你有益,这将为您提供更高的性能
		request.setIdOnly(false);
		parameter.setReportRequestType(request);
		// 调用该方法.
		GetProfessionalReportIdResponse ret = service.getProfessionalReportId(parameter);
		// 处理响应标头,第二个参数控制是否打印到控制台或不响应头
		ResHeader rheader = ResHeaderUtil.getResHeader(service, false);
		// 如果状态等于零,没有错误。否则,您需要检查错误响应头中。
		if (rheader.getStatus() == 0) {
			logger.info("获得当前报表的ReportId ：\n" + ObjToStringUtil.objToString(ret));
			return ret;
		} else {
			logger.error("得到 getProfessionalReportId 错误，请检查百度帐号和密码是否正确！状态码返回错误，当前状态码：" + rheader.getStatus());
			throw new ClientBusinessException(rheader, ret);
		}
	}
	
	
	public GetProfessionalReportIdResponse getProfessionalReportIdByKeyID(FetcherConfig fetcherDateTimeConfig) {
		GetProfessionalReportIdRequest parameter = new GetProfessionalReportIdRequest();
		
		// 设置报表参数
		ReportRequestType request = new ReportRequestType();
		
		// 报表类型
		request.setReportType(14);

		// 开始日期
		request.setStartDate(fetcherDateTimeConfig.getFetcherBeginTime());

		// 结束日期
		request.setEndDate(fetcherDateTimeConfig.getFetcherEndTime());

		// (关键词keywordid粒度)
		request.setLevelOfDetails(11);

		// 2, 3, 5,11(账户范围，计划范围，单元范围，关键词keywordid范围)
		request.setStatRange(11);

		// 1,3,4,5,8 (年报，月报，周报，日报，请求时间段汇总)
		request.setUnitOfTime(5);

		// 1,0 (关键词报告不支持platform=网盟推广)
		request.setPlatform(1);
		
		request.getPerformanceData().add("cost");
		request.getPerformanceData().add("cpc");
		request.getPerformanceData().add("click");
		request.getPerformanceData().add("impression");
		request.getPerformanceData().add("ctr");
		request.getPerformanceData().add("cpm");
		request.getPerformanceData().add("position");
		// dOnly不是必需的,默认值是false,这意味着你可以得到文字信息的材料在返回的结果.
		// 当文字信息不是对你有益,这将为您提供更高的性能
		request.setIdOnly(false);
		parameter.setReportRequestType(request);
		// 调用该方法.
		GetProfessionalReportIdResponse ret = service.getProfessionalReportId(parameter);
		// 处理响应标头,第二个参数控制是否打印到控制台或不响应头
		ResHeader rheader = ResHeaderUtil.getResHeader(service, false);
		// 如果状态等于零,没有错误。否则,您需要检查错误响应头中。
		if (rheader.getStatus() == 0) {
			logger.info("KeyId获得当前报表的ReportId ：\n" + ObjToStringUtil.objToString(ret));
			return ret;
		} else {
			logger.error("KeyId得到 getProfessionalReportId 错误，请检查百度帐号和密码是否正确！状态码返回错误，当前状态码：" + rheader.getStatus());
			throw new ClientBusinessException(rheader, ret);
		}
	}
}
