package test;

import com.xmlframework.client.RequestFactory;
import com.xmlframework.entity.fetch.FetchReq;
import com.xmlframework.entity.fetch.FetchResp;
import com.xmlframework.handler.XmlHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

public class Test {

    public static void main(String[] args) throws JAXBException {
        FetchReq req = RequestFactory.genFetchReq();
        String str = XmlHandler.objectToXmlString(req);

        FetchReq reqG = (FetchReq) XmlHandler.xmlStringToObject(str, FetchReq.class);
        System.out.println(reqG.getHeader().getReqCode());
    }

}
