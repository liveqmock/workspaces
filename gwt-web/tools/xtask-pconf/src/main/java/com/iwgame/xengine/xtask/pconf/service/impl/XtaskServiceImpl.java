/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： XtaskServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.pconf.service.impl;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.pconf.service.ProductConfigurationService;
import com.iwgame.xengine.xtask.pconf.service.XtaskService;

/**
 * 类说明
 * 
 * @简述： XtaskService实现类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午06:01:16
 */
@Service
public class XtaskServiceImpl implements XtaskService {
	private Logger logger = Logger.getLogger(XtaskServiceImpl.class);
	
	private String pconfFileInfo = "";
	private ProductConfigurationService productConfiguration;
	
	public void setPconfFileInfo(String pconfFileInfo) {
		this.pconfFileInfo = pconfFileInfo;
	}

	public void setProductConfiguration(
			ProductConfigurationService productConfiguration) {
		this.productConfiguration = productConfiguration;
	}


	@Override
	public void makeFile() throws IOException {
		//生成策略文件
		logger.info("开始生成Pconf文件...");
		logger.info("文件路径:" + pconfFileInfo);
		
		//生成临时文件
		File tempFile = getTmpPconfFile();
		FileUtils.writeStringToFile(tempFile, getPconfContent(), "utf-8");
		logger.info("生成临时文件成功:" + tempFile.getAbsolutePath());
		
		//正式文件
		File pconfFile = getPconfFile();
		forceDeletePconfFile(pconfFile); //删除已经存在的文件
		boolean flag = tempFile.renameTo(pconfFile);
		if (flag) {
			logger.info("临时文件重命名成功。");
		}
		
		logger.info("完成生成！");
	}
	
	
	/**
	 * 获取区组内容
	 * @return
	 */
	private String getPconfContent() {
		JSONArray jsonArray = JSONArray.fromObject(productConfiguration.getAllProduct());
		return jsonArray.toString();
	}
	
	/**
	 * 获取临时文件 
	 * @return
	 */
	private File getTmpPconfFile() {
		File tmpFile = new File(pconfFileInfo + ".tmp");
		return tmpFile;
	}
	
	/**
	 * 获取文件
	 * @return
	 */
	private File getPconfFile() {
		File pconfFile = new File(pconfFileInfo);
		return pconfFile;
	}
	
	private void forceDeletePconfFile(File pconfFile) {
		try {
			if (pconfFile.exists()) {
				boolean flag = pconfFile.delete();
				if (flag) {
					logger.info("old pconf 删除成功!");
				} else {
					logger.info("old pconf 删除失败!");
				}
			}
		} catch (Exception e) {
			logger.info("old pconf 删除出错!" + e);
		}
	}
}
