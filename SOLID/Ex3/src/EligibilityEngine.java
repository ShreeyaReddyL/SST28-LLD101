import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;
    private final ReportPrinter printer;

    public EligibilityEngine(FakeEligibilityStore store, List<EligibilityRule> rules, ReportPrinter printer) {
        this.store = store;
        this.rules = rules;
        this.printer = printer;
    }

    public void runAndPrint(StudentProfile s) {
        EligibilityEngineResult r = evaluate(s);
        printer.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        // Iterates through rules instead of a giant if/else chain
        for (EligibilityRule rule : rules) {
            String failureReason = rule.check(s);
            if (failureReason != null) {
                status = "NOT_ELIGIBLE";
                reasons.add(failureReason);
                break; // We break to exactly match the original `else if` behavior
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}