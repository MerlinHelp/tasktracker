package src.lib.time;

import java.time.LocalDateTime;

import src.lib.time.ITime;

public class JavaTime implements ITime {

    public JavaTime() {
    }

    // TODO: Change current time format depending on STYLE chosen
    @Override
    public int getCurrentTime() {
        return getCurrentTimeNanoSeconds();
    }

    public int getCurrentTimeNanoSeconds() {
        return LocalDateTime.now().getNano();
    }
}
