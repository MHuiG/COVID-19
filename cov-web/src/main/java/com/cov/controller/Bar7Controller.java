package com.cov.controller;


import com.cov.service.Bar7Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@Controller
@ServerEndpoint("/get_bar7_data")
public class Bar7Controller extends BaseController {

    private static Bar7Service bar7Service;

    @Override
    protected String getTitle() {
        return "get_bar7_data";
    }

    @Override
    protected void doMessage(String messages, Session session) throws InterruptedException, IOException {
        while (true) {
            String total = bar7Service.get_data();
//            System.out.println("bar7Service.test===============================>" + total);
            session.getBasicRemote().sendText(total);
            Thread.sleep(1000);
        }
    }

    //静态注入
    @Autowired
    public void setBar2Service(Bar7Service bar7Service) {
        Bar7Controller.bar7Service = bar7Service;
    }

}
