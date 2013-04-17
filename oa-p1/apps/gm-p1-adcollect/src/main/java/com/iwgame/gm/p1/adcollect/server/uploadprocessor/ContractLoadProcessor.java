/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ContractLoadProcessor.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.uploadprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.icu.text.SimpleDateFormat;
import com.iwgame.gm.p1.adcollect.server.OperatorLogger;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.ContractDataBase;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xmvp.server.fileupload.AbstractMediaFileProcessor;
import com.iwgame.xmvp.shared.Media;
import com.iwgame.xportal.common.server.dao.ProductDao;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.shared.model.Product;

/**
 * 类说明
 * 
 * @简述： 合同管理处理类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-11 下午05:36:14
 */
// @NeedAuthorization
public class ContractLoadProcessor extends AbstractMediaFileProcessor {

	private static final String AHA = "ad.hard.admin.";

	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_UPLOAD_CONTRACT,
			LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(ContractLoadProcessor.class);

	private ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	private OperatorLogger businessLogger;// 业务日志

	@Autowired
	public void setBusinessLogger(final OperatorLogger businessLogger) {
		this.businessLogger = businessLogger;
	}

	private String physicalPath;// 合同附件保存物理路径

	@Override
	public String getPhysicalPath() {
		return physicalPath;
	}

	public void setPhysicalPath(final String physicalPath) {
		this.physicalPath = physicalPath;
	}

	private DBConnection dbConnectorConnection;// 数据连接

	@Autowired
	public void setDbConnection(final DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	protected SqlSessionTemplate getSqlSessionTemplate(final String productId) {
		final String targetId = productId + "-AD";
		final SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId,
				targetId, null);
		return sqlSessionTemplate;
	}

	@Override
	// @AccessResource(name = "ad-hard-admin-contract-uploadfile")
	protected void postUploadFile() {
		// 在这里 写 保存数据

		final Map<String, Media> media = getFiles();

		final String productId = getCommonFields().get("productId").toString();
		final String operation = getCommonFields().get("operation").toString();
		final ContractDataBase newDataBase = new ContractDataBase();

		newDataBase.setItemno(getCommonFields().get("itemno"));
		newDataBase.setName(getCommonFields().get("name"));
		newDataBase.setSignedString(getCommonFields().get("signed"));
		newDataBase.setApplyman(getCommonFields().get("applyman"));
		newDataBase.setSection(getCommonFields().get("section"));
		newDataBase.setLevel(getCommonFields().get("level"));
		newDataBase.setType(getCommonFields().get("type"));
		if ((null != getCommonFields().get("mediaId")) && !getCommonFields().get("mediaId").equals("-1")) {
			newDataBase.setMediaId(getCommonFields().get("mediaId"));
		}
		// 设置是否有附件 0 是 1 否
		if ((null != media) && !media.isEmpty()) {
			newDataBase.setPath(media.get("adjunct").getUrl());
			newDataBase.setIsatt("0");
		} else {
			newDataBase.setIsatt("1");
		}

		newDataBase.setTitleId(IsNotNullAndStringToInt("titleId"));
		newDataBase.setLogoId(IsNotNullAndStringToInt("logoId"));
		newDataBase.setContent(getCommonFields().get("content"));
		newDataBase.setDescription(getCommonFields().get("description"));
		newDataBase.setNote(getCommonFields().get("note"));
		newDataBase.setAdsituation(getCommonFields().get("adsituation"));
		newDataBase.setPayedString(getCommonFields().get("payed"));

		newDataBase.setTotal(IsNotNullAndStringToDouble("total"));
		newDataBase.setDiscount(IsNotNullAndStringToDouble("discount"));
		newDataBase.setStdRegcost(IsNotNullAndStringToDouble("stdRegcost"));
		newDataBase.setStdLogincost(IsNotNullAndStringToDouble("stdLogincost"));
		newDataBase.setStdDiscount(IsNotNullAndStringToDouble("stdDiscount"));
		newDataBase.setAdamt(IsNotNullAndStringToInt("adamt"));

		if (operation.equals("add")) {// 添加
			addContract(productId, newDataBase);
		}

		if (operation.equals("update")) {// 修改.
			updateContract(productId, newDataBase);
		}
		super.postUploadFile();
	}

	// @AccessResource(name = "ad-hard-admin-contract-update")
	private void updateContract(final String productId, final ContractDataBase newDataBase) {
		final int id = Integer.parseInt(getCommonFields().get("id"));
		newDataBase.setId(id);
		// 原来的数据
		final ContractDataBase oldDataBase = (ContractDataBase) getSqlSessionTemplate(productId).selectOne(
				AHA + "getContractById", id);

		List<String> list = getProducts();
		for (String product : list) {
			try {
				// 执行修改数据
				getSqlSessionTemplate(product).update(AHA + "updateContract", newDataBase);
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_CONTRACT, "产品" + product + "修改合同成功");
				final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if (null != oldDataBase.getSigned()) {
					oldDataBase.setSignedString(formatter.format(oldDataBase.getSigned()));
				}
				if (null != oldDataBase.getPayed()) {
					oldDataBase.setPayedString(formatter.format(oldDataBase.getPayed()));
				}
				// 写日志
				businessLogger.writeModifyLog(product, oldDataBase, newDataBase);

			} catch (Exception e) {
				logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_CONTRACT, "产品" + product + "修改合同失败" + e);
				logger4j.error(e);
			}
		}
	}

	// @AccessResource(name = "ad-hard-admin-contract-add")
	private void addContract(final String productId, final ContractDataBase newDataBase) {
		List<String> list = getProducts();
		for (String product : list) {
			try {

				getSqlSessionTemplate(product).insert(AHA + "addContract", newDataBase);
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_CONTRACT, "产品" + product + "添加合同成功");
				newDataBase.setId(newDataBase.getId());
				businessLogger.writeCreateLog(product, newDataBase);
			} catch (Exception e) {
				logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_CONTRACT, "产品" + product + "添加合同失败" + e);
			}
		}
	}

	/**
	 * 判断 不为空 转为int 为空则 转成 零
	 */
	public int IsNotNullAndStringToInt(final String value) {
		if (null != getCommonFields().get(value)) {
			return Integer.parseInt(getCommonFields().get(value));
		} else {
			return 0;
		}
	}

	/**
	 * 判断 不为空 转为double 为空则 转成 零
	 */
	public double IsNotNullAndStringToDouble(final String value) {
		if ((null != getCommonFields().get(value)) && !getCommonFields().get(value).equals("")) {
			return Double.parseDouble(getCommonFields().get(value));
		} else {
			return 0;
		}
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
}
