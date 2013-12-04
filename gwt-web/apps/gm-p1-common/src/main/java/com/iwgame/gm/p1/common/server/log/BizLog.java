/**      
 * BizLog.java Create on 2012-9-27     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.common.server.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: BizLog
 * @Description: 日志标注
 * @author 吴江晖
 * @date 2012-9-27 下午05:02:40
 * @Version 1.0
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizLog {

	String value();

	String createTemplate();

	String relId();
}
