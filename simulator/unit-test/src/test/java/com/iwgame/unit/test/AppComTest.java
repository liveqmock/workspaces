package com.iwgame.unit.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author jjwu
 * 
 */
public class AppComTest {
	@Test
	public void testTel() {
		final WebResource client = Client.create().resource("");
		final String pid = "common";

		final WebResource wr = client.path("http://data.iwgame.com/service/sms/" + pid + "/sendsms");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String phone = "13776801367";

		final String str = pid + "&" + phone + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "sq");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("phone", phone);
		param.add("message", "这是测试短信");
		param.add("queueNo", "3722");

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

		System.out.println(rs);
	}

	//
	// @TestConfig
	// public void testEmail() {
	// WebResource client = Client.create().resource("");
	// String pid = "common";
	//
	// WebResource wr = client.path("http://data.iwgame.com/service/mail/" + pid
	// + "/sendmail");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// // String emailAddress = "4958454@qq.com";
	// // String emailAddress = "335201@qq.com";
	// String emailAddress = "asdf2hjkl@163.com";
	//
	// String templateId = "210311";
	// String str = pid + "&" + emailAddress + "&" +
	// "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "yunwei");
	// param.add("ts", ts);
	// param.add("sign", sign);
	// param.add("templateId", templateId);
	// param.add("emailAddress", emailAddress);
	// param.add("FNAME", "测试卡号:10101010101");
	// param.add("LNAME", "测试卡密:12345678901");
	//
	// param.add("aparam", "");
	// param.add("bparam", "");
	// param.add("cparam", "");
	//
	// String rs =
	// wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
	//
	// System.out.println(rs);
	// }
	//
	@Test
	public void testItemCardList() {

		final File file = new File("/Users/jjwu/Desktop/tmp.txt");
		FileReader reader = null;
		BufferedReader buffer = null;
		try {
			reader = new FileReader(file);
			buffer = new BufferedReader(reader);
			String json = null;
			int count = 0;
			while ((json = buffer.readLine()) != null) {
				final JSONObject jsonObject = JSONObject.fromObject(json);
				final WebResource client = Client.create().resource("");

				final String pid = "P-P1";

				final WebResource wr = client.path("http://data.iwgame.com/service/itemcard/" + pid + "/senditemcard");

				final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

				final String ts = String.valueOf(System.currentTimeMillis());

				final String username = jsonObject.getString("username");
				final String guid = jsonObject.getString("guid");
				final String appname = jsonObject.getString("appname");

				final String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
				final String sign = MD5Util.md5sum(str);

				param.add("appname", appname);
				param.add("ts", ts);
				param.add("sign", sign);
				param.add("guid", guid);
				param.add("username", username);
				param.add("cardnum", jsonObject.getString("cardnum"));
				param.add("cardpwd", jsonObject.getString("cardpwd"));
				param.add("validtime", "");
				param.add("itype", "0");

				final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
				count++;
				System.out.println("row:"+count+"  "+rs);
			}
		} catch (final FileNotFoundException e) {
			System.err.println("文件没有找到!");
		} catch (final IOException e) {
			System.err.println("IO异常");
		} finally {
			try {
				reader.close();
				buffer.close();
			} catch (final IOException e) {
				System.err.println("IO异常");
			}

		}
	}

	@Test
	public void testItemCardOne() {
		final WebResource client = Client.create().resource("");

		final String pid = "P-P1";

		final WebResource wr = client.path("http://data.iwgame.com/service/itemcard/" + pid + "/senditemcard");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String username = "duanzhijian12211";
		final String guid = "dx17";

		final String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "snsgame2");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("guid", guid);
		param.add("username", username);
		param.add("cardnum", "STRBSA40HH79F8B");
		param.add("cardpwd", "27139472061952");
		param.add("validtime", "");
		param.add("itype", "0");

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

		System.out.println(rs);
	}

	/**
	 * 密保卡测试
	 */
	@Test
	public void testSecurityCard() {
		final WebResource client = Client.create().resource("");

		final String pid = "P-P1";

		final WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid + "/sendsecuritycard");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String username = "shumenceshi005";
		final String guid = "dx1";

		final String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "yunwei");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("guid", guid);
		param.add("name", username);
		param.add("type", "6");
		param.add("str3",
				"722337968335246445981686533794724395603395427965655695720745496692027295209220923293681771746988115637");
		param.add("operate", "2");

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

		System.out.println(rs);
	}

	/**
	 * 密保卡测试
	 */
	@Test
	public void testKickuser() {
		final WebResource client = Client.create().resource("");

		final String pid = "P-P1";

		final WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid + "/sendkickuser");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String username = "shumenceshi005";
		final String guid = "dx1";

		final String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "yunwei");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("guid", guid);
		param.add("username", username);

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

		System.out.println(rs);
	}

	@Test
	public void testNoTalk() {
		final WebResource client = Client.create().resource("");

		final String pid = "P-P1";

		final WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid + "/sendnotalk");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String username = "yunwei";
		final String guid = "dx1";

		final String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "yunwei");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("guid", guid);
		param.add("username", username);
		param.add("groupname", "电信二三区【新月风云】");
		param.add("validtime", "10");

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

		System.out.println(rs);
	}

	@Test
	public void testTalk() {
		final WebResource client = Client.create().resource("");

		final String pid = "P-P1";

		final WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid + "/sendtalk");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String username = "yunwei";
		final String guid = "dx1";

		final String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "解除禁言");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("guid", guid);
		param.add("username", username);
		param.add("groupname", "电信二三区【新月风云】");
		param.add("validtime", "10");

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

		System.out.println(rs);
	}

	/**
	 * 账号解封
	 */
	@Test
	public void testUnLockAccount() {
		final WebResource client = Client.create().resource("");

		final String pid = "P-P1";

		final WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid + "/sendunlockaccount");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String username = "shumenceshi005";
		final String guid = "dxfc";

		final String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "unknown");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("guid", guid);
		param.add("username", username);
		param.add("sealtime", "10");
		param.add("rolename", "");
		param.add("note", "");

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

		System.out.println(rs);
	}

	/**
	 * 账号解封
	 */
	@Test
	public void testLockAccount() {
		final WebResource client = Client.create().resource("");

		final String pid = "P-P1";

		final WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid + "/sendlockaccount");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String username = "shumenceshi005";
		final String guid = "dxfc";

		final String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "unknown");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("guid", guid);
		param.add("username", username);
		param.add("sealtime", "10");
		param.add("rolename", "");
		param.add("note", "");

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

		System.out.println(rs);
	}

	@Test
	public void testActor() {
		final WebResource client = Client.create().resource("");

		final String pid = "P-P1";

		final String accountid = "24667552";

		final WebResource wr = client.path("http://data.iwgame.test/service/account/" + pid + "/" + accountid
				+ "/getAccount");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String str = pid + "&" + accountid + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "获取角色信息测试");
		param.add("ts", ts);
		param.add("sign", sign);

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).get(String.class);

		System.out.println(rs);
	}

	@Test
	public void testGold() {
		final WebResource client = Client.create().resource("");
		final String pid = "P-P1";

		final WebResource wr = client.path("http://data.iwgame.com/service/gold/" + pid + "/sendgold");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String accountid = "335920";

		final String str = pid + "&" + accountid + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "wwwhd");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("activeid", "123456");
		param.add("activename", "送水晶test");
		param.add("accountid", accountid);
		param.add("name", "wujunjie");

		param.add("actor", "霸王别姬");
		param.add("type", "1");
		param.add("gameid", "2");
		param.add("zoneid", "2");
		param.add("value", "2");

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

		System.out.println(rs);
	}

	@Test
	public void test_IPTransfer() {
		final WebResource client = Client.create().resource("");

		final String pid = "common";

		final String ip = "";

		final WebResource wr = client.path("http://data.iwgame.com/service/iptranslate/"+pid+"/getTranslate");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String str = pid + "&" + ip + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "security");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("ip", ip);

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(rs);
	}

	@Test
	public void testItemCardActivity() {
		final WebResource client = Client.create().resource("");
		final String pid = "common";
		final WebResource wr = client.path("http://.iwgame.com/service/sms/" + pid + "/sendsms");
		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();
		final String ts = String.valueOf(System.currentTimeMillis());

		final String phone = "13776801367";
		final String str = pid + "&" + phone + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "sq");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("phone", phone);
		param.add("message", "这是测试短信");
		param.add("queueNo", "3722");

		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
		System.out.println(rs);
	}


	@Test
	public void testCustomerService() {
		final WebResource client = Client.create().resource("");

		final String pid = "P-P1";

		final String username = "kakapo";

		final WebResource wr = client.path("http://data.iwgame.com/service/customerService/" + pid + "/getUserInfo");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

		final String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		final String sign = MD5Util.md5sum(str);

		param.add("appname", "wwwhd");
		param.add("ts", ts);
		param.add("sign", sign);

		param.add("username", username);
		param.add("svr", "all");
		param.add("matchtype", "1");
		param.add("begintime", "2013-01-20");
		param.add("endtime", "2013-03-20");
		final String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(rs);
	}

}
