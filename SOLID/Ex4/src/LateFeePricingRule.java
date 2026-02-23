// Fulfills the stretch goal! We can add this rule to the calculator 
// without editing the calculator's core loop at all.
public class LateFeePricingRule implements PricingRule {
    private final boolean applyLateFee;
    
    public LateFeePricingRule(boolean applyLateFee) {
        this.applyLateFee = applyLateFee;
    }

    @Override
    public Money calculate(BookingRequest req) {
        return applyLateFee ? new Money(500.0) : new Money(0.0);
    }
}