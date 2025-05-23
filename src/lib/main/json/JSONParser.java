package src.lib.main.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import src.lib.main.TaskList;

public class JSONParser {
    private static final String path = "tasks.json";

    public JSONParser() {
    }

    public static String getJSONString() {
        String jsonString = "";
        try (BufferedReader bfr = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = bfr.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            jsonString = sb.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return jsonString;
    }

    public static TaskList jsonToTaskList() {
        String jsonString = getJSONString();
        if (jsonString.isEmpty()) return null;

        String tasks = jsonString.substring(
                jsonString.indexOf("[") + 2,
                jsonString.indexOf("]") - 2);
        System.out.print(tasks);
        return new TaskList();
    }
}
