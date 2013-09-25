/****************************************************************
 *  文件名   	:	ZookeeperTest.java
 *  日期		:  	2013-8-7
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app.callback.chapter1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-8-7 下午12:18:16
 * @版本:   	v1.0.0
 */
public class CallBackrTest {
	
	public static void main(String[] args) throws Throwable {
		
		List<Object> list = new ArrayList<Object>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("u", "u");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("u", "u");
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("u", "u");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		
		JSONArray object = JSONArray.fromObject(list);
//		System.out.println(object.toString());
		
//		JSONObject json = JSONObject.fromObject("[{\"u\":\"u\"},{\"u\":\"u\"},{\"u\":\"u\"}]");
		JSONObject json = JSONObject.fromObject(object);
		if(json.isArray()){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		EventButton eventButton = new EventButton() {
//			
//			@Override
//			public void zoomlEvent() {
//				
//			}
//			
//			@Override
//			public void minimizeEvent() {
//				
//			}
//			
//			@Override
//			public void confrimEvent() {
//				
//			}
//			
//			@Override
//			public void cancelEvent() {
//				System.out.println("窗口取消事件触发!");
//				System.out.println();
//				System.out.println("更新数据库ing....");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//				}
//				System.out.println("更新数据库成功!");
//			}
//		};
//		
//		
//		ViewImpl view = new ViewImpl();
//		view.registerEvent(eventButton);
//		view.cancel();
	}

}
