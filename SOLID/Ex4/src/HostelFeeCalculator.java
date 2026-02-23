import java.util.*;

public class HostelFeeCalculator {
    private final List<PricingRule> rules;
    private final BookingRepository repo;
    private final BookingPrinter printer;

    public HostelFeeCalculator(List<PricingRule> rules, BookingRepository repo, BookingPrinter printer) {
        this.rules = rules;
        this.repo = repo;
        this.printer = printer;
    }

    public void process(BookingRequest req) {
        Money monthly = new Money(0.0);
        
        // OCP Compliant: Calculates totals dynamically based on the injected rules
        for (PricingRule rule : rules) {
            monthly = monthly.plus(rule.calculate(req));
        }
        
        Money deposit = new Money(5000.00);

        printer.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}