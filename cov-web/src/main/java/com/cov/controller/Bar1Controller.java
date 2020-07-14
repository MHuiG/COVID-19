package com.cov.controller;


import com.cov.service.Bar1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@Controller
@ServerEndpoint("/get_bar1_data")
public class Bar1Controller extends BaseController {

    private static Bar1Service bar1Service;

    @Override
    protected String getTitle() {
        return "get_bar1_data";
    }

    @Override
    protected void doMessage(String messages, Session session) throws InterruptedException, IOException {
        while (true) {
            String total = bar1Service.get_data();
//            System.out.println("bar1Service.test===============================>" + total);
            session.getBasicRemote().sendText(total);
            Thread.sleep(1000);
        }
    }

    //静态注入
    @Autowired
    public void setBar1Service(Bar1Service bar1Service) {
        Bar1Controller.bar1Service = bar1Service;
    }

}
