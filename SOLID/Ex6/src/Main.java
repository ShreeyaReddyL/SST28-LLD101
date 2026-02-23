public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification req1 = new Notification("riya@sst.edu", "Welcome", "Hello and welcome to SST!");
        Notification req2 = new Notification("9876543210", "Welcome", "Hello and welcome to SST!");
        Notification req3 = new Notification("9876543210", "Welcome", "Hello and welcome to SST!");

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms = new SmsSender(audit);
        NotificationSender wa = new WhatsAppSender(audit);

        safeSend(email, req1);
        safeSend(sms, req2);
        safeSend(wa, req3);

        System.out.println("AUDIT entries=" + audit.getEntries());
    }

    private static void safeSend(NotificationSender sender, Notification n) {
        try {
            // Polymorphism at work: We rely entirely on the base class contract!
            sender.send(n);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}