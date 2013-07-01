/****************************************************************
 *  文件名   	:	Model.java
 *  日期		:  	2013-6-8
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-6-8 下午4:34:57
 * @版本:   	v1.0.0
 */
public class Model {

	public static void main(String[] args) {


		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("zone", "dx1");
		param.put("group", "game1");



		Map<String, String> wuqi1 = new LinkedHashMap<String, String>();
		wuqi1.put("id", "1");
		wuqi1.put("ownername", "百战不胜之恨");
		wuqi1.put("lev", "76");
		wuqi1.put("equipname", "黑炎(8阶毒系匕首)");
		wuqi1.put("equipscore", "5622");
		Map<String, String> wuqi2 = new LinkedHashMap<String, String>();
		wuqi2.put("id", "2");
		wuqi2.put("ownername", "不胜");
		wuqi2.put("lev", "76");
		wuqi2.put("equipname", "黑炎(8阶毒系匕首)");
		wuqi2.put("equipscore", "5622");

		List<Map<String,String>> wuqis = new ArrayList<Map<String,String>>();
		wuqis.add(wuqi1);
		wuqis.add(wuqi2);

		Map<String, String> renqi = new LinkedHashMap<String, String>();
		renqi.put("id", "1");
		renqi.put("name", "┖s°靓靓");
		renqi.put("lev", "76");
		renqi.put("renqi", "57650");
		renqi.put("guildname", "☆紫色☆");
		List<Map<String,String>> renqis = new ArrayList<Map<String,String>>();
		renqis.add(renqi);
		param.put("type", "wuqi");
		param.put("msg", wuqis);


		System.out.println(JSONObject.fromObject(param).toString());


	}


}
