package akvv.app.controllers;

import akvv.app.components.WordsListComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
public class WordsController {

    private final WordsListComponent wlc;

    public WordsController(WordsListComponent wlc) {
        this.wlc = wlc;
    }

    @GetMapping("api/words/{num}")
    public Set<String> getRandomWords(@PathVariable @Min(1) @Max(10) int num) {
        List<String> words        = wlc.getWords();
        Set<String> selectedWords = new HashSet<>();
        Random random             = new Random();

        while (selectedWords.size() != num) {
            selectedWords.add(
                    words.get(random.nextInt(words.size()))
            );
        }

        return selectedWords;
    }
}
