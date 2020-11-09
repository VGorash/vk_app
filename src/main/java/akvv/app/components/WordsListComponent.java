package akvv.app.components;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class WordsListComponent {

    private static final String PATH_TO_FILE = "words.txt";


    private List <String> words;

    private void setWordsList() {
        try {
            words = readWordsFromFile(PATH_TO_FILE);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private List<String> readWordsFromFile(String path) throws IOException {
        String real_path = System.getProperty("user.home") + File.separator + path;
        return Files.readAllLines(Paths.get(real_path));
    }

    public List<String> updateWords() {
        setWordsList();
        return words;
    }

    public List<String> getWords() {
        return words;
    }

    public WordsListComponent() {
        setWordsList();
    }
}
