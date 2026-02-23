import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        // 1. Dependency Setup
        MenuRepository menu = new InMemoryMenu();
        menu.add(new MenuItem("M1", "Veg Thali", 80.00));
        menu.add(new MenuItem("C1", "Coffee", 30.00));
        menu.add(new MenuItem("S1", "Sandwich", 60.00));

        PolicyRegistry policies = new PolicyRegistry();
        
        // Configuration: Register Student Rules
        policies.registerTaxPolicy("student", () -> 5.0);
        policies.registerDiscountPolicy("student", (subtotal, lines) -> subtotal >= 180.0 ? 10.0 : 0.0);
        
        // Configuration: Register Staff Rules
        policies.registerTaxPolicy("staff", () -> 2.0);
        policies.registerDiscountPolicy("staff", (subtotal, lines) -> lines >= 3 ? 15.0 : 5.0);

        InvoiceFormatter formatter = new StandardInvoiceFormatter();
        InvoiceStore store = new FileStore();

        CafeteriaSystem sys = new CafeteriaSystem(menu, policies, formatter, store);

        // 2. Execute Student Order (Original Requirement)
        List<OrderLine> order1 = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );
        sys.checkout("student", order1);

        // 3. Execute Staff Order (Stretch Goal)
        System.out.println("\n--- Stretch Goal: Staff Order ---");
        List<OrderLine> order2 = List.of(
                new OrderLine("S1", 1),
                new OrderLine("C1", 1),
                new OrderLine("M1", 1)
        );
        sys.checkout("staff", order2);
    }
}