package com.hujingli.share.server;

import com.hujingli.xmler.core.TransProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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
            // 自动处理请求
            String retStr = this.autoProcess(socket);

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

    private String autoProcess(Socket socket) throws Exception {
        return TransProxy.call(socket);
    }


    private void writeToClient(OutputStream os, String data) throws IOException {
        os.write(data.getBytes());
        os.flush();
    }
}
