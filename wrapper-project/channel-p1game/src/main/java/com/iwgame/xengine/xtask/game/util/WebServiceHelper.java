package com.iwgame.xengine.xtask.game.util;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.iwgame.xconnector.exeception.GMConnectException;

public class WebServiceHelper {

	/**
	 * ws通用调用接口
	 * 
	 * @param endpoint
	 *            ws 地址
	 * @param nameSpace
	 *            nameSpace
	 * @param funName
	 *            方法名称
	 * @param inParamName
	 *            输入参数名称数组
	 * @param inParamType
	 *            输入参数类型数组(string,boolean,double,float,int,integer,long,short,
	 *            byte...其他类型查看org.apache.axis.Constants.URI_DEFAULT_SCHEMA_XSD)
	 * @param inparamValue
	 *            输入参数值数组
	 * @param returnType
	 *            返回类型
	 * @return
	 * @throws Exception
	 */
	public static Object callWSURL(String endpoint, String nameSpace, String funName, String[] inParamName,
			String[] inParamType, Object[] inparamValue, String returnType) throws Exception {
		try {
			Service service = new Service();
			// 创建一个call对象
			Call call = (Call) service.createCall();
			// 设置目标地址，即webservice路径
			call.setTargetEndpointAddress(endpoint);
			// 设置操作名称，即方法名称
			call.setOperationName(new QName(nameSpace, funName));
			// 设置方法参数
			for (int i = 0; i < inParamName.length; i++) {
				call.addParameter(new QName(nameSpace, inParamName[i]), new QName(Constants.URI_DEFAULT_SCHEMA_XSD,
						inParamType[i]), ParameterMode.IN);
			}
			// 设置返回值类型
			// 对于返回是字符串数组的返回类型只有这两种可行
			call.setReturnType(new QName(Constants.URI_DEFAULT_SCHEMA_XSD, returnType));
			// call.setReturnClass(java.lang.String[].class);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(nameSpace + funName);
			return call.invoke(inparamValue);
		} catch (Exception e) {
			throw new GMConnectException(e);
		}
	}

//	@Test
//	public void testWebService() throws Exception {
//		String endpoint = "http://sm.iwgame.dev/service/game-card/do/key/m1yvPFJJiHT8Mcjiuzai";
//		String nameSpace = "http://sm.iwgame.dev/service/game-card/do/key/m1yvPFJJiHT8Mcjiuzai";
//		Object a = callWSURL(endpoint, nameSpace, "active", new String[] { "name", "cardid", "cardpwd", "zone" },
//				new String[] { "string", "string", "string", "string" }, new Object[] { "sniper2", "123", "pwd001",
//						"dx1" }, "boolean");
//		System.out.println(a);
//
//	}
}
