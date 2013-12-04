/**      
 * BizLogField.java Create on 2012-9-27     
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
 * @ClassName: BizLogField
 * @Description: 业务日志注释
 * @author 吴江晖
 * @date 2012-9-27 下午02:56:32
 * @Version 1.0
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizLogField {

	/**
	 * 日志标题
	 */
	String value();

	/**
	 * 日志模板
	 */
	String template() default "修改 【{0}】： 将 【{1}】 修改为 【{2}】， 修改人【{3}】";
}
