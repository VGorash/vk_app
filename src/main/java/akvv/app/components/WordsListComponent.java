package akvv.app.components;


import akvv.app.enums.Category;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class WordsListComponent {

    private final Map<Category, List<String>> wordLists;


    private List<String> getWordsList(Category category) {
        if(!wordLists.containsKey(category)){
            try {
                wordLists.put(category, readWordsFromFile(category.getPath()));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return wordLists.get(category);
    }

    private List<String> readWordsFromFile(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    public Set<String> getWordsSet(int numWords, Category category){
        List<String> words = getWordsList(category);
        Set<String> result = new HashSet<>();
        Random random = new Random();
        while (result.size()<numWords){
            result.add(words.get(random.nextInt(words.size())));
        }
        return result;
    }

    public WordsListComponent() {
        wordLists = new HashMap<>();
    }
}
