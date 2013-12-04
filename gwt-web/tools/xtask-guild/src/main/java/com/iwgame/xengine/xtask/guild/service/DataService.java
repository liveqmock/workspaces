/**      
 * DataService.java Create on 2012-6-26     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.service;

import java.util.Date;

/**
 * @ClassName: DataService
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-26 下午02:57:24
 * @Version 1.0
 * 
 */
public interface DataService {

	public int addConsume(String productId, String username, double money);

	public int addPaid(String productId, String username, double money);

	public int updateLogin(String productId, String username, Date lastLogin, long onlineTime);

	public int updateActor(String productId, String username, String zone, String group, int level, int score,
			int incrScore);
}
