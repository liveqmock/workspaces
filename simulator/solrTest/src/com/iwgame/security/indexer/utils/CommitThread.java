package com.iwgame.security.indexer.utils;

import java.io.IOException;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;

import com.iwgame.security.indexer.bean.DataModel;

public class CommitThread extends Thread{

	private Set<DataModel> accounts;
	private final String solrUrl;

	public CommitThread(Set<DataModel> accounts, String solrUrl)
	{
		this.accounts = accounts;
		this.solrUrl = solrUrl;
	}
	
	@Override
	public void run() {
		System.out.println(this.getName()+"启动");
		long startTime = System.currentTimeMillis();
		HttpSolrServer solrServer = SolrUtil.getHttpSolrServer(solrUrl);
		try {
			if (solrServer.ping().getStatus()==0) {
				System.out.println(this.getName()+"连接服务器ok.");
				solrServer.addBeans(accounts);
				UpdateResponse response = solrServer.commit();
				if (response.getStatus()==0) {
					System.out.println(this.getName()+"提交"+accounts.size()+"条数据完成,用时:"+response.getQTime()+"毫秒");
				}else {
					System.out.println(this.getName()+"索引提交失败");
				}
			}else {
				System.out.println(this.getName()+"连接solr服务器失败.");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}finally{
			accounts.clear();
			accounts=null;
			solrServer.shutdown();
		}
		long endTime = System.currentTimeMillis();
		long castTime=endTime-startTime;
		System.out.println(this.getName()+":执行"+castTime+"毫秒");
	}
}
