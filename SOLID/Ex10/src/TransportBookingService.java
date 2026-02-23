public class TransportBookingService {
    private final DistanceCalculator dist;
    private final DriverAllocator alloc;
    private final PaymentGateway pay;
    private final PricingStrategy pricing;
    private final BookingPrinter printer;

    // DIP: Injects dependencies via constructor
    public TransportBookingService(DistanceCalculator dist, DriverAllocator alloc, 
                                   PaymentGateway pay, PricingStrategy pricing, 
                                   BookingPrinter printer) {
        this.dist = dist;
        this.alloc = alloc;
        this.pay = pay;
        this.pricing = pricing;
        this.printer = printer;
    }

    public void book(TripRequest req) {
        double km = dist.km(req.from, req.to);
        printer.printDistance(km);

        String driver = alloc.allocate(req.studentId);
        printer.printDriver(driver);

        double fare = pricing.calculateFare(km);
        String txn = pay.charge(req.studentId, fare);
        printer.printPayment(txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        printer.printReceipt(r);
    }
}