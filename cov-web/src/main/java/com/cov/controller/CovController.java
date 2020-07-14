package com.cov.controller;


import com.cov.service.CovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@Controller
@ServerEndpoint("/get_map_data")
public class CovController extends BaseController {

    private static CovService covService;

    @Override
    protected String getTitle() {
        return "get_map_data";
    }

    @Override
    protected void doMessage(String messages, Session session) throws InterruptedException, IOException {
        while (true) {
            String total = covService.test();
//            System.out.println("covService.test===============================>" + total);
            session.getBasicRemote().sendText(total);
            Thread.sleep(1000);
        }
    }

    //静态注入
    @Autowired
    public void setCovService(CovService covService) {
        CovController.covService = covService;
    }

}
