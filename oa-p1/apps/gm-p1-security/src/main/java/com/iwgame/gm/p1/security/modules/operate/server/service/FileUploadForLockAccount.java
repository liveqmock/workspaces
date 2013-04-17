/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FileUploadForUsername.java
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
import com.iwgame.gm.p1.security.common.server.dao.KilledRecordDao;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.server.util.DateHelper;
import com.iwgame.gm.p1.security.common.server.util.GuidHelper;
import com.iwgame.gm.p1.security.common.shared.bean.GlobalResource;
import com.iwgame.gm.p1.security.common.shared.model.IwResultTrack;
import com.iwgame.gm.p1.security.common.shared.model.KilledRecord;
import com.iwgame.gm.p1.security.common.shared.util.RegexGwtHelper;
import com.iwgame.gm.p1.security.modules.operate.server.bean.ERData;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.LockAccountBean;
import com.iwgame.xmvp.server.fileupload.FileProcessor;
import com.iwgame.xmvp.server.fileupload.FileUploadMessages;

/**
 * 类说明
 * @简述：封杀账号处理bean
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-16 上午11:11:06
 */
public class FileUploadForLockAccount implements FileProcessor,FileUploadMessages {
	private Logger logger4j = Logger.getLogger(FileUploadForLockAccount.class);
	
	@Resource(name = "globalResource")
	private GlobalResource globalResource;
	
	@Resource(name = "killedRecordDaoImpl")
	private KilledRecordDao killedRecordDao;
	
	@Resource(name = "iwResultTrackDaoImpl")
	private IwResultTrackDao iwResultTrackDao;
	
	@Resource(name = "securityLockAccountErData")
	private ERData secrityLockAccountErData;
	
	@Resource(name = "securityRabbitTemplate")
	private RabbitTemplate rabbitTmeplate;
	
	private String resultStr = "";
	
	@Override
	public void processUploadedFile(Map<String, String> parameters,
			List<FileItem> files) {	
		List<String> names = getUserNameList(files);
		if(names.size()>0){
			if(names.size()>globalResource.getFileMaxLine()){
				resultStr = "批量账号上传数量不得超过"+globalResource.getFileMaxLine()+"个";
			}else if(names.size()>globalResource.getOnlineMaxNum()&&("1".equals(parameters.get("online")))){
				resultStr = "立即强制下线(会影响账号注册充值,批量账号下线禁止超过"+globalResource.getOnlineMaxNum()+"个账号)";
			}else if(names.size()>globalResource.getMailnoticeMaxNum()&&("1".equals(parameters.get("mailnotice")))){
				resultStr = "邮件通知(数量过多会影响公司邮件发送,禁止超过"+globalResource.getMailnoticeMaxNum()+"个账号)";
			}else{
				int count = 0;
				int delcount = 0;
				//批次号
				String batchid = GuidHelper.getBatchId();
				List<LockAccountBean> beans = new ArrayList<LockAccountBean>();
				for(String name:names){
					if(name.matches(RegexGwtHelper.STR_ENG_NUM)){
						if((ConstantServer.PRODUCT_ID_ZXY).equals(parameters.get("pid"))){
							name = name + "_zxy";
						}
						KilledRecord killedRecord = null;
						boolean isKill = true;
						try {
							killedRecord = killedRecordDao.getRecordByUsername(parameters.get("pid"), name);
						} catch (Exception e1) {
							logger4j.error("根据账号查询封杀记录错误："+name);
						}
						if(killedRecord!=null){
							// look
							if("2".equals(parameters.get("optype"))){
								if(killedRecord.getType()==1 
										&& !(DateHelper.compareElapsedTime(DateHelper.getCurrentDate(), killedRecord.getOptime(), DateHelper.DIFF_DAY, killedRecord.getDays()))){
									//封杀有效期内
									isKill = false;	
								}else if(killedRecord.getType()==2){
									//冻结中
									isKill = false;
								}
							} else if("1".equals(parameters.get("optype"))) {
								if(killedRecord.getType()==1 
										&& !(DateHelper.compareElapsedTime(DateHelper.getCurrentDate(), killedRecord.getOptime(), DateHelper.DIFF_DAY, killedRecord.getDays()))){
									//封杀有效期内
									isKill = false;
								}
							}
						}
						if(isKill){
							LockAccountBean bean = new LockAccountBean();
							bean.setPid(parameters.get("pid"));
							bean.setGuid("all");
							bean.setSealtime(Integer.parseInt(parameters.get("sealtime"))*24*60);
							bean.setUsername(name);
							bean.setShowtime(Integer.parseInt(parameters.get("showtime")));
							bean.setOnline(Integer.parseInt(parameters.get("online")));
							bean.setMailnotice(Integer.parseInt(parameters.get("mailnotice")));
							bean.setSqlogin(Integer.parseInt(parameters.get("sqlogin")));
							bean.setOptype(Integer.parseInt(parameters.get("optype")));
							bean.setCausetype(Integer.parseInt(parameters.get("causetype")));
							bean.setCausenote(parameters.get("causenote"));
							bean.setBatchid(batchid);
							
							KilledRecord kr = new KilledRecord();
							kr.setZone(bean.getGuid());
							kr.setUsername(bean.getUsername());
							kr.setType(bean.getOptype());
							kr.setDays(Integer.parseInt(parameters.get("sealtime")));
							kr.setBatchid(bean.getBatchid());
							kr.setOperator(parameters.get("operator"));
							kr.setHandleStatus("1");
							kr.setCauseNote(bean.getCausenote());
							kr.setCauseType(bean.getCausetype());
							kr.setKilledType(bean.getOptype());
							
							try {
								killedRecordDao.insert(parameters.get("pid"),kr);
								beans.add(bean);
								count++;
							} catch (Exception e) {
								logger4j.error("批量封杀,账号:"+name+"错误提示:"+e);
								delcount++;
							}
						}else{
							logger4j.error("账号已封杀或冻结,账号:"+name);
							delcount++;
						}
					}else{
						logger4j.error("账号输入不合法,账号:"+name);
						delcount++;
					}
				}
				if(count>0){
					IwResultTrack iwrt = new IwResultTrack();
					iwrt.setBatchid(batchid);
					iwrt.setTrackType(Integer.parseInt(parameters.get("optype")));//1:封杀,2:冻结,3:解封,4:安全模式,5:解除安全模式
					iwrt.setSubmitNum(count);
					iwrt.setOperator(parameters.get("operator"));
					try {
						iwResultTrackDao.insert(parameters.get("pid"),iwrt);
					} catch (Exception e) {
						logger4j.error("封杀追踪记录:"+e);
					}
					for(LockAccountBean lockAccountBean:beans){
						rabbitTmeplate.convertAndSend(secrityLockAccountErData.getExchangeName(),
								secrityLockAccountErData.getRoutinKeyName(),
								JSONObject.fromObject(lockAccountBean).toString());
					}
				}
				beans.clear();
				resultStr = "成功提交封杀账号"+count+"条,忽略"+delcount+"条";
				if(count!=0){
					resultStr = resultStr+",批次号:"+batchid;
				}
			}
			names.clear();
		}
		
	}
	
	private List<String> getUserNameList(List<FileItem> files){
		List<String> names = new ArrayList<String>();
		// 读取
		final InputStream inputStream = getInputStream(files);
		if(inputStream!=null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			
			String line = null;
			try {
				while ((line=reader.readLine())!=null) {
					names.add(line.trim());
				}
				if(names.size()==0){
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
		}
		return names;
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

	@Override
	public String getProcessMessage() {
		return resultStr;
	}
}
