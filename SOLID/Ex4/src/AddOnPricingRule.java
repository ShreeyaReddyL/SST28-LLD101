import java.util.Map;
import java.util.HashMap;

public class AddOnPricingRule implements PricingRule {
    private final Map<AddOn, Double> addOnRates = new HashMap<>();

    public void addRate(AddOn addOn, double rate) {
        addOnRates.put(addOn, rate);
    }

    @Override
    public Money calculate(BookingRequest req) {
        double total = 0.0;
        for (AddOn a : req.addOns) {
            total += addOnRates.getOrDefault(a, 0.0);
        }
        return new Money(total);
    }
}