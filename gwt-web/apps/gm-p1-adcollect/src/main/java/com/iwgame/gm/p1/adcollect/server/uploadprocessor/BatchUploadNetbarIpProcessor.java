/**      
 * BatchUploadNetbarIpProcessor.java Create on 2012-11-9 上午11:42:37    
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
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

import com.csvreader.CsvReader;
import com.iwgame.gm.p1.adcollect.server.service.BaseService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.NetbarIpDataBase;
import com.iwgame.xmvp.server.fileupload.FileProcessor;
import com.iwgame.xmvp.server.fileupload.FileUploadMessages;
import com.iwgame.xportal.common.server.dao.ProductDao;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.shared.model.Product;

/**
 * @ClassName: 批处理媒体对应IP
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-9 上午11:42:37
 * @Version 1.0
 * 
 */
public class BatchUploadNetbarIpProcessor extends BaseService implements FileProcessor, FileUploadMessages {

	private static final String AHA = "ad.hard.admin.";
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_UPLOAD_IP, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger
			.getLogger(BatchUploadNetbarIpProcessor.class);

	private ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 返回客户端的信息
	private String resultMessage;

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xmvp.server.fileupload.FileProcessor#processUploadedFile(java
	 * .util.Map, java.util.List)
	 */
	@Override
	public void processUploadedFile(Map<String, String> parameters, List<FileItem> files) {
		InputStream inputStream = null;
		try {
			// 获取产品ID
			final String productId = getProducts().get(0);
			int columnCount = 0;
			// 获取参数
			// 读取
			inputStream = getInputStream(files);
			if (inputStream != null) {
				// 解析CSV
				final List<NetbarIpDataBase> bases = readCSVFile(productId, getInputStream(files));
				// 入库
				for (int i = 0; i < bases.size(); i++) {
					final Map<String, String> paramMap = new HashMap<String, String>();

					paramMap.put("mediaId", bases.get(i).getMediaId());
					paramMap.put("ip", bases.get(i).getIp());

					try {
						List<String> list = getProducts();
						for (String product : list) {
							insert(product, AHA + "addNetbarIp", paramMap);
						}
					} catch (final Exception e) {
						logger4j.error(e);
						columnCount++;
					}
				}
				setResultMessage("批量导入媒体对应的IP : 成功--" + (bases.size() - columnCount) + "-条数据;失败--" + columnCount
						+ "-条数据[失败包括重复的数据]");
				logger4j.info("批量导入媒体对应的IP : 成功--" + (bases.size() - columnCount) + "-条数据;失败--" + columnCount
						+ "-条数据[失败包括重复的数据]");
			}
			logger.writeSuccessfullyLog(LoggerConstants.APPUNITNAME_UPLOAD_IP_ADD_BATCH, "批量添加IP成功");
		} catch (final Exception e) {
//			e.printStackTrace();
			logger4j.error(LoggerConstants.APPUNITNAME_UPLOAD_IP_ADD_BATCH, e);
			logger.writeFailedLog(LoggerConstants.APPUNITNAME_UPLOAD_IP_ADD_BATCH, e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(LoggerConstants.APPUNITNAME_UPLOAD_IP_ADD_BATCH, e);
				}
			}
		}
	}

	/**
	 * @param files
	 * @return
	 */
	private InputStream getInputStream(List<FileItem> files) {
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
	private List<NetbarIpDataBase> readCSVFile(final String productId, final InputStream in) {
		// 统计上传CSV文件的列数
		int columnCount = 0;
		// 用于存放CSV文件的List
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		final List<NetbarIpDataBase> resouceList = new ArrayList<NetbarIpDataBase>();
		int errorRecords = 0;
		try {
			final CsvReader reader = new CsvReader(in, Charset.forName("gbk"));
			// 读取CSV文件的表头，如果CSV文件没有表头则可以注释 掉
			while (reader.readRecord()) {
				columnCount = reader.getColumnCount();
				csvList.add(reader.getValues());
			}
			reader.close();

			// 遍历CSV文件中的信息
			NetbarIpDataBase dataBase = null;
			for (int row = 0; row < csvList.size(); row++) {
				// INIT
				dataBase = new NetbarIpDataBase();

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
							String mediaId = selectOne(productId, AHA + "getMediaIdByName", paramMap);
							if (null != mediaId && mediaId.length() > 0) {
								mediaId = StringUtils.leftPad(mediaId, 4, '0');
								dataBase.setMediaId(mediaId);
							} else {
								break;
							}
						}
					} else if (i == 1) {
						// IP
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							dataBase.setIp(cell);
							dataBase.setFull(true);
						}
					}
				}
				if (dataBase.isFull()) {
					resouceList.add(dataBase);
				} else {
					errorRecords++;
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			csvList = null;
		}
		logger4j.info("解析csv文件  无效数据：" + errorRecords + " 条, 实际数据为：" + (csvList.size() - errorRecords) + " 条.");
		return resouceList;
	}

	/**
	 * 获取所有产品ID方法
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

	/**
	 * 上传任务完成后 返回客户端的信息
	 */
	@Override
	public String getProcessMessage() {
		return getResultMessage();
	}
}
