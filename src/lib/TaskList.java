package src.lib;

import java.util.ArrayList;
import java.util.List;

import src.lib.Task;

// For now, dummy class
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void updateTask(int id, String newDescription) {
        Task currTask;
        if ((currTask = getTaskById(id)) != null) {
            currTask.updateDescription(newDescription);
        }
    }

    public void deleteTask(int id) {
        if (isValidId(id)) {
            tasks.remove(id);
        }
    }
    
    public Task getTaskById(int id) {
        return isValidId(id) ? tasks.get(id) : null;
    }

    public boolean isValidId(int id) {
        return id >= 0 && id < tasks.size();
    }

    public void changeStatus(int id, Task.Status newStatus) {
        Task currTask;
        if ((currTask = getTaskById(id)) != null) {
            currTask.updateStatus(newStatus);
        }
    }

    @Override
    public String toString() {
        String taskList = "";
        for (int i = 0; i < tasks.size(); ++i) {
            taskList += tasks.get(i);
        }
        return taskList;
    }
}
