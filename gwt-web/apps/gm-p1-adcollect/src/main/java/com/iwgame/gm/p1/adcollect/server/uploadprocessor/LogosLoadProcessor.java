/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LogosLoadProcessor.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.uploadprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xmvp.server.fileupload.AbstractMediaFileProcessor;
import com.iwgame.xmvp.shared.Media;
import com.iwgame.xportal.common.server.dao.ProductDao;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.shared.model.Product;

/**
 * 类说明
 * 
 * @简述： LPGPS
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午03:46:42
 */
public class LogosLoadProcessor extends AbstractMediaFileProcessor {
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_ADMIN_MGR, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(LogosLoadProcessor.class);
	private static final String AHA = "ad.hard.admin.";

	private ProductDao productDao;
	
	@Autowired
	public void setProductDao(ProductDao productDao) {
	    this.productDao = productDao;
	}
	
	private String physicalPath;

	@Override
	public String getPhysicalPath() {
		return physicalPath;
	}

	public void setPhysicalPath(final String physicalPath) {
		this.physicalPath = physicalPath;
	}

	private DBConnection dbConnectorConnection;

	@Autowired
	public void setDbConnection(final DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	protected SqlSessionTemplate getSqlSessionTemplate(final String productId) {
		final String targetId = productId + "-AD";
		final SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId,
				targetId, null);
		return sqlSessionTemplate;
	}

	@Override
	protected void postUploadFile() {
		final Map<String, Object> parameter = new HashMap<String, Object>();
		final Map<String, Media> media = getFiles();
		if ((null != media) && !media.isEmpty()) {
			parameter.put("path", media.get("logos").getUrl());
			parameter.put("width", media.get("logos").getWidth());
			parameter.put("height", media.get("logos").getHeight());
			final long size = media.get("logos").getSize();
			parameter.put("capacity", Integer.parseInt(size + ""));
			parameter.put("contentType", media.get("logos").getContentType());
		}
		parameter.put("name", getCommonFields().get("name").toString());
//		final String productId = getCommonFields().get("productId").toString();
		final String operation = getCommonFields().get("operation").toString();

		if (operation.equals("add")) {// 添加
		    try {
			List<String> list = getProducts();
			for (String product : list) {
			    getSqlSessionTemplate(product).insert(AHA + "addLogos", parameter);
			    logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_CONTRACT_LOGO, "产品"+product+"添加合同Logo成功");
			}
		    } catch (Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_CONTRACT_LOGO, "添加合同Logo失败");
		    }	
		}
		if (operation.equals("update")) {// 修改.
		    final Integer id = Integer.parseInt(getCommonFields().get("id"));
		    parameter.put("id", id);
		    try {
			List<String> list = getProducts();
			for (String product : list) {
			    getSqlSessionTemplate(product).update(AHA + "updateLogos", parameter);
			    logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_CONTRACT_LOGO, "产品"+product+"修改合同Logo成功");
			}
		    } catch (Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_CONTRACT_LOGO, "修改合同Logo失败");
		    }	
		}

		super.postUploadFile();
	}
	
	/**
	 * 获取产品ID方法
	 * 
	 * @return
	 */
	protected List<String> getProducts() {
	    List<Product> product =  productDao.getProducts();
	    List<String> list = new ArrayList<String>();
	    for (Product p : product) {
		list.add(p.getName());
	    }
	    return list;
	}
}
