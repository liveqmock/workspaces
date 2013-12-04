/**      
* DangerIdCardFileUploadHandler.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.server.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


import com.iwgame.gm.p1.security.common.server.util.RegexHelper;
import com.iwgame.gm.p1.security.common.shared.bean.GlobalResource;
import com.iwgame.gm.p1.security.common.shared.model.DangerIdCard;
import com.iwgame.gm.p1.security.modules.manage.shared.rpc.SecurityDangerIdCardService;
import com.iwgame.xmvp.server.fileupload.FileProcessor;
import com.iwgame.xmvp.server.fileupload.FileUploadMessages;
/** 
 * @简述: 高危身份证批量上传处理
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-19 下午05:55:56 
 */
public class DangerIdCardUploadHandler implements FileProcessor,FileUploadMessages{
	
	@Resource(name="securityDangerIdCardServiceImpl")
	private SecurityDangerIdCardService dangerIdCardServiceImpl;
	
	@Resource(name="globalResource")
	private GlobalResource resource;
	
	private final Logger log4j = Logger.getLogger(DangerIdCardUploadHandler.class);
	
	private String messages;
	@Override
	public void processUploadedFile(Map<String, String> parameters,
			List<FileItem> files) {
		InputStream is = getInputStream(files);
		if (is!=null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(is,"GBK"));
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}
			String line = null;
			List<DangerIdCard> idCards = new ArrayList<DangerIdCard>();
			StringBuilder failLinesNum = new StringBuilder();
			int totalCount = 0;
			int failCount = 0;
			try {
				while ((line=reader.readLine())!=null) {
					totalCount++;
					if (line.length()>0) {		//过滤空行
							String[] infos = StringUtils.split(line, "\t");//按tab分割
							String idCard = infos[0];
							String causeNote=null;
							if (infos.length==2) {//处理第2列
								causeNote = infos[1];
							}
							if (RegexHelper.isIdCard(idCard)) {//身份证格式校验
								DangerIdCard dangerIdCard = new DangerIdCard();
								dangerIdCard.setIdCard(idCard);
								dangerIdCard.setCauseNote(causeNote);
								idCards.add(dangerIdCard);
							}else {
								failCount++;
								failLinesNum.append(totalCount);
								failLinesNum.append(",");
							}
					}
				}
				String productId = parameters.get("pid") ;
				int size = idCards.size();
				boolean success = dangerIdCardServiceImpl.addDangerIdCard(productId,idCards);
				StringBuilder msg = new StringBuilder();
				msg.append("共上传");
				msg.append(totalCount);
				msg.append("行.");
				if (success) {
					msg.append("成功导入:");
					msg.append(size);
					msg.append("行.");
				}
				if (failCount!=0) {
					msg.append("第");
					msg.append(failLinesNum.toString().substring(0, failLinesNum.length()-1));
					msg.append("行身份证格式错误");
				}
				messages = msg.toString();
			} catch (IOException e) {
				log4j.error("高危身份证批量导入导入失败,", e);
				messages = "导入失败,"+e.getCause().getMessage();
			} catch (Exception e) {
				log4j.error("高危身份证批量导入导入失败,", e);
				messages = "导入失败,"+e.getCause().getMessage();
			}finally{
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
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
		try {
			for (final FileItem fileItem : files) {
				if (fileItem.isFormField()==false) {// 如果该FileItem是文件域
						long size = fileItem.getSize();
						long oneMB = resource.getFileMaxSize()*1024*1024;
						if (size>oneMB) {
							messages="批量上传的txt文件不能大于"+resource.getFileMaxSize()+"MB.";
							return null;
						}
						inputStream = fileItem.getInputStream();// 获得输入数据流文件
						return inputStream;
				}
			}
		} catch (final IOException e1) {
			log4j.error("高危身份证批量导入导入失败,", e1);
		}
		return null;
	}

	@Override
	public String getProcessMessage() {
		
		return messages;
	}
}
