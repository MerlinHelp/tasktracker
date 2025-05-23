package src.lib.main.json;

import java.util.Formatter;

import src.lib.main.Task;
import src.lib.main.TaskList;

public class JSONStringBuilder {

    public static String taskToJSON(Task task) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("{%n");
        formatter.format("}");
        formatter.flush();
        return sb.toString();
    }
    
    public static String taskListToJSON(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("{%n");
        formatter.format("}");
        formatter.flush();
        return sb.toString();
    }
}
