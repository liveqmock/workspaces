/****************************************************************
 *  文件名     ： ZxyAdsDao.java
 *  日期         :  2012-8-15
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.baidu.task.service.zxy;

import java.util.Map;

/**
 * @ClassName: ZxyAdsDao
 * @Description: TODO(...)
 * @author: 吴君杰
 * @email: wujunjie@iwgame.com
 * @date: 2012-8-15下午07:07:34
 * @Version: 1.0
 */
@SuppressWarnings("rawtypes")
public interface ZxyAdsDao {

	public int sp_store_baidu_ads(Map param);

	public int sp_store_baidu_keyid_ads(Map param);
}
