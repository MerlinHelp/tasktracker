package src.lib.time;

import java.time.LocalDateTime;

import src.lib.time.ITime;

public class JavaTime implements ITime {

    TimeStyle timeStyle = TimeStyle.AMERICAN;

    enum TimeStyle {
        AMERICAN,
        NONAMERICAN
    }

    public JavaTime() {
        timeStyle = TimeStyle.AMERICAN;
    }

    // TODO: Change current time format depending on STYLE chosen
    @Override
    public String getCurrentTime() {
        return LocalDateTime.now().toString();
    }

    public int getCurrentTimeNanoSeconds() {
        return LocalDateTime.now().getNano();
    }

    public void setAmericanDateFormat() {
        this.timeStyle = TimeStyle.AMERICAN;
    }
}
