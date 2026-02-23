public interface BookingPrinter {
    void print(BookingRequest req, Money monthly, Money deposit);
}