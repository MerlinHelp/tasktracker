package src.lib.main.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.lib.main.Task;
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
        }
        return jsonString;
    }

    public static TaskList jsonToTaskList() {
        TaskList ret = new TaskList();

        String jsonString = getJSONString();
        if (jsonString.isEmpty()) return ret;

        String tasks = jsonString.substring(
                jsonString.indexOf("[") + 2,
                jsonString.indexOf("]") - 2);
        String regex =  "\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        tasks = tasks.replaceAll(regex, "");

        String[] taskArr = tasks.split("[{}]", -1);

        // Splitting tasks creates blanks every match
        try {
            for (int i = 1; i < taskArr.length; i += 2) {
                String currTask = taskArr[i];

                Object[] idInfo = jsonIntToJavaString(currTask, 0, "id");
                int id = ((Integer) idInfo[0]).intValue() - 1;

                Object[] statusInfo = jsonStringToJavaString(currTask, (Integer) idInfo[1], "status");
                String status = (String) statusInfo[0];

                Object[] descriptionInfo = jsonStringToJavaString(currTask, (Integer) statusInfo[1], "description");
                String description = (String) descriptionInfo[0];

                Object[] createdAtInfo = jsonIntToJavaString(currTask, (Integer) descriptionInfo[1], "createdAt");
                int createdAt = ((Integer) createdAtInfo[0]).intValue();

                Object[] updatedAtInfo = jsonIntToJavaString(currTask, (Integer) createdAtInfo[1], "updatedAt");
                int updatedAt = ((Integer) updatedAtInfo[0]).intValue();

                Task taskRead = new Task(id, Task.getStatusFromString(status), description, createdAt, updatedAt);
                ret.addTask(taskRead);
            }
        } catch(Exception e) {
            throw(e);
        }
        return ret;
    }

    private static Object[] jsonIntToJavaString(String taskString, int startIdx, String key) {
        int valueStartIdx = startIdx + String.format("\"%s\":", key).length();
        int valueEndIdx = taskString.indexOf("\"", valueStartIdx);

        Object[] ret = new Object[2];
        if (valueEndIdx == -1) {
            ret[0] = Integer.valueOf(taskString.substring(valueStartIdx));
        } else {
            ret[0] = Integer.valueOf(taskString.substring(valueStartIdx, valueEndIdx));
        }
        ret[1] = Integer.valueOf(valueEndIdx);
        return ret;
    }

    private static Object[] jsonStringToJavaString(String taskString, int startIdx, String key) {
        int statusStartIdx = startIdx + String.format("\"%s\":", key).length();
        int statusEndIdx = statusStartIdx + 1;
        while (true) {
            statusEndIdx = taskString.indexOf('\"', statusEndIdx);
            char c = taskString.charAt(statusEndIdx - 1);
            if(c != '\\') {
                break;
            }
        }

        Object[] ret = new Object[2];
        ret[0] = taskString.substring(statusStartIdx + 1, statusEndIdx);
        ret[1] = Integer.valueOf(statusEndIdx + 1);
        return ret; 
    }
}
