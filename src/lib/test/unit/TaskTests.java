package src.lib.test.unit;

import static src.lib.main.Task.Status;
import static src.lib.test.Asserts.assertEquals;
import static src.lib.test.Asserts.assertNotEquals;

import src.lib.main.Task;
import src.lib.test.time.MockITime;

public class TaskTests {
    private int taskId1 = 0;
    private int taskId2 = 1;

    private String descrip1 = "Buy groceries";
    private String descrip2 = "Buy groceries and cook dinner";

    public TaskTests() {}

    public void runAllTests() {
        testConstructorValid();
        testCreateEmpty();
        testGetAndSet();
    }

    public void testConstructorValid() {
        MockITime mockTime = new MockITime();

        mockTime.setMockTimeNano(0);

        Task task1 = new Task(
            taskId1,
            Status.TODO,
            descrip1,
            mockTime.getCurrentTime(),
            -1
        );

        mockTime.setMockTimeNano(10);

        assertEquals(taskId1, task1.getId());
        assertEquals(Status.TODO, task1.getStatus());
        assertEquals(descrip1, task1.getDescription());
        assertEquals(0, task1.getCreatedAt());
        assertEquals(-1, task1.getUpdatedAt());
    }

    public void testCreateEmpty() {
        MockITime mockTime = new MockITime();
        mockTime.setMockTimeNano(5);

        Task task1 = Task.createEmpty();

        mockTime.setMockTimeNano(10);

        assertEquals(-1, task1.getId());
        assertEquals(Status.TODO, task1.getStatus());
        assertEquals("", task1.getDescription());
        assertEquals(0, task1.getCreatedAt());
        assertEquals(-1, task1.getUpdatedAt());
    }

    public void testGetAndSet() {
        MockITime mockTime = new MockITime();
        mockTime.setMockTimeNano(5);

        Task task1 = Task.createEmpty();

        mockTime.setMockTimeNano(10);

        task1.setId(42);
        task1.setStatus(Status.DONE);
        task1.setDescription(descrip2);
        task1.setUpdatedAtTime(mockTime.getCurrentTime());
        
        assertEquals(42, task1.getId());
        assertEquals(Status.DONE, task1.getStatus());
        assertEquals(descrip2, task1.getDescription());
        assertEquals(0, task1.getCreatedAt());
        assertEquals(mockTime.getCurrentTime(), task1.getUpdatedAt());
    }
}
