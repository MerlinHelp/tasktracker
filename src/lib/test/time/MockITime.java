package src.lib.test.time;

import src.lib.time.ITime;

public class MockITime implements ITime {
    private int mockTimeNano;

    public MockITime() {
        this.mockTimeNano = 0;
    }

    public int getCurrentTime() {
        return this.mockTimeNano;
    }

    public void setMockTimeNano(int newMockTimeNano) {
        this.mockTimeNano = newMockTimeNano;
    }

    public void addMockTimeNano(int mockTimeIncrement) {
        this.mockTimeNano += mockTimeIncrement;
    }
}
