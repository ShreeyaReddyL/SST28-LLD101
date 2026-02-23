import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);
        
        // Setup configuration (Stretch Goal)
        RuleInput config = new RuleInput();
        
        // Wire up the list of rules
        List<EligibilityRule> rules = List.of(
            new DisciplinaryRule(),
            new CgrRule(config),
            new AttendanceRule(config),
            new CreditsRule(config)
        );

        // Inject dependencies
        EligibilityEngine engine = new EligibilityEngine(new FakeEligibilityStore(), rules, new ReportPrinter());
        
        // Run
        engine.runAndPrint(s);
    }
}