package com.iwgame.baidu.fetcher;


import java.util.List;

import com.baidu.api.core.CommercialClient;
import com.baidu.api.core.JsonEnvelop;
import com.baidu.api.core.ResHeader;
import com.baidu.api.exception.ApiException;
import com.baidu.api.v2.CampaignAdgroupId;
import com.baidu.api.v2.GetAllAdgroupIdRequest;
import com.baidu.api.v2.GetAllAdgroupIdResponse;

public class SyncDemo {
	public static CommercialClient client;
	public static JsonEnvelop<ResHeader, ?> env;

	public static void main(String[] args) {
		try {
			client = new CommercialClient("","","","","","");
		} catch (ApiException e) {
			e.printStackTrace();
		}

		GetAllAdgroupIdRequest req = new GetAllAdgroupIdRequest();
		try {
			env = client.execute(req);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		GetAllAdgroupIdResponse res = (GetAllAdgroupIdResponse) env.getBody();
		List<CampaignAdgroupId> types = res.getCampaignAdgroupIds();
		for (CampaignAdgroupId type : types) {
			System.out.println(type.getCampaignId());
			System.out.println(type.getAdgroupIds());
		}

	}
}
