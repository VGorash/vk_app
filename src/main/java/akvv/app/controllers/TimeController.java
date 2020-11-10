package akvv.app.controllers;

import akvv.app.responses.TimeMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class TimeController {

    private final SimpMessagingTemplate webSocket;

    public TimeController(SimpMessagingTemplate webSocket){
        this.webSocket = webSocket;
    }

    @GetMapping("/start/{seconds}")
    public String start(@PathVariable Integer seconds) {
        final String id = UUID.randomUUID().toString();
        Runnable r = () -> {
            int sec = seconds;
            while (sec != 0) {
                try {
                    Thread.sleep(1000);
                    webSocket.convertAndSend("/room/"+id+"/time", new TimeMessage(sec));
                    sec--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread th = new Thread(r);
        th.start();

        return id;
    }
}
