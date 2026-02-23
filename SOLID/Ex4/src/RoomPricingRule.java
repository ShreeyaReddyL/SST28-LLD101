import java.util.Map;
import java.util.HashMap;

public class RoomPricingRule implements PricingRule {
    private final Map<Integer, Double> roomRates = new HashMap<>();
    private final double defaultRate;

    public RoomPricingRule(double defaultRate) {
        this.defaultRate = defaultRate;
    }

    public void addRate(int roomType, double rate) {
        roomRates.put(roomType, rate);
    }

    @Override
    public Money calculate(BookingRequest req) {
        return new Money(roomRates.getOrDefault(req.roomType, defaultRate));
    }
}