package src.lib;

import java.util.HashMap;
import java.util.Map;

public class Task {
    private int id;
    private Status status;
    private String description;
    private int createdAt;
    private int updatedAt;
    private final String taskFormatString = "%-11s [ID: %d]: %s%n";

    enum Status {
        TODO,
        INPROGRESS,
        DONE
    }

    private static HashMap<Status, String> statusToString = new HashMap<>(
            Map.of(Status.TODO, "TODO",
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

    public static Task createEmpty() {
        return new Task(-1, Status.TODO, "", 0, 0);
    }

    public void updateId(int newId) {
        this.id = newId;
    }
    
    public void updateStatus(Status newStatus) {
        this.status = newStatus;
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return String.format(taskFormatString, status, id, description);
    }

}
