package src.lib.main.json;

import java.io.FileWriter;
import java.io.IOException;

import src.lib.main.TaskList;
import src.lib.main.json.JSONStringBuilder;

public class JSONWriter {
    private static String path = "tasks.json";

    public JSONWriter() {
    }

    public static void writeTaskListToJSON(TaskList taskList) {
        writeToJSON(JSONStringBuilder.taskListToJSON(taskList));
    }

    public static void writeToJSON(String jsonString) {
        try {
            FileWriter fw = new FileWriter(JSONWriter.path);
            fw.write(jsonString);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
