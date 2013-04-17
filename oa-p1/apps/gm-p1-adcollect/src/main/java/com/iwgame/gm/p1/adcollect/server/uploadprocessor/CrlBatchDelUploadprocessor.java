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
import com.iwgame.xmvp.server.fileupload.FileProcessor;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： 黑名单账户号批量删除
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-8-20 下午06:11:44
 */
@NeedAuthorization
public class CrlBatchDelUploadprocessor extends BaseService implements FileProcessor {

	private static final String NAMESPACE = "adcollect.crl.";
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger
			.getLogger(CrlBatchDelUploadprocessor.class);
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_CRL, LoggerConstants.APPNAME);

	@Override
	@AccessResource(name = "ad-crl-batchDel")
	public void processUploadedFile(Map<String, String> parameters, List<FileItem> files) {
		
		InputStream inputStream = null;
		
		try {
			// 获取参数
			final String productId = parameters.get("hiddenField_productId");
			// 读取
			inputStream = getInputStream(files);
			int num = 0;
			if (inputStream != null) {
				// 解析CSV
				final List<String> userNameList = readCSVFile(productId, getInputStream(files));
				// 入库
				for (int i = 0; i < userNameList.size(); i++) {
					final Map<String, String> parameter = new HashMap<String, String>();
					parameter.put("username", userNameList.get(i));
					final Integer totalCount = insert(productId, NAMESPACE + "batchDelUsername", parameter);
					// 写入操作日志表
					if (totalCount > 0) {
						num++;
					}
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_CRLBATCHDEL, "批量删除黑名单列表成功,共删除 "+ num + " 条数据");
			}
		} catch (final Exception e) {
			e.printStackTrace();
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_CRLBATCHDEL, e);
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger4j.error(e);
					logger.writeFailedLog(LoggerConstants.ACTION_CRLBATCHDEL, e);
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
	 * 解析CSV文件
	 * 
	 * @param productId
	 * @param in
	 * @return
	 */
	private List<String> readCSVFile(String productId, InputStream in) {
		// 统计上传CSV文件的列数
		int columnCount = 0;
		// 用于存放CSV文件的List
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		final List<String> accountList = new ArrayList<String>();
		try {
			final CsvReader reader = new CsvReader(in, Charset.forName("gbk"));
			// 读取CSV文件的表头，如果CSV文件没有表头则可以注释 掉
//			reader.readHeaders();
			while (reader.readRecord()) {
				columnCount = reader.getColumnCount();
				csvList.add(reader.getValues());
			}
			reader.close();

			// 遍历CSV文件中的信息
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_READCSVFILE, "-共有   " + csvList.size() + " 条数据准备导入...");
			int errorRecords = 0;//错误记录条数
			int repeatRecords = 0;//重复记录条数
			
			String accout = null;//账号名称
			
			for (int row = 0; row < csvList.size(); row++) {
				for (int i = 0; i < columnCount; i++) {
					// 读取列
					final String cell = csvList.get(row)[i];
					// 账号列表
					if (i == 0) {
						// 邮箱
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							accout = cell;	
						}
						// 加入存储list
						if (!"".equals(accout) && accout != null) {
						    //数据是否重复
							if (!accountList.contains(accout)) {
						    	accountList.add(accout);
							}else {
								repeatRecords++;
							}  
						} else {
							errorRecords++;
						}

					} else {
						//有错记录日志
						logger.writeFailedLog(LoggerConstants.ACTION_READCSVFILE, "账号列表CSV文件有错");
					}
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_READCSVFILE, " 无效数据：" + errorRecords + " 条,重复数据："+repeatRecords+" 条,实际数据为："
						+ (csvList.size() - errorRecords) + " 条.");
			}
		} catch (final Exception e) {
			csvList = null;
		}finally{
			csvList = null;
		}
		return accountList;
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
