/****************************************************************
 *  文件名     ： GameSendService.java
 *  日期         :  2012-9-24
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

import com.iwgame.xcloud.searcher.model.AccountParamBean;
import com.iwgame.xcloud.searcher.model.KickParamBean;
import com.iwgame.xcloud.searcher.model.SafeModelBean;
import com.iwgame.xcloud.searcher.model.SecurityCardParamBean;
import com.iwgame.xcloud.searcher.model.TalkParamBean;

/** 
 * @ClassName:    GameSendService 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-9-24下午04:00:36
 * @Version:      1.0 
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface GameSendService {
	
	/**
	 * 密保卡绑定于解绑
	 * @param pid
	 * @param securityCardParamBean
	 * @return
	 */
	@POST
	@Path("/{pid}/sendsecuritycard")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendSecurityCardByMQ(
			@PathParam("pid") String pid,
			@FormParam("")	  SecurityCardParamBean securityCardParamBean
	
	);
	
	
	/**
	 * 角色禁言
	 * @param pid
	 * @param talkParamBean
	 * @return
	 */
	@POST
	@Path("/{pid}/sendnotalk")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendNoTalkByMQ(
			@PathParam("pid") String pid,
			@FormParam("")	  TalkParamBean talkParamBean
	);
	
	
	/**
	 * 禁言解除
	 * @param pid
	 * @param talkParamBean
	 * @return
	 */
	@POST
	@Path("/{pid}/sendtalk")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendTalkByMQ(
			@PathParam("pid") String pid,
			@FormParam("")	  TalkParamBean talkParamBean
	);
	
	/**
	 * 账号封杀,冻结
	 * @param pid
	 * @param accountParamBean
	 * @return
	 */
	@POST
	@Path("/{pid}/sendlockaccount")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendLockAccountByMQ(
			@PathParam("pid") String pid,
			@FormParam("")	  AccountParamBean accountParamBean
	);
	
	/**
	 * 账号解封,解冻
	 * @param pid
	 * @param accountParamBean
	 * @return
	 */
	@POST
	@Path("/{pid}/sendunlockaccount")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendUnLockAccountByMQ(
			@PathParam("pid") String pid,
			@FormParam("")	  AccountParamBean accountParamBean
	);
	
	
	/**
	 * 踢人
	 * @param pid
	 * @param kickParamBean
	 * @return
	 */
	@POST
	@Path("/{pid}/sendkickuser")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendKickUserByMQ(
			@PathParam("pid") String pid,
			@FormParam("")	  KickParamBean kickParamBean
	);
	
	/**
	 * 添加安全模式
	 * @param pid
	 * @param kickParamBean
	 * @return
	 */
	@POST
	@Path("/{pid}/sendAddSafeModel")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendAddSafeModelByMQ(
			@PathParam("pid") String pid,
			@FormParam("")	  SafeModelBean safeModelBean
	);
	
	/**
	 * 解除安全模式
	 * @param pid
	 * @param kickParamBean
	 * @return
	 */
	@POST
	@Path("/{pid}/sendUnSafeModel")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendUnSafeModelByMQ(
			@PathParam("pid") String pid,
			@FormParam("")	  SafeModelBean safeModelBean
	);
}
