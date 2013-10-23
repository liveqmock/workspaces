package com.iwgame.baidu.fetcher;


import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.baidu.api.core.Callback;
import com.baidu.api.core.CommercialClient;
import com.baidu.api.core.JsonEnvelop;
import com.baidu.api.core.ResHeader;
import com.baidu.api.exception.ApiException;
import com.baidu.api.v2.CampaignAdgroupId;
import com.baidu.api.v2.GetAllAdgroupIdRequest;
import com.baidu.api.v2.GetAllAdgroupIdResponse;

public class AsyncDemo {
	public static CommercialClient client;
	public static JsonEnvelop<ResHeader, ?> env;

	public static void main(String[] args) {
		try {
			client = new CommercialClient("username","password","token","AdgroupService", "getAllAdgroupId","");
		} catch (ApiException e) {
			e.printStackTrace();
		}

		GetAllAdgroupIdRequest req = new GetAllAdgroupIdRequest();
		final CountDownLatch latch = new CountDownLatch(1);
		Callback<JsonEnvelop<ResHeader, ?>> callback = new Callback<JsonEnvelop<ResHeader, ?>>() {

			@Override
			public void execResult(JsonEnvelop<ResHeader, ?> result) {
				ResHeader header = result.getHeader();
				GetAllAdgroupIdResponse res = (GetAllAdgroupIdResponse) result
						.getBody();
				System.out.println(header);
				List<CampaignAdgroupId> types = res.getCampaignAdgroupIds();
				for (CampaignAdgroupId type : types) {
					System.out.println(type.getCampaignId());
					System.out.println(type.getAdgroupIds());
				}

				latch.countDown();
			}

			@Override
			public void execError(Throwable error) {

			}

		};
		System.out.println("---------------------start execute!");
		try {
			client.execute(req, callback);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("---------------------end execute!");
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
