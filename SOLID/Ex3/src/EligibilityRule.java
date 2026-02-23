public interface EligibilityRule {
    // Returns the failure reason if the rule is violated, or null if the student passes.
    String check(StudentProfile s);
}