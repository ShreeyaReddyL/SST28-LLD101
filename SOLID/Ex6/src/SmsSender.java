public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    protected void deliver(Notification n) {
        // The base contract explicitly allows ignoring unsupported fields like 'subject'
        ConsolePreview.print("SMS", "to=" + n.to + " body=" + n.body);
    }
}