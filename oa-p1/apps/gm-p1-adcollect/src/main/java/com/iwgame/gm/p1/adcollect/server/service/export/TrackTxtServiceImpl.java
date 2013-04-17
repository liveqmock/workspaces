/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： TrackTxtServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service.export;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;

import com.iwgame.gm.p1.adcollect.server.service.BaseService;
import com.iwgame.gm.p1.adcollect.server.util.StringIsEmpty;
import com.iwgame.gm.p1.adcollect.shared.model.AdvtxtHour;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-28 下午02:19:46
 */
@NeedAuthorization
public class TrackTxtServiceImpl extends BaseService implements ExportQuery {

	@Override
	@AccessResource(name = "ad-reports-txt-hour-export")
	public List<BaseModelData> query(Map<String, String> parameters) throws AccessDeniedException{
		List<BaseModelData> datas = null;
		try {

			final String productId = parameters.get("productId");
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("limit", null);
			parameter.put("offset", null);
			parameter.put("key", parameters.get("key"));
			parameter.put("beganTime", parameters.get("startTime"));
			parameter.put("endTime", parameters.get("endTime"));
			final String date = parameter.get("endTime").toString();
			final String tableName = date.substring(0, 4) + date.substring(5, 7);
			parameter.put("tableName", tableName);
			final List<AdvtxtHour> list = selectList(productId, "ad.hour.track." + "getHourTrackListByTxt", parameter);
			datas = new ArrayList<BaseModelData>();

			if (list.size() > 0) {

				// 时间格式转换
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				for (final AdvtxtHour advtxtHour : list) {
					final BaseModelData data = new BaseModelData();
					data.set("id", StringIsEmpty.isEmpty(advtxtHour.getId() + ""));
					data.set("adIdSub", StringIsEmpty.isEmpty(advtxtHour.getAdIdSub() + ""));
					data.set("keyName", StringIsEmpty.isEmpty(advtxtHour.getKeyName() + ""));
					data.set("click", StringIsEmpty.isEmpty(advtxtHour.getClick() + ""));
					if (null != advtxtHour.getDate()) {
						data.set("date", format.format(advtxtHour.getDate()) + "");
					}
					data.set("newReg", StringIsEmpty.isEmpty(advtxtHour.getNewReg() + ""));
					data.set("reg", StringIsEmpty.isEmpty(advtxtHour.getReg() + ""));
					data.set("subNewReg", StringIsEmpty.isEmpty(advtxtHour.getSubNewReg() + ""));
					datas.add(data);
				}
			}

		} catch (final Exception e) {
			// TODO: handle exception
		}
		return datas;
	}

}
