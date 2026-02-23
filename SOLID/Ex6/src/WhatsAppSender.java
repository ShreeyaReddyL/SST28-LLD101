public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    protected void deliver(Notification n) {
        // Enforcing the precondition explicitly allowed by the base contract's @throws clause
        if (!n.to.startsWith("+")) {
            throw new IllegalArgumentException("WA ERROR: phone must start with + and country code");
        }
        ConsolePreview.print("WA", "to=" + n.to + " body=" + n.body);
    }
}