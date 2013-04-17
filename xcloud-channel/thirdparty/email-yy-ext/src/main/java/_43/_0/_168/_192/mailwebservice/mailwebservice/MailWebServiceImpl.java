
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;

@WebService(serviceName = "MailWebService", targetNamespace = "http://192.168.0.43/MailWebService/MailWebService.asmx", endpointInterface = "_43._0._168._192.mailwebservice.mailwebservice.MailWebServiceSoap")
public class MailWebServiceImpl
    implements MailWebServiceSoap
{


    public WebServiceReportDetailReturnDTO getWebServiceReportDetail(EaseyeUserAccountDTO easeyeUserAccountDTO, String templateName, XMLGregorianCalendar sentDatetimeFrom, XMLGregorianCalendar sentDatetimeTo, int sentFlag, int pageSize, int currentPageIndex) {
        throw new UnsupportedOperationException();
    }

    public EaseyeDetailedResultReturnDTO importContactByGroupExt(EaseyeUserAccountDTO easeyeUserAccountDTO, EaseyeGroupDTO easeyeGroupDTO, EaseyeMessageCustomReceiverDTO easeyeMessageReceiveDTO, String importName) {
        throw new UnsupportedOperationException();
    }

    public EaseyeMailSenderStatusReturnDTO getMailSenderStatus(EaseyeUserAccountDTO easeyeUserAccountDTO, String email, String sendEmail) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO eY_SendMailListRequest(EaseyeUserAccountDTO easeyeUserAccountDTO, EaseyeMessageTemplateDTO easeyeMessageTemplateDTO, ArrayOfEYMessageReceiveDTO ey_MessageReceiveDTOArray, EaseyeSendOptionDTO easeyeSendOptionDTO) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO sendTemplate(SendTemplateRequest sendTemplateRequest) {
        throw new UnsupportedOperationException();
    }

    public boolean accessCheck(String userName, String password) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO validateEmail(EaseyeUserAccountDTO easeyeUserAccountDTO, String email, String returnUrl) {
        throw new UnsupportedOperationException();
    }

    public void modifyGroupName(EaseyeUserAccountDTO easeyeUserAccountDTO, String oldGroupName, String newGroupName) {
        throw new UnsupportedOperationException();
    }

    public WebServiceReportReturnDTO getWebServiceSumReport(EaseyeUserAccountDTO easeyeUserAccountDTO, String templateName, XMLGregorianCalendar sentDatetimeFrom, XMLGregorianCalendar sentDatetimeTo) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO removeTemplate(EaseyeUserAccountDTO easeyeUserAccountDTO, String templateName) {
        throw new UnsupportedOperationException();
    }

    public ArrayOfReportDetail getReportDetail(EaseyeUserAccountDTO easeyeUserAccountDTO, int sentMailListId, int sentFlag, int pageSize, int currentPageIndex) {
        throw new UnsupportedOperationException();
    }

    public void createTask(EaseyeUserAccountDTO easeyeUserAccountDTO) {
        throw new UnsupportedOperationException();
    }

    public boolean queryContactByGroup(EaseyeUserAccountDTO easeyeUserAccountDTO, String groupName, String email) {
        throw new UnsupportedOperationException();
    }

    public MailSumReportReturnDTO getMailSumReport(EaseyeUserAccountDTO easeyeUserAccountDTO, String projectName, String maillistName, XMLGregorianCalendar sentDatetimeFrom, XMLGregorianCalendar sentDatetimeTo) {
        throw new UnsupportedOperationException();
    }

    public ArrayOfEaseyeGroupDTO getGroupList(EaseyeUserAccountDTO easeyeUserAccountDTO) {
        throw new UnsupportedOperationException();
    }

    public String helloWorld() {
        throw new UnsupportedOperationException();
    }

    public ArrayOfMailStatusReportDTO getSumReport(EaseyeUserAccountDTO easeyeUserAccountDTO, String projectName, String maillistName, XMLGregorianCalendar sentDatetimeFrom, XMLGregorianCalendar sentDatetimeTo) {
        throw new UnsupportedOperationException();
    }

    public ArrayOfEYMailStatusReportDTO eY_GetSumReport(EaseyeUserAccountDTO easeyeUserAccountDTO, String projectName, String maillistName, XMLGregorianCalendar sentDatetimeFrom, XMLGregorianCalendar sentDatetimeTo) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO sendMailListRequest(EaseyeUserAccountDTO easeyeUserAccountDTO, EaseyeMessageTemplateDTO easeyeMessageTemplateDTO, ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray, EaseyeSendOptionDTO easeyeSendOptionDTO) {
        throw new UnsupportedOperationException();
    }

    public EaseyeDetailedResultReturnDTO getImportFailedRecords(EaseyeUserAccountDTO easeyeUserAccountDTO, String importName, int pageSize, int currentPageIndex) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO deleteGroupByGroupName(EaseyeUserAccountDTO easeyeUserAccountDTO, String groupName) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO manageUserPoint(EaseyeUserAccountDTO easeyeUserAccountDTO, String email, int point) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO moveContactToNewGroup(EaseyeUserAccountDTO easeyeUserAccountDTO, String newGroupName, ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO deleteUser(String adminEmail, String adminPassword, String email) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO updateTemplate(EaseyeUserAccountDTO easeyeUserAccountDTO, EaseyeMessageTemplateDTO easeyeMessageTemplateDTO) {
        throw new UnsupportedOperationException();
    }

    public EaseyeDetailedResultReturnDTO eY_ImportContactByGroupDetailed(EaseyeUserAccountDTO easeyeUserAccountDTO, EaseyeGroupDTO easeyeGroupDTO, ArrayOfEYMessageReceiveDTO ey_MessageReceiveDTOArray) {
        throw new UnsupportedOperationException();
    }

    public GetGroupInfoReturnDTO getGroupInfoByGroupName(EaseyeUserAccountDTO easeyeUserAccountDTO, String GroupName) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO updateUser(EaseyeUserAccountDTO easeyeUserAccountDTO, CustomMailUser mailUser) {
        throw new UnsupportedOperationException();
    }

    public EaseyeDetailedResultReturnDTO deleteContactByGroup(EaseyeUserAccountDTO easeyeUserAccountDTO, ArrayOfString emails, String groupName) {
        throw new UnsupportedOperationException();
    }

    public AttachmentReturnDTO attachmentQuery(EaseyeUserAccountDTO easeyeUserAccountDTO, String fileName) {
        throw new UnsupportedOperationException();
    }

    public EaseyeDetailedResultReturnDTO updateRecipientsStatus(EaseyeUserAccountDTO easeyeUserAccountDTO, ArrayOfString recipients, int status) {
        throw new UnsupportedOperationException();
    }

    public EaseyeDetailedResultReturnDTO cancelMaillist(EaseyeUserAccountDTO easeyeUserAccountDTO, String mailListName) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO addUser(EaseyeUserAccountDTO easeyeUserAccountDTO, CustomMailUser mailUser) {
        throw new UnsupportedOperationException();
    }

    public MailReportDetailReturnDTO getMailReportDetail(EaseyeUserAccountDTO easeyeUserAccountDTO, int sentMailListId, int sentFlag, int pageSize, int currentPageIndex) {
        throw new UnsupportedOperationException();
    }

    public int getCountByGroupName(EaseyeUserAccountDTO easeyeUserAccountDTO, String groupName) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO importContactByGroup(EaseyeUserAccountDTO easeyeUserAccountDTO, EaseyeGroupDTO easeyeGroupDTO, ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray) {
        throw new UnsupportedOperationException();
    }

    public MailContactInActionReturnDTO getMailContactInAction(EaseyeUserAccountDTO easeyeUserAccountDTO, int sentFlag, XMLGregorianCalendar dateFrom, XMLGregorianCalendar dateTo, int pageSize, int currentPageIndex) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO sendMailListRequestByGroup(EaseyeUserAccountDTO easeyeUserAccountDTO, EaseyeMessageTemplateDTO easeyeMessageTemplateDTO, EaseyeGroupDTO easeyeGroupDTO, EaseyeSendOptionDTO easeyeSendOptionDTO) {
        throw new UnsupportedOperationException();
    }

    public EaseyeDetailedResultReturnDTO importContactByGroupDetailed(EaseyeUserAccountDTO easeyeUserAccountDTO, EaseyeGroupDTO easeyeGroupDTO, ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray, String importName) {
        throw new UnsupportedOperationException();
    }

    public AttachmentReturnDTO uploadAttachment(EaseyeUserAccountDTO easeyeUserAccountDTO, String attachmentContent, String attachmentName, String uploadMode) {
        throw new UnsupportedOperationException();
    }

    public EaseyeReturnDTO sendMail(EaseyeUserAccountDTO easeyeUserAccountDTO, EaseyeMessageReceiveDTO easeyeMessageReceiveDTO, String templateName, EaseyeSendOptionDTO easeyeSendOptionDTO) {
        throw new UnsupportedOperationException();
    }

    public ArrayOfReportMoreDetail getReportMoreDetail(EaseyeUserAccountDTO easeyeUserAccountDTO, int sentMailListId, int sentFlag, int pageSize, int currentPageIndex) {
        throw new UnsupportedOperationException();
    }

    public MailStatusReportDTO sendMailReportResponse(EaseyeUserAccountDTO easeyeUserAccountDTO, String queryId, int reportOption) {
        throw new UnsupportedOperationException();
    }

    public SmtpSendResultReturnDTO getSmtpSendResultByEmail(EaseyeUserAccountDTO easeyeUserAccountDTO, String email, XMLGregorianCalendar dtBeginTime, XMLGregorianCalendar dtEndTime) {
        throw new UnsupportedOperationException();
    }

}
