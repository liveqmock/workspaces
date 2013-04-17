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
 * Unit test for simple App.
 */
public class AppComTest {
	@Test
	public void testTel() {
		WebResource client = Client.create().resource("");
		String pid = "common";

		WebResource wr = client.path("http://data.iwgame.com/service/sms/" + pid + "/sendsms");

		MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		String ts = String.valueOf(System.currentTimeMillis());

		String phone = "13776801367";

		String str = pid + "&" + phone + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
		String sign = MD5Util.md5sum(str);

		param.add("appname", "sq");
		param.add("ts", ts);
		param.add("sign", sign);
		param.add("phone", phone);
		param.add("message", "这是测试短信");
		param.add("queueNo", "3722");

		String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

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
	public void testItemCard() {

		File file = new File("/Users/jjwu/Desktop/card.txt");
		FileReader reader = null;
		BufferedReader buffer = null;
		try {
			reader = new FileReader(file);
			buffer = new BufferedReader(reader);
			String json = null;

			while ((json = buffer.readLine()) != null) {
				JSONObject jsonObject = JSONObject.fromObject(json);
				WebResource client = Client.create().resource("");

				String pid = "P-P1";

				WebResource wr = client.path("http://data.iwgame.com/service/itemcard/" + pid + "/senditemcard");

				MultivaluedMap<String, String> param = new MultivaluedMapImpl();

				String ts = String.valueOf(System.currentTimeMillis());

				String username = jsonObject.getString("username");
				String guid = jsonObject.getString("guid");
				String appname = jsonObject.getString("appname");

				String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
				String sign = MD5Util.md5sum(str);

				param.add("appname", appname);
				param.add("ts", ts);
				param.add("sign", sign);
				param.add("guid", guid);
				param.add("username", username);
				param.add("cardnum", jsonObject.getString("cardnum"));
				param.add("cardpwd", jsonObject.getString("cardpwd"));
				param.add("validtime", "");
				param.add("itype", "0");

				String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);

				System.out.println(rs);
			}
		} catch (FileNotFoundException e) {
			System.err.println("文件没有找到!");
		} catch (IOException e) {
			System.err.println("IO异常");
		} finally {
			try {
				reader.close();
				buffer.close();
			} catch (IOException e) {
				System.err.println("IO异常");
			}

		}
	}

	// WebResource client = Client.create().resource("");
	//
	// String pid = "P-P1.5";
	//
	// WebResource wr =
	// client.path("http://data.iwgame.com/service/itemcard/" + pid +
	// "/senditemcard");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// String username = "wujunjie";
	// String guid = "all";
	//
	// String str = pid + "&" + username + "&" +
	// "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "sq");
	// param.add("ts", ts);
	// param.add("sign", sign);
	// param.add("guid", guid);
	// param.add("username", username);
	// param.add("cardnum", "WUJUNJIE_1");
	// param.add("cardpwd", "123456789012");
	// param.add("validtime", "");
	// param.add("itype", "0");
	//
	// String rs =
	// wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
	//
	// System.out.println(rs);
	// }

	//
	// /**
	// * 密保卡测试
	// */
	// @TestConfig
	// public void testSecurityCard() {
	// WebResource client = Client.create().resource("");
	//
	// String pid = "P-P1";
	//
	// WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid
	// + "/sendsecuritycard");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// String username = "shumenceshi005";
	// String guid = "dx1";
	//
	// String str = pid + "&" + username + "&" +
	// "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "yunwei");
	// param.add("ts", ts);
	// param.add("sign", sign);
	// param.add("guid", guid);
	// param.add("name", username);
	// param.add("type", "6");
	// param.add("str3",
	// "722337968335246445981686533794724395603395427965655695720745496692027295209220923293681771746988115637");
	// param.add("operate", "2");
	//
	// String rs =
	// wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
	//
	// System.out.println(rs);
	// }
	//
	// /**
	// * 密保卡测试
	// */
	// @TestConfig
	// public void testKickuser() {
	// WebResource client = Client.create().resource("");
	//
	// String pid = "P-P1";
	//
	// WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid
	// + "/sendkickuser");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// String username = "shumenceshi005";
	// String guid = "dx1";
	//
	// String str = pid + "&" + username + "&" +
	// "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "yunwei");
	// param.add("ts", ts);
	// param.add("sign", sign);
	// param.add("guid", guid);
	// param.add("username", username);
	//
	// String rs =
	// wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
	//
	// System.out.println(rs);
	// }
	//
	// @TestConfig
	// public void testNoTalk() {
	// WebResource client = Client.create().resource("");
	//
	// String pid = "P-P1";
	//
	// WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid
	// + "/sendnotalk");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// String username = "yunwei";
	// String guid = "dx1";
	//
	// String str = pid + "&" + username + "&" +
	// "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "yunwei");
	// param.add("ts", ts);
	// param.add("sign", sign);
	// param.add("guid", guid);
	// param.add("username", username);
	// param.add("groupname", "电信二三区【新月风云】");
	// param.add("validtime", "10");
	//
	// String rs =
	// wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
	//
	// System.out.println(rs);
	// }
	//
	// @TestConfig
	// public void testTalk() {
	// WebResource client = Client.create().resource("");
	//
	// String pid = "P-P1";
	//
	// WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid
	// + "/sendtalk");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// String username = "yunwei";
	// String guid = "dx1";
	//
	// String str = pid + "&" + username + "&" +
	// "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "解除禁言");
	// param.add("ts", ts);
	// param.add("sign", sign);
	// param.add("guid", guid);
	// param.add("username", username);
	// param.add("groupname", "电信二三区【新月风云】");
	// param.add("validtime", "10");
	//
	// String rs =
	// wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
	//
	// System.out.println(rs);
	// }
	//
	//
	// /**
	// * 账号解封
	// */
	// @Test
	// public void testUnLockAccount() {
	// WebResource client = Client.create().resource("");
	//
	// String pid = "P-P1";
	//
	// WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid
	// + "/sendunlockaccount");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// String username = "shumenceshi005";
	// String guid = "dxfc";
	//
	// String str = pid + "&" + username + "&" +
	// "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "unknown");
	// param.add("ts", ts);
	// param.add("sign", sign);
	// param.add("guid", guid);
	// param.add("username", username);
	// param.add("sealtime", "10");
	// param.add("rolename", "");
	// param.add("note", "");
	//
	// String rs =
	// wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
	//
	// System.out.println(rs);
	// }

	// /**
	// * 账号解封
	// */
	// @Test
	// public void testLockAccount() {
	// WebResource client = Client.create().resource("");
	//
	// String pid = "P-P1";
	//
	// WebResource wr = client.path("http://data.iwgame.com/service/game/" + pid
	// + "/sendlockaccount");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// String username = "shumenceshi005";
	// String guid = "dxfc";
	//
	// String str = pid + "&" + username + "&" +
	// "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "unknown");
	// param.add("ts", ts);
	// param.add("sign", sign);
	// param.add("guid", guid);
	// param.add("username", username);
	// param.add("sealtime", "10");
	// param.add("rolename", "");
	// param.add("note", "");
	//
	// String rs =
	// wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
	//
	// System.out.println(rs);
	// }

	//
	//
	// @Test
	// public void testActor() {
	// WebResource client = Client.create().resource("");
	//
	// String pid = "P-P1";
	//
	// String accountid = "24667552";
	//
	// WebResource wr = client.path("http://data.iwgame.test/service/account/" + pid + "/" + accountid + "/getAccount");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// String str = pid + "&" + accountid + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "获取角色信息测试");
	// param.add("ts", ts);
	// param.add("sign", sign);
	//
	// String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).get(String.class);
	//
	// System.out.println(rs);
	// }
	//
	// @Test
	// public void testGold() {
	// WebResource client = Client.create().resource("");
	// String pid = "P-P1";
	//
	// WebResource wr = client.path("http://data.iwgame.com/service/gold/" + pid
	// + "/sendgold");
	//
	// MultivaluedMap<String, String> param = new MultivaluedMapImpl();
	//
	// String ts = String.valueOf(System.currentTimeMillis());
	//
	// String accountid = "335920";
	//
	// String str = pid + "&" + accountid + "&" +
	// "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
	// String sign = MD5Util.md5sum(str);
	//
	// param.add("appname", "wwwhd");
	// param.add("ts", ts);
	// param.add("sign", sign);
	// param.add("activeid", "123456");
	// param.add("activename", "送水晶test");
	// param.add("accountid", accountid);
	// param.add("name", "wujunjie");
	//
	// param.add("actor", "霸王别姬");
	// param.add("type", "1");
	// param.add("gameid", "2");
	// param.add("zoneid", "2");
	// param.add("value", "2");
	//
	// String rs =
	// wr.queryParams(param).accept(MediaType.APPLICATION_JSON).post(String.class);
	//
	// System.out.println(rs);
	// }
	
	@Test
    public void testCustomerService() {
        WebResource client = Client.create().resource("");

        String pid = "P-P1";

        String username = "kakapo";

        WebResource wr = client.path("http://data.iwgame.com/service/customerService/" + pid + "/getUserInfo");

        MultivaluedMap<String, String> param = new MultivaluedMapImpl();

        String ts = String.valueOf(System.currentTimeMillis());

        String str = pid + "&" + username + "&" + "glWN8S1Al1JznVqjf1jV1CMOifQyp8Ve" + "&" + ts;
        String sign = MD5Util.md5sum(str);

        param.add("appname", "wwwhd");
        param.add("ts", ts);
        param.add("sign", sign);
        
        param.add("username", username);
        param.add("svr", "all");
        param.add("matchtype", "1");
        param.add("begintime", "2013-01-20");
        param.add("endtime", "2013-03-20");
        String rs = wr.queryParams(param).accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(rs);
    }

}
