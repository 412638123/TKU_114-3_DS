public class DeliveryTask {
    String taskId;
    String recipient;
    String address;

    public DeliveryTask(String taskId, String recipient, String address) {
        this.taskId = taskId;
        this.recipient = recipient;
        this.address = address;
    }
}