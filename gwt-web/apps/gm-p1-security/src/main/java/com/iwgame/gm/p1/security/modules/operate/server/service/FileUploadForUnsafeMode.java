/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FileUploadForUnsafeMode.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.iwgame.gm.p1.security.common.server.dao.IwResultTrackDao;
import com.iwgame.gm.p1.security.common.server.dao.SafeModeRecordDao;
import com.iwgame.gm.p1.security.common.server.service.SolrHttpService;
import com.iwgame.gm.p1.security.common.server.util.GuidHelper;
import com.iwgame.gm.p1.security.common.shared.bean.GlobalResource;
import com.iwgame.gm.p1.security.common.shared.bean.RoleDocs;
import com.iwgame.gm.p1.security.common.shared.bean.RoleParam;
import com.iwgame.gm.p1.security.common.shared.model.IwResultTrack;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeRecord;
import com.iwgame.gm.p1.security.common.shared.util.RegexGwtHelper;
import com.iwgame.gm.p1.security.modules.operate.server.bean.ERData;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.SafeModeBean;
import com.iwgame.xmvp.server.fileupload.FileProcessor;
import com.iwgame.xmvp.server.fileupload.FileUploadMessages;

/**
 * 类说明
 * @简述： 解除安全模式处理bean
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午10:29:29
 */
public class FileUploadForUnsafeMode implements FileProcessor,FileUploadMessages{
	private Logger logger4j = Logger.getLogger(FileUploadForUnsafeMode.class);
	
	@Resource(name = "globalResource")
	private GlobalResource globalResource;

	@Resource(name = "iwResultTrackDaoImpl")
	private IwResultTrackDao iwResultTrackDao;
	
	@Resource(name = "safeModeRecordDaoImpl")
	private SafeModeRecordDao safeModeRecordDao;
	
	@Resource(name = "securityUnsafeModeErData")
	private ERData securityUnsafeModeErData;
	
	@Resource(name = "securityRabbitTemplate")
	private RabbitTemplate rabbitTmeplate;
	
	@Resource(name = "solrHttpServiceImpl")
	private SolrHttpService solrHttpService;
	
	private String resultStr = "";
	
	@Override
	public String getProcessMessage() {
		return resultStr;
	}

	@Override
	public void processUploadedFile(Map<String, String> parameters,
			List<FileItem> files) {
		List<String> dbids = this.getBbIdList( files);
		if(dbids!=null){
			if(dbids.size()>globalResource.getFileMaxLine()){
				resultStr = "批量账号上传数量不得超过"+globalResource.getFileMaxLine()+"个";
			}else{
				int count = 0;
				int delcount = 0;
				//批次号
				String batchid = GuidHelper.getBatchId();
				SafeModeRecord smr = new SafeModeRecord();
				RoleDocs docs = null;
				List<SafeModeBean> beans = new ArrayList<SafeModeBean>();
				RoleParam roleParam = new RoleParam();
				for(String dbid:dbids){
					if(dbid.matches(RegexGwtHelper.STR_NUM_)){
						roleParam.setProductId(parameters.get("pid"));
						roleParam.setDbid(dbid);
						docs = solrHttpService.getRoleName(roleParam);
						if(docs!=null){
							SafeModeBean bean = new SafeModeBean();
							bean.setPid(parameters.get("pid"));
							bean.setDbid(dbid);
							bean.setCausenote(parameters.get("causenote"));
							bean.setBatchid(batchid);
							bean.setAccountid(docs.getAccountid());
							bean.setGroupname(docs.getSvr());
							bean.setRolename(docs.getName());
							bean.setGuid(docs.getGuid());
							bean.setUsername(solrHttpService.getAccountNameByAccountId(parameters.get("pid"),docs.getAccountid()));
							
							smr.setId(0);
							smr.setRolename(bean.getRolename());
							smr.setDbid(bean.getDbid());
							smr.setOperator(parameters.get("operator"));
							smr.setModeType(2);
							smr.setCauseNote(bean.getCausenote());
							smr.setGroupname(bean.getGroupname());
							smr.setGuid(bean.getGuid());
							smr.setUsername(bean.getUsername());
							try {
								safeModeRecordDao.insert(parameters.get("pid"),smr);
								beans.add(bean);
								count++;
							} catch (Exception e) {
								logger4j.error("批量解除安全模式,DBID:"+dbid+"错误提示:"+e);
								delcount++;
							}
						}else{
							logger4j.error("获取角色信息返回空,DBID:"+dbid);
							delcount++;
						}
					}else {
						logger4j.error("dbid校验不合法,DBID:"+dbid);
						delcount++;
					}
				}
				if(count>0){
					IwResultTrack iwrt = new IwResultTrack();
					iwrt.setBatchid(batchid);
					iwrt.setTrackType(5);//1:封杀,2:冻结,3:解封,4:安全模式,5:解除安全模式
					iwrt.setSubmitNum(count);
					iwrt.setOperator(parameters.get("operator"));
					try {
						iwResultTrackDao.insert(parameters.get("pid"),iwrt);
					} catch (Exception e) {
						logger4j.error("批量解除安全模式追踪记录:"+e);
					}
				
					for(SafeModeBean safeModeBean:beans){
						rabbitTmeplate.convertAndSend(securityUnsafeModeErData.getExchangeName(),
								securityUnsafeModeErData.getRoutinKeyName(),
								JSONObject.fromObject(safeModeBean).toString());
					}
				}
				beans.clear();
				resultStr = "成功提交解除安全模式账号"+count+"条,忽略"+delcount+"条";
				if(count!=0){
					resultStr = resultStr+",批次号:"+batchid;
				}
			}
			dbids.clear();
		}
	}

	private List<String> getBbIdList(List<FileItem> files){
		List<String> dbids = new ArrayList<String>();
		// 读取
		final InputStream inputStream = getInputStream(files);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		String line = null;
		try {
			while ((line=reader.readLine())!=null) {
				dbids.add(line.trim());
			}
			if(dbids.size()==0){
				resultStr = "上传批量文件为空";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	return dbids;
	}

	/**
	 * 读取文件
	 * 
	 * @param files
	 * @return
	 */
	private InputStream getInputStream(final List<FileItem> files) {
		InputStream inputStream = null;
		for (final FileItem fileItem : files) {
			if (!fileItem.isFormField()) {// 如果该FileItem不是表单域
				fileItem.getSize();
				try {
					if(fileItem.getSize()<(globalResource.getFileMaxSize())*(1024*1024)){
						inputStream = fileItem.getInputStream();// 获得输入数据流文件
						return inputStream;
					}else{
						resultStr = "文件大小不得超过1MB";
					}
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
}
