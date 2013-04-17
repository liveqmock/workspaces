package com.iwgame.security.indexer.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;


import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;

public class SolrUtil {
	
	/**
	 * @简述     批量添加数据到索引
	 * @param beans 对象集合
	 * @param solrServer
	 */
	public static int commit(Collection<?> beans,SolrServer solrServer)
	{
		try {
			solrServer.addBeans(beans);
			UpdateResponse updateResponse =solrServer.commit();
			return updateResponse.getStatus();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * @简述     添加一条数据到索引
	 * @param obj 
	 * @param solrServer
	 */
	public static int commit(Object obj,SolrServer solrServer)
	{
		try {
			solrServer.addBean(obj);
			UpdateResponse updateResponse =solrServer.commit();
			return updateResponse.getStatus();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * @简述    删除索引记录
	 * @param ids 主键Id集合
	 * @param uniqueKey schema.xml中定义的uniqueKey,主键列名称
	 * @param solrServer
	 */
	public static int deleteById(List<String> ids, String uniqueKey, SolrServer solrServer)
	{
		try {
			solrServer.deleteById(ids);
			UpdateResponse updateResponse =solrServer.commit();
			return updateResponse.getStatus();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * @简述 根据SolrParams查询
	 * @param params SolrParams
	 * @param server solrSerer
	 * @return SolrDocumentList 符合条件的document对象
	 */
	public static SolrDocumentList query(SolrParams params,SolrServer server)
	{
		try {
			QueryResponse response = server.query(params);
			SolrDocumentList documentList = response.getResults();
			System.out.println("查询用时:"+response.getQTime()+"毫秒");
			return documentList;
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @简述   根据查询条件 删除索引记录
	 */
	public static int deleteByQuery(String keyword,SolrServer solrServer)
	{
		try {
			solrServer.deleteByQuery(keyword);
			UpdateResponse updateResponse =solrServer.commit();
			return updateResponse.getStatus();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * @简述: 根据SolrQuery查询
	 * @param query SolrQuery
	 * @param cls class对象
	 * @param server SolrServer
	 * @return List<T> 符合条件的对象集合
	 */
//	public static <T> List<T> queryBeans(SolrParams params, Class<T> cls,SolrServer server)
//	{
//		try {
//			QueryResponse response = server.query(params);
//			List<T> beans = response.getBeans(cls);
//			long total=response.getResults().getNumFound();
//			System.out.println("查询用时:"+response.getQTime()+"毫秒");
//			System.out.println("共查询到"+total+"条符合条件的数据");
//			server.shutdown();
//			return beans;
//		} catch (SolrServerException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	
	/**
	 * @简述  根据solr服务器url获取HttpSolrServer实例
	 * @param url 服务器url,不能为空
	 * @return HttpSolrServer
	 */
	public static HttpSolrServer getHttpSolrServer(String url)
	{
		HttpSolrServer solrServer = null;
		if (url==null||"".equals(url.trim())) {
			
			return solrServer;
		}
		try {
			solrServer =new HttpSolrServer(url);
			solrServer.setRequestWriter(new BinaryRequestWriter());
//			solrServer.setSoTimeout(1000);// socket read timeout
//			solrServer.setConnectionTimeout(100);
//			solrServer.setMaxTotalConnections(100);
			solrServer.setFollowRedirects(false);// defaults to false
			// allowCompression defaults to false.
			// Server side must support gzip or deflate for this to have any effect.
			solrServer.setAllowCompression(true);
			solrServer.setMaxRetries(1);// defaults to 0.  > 1 not recommended.
			return solrServer;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return solrServer;
	}
}
