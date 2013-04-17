/**      
 * KillEventPool.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.service;

import java.util.Date;
import java.util.List;

import com.iwgame.xengine.xtask.killer.model.KillEvent;

/**
 * @ClassName: KillEventPool
 * @Description: 封杀事件池
 * @author 吴江晖
 * @date 2012-7-11 上午10:21:38
 * @Version 1.0
 * 
 */
public interface KillEventPool {

	public void addKillEvent(KillEvent event);

	public void removeKillEvent(KillEvent event);

	public List<KillEvent> locateKillEvent(Date date);
}
