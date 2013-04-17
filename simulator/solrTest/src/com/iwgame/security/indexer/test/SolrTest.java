package com.iwgame.security.indexer.test;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iwgame.security.indexer.bean.Account;
import com.iwgame.security.indexer.utils.DateFunc;
import com.iwgame.security.indexer.utils.SolrUtil;


//构造测试数据,测试索引CRUD
public class SolrTest {
	private static final String SOLR_SERVER_URL = "http://solr.mac.tec/solr/sm-account";
	private static HttpSolrServer solrServer =null;
	
	@Before
	public void init()
	{
		solrServer = SolrUtil.getHttpSolrServer(SOLR_SERVER_URL);
	}
	
	/*
	 * 常用查询参数:
	 * 	q - 查询字符串，必须的。
		fl - 指定返回那些字段内容，用逗号或空格分隔多个。
		start - 返回第一条记录在完整找到结果中的偏移位置，0开始，一般分页用。
		rows - 指定返回结果最多有多少条记录，配合start来实现分页。
		sort - 排序，格式：sort=<field name>+<desc|asc>[,<field name>+<desc|asc>]… 。示例：（inStock desc, price asc）表示先 “inStock” 降序, 再 “price” 升序，默认是相关性降序。
		wt - (writer type)指定输出格式，可以有 xml, json, php, phps, 后面 solr 1.3增加的，要用通知我们，因为默认没有打开。
		fq - （filter query）过虑查询，作用：在q查询符合结果中同时是fq查询符合的，例如：q=mm&fq=date_time
	*/
	@Test
	public void testQueryDocumentList()
	{
		ModifiableSolrParams params = new ModifiableSolrParams();
		SolrQuery filterQuery = new SolrQuery();
		//查询默认filed
		//账户模糊查询
		filterQuery.add("q", "*:*");
		//注册模糊IP查询
		//filterQuery.add("q", "user_ip:180.120* OR user_ip:101.142*");
		/*
		 * 范围查询
		 * [] <= and >=
		 * {} < and >
		 * 1.日期范围查询
		 * user_reg_time:[* TO NOW]
		 * user_reg_time:[2010-10-5T12:59:59Z TO NOW]
		 * user_reg_time:[2010-10-5T12:59:59Z TO 2011-8-22T00:00:00Z]
		 * 2.数字范围查询
		 * totalCost<=1000
		 * totalCost:[* TO 1000]
		 * totalCost>=1000
		 * totalCost:[1000 TO *]
		 * totalCost<=1000 and totalCost>=2555
		 * totalCost:[1000 TO 2555]
		 */
		//多条件过滤查询
		Date nowDate = new Date();
		//默认查询3个月
		Date m3date=DateFunc.getRelativeDate(nowDate, DateFunc.DIFF_MONTH, -3);
		String m3 = DateFunc.getDateString(m3date, "yyyy-MM-dd HH:mm:ss").replace(" ", "T")+"Z";
		String now = DateFunc.getDateString(nowDate, "yyyy-MM-dd HH:mm:ss").replace(" ", "T")+"Z";
		filterQuery.addFilterQuery("user_reg_time:["+m3+" TO "+now+"]");
		//filterQuery.addFilterQuery("totalCost:[1000 TO *]");
		filterQuery.addFilterQuery("user_email:*xXdg@iwgame.com*");
		//filterQuery.addFilterQuery("idCard:*411323*");
		//排序
		//filterQuery.addSortField("accountid",ORDER.desc);
		filterQuery.addSortField("user_reg_time", ORDER.desc);
		//分页处理
		filterQuery.setStart(0);
		filterQuery.setRows(50);
		
		params.add(filterQuery);
		SolrDocumentList documentList = SolrUtil.query(params, solrServer);
		//符合条件的记录总数
		long total = documentList.getNumFound();
		//solrDocument转换为bean
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<Account> accounts = binder.getBeans(Account.class, documentList);
		for (Account account : accounts) {
			System.out.println(account);
		}

		System.out.println("共找到"+total+"条符合条件的记录");
	}
	
	/*
	//测试查询bean
	@Test
	public void testQueryBeans()
	{
		ModifiableSolrParams params = new ModifiableSolrParams();
		SolrQuery filterQuery = new SolrQuery();
		
		filterQuery.add("q", "user_email:*@sina.com*");
		filterQuery.setStart(0);
		filterQuery.setRows(200);
		params.add(filterQuery);
		List<Account> accounts = SolrUtil.queryBeans(params, Account.class, solrServer);
		for (Account account : accounts) {
			System.out.println(account);
		}
	}
	*/
	@Test
	public void testDeleteByQuery()
	{
		ModifiableSolrParams params = new ModifiableSolrParams();
		//查询默认filed
		params.add("q", "*MooYAybnIB*");
		SolrDocumentList documentList = SolrUtil.query(params, solrServer);
		
		int status = SolrUtil.deleteByQuery("*MooYAybnIB*", solrServer);
		
		//符合条件的记录总数
		long total = documentList.getNumFound();
		
		Assert.assertEquals(0, status);
		
		Assert.assertEquals(0, total);
	}


}
