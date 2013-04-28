/****************************************************************
 *  文件名     ： TestCommon.java
 *  日期         :  2012-9-4
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.test;

<<<<<<< HEAD
import org.junit.Before;
import org.junit.Test;

import com.iwgame.xvalidators.Xvalidator;
import com.iwgame.xvalidators.annotation.IntType;
import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;
=======
import jjwu.xdeveloper.xvalidators.Xvalidator;
import jjwu.xdeveloper.xvalidators.annotation.IntType;
import jjwu.xdeveloper.xvalidators.annotation.IwAnnotation;
import jjwu.xdeveloper.xvalidators.annotation.NotEmpty;

import org.junit.Before;
import org.junit.Test;

>>>>>>> xdeveloper-xvalidator

/**
 * @ClassName: TestCommon
 * @Description: TODO(...)
 * @author: 吴君杰
 * @email: wujunjie@iwgame.com
 * @date: 2012-9-4下午05:00:16
 * @Version: 1.0
 */
public class TestCommon {

	private User user;

	@Before
	public void initialization() {
		user = new User();
		user.setName("wujunjie");
		user.setAge(18);

	}

	@Test
	public void test() {
		if (Xvalidator.getInstance().validate(user)) {
			System.out.println("验证通过!");
		} else {
			System.err.println("不合法参数!");
		}
	}
}

class User implements IwAnnotation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3566293843715787399L;

	@NotEmpty
	private String name;

	@IntType(min = 18, max = 100)
	private int age;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

}
