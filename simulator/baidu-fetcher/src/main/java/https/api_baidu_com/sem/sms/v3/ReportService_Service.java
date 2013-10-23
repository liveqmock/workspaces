package https.api_baidu_com.sem.sms.v3;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-10-13T00:18:41.580+08:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "ReportService", 
                  wsdlLocation = "file:/Users/jjwu/Yunio/workspaces/simulator/baidu-fetcher/src/main/java/test.wsdl",
                  targetNamespace = "https://api.baidu.com/sem/sms/v3") 
public class ReportService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("https://api.baidu.com/sem/sms/v3", "ReportService");
    public final static QName ReportService = new QName("https://api.baidu.com/sem/sms/v3", "ReportService");
    static {
        URL url = null;
        try {
            url = new URL("file:/Users/jjwu/Yunio/workspaces/simulator/baidu-fetcher/src/main/java/test.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ReportService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/Users/jjwu/Yunio/workspaces/simulator/baidu-fetcher/src/main/java/test.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ReportService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ReportService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ReportService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns ReportService
     */
    @WebEndpoint(name = "ReportService")
    public ReportService getReportService() {
        return super.getPort(ReportService, ReportService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReportService
     */
    @WebEndpoint(name = "ReportService")
    public ReportService getReportService(WebServiceFeature... features) {
        return super.getPort(ReportService, ReportService.class, features);
    }

}
