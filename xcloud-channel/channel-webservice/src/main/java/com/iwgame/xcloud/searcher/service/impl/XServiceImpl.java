/**      
 * PlayerSearchServiceImpl.java Create on 2012-3-21     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xcloud.searcher.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.iwgame.dao.XDao;
import com.iwgame.xcloud.searcher.model.ActWinner;
import com.iwgame.xcloud.searcher.service.XService;
import com.iwgame.xcloud.searcher.util.MD5Util;

/**
 * @ClassName: XServiceImpl
 * @author: 吴君杰
 * @email: wujunjie@iwgame.com
 * @date: 2012-7-27上午11:15:17
 * @Version: 1.0
 */
@WebService(endpointInterface = "com.iwgame.xcloud.searcher.service.XService")
public class XServiceImpl implements XService {
	
	private final Logger logger = Logger.getLogger(XServiceImpl.class);

	@Resource
	private XDao xDao;

	@Override
	public String winners(String gameid, String actid, String maxid, String limit,String sign) {
		
		//签名为空
		if(StringUtils.isBlank(sign)){
			return "";
		}
		
		//签名验证
		String key = gameid + actid + maxid + limit + "xuyjdkfu43U76YHVsnV!@#sPOK";
		String localSing = MD5Util.md5sum(key.getBytes());
		if(!sign.equals(localSing)){
			logger.error("签名 [ "+sign+" ] 不合法....");
			return "";
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		String reslut = "";
		try {
			params.put("gameid", Integer.valueOf(gameid == null ? "0" : gameid));
			params.put("actid", Integer.valueOf(actid == null ? "0" : actid));
			params.put("maxid", Integer.valueOf(maxid == null ? "0" : maxid));
			if(updateWinnersFlag(params)){
				params.put("limit", Integer.valueOf(limit == null ? "0" : limit));
				if(!StringUtils.isNotBlank(limit)){
					params.put("limit", 500);
				}
				List<ActWinner> winnerss = xDao.selectActWinner(params);
				reslut = getDataXML(winnerss);
				logger.info("成功返回数据  ["+winnerss.size()+"] 条!...");
			}
		} catch (Exception e) {
			logger.error("查询ActWinner失败",e);
			return reslut;
		}
		return reslut;
	}

	private boolean updateWinnersFlag(Map<String,Object> params) {
		try {
			if(!StringUtils.isNotBlank(String.valueOf(params.get("gameid")))||!StringUtils.isNotBlank(String.valueOf(params.get("gameid")))){
				return false;
			}
			xDao.updateWinnersFlag(params);
			return true;
		} catch (Exception e) {
			logger.error("更新ActWinner失败!",e);
			return false;
		}
	}
	
	private String getDataXML(List<ActWinner> winnerss)
    {
        if (winnerss == null || winnerss.size() < 1){
        	return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<act_data>");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        for (ActWinner actWinner : winnerss) {
            sb.append("<detail>");
            sb.append("<AcceptId>");
            sb.append(actWinner.getIdx());
            sb.append("</AcceptId>");
            sb.append("<UserName>");
            sb.append(actWinner.getUser_name());
            sb.append("</UserName>");
            sb.append("<RoleName>");
            sb.append(actWinner.getRole_name());
            sb.append("</RoleName>");
            sb.append("<GameId>");
            sb.append(actWinner.getGameid());
            sb.append("</GameId>");
            sb.append("<ActId>");
            sb.append(actWinner.getActid());
            sb.append("</ActId>");
            sb.append("<type>");
            sb.append(actWinner.getType());
            sb.append("</type>");
            sb.append("<AreaId>");
            sb.append(actWinner.getZone());
            sb.append("</AreaId>");
            sb.append("<value>");
            sb.append(actWinner.getValue());
            sb.append("</value>");
            sb.append("<CreateTime>");
            String time = format.format(actWinner.getAddtime());
            sb.append(time == null ? "1900-01-01 00:00:00" : time);
            sb.append("</CreateTime>");
            sb.append("</detail>");
        }
        sb.append("</act_data>");
        sb.append("</xml>");
        return sb.toString();
    }
}
