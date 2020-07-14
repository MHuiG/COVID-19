package com.cov.controller;

import com.cov.service.Line6Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Controller
@ServerEndpoint("/get_line6_data")
public class Line6Controller extends BaseController {
    private static Line6Service Service;

    @Override
    protected String getTitle() {
        return "get_line6_data";
    }

    @Override
    protected void doMessage(String messages, Session session) throws InterruptedException, IOException {
        while (true) {
            String total = Service.test();
//            System.out.println("covService.test===============================>" + total);
            session.getBasicRemote().sendText(total);
            Thread.sleep(1000);
        }
    }

    //静态注入
    @Autowired
    public void setCovService(Line6Service Service) {
        Line6Controller.Service = Service;
    }
}
