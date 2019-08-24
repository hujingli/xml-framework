package com.xmlframework.client;

import com.xmlframework.annoscan.util.PropertiesUtil;
import com.xmlframework.entity.fetch.FetchReq;
import com.xmlframework.handler.MessageHandler;
import com.xmlframework.handler.XmlHandler;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BIOClient {

    /**
     * 服务端地址
     */
    private String addr;
    /**
     * 服务端端口
     */
    private int port;

    /**
     * 信息初始化
     */
    private void init() {
        this.addr = PropertiesUtil.getPropByName("socket.addr");
        this.port = Integer.parseInt(PropertiesUtil.getPropByName("socket.port"));
    }

    private void start() {
        this.init();

        Socket socket;
        OutputStream os;
        InputStream is;
        try {
            socket = new Socket(this.addr, this.port);

            os = socket.getOutputStream();

            FetchReq req = RequestFactory.genFetchReq();

            String xmlStr = XmlHandler.objectToXmlString(req);
            String data = MessageHandler.addLenPrefix(xmlStr);
            os.write(data.getBytes());
            os.flush();

            is = socket.getInputStream();

            String ret = readData(is);

            System.out.println(ret);

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    private String readData(InputStream is) throws IOException {
        String dataLen = MessageHandler.readByLen(is, 6);

        return MessageHandler.readByLen(is, Integer.parseInt(dataLen));
    }

    public static void main(String[] args) {
        new BIOClient().start();
    }
}
