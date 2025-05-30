package src.main;

import src.lib.main.TaskList;
import src.lib.main.Task;
import src.lib.main.json.JSONParser;
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

        //TaskList tasksToWrite = new TaskList();
        //tasksToWrite.addTask(Task.createEmpty());
        //tasksToWrite.addTaskWithDescription("Facts breakfast");
        //JSONWriter.writeTaskListToJSON(tasksToWrite);

        TaskList currTasks = JSONParser.jsonToTaskList();
        executeCommand(currTasks, args);
    }

    public static void executeCommand(TaskList taskList, String[] args) {
        ExecuteCommand exec = new ExecuteCommand(taskList);
        exec.execute(args);
    }
}
