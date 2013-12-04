/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MaterialUploadProcessor.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.uploadprocessor;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xmvp.server.fileupload.AbstractMediaFileProcessor;
import com.iwgame.xmvp.shared.Media;
import com.iwgame.xportal.common.server.log.Logger;

/**
 * 类说明
 * 
 * @简述： 保存素材
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-27 下午12:28:33
 */
//@NeedAuthorization
public class MaterialUploadProcessor extends AbstractMediaFileProcessor {

	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_UPLOAD_MATERIAL, LoggerConstants.APPNAME);
	private static final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(MaterialUploadProcessor.class);

	private static final String AHA = "ad.hard.admin.";

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xmvp.server.fileupload.AbstractMediaFileProcessor#postUploadFile
	 * ()
	 */
	@Override
//	@AccessResource(name = "ad-hard-admin-material-add")
	protected void postUploadFile() {
		final Map<String, Object> parameter = new HashMap<String, Object>();
		final Map<String, Media> media = getFiles();
		parameter.put("name", getCommonFields().get("name").toString());
		parameter.put("type", Integer.parseInt(getCommonFields().get("type")));
		// if(!getCommonFields().get("width").equals("")){
		// parameter.put("width",
		// Integer.parseInt(getCommonFields().get("width")));
		// }else
		if (media.get("uploadMaterial").getWidth() != 0) {
			parameter.put("width", media.get("uploadMaterial").getWidth());
		} else {
			parameter.put("width", 0);
		}
		// if(!getCommonFields().get("height").equals("")){
		// parameter.put("height",
		// Integer.parseInt(getCommonFields().get("height")));
		// }else
		if (media.get("uploadMaterial").getHeight() != 0) {
			parameter.put("height", media.get("uploadMaterial").getHeight());
		} else {
			parameter.put("height", 0);
		}
		parameter.put("note", getCommonFields().get("note").toString());
		parameter.put("generate", getCommonFields().get("generate").toString());
		parameter.put("url", media.get("uploadMaterial").getUrl());
		final long size = media.get("uploadMaterial").getSize();
		parameter.put("size", Integer.parseInt(size + ""));
		parameter.put("contentType", media.get("uploadMaterial").getContentType());

		insertMaterial(getCommonFields().get("hiddenField_productId").toString(), parameter);
		super.postUploadFile();
	}

	public int insertMaterial(final String productId, final Map<String, Object> parameter) {
		Integer result = 0;
		try {
			final String generate = StringUtils.leftPad(parameter.get("generate").toString(), 2, '0');
			final Map<String, String> p = new HashMap<String, String>();
			p.put("generate", generate);
			Integer priorId = (Integer) getSqlSessionTemplate(productId).selectOne(AHA + "getGenerateMaxId", p);
			String id = "";
			if (null != priorId) {
				id = Integer.toString((++priorId));
				parameter.put("id", StringUtils.leftPad(id, 6, '0'));
			} else {
				id = generate + StringUtils.leftPad("1", 4, '0');
				parameter.put("id", id);
			}

			result = getSqlSessionTemplate(productId).insert(AHA + "insertMaterial", parameter);
			logger.writeSuccessfullyLog(LoggerConstants.APPUNITNAME_UPLOAD_MATERIAL_ADD, "添加素材成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.APPUNITNAME_UPLOAD_MATERIAL_ADD, e);
			logger4j.error("素材上传后处理异常", e);
		}
		return result;
	}
}
