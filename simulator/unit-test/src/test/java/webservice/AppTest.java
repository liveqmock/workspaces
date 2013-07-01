package webservice;


import org.junit.Test;

import com.iwgame.security.key.MasterKeyUtil;


public class AppTest {


	@Test
	public void getWebService() throws Throwable {
		//		Service service = new Service();
		//		Object obj = null;
		//		Call call = (Call) service.createCall();
		//		call.setTargetEndpointAddress(new URL("http://lashv10ws.shumenol.dev/main.asmx"));
		//		call.setOperationName(new QName("http://tempuri.org/", "CheckUserInput_sm"));
		//		call.addParameter(new QName("http://tempuri.org/", "fUserName"),
		//				org.apache.axis.encoding.XMLType.XSD_STRING,
		//				javax.xml.rpc.ParameterMode.IN);
		//		call.setUseSOAPAction(true);
		//		call.setReturnType(org.apache.axis.Constants.XSD_STRING);
		//		call.setSOAPActionURI("http://tempuri.org/" + "CheckUserInput_sm");
		//		obj = call.invoke(new Object[] {"aaabbb"});
		//		System.out.println(obj);

		System.out.println(MasterKeyUtil.decKey("KrvTp6mQ088Ilosd8wvco+AoBZA="));
	}

}
