package com.xmlframework.client;

import com.xmlframework.annoscan.util.PropertiesUtil;

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

        long start = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            ClientHandler serverHandler = new ClientHandler("client" + i, this.addr, this.port);
            Thread t = new Thread(serverHandler);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();

        System.out.println("finished" + (end - start));
    }

    public static void main(String[] args) {
        new BIOClient().start();
    }
}
