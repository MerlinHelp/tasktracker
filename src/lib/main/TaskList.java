package src.lib.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import src.lib.main.Task;

// For now, dummy class
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        task.setId(size());
        tasks.add(task);
    }

    // TODO: Get LocalDateTime.nano()
    public void addTaskWithDescription(String description) {
        Task newTask = new Task(size(), Task.Status.TODO, description, 0, 0);
    }

    public void setTaskIds() {
        IntStream.range(0, size())
            .forEach(idx -> {
                Task currTask = tasks.get(idx);
                currTask.setId(idx);
            });
    }

    public void setTask(int id, String newDescription) {
        Task currTask;
        if ((currTask = getTaskById(id)) != null) {
            currTask.setDescription(newDescription);
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
            currTask.setStatus(newStatus);
        }
    }

    @Override
    public String toString() {
        String taskList = "";
        for (int i = 0; i < tasks.size() - 1; ++i) {
            taskList += tasks.get(i) + "\n";
        }
        return taskList + tasks.get(tasks.size() - 1);
    }
}
