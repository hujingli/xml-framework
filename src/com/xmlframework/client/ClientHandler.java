package com.xmlframework.client;

import com.xmlframework.entity.fetch.FetchItem;
import com.xmlframework.entity.fetch.FetchReq;
import com.xmlframework.entity.fetch.FetchResp;
import com.xmlframework.entity.seckill.SecKillReq;
import com.xmlframework.entity.seckill.SecKillResp;
import com.xmlframework.handler.MessageHandler;
import com.xmlframework.handler.XmlHandler;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ClientHandler implements Runnable {

    private String addr;
    private int port;
    private String name;

    public ClientHandler(String name, String addr, int port) {
        this.addr = addr;
        this.port = port;
        this.name = name;
    }

    @Override
    public void run() {

        Random random = new Random();
        while (true) {
            // do fetch
            List<FetchItem> list = doFetch();
            if (list == null || list.size() == 0) {
                break;
            }

            // do sec kill
            int idx = random.nextInt(list.size());
            String itemId = list.get(idx).getItemId();
            // return code
            SecKillResp resp = doSecKill(itemId);
            if (resp != null) {
                if ("02".equals(resp.getBody().getOrderStatus())) {
                    System.out.println(this.name + " 抢购 " + itemId + "失败， 因为 " + resp.getHeader().getRespMsg());
                }
            }
        }

    }

    private SecKillResp doSecKill(String itemId) {
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            socket = new Socket(this.addr, this.port);

            os = socket.getOutputStream();


            SecKillReq req = RequestFactory.genSecKillReq(this.name, itemId);

            String xmlStr = XmlHandler.objectToXmlString(req);
            String data = MessageHandler.addLenPrefix(xmlStr);
            os.write(data.getBytes());
            os.flush();

            is = socket.getInputStream();

            String ret = readData(is);
            SecKillResp resp = (SecKillResp) XmlHandler.xmlStringToObject(ret, SecKillResp.class);
            return resp;
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

        return null;
    }

    private List<FetchItem> doFetch() {
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
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

            FetchResp resp = (FetchResp) XmlHandler.xmlStringToObject(ret, FetchResp.class);
            if (resp.getBody().getNums() > 0) {
                return resp.getBody().getRows().stream().map(wrapper -> wrapper.getRow()).collect(Collectors.toList());
            } else {
                return null;
            }
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
        return null;
    }

    private String readData(InputStream is) throws IOException {
        String dataLen = MessageHandler.readByLen(is, 6);

        return MessageHandler.readByLen(is, Integer.parseInt(dataLen));
    }

}
