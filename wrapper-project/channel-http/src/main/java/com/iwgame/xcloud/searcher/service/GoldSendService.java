/****************************************************************
 *  文件名     ： GoldSendService.java
 *  日期         :  2012-9-21
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
import javax.ws.rs.core.MediaType;

import com.iwgame.xcloud.searcher.model.GoldParamBean;

/** 
 * @ClassName:    GoldSendService 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-9-21上午10:30:40
 * @Version:      1.0 
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface GoldSendService {
	
	@POST
	@Path("/{pid}/sendgold")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendGoldByMQ(
			@PathParam("pid") String pid,
			@FormParam("")	  GoldParamBean goldParamBean
	
	);

}
