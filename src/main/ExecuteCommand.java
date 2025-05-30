package src.main;

import static src.lib.main.Task.Status;

import src.lib.main.TaskList;
import src.lib.main.json.JSONParser;
import src.lib.main.json.JSONWriter;
import src.lib.main.Task;

import java.util.ArrayList;
import java.util.List;



public class ExecuteCommand {

    private TaskList taskList;

    public ExecuteCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    // TODO: Give error message when modifying a task that does not exist
    public void execute(String[] args) {
        switch (args[0]) {
            case "update":
            case "delete":
            case "mark-in-progress":
            case "mark-done":
                Integer taskId = Integer.valueOf(args[1]).intValue() - 1;
                Task currTask = taskList.getTaskById(taskId);
                if (currTask == null) System.exit(-1);
                modifyTask(currTask, args);
                break;
            case "add":
                taskList.addTaskWithDescription(args[1]);
                break;
            case "list":
                runListCommand(args.length == 2 ? args[1] : null);
                return;
        }

        JSONWriter.writeTaskListToJSON(taskList);
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
                printList = taskList.getTasksList();
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
            printString = TaskList.getTaskListString(printList);
        }
        System.out.println(printString);
    }
}
