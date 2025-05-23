package src.main;

import static src.lib.main.Task.Status;

import src.lib.main.TaskList;
import src.lib.main.Task;

import java.util.ArrayList;
import java.util.List;



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
                newTask.setDescription(args[1]);
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
                task.setDescription(args[2]);
                break;
            case "delete":
                taskList.deleteTask(Integer.valueOf(args[1]));
                break;
            case "mark-in-progress":
                task.setStatus(Status.INPROGRESS);
                break;
            case "mark-done":
                task.setStatus(Status.DONE);
                break;
        }
    }


    public void runListCommand(String subcommand) {
        String printString = "";
        List<Task> printList = null;
        switch (subcommand) {
            case null:
                printString = taskList.toString();
                break;
            case "done":
                printList = taskList.filterByStatus(Status.DONE);
                break;
            case "todo":
                printList = taskList.filterByStatus(Status.TODO);
                break;
            case "in-progress":
                printList = taskList.filterByStatus(Status.INPROGRESS);
                break;
            default:
        }
        if (printList != null && !printList.isEmpty()) {
            printString = TaskList.printGivenTasks(printList);
        }
        System.out.println(printString);
    }
}
