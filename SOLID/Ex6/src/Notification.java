public class Notification {
    public final String to;
    public final String subject;
    public final String body;

    public Notification(String to, String subject, String body) {
        this.to = to; 
        this.subject = subject; 
        this.body = body;
    }
}