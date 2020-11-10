package akvv.app.controllers;

import akvv.app.components.WordsListComponent;
import akvv.app.enums.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@RestController
public class WordsController {

    private final WordsListComponent wlc;

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
}
