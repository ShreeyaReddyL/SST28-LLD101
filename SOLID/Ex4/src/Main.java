import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        
        // 1. Configure Rules
        RoomPricingRule roomRule = new RoomPricingRule(16000.0);
        roomRule.addRate(LegacyRoomTypes.SINGLE, 14000.0);
        roomRule.addRate(LegacyRoomTypes.DOUBLE, 15000.0);
        roomRule.addRate(LegacyRoomTypes.TRIPLE, 12000.0);

        AddOnPricingRule addOnRule = new AddOnPricingRule();
        addOnRule.addRate(AddOn.MESS, 1000.0);
        addOnRule.addRate(AddOn.LAUNDRY, 500.0);
        addOnRule.addRate(AddOn.GYM, 300.0);

        // Stretch Goal integration: Pass 'true' to dynamically apply a 500 late fee
        LateFeePricingRule lateFeeRule = new LateFeePricingRule(false); 

        List<PricingRule> rules = List.of(roomRule, addOnRule, lateFeeRule);

        // 2. Wire Dependencies
        HostelFeeCalculator calc = new HostelFeeCalculator(
            rules, 
            new FakeBookingRepo(), 
            new ConsoleReceiptPrinter()
        );

        // 3. Execute
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        calc.process(req);
    }
}