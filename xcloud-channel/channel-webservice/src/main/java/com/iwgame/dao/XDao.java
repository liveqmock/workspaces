/****************************************************************
 *  文件名     ： FetchInfo.java
 *  日期         :  2012-7-27
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.dao;

import java.util.List;
import java.util.Map;

import com.iwgame.xcloud.searcher.model.ActWinner;



/**
 * 
 * @ClassName:    XDao 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-7-27上午09:36:16
 * @Version:      1.0
 */
public interface XDao {

	List<ActWinner> selectActWinner(Map<String, Object> paramMap);
	
	int updateWinnersFlag(Map<String,Object> params);
	
}
