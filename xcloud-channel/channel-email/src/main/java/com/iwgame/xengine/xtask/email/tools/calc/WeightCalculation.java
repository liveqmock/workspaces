/****************************************************************
 *  文件名     ： WeightCalculation.java
 *  日期         :  2012-12-31
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.email.tools.calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iwgame.xengine.xtask.email.cache.WeightModelCache;
import com.iwgame.xengine.xtask.email.model.WeightModel;

/**
 * @类名: WeightCalculation
 * @描述: 权重算法的实现类
 * 
 * @作者: 吴君杰
 * @邮件: wujunjie@iwgame.com
 * @日期: 2012-12-31下午10:31:44
 * @版本: 1.0
 */
@Component
public class WeightCalculation {

	@Resource
	private WeightModelCache weightModelCache;

	private Logger logger = Logger.getLogger(WeightCalculation.class);

	public Map<Integer, List<WeightModel>> tempWeightCache = new Hashtable<Integer, List<WeightModel>>();

	/**
	 * 取得当前最大权重的对象,对权重排序,并对权重进行运算
	 * 
	 * @param channel_id
	 * @return
	 */
	public WeightModel getMaxWeight(int channel_id, String failed) {

		// 防止加减权重出现并发情况
		synchronized (this) {
			WeightModel resutl = null;

			// 缓存中 取出权重对象列表
			List<WeightModel> weightModels = getWeightForCache(channel_id);
			if (null == weightModels) {
				return null;
			} else {
				// 如果只有一个模板 直接返回,不在做加减操作
				if (weightModels.size() == 1 && weightModels.get(0).getChannel_weight() > 0) {
					return weightModels.get(0);
				}

				// 权重排序
				Collections.sort(weightModels, Collections.reverseOrder());

				List<WeightModel> newList = new ArrayList<WeightModel>();
				for (int i = 1; i <= weightModels.size(); i++) {
					WeightModel weightModel = weightModels.get(i - 1);
					if (i == 1) {
						// 克隆一个对象,保存一个新的副本,返回出原始的计算前的数据,以免计算后影响现有对象
						resutl = (WeightModel) weightModel.clone();
						// 然后计算
						// 如果某个通道有错误信息,直接把权重设置为0
						if (null != failed && !"".equals(failed)) {
							weightModel.setChannel_weight(weightModel.getChannel_weight() - 2 * 2);
						} else {
							weightModel.setChannel_weight(weightModel.getChannel_weight() - 1);
						}
					}
					newList.add(weightModel);
				}
				// 更新的数据再放回临时缓存
				tempWeightCache.put(channel_id, newList);
				return resutl;
			}

		}
	}

	/**
	 * 缓存中取得权重对象,缓存中没有,读取数据库
	 * 
	 * @param channel_id
	 * @return
	 */
	private List<WeightModel> getWeightForCache(int channel_id) {

		// 第一次从临时缓存去取可能为空
		List<WeightModel> weightModels = tempWeightCache.get(channel_id);
		if (weightModels == null || weightModels.size() == 0) {
			// 从权重缓存取数据,取不到从数据库中读取
			weightModels = weightModelCache.getWeightModelsByChanenlid(channel_id);
			if (weightModels != null && weightModels.size() >= 1) {
				// 判断是否所有权重是否减为0
				for (WeightModel weightModel : weightModels) {
					if (weightModel.getChannel_weight() == 0) {
						continue;
					} else {
						tempWeightCache.put(channel_id, cloneObject(weightModels));
						return tempWeightCache.get(channel_id);
					}
				}
				logger.error("警告: 当前通道 [" + channel_id + "] 的模板列表中的权重值都为 0");
				return null;
			} else {
				logger.error("警告: 当前通道 [" + channel_id + "] 的模板里表为null");
				return null;
			}

		} else {

			// 判断是否所有权重是否减为0
			for (WeightModel weightModel : weightModels) {
				if (weightModel.getChannel_weight() == 0) {
					continue;
				} else {
					return weightModels;
				}
			}

			// 当所有的通道的权重为0了,重新取最新缓存(原始缓存)
			List<WeightModel> prototypes = weightModelCache.getWeightModelsByChanenlid(channel_id);
			// 可能出现删除通道ID的情况
			if (prototypes == null || prototypes.size() == 0) {
				tempWeightCache.remove(channel_id);
				logger.info("数据库中和原始缓存中未找到通道ID[" + channel_id + "]的模板列表,删除临时缓存中数据!");
				return null;
			} else {
				tempWeightCache.remove(channel_id);
				tempWeightCache.put(channel_id, cloneObject(prototypes));
				return tempWeightCache.get(channel_id);
			}

		}
	}

	/**
	 * 对象克隆,防止修改缓存中源数据
	 * 
	 * @param src
	 * @return
	 */
	private List<WeightModel> cloneObject(List<WeightModel> src) {
		List<WeightModel> dest = new ArrayList<WeightModel>();
		for (WeightModel weightModel : src) {
			dest.add((WeightModel) weightModel.clone());
		}
		return dest;
	}

	public void setWeightForZoreByChannelid(int channelid) {
		List<WeightModel> list = tempWeightCache.get(channelid);
		if (null != list && list.size() > 0) {
			for (WeightModel weightModel : list) {
				if (null != weightModel) {
					weightModel.setChannel_weight(0);
				}
			}
		}

	}

	public void setWeightAllForZore() {
		Set<Integer> keys = tempWeightCache.keySet();
		for (Integer key : keys) {
			if (null != key) {
				List<WeightModel> list = tempWeightCache.get(key);
				if (null != list && list.size() > 0) {
					for (WeightModel weightModel : list) {
						if (null != weightModel) {
							weightModel.setChannel_weight(0);
						}
					}
				}
			}
		}
	}

}
