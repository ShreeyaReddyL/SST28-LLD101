public class Demo10 {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));
        
        // Dependency Wiring
        DistanceCalculator dist = new ManhattanDistance();
        DriverAllocator alloc = new CampusDriverAllocator();
        PaymentGateway pay = new CampusPaymentGateway();
        PricingStrategy pricing = new StandardPricing();
        BookingPrinter printer = new ConsoleBookingPrinter();

        TransportBookingService svc = new TransportBookingService(dist, alloc, pay, pricing, printer);
        svc.book(req);
        
        
    }
}