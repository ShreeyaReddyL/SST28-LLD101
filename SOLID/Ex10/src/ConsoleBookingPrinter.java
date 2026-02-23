public class ConsoleBookingPrinter implements BookingPrinter {
    @Override
    public void printDistance(double km) { System.out.println("DistanceKm=" + km); }
    @Override
    public void printDriver(String driver) { System.out.println("Driver=" + driver); }
    @Override
    public void printPayment(String txn) { System.out.println("Payment=PAID txn=" + txn); }
    @Override
    public void printReceipt(BookingReceipt r) {
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}