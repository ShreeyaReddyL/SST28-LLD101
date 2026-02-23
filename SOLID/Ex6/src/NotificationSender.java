/**
 * Base contract for sending notifications.
 * * Preconditions: 
 * - Notification must not be null.
 * * Postconditions:
 * - An audit log is always written.
 * - Unsupported fields (like 'subject' for SMS) are safely ignored.
 * * @throws IllegalArgumentException if the 'to' address format is invalid for the specific channel.
 */
public abstract class NotificationSender {
    private final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public final void send(Notification n) {
        if (n == null) throw new IllegalArgumentException("Notification cannot be null");
        
        audit.log("Attempted to send notification to " + n.to);
        deliver(n); // Delegate the specific logic to subclasses
    }

    protected abstract void deliver(Notification n);
}