package src.main;

import src.lib.main.TaskList;
import src.lib.main.Task;
import src.lib.main.json.JSONWriter;
import src.lib.test.unit.TaskTests;
import src.main.ExecuteCommand;
import src.main.InputParser;

public class TaskTracker {

    public static void main(String args[]) {
        TaskTests tests = new TaskTests();
        tests.runAllTests();

        InputParser inputParser = new InputParser(args);
        if (!(inputParser.isValidBaseCommand() && inputParser.isValidSubCommand())) {
            System.exit(-1);
        }

        TaskList taskList = new TaskList();
        taskList.addTask(Task.createEmpty());
        taskList.addTaskWithDescription("Make breakfast");

        JSONWriter.writeTaskListToJSON(taskList);
    }

    public static void executeCommand(TaskList taskList, String[] args) {
        ExecuteCommand exec = new ExecuteCommand(taskList);
        exec.execute(args);
    }
}
