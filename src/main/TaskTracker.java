package src.main;

import src.lib.main.TaskList;
import src.lib.main.Task;
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
        Task t1 = Task.createEmpty();
        Task t2 = Task.createEmpty();
        taskList.addTask(t1);
        taskList.addTask(t2);

        t1.setId(2);
        t1.setDescription("Make breakfast");
        t2.setStatus(Task.Status.DONE);

        taskList.setTaskIds();

        ExecuteCommand exec = new ExecuteCommand(taskList);
        exec.execute(args);
    }
}
