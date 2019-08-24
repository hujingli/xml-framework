package com.xmlframework.server;

import com.log.ILog;
import com.log.LogTypeEnum;
import com.log.LoggerFactory;
import com.xmlframework.annoscan.handler.ClassLoadHandler;
import com.xmlframework.annoscan.util.PropertiesUtil;
import com.xmlframework.handler.OrderHandler;
import com.xmlframework.handler.StockHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * bio and executors
 */
public class BIOServer {

    private static ILog logger = LoggerFactory.getLogger(BIOServer.class, LogTypeEnum.FILEWITHLOCK);

    private int port;

    private ExecutorService es;

    public static OrderHandler orderHandler;

    /**
     * 完成“框架”启动服务
     */
    private void init() {
        // 类加载器加载
        ClassLoadHandler.init();

        // 加载配置文件
        this.loadConfig();

        // 加载商品
        StockHandler.init();

        // 初始化线程池和订单守护线程
        es = Executors.newCachedThreadPool();
        orderHandler = new OrderHandler();
    }

    /**
     * 读取配置文件
     */
    private void loadConfig() {
        this.port = Integer.parseInt(PropertiesUtil.getPropByName("socket.port"));
    }

    /**
     * 开启服务，仅处理一种业务逻辑
     */
    private void listenAndServe() {
        this.init();

        ServerSocket serverSocket;
        Socket client;

        try {
            serverSocket = new ServerSocket(this.port);

            System.out.println("server starts at:" + this.port);

            // 一直接收客户端请求
            while(true) {
                client = serverSocket.accept();
                es.execute(new ServerHandler(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BIOServer().listenAndServe();
    }



}
