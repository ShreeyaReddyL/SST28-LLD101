public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    protected void deliver(Notification n) {
        // LSP Fix: We removed the silent truncation! It now preserves data integrity.
        ConsolePreview.print("EMAIL", "to=" + n.to + " subject=" + n.subject + " body=" + n.body);
    }
}