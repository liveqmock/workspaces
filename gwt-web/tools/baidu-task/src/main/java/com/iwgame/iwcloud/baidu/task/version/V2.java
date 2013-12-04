package com.iwgame.iwcloud.baidu.task.version;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import com.baidu.api.sem.sms.v2.AccountService;
import com.baidu.api.sem.sms.v2.AdgroupService;
import com.baidu.api.sem.sms.v2.CampaignService;
import com.baidu.api.sem.sms.v2.CreativeService;
import com.baidu.api.sem.sms.v2.FolderService;
import com.baidu.api.sem.sms.v2.KRService;
import com.baidu.api.sem.sms.v2.KeywordService;
import com.baidu.api.sem.sms.v2.ReportService;

/**
 * 
 * 类说明
 * 
 * @简述： 当前版本的配置类,用于配置QName和服务地址
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-6-27 下午05:52:48
 */
public class V2 extends Version {
	private static final String NAMESPACE_COMMON = "http://api.baidu.com/sem/common/v2";
	private static final String NAME_SPACE = "https://api.baidu.com/sem/sms/v2";
	private static Map<String, String> urls = new HashMap<String, String>();
	private static Map<String, QName> qNames = new HashMap<String, QName>();
	
	public static final V2 v = new V2();

	static {
	    //Account
		urls.put(AccountService.class.getName(), "/sem/sms/v2/AccountService?wsdl");
		qNames.put(AccountService.class.getName(), new QName(NAME_SPACE, "AccountService"));
		//Campaign
		urls.put(CampaignService.class.getName(), "/sem/sms/v2/CampaignService?wsdl");
		qNames.put(CampaignService.class.getName(), new QName(NAME_SPACE, "CampaignService"));
		//Adgroup
		urls.put(AdgroupService.class.getName(), "/sem/sms/v2/AdgroupService?wsdl");
		qNames.put(AdgroupService.class.getName(), new QName(NAME_SPACE, "AdgroupService"));
		//Creative
		urls.put(CreativeService.class.getName(), "/sem/sms/v2/CreativeService?wsdl");
		qNames.put(CreativeService.class.getName(), new QName(NAME_SPACE, "CreativeService"));
		//Keyword
		urls.put(KeywordService.class.getName(), "/sem/sms/v2/KeywordService?wsdl");
		qNames.put(KeywordService.class.getName(), new QName(NAME_SPACE, "KeywordService"));
		//Report
		urls.put(ReportService.class.getName(), "/sem/sms/v2/ReportService?wsdl");
		qNames.put(ReportService.class.getName(), new QName(NAME_SPACE, "ReportService"));
		//Folder
		urls.put(FolderService.class.getName(), "/sem/sms/v2/FolderService?wsdl");
		qNames.put(FolderService.class.getName(), new QName(NAME_SPACE, "FolderService"));
		//KR
		urls.put(KRService.class.getName(), "/sem/sms/v2/KRService?wsdl");
		qNames.put(KRService.class.getName(), new QName(NAME_SPACE, "KRService"));
	}
	
	private V2() {}

	@Override
	public String getVersion() {
		return "V2";
	}

	@Override
	public <T> QName getQName(Class<T> cls) {
		return qNames.get(cls.getName());
	}

	@Override
	public <T> String getAddr(Class<T> cls) {
		return urls.get(cls.getName());
	}

	@Override
	public String getHeaderNameSpace() {
		return NAMESPACE_COMMON;
	}

}
