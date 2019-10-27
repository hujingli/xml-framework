package com.hujingli.share.client;

import com.hujingli.share.bean.common.XMLReqHeader;
import com.hujingli.share.bean.service.add.XMLAddReq;
import com.hujingli.share.bean.service.add.XMLAddReqBody;
import com.hujingli.xmler.util.MessageUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BIOClient {

    public static void main(String[] args) {
        String  addr = "127.0.0.1";
        int port = 8080;
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            socket = new Socket(addr, port);
            os = socket.getOutputStream();

            XMLReqHeader xmlReqHeader = new XMLReqHeader("1001");
            XMLAddReqBody xmlAddReqBody = new XMLAddReqBody(-1, 2);
            XMLAddReq xmlAddReq = new XMLAddReq(xmlReqHeader, xmlAddReqBody);

            String data = MessageUtil.objectToXmlStringWithPrefix(xmlAddReq);
            os.write(data.getBytes());
            os.flush();

            System.out.println("send: " + data);

            is = socket.getInputStream();

            String ret = MessageUtil.checkoutData(is);

            System.out.println("recv: " + ret);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (os != null) os.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
