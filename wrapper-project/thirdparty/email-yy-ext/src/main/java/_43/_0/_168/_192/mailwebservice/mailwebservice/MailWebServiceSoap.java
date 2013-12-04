
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.datatype.XMLGregorianCalendar;

@WebService(name = "MailWebServiceSoap", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface MailWebServiceSoap {


    @WebMethod(operationName = "GetWebServiceReportDetail", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetWebServiceReportDetail")
    @WebResult(name = "GetWebServiceReportDetailResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public WebServiceReportDetailReturnDTO getWebServiceReportDetail(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "templateName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String templateName,
        @WebParam(name = "sentDatetimeFrom", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeFrom,
        @WebParam(name = "sentDatetimeTo", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeTo,
        @WebParam(name = "sentFlag", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int sentFlag,
        @WebParam(name = "pageSize", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int pageSize,
        @WebParam(name = "currentPageIndex", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int currentPageIndex);

    @WebMethod(operationName = "ImportContactByGroupExt", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/ImportContactByGroupExt")
    @WebResult(name = "ImportContactByGroupExtResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeDetailedResultReturnDTO importContactByGroupExt(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "easeyeGroupDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeGroupDTO easeyeGroupDTO,
        @WebParam(name = "easeyeMessageReceiveDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeMessageCustomReceiverDTO easeyeMessageReceiveDTO,
        @WebParam(name = "importName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String importName);

    @WebMethod(operationName = "GetMailSenderStatus", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetMailSenderStatus")
    @WebResult(name = "GetMailSenderStatusResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeMailSenderStatusReturnDTO getMailSenderStatus(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "email", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String email,
        @WebParam(name = "sendEmail", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String sendEmail);

    @WebMethod(operationName = "EY_SendMailListRequest", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/EY_SendMailListRequest")
    @WebResult(name = "EY_SendMailListRequestResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO eY_SendMailListRequest(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "easeyeMessageTemplateDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeMessageTemplateDTO easeyeMessageTemplateDTO,
        @WebParam(name = "ey_MessageReceiveDTOArray", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        ArrayOfEYMessageReceiveDTO ey_MessageReceiveDTOArray,
        @WebParam(name = "easeyeSendOptionDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeSendOptionDTO easeyeSendOptionDTO);

    @WebMethod(operationName = "SendTemplate", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/SendTemplate")
    @WebResult(name = "SendTemplateResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO sendTemplate(
        @WebParam(name = "sendTemplateRequest", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        SendTemplateRequest sendTemplateRequest);

    @WebMethod(operationName = "AccessCheck", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/AccessCheck")
    @WebResult(name = "AccessCheckResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public boolean accessCheck(
        @WebParam(name = "userName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String password);

    @WebMethod(operationName = "ValidateEmail", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/ValidateEmail")
    @WebResult(name = "ValidateEmailResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO validateEmail(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "email", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String email,
        @WebParam(name = "returnUrl", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String returnUrl);

    @WebMethod(operationName = "ModifyGroupName", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/ModifyGroupName")
    public void modifyGroupName(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "oldGroupName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String oldGroupName,
        @WebParam(name = "newGroupName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String newGroupName);

    @WebMethod(operationName = "GetWebServiceSumReport", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetWebServiceSumReport")
    @WebResult(name = "GetWebServiceSumReportResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public WebServiceReportReturnDTO getWebServiceSumReport(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "templateName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String templateName,
        @WebParam(name = "sentDatetimeFrom", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeFrom,
        @WebParam(name = "sentDatetimeTo", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeTo);

    @WebMethod(operationName = "RemoveTemplate", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/RemoveTemplate")
    @WebResult(name = "RemoveTemplateResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO removeTemplate(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "templateName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String templateName);

    @WebMethod(operationName = "GetReportDetail", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetReportDetail")
    @WebResult(name = "GetReportDetailResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public ArrayOfReportDetail getReportDetail(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "sentMailListId", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int sentMailListId,
        @WebParam(name = "sentFlag", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int sentFlag,
        @WebParam(name = "pageSize", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int pageSize,
        @WebParam(name = "currentPageIndex", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int currentPageIndex);

    @WebMethod(operationName = "CreateTask", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/CreateTask")
    public void createTask(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO);

    @WebMethod(operationName = "QueryContactByGroup", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/QueryContactByGroup")
    @WebResult(name = "QueryContactByGroupResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public boolean queryContactByGroup(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "groupName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String groupName,
        @WebParam(name = "email", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String email);

    @WebMethod(operationName = "GetMailSumReport", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetMailSumReport")
    @WebResult(name = "GetMailSumReportResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public MailSumReportReturnDTO getMailSumReport(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "projectName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String projectName,
        @WebParam(name = "maillistName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String maillistName,
        @WebParam(name = "sentDatetimeFrom", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeFrom,
        @WebParam(name = "sentDatetimeTo", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeTo);

    @WebMethod(operationName = "GetGroupList", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetGroupList")
    @WebResult(name = "GetGroupListResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public ArrayOfEaseyeGroupDTO getGroupList(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO);

    @WebMethod(operationName = "HelloWorld", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/HelloWorld")
    @WebResult(name = "HelloWorldResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public String helloWorld();

    @WebMethod(operationName = "GetSumReport", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetSumReport")
    @WebResult(name = "GetSumReportResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public ArrayOfMailStatusReportDTO getSumReport(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "projectName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String projectName,
        @WebParam(name = "maillistName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String maillistName,
        @WebParam(name = "sentDatetimeFrom", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeFrom,
        @WebParam(name = "sentDatetimeTo", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeTo);

    @WebMethod(operationName = "EY_GetSumReport", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/EY_GetSumReport")
    @WebResult(name = "EY_GetSumReportResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public ArrayOfEYMailStatusReportDTO eY_GetSumReport(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "projectName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String projectName,
        @WebParam(name = "maillistName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String maillistName,
        @WebParam(name = "sentDatetimeFrom", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeFrom,
        @WebParam(name = "sentDatetimeTo", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar sentDatetimeTo);

    @WebMethod(operationName = "SendMailListRequest", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/SendMailListRequest")
    @WebResult(name = "SendMailListRequestResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO sendMailListRequest(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "easeyeMessageTemplateDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeMessageTemplateDTO easeyeMessageTemplateDTO,
        @WebParam(name = "easeyeMessageReceiveDTOArray", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray,
        @WebParam(name = "easeyeSendOptionDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeSendOptionDTO easeyeSendOptionDTO);

    @WebMethod(operationName = "GetImportFailedRecords", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetImportFailedRecords")
    @WebResult(name = "GetImportFailedRecordsResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeDetailedResultReturnDTO getImportFailedRecords(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "importName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String importName,
        @WebParam(name = "pageSize", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int pageSize,
        @WebParam(name = "currentPageIndex", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int currentPageIndex);

    @WebMethod(operationName = "DeleteGroupByGroupName", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/DeleteGroupByGroupName")
    @WebResult(name = "DeleteGroupByGroupNameResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO deleteGroupByGroupName(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "groupName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String groupName);

    @WebMethod(operationName = "ManageUserPoint", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/ManageUserPoint")
    @WebResult(name = "ManageUserPointResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO manageUserPoint(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "email", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String email,
        @WebParam(name = "point", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int point);

    @WebMethod(operationName = "MoveContactToNewGroup", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/MoveContactToNewGroup")
    @WebResult(name = "MoveContactToNewGroupResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO moveContactToNewGroup(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "newGroupName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String newGroupName,
        @WebParam(name = "easeyeMessageReceiveDTOArray", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray);

    @WebMethod(operationName = "DeleteUser", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/DeleteUser")
    @WebResult(name = "DeleteUserResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO deleteUser(
        @WebParam(name = "adminEmail", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String adminEmail,
        @WebParam(name = "adminPassword", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String adminPassword,
        @WebParam(name = "email", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String email);

    @WebMethod(operationName = "UpdateTemplate", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/UpdateTemplate")
    @WebResult(name = "UpdateTemplateResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO updateTemplate(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "easeyeMessageTemplateDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeMessageTemplateDTO easeyeMessageTemplateDTO);

    @WebMethod(operationName = "EY_ImportContactByGroupDetailed", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/EY_ImportContactByGroupDetailed")
    @WebResult(name = "EY_ImportContactByGroupDetailedResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeDetailedResultReturnDTO eY_ImportContactByGroupDetailed(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "easeyeGroupDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeGroupDTO easeyeGroupDTO,
        @WebParam(name = "ey_MessageReceiveDTOArray", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        ArrayOfEYMessageReceiveDTO ey_MessageReceiveDTOArray);

    @WebMethod(operationName = "GetGroupInfoByGroupName", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetGroupInfoByGroupName")
    @WebResult(name = "GetGroupInfoByGroupNameResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public GetGroupInfoReturnDTO getGroupInfoByGroupName(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "GroupName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String GroupName);

    @WebMethod(operationName = "UpdateUser", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/UpdateUser")
    @WebResult(name = "UpdateUserResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO updateUser(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "mailUser", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        CustomMailUser mailUser);

    @WebMethod(operationName = "DeleteContactByGroup", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/DeleteContactByGroup")
    @WebResult(name = "DeleteContactByGroupResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeDetailedResultReturnDTO deleteContactByGroup(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "emails", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        ArrayOfString emails,
        @WebParam(name = "groupName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String groupName);

    @WebMethod(operationName = "AttachmentQuery", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/AttachmentQuery")
    @WebResult(name = "AttachmentQueryResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public AttachmentReturnDTO attachmentQuery(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "fileName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String fileName);

    @WebMethod(operationName = "UpdateRecipientsStatus", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/UpdateRecipientsStatus")
    @WebResult(name = "UpdateRecipientsStatusResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeDetailedResultReturnDTO updateRecipientsStatus(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "recipients", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        ArrayOfString recipients,
        @WebParam(name = "status", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int status);

    @WebMethod(operationName = "CancelMaillist", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/CancelMaillist")
    @WebResult(name = "CancelMaillistResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeDetailedResultReturnDTO cancelMaillist(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "mailListName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String mailListName);

    @WebMethod(operationName = "AddUser", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/AddUser")
    @WebResult(name = "AddUserResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO addUser(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "mailUser", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        CustomMailUser mailUser);

    @WebMethod(operationName = "GetMailReportDetail", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetMailReportDetail")
    @WebResult(name = "GetMailReportDetailResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public MailReportDetailReturnDTO getMailReportDetail(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "sentMailListId", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int sentMailListId,
        @WebParam(name = "sentFlag", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int sentFlag,
        @WebParam(name = "pageSize", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int pageSize,
        @WebParam(name = "currentPageIndex", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int currentPageIndex);

    @WebMethod(operationName = "GetCountByGroupName", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetCountByGroupName")
    @WebResult(name = "GetCountByGroupNameResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public int getCountByGroupName(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "groupName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String groupName);

    @WebMethod(operationName = "ImportContactByGroup", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/ImportContactByGroup")
    @WebResult(name = "ImportContactByGroupResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO importContactByGroup(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "easeyeGroupDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeGroupDTO easeyeGroupDTO,
        @WebParam(name = "easeyeMessageReceiveDTOArray", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray);

    @WebMethod(operationName = "GetMailContactInAction", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetMailContactInAction")
    @WebResult(name = "GetMailContactInActionResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public MailContactInActionReturnDTO getMailContactInAction(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "sentFlag", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int sentFlag,
        @WebParam(name = "dateFrom", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar dateFrom,
        @WebParam(name = "dateTo", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar dateTo,
        @WebParam(name = "pageSize", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int pageSize,
        @WebParam(name = "currentPageIndex", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int currentPageIndex);

    @WebMethod(operationName = "SendMailListRequestByGroup", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/SendMailListRequestByGroup")
    @WebResult(name = "SendMailListRequestByGroupResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO sendMailListRequestByGroup(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "easeyeMessageTemplateDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeMessageTemplateDTO easeyeMessageTemplateDTO,
        @WebParam(name = "easeyeGroupDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeGroupDTO easeyeGroupDTO,
        @WebParam(name = "easeyeSendOptionDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeSendOptionDTO easeyeSendOptionDTO);

    @WebMethod(operationName = "ImportContactByGroupDetailed", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/ImportContactByGroupDetailed")
    @WebResult(name = "ImportContactByGroupDetailedResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeDetailedResultReturnDTO importContactByGroupDetailed(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "easeyeGroupDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeGroupDTO easeyeGroupDTO,
        @WebParam(name = "easeyeMessageReceiveDTOArray", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray,
        @WebParam(name = "importName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String importName);

    @WebMethod(operationName = "UploadAttachment", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/UploadAttachment")
    @WebResult(name = "UploadAttachmentResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public AttachmentReturnDTO uploadAttachment(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "attachmentContent", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String attachmentContent,
        @WebParam(name = "attachmentName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String attachmentName,
        @WebParam(name = "uploadMode", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String uploadMode);

    @WebMethod(operationName = "SendMail", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/SendMail")
    @WebResult(name = "SendMailResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public EaseyeReturnDTO sendMail(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "easeyeMessageReceiveDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeMessageReceiveDTO easeyeMessageReceiveDTO,
        @WebParam(name = "templateName", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String templateName,
        @WebParam(name = "easeyeSendOptionDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeSendOptionDTO easeyeSendOptionDTO);

    @WebMethod(operationName = "GetReportMoreDetail", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetReportMoreDetail")
    @WebResult(name = "GetReportMoreDetailResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public ArrayOfReportMoreDetail getReportMoreDetail(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "sentMailListId", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int sentMailListId,
        @WebParam(name = "sentFlag", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int sentFlag,
        @WebParam(name = "pageSize", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int pageSize,
        @WebParam(name = "currentPageIndex", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int currentPageIndex);

    @WebMethod(operationName = "SendMailReportResponse", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/SendMailReportResponse")
    @WebResult(name = "SendMailReportResponseResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public MailStatusReportDTO sendMailReportResponse(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "queryId", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String queryId,
        @WebParam(name = "reportOption", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        int reportOption);

    @WebMethod(operationName = "GetSmtpSendResultByEmail", action = "http://192.168.0.43/MailWebService/MailWebService.asmx/GetSmtpSendResultByEmail")
    @WebResult(name = "GetSmtpSendResultByEmailResult", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
    public SmtpSendResultReturnDTO getSmtpSendResultByEmail(
        @WebParam(name = "easeyeUserAccountDTO", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        EaseyeUserAccountDTO easeyeUserAccountDTO,
        @WebParam(name = "email", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        String email,
        @WebParam(name = "dtBeginTime", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar dtBeginTime,
        @WebParam(name = "dtEndTime", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx")
        XMLGregorianCalendar dtEndTime);

}
