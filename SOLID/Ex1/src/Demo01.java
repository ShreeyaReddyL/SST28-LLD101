import java.util.Set;

public class Demo01 {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        
        // 1. Dependency Wiring
        StudentRepository db = new FakeDb();
        StudentParser parser = new StudentParser();
        StudentValidator validator = new StudentValidator(Set.of("CSE", "AI", "SWE")); 
        ConsolePrinter printer = new ConsolePrinter();
        
        OnboardingService svc = new OnboardingService(parser, validator, db, printer);

        // 2. Run the main successful input
        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}