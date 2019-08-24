package com.xmlframework.server;

import com.xmlframework.annoscan.entity.ReqHandler;
import com.xmlframework.annoscan.handler.ControllerHandler;
import com.xmlframework.handler.MessageHandler;
import com.xmlframework.util.ReflectUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            // 解析报文
            is = this.socket.getInputStream();
            String xmlStr = this.readData(is);

            // 自动处理请求
            String retStr = this.autoProcess(xmlStr);

            // 响应
            os = this.socket.getOutputStream();
            writeToClient(os, retStr);
        } catch (Exception e) {
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

    /**
     * 从 InputStream 中解析出xml报文
     * @param is InputStream
     * @return xml string
     * @throws IOException e
     */
    private String readData(InputStream is) throws IOException {
        String dataLen = MessageHandler.readByLen(is, 6);

        return MessageHandler.readByLen(is, Integer.parseInt(dataLen));
    }

    /**
     * 根据 auto scan，半自动调用方法
     *
     * @param data data string
     * @return xml string
     * @throws Exception e
     */
    private String autoProcess(String data) throws Exception {
        // decode 出 reqCode
        // 根据 regex 取出<reqCode></reqCode>之间的具体业务代码，再从从Handler中获取所要执行方法的 Class 和 Method
        String code = extractCode(data);
        if ("".equals(code)) {
            return "";
        }

        // invoke方法获得返回值(xml string)
        ReqHandler handler = ControllerHandler.getHandler(code);

        Object obj = ReflectUtil.invokeMethod(handler.getControllerClass(), handler.getActionMethod(), data);

        return (String) obj;
    }

    /**
     * 从xml string中获取reqCode
     *
     * @param data xml string
     * @return req code
     */
    private String extractCode(String data) {

        String pattern = "(<reqCode>)(.*?)(</reqCode>)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(data);

        if (m.find()) {
            return m.group(2);
        }

        return "";
    }

    /**
     * 将数据写回客户端
     * @param os output stream
     * @param data data
     * @throws IOException e
     */
    private void writeToClient(OutputStream os, String data) throws IOException {
        String retMsg = MessageHandler.addLenPrefix(data);
        os.write(retMsg.getBytes());
        os.flush();
    }
}