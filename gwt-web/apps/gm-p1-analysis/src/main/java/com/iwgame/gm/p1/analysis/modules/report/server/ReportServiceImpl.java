/****************************************************************
 *  系统名称  ： 'P1项目-[gm-p1-analysis]'
 *  文件名    ： TestDemoSerivaceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.analysis.modules.report.server;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.iwgame.gm.p1.analysis.modules.report.shared.rpc.ReportService;
import com.iwgame.gm.p1.analysis.shared.model.Zone;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xportal.common.shared.model.Product;

/**
 * 类说明
 * @简述： 报表服务实现类
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-3-6 上午10:04:50
 */
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1674368631302225405L;
	
	private DBConnection dbConnectorConnection;
	
	@Autowired
	public void setDbConnection(DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	@Override
	public List<Zone> getZone(Product product) throws AccessDeniedException {
	    
		String productId = product.getName();
	    String targetId = productId + "-OA";
	    
		SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId,targetId, null);
		@SuppressWarnings("unchecked")
		List<Zone> zoneList = (List<Zone>) sqlSessionTemplate.selectList("gm-p1-analysis.queryZone");
		return zoneList ;
	
	}
	
	

	

}
