public class AuditLog {
    private int entries = 0;
    public void log(String msg) { entries++; }
    public int getEntries() { return entries; }
}