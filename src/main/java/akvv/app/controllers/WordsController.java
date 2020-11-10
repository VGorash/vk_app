package akvv.app.controllers;

import akvv.app.TimeMessage;
import akvv.app.components.WordsListComponent;
import akvv.app.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@RestController
public class WordsController {

    private final WordsListComponent wlc;


    @Autowired
    private SimpMessagingTemplate webSocket;


    public WordsController(WordsListComponent wlc) {
        this.wlc = wlc;
    }

    @GetMapping("api/words/{num}")
    public Set<String> getRandomWords(@PathVariable @Min(1) @Max(10) int num) {
        return wlc.getWordsSet(num, Category.DEFAULT);
    }

    @GetMapping("api/military/{num}")
    public Set<String> getMilitaryWords(@PathVariable @Min(1) @Max(10) int num) {
        return wlc.getWordsSet(num, Category.MILITARY);
    }



    @GetMapping("/start/{seconds}")
    public String start(@PathVariable Integer seconds) throws Exception {

        Runnable r = () -> {
            int sec = seconds;

            while (sec != 0) {
                try {
                    Thread.sleep(1000);
                    webSocket.convertAndSend("/room/time", new TimeMessage(sec));
                    sec--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread th = new Thread(r);
        th.start();

        return "super";
    }

}
