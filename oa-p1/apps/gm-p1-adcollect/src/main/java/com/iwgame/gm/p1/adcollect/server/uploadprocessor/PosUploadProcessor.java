/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： PosUploadProcessor.java
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

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.adcollect.server.OperatorLogger;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.PositionDateBase;
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
 * @简述： 保存广告位
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-8 下午04:44:55
 */
// @NeedAuthorization
public class PosUploadProcessor extends AbstractMediaFileProcessor {
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_UPLOAD_POS, LoggerConstants.APPNAME);
	private static final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(PosUploadProcessor.class);
	private static final String AHA = "ad.hard.admin.";

	private ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	private OperatorLogger businessLogger;

	@Autowired
	public void setBusinessLogger(final OperatorLogger businessLogger) {
		this.businessLogger = businessLogger;
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
	// @AccessResource(name = "ad-hard-admin-pos-uploadfile")
	protected void postUploadFile() {
		// 在这里 写 保存数据

		final Map<String, Object> parameter = new HashMap<String, Object>();
		final Map<String, Media> media = getFiles();

		final String productId = getCommonFields().get("productId").toString();
		final String operation = getCommonFields().get("operation").toString();
		if ((null != media) && !media.isEmpty()) {
			parameter.put("legend", media.get("legend").getUrl());
			parameter.put("width", media.get("legend").getWidth());
			parameter.put("height", media.get("legend").getHeight());
			final long size = media.get("legend").getSize();
			parameter.put("capacity", Integer.parseInt(size + ""));
			parameter.put("contentType", media.get("legend").getContentType());
		}

		// parameter.put("adds", getCommonFields().get("adds"));
		parameter.put("generalPrice", getCommonFields().get("generalPrice"));
		parameter.put("specialPrice", getCommonFields().get("specialPrice"));
		parameter.put("format", getCommonFields().get("format").toString());
		parameter.put("explain", getCommonFields().get("explain").toString());
		parameter.put("remark", getCommonFields().get("remark").toString());
		parameter.put("units", getCommonFields().get("units"));
		parameter.put("adds", getCommonFields().get("adds"));
		String adType = getCommonFields().get("adType");
		if (null != adType) {
			parameter.put("adType", Integer.parseInt(adType));
		}

		if (operation.equals("add")) {// 添加
			analyticParametersAdd(parameter, productId);
		}

		final PositionDateBase newBase = new PositionDateBase();
		if (operation.equals("update")) {// 修改.
			updatePos(parameter, media, productId, newBase);
		}

		super.postUploadFile();
	}

	// @AccessResource(name = "ad-hard-admin-update-pos")
	private void updatePos(final Map<String, Object> parameter, final Map<String, Media> media, final String productId,
			final PositionDateBase newBase) {

		final String id = getCommonFields().get("id");
		newBase.setId(id);
		parameter.put("id", id);

		// 原来的数据selectOne
		final PositionDateBase oldBase = (PositionDateBase) getSqlSessionTemplate(productId).selectOne(
				AHA + "getPositionById", parameter);

		List<String> list = getProducts();
		for (String product : list) {
			try {
				// 更新 数据
				final Integer count = getSqlSessionTemplate(product).update(AHA + "updatePosition", parameter);
				if (null !=media.get("legend") && null != media.get("legend").getUrl()) {

					newBase.setLegend(media.get("legend").getUrl());
				} else {
					newBase.setLegend(oldBase.getLegend());
				}
				if (null != getCommonFields().get("generalPrice")) {
					newBase.setGeneralPrice(Double.parseDouble(getCommonFields().get("generalPrice")));
				} else {
					newBase.setGeneralPrice(oldBase.getGeneralPrice());
				}
				if (null != getCommonFields().get("specialPrice")) {
					newBase.setSpecialPrice(Double.parseDouble(getCommonFields().get("specialPrice")));
				} else {
					newBase.setSpecialPrice(oldBase.getSpecialPrice());
				}
				if (null != getCommonFields().get("format")) {
					newBase.setFormat(getCommonFields().get("format").toString());
				} else {
					newBase.setFormat(oldBase.getFormat());
				}
				if (null != getCommonFields().get("explain")) {
					newBase.setExplain(getCommonFields().get("explain").toString());
				} else {
					newBase.setExplain(oldBase.getExplain());
				}
				if (null != getCommonFields().get("remark")) {
					newBase.setRemark(getCommonFields().get("remark").toString());
				} else {
					newBase.setRemark(oldBase.getRemark());
				}
				if (null != getCommonFields().get("units")) {
					newBase.setUnits(Integer.parseInt(getCommonFields().get("units")));
				} else {
					newBase.setUnits(oldBase.getUnits());
				}
				if (null != getCommonFields().get("adds")) {
					newBase.setAdds(getCommonFields().get("adds").toString());
				} else {
					newBase.setAdds(oldBase.getAdds());
				}
				if (count == 1) {
					businessLogger.writeModifyLog(product, oldBase, newBase);
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_POSTION, "产品" + product + "修改广告位成功");
			} catch (Exception e) {
				logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_POSTION, "产品" + product + "修改广告位失败" + e);
			}

		}
	}

	/**
	 * 解析参数 调用添加 方法
	 */
	// @AccessResource(name = "ad-hard-admin-add-pos")
	@SuppressWarnings({ "unchecked" })
	private void analyticParametersAdd(final Map<String, Object> parameter, final String productId) {
		try {
			String mediaId = getCommonFields().get("media");
			parameter.put("mediaId", getCommonFields().get("media"));
			parameter.put("name", getCommonFields().get("name"));

			// 查询上一条 添加的数据 的 ID 和 Key
			Map<String, String> beforeCode = (Map<String, String>) getSqlSessionTemplate(productId).selectOne(
					AHA + "getPositionMaxId", mediaId);
			String id = ""; // 自增的ID
			String key = ""; //  
			// 表中最大的自增ID
			Integer beforeId = (Integer) getSqlSessionTemplate(productId).selectOne(AHA + "getPositionMaxPosId");
			if (beforeId != null){
				id = (beforeId+1)+"";
			}else{
				id = "1";
			}

			if(beforeCode == null){ // 次媒体下还没有广告位
				key = "001";

				addPos(parameter, id, key);
			}
			// 存在媒体次媒体下已经存在了广告位
			if (beforeCode != null) { 
				String beforeKey = beforeCode.get("key");

				if (beforeId != null && beforeKey != null) { 			
					Integer intBeforeKey = Integer.parseInt(beforeKey);
					key = StringUtils.leftPad(Integer.toString((intBeforeKey == null ? 0 : intBeforeKey) + 1), 3, '0');

					addPos(parameter, id, key);
				} 
			} 

		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_POSTION, "pos_id或者pos_key解析错误" + e);
		}
	}

	/**
	 * 添加方法
	 */
	private void addPos(final Map<String, Object> parameter, String id, String key) {
		// 生成 广告ID
		final String adid = getCommonFields().get("adid");
		parameter.put("id", id);
		parameter.put("key", key);
		if (null == adid) {
			parameter.put("adid", getCommonFields().get("media") + key);
		} else {
			parameter.put("adid", adid);
		}

		// 获取产品ID方法
		List<String> list = getProducts();
		// 循环插入
		for (String product : list) {
			try {
				getSqlSessionTemplate(product).insert(AHA + "addPosition", parameter);
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_POSTION, "产品" + product + "增加广告位成功");
				final PositionDateBase base = new PositionDateBase();
				base.setId(id);
				businessLogger.writeCreateLog(product, base);
			} catch (Exception e) {
				logger4j.error(e);
				logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_POSTION, "产品" + product + "增加广告为失败" + e);
			}
		}
	}

	/**
	 * 获取产品ID方法
	 * 
	 * @return
	 */
	protected List<String> getProducts() {
		List<Product> product = productDao.getProducts();
		List<String> list = new ArrayList<String>();
		for (Product p : product) {
			list.add(p.getName());
		}
		return list;
	}

}
