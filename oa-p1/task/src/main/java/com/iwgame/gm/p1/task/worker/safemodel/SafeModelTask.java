/****************************************************************
 *  文件名     ： UnSafeModelTask.java
 *  日期         :  2012-11-16
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.worker.safemodel;

import javax.annotation.Resource;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.iwgame.gm.p1.task.bean.SafeModelBean;
import com.iwgame.gm.p1.task.security.dao.sm.SmAccountDataStoreDao;
import com.iwgame.gm.p1.task.security.dao.zxy.ZxyAccountDataStoreDao;
import com.iwgame.gm.p1.task.service.XhttpService;
import com.iwgame.gm.p1.task.util.Constant;

/**
 * @描述: 增加安全模式
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-16下午03:01:47
 * @版本: v1.0
 */
@Component
public class SafeModelTask {

	@Resource
	private XhttpService xhttpService;

	@Resource
	private ZxyAccountDataStoreDao zxyAccountDataStoreDao;

	@Resource
	private SmAccountDataStoreDao smAccountDataStoreDao;

	private final Logger logger = Logger.getLogger(Constant.LOG_SAFE_MODEL);

	public void handleMessage(String source) throws Exception {

		logger.info("收到添加安全模式请求:" + source);

		try {
			SafeModelBean safemodel = new SafeModelBean(source);
			// 允许玩家自助解除安全模式,根据玩家帐号ID查询出玩家帐号,写入小戴指定的一张表,提供玩家自助解除安全模式
			if (safemodel.getFlag() == 1) {
				if (!"".equals(safemodel.getUsername())) {
					try {
						if ("p-p1".equalsIgnoreCase(safemodel.getPid())) {
							int result = smAccountDataStoreDao.addAccountByUserNameToSafeModel(safemodel.getUsername());
							if (result > 0) {
								logger.info("安全模式写入前台自助解除表成功!");
							}
						} else if ("p-p1.5".equalsIgnoreCase(safemodel.getPid())) {
							// 醉逍遥帐号处理
							String username = safemodel.getUsername();
							if (!username.endsWith("_zxy")) {
								username = username + "_zxy";
							}
							int result = zxyAccountDataStoreDao.addAccountByUserNameToSafeModel(username);
							if (result > 0) {
								logger.info("安全模式写入前台自助解除表成功!");
							}
						}
					} catch (DuplicateKeyException e) {
						// 忽略主键冲突的异常信息...
						logger.info("安全模式 - 帐号:[" + safemodel.getUsername() + "] 此帐号已经存在表中,无需插入!");
					} catch (Exception e) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException ex) {
						}
						logger.error("添加安全模式 - 帐号:[" + safemodel.getUsername() + "] 失败! 重试, 异常:" + e);
						throw e;
					}
				} else {
					logger.error("添加安全模式,玩家帐号为空,忽略,请求:" + source);
				}
			}

			// 调用添加安全模式,xhttpservice接口
			int result = xhttpService.sendAddSafeModelByHttpService(safemodel);
			if (result == 0) {
				logger.info("添加安全模式成功!帐号:[" + safemodel.getUsername() + "] 角色ID:" + safemodel.getDbid());
			} else {
				logger.error("添加安全模式失败!帐号:[" + safemodel.getUsername() + "] 返回值:" + result + "角色ID:" + safemodel.getDbid());
			}
		} catch (JSONException e) {
			logger.error("忽略,参数异常!请求:" + source, e);
		} catch (Exception e) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
			}
			logger.error("解封帐号失败,重试...", e);
			throw e;
		}
	}
}
