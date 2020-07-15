package com.cov.controller;

import javax.websocket.*;
import java.io.IOException;


abstract public class BaseController {

    @OnMessage
    public void onMessage(String messages, Session session)
            throws IOException, InterruptedException {
        doMessage(messages, session);
    }

    protected abstract void doMessage(String messages, Session session) throws InterruptedException, IOException;

    @OnError
    public void onError(Session session, Throwable error) {
//        System.out.println("RORRE...");
//        error.printStackTrace();
    }

    @OnOpen
    public void onOpen() {
        String title = getTitle();
        System.out.println(title + ":连接到服务了*************");
    }

    protected abstract String getTitle();

    @OnClose
    public void onClose() {
        System.out.println("Connection closed....");
    }
}
