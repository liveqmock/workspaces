/****************************************************************
 *  文件名     ： XtaskLogService.java
 *  日期         :  2012-9-14
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xtask.support.service;

import java.util.Map;


/** 
 * @ClassName:    XtaskLogService 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-9-14上午09:51:38
 * @Version:      1.0 
 */
public interface XtaskLogService {
	
	public int saveXtaskLogInfo(Map<String,Object> xtasklog);

}
