package akvv.app;

public class TimeMessage {

    private Integer content; //secondsLeft

    public TimeMessage() {
    }

    public TimeMessage(Integer secondLeft) {
        this.content = secondLeft;
    }

    public Integer getContent() {
        return content;
    }


}
