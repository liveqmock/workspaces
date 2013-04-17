/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： Torch.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.model;

import java.io.Serializable;

/**
 * 类说明
 * @简述： 积分
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-5-24 上午11:32:53
 */
public class Torch  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -546812970652726594L;
	
    
	private String g_pay_amount; //充值金币	
	private String torch_id;  //积分ID
	private String torch_name; //积分名称
	private String torch_num;  //积分数量
	
	
	public String getG_pay_amount() {
		return g_pay_amount;
	}
	public void setG_pay_amount(String g_pay_amount) {
		this.g_pay_amount = g_pay_amount;
	}
	public String getTorch_id() {
		return torch_id;
	}
	public void setTorch_id(String torch_id) {
		this.torch_id = torch_id;
	}
	public String getTorch_name() {
		return torch_name;
	}
	public void setTorch_name(String torch_name) {
		this.torch_name = torch_name;
	}
	public String getTorch_num() {
		return torch_num;
	}
	public void setTorch_num(String torch_num) {
		this.torch_num = torch_num;
	}
	
	
}
