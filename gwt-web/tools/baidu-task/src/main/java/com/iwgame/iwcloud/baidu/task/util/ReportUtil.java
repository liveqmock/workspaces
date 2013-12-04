package com.iwgame.iwcloud.baidu.task.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.baidu.api.sem.common.v2.ResHeader;
import com.baidu.api.sem.sms.v2.GetReportFileUrlRequest;
import com.baidu.api.sem.sms.v2.GetReportFileUrlResponse;
import com.baidu.api.sem.sms.v2.GetReportStateRequest;
import com.baidu.api.sem.sms.v2.GetReportStateResponse;
import com.baidu.api.sem.sms.v2.ReportService;
import com.iwgame.iwcloud.baidu.task.core.ClientBusinessException;
import com.iwgame.iwcloud.baidu.task.core.ClientInternalException;
import com.iwgame.iwcloud.baidu.task.core.GZipUtil;
import com.iwgame.iwcloud.baidu.task.core.ObjToStringUtil;
import com.iwgame.iwcloud.baidu.task.core.ResHeaderUtil;

/**
 * 
 * 类说明
 * 
 * @简述： 报表辅助类
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-6-28 下午08:18:32
 */
public abstract class ReportUtil {

	private static final Logger logger = Logger.getLogger(ReportUtil.class);

	/**
	 * 拿到这份报告文件Url通过报告Id。我们将检查该文件内部状态.
	 * 
	 * @param service
	 *            实例ReportService
	 * @param reportId
	 *            该报告Id.
	 * @param retryNum
	 *            尝试次数, 我们将检查文件状态每30秒.因为我们一天去取一次，建议尝试两次 为1分钟
	 * @return
	 */
	public static final GetReportFileUrlResponse getReportFileUrl(ReportService service, String reportId, int retryNum) {
		logger.info("每15秒我们将检查文件状态,请稍候...");
		// 这是请求
		GetReportStateRequest parameters = new GetReportStateRequest();
		parameters.setReportId(reportId);
		int lastStatus = -1;
		while (retryNum-- > 0) {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 调用该方法.
			GetReportStateResponse ret = service.getReportState(parameters);
			// 处理响应标头,第二个参数控制是否打印INFO日志或不响应头。
			ResHeader rheader = ResHeaderUtil.getResHeader(service, false);
			// 如果状态等于零,没有错误。否则,您需要检查错误响应头中。
			if (rheader.getStatus() == 0) {
				logger.info("验证报表状态码[1:等待中,2:处理中,3:处理成功 ] 当前状态： \n" + ObjToStringUtil.objToString(ret));
				/**
				 * getIsGenerated 1：等待中 2：处理中 3：处理成功 此处返回代码状态含义与V1不同，请注意
				 * 对于生成失败的报告返回null和对应的errorcode， 可能有901915,901912,901911,901910
				 */
				if (ret.getIsGenerated() == 3) {
					lastStatus = 3;
					break;
				}
				String statusName = 1 == ret.getIsGenerated() ? "等待中" : "处理中";
				logger.info("当前报表未生成好,报表状态为:[ " + statusName + " ] ,将再次获取,请等待...");
			} else {
				logger.error("您需要检查错误响应头中  rheader.getStatus() 状态错误[1]! rheader:" + rheader + "ret:" + ret);
				throw new ClientBusinessException(rheader, ret);
			}
		}
		
		
		// 处理成功，再次去请求！
		if (lastStatus == 3) {
			// 这是请求
			GetReportFileUrlRequest parameters2 = new GetReportFileUrlRequest();
			parameters2.setReportId(reportId);
			// 调用该方法.
			GetReportFileUrlResponse ret = service.getReportFileUrl(parameters2);
			// 处理响应标头,第二个参数控制是否打印到控制台或不响应头.
			ResHeader rheader = ResHeaderUtil.getResHeader(service, false);
			// 如果状态等于零,没有错误。否则,您需要检查错误响应头中.
			if (rheader.getStatus() == 0) {
				logger.info("获得报表下载地址:\n" + ObjToStringUtil.objToString(ret));
				return ret;
			} else {
				logger.error("得到  rheader.getStatus() 状态错误[2]! rheader:" + rheader + "ret:" + ret);
				throw new ClientBusinessException(rheader, ret);
			}
		}
		logger.error("We tried to get file for " + retryNum / 2 + " minites, but file still not ready!");
		throw new ClientInternalException("We tried to get file for " + retryNum / 2 + " minites, but file still not ready!");
	}

	/**
	 * 下载这个文件从服务器和解码它作为GBK网页.
	 * 
	 * @param url
	 *            The file url
	 * @return
	 * @throws IOException
	 */
	public static final String getFileContent(String url, String savePath) throws IOException {
		return getFileContent(url, null, null, savePath);
	}

	/**
	 * 从服务器下载该文件,将它解压缩.
	 * 
	 * @param url
	 *            The file url
	 * @param format
	 *            The compressing format
	 * @return The character file content
	 * @throws IOException
	 */
	public static final String getFileContent(String url, String format, String md5, String savePath) {
		byte[] fileContent = null;
		String ret = "";
		try {
			fileContent = DownloadUtil.downloadFile(url);

			try {
				// 保存文件备份日志
				File saveFile = new File(savePath);

				FileUtils.writeByteArrayToFile(saveFile, fileContent);

			} catch (IOException e) {
				logger.error("备份日志失败！File：" + savePath);
			}

			if (md5 != null && !MD5Util.md5check(fileContent, md5)) {
				logger.error("MD5 check failed, please contact the administrator.");
			}
			if (format == null || format.equals("")) {
				try {
					ret = new String(fileContent, "gbk");
					return ret;
				} catch (Exception e) {
					logger.error("获取报表内容失败， [format] 字符串格式解析错误！ fileContent 可能为空！", e);
				}
			}
			if (format.equalsIgnoreCase("gzip")) {
				try {
					fileContent = GZipUtil.unGzip(fileContent);
					ret = new String(fileContent, "gbk");
					return ret;
				} catch (Exception e) {
					logger.error("获取报表内容失败，[gzip] 字符串格式解析错误！ fileContent 可能为空！", e);
				}
			}
			logger.error("We only support GZIP now, please try to use GZIP.");
		} catch (IOException e) {
			logger.error("从服务器下载该文件出错!",e);
			return ret;
		}
		
		return ret;
	}
}
