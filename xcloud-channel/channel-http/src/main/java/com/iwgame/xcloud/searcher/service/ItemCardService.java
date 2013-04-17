/****************************************************************
 *  文件名     ： ItemCardActivateService.java
 *  日期         :  2012-9-7
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.iwgame.xcloud.searcher.model.ItemCardParamBean;

/** 
 * @ClassName:    ItemCardActivateService 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-9-7下午02:48:22
 * @Version:      1.0 
 */
@Path("/")
@Produces("appication/json")
public interface ItemCardService {
	
	
	@POST
	@Path("/{pid}/senditemcard")
	@Produces("application/json")
	public String sendItemCardByMQ(
			@PathParam("pid")			String pid,
			@FormParam("")				ItemCardParamBean	itemCardParamBean
	);

}
