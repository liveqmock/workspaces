/****************************************************************
 *  文件名     ： SmsSendService.java
 *  日期         :  2012-9-7
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.iwgame.xcloud.searcher.model.SmsParamBean;

/**
 * @ClassName:    SmsSendService
 * @Description:  TODO(...)
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-9-7下午02:46:48
 * @Version:      1.0
 */
@Path("/")
@Produces("appication/json")
public interface SmsSendService {

	@POST
	@Path("/{pid}/sendsms")
	@Produces("application/json")
	public String sendSmsByMQ(
			@PathParam("pid")			String pid,
			@FormParam("")				SmsParamBean smsParamBean
			);

	@GET
	@Path("/{pid}/test")
	@Produces("application/json")
<<<<<<< HEAD
	public String test(@Context HttpServletRequest request , @Context HttpServletResponse httpServletResponse);
=======
	public String getHttpRequest(@Context HttpServletRequest request , @Context HttpServletResponse httpServletResponse);
>>>>>>> httprequest

}
