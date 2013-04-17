/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： DataDictionaryUploadProcessor.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.uploadprocessor;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xmvp.server.fileupload.AbstractMediaFileProcessor;
import com.iwgame.xmvp.server.fileupload.FileProcessor;
import com.iwgame.xmvp.server.fileupload.FileUploadMessages;
import com.iwgame.xmvp.shared.Media;
import com.iwgame.xportal.common.server.log.Logger;

/**
 * @Description: 上传数据字典服务
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-20 下午12:10:29
 */
public class DataDictionaryUploadProcessor extends AbstractMediaFileProcessor implements FileProcessor,
		FileUploadMessages {

	private static final String AE = "ad.explain.";
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_UPLOAD_DICTIONARY,
			LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger
			.getLogger(DataDictionaryUploadProcessor.class);

	// 返回客户端的信息
	private String resultMessage;

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
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

	/**
	 * 返回 客户端的提示
	 */
	@Override
	public String getProcessMessage() {
		return getResultMessage();
	}

	@Override
	protected void postUploadFile() {
		setResultMessage("0");
		final Map<String, Media> media = getFiles();
		try {
			// 在这里 写 保存数据
			final Map<String, Object> parameter = new HashMap<String, Object>();
			final String productId = getCommonFields().get("productId").toString();
			final String name = getCommonFields().get("name").toString();
			parameter.put("name", name);
			final String function = getCommonFields().get("function").toString();
			parameter.put("function", function);
			// 获取 上传对象
			if ((null != media) && !media.isEmpty()) {
				// 获取上传的文件 路径以及文件名称
				parameter.put("dictionary", media.get("dictionary").getUrl());
			}
			Integer result = getSqlSessionTemplate(productId).insert(AE + "insertDataDictionary", parameter);
			setResultMessage(result +"");
			logger.writeSuccessfullyLog(LoggerConstants.APPUNITNAME_UPLOAD_DICTIONARY_EXPORT, "数据字典上传成功");
		} catch (Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.APPUNITNAME_UPLOAD_DICTIONARY_EXPORT, e);
		}
		super.postUploadFile();
	}
}
