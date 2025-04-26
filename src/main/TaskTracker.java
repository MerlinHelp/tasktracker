package src.main;

import src.lib.TaskList;
import src.lib.Task;
import src.main.ExecuteCommand;
import src.main.InputParser;

public class TaskTracker {
    public static void main(String args[]) {
        InputParser inputParser = new InputParser(args);
        if (!(inputParser.isValidBaseCommand() && inputParser.isValidSubCommand())) {
            System.exit(-1);
        }

        TaskList taskList = new TaskList();
        Task t1 = Task.createEmpty();
        Task t2 = Task.createEmpty();
        taskList.addTask(t1);
        taskList.addTask(t2);

        ExecuteCommand exec = new ExecuteCommand(taskList);
        exec.execute(args);
    }
}
