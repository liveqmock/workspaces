/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： BatchAddUploadprocessor.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.uploadprocessor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.csvreader.CsvReader;
import com.iwgame.gm.p1.adcollect.server.OperatorLogger;
import com.iwgame.gm.p1.adcollect.server.service.BaseService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.xmvp.server.fileupload.FileProcessor;
import com.iwgame.xportal.common.server.SecurityUserHolder;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： 批量导入百度关键字
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-8-20 下午06:11:44
 */
@NeedAuthorization
public class BatchAddUploadprocessor extends BaseService implements FileProcessor {

	private static final String NAMESPACE = "adcollect.keys.";
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(BatchAddUploadprocessor.class);
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_KEYS, LoggerConstants.APPNAME);
	private OperatorLogger businessLogger;

	@Autowired
	public void setBusinessLogger(final OperatorLogger businessLogger) {
		this.businessLogger = businessLogger;
	}

	@Override
	@AccessResource(name = "ad-keys-batchInput")
	public void processUploadedFile(final Map<String, String> parameters, final List<FileItem> files) {
		InputStream inputStream = null;
		try {
			// 获取参数
			final String productId = parameters.get("hiddenField_productId");
			// 读取
			inputStream = getInputStream(files);
			if (inputStream != null) {
				// 解析CSV
				final List<AdvertisementInfo> userResouces = readCSVFile(productId, getInputStream(files));
				// 入库
				for (int i = 0; i < userResouces.size(); i++) {
					final Map<String, String> paramMap = new HashMap<String, String>();

					paramMap.put("keyword", userResouces.get(i).getKeyword());
					paramMap.put("type", userResouces.get(i).getType());
					paramMap.put("mark", userResouces.get(i).getMark());
					paramMap.put("media", userResouces.get(i).getMedia());
					paramMap.put("link", userResouces.get(i).getUrl());

					String adid = "";
					try {
						// 先取得最大的广告id
						final int maxId = (Integer) selectOne(productId, NAMESPACE + "getAdid", paramMap);
						if (String.valueOf(maxId).length() > 6) {
							adid = String.valueOf(maxId + 1);
						} else {
							adid = paramMap.get("media") + String.valueOf(maxId + 1);
						}

						paramMap.put("adid", adid);
						final String link = paramMap.get("link") + "?stat=" + adid;
						paramMap.put("link", link);
						final Integer totalCount = insert(productId, NAMESPACE + "insertAdInfo", paramMap);
						if (totalCount > 0) {
							/*
							 * Map<String,Object> logMap = new HashMap<String,
							 * Object>(); logMap.put("note", "创建媒体");
							 * logMap.put("relId", adid);
							 * logMap.put("productId", productId);
							 */
							final AdvertisementInfo info = new AdvertisementInfo();
							info.setId(adid);
							businessLogger.writeCreateLog(productId, info);
							// addLog(logMap);
						}
						logger.writeSuccessfullyLog(LoggerConstants.ACTION_INSERTADINFO, "增加广告列表成功");
					} catch (final Exception e) {
						logger4j.error(e);
						logger.writeFailedLog(LoggerConstants.ACTION_INSERTADINFO, e);
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}finally{
			if (inputStream !=null) {
				try {
					inputStream.close();
				} catch (Exception e2) {
					logger4j.error(e2);
					logger.writeFailedLog(LoggerConstants.ACTION_INSERTADINFO, e2);
				}
			}
		}

	}

	/**
	 * 读取文件(目前的业务是只有一条记录)
	 * 
	 * @param files
	 * @return
	 */
	private InputStream getInputStream(final List<FileItem> files) {
		InputStream inputStream = null;
		for (final FileItem fileItem : files) {
			if (!fileItem.isFormField()) {// 如果该FileItem不是表单域
				try {
					inputStream = fileItem.getInputStream();// 获得输入数据流文件
					return inputStream;
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 分析上传的文件
	 * 
	 * @param in
	 *            文件流内容
	 * @param type
	 *            类型:0:非vip;1:vip
	 * @param source
	 *            来源:
	 * @param operater
	 *            操作者:
	 * @return
	 */
	private List<AdvertisementInfo> readCSVFile(final String productId, final InputStream in) {
		// 统计上传CSV文件的列数
		int columnCount = 0;
		// 用于存放CSV文件的List
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		final List<AdvertisementInfo> resouceList = new ArrayList<AdvertisementInfo>();
		try {
			final CsvReader reader = new CsvReader(in, Charset.forName("gbk"));
			// 读取CSV文件的表头，如果CSV文件没有表头则可以注释 掉
			// reader.readHeaders();
			while (reader.readRecord()) {
				columnCount = reader.getColumnCount();
				csvList.add(reader.getValues());
			}
			reader.close();

			// 遍历CSV文件中的信息
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_READCSVFILE, "-共有   " + csvList.size() + " 条数据准备导入...");
			int errorRecords = 0;
			AdvertisementInfo adInfo = null;
			for (int row = 0; row < csvList.size(); row++) {
				// INIT
				adInfo = new AdvertisementInfo();

				for (int i = 0; i < columnCount; i++) {
					// 读取列
					final String cell = csvList.get(row)[i];
					// 媒体名 关键字文本 关键词类型 关键词标识 关键词链接
					if (i == 0) {
						// 媒体名
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							// 查数据库 看是否存在该媒体名
							final Map<String, String> paramMap = new HashMap<String, String>();
							paramMap.put("mediaName", cell);
							final String mediaId = selectOne(productId, NAMESPACE + "getMediaId", paramMap);
							if ((mediaId != null) && !("").equals(mediaId)) {
								adInfo.setMedia(mediaId);
							} else {
								break;
							}
						}
					} else if (i == 1) {
						// 关键字文本
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							adInfo.setKeyword(cell);
						}
					} else if (i == 2) {
						// 关键词类型
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							adInfo.setType(cell);
						}
					} else if (i == 3) {
						// 关键词标识
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							adInfo.setMark(cell);
						}
					} else if (i == 4) {
						// 关键词链接
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							adInfo.setUrl(cell);
							adInfo.setFull(true);
						}
					}
				}
				if (adInfo.isFull()) {
					resouceList.add(adInfo);
				} else {
					errorRecords++;
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_READCSVFILE, " 无效数据：" + errorRecords + " 条, 实际数据为："
						+ (csvList.size() - errorRecords) + " 条.");
			}
		} catch (final Exception e) {
			csvList = null;
		}
		return resouceList;
	}

	@AccessResource(name = "ad-keys-getLog")
	public Integer addLog(final Map<String, Object> paramMap) throws AccessDeniedException {
		Integer i = 0;
		try {
			final String productId = String.valueOf(paramMap.get("productId"));
			final String insertUser = SecurityUserHolder.getCurrentUser().getUsername();
			paramMap.put("insertUser", insertUser);
			final Integer totalCount = insert(productId, NAMESPACE + "addLog", paramMap);
			i = totalCount;
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADDLOG, "增加日志成功");
		} catch (final Exception e) {
			e.printStackTrace();
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADDLOG, e);
		}
		return i;
	}

}
