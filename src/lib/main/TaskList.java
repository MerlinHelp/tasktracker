package src.lib.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;

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
        setTaskIds();
    }

    // TODO: Get LocalDateTime.nano()
    public void addTaskWithDescription(String description) {
        Task newTask = new Task(size(), Task.Status.TODO, description, 0, 0);
        tasks.add(newTask);
        setTaskIds();
    }


    public Task[] getTasks() {
        Task[] tasks = this.tasks.toArray(new Task[this.tasks.size()]);
        return tasks;
    }

    public List<Task> getTasksList() {
        return tasks;
    }

    public List<Task> filterByStatus(Task.Status status) {
        return tasks
            .stream()
            .filter(t -> t.getStatus() == status)
            .collect(Collectors.toList());
    }

    public void setTaskIds() {
        IntStream.range(0, size())
            .forEach(idx -> {
                Task currTask = tasks.get(idx);
                currTask.setId(idx);
            });
    }

    public void updateTaskDescription(int id, String newDescription) {
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


    public static String getTaskListString(List<Task> tasks) {
        String taskList = "";
        for (int i = 0; i < tasks.size() - 1; ++i) {
            Task currTask = tasks.get(i);
            currTask.setId(i + 1);
            taskList += currTask + "\n";
            currTask.setId(i);
        }

        Task lastTask = tasks.get(tasks.size() - 1);
        lastTask.setId(tasks.size());
        taskList += lastTask;
        lastTask.setId(tasks.size() - 1);

        return taskList;
    }


    @Override
    public String toString() {
        return TaskList.getTaskListString(this.tasks);
    }
}
