package src.lib.time;

import src.lib.time.JavaTime;
import src.lib.time.ITimeFormatter;

public class JavaTimeFormatter implements ITimeFormatter {
    enum TimeStyle {
        AMERICAN,
        NONAMERICAN
    }

    private TimeStyle currStyle;
    private JavaTime javaTime;

    public JavaTimeFormatter(JavaTime javaTime) {
        this.javaTime = javaTime; 
        this.currStyle = TimeStyle.AMERICAN;
    }

    public void setTimeFormat(TimeStyle newStyle) {
        this.currStyle = newStyle;
    }

    public String getCurrentTimeString() {
        return "";
    }
}
