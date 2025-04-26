package src.main;

import src.lib.TaskList;
import src.lib.Task;

public class ExecuteCommand {

    private TaskList taskList;

    public ExecuteCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    public void execute(String[] args) {
        switch (args[0]) {
            case "update":
            case "delete":
            case "mark-in-progress":
            case "mark-done":
                Integer taskId = Integer.valueOf(args[1]);
                Task currTask = taskList.getTaskById(taskId);
                modifyTask(currTask, args);
                break;
            case "add":
                Task newTask = Task.createEmpty();
                newTask.updateDescription(args[1]);
                taskList.addTask(newTask);
                break;
            case "list":
                runListCommand(args.length == 2 ? args[1] : null);
                break;
        }

    }
    public void modifyTask(Task task, String args[]) {
        switch (args[0]) {
            case "update":
                task.updateDescription(args[2]);
                break;
            case "delete":
                taskList.deleteTask(Integer.valueOf(args[1]));
                break;
            case "mark-in-progress":
                task.updateStatus(Task.Status.INPROGRESS);
                break;
            case "mark-done":
                task.updateStatus(Task.Status.DONE);
                break;
        }
    }


    public void runListCommand(String subcommand) {
        String printString = "";
        switch (subcommand) {
            case null:
                printString = taskList.toString();
                break;
            default:
        }
        System.out.println(printString);
    }
}
