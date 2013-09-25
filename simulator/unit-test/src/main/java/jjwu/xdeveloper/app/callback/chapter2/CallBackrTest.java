/****************************************************************
 *  文件名   	:	ZookeeperTest.java
 *  日期		:  	2013-8-7
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app.callback.chapter2;


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
		
		KillViewImpl view = new KillViewImpl() {

			@Override
			public void confrimEvent1() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void confrimEvent2() {
				// TODO Auto-generated method stub
				
			}
			
			
		};
		
		
		Thread.sleep(1000);
		
		view.confirm();
		
		
		

	}

}
