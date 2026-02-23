public interface BookingPrinter {
    void printDistance(double km);
    void printDriver(String driver);
    void printPayment(String txn);
    void printReceipt(BookingReceipt r);
}