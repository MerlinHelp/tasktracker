package src.lib.main.json;

import java.util.Formatter;

import src.lib.main.Task;
import src.lib.main.TaskList;

public class JSONStringBuilder {
    private static int TABSPACES = 4;
    private static int currIndent = 1;

    public static void formatEntry(Formatter formatter, String key, int val) {
            formatter.format("\"%s\": %d".indent(TABSPACES * (currIndent + 1)), key, val);
    }
    public static void formatEntry(Formatter formatter, String key, String val) {
            formatter.format("\"%s\": \"%s\"".indent(TABSPACES * (currIndent + 1)), key, val);
    }

    public static String taskToJSON(Task task) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        formatEntry(formatter, "id", task.getId());
        formatEntry(formatter, "status", task.getStatusString());
        formatEntry(formatter, "description", task.getDescription());
        formatEntry(formatter, "createdAt", task.getCreatedAt());
        formatEntry(formatter, "updatedAt", task.getUpdatedAt());

        formatter.flush();
        return sb.toString();
    }
    
    public static String taskListToJSON(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        formatter.format("{%n");
        formatter.format("\"tasks\": [".indent(TABSPACES * currIndent++));

        Task[] tasks = taskList.getTasks();
        for (int i = 0; i < tasks.length; ++i) {
            formatter.format("{".indent(TABSPACES * currIndent));
            formatter.format("%s", taskToJSON(tasks[i]));
            formatter.format(("}" + (i != tasks.length - 1 ? "," : "")).indent(TABSPACES * currIndent));
        }

        formatter.format("]".indent(TABSPACES * --currIndent));
        formatter.format("}");

        formatter.flush();
        return sb.toString();
    }
}
