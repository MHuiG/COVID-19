package com.cov.controller;


import com.cov.service.Bar2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@Controller
@ServerEndpoint("/get_bar2_data")
public class Bar2Controller extends BaseController {

    private static Bar2Service bar2Service;

    @Override
    protected String getTitle() {
        return "get_bar2_data";
    }

    @Override
    protected void doMessage(String messages, Session session) throws InterruptedException, IOException {
        while (true) {
            String total = bar2Service.get_data();
//            System.out.println("bar1Service.test===============================>" + total);
            session.getBasicRemote().sendText(total);
            Thread.sleep(1000);
        }
    }

    //静态注入
    @Autowired
    public void setBar2Service(Bar2Service bar2Service) {
        Bar2Controller.bar2Service = bar2Service;
    }

}
