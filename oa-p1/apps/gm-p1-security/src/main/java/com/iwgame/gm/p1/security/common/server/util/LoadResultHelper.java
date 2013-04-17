/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： LoadResultHelper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.util;

import java.util.List;


import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;

/**
 * @简述： 将结果list转成schemaGridView识别的json串
 * @作者： 张 扬
 * @版本： 1.0
 * @邮箱： zhang_yang@iwgame.com
 * @修改时间：2012-10-12 下午06:50:29
 */
public class LoadResultHelper {

	public static <T> String buildResult(List<T> list,int limit,int offset,int total) {
		PagingLoadResult<T> result = new PagingLoadResult<T>();
		result.setRows(list);
		result.setLimit(limit);
		result.setTotal(total);
		result.setOffset(offset);
		return GridHelper.buildResults(result);
	}

}
