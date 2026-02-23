public class Main {
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
        
        // STRETCH GOAL DEMONSTRATION:
        // If we wanted to test this without calling real services, we can just inject our mocks!
        // TransportBookingService mockSvc = new TransportBookingService(dist, new MockDriverAllocator(), new MockPaymentGateway(), pricing, printer);
    }
}