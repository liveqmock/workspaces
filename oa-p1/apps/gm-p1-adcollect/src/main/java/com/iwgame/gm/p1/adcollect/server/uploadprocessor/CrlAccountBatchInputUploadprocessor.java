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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

import com.csvreader.CsvReader;
import com.iwgame.gm.p1.adcollect.server.service.BaseService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.BlackUsers;
import com.iwgame.xmvp.server.fileupload.FileProcessor;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： 黑名单账户号批量导入
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-8-20 下午06:11:44
 */
@NeedAuthorization
public class CrlAccountBatchInputUploadprocessor extends BaseService implements FileProcessor {

	private static final String NAMESPACE = "adcollect.crl.";
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger
			.getLogger(CrlAccountBatchInputUploadprocessor.class);
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_CRL, LoggerConstants.APPNAME);

	@Override
	@AccessResource(name = "ad-crl-batchAdd")
	public void processUploadedFile(Map<String, String> parameters, List<FileItem> files) {
		
		InputStream inputStream = null;
		
		try {
			// 获取参数
			final String productId = parameters.get("hiddenField_productId");
			// 读取
			inputStream = getInputStream(files);
			if (inputStream != null) {
				// 解析CSV
				final List<BlackUsers> blackUserList = readCSVFile(productId, getInputStream(files));
				BlackUsers user = null;
				// 入库
				int num = 0;
				for (int i = 0; i < blackUserList.size(); i++) {
					user = blackUserList.get(i);
					if (user!=null) {
						final Map<String, Object> parameter = new HashMap<String, Object>();
						parameter.put("userName", user.getUserName());
						parameter.put("regTime", user.getRegTime());
						parameter.put("regIp", user.getRegIp());
						parameter.put("regEmail", user.getRegEmail());
						parameter.put("idCard", user.getIdCard());
						parameter.put("sourceType", user.getSourceType());
						parameter.put("sourceId", user.getSourceId());
						parameter.put("loaction", user.getLoaction());
						parameter.put("reason", user.getReason());
						
						final Integer totalCount = insert(productId, NAMESPACE + "batchAddBlackUsers", parameter);
						if (totalCount > 0) {
							num++;  
						}
						logger.writeSuccessfullyLog(LoggerConstants.ACTION_CRLACCOUNTBATCHINPUT, "批量导入黑名单成功,共导入"+ num + " 条数据");
					}else {
						//记录出错
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_CRLACCOUNTBATCHINPUT, e);
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger4j.error(e);
					logger.writeFailedLog(LoggerConstants.ACTION_CRLACCOUNTBATCHINPUT, e);
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
	 * @param productId
	 * @param in
	 * @return
	 */
	private List<BlackUsers> readCSVFile(String productId, InputStream in) {
		// 统计上传CSV文件的列数
		int columnCount = 0;
		// 用于存放CSV文件的List
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		final List<BlackUsers> blackUsersList = new ArrayList<BlackUsers>();
	    List<String> usernameList = new ArrayList<String>();
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

			int errorRecords = 0;
			int repeatRecords = 0;
			BlackUsers users = null;
			for (int row = 0; row < csvList.size(); row++) {
				users = new BlackUsers();
				for (int i = 0; i < columnCount; i++) {
					// 读取列
					final String cell = csvList.get(row)[i];

					if (i == 0) {
						// 账号名称
						if (StringUtils.isEmpty(cell)) {
							break;
						} else if (usernameList.contains(cell)) {
							repeatRecords++;
							break;
						}else{
							usernameList.add(cell);
							users.setUserName(cell);
							users.setReason("3");
						}
					} else if (i == 1) {
						// 注册时间
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							users.setRegTime(string2Date(cell));
						}
					}else if (i == 2) {
						// 注册IP
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							users.setRegIp(cell);
						}
					} else if (i == 3) {
						// 身份证
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							users.setIdCard(cell);
						}
					} else if (i == 4) {
						// 邮箱
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							users.setRegEmail(cell);
						}
					} else if (i == 5) {
						// 来源类型
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							users.setSourceType(cell);
						}
					} else if (i == 6) {
						// 来源ID
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							users.setSourceId(cell);
						}
					} else if (i == 7) {
						// 来源URL
						if (StringUtils.isEmpty(cell)) {
							break;
						} else {
							users.setLoaction(cell);
							users.setFull(true);
						}
					} 
					
				}
				if (users.isFull()) {
					blackUsersList.add(users);
				}else {
					errorRecords++;
				}
			}
			usernameList = null;
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_READCSVFILE, " 无效数据：" + errorRecords + " 条, 重复数据为 "+ repeatRecords +" 条,实际数据为："+ (csvList.size() - errorRecords) + " 条.");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_READCSVFILE, e);
			csvList = null;
		}finally{
			csvList = null;
		}
		return blackUsersList;
	}

	/**
	 * 时间转日期
	 * 
	 * @param datetime
	 * @return
	 * @throws ParseException 
	 */
	private Date string2Date(String datetime) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.parse(datetime);
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
