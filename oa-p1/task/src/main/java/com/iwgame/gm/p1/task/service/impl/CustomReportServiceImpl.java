/****************************************************************
 *  系统名称  ：  '广告系统任务处理'
 *  文件名    ： LogMonitorService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.task.bean.CustomReport;
import com.iwgame.gm.p1.task.dao.AdcollectMonitorDao;
import com.iwgame.gm.p1.task.exception.CustomReportException;
import com.iwgame.gm.p1.task.exception.SPSinkException;
import com.iwgame.gm.p1.task.service.CustomReportService;
import com.iwgame.gm.p1.task.util.Constant;

/**
 * 类说明
 * @简述： 定制报表服务
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-8-30 下午3:46:07
 */
public class CustomReportServiceImpl implements CustomReportService{
	private static Logger logger = Logger.getLogger(Constant.LOG_CUSTOM_REPORT);
	//列名    1:留存 2:留存排期 3:充值 4:充值排期
	private String remainColumnString = "";
	private String remainSheduleColumnString = "";
	private String remainColumnTitleString = "";
	private String remainSheduleColumnTitleString = "";

	private String paymentColumnString = "";
	private String paymentSheduleColumnString = "";
	private String paymentColumnTitleString = "";
	private String paymentSheduleColumnTitleString = "";
	
	private static final String EXCEL_SUBFIX = ".xls";
	
	//保存路径
	private String filePath = "";
	private String policy = "";
	
	//文件相对路径包括文件名
	private String path = "";
	
	private DataSource dataSource;
	
	private AdcollectMonitorDao adcollectMonitorDao;
	
	@Autowired
	public void setRemainSheduleColumnString(String remainSheduleColumnString) {
		this.remainSheduleColumnString = remainSheduleColumnString;
	}

	@Autowired
	public void setRemainSheduleColumnTitleString(
			String remainSheduleColumnTitleString) {
		this.remainSheduleColumnTitleString = remainSheduleColumnTitleString;
	}

	@Autowired
	public void setPaymentSheduleColumnString(String paymentSheduleColumnString) {
		this.paymentSheduleColumnString = paymentSheduleColumnString;
	}
    
	@Autowired
	public void setPaymentSheduleColumnTitleString(
			String paymentSheduleColumnTitleString) {
		this.paymentSheduleColumnTitleString = paymentSheduleColumnTitleString;
	}

	@Autowired
	public void setRemainColumnString(String remainColumnString) {
		this.remainColumnString = remainColumnString;
	}

	@Autowired
	public void setRemainColumnTitleString(String remainColumnTitleString) {
		this.remainColumnTitleString = remainColumnTitleString;
	}
    
	@Autowired
	public void setPaymentColumnString(String paymentColumnString) {
		this.paymentColumnString = paymentColumnString;
	}
    
	@Autowired
	public void setPaymentColumnTitleString(String paymentColumnTitleString) {
		this.paymentColumnTitleString = paymentColumnTitleString;
	}
    
	@Autowired
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	
	
	@Autowired
	public void setAdcollectMonitorDao(AdcollectMonitorDao adcollectMonitorDao) {
		this.adcollectMonitorDao = adcollectMonitorDao;
	}

	public void createCustomReport() throws CustomReportException{
		CustomReport customReport = null;
		List<Map<String, String>> resultList = null;
		try {
			//获取定制报表任务
			customReport = getUnTreatedCustomReport();
			if (customReport == null) {
				logger.info("本次没有找到需要执行的定制报表任务!");
			} else {
				//更新报表状态为处理中
				updateCustomReportStatus(customReport.getId());
				
				//取得报表类型
				String report_type = JSONObject.fromObject(customReport.getParam()).get("report_type").toString();//1:留存 remain 2：充值 payment
				String summary_type = JSONObject.fromObject(customReport.getParam()).get("summary_type").toString();//1:广告ID  2:排期 shedule
				
				//取得结果列表
				resultList  = getReportResultList(report_type,summary_type,customReport);
				
				//生成报表
				if (resultList!=null && resultList.size()>0) {
					//构建Excel并取得耗时与文件路径
					Map<String, String> resultMap =	getBuildExcelResultMap(report_type,summary_type,resultList);
					
					if (resultMap != null && resultMap.get("ablpath") != null) {
						//文件已经生成成功
						if (new File(resultMap.get("ablpath")).exists()) {
							//保存报表信息到数据库
							updateCustomResult(customReport.getId(),resultMap.get("costTime"),resultMap.get("path"));
							logger.info("Excel报表生成成功,reportId=" + customReport.getId()+" ,path=" + resultMap.get("path") + ",costTime=" + resultMap.get("costTime"));
						}else {
							regainStatus(customReport.getId());
							logger.error("构建Excel出错,错误路径为："+resultMap.get("ablpath")+"");
						}
					}else {
						regainStatus(customReport.getId());
					}
				}else {
					//存储过程没有返回值
					noResultRegainStatus(customReport.getId());
					logger.info("取得的结果集长度为0,不生成报表.");
				}
				
				logger.info("完成一次定制报表生成任务,reportId=" + customReport.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomReportException(e);
		} finally {
			//释放对象
			resultList = null;
			customReport = null;
		}
	}
	
   /**
    * 调用构建报表方法取得耗时与地址	
    * @param report_type
    * @param summary_type
    * @param resultList
    * @return
	* @throws Exception 
    */
   private  Map<String, String> getBuildExcelResultMap(String report_type,String summary_type,List<Map<String, String> > resultList) throws Exception{
	   Map<String, String> resultMap = null;
	   String columnTitle = "";
	   String columnName = "";
		// 1:留存，2：充值
		if (Type.REPORTTYPE_REMAIN.getContext().equals(report_type)) {
			if (Type.SUMMARY_ADID.getContext().equals(summary_type)) {
       		    columnName = remainColumnString;
       		    columnTitle = remainColumnTitleString;
			}else {
				columnName = remainSheduleColumnString;
				columnTitle = remainSheduleColumnTitleString;
			}
		}else {
			if (Type.SUMMARY_ADID.getContext().equals(summary_type)) {
				columnName = paymentColumnString;
				columnTitle = paymentColumnTitleString;
			}else {
				columnName = paymentSheduleColumnString;
				columnTitle = paymentSheduleColumnTitleString;
			}
		}
		resultMap = buildExcel(columnName,columnTitle, resultList);
		return resultMap;
   }
	
	/**
	 *  调用存储过程取得结果集
	 * @param report_type
	 * @param summary_type
	 * @param customReport
	 * @return
	 * @throws Exception 
	 */
	private List<Map<String, String>> getReportResultList(String report_type,String summary_type,CustomReport customReport) throws Exception{
		List<Map<String, String>> resultList = null;
		String column = "";
        if (Type.REPORTTYPE_REMAIN.getContext().equals(report_type)) {
        	column	= getRemainColumn(column,summary_type);
		}else {
			column	= getPaymentColumn(column,summary_type);
		}
        resultList = getResultSetList(column,dataSource, customReport);
		return resultList;
	}

	
	/**
	 * 构建Excel,返回耗时与路径地址
	 * @param columnNames
	 * @param columnTitleNames
	 * @param result
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> buildExcel(final String columnNames,final String columnTitleNames, final List<Map<String, String>> result) throws Exception{
		Map<String, String> map = null;
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try {
			map = new HashMap<String, String>();
			String fileName = getFileName();
			path = getPath()+fileName+EXCEL_SUBFIX;
		    //相对路径
			String relPath = getDateFilePath()+fileName+EXCEL_SUBFIX;
			map.put("path", relPath);
			map.put("ablpath", path);
			os = new FileOutputStream(path);   
			long startTime = System.currentTimeMillis();
			wwb = buildSheet(columnNames,columnTitleNames, result, os);
			long endTime = System.currentTimeMillis();
			long costTime = (endTime - startTime)/1000;
            map.put("costTime", String.valueOf(costTime));
			wwb.write();
            logger.info("生成报表成功,耗时 "+costTime +" 秒.");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (wwb!=null) {
				try {
					wwb.close();
				} catch (WriteException e) {
					logger.error("关闭WritableWorkbook出错," + e);
				} catch (IOException e) {
					logger.error("关闭WritableWorkbook出错," + e);
				}
			}
			if (os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("关闭OutputStream出错," + e);
				}
			}
		}
		return map;
	}

	/**
	 * 构建一个Excel表格
	 * @param columnNames
	 * @param columnTitleNames
	 * @param result
	 * @param os
	 * @return
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private WritableWorkbook buildSheet(final String columnNames,final String columnTitleNames, final List<Map<String, String>> result, final OutputStream os) throws IOException, WriteException,
			RowsExceededException {
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		int ncout = result.size();// 数据集条数
		int maxnum = 50000; // 一次最多写入量
		int times = (ncout + maxnum - 1) / maxnum;

		WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
				jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
		WritableCellFormat titleFormat = new WritableCellFormat(font);
		
		String[] titleArray = columnNames.split(",");
		ArrayList<String> coltitles = new ArrayList<String>();
		
		//将表头名称取出
		String[] coltitle = columnTitleNames.split(",");

		if (coltitle!=null && coltitle.length>0) {
			for (int i = 0; i < coltitle.length; i++){
				coltitles.add(coltitle[i]);
			}
		}

		for (int t = 0; t < times; t++) {

			// 新建一张表
			WritableSheet wsheet = wwb.createSheet("Sheet_" + (t + 1), t);

			// 设置表头
			for (int index = 0; index < coltitles.size(); index++) {
				Label excelTitle = new Label(index, 0, coltitles.get(index), titleFormat);
				wsheet.addCell(excelTitle);
			}

			// 读出数据
			if (result != null) {
				int base = (t * maxnum);
				int size = (result.size() - base > maxnum) ? maxnum : result.size() - base;
				for (int i = 0; i < size; i++) {
					Map<String, String> data = result.get(i + base);

					for (int index = 0; index < titleArray.length; index++) {
						Object o = data.get(titleArray[index]);
						if (o != null) {
							Label label = new Label(index, (i + 1), o.toString());
							wsheet.addCell(label);
						}
					}
				}
			}
		}
		return wwb;
	}
	
	/**
	 * 取得未处理的任务每次一条
	 * @return
	 */
	private CustomReport getUnTreatedCustomReport() throws Exception{
		CustomReport customReport = null;
		try {
			List<CustomReport> result = adcollectMonitorDao.getCustomReportList();
			if (result!=null && result.size()>0) {
				customReport = result.get(0);
			}
			if (customReport!=null) {
				logger.info("取得定制报表任务"+customReport);
			}else {
				logger.info("没有需要的定制报表任务");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("取得定制报表任务失败," + e);
		}
		return customReport ;
	};
	
	/**
	 * 设置未处理任务状态为处理中,成功才可以取数据
	 * @param reportId
	 * @return
	 */
	private int updateCustomReportStatus(String reportId) throws Exception{
		int result = 0;
		try {
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("id", reportId);
			result = adcollectMonitorDao.updateStatus(paraMap);
			logger.info("更新报表为处理中成功,reportId=" + reportId);
			return result;
		} catch (Exception e) {
			throw new Exception("更新任务状态时失败," + e);
		}
    }

	//任务失败 重置状态
	private void regainStatus(String reportId){
		try {
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("id", reportId);
			adcollectMonitorDao.regainStatus(paraMap);
		} catch (Exception e) {
			logger.error("为任务重置状态失败," + e);
		}
	}

	/**
	 * 任务失败 重置状态
	 * @param reprotId
	 */
	private void noResultRegainStatus(String reportId){
		try {
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("id", reportId);
			adcollectMonitorDao.noResultRegainStatus(paraMap);
		} catch (Exception e) {
			logger.error("为任务重置状态失败," + e);
		}
	}

	/**
	 * 将处理完成的任务结果写入数据库
	 * @param customReport
	 * @return
	 */
	private void updateCustomResult(String reprotId,String costTime,String path){
		try {
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("id", reprotId);
			paraMap.put("costTime", costTime);
			paraMap.put("path", path);
			adcollectMonitorDao.updateCustomResult(paraMap);
			logger.info("任务成功，将结果写入数据库成功");
		} catch (Exception e) {
			logger.error("任务成功，将结果写入数据库失败",e);
		}
	}
    
	/**
	 * 调用存储过程 取得结果集list
	 * @param column
	 * @param dataSouce
	 * @param customReport
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, String>> getResultSetList(String column,DataSource dataSouce,CustomReport customReport) throws Exception {
		List<Map<String, String>> resultList = null; //调用存储过程的结果集
		String procStatement = null;
		PolicyInfo policyInfo = new PolicyInfo();
		try {
			// 读取JSON的数值
			policyInfo = getPolicyInfo(customReport, policy);
			// 构建存储过程语句
			procStatement = buildProcStatement(policyInfo.getProcName(),policyInfo.getParamValues());
			logger.info("执行存储过程:"+ procStatement);
			
			// 调用存储过程
			long starttime = System.currentTimeMillis();
			resultList = execProcedure(column,dataSouce, procStatement,policyInfo.getParamValues());
			long endtime = System.currentTimeMillis();
			long costtime = endtime - starttime;
			
			logger.info("完成存储过程调用,proc=" + policyInfo.getProcName() + "->耗时："+ costtime + "ms");
		} catch (Exception e) {
			e.printStackTrace();
            throw e;
		} finally {
			procStatement = null;
			policyInfo = null;
		}
		return resultList;
	}

	/**
	 * 生成存储过程调用SQL语句
	 * @param procName
	 * @param procParam
	 * @return
	 */
	private String buildProcStatement(String procName,
			List<String> procParam) {
		StringBuilder procedureSQL = new StringBuilder();
		procedureSQL.append("{call " + procName + "(");

		if (procParam != null && procParam.size() > 0) {
			for (int i = 0; i < procParam.size(); i++) {
				procedureSQL.append("?").append(",");
			}
		}
		procedureSQL.deleteCharAt(procedureSQL.length()-1);
		procedureSQL.append(")}");
		return procedureSQL.toString();
	}
	
    
	/**
	 * 获取参数
	 * @param customReport
	 * @param policy
	 * @return
	 * @throws SPSinkException
	 */
	private  PolicyInfo getPolicyInfo(CustomReport customReport, String policy) throws SPSinkException {
		try {
			PolicyInfo policyInfo = new PolicyInfo();
			List<String> paramValue = new ArrayList<String>();
			String param = customReport.getParam();
			String[] arr = policy.split(",");
			JSONObject jsonObject = JSONObject.fromObject(param);
			if (jsonObject != null) {
				policyInfo.setProcName(arr[0]);
				for (int i = 1; i < arr.length; i++) {
					String key = arr[i];
						if (jsonObject.has(key)) {
							paramValue.add(jsonObject.getString(key));// 取得值
						} else {
							throw new SPSinkException("调用存储过程参数不匹配： "+ key + "== policy:" + arr[0]
									+ "==param:" + param + "====>>>> "+ customReport.toString());
					}
				}
				policyInfo.setParamValues(paramValue);

				return policyInfo;
			} else {
				throw new SPSinkException("数据格式不正确,非json格式：" + customReport + " | "
						+ customReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SPSinkException(e.getMessage());
		}
	}

	/**
	 * 执行存储过程,返回结果集的list
	 * 
	 * @param dataSouce
	 *            数据源标志
	 * @param procName
	 *            存储过程名称
	 * @param procValues
	 *            参数列表
	 * @throws DBConnectException
	 * @throws SyncException
	 */
	private List<Map<String, String>> execProcedure(String column,DataSource dataSouce,String procName, List<String> procValues)
			throws Exception {
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		ResultSet resultCode = null;
		List<Map<String, String>> resultList = new ArrayList<Map<String,String>>();
		
		try {
			// 封装数据库连接异常
			dbConnection = dataSouce.getConnection();
			
			if (dbConnection == null || dbConnection.isClosed()) {
				throw new SQLException("连接已经关闭！");
			}
			// 执行
			callableStatement = dbConnection.prepareCall(procName);
			
			// 参数赋值
			for (int i = 0; i < procValues.size(); i++) {
				callableStatement.setString((i + 1), procValues.get(i));
			}
          
			callableStatement.execute();

			String[] titleArray = column.split(",");
			Map<String, String> resultMap = null;	
			
			resultCode = callableStatement.getResultSet();
			while(resultCode.next()){
			    resultMap = new HashMap<String, String>();
				for (int i = 0; i < titleArray.length; i++) {
				    resultMap.put(titleArray[i], resultCode.getString(titleArray[i]));
				}
				resultList.add(resultMap);
				resultMap = null;
		  }
			logger.info("调用存储过程成功,共取得["+resultList.size()+"]条数据");
		}  catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (resultCode !=null) {
				try {
					resultCode.close();
					resultCode = null;
				} catch (Exception e) {
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (Exception e) {
				}
				callableStatement = null;
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (Exception e) {
				}
			}
		}
        return resultList;
	}

	//得到文件路径与名称
	private String getPath(){
		String path = "";
		if (filePath.endsWith("/")) {
			path = filePath.substring(0, filePath.length()-1)+getDateFilePath();
		}else {
			path = filePath+getDateFilePath();
		}
		File file = new File(path);
		if (file.exists()) {
			return path;
		}else {
			file.mkdirs();
			return path;
		}
	}
 	//取得留存列名
	private String getRemainColumn(String column,String summary_type){
		if (Type.SUMMARY_ADID.getContext().equals(summary_type)) {
    		column = remainColumnString;
		}else {
			column = remainSheduleColumnString;
		}
		return column;
	}

	//取得充值列名
	private String getPaymentColumn(String column,String summary_type){
		if (Type.SUMMARY_ADID.getContext().equals(summary_type)) {
			column = paymentColumnString;
		}else{
			column = paymentSheduleColumnString;
		}
		return column;
	}
	
	//得到时间路径
	private String getDateFilePath(){
	    Calendar cal=Calendar.getInstance();//使用日历类
		int year=cal.get(Calendar.YEAR);//得到年
		int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
		int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
		return "/"+year+"/"+month+"/"+day+"/";
	}
	
	//得到文件名称
	private String getFileName(){
		long currentTime = System.currentTimeMillis();
		return String.valueOf(currentTime); 
	}
	
    //报表类型与求和类型
	private enum Type {
	  
	  REPORTTYPE_REMAIN("1"),  //留存
	  REPORTTYPE_PAYMENT("2"), //充值
     
	  SUMMARY_ADID("1"),   //广告ID
	  SUMMARY_SHEDULE("2"); //排期
    
	  private String context;
      private String getContext(){
	      return this.context;
      }
      private Type(String context){
		  this.context = context;
      }
	}	  
	private class PolicyInfo {
		private List<String> paramValues = null;
		private String procName = "";

		public List<String> getParamValues() {
			return paramValues;
		}

		public void setParamValues(List<String> paramValues) {
			this.paramValues = paramValues;
		}

		public String getProcName() {
			return procName;
		}

		public void setProcName(String procName) {
			this.procName = procName;
		}
	}
    
	
}
