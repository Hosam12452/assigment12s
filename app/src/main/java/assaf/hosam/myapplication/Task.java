package assaf.hosam.myapplication;

public class Task {
    private String deadLine;
    String status;
    private String taskName;
    private int taskId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Task(String deadLine, String taskName, int taskId,String status) {
        this.deadLine = deadLine;
        this.taskName = taskName;
        this.taskId = taskId;
        this.status=status;



    }
}
