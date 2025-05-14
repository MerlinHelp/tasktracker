package src.lib.main;

import java.util.HashMap;
import java.util.Map;

import src.lib.time.JavaTime;

public class Task {
    private int id;
    private Status status;
    private String description;
    private int createdAt;
    private int updatedAt;
    private final String taskFormatString = "%-11s [ID: %d]: %s";

    public enum Status {
        TODO,
        INPROGRESS,
        DONE
    }

    private static HashMap<Status, String> statusToString = new HashMap<>(
        Map.of(
            Status.TODO, "TODO",
            Status.INPROGRESS, "INPROGRESS",
            Status.DONE, "DONE"
        ));
                   

    public Task(int id,
                Status status,
                String description,
                int createdAt,
                int updatedAt) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // We need observers badly. Need to update updatedAt on any other update.
    public static Task createEmpty() {
        return new Task(-1, Task.Status.TODO, "", 0, -1);
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public Task.Status getStatus() {
        return status;
    }
    public int getCreatedAt() {
        return createdAt;
    }
    public int getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(int newId) {
        this.id = newId;
    }
    
    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setUpdatedAtTime(int newTime) {
        this.updatedAt = newTime;
    }

    @Override
    public String toString() {
        return String.format(taskFormatString, status, id, description);
    }

}
