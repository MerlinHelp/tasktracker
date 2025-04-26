package src.main;

import src.main.InputParser;
import src.lib.TaskList;
import src.lib.Task;

public class TaskTracker {
    public static void main(String args[]) {
        InputParser inputParser = new InputParser(args);
        inputParser.isValidBaseCommand();
        inputParser.isValidSubCommand();

        TaskList taskList = new TaskList();
        Task t1 = Task.createEmpty();
        Task t2 = Task.createEmpty();
        taskList.addTask(t1);
        taskList.addTask(t2);
        System.out.println(taskList);
    }
}
