package jjwu.xdeveloper.app.xml;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.iwgame.unit.test.MD5Util;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class Action {
	private Long id;
	private String name;
	private int isSub;
	private String remarks;

	public void sendsms(String phone) {

		final WebResource client = Client.create().resource("");
		final String pid = "common";

		final WebResource wr = client.path("http://data.iwgame.com/service/sms/" + pid + "/sendsms");

		final MultivaluedMap<String, String> param = new MultivaluedMapImpl();

		final String ts = String.valueOf(System.currentTimeMillis());

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsSub() {
		return isSub;
	}

	public void setIsSub(int isSub) {
		this.isSub = isSub;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
