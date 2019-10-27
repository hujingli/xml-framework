package com.hujingli.share.server;

import com.hujingli.xmler.handler.ClassLoadHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BIOServer {

    private int port = 8080;

    private ExecutorService es;


    private void startup() {
        ClassLoadHandler.init();

        es = new ThreadPoolExecutor(200, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());


    }

    private void listenAndServe() {
        this.startup();

        ServerSocket serverSocket;
        Socket client;

        try {
            serverSocket = new ServerSocket(this.port);

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
