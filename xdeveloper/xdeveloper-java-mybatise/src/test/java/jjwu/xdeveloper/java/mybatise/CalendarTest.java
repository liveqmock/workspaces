/****************************************************************
 *  文件名     ： CalendarTest.java
 *  日期         :  2013-3-4
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.java.mybatise;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/** 
 * @类名:    CalendarTest 
 * @描述:    TODO(...) 

 * @作者:    吴君杰
 * @邮件:    wujunjie@iwgame.com
 * @日期:    2013-3-4上午9:52:02
 * @版本:    1.0.0 
 */
public class CalendarTest {
	
	
	@Test
	public void test(){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		Calendar calendar = Calendar.getInstance();
		Date now = new Date();
		calendar.setTime(now);
		
		System.out.println(dateFormat.format(now));
		
		calendar.add(Calendar.MINUTE, 1);
		
		System.out.println(dateFormat.format(calendar.getTime()));
		
	}
	
	@Test
	public void test2(){
		List<String> list = new ArrayList<String>();
		list.add("wujunjie");
		list.set(0, "jiangxiaohui");
		for (int i = 0; i < 5; i++) {
			list.add(String.valueOf(i));
		}
		System.out.println(list.size());
		
	}

}
