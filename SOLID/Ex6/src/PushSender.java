public class PushSender extends NotificationSender {
    public PushSender(AuditLog audit) { super(audit); }

    @Override
    protected void deliver(Notification n) {
        ConsolePreview.print("PUSH", "deviceToken=" + n.to + " msg=" + n.body);
    }
}