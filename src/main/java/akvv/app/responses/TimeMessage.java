package akvv.app.responses;

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
