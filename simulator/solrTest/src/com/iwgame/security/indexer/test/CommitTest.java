package com.iwgame.security.indexer.test;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;

import com.iwgame.security.indexer.bean.Account;
import com.iwgame.security.indexer.utils.SolrUtil;

public class CommitTest {
	private static final String SOLR_SERVER_URL = "http://127.0.0.1:8983/solr/sm-account";

	public static void main(String[] args) {
		Set<Account> accounts = new HashSet<Account>();
		Account account = new Account();
		account.setS_account_id("1");
		account.setS_account_status("1n");
		account.setS_idcard("1m");
		account.setS_idcard_status("1l");
		account.setS_max_level(1);
		account.setS_password4("1k");
		account.setS_real_name("1j");
		account.setS_register_ip("1h");
		account.setS_register_sourceid("1g");
		account.setS_register_sourcetype("1f");
		account.setS_register_sourceurl("1e");
		account.setS_register_time(new Date());
		account.setS_total_paid(1);
		account.setS_user_email("1c");
		account.setS_user_email_new("1b");
		account.setS_user_name("1a");
		accounts.add(account);

		System.out.println("启动");
		long startTime = System.currentTimeMillis();
		HttpSolrServer solrServer = SolrUtil.getHttpSolrServer(SOLR_SERVER_URL);
		try {
			if (solrServer.ping().getStatus() == 0) {
				System.out.println("连接服务器ok.");
				solrServer.addBeans(accounts);
				UpdateResponse response = solrServer.commit();
				if (response.getStatus() == 0) {
					System.out.println("提交" + accounts.size() + "条数据完成,用时:" + response.getQTime() + "毫秒");
				} else {
					System.out.println("索引提交失败");
				}
			} else {
				System.out.println("连接solr服务器失败.");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} finally {
			accounts.clear();
			accounts = null;
			solrServer.shutdown();
		}
		long endTime = System.currentTimeMillis();
		long castTime = endTime - startTime;
		System.out.println("执行" + castTime + "毫秒");
	}

}
