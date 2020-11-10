package akvv.app.enums;

public enum Category {
    DEFAULT("./target/words.txt"), MILITARY("./target/military_words.txt");

    private final String path;

    Category(String path){
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}
