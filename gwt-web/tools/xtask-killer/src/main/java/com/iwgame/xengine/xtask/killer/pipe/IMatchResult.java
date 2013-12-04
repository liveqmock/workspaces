/**      
 * IMatchResult.java Create on 2012-4-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.pipe;

/**
 * @ClassName: IMatchResult
 * @Description: 目标数据匹配结果接口
 * @author 吴江晖
 * @date 2012-4-28 上午11:56:21
 * @Version 1.0
 * 
 */
public interface IMatchResult {

	/**
	 * @return 匹配结果，true=匹配；反之不匹配
	 */
	public boolean isMatch();
}
