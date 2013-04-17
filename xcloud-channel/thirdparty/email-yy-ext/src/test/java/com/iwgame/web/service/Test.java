// package com.iwgame.web.service;
//
// import java.io.IOException;
//
// import _43._0._168._192.mailwebservice.mailwebservice.EaseyeReturnDTO;
// import _43._0._168._192.mailwebservice.mailwebservice.MailUser;
// import _43._0._168._192.mailwebservice.mailwebservice.SendTemplateRequest;
// import _43._0._168._192.mailwebservice.mailwebservice.SingleRecipient;
// import _43._0._168._192.mailwebservice.mailwebservice_asmx.MailWebService;
// import _43._0._168._192.mailwebservice.mailwebservice_asmx.MailWebService2;
// import
// _43._0._168._192.mailwebservice.mailwebservice_asmx.MailWebServiceSoap;
//
// public class Test {
// public static void main(String[] args) throws IOException {
// long time = System.currentTimeMillis();
// sendTemplate2();
// System.out.println((System.currentTimeMillis() - time));
//
// }
//
// public static void sendTemplate() throws IOException {
// MailWebService webser = new MailWebService();
// MailWebServiceSoap mailProxy = webser.getMailWebServiceSoap();
//
// try {
// SendTemplateRequest sendTemplateRequest = new SendTemplateRequest();
//
// SingleRecipient singleRecipient = new SingleRecipient();
// singleRecipient.setEmail("335201@qq.com");// 收件人
//
// MailUser mailUser = new MailUser();
// mailUser.setEmail("jqye@shumenol.com");
// mailUser.setPassword("chinayjq1124");
// sendTemplateRequest.setMailUser(mailUser);// 帐户信息(必填)
// sendTemplateRequest.setFromEmail("kefu@shumenol.com");// 发送者Email(必填)
// sendTemplateRequest.setSingleRecipient(singleRecipient);// 接收者信息(必填)
// sendTemplateRequest.setTemplateName("蜀门vip回归");// 模板名称(必填)
//
// EaseyeReturnDTO dto = mailProxy.sendTemplate(sendTemplateRequest);
// System.out.println(dto.getErrorInfo());
// System.out.println(dto.getErrorCode());
//
// } catch (RuntimeException e) {
// e.printStackTrace();
// System.out.println(e.getMessage());
// }
// }
//
// public static void sendTemplate2() throws IOException {
// MailWebService2 webser = new
// MailWebService2("http://app.easeye.com.cn/MailWebService/MailWebService.asmx");
// MailWebServiceSoap mailProxy = webser.getMailWebServiceSoap();
//
// try {
// SendTemplateRequest sendTemplateRequest = new SendTemplateRequest();
//
// SingleRecipient singleRecipient = new SingleRecipient();
// singleRecipient.setEmail("335201@qq.com");// 收件人
//
// MailUser mailUser = new MailUser();
// mailUser.setEmail("jqye@shumenol.com");
// mailUser.setPassword("chinayjq1124");
// sendTemplateRequest.setMailUser(mailUser);// 帐户信息(必填)
// sendTemplateRequest.setFromEmail("kefu@shumenol.com");// 发送者Email(必填)
// sendTemplateRequest.setSingleRecipient(singleRecipient);// 接收者信息(必填)
// sendTemplateRequest.setTemplateName("蜀门vip回归");// 模板名称(必填)
//
// EaseyeReturnDTO dto = mailProxy.sendTemplate(sendTemplateRequest);
// System.out.println(dto.getErrorInfo());
// System.out.println(dto.getErrorCode());
//
// } catch (RuntimeException e) {
// e.printStackTrace();
// System.out.println(e.getMessage());
// }
// }
//
// }
