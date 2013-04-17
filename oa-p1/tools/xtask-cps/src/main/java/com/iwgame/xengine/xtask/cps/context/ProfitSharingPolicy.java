/**      
 * ProfitSharingPolicy.java Create on 2012-5-16     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.context;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ProfitSharingPolicy
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-16 上午10:34:49
 * @Version 1.0
 * 
 */
public class ProfitSharingPolicy {

	public class BounsPolicy {

		private int min;
		private int max;
		private double props;
		private double quota;

		/**
		 * @param min
		 * @param max
		 * @param props
		 * @param quota
		 */
		public BounsPolicy(final int min, final int max, final double props,
				final double quota) {
			super();
			this.min = min;
			this.max = max;
			this.props = props;
			this.quota = quota;
		}

		public int getMin() {
			return min;
		}

		public void setMin(final int min) {
			this.min = min;
		}

		public int getMax() {
			return max;
		}

		public void setMax(final int max) {
			this.max = max;
		}

		public double getProps() {
			return props;
		}

		public void setProps(final double props) {
			this.props = props;
		}

		public double getQuota() {
			return quota;
		}

		public void setQuota(final double quota) {
			this.quota = quota;
		}

	}

	public class AwardPolicy {

		private double consume;
		private double award;

		/**
		 * @param consume
		 * @param award
		 */
		public AwardPolicy(final double consume, final double award) {
			super();
			this.consume = consume;
			this.award = award;
		}

		public double getConsume() {
			return consume;
		}

		public void setConsume(final double consume) {
			this.consume = consume;
		}

		public double getAward() {
			return award;
		}

		public void setAward(final double award) {
			this.award = award;
		}

	}

	List<BounsPolicy> bounsPolicies;
	List<AwardPolicy> awardPolicies;

	/**
	 * 
	 */
	public ProfitSharingPolicy() {
		bounsPolicies = new ArrayList<ProfitSharingPolicy.BounsPolicy>();
		awardPolicies = new ArrayList<ProfitSharingPolicy.AwardPolicy>();
	}

	public List<BounsPolicy> getBounsPolicies() {
		return bounsPolicies;
	}

	public List<AwardPolicy> getAwardPolicies() {
		return awardPolicies;
	}

	public void addBounsPolicy(final int min, final int max,
			final double props, final double quota) {
		bounsPolicies.add(new BounsPolicy(min, max, props, quota));
	}

	public void addAwardPolicy(final double consume, final double award) {
		awardPolicies.add(new AwardPolicy(consume, award));
	}

}
