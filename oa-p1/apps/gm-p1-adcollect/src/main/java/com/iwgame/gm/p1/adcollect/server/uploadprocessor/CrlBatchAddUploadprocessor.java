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

import com.csvreader.CsvReader;
import com.iwgame.gm.p1.adcollect.server.service.BaseService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.BlackListManage;
import com.iwgame.xmvp.server.fileupload.FileProcessor;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-8-20 下午06:11:44
 */
@NeedAuthorization
public class CrlBatchAddUploadprocessor extends BaseService implements FileProcessor {

	private static final String NAMESPACE = "adcollect.crl.";
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger
			.getLogger(CrlBatchAddUploadprocessor.class);
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_CRL, LoggerConstants.APPNAME);

	@Override
	@AccessResource(name = "ad-keys-crlbatchinput")
	public void processUploadedFile(Map<String, String> parameters, List<FileItem> files) {
		
		InputStream inputStream = null;
		
		try {
			// 获取参数
			final String productId = parameters.get("hiddenField_productId");
			// 读取
			inputStream = getInputStream(files);
			if (inputStream != null) {
				// 解析CSV
				final List<BlackListManage> blackListManages = readCSVFile(productId, getInputStream(files));
				// 入库
				for (int i = 0; i < blackListManages.size(); i++) {
					final Map<String, String> parameter = new HashMap<String, String>();
					parameter.put("type", blackListManages.get(i).getManageType());
					if (blackListManages.get(i).getManageText().length() != 40) {
						parameter.put("keyword", blackListManages.get(i).getManageText().toLowerCase());
					} else if (blackListManages.get(i).getManageText().contains("@")) {
						parameter.put("keyword", blackListManages.get(i).getManageText().toLowerCase());
					} else {
						parameter.put("SHA1keyword", blackListManages.get(i).getManageText());
					}

					final Integer totalCount = insert(productId, NAMESPACE + "insertBlackManage", parameter);

					// 写入操作日志表
					if (totalCount > 0) {
					}
					/*
					 * logger.writeSuccessfullyLog(LoggerConstants.
					 * ACTION_INSERTADINFO, "增加广告列表成功"); } catch (Exception e) {
					 * logger4j.error(e);
					 * logger.writeFailedLog(LoggerConstants.ACTION_INSERTADINFO
					 * , e); }
					 */
					logger.writeSuccessfullyLog(LoggerConstants.ACTION_INSERTADINFO, "增加广告列表成功");
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_INSERTADINFO, e);
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger4j.error(e);
					logger.writeFailedLog(LoggerConstants.ACTION_INSERTADINFO, e);
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
	private List<BlackListManage> readCSVFile(String productId, InputStream in) {
		// 统计上传CSV文件的列数
		int columnCount = 0;
		// 用于存放CSV文件的List
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		final List<BlackListManage> blackList = new ArrayList<BlackListManage>();
		try {
			final CsvReader reader = new CsvReader(in, Charset.forName("gbk"));
			// 读取CSV文件的表头，如果CSV文件没有表头则可以注释 掉
			reader.readHeaders();
			while (reader.readRecord()) {
				columnCount = reader.getColumnCount();
				csvList.add(reader.getValues());
			}
			reader.close();

			// 遍历CSV文件中的信息
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_READCSVFILE, "-共有   " + csvList.size() + " 条数据准备导入...");
			int errorRecords = 0;
			BlackListManage blackEmail = null;
			BlackListManage blackIdcard = null;
			for (int row = 0; row < csvList.size(); row++) {
				for (int i = 0; i < columnCount; i++) {
					// 读取列
					final String cell = csvList.get(row)[i];
					// 邮箱 身份证
					if (i == 0) {
						blackEmail = new BlackListManage();
						// 邮箱
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							blackEmail.setManageType("1");
							blackEmail.setManageText(cell);
							blackEmail.setFull(true);
						}
						// 加入存储list
						if (blackEmail.isFull()) {
							blackList.add(blackEmail);
						} else {
							errorRecords++;
						}

					} else if (i == 1) {
						// 身份证
						blackIdcard = new BlackListManage();
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							blackIdcard.setManageType("2");
							blackIdcard.setManageText(cell);
							blackIdcard.setFull(true);
						}
						// 加入存储list
						if (blackIdcard.isFull()) {
							blackList.add(blackIdcard);
						} else {
							errorRecords++;
						}
					}
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_READCSVFILE, " 无效数据：" + errorRecords + " 条, 实际数据为："
						+ (csvList.size() - errorRecords) + " 条.");
			}
		} catch (final Exception e) {
			csvList = null;
		}finally{
			csvList = null;
		}
		return blackList;
	}

	/*
	 * @AccessResource(name = "ad-keys-getLog") public Integer
	 * addLog(Map<String, Object> paramMap) throws AccessDeniedException {
	 * Integer i = 0; try { String productId =
	 * String.valueOf(paramMap.get("productId")); String insertUser =
	 * SecurityUserHolder.getCurrentUser().getUsername();
	 * paramMap.put("insertUser", insertUser); Integer totalCount =
	 * insert(productId, NAMESPACE+ "addLog", paramMap); i = totalCount;
	 * logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADDLOG, "增加日志成功"); }
	 * catch (Exception e) { e.printStackTrace(); logger4j.error(e);
	 * logger.writeFailedLog(LoggerConstants.ACTION_ADDLOG, e); } return i; }
	 */

}
