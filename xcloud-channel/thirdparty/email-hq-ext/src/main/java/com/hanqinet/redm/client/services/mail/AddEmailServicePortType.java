
package com.hanqinet.redm.client.services.mail;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.hanqinet.redm.client.model.mail.BasalMailData;

@WebService(name = "AddEmailServicePortType", targetNamespace = "http://mail.services.redm.hanqinet.com")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AddEmailServicePortType {

	@WebMethod(operationName = "AddEmail", action = "")
	@WebResult(name = "out", targetNamespace = "http://mail.services.redm.hanqinet.com")
	public Integer addEmail(
			@WebParam(name = "in0", targetNamespace = "http://mail.services.redm.hanqinet.com") String in0,
			@WebParam(name = "in1", targetNamespace = "http://mail.services.redm.hanqinet.com") String in1,
			@WebParam(name = "in2", targetNamespace = "http://mail.services.redm.hanqinet.com") BasalMailData in2);

}
